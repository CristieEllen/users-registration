package br.com.project.register.services.IntegrationTests;

import br.com.project.register.entities.Address;
import br.com.project.register.entities.Customer;
import br.com.project.register.enums.CustomerTypes;
import br.com.project.register.factorys.CustomerFactory;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

@Profile("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerServiceIntegrationTesting {

    @LocalServerPort
    int port;

    final static Gson gson = new Gson();

    @BeforeAll
    void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;

        defaultCustomer();
    }

    @Test
    void postRequest() {
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
    void postRequestWithoutAddress() {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(gson.toJson(CustomerFactory.customerWithoutAddress()))
                .when()
                .post("/customers")
                .then()
                .extract().response();

        Assertions.assertEquals(400, response.statusCode());

    }
    @Test
    void patchRequest() {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(gson.toJson(CustomerFactory.customerPatch()))
                .when()
                .patch("/customers/1")
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("José Maria Santos", response.jsonPath().getString("name"));
    }

    @Test
    void patchRequestInvalidId() {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(gson.toJson(CustomerFactory.customerPatch()))
                .when()
                .patch("/customers/5")
                .then()
                .extract().response();

        Assertions.assertEquals(404, response.statusCode());

    }

    @Test
    void getRequest() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/customers")
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    void getIdRequest() {

        Response response = given()
                .contentType(ContentType.JSON)
                .pathParam("id", 1)
                .when()
                .get("/customers/{id}")
                .then()
                .extract().response();

        System.out.println(response.jsonPath().prettify());
        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("joao@email.com", response.jsonPath().getString("email"));
    }

    @Test
    void getIdInvalidRequest() {
        Response response = given()
                .contentType(ContentType.JSON)
                .pathParam("id", "5")
                .when()
                .get("/customers/{id}")
                .then()
                .extract().response();

        Assertions.assertEquals(404, response.statusCode());

    }


    @Test
    void deleteRequest() {
        Response response = given()
                .header("Content-type", "application/json")
                .when()
                .delete("/customers/1")
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    void deleteIdInvalidRequest() {
        Response response = given()
                .header("Content-type", "application/json")
                .when()
                .delete("/customers/5")
                .then()
                .extract().response();

        Assertions.assertEquals(404, response.statusCode());
    }

   Response defaultCustomer(){
        List<Address> addresses = new ArrayList<>();
        addresses.add(new Address("José Antônio","223", "Vila Nova", "Bananeira", "12345678","São Paulo" , true));
        Customer customer = new Customer("João Cesaro", "592.640.690-03", "joao@email.com", "12912342345", CustomerTypes.valueOf("PF"), addresses);

          Response response = given()
                .header("Content-Type", "application/json")
                .and()
                .body(gson.toJson(customer))
                .when()
                .post("/customers")
                .then()
                .extract().response();
        return  response;
    }

}
