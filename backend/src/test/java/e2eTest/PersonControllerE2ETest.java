package e2eTest;

import com.datingfood.backend.BackendApplication;
import com.datingfood.backend.dto.AuthResponseDTO;
import com.datingfood.backend.dto.FoodRequestDTO;
import com.datingfood.backend.dto.LoginDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = BackendApplication.class)
@AutoConfigureWebTestClient
public class PersonControllerE2ETest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    void test_updateFoodChoice(){
        // GIVEN
        LoginDTO loginDTO = new LoginDTO("alice.smith", "strongpassword");
        String token = webTestClient.post()
                .uri("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(loginDTO)
                .exchange()
                .expectStatus().isOk()
                .expectBody(AuthResponseDTO.class)
                .returnResult()
                .getResponseBody().getAccessToken();


        FoodRequestDTO foodRequestDTO = new FoodRequestDTO();
        foodRequestDTO.setFoodId(2);

        // WHEN
        webTestClient.put()
                .uri("/api/person/alice.smith")
                .header(HttpHeaders.AUTHORIZATION,"Bearer "+ token)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(foodRequestDTO)
                .exchange()
                .expectStatus()
                .isEqualTo(HttpStatus.OK);// THEN

    }
}
