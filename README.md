[![Build Status](https://travis-ci.org/avpatel257/nordic-apis-2019.svg?branch=master)](https://travis-ci.org/avpatel257/nordic-apis-2019)

Rest API implementation with Spring Boot + Swagger support.

### How to run?

#### With H2 DB
For this sample app, we're using `H2 DB`. To run the source code, please execute following command:

```bash
mvn clean spring-boot:run
```

##### H2 Console:
[http://localhost:8080/h2-console](http://localhost:8080/h2-console)

> **Note:** You will need to supply the same credentials as defined in [application.properties](https://github.com/avpatel257/nordic-apis-2019/blob/master/src/main/resources/application.properties)




#### With MySql

Copy the mysql configs from `application.properties.mysql` to `application.properties` and follow the instructions below to start the app. 
#### Start the application:
```bash
docker-compose up
```


### Swagger Docs
[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)



##### Test Data Load
```bash
curl -X POST "http://localhost:8080/api/v1/customers" -H "accept: */*" -H "Content-Type: application/json" -d '{ "companyName": "Docker", "firstName": "John", "lastName": "Doe", "address1": "111 Broadway", "city": "New York", "state": "New York", "country": "USA", "postalCode": "10012", "phoneNumber": "+1 111-111-1111", "primaryEmail": "john.doe@docker.com"}'

curl -X POST "http://localhost:8080/api/v1/customers" -H "accept: */*" -H "Content-Type: application/json" -d '{ "companyName": "Amazon", "firstName": "John", "lastName": "Doe", "address1": "222 Broadway", "city": "New York", "state": "New York", "country": "USA", "postalCode": "22222", "phoneNumber": "+1 222-222-2222", "primaryEmail": "john.doe@amazon.com"}'

curl -X POST "http://localhost:8080/api/v1/customers" -H "accept: */*" -H "Content-Type: application/json" -d '{ "companyName": "Apple", "firstName": "John", "lastName": "Doe", "address1": "333 Broadway", "city": "New York", "state": "New York", "country": "USA", "postalCode": "33333", "phoneNumber": "+1 333-333-3333", "primaryEmail": "john.doe@apple.com"}'

curl -X POST "http://localhost:8080/api/v1/customers" -H "accept: */*" -H "Content-Type: application/json" -d '{ "companyName": "Dropbox", "firstName": "John", "lastName": "Doe", "address1": "444 Broadway", "city": "New York", "state": "New York", "country": "USA", "postalCode": "44444", "phoneNumber": "+1 444-444-4444", "primaryEmail": "john.doe@dropbox.com"}'
```

##### Filter Response
`findAll` -> [http://localhost:8080/api/v1/customers](http://localhost:8080/api/v1/customers)

`search cust by companyName=Docker*` -> [http://localhost:8080/api/v1/customers?filter=companyName==Docker](http://localhost:8080/api/v1/customers?filter=companyName==Docker)


[Click here](https://github.com/avpatel257/nordic-apis-2019/blob/master/src/main/java/com/two57/demo/apininja/repository/rsql/GenericRsqlSpecBuilder.java#L15-L37) for more search patterns (RSQL - A super set of FIQL)


##### Selective Fields Retrieval
`Only retrieve id, companyName and primaryEmail of all customers starting with letter "D" in country=USA` -> [http://localhost:8080/api/v1/customers?filter=companyName==D*;USA&fields=id,companyName,primaryEmail](http://localhost:8080/api/v1/customers?filter=companyName==D*;country==USA&fields=id,companyName,primaryEmail) 
#### Cleanup
```bash
docker-compose stop/down
```
