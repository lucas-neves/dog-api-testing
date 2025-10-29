package com.dogapi;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Epic("Dog API Tests")
@Feature("Random Image")
public class RandomImageTest extends BaseTest {

    @Test(description = "Deve retornar imagem aleatória com sucesso")
    @Description("Valida que o endpoint retorna uma URL de imagem válida")
    public void testGetRandomImage() {
        given()
                .spec(spec)
        .when()
                .get("/breeds/image/random")
        .then()
                .statusCode(200)
                .body("status", equalTo("success"))
                .body("message", notNullValue())
                .body("message", startsWith("https://images.dog.ceo/breeds/"));
    }

    @Test(description = "Deve retornar URL de imagem válida")
    @Description("Valida que a URL retornada segue o padrão esperado")
    public void testRandomImageUrlFormat() {
        given()
                .spec(spec)
        .when()
                .get("/breeds/image/random")
        .then()
                .statusCode(200)
                .body("message", matchesRegex("^https://images\\.dog\\.ceo/breeds/.+\\.(jpg|png|jpeg)$"));
    }

    @Test(description = "Deve retornar múltiplas imagens aleatórias")
    @Description("Valida que o endpoint retorna 5 imagens aleatórias")
    public void testGetMultipleRandomImages() {
        given()
                .spec(spec)
                .pathParam("count", 5)
        .when()
                .get("/breeds/image/random/{count}")
        .then()
                .statusCode(200)
                .body("status", equalTo("success"))
                .body("message", instanceOf(java.util.List.class))
                .body("message.size()", equalTo(5));
    }

    @Test(description = "Deve conter campos obrigatórios")
    @Description("Valida que a resposta contém os campos message e status")
    public void testRequiredFields() {
        given()
                .spec(spec)
        .when()
                .get("/breeds/image/random")
        .then()
                .statusCode(200)
                .body("$", hasKey("message"))
                .body("$", hasKey("status"))
                .body("message", instanceOf(String.class));
    }

    @Test(description = "Deve retornar Content-Type JSON")
    @Description("Valida que o Content-Type é application/json")
    public void testContentType() {
        given()
                .spec(spec)
        .when()
                .get("/breeds/image/random")
        .then()
                .statusCode(200)
                .contentType("application/json");
    }
}
