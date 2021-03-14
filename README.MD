# Tracker Of Expenses
The simple tracker of expenses, created with Spring, RestAPI,  database PostgreSQL, hibernate, mockito, junit, lombok in Java14. I made this project to learn and practice technologies mentioned above.
## Requirements
- jdk 14
- maven
- postgresql
### How to build
```bash
mvn clean install
```
### How to run
```bash
mvn spring-boot:run
```
### Configuration example
```yaml
spring: 
  datasource: 
    username: "USERNAME" 
    password: "PASSWORD"  
    url: "JDBC_URL" 
    driverclass: "DRIVER"
```