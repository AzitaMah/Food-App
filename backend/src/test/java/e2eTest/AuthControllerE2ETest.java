package e2eTest;

import com.datingfood.backend.dto.AuthResponseDTO;
import com.datingfood.backend.dto.LoginDTO;
import com.datingfood.backend.dto.RegisterDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import com.datingfood.backend.BackendApplication;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.time.LocalDate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = BackendApplication.class)
@AutoConfigureWebTestClient
class AuthControllerE2ETest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    void test_register() {
        // GIVEN
        LocalDate localDate = LocalDate.of(1982, 9, 21);
        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.setUsername("MiaE");
        registerDTO.setPassword("MiaPW");
        registerDTO.setFirstname("Mia");
        registerDTO.setLastname("Eiching");
        registerDTO.setBirthdate(localDate);
        registerDTO.setContact("01745376");

        // WHEN
        webTestClient.post()
                .uri("/api/auth/registration")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(registerDTO)
                .exchange()
                .expectStatus().isOk() // THEN
                .expectBody().isEmpty();
    }
    @Test
    void test_login(){
        // GIVEN
        LoginDTO loginDTO = new LoginDTO("alice.smith", "strongpassword");

        // WHEN
        String token = webTestClient.post()
                .uri("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(loginDTO)
                .exchange()
                .expectStatus().isOk() // THEN
                .expectBody(AuthResponseDTO.class)
                .returnResult()
                .getResponseBody().getAccessToken();

        assertNotNull(token, "Access token should not be null");
    }

    @TestConfiguration
    public static class TestConfig {
        @Bean
        public WebTestClient webTestClient() {
            return WebTestClient.bindToServer().baseUrl("http://localhost:8080").build();
            //WebTestClient bean, configured to connect to the server at the specified base URL ("http://localhost:8080")
        }
    }

}
