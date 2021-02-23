# StockHub

## Requirements to run the application:
- installation of Java 11 (https://www.oracle.com/java/)
- installation of development environment, preferably Intellij Idea (https://www.jetbrains.com/idea/)
- installation of relational-database, preferably Postgresql (https://www.postgresql.org/)

## How to run the application?:
- clone repository to your chosen directory
- open it with IDE
- wait for the IDE to automatically download all the proper libraries needed
- create a database wih the desired relational-database
- connect you database with you IDE via environment variables, for Intellij Idea here is a detailed descripttion how to do it (https://www.jetbrains.com/help/idea/connecting-to-a-database.html#connect-to-amazon-redshift)
- add environment variables, in Intellij Idea just click on StockhubApplication next to run button -> edit configurations -> environment/environment variables
- run the application
- follow frontend instructions -> The frontend is available on this repo link: https://github.com/Kimyan099/Stockhub

## What does the application do?:
This is the backend code of a fullstack application called Stockhub developed over 4 sprints in a team of 4 people. 
The application shows stock related charts and news and company profiles. Data is obtained from finnhub API and tradingview embedded codes.
It uses Spring boot, Spring security and JWT, PostgreSQL, Hibernate, Zuul and Eureka on the backend and React (hooks, context) on the frontend.

### Technologies
- Spring/Spring boot
- Spring security
- JWT
- PostgreSQL
- Hibernate
- Zuul, Eureka
- React


### Features
- Register/Login
- User verification and authorization using JWT
- Buying stocks
- Searching for general news
- Searching for companies
- Displaying charts and related information for selected companies
- update profile details


### Main page
<img width="1228" alt="Screenshot 2021-01-11 at 15 12 50" src="https://user-images.githubusercontent.com/22557066/104194483-b0b15e00-5421-11eb-994b-ab4cac577e49.png">

### Mobile mode
<img width="314" alt="Screenshot 2021-01-11 at 15 13 22" src="https://user-images.githubusercontent.com/22557066/104194583-ce7ec300-5421-11eb-94ba-657f3d718b81.png">

### Stock chart and companies
<img width="822" alt="Screenshot 2021-01-11 at 15 14 17" src="https://user-images.githubusercontent.com/22557066/104194682-eb1afb00-5421-11eb-8d96-06f386cc6d74.png">

### General news
<img width="1397" alt="Screenshot 2021-01-11 at 15 26 54" src="https://user-images.githubusercontent.com/22557066/104194753-038b1580-5422-11eb-8a1b-11183813a853.png">
