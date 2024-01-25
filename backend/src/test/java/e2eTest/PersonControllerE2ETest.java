package e2eTest;

import com.datingfood.backend.BackendApplication;
import com.datingfood.backend.dto.AuthResponseDTO;
import com.datingfood.backend.dto.FoodRequestDTO;
import com.datingfood.backend.dto.LoginDTO;
import com.datingfood.backend.entities.Person;
import com.datingfood.backend.repositories.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = BackendApplication.class)
@AutoConfigureWebTestClient
public class PersonControllerE2ETest {

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    PersonRepository personRepository;

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

    @Test
    void test_updateFoodChoice_invalid_token(){
        // GIVEN
        String token = "invalid";

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
                .isEqualTo(HttpStatus.FORBIDDEN);// THEN
    }


    @Test
    void test_deletePerson(){
        // GIVEN
        LoginDTO loginDTO = new LoginDTO("admin", "admin");
        String token = webTestClient.post()
                .uri("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(loginDTO)
                .exchange()
                .expectStatus().isOk()
                .expectBody(AuthResponseDTO.class)
                .returnResult()
                .getResponseBody().getAccessToken();

        // WHEN
        webTestClient.delete()
                .uri("/api/admin/person/alice.smith")
                .header(HttpHeaders.AUTHORIZATION,"Bearer "+ token)
                .exchange()
                .expectStatus()
                .isEqualTo(HttpStatus.OK);// THEN

        Optional<Person> optionalPerson= personRepository.findByUsername("alice.smith");
        assertTrue(optionalPerson.isEmpty());
    }
    @Test
    void test_deletePerson_username_not_existing(){
        // GIVEN
        LoginDTO loginDTO = new LoginDTO("admin", "admin");
        String token = webTestClient.post()
                .uri("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(loginDTO)
                .exchange()
                .expectStatus().isOk()
                .expectBody(AuthResponseDTO.class)
                .returnResult()
                .getResponseBody().getAccessToken();

        // WHEN
        webTestClient.delete()
                .uri("/api/admin/person/invalidUser")
                .header(HttpHeaders.AUTHORIZATION,"Bearer "+ token)
                .exchange()
                .expectStatus()
                .isEqualTo(HttpStatus.NOT_FOUND);// THEN

        Optional<Person> optionalPerson= personRepository.findByUsername("alice.smith");
        assertFalse(optionalPerson.isEmpty());
    }
    @Test
    void test_deletePerson_with_userToken(){
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

        // WHEN
        webTestClient.delete()
                .uri("/api/admin/person/alice.smith")
                .header(HttpHeaders.AUTHORIZATION,"Bearer "+ token)
                .exchange()
                .expectStatus()
                .isEqualTo(HttpStatus.FORBIDDEN);// THEN

        Optional<Person> optionalPerson= personRepository.findByUsername("alice.smith");
        assertFalse(optionalPerson.isEmpty());
    }

    @Test
    void test_getAllUser(){
        // GIVEN
        LoginDTO loginDTO = new LoginDTO("admin", "admin");
        String token = webTestClient.post()
                .uri("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(loginDTO)
                .exchange()
                .expectStatus().isOk()
                .expectBody(AuthResponseDTO.class)
                .returnResult()
                .getResponseBody().getAccessToken();

        // WHEN
        webTestClient.get()
                .uri("/api/admin/person")
                .header(HttpHeaders.AUTHORIZATION,"Bearer "+ token)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Person.class).hasSize(11);// THEN
    }

}
