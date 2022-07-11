package br.com.project.register.services;

import br.com.project.register.dto.request.CustomerRequestDtoPatch;
import br.com.project.register.entities.Address;
import br.com.project.register.entities.Customer;
import br.com.project.register.enums.CustomerTypes;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class CustomerServiceIntegrationTesting {


    final static Gson gson = new Gson();

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Test
    public void postRequest() {
        Address address = new Address("José da Vila", "25", "São Paulo", "Unidos", "12345678", "São Paulo", true);
        List<Address> addresses = new ArrayList<>();
        addresses.add(address);
        Customer customer = new Customer("José Antônio", "586.425.720-60", "jose@email.com", "12912342345", CustomerTypes.valueOf("PF"), addresses);

        Response response = given()
                .header("Content-Type", "application/json")
                .and()
                .body(gson.toJson(customer))
                .when()
                .post("/customers")
                .then()
                .extract().response();

        System.out.println(response.asString());
        Assertions.assertEquals(201, response.statusCode());

    }

    @Test
    public void postRequestWithSameCPF() {
        List<Address> addresses = new ArrayList<>();
        addresses.add(new Address("José da Vila", "25", "São Paulo", "Unidos", "12345678", "São Paulo", true));
        Customer customer = new Customer("José Antônio", "586.425.720-60", "jose@email.com", "12912342345", CustomerTypes.valueOf("PF"), addresses);

        Response response = given()
                .header("Content-Type", "application/json")
                .and()
                .body(gson.toJson(customer))
                .when()
                .post("/customers")
                .then()
                .extract().response();

        Assertions.assertEquals(400, response.statusCode());
        Assertions.assertEquals("Document already registered!", response.jsonPath().getString("message"));

    }

    @Test
    public void postRequestWithoutAddress() {
        List<Address> addresses = new ArrayList<>();
        Customer customer = new Customer("José Antônio", "586.425.720-60", "jose@email.com", "12912342345", CustomerTypes.valueOf("PF"), addresses);
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(gson.toJson(customer))
                .when()
                .post("/customers")
                .then()
                .extract().response();

        Assertions.assertEquals(400, response.statusCode());

    }
    @Test
    public void patchRequest() {
        CustomerRequestDtoPatch customerDto = new CustomerRequestDtoPatch("José Maria Santos", null, null);

        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(gson.toJson(customerDto))
                .when()
                .patch("/customers/1")
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("José Maria Santos", response.jsonPath().getString("name"));

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
        Assertions.assertEquals("ana@gmail.com", response.jsonPath().getString("email"));
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
