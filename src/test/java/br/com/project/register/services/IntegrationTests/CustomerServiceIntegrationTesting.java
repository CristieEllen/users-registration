package br.com.project.register.services.IntegrationTests;

import br.com.project.register.factorys.CustomerFactory;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class CustomerServiceIntegrationTesting {

    final static Gson gson = new Gson();

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Test
    public void postRequest() {
        Response response = given()
                .header("Content-Type", "application/json")
                .and()
                .body(gson.toJson(CustomerFactory.defaultCustomer()))
                .when()
                .post("/customers")
                .then()
                .extract().response();

        Assertions.assertEquals(201, response.statusCode());

    }

    @Test
    public void postRequestWithSameCPF() {
        Response response = given()
                .header("Content-Type", "application/json")
                .and()
                .body(gson.toJson(CustomerFactory.defaultCustomer()))
                .when()
                .post("/customers")
                .then()
                .extract().response();

        Assertions.assertEquals(400, response.statusCode());
        Assertions.assertEquals("Document already registered!", response.jsonPath().getString("message"));

    }

    @Test
    public void postRequestWithoutAddress() {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(gson.toJson(CustomerFactory.defaultCustomerWithoutAddress()))
                .when()
                .post("/customers")
                .then()
                .extract().response();

        Assertions.assertEquals(400, response.statusCode());

    }
    @Test
    public void patchRequest() {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(gson.toJson(CustomerFactory.defaultCustomerPatch()))
                .when()
                .patch("/customers/1")
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("José Maria Santos", response.jsonPath().getString("name"));

    }

    @Test
    public void patchRequestInvalidId() {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(gson.toJson(CustomerFactory.defaultCustomerPatch()))
                .when()
                .patch("/customers/5")
                .then()
                .extract().response();

        Assertions.assertEquals(404, response.statusCode());

    }

    @Test
    public void getRequest() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/customers")
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    public void getIdRequest() {
        Response response = given()
                .contentType(ContentType.JSON)
                .pathParam("id", "1")
                .when()
                .get("/customers/{id}")
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("joao@email.com", response.jsonPath().getString("email"));
    }

    @Test
    public void getIdInvalidRequest() {
        Response response = given()
                .contentType(ContentType.JSON)
                .pathParam("id", "2")
                .when()
                .get("/customers/{id}")
                .then()
                .extract().response();

        Assertions.assertEquals(404, response.statusCode());

    }


    @Test
    public void deleteRequest() {
        Response response = given()
                .header("Content-type", "application/json")
                .when()
                .delete("/customers/1")
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    public void deleteIdInvalidRequest() {
        Response response = given()
                .header("Content-type", "application/json")
                .when()
                .delete("/customers/2")
                .then()
                .extract().response();

        Assertions.assertEquals(404, response.statusCode());
    }

}
