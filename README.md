# ElectronicQueue

System allows creating queues any registered user can take tickets to. Creator of the queue is moderator, s/he moves the ticket line. 
Users get 5-second updates via AJAX on main queue screen, no refresh required. Users can create comments in any queue. 
Search looks for queue name keywords. Private queues currently unavailable but are in the TODO list.

### Installing

Set up an SQL database with new schema.
Create a file with SQL server authentication data

```
/resources/application.properties
```
Example:
```
# ===============================
# = DATA SOURCE
# ===============================
# Set here configurations for the database connection
spring.datasource.url=jdbc:mysql://SQL_IP_ADDRESS:3306/DATABASE_NAME
# Username and secret
#spring.datasource.username=SQL_LOGIN
#spring.datasource.password=SQL_PASSWORD
```

Building and running shouldn't be a problem

## Deployment

Configure an Apache/Nginx virtual host and header redirects.


## Built With

* [Spring Boot](https://projects.spring.io/spring-boot/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management

## Authors

* **Nikita Shevchuk** - (https://github.com/shevchuk-na)
