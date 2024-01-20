package e2eTest;
import com.datingfood.backend.dto.RegisterDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.context.ActiveProfiles;
import com.datingfood.backend.BackendApplication;

import java.time.LocalDate;

@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = BackendApplication.class )
@ActiveProfiles("test")
@AutoConfigureWebTestClient
public class RegistrationControllerE2ETest {

    @Autowired
    private WebTestClient webTestClient;
    LocalDate localDate = LocalDate.of(1982,9,21);



    @Test

    public void testRegistrationEndpoint(){
        // GIVEN
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

    @TestConfiguration
    public static class TestConfig {
        @Bean
        public WebTestClient webTestClient() {
            return WebTestClient.bindToServer().baseUrl("http://localhost:8080").build();
        }
    }

}
