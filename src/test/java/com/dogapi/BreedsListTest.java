package com.dogapi;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Epic("Dog API Tests")
@Feature("Breeds List")
public class BreedsListTest extends BaseTest {

    @Test(description = "Deve retornar lista completa de raças com sucesso")
    @Description("Valida que o endpoint retorna status 200, formato correto e contém raças esperadas")
    public void testGetAllBreedsList() {
        given()
                .spec(spec)
        .when()
                .get("/breeds/list/all")
        .then()
                .statusCode(200)
                .body("status", equalTo("success"))
                .body("message", instanceOf(Object.class))
                .body("message.affenpinscher", notNullValue())
                .body("message.african", hasItem("wild"))
                .body("message.bulldog", hasItems("boston", "english", "french"))
                .body("message.poodle", hasItems("medium", "miniature", "standard"));
    }

    @Test(description = "Deve retornar estrutura de dados válida")
    @Description("Valida que as raças retornam arrays de sub-raças")
    public void testBreedsDataStructure() {
        given()
                .spec(spec)
        .when()
                .get("/breeds/list/all")
        .then()
                .statusCode(200)
                .body("message.labrador", instanceOf(java.util.List.class))
                .body("message.retriever", hasItems("chesapeake", "curly", "flatcoated", "golden"));
    }

    @Test(description = "Deve conter campos obrigatórios")
    @Description("Valida que a resposta contém os campos message e status")
    public void testRequiredFields() {
        given()
                .spec(spec)
        .when()
                .get("/breeds/list/all")
        .then()
                .statusCode(200)
                .body("$", hasKey("message"))
                .body("$", hasKey("status"));
    }

    @Test(description = "Deve retornar Content-Type JSON")
    @Description("Valida que o Content-Type é application/json")
    public void testContentType() {
        given()
                .spec(spec)
        .when()
                .get("/breeds/list/all")
        .then()
                .statusCode(200)
                .contentType("application/json");
    }
}
