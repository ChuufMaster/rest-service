# Rest Service

## How I did it

I decided to use spring boot to make the rest server as it is lightweight and
easy to set up. With this I also decided to make the main class the controller
as the only requests it would be responding to would be POST requests and the
POST requests are simple and could be done statically, so there was no need to
add any extra classes which would spawn unnecessary entities.

There are also more explanations in the applications code in
`./src/main/java/com/rest_service/RestServiceApplication.java`

## Some Extra things

### JMETER

I decided to test the service using JMETER. The test that I did was simulated 1000
users simultaneously making post requests with random names and each user makes
1000 requests which gives a total of 1 000 000 requests. This test takes about 7
seconds to run, so the service is highly performant

### Docker

I included a Dockerfile that can be used for scaling as this allows the server
to be run on any port by just mapping port 8080 on the container to any other
port allowing for easy scaling.

## Notes

### How to run

To run the project there is a jar that is included in target that will just run
the service as is on port `8080`. It is also possible to run it using

```sh
./mvnw spring-boot:run
```

```sh
mvn spring-boot:run 
```

```sh
mvn package clean && java -jar ./target/rest-service-0.0.1-SNAPSHOT.jar
```

```sh
mvn clean package
docker build -t rest-service .
docker run -p 8080:8080 rest-service
```
