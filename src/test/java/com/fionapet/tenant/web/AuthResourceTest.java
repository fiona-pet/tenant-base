package com.fionapet.tenant.web;

import static org.junit.Assert.assertTrue;

import com.fionapet.tenant.FionaTenantApp;
import com.fionapet.tenant.web.dto.LoginRequest;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tom on 2018/8/22.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FionaTenantApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
@Setter
@Slf4j
public class AuthResourceTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;


    /**
     * @author PQF
     */
    @Test
    public void login() {
        try {
            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setUsername("admin");
            loginRequest.setPassword("admin");

            HttpHeaders headers = new HttpHeaders();

            List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
            acceptableMediaTypes.add(MediaType.TEXT_HTML);
            acceptableMediaTypes.add(MediaType.TEXT_PLAIN);

            headers.setAccept(acceptableMediaTypes);
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity formEntity = new HttpEntity(loginRequest, headers);

            ResponseEntity<String>
                    result =
                    testRestTemplate
                            .exchange("/api/auth/login", HttpMethod.POST, formEntity, String.class);

            String token = testRestTemplate.postForObject("/api/auth/login", loginRequest, String.class);

            log.info("token:{}", token);

            log.info("token:{}", result.getBody());

            assertTrue(!result.getBody().isEmpty());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
