package TestesIntegracao;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class InvestimentoResourceIT {

    @Test
    public void deveRetornarNotFoundQuandoNaoExisteOCliente() {
        given()
                .when().get("/investimentos/17465")
                .then()
                .statusCode(404)
                .body("mensagem", equalTo("Cliente não encontrado."));
    }
}


