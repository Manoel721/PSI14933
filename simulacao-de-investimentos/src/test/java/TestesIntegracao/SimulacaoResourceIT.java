package TestesIntegracao;


import Entidades.Investimento;
import Entidades.Simulacao;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
class SimulacaoResourceIT {

    @Inject
    EntityManager em;

    @BeforeEach
    @Transactional
    void prepararBanco() {
        em.createNativeQuery("SET IDENTITY_INSERT Investimento ON").executeUpdate();
        em.createNativeQuery(
                "INSERT INTO Investimento (nome, rentabilidade, risco, tipo) VALUES ('Tesouro Direto', 10.5, 'Baixo', 'Renda Fixa')"
        ).executeUpdate();
        em.createNativeQuery("SET IDENTITY_INSERT Investimento OFF").executeUpdate();
    }

    @Test
    void deveRetornarBadRequestQuandoPayloadNulo() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .post("/simular-investimento")
                .then()
                .statusCode(400)
                .body(containsString("Envelope não enviado"));
    }

    @Test
    void deveRetornarNotFoundQuandoProdutoNaoExiste() {
        Simulacao simulacao = new Simulacao();
        simulacao.setClienteId(1L);
        simulacao.setTipoProduto("LCI"); // não existe no banco
        simulacao.setValor(1000.0);
        simulacao.setPrazoMeses(12);

        given()
                .contentType(ContentType.JSON)
                .body(simulacao)
                .when()
                .post("/simular-investimento")
                .then()
                .statusCode(404)
                .body(containsString("Produto não encontrado"));
    }


}
