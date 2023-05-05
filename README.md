# ramen-shop

Excalidraw [link](https://excalidraw.com/#room=332d299145b61a74b7da,2Yy-GYL_YDplYIcg6AmzHA) for planning/brainstorming

The server application is built with Spring Boot 3.0.6
  - To start up the mongoDb container for the first time, run: 
  ```
  docker run --name ramen-datastore -d -p 27017:27017 mongo:latest
  ```
  - stop the instance with 
  ```
  docker stop ramen-datastore
  ```
  - resume container with
  ```
  docker start ramen-datastore
  ```
The client application is built with React
  - Bootstrap and Reactstrap were installed to assist with component development for ease of use
  - Reactstrap documentation can be found [here](https://reactstrap.github.io/?path=/docs/home-installation--page)
    - navigate to the components to see utilization and implementation


---
Resources/References:
Server:
- Using appropriate status codes with REST verbs [link](https://restfulapi.net/http-methods/)
- Documenting the Rest controller with OpenAPI [link](https://www.baeldung.com/spring-rest-openapi-documentation)
- JSON patch with Spring boot [link](https://www.baeldung.com/spring-rest-json-patch)
