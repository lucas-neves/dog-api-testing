package com.dogapi;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Epic("Dog API Tests")
@Feature("Breed Images")
public class BreedImagesTest extends BaseTest {

    @Test(description = "Deve retornar lista de imagens para raça válida")
    @Description("Valida que o endpoint retorna imagens da raça husky com sucesso")
    public void testGetBreedImages() {
        given()
                .spec(spec)
                .pathParam("breed", "husky")
        .when()
                .get("/breed/{breed}/images")
        .then()
                .statusCode(200)
                .body("status", equalTo("success"))
                .body("message", instanceOf(java.util.List.class))
                .body("message", not(empty()))
                .body("message[0]", startsWith("https://images.dog.ceo/breeds/husky/"));
    }

    @Test(description = "Deve retornar URLs válidas de imagens")
    @Description("Valida que as URLs retornadas são válidas e acessíveis")
    public void testImageUrlsFormat() {
        given()
                .spec(spec)
                .pathParam("breed", "labrador")
        .when()
                .get("/breed/{breed}/images")
        .then()
                .statusCode(200)
                .body("message[0]", matchesRegex("^https://images\\.dog\\.ceo/breeds/.+\\.(jpg|png|jpeg)$"));
    }

    @Test(description = "Deve retornar erro para raça inválida")
    @Description("Valida que o endpoint retorna erro 404 para raça inexistente")
    public void testInvalidBreed() {
        given()
                .spec(spec)
                .pathParam("breed", "racainvalida123")
        .when()
                .get("/breed/{breed}/images")
        .then()
                .statusCode(404)
                .body("status", equalTo("error"))
                .body("message", containsString("Breed not found"));
    }

    @Test(description = "Deve retornar imagens para sub-raça")
    @Description("Valida que o endpoint funciona com sub-raças")
    public void testSubBreedImages() {
        given()
                .spec(spec)
                .pathParam("breed", "hound")
                .pathParam("subBreed", "afghan")
        .when()
                .get("/breed/{breed}/{subBreed}/images")
        .then()
                .statusCode(200)
                .body("status", equalTo("success"))
                .body("message", not(empty()))
                .body("message[0]", containsString("hound-afghan"));
    }

    @Test(description = "Deve retornar Content-Type JSON")
    @Description("Valida que o Content-Type é application/json")
    public void testContentType() {
        given()
                .spec(spec)
                .pathParam("breed", "beagle")
        .when()
                .get("/breed/{breed}/images")
        .then()
                .statusCode(200)
                .contentType("application/json");
    }
}
