package br.com.project.register.services.IntegrationTests;

import br.com.project.register.factorys.AddressFactory;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class AddressServiceIntegrationTest {

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
                .body(gson.toJson(AddressFactory.defaultAddress()))
                .when()
                .post("/addresses/1")
                .then()
                .extract().response();
        Assertions.assertEquals(201, response.statusCode());
    }

    @Test
    public void postRequestLimitAddress() {
        Response response = given()
                .header("Content-Type", "application/json")
                .and()
                .body(gson.toJson(AddressFactory.defaultAddress()))
                .when()
                .post("/addresses/1")
                .then()
                .extract().response();

        Assertions.assertEquals(400, response.statusCode());
        Assertions.assertEquals("It is not possible to add more address. Maximum:5", response.jsonPath().getString("message"));
    }

    @Test
    public void postRequestInvalidId() {
        Response response = given()
                .header("Content-Type", "application/json")
                .and()
                .body(gson.toJson(AddressFactory.defaultAddress()))
                .when()
                .post("/addresses/5")
                .then()
                .extract().response();
        Assertions.assertEquals(404, response.statusCode());
    }

    @Test
    public void patchRequest() {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(gson.toJson(AddressFactory.defaultAddressPatch()))
                .when()
                .patch("/addresses/1/1")
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("Maria José Antônio", response.jsonPath().getString("name"));
    }

    @Test
    public void patchRequestInvalidId() {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(gson.toJson(AddressFactory.defaultAddressPatch()))
                .when()
                .patch("/addresses/1/5")
                .then()
                .extract().response();

        Assertions.assertEquals(404, response.statusCode());
        Assertions.assertEquals("Element of id 5 does not exist", response.jsonPath().getString("message"));
    }

    @Test
    public void patchRequestInvalidIdCustomer() {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(gson.toJson(AddressFactory.defaultAddressPatch()))
                .when()
                .patch("/addresses/2/1")
                .then()
                .extract().response();

        Assertions.assertEquals(400, response.statusCode());
        Assertions.assertEquals("This address does not belong to id 2.", response.jsonPath().getString("message"));
    }

    @Test
    public void putRequest() {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(gson.toJson(AddressFactory.defaultAddressPutTrue()))
                .when()
                .put("/addresses/1/2")
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    public void putRequestInputFalse() {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(gson.toJson(AddressFactory.defaultAddressPutFalse()))
                .when()
                .put("/addresses/1/2")
                .then()
                .extract().response();

        Assertions.assertEquals(400, response.statusCode());
        Assertions.assertEquals("This principal address can not be changed.", response.jsonPath().getString("message"));
    }

    @Test
    public void getIdRequest() {
        Response response = given()
                .contentType(ContentType.JSON)
                .pathParam("id", "1")
                .when()
                .get("/addresses/{id}")
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    public void getIdInvalidRequest() {
        Response response = given()
                .contentType(ContentType.JSON)
                .pathParam("id", "2")
                .when()
                .get("/addresses/{id}")
                .then()
                .extract().response();

        Assertions.assertEquals(404, response.statusCode());

    }

    @Test
    public void notDeleteRequestIfPrincipalAddressEqualTrue() {
        Response response = given()
                .header("Content-type", "application/json")
                .when()
                .delete("/addresses/1/1")
                .then()
                .extract().response();

        Assertions.assertEquals(400, response.statusCode());
        Assertions.assertEquals("Can not delete main address", response.jsonPath().getString("message"));
    }

    @Test
    public void notDeleteRequestIfAddressNotBelongTheCustomer() {
        Response response = given()
                .header("Content-type", "application/json")
                .when()
                .delete("/addresses/2/1")
                .then()
                .extract().response();


        Assertions.assertEquals(400, response.statusCode());
        Assertions.assertEquals("This address does not belong to id 2.", response.jsonPath().getString("message"));
    }

    @Test
    public void notdeleteIdInvalidRequest() {
        Response response = given()
                .header("Content-type", "application/json")
                .when()
                .delete("/addresses/1/5")
                .then()
                .extract().response();

        Assertions.assertEquals(404, response.statusCode());
    }

    @Test
    public void deleteRequest() {
        Response response = given()
                .header("Content-type", "application/json")
                .when()
                .delete("/addresses/1/2")
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
    }


}
