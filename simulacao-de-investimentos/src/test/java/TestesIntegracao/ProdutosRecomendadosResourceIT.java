package TestesIntegracao;

import Entidades.MotorRecomendacao;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
class ProdutosRecomendadosResourceIT {

    @Test
    void deveRetornarNotFoundQuandoNaoExistemInvestimentos() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/produtos-recomendados/" + MotorRecomendacao.PerfilInvestidor.MODERADO)
                .then()
                .statusCode(200);
    }

    @Test
    void deveRetornarInvestimentosAgressivos() {

        given()
                .accept(ContentType.JSON)
                .when()
                .get("/produtos-recomendados/" + MotorRecomendacao.PerfilInvestidor.AGRESSIVO)
                .then()
                .statusCode(200)
                .body(containsString("Alto"))
                .body(not(containsString("Baixo")));
    }
}
