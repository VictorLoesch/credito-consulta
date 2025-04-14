package com.desafio.backend_credito_consulta.integration.controller;

import com.desafio.backend_credito_consulta.Entity.Credito;
import com.desafio.backend_credito_consulta.integration.config.AbstractIntegrationTest;
import com.desafio.backend_credito_consulta.repository.CreditoRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CreditoControllerIntegrationTest extends AbstractIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private CreditoRepository repository;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
    }

    @Test
    @Order(1)
    void deveCriarCreditoComSucesso() {
        Credito credito = criarCredito("CRED101", "NFSE101");

        given()
                .contentType(ContentType.JSON)
                .body(credito)
                .when()
                .post("/api/creditos")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("numeroCredito", equalTo("CRED101"))
                .body("numeroNfse", equalTo("NFSE101"));
    }

    @Test
    @Order(2)
    void deveConsultarCreditoCriado() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/api/creditos/NFSE101")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("[0].numeroCredito", equalTo("CRED101"))
                .body("[0].numeroNfse", equalTo("NFSE101"));
    }

    @Test
    @Order(3)
    void deveRetornarListaVaziaParaNfseInexistente() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/api/creditos/NFSE999")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("$", hasSize(0));
    }

    @Test
    @Order(4)
    void deveAtualizarCreditoComSucesso() {
        Credito creditoAtualizado = criarCredito("CRED101", "NFSE101-ATUAL");
        creditoAtualizado.setValorIssqn(new BigDecimal("999.99"));

        given()
                .contentType(ContentType.JSON)
                .body(creditoAtualizado)
                .when()
                .put("/api/creditos/CRED101")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("numeroCredito", equalTo("CRED101"))
                .body("numeroNfse", equalTo("NFSE101-ATUAL"))
                .body("valorIssqn", is(999.99f));
    }

    @Test
    @Order(5)
    void deveRetornar404AoAtualizarCreditoInexistente() {
        Credito creditoInexistente = criarCredito("INEXISTENTE", "NFSE404");

        given()
                .contentType(ContentType.JSON)
                .body(creditoInexistente)
                .when()
                .put("/api/creditos/INEXISTENTE")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    @Order(6)
    void deveDeletarCreditoComSucesso() {
        given()
                .when()
                .delete("/api/creditos/CRED101")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    @Order(7)
    void deveRetornar404AoDeletarCreditoInexistente() {
        given()
                .when()
                .delete("/api/creditos/CRED999")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    private Credito criarCredito(String numeroCredito, String numeroNfse) {
        Credito c = new Credito();
        c.setNumeroCredito(numeroCredito);
        c.setNumeroNfse(numeroNfse);
        c.setDataConstituicao(LocalDate.now());
        c.setValorIssqn(BigDecimal.valueOf(100));
        c.setTipoCredito("ISSQN");
        c.setSimplesNacional(true);
        c.setAliquota(BigDecimal.valueOf(5.0));
        c.setValorFaturado(BigDecimal.valueOf(1000));
        c.setValorDeducao(BigDecimal.valueOf(100));
        c.setBaseCalculo(BigDecimal.valueOf(900));
        return c;
    }


}
