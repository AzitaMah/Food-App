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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import com.datingfood.backend.BackendApplication;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.time.LocalDate;

@TestPropertySource(locations = "classpath:application-test.properties")
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
    void test_register_username_already_exists() {
        // GIVEN
        LocalDate localDate = LocalDate.of(1985, 5, 15);
        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.setUsername("alice.smith");
        registerDTO.setPassword("strongpassword");
        registerDTO.setFirstname("Alice");
        registerDTO.setLastname("Smith");
        registerDTO.setBirthdate(localDate);
        registerDTO.setContact("+9876543210");

        // WHEN
        webTestClient.post()
                .uri("/api/auth/registration")
                .bodyValue(registerDTO)
                .exchange()
                .expectStatus()
                .isEqualTo(HttpStatus.BAD_REQUEST);// THEN

    }

    @Test
    void test_register_missing_value() {
        // GIVEN
        LocalDate localDate = LocalDate.of(1985, 5, 15);
        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.setUsername("alice.smith");
        registerDTO.setFirstname("Alice");
        registerDTO.setLastname("Smith");
        registerDTO.setBirthdate(localDate);
        registerDTO.setContact("+9876543210");

        // WHEN
        webTestClient.post()
                .uri("/api/auth/registration")
                .bodyValue(registerDTO)
                .exchange()
                .expectStatus()
                .isEqualTo(HttpStatus.BAD_REQUEST);// THEN

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

    @Test
    void test_login_invalid_username(){
        // GIVEN
        LoginDTO loginDTO = new LoginDTO("invalidUser", "strongpassword");
        // WHEN
        webTestClient.post()
                .uri("/api/auth/login")
                .bodyValue(loginDTO)
                .exchange()
                .expectStatus()
                .isEqualTo(HttpStatus.NOT_FOUND);// THEN
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
