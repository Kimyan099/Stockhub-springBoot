package com.codecool.stockhub.controller;

import com.codecool.stockhub.logger.ExceptionLog;
import com.codecool.stockhub.model.Client;
import com.codecool.stockhub.model.Stock;
import com.codecool.stockhub.model.UserCredentials;
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


@RestController
public class ClientController {

    //private static final String ORIGIN = "http://localhost:3000";
    private static final String ORIGIN = "*";

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
        System.out.println(data);
        try {
            String email = data.getEmail();
            String password = data.getPassword();
            Client client = clientList.checkIfCanLogIn(email, password);

            //Generate jwt
            // authenticationManager.authenticate calls loadUserByUsername in CustomUserDetailsService

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

            //return client;
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

    @CrossOrigin(origins = ORIGIN)
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

    @CrossOrigin(origins = ORIGIN)
    @PostMapping(value = "/active/set-phone-number")
    public void setPhoneNumber(String phoneNumber, HttpServletResponse response) {
        try {
            response.setStatus(200);
            Client client = clientList.getLoggedInUser();
            client.setPhoneNumber(phoneNumber);
            clientList.updateClient(client);
        } catch (IllegalArgumentException e) {
            response.setStatus(400);
            exceptionLog.log(e);
            throw new IllegalArgumentException("Phone number in invalid format");
        }
    }

    @CrossOrigin(origins = ORIGIN)
    @PostMapping(value = "/active/set-mobile-number")
    public void setMobileNumber(String mobileNumber, HttpServletResponse response) {
        try {
            response.setStatus(200);
            Client client = clientList.getLoggedInUser();
            client.setMobileNumber(mobileNumber);
            clientList.updateClient(client);
        } catch (IllegalArgumentException e) {
            response.setStatus(400);
            exceptionLog.log(e);
            throw new IllegalArgumentException("Mobile number in invalid format");
        }
    }

    @CrossOrigin(origins = ORIGIN)
    @PostMapping(value = "/active/set-address")
    public void setAddress(String address, HttpServletResponse response) {
        try {
            response.setStatus(200);
            Client client = clientList.getLoggedInUser();
            client.setAddress(address);
            clientList.updateClient(client);
        } catch (IllegalArgumentException e) {
            response.setStatus(400);
            exceptionLog.log(e);
            throw new IllegalArgumentException("Mobile number in invalid format");
        }
    }

    @CrossOrigin(origins = ORIGIN)
    @PostMapping(value = "/buy")
    public void buyStock(@RequestBody Stock stock, HttpServletResponse response) {
        try {
            response.setHeader("Access-Control-Allow-Origin", "*");
            System.out.println(stock);
            Client client = clientList.getLoggedInUser();

            Set<Stock> stocks = new HashSet<>();
            stock.setClient(client);
            stockRepository.saveAndFlush(stock);
            stocks.add(stock);
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

    @CrossOrigin(origins = ORIGIN)
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

    @CrossOrigin(origins = ORIGIN)
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
