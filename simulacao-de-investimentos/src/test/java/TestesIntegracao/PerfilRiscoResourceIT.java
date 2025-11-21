package TestesIntegracao;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
class PerfilRiscoResourceIT {

    @Test
    void deveRetornarNotFoundQuandoClienteNaoExiste() {
        given()
                .when().get("/perfil-risco/9999")
                .then()
                .statusCode(404)
                .body(containsString("Nenhuma simulação encontrada"));
    }

    @Test
    void deveRetornarPerfilAgressivo() {
        given()
                .when().get("/perfil-risco/1")
                .then()
                .statusCode(200)
                .body("clienteId", equalTo(1))
                .body("perfil", equalTo("AGRESSIVO"))
                .body("pontuacao", greaterThan(0))
                .body("descricao", notNullValue());
    }

    @Test
    void deveRetornarPerfilModerado() {
        given()
                .when().get("/perfil-risco/2")
                .then()
                .statusCode(200)
                .body("perfil", equalTo("MODERADO"));
    }

}

