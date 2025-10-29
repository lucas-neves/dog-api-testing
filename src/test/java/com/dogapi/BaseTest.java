package com.dogapi;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    protected static RequestSpecification spec;

    @BeforeClass
    public static void setup() {
        spec = new RequestSpecBuilder()
                .setBaseUri("https://dog.ceo/api")
                .addFilter(new AllureRestAssured())
                .build();
    }
}
