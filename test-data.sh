curl -X POST "http://localhost:8080/api/v1/customers" -H "accept: */*" -H "Content-Type: application/json" -d '{ "companyName": "Docker", "firstName": "John", "lastName": "Doe", "address1": "111 Broadway", "city": "New York", "state": "New York", "country": "USA", "postalCode": "11111", "phoneNumber": "+1 111-111-1111", "primaryEmail": "john.doe@docker.com"}'

curl -X POST "http://localhost:8080/api/v1/customers" -H "accept: */*" -H "Content-Type: application/json" -d '{ "companyName": "Amazon", "firstName": "John", "lastName": "Doe", "address1": "222 Broadway", "city": "New York", "state": "New York", "country": "USA", "postalCode": "22222", "phoneNumber": "+1 222-222-2222", "primaryEmail": "john.doe@amazon.com"}'

curl -X POST "http://localhost:8080/api/v1/customers" -H "accept: */*" -H "Content-Type: application/json" -d '{ "companyName": "Apple", "firstName": "John", "lastName": "Doe", "address1": "333 Broadway", "city": "New York", "state": "New York", "country": "USA", "postalCode": "33333", "phoneNumber": "+1 333-333-3333", "primaryEmail": "john.doe@apple.com"}'

curl -X POST "http://localhost:8080/api/v1/customers" -H "accept: */*" -H "Content-Type: application/json" -d '{ "companyName": "Dropbox", "firstName": "John", "lastName": "Doe", "address1": "444 Broadway", "city": "New York", "state": "New York", "country": "USA", "postalCode": "44444", "phoneNumber": "+1 444-444-4444", "primaryEmail": "john.doe@dropbox.com"}'