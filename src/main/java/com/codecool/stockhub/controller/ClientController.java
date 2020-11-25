package com.codecool.stockhub.controller;

import com.codecool.stockhub.logger.ExceptionLog;
import com.codecool.stockhub.model.*;
import com.codecool.stockhub.repository.ClientRepository;
import com.codecool.stockhub.repository.StockRepository;
import com.codecool.stockhub.security.JwtTokenServices;
import com.codecool.stockhub.service.ClientList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

import static com.codecool.stockhub.model.ApplicationUserRole.CLIENT;


@RestController
public class ClientController {

    private static final String ORIGIN = "http://localhost:3000";

    @Autowired
    private ClientList clientList;

    @Autowired
    StockRepository stockRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    private ExceptionLog exceptionLog;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenServices jwtTokenServices;

    public ClientController(AuthenticationManager authenticationManager, JwtTokenServices jwtTokenServices, ClientRepository users) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenServices = jwtTokenServices;
    }

    @CrossOrigin(origins = ORIGIN, allowCredentials = "true")
    @PostMapping(value = "/login")
    public ResponseEntity update(@RequestBody UserCredentials data, HttpServletResponse response) {
        try {
            String email = data.getEmail();
            String password = data.getPassword();
            Client client = clientList.checkIfCanLogIn(email, password);

            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            List<String> authorities = authentication.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            String token = jwtTokenServices.createToken(password, authorities);

            Cookie cookie = new Cookie("token", token);
            cookie.setHttpOnly(true);
            response.addCookie(cookie);

            Map<Object, Object> model = new HashMap<>();
            model.put("email", email);
            model.put("authorities", authorities);
            model.put("token", token);
            model.put("address", client.getAddress());
            model.put("name", client.getName());
            model.put("phoneNumber", client.getPhoneNumber());
            model.put("mobileNumber", client.getMobileNumber());
            response.setStatus(200);

            return ResponseEntity.ok(model);
        } catch (IllegalArgumentException e) {
            response.setStatus(400);
            exceptionLog.log(e);
            throw new IllegalArgumentException("Invalid email or password");
        }
    }

    @CrossOrigin(origins = ORIGIN, allowCredentials = "true")
    @PostMapping(value = "/add")
    public void addUser(@RequestBody Client client, HttpServletResponse response, HttpServletRequest request) {
        try {
            client.setAuthorities(CLIENT.getGrantedAuthorities());
            clientList.registerUser(client);
            response.setStatus(200);
        } catch (IllegalArgumentException e) {
            response.setStatus(400);
            exceptionLog.log(e);
            throw new IllegalArgumentException("Invalid user");

        } catch (NullPointerException e) {
            response.setStatus(400);
            throw new NullPointerException("User not created");
        }
    }

    @GetMapping("/users")
    public List<Client> getUsers(HttpServletResponse response){
        try {
            response.setStatus(200);
            return clientList.getUsers();
        } catch (IllegalArgumentException e) {
            response.setStatus(400);
            exceptionLog.log(e);
            throw new IllegalArgumentException("Illegal arguments in user list");
        } catch (IndexOutOfBoundsException e) {
            response.setStatus(400);
            exceptionLog.log(e);
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
    }

    @CrossOrigin(origins = ORIGIN,  allowCredentials = "true")
    @PostMapping(value = "/user")
    public boolean isUserExist(String email, HttpServletResponse response) {
        try {
            response.setStatus(200);
            return clientList.isUserExist(email);
        } catch (IllegalArgumentException e) {
            response.setStatus(400);
            exceptionLog.log(e);
            throw new IllegalArgumentException("Email in invalid format");
        }
    }


    @CrossOrigin(origins = ORIGIN, allowCredentials = "true")
    @PostMapping(value = "/active/set-phone-number")
    public void setPhoneNumber(@RequestBody ProfileDetailCredential credentials, HttpServletResponse response) {
        try {
            String phoneNumber = credentials.getDetail();
            Client client = clientList.getLoggedInUser();
            client.setPhoneNumber(phoneNumber);
            clientList.updateClient(client);
        } catch (IllegalArgumentException e) {
            response.setStatus(400);
            exceptionLog.log(e);
            throw new IllegalArgumentException("Phone number in invalid format");
        }
        response.setStatus(200);
    }

    @CrossOrigin(origins = ORIGIN, allowCredentials = "true")
    @PostMapping(value = "/active/set-mobile-number")
    public void setMobileNumber(@RequestBody ProfileDetailCredential credentials, HttpServletResponse response) {
        try {
            String mobileNumber = credentials.getDetail();
            Client client = clientList.getLoggedInUser();
            client.setMobileNumber(mobileNumber);
            clientList.updateClient(client);
        } catch (IllegalArgumentException e) {
            response.setStatus(400);
            exceptionLog.log(e);
            throw new IllegalArgumentException("Mobile number in invalid format");
        }
        response.setStatus(200);
    }

    @CrossOrigin(origins = ORIGIN, allowCredentials = "true")
    @PostMapping(value = "/active/set-address")
    public void setAddress(@RequestBody ProfileDetailCredential credentials, HttpServletResponse response) {
        try {
            String address = credentials.getDetail();
            Client client = clientList.getLoggedInUser();
            client.setAddress(address);
            clientList.updateClient(client);
        } catch (IllegalArgumentException e) {
            response.setStatus(400);
            exceptionLog.log(e);
            throw new IllegalArgumentException("Address number in invalid format");
        }
        response.setStatus(200);
    }

    @CrossOrigin(origins = ORIGIN, allowCredentials = "true")
    @PostMapping(value = "/buy")
    public void buyStock(@RequestBody StockCredential stock, HttpServletResponse response) {
        try {
            Stock currentStock = Stock.builder()
                    .imageLink(stock.getImageLink())
                    .name(stock.getName())
                    .price(stock.getPrice())
                    .symbol(stock.getSymbol())
                    .build();
            System.out.println(stock);
            Client client = clientList.getLoggedInUser();

            Set<Stock> stocks = new HashSet<>();
            currentStock.setClient(client);
            stockRepository.saveAndFlush(currentStock);
            stocks.add(currentStock);
            client.setStocks(stocks);
            clientList.registerUser(client);
            System.out.println(client);

        } catch (IllegalArgumentException e) {
            response.setStatus(400);
            exceptionLog.log(e);
            throw new IllegalArgumentException("Invalid user");

        } catch (NullPointerException e) {
            response.setStatus(400);
            throw new NullPointerException("User not created");
        }
    }

    @CrossOrigin(origins = ORIGIN,  allowCredentials = "true")
    @GetMapping("/client/active")
    public List<Stock> getActiveClientStocks(HttpServletResponse response){
        try {
            response.setStatus(200);
            return stockRepository.getStocksByLoggedInClient(clientList.getLoggedInUser().getId());
        } catch (IllegalArgumentException e) {
            response.setStatus(400);
            exceptionLog.log(e);
            throw new IllegalArgumentException();
        }
    }

    @CrossOrigin(origins = ORIGIN,  allowCredentials = "true")
    @GetMapping("/client/{id}")
    public List<Stock> getClientStocks(@PathVariable Long id, HttpServletResponse response){
        try {
            response.setStatus(200);
            return stockRepository.getStocksByClient_Id(id);
        } catch (IllegalArgumentException e) {
            response.setStatus(400);
            exceptionLog.log(e);
            throw new IllegalArgumentException("Invalid id!");
        }

    }
}
