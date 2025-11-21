package TestesIntegracao;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
class SimulacoesProdutoDiaResourceIT {

    @Inject
    EntityManager em;

    @Test
    public void deveRetornarDadosQueExistemNoBD() {
        given()
                .when().get("/simulacoes/por-produto-dia")
                .then()
                .statusCode(200)
                .body("[0].produto", equalTo("CBD Caixa 2026"))
                .body("[0].data", equalTo("2025-11-18"))
                .body("[0].quantidadeSimulacoes", equalTo(1))
                .body("[0].mediaValorFinal", equalTo(19000.0f));
    }

}



