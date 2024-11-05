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
