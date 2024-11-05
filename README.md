# Rest Service

```java

package com.rest_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * To run this project just use
 * <p>
 * `./mvnw spring-boot`
 * <p>
 * to run the JMeter test script use
 * <p>
 * `mvn jmeter:jmeter`
 * <p>
 * The JMeter tests run 1000 users making 1000 requests each, the api drops no
 * calls and does all one million request and does them all in about 7 seconds,
 * atleast on my machine
 * <p>
 * The api reponds to all post requests regardless of path since to me it made
 * sense that it would since it only responds to POST requests and no other
 * requests were specified.
 * <p>
 * I have also included a Dockerfile that can be used to build an image for
 * running the server on a container for easier scaling.
 * 
 * @author Ivan Horak
 * @see <a href="https://github.com/ChuufMaster">ChuufMaster</a>
 */
@RestController
@SpringBootApplication
public class RestServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestServiceApplication.class, args);
    }

    /**
     * Responds to all post requests on port 8080
     * <p>
     * I decided to make the actual main the controller because it only has one
     * static POST method and there is no need to spawn an entity for every
     * call. This reduces a lot of overhead as there are no other entities other
     * than the main and only static responces are made since nothing needs to
     * get stored. This means that scaling is simple since all you need to do is
     * give it more resources and it will respond the same even if you run it on
     * multiple machines. Its all static so scaling is trivial since there isn't
     * need
     * 
     * @param name The name that will be returned in the string
     * @return Http 200 response with the text "Hello ${name}"
     */
    @PostMapping(value = "/**")
    public static String postHelloName(@RequestParam String name) {
        return "Hello " + name + "\r\n";
    }
}
```

## Repository

[rest-service](https://github.com/ChuufMaster/rest-service)

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
