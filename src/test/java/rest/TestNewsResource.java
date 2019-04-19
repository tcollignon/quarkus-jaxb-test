package rest;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class TestNewsResource {

    @Test
    public void testHelloEndpoint() {
        given()
                .when().get("/getnews")
                .then()
                .statusCode(200)
                .body(is("[{\"author\":\"cheval\",\"content\":\"\\nVoici le lien vers la revue de presse front de la semaine :\\n\",\"description\":\"\\nVoici le lien vers la revue de presse front de la semaine :\\n\",\"title\":\"veille techno • Revue de presse front du 18/04/2019\",\"url\":\"http://eforum.uem.lan/viewtopic.php?t=2905&p=11262#p11262\",\"version\":0}]"));
    }
}
