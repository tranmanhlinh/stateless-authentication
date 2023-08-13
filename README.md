# Getting Started

1. **Prerequisites**: Ensure you have Java JDK 17 and Gradle 7.0+ installed.

2. **Start the App**:
   Use the `bootRun` Gradle task with Java 17:

   ```bash
   gradle bootRun
   
3. **API Documentation**:

- ***Login to get raw token***
    ```bash
  curl --location 'http://localhost:8080/auth/login-raw-token' \
  --header 'Content-Type: application/json' \
  --data '{
  "username": "username",
  "password": "password"
  }'
    ```
- ***Login to get hash token***
    ```bash
   curl --location 'http://localhost:8080/auth/login-hash-token' \
    --header 'Content-Type: application/json' \
    --data '{
    "username": "username",
    "password": "password"
    }'
    ```
- ***Login to get JWTs***
    ```bash
   curl --location 'http://localhost:8080/auth/login-jwt' \
    --header 'Content-Type: application/json' \
    --data '{
    "username": "username",
    "password": "password"
    }'
    ```
- ***Call API with raw token***
    ```bash
   curl --location 'http://localhost:8080/api/rawtoken' \
    --header 'Authorization: Token_123456'
    ```
- ***Call API with hash token***
    ```bash
   curl --location 'http://localhost:8080/api/hashtoken' \
    --header 'Authorization: bTWraoJ/5vjEHAAAU0K6TvqLzRiepvPMsq7FmC/4sQQ=' \
    --header 'Username: username'
    ```
- ***Call API with JWT***
    ```bash
   curl --location 'http://localhost:8080/api/jwt' \
    --header 'Authorization: token'
    ```
