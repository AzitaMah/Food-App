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
import org.springframework.test.context.ActiveProfiles;
import com.datingfood.backend.BackendApplication;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.time.LocalDate;

@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = BackendApplication.class)
@ActiveProfiles("test")
@AutoConfigureWebTestClient
class NegativeAuthControllerE2ETest {

    @Autowired
    WebTestClient webTestClient;


    @Test
    void test_invalidRegister() {
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
    void test_invalidLogin(){
        // GIVEN
        LoginDTO loginDTO = new LoginDTO("alce.smith", "strongpassword");
        // WHEN
        webTestClient.post()
                .uri("/api/auth/login")
                .bodyValue(loginDTO)
                .exchange()
                .expectStatus()
                .isEqualTo(HttpStatus.NOT_FOUND);// THEN
    }

}
