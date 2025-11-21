package TestesIntegracao;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
class SimulacoesResourceIT {

    @Test
    void deveRetornarListaDeSimulacoesViaHttp() {
        given()
                .when().get("/simulacoes")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .body("$", notNullValue());
    }

    @Test
    void deveConterCamposEsperadosNaResposta() {
        given()
                .when().get("/simulacoes")
                .then()
                .statusCode(200)
                .body("[0].id", notNullValue())
                .body("[0].clienteId", notNullValue())
                .body("[0].produto", notNullValue())
                .body("[0].valorInvestido", notNullValue())
                .body("[0].valorFinal", notNullValue())
                .body("[0].prazoMeses", notNullValue())
                .body("[0].dataSimulacao", notNullValue());
    }
}

