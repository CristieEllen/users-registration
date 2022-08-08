package br.com.project.register.services.IntegrationTests;

import br.com.project.register.entities.Address;
import br.com.project.register.entities.Customer;
import br.com.project.register.enums.CustomerTypes;
import br.com.project.register.factorys.AddressFactory;
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

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AddressServiceIntegrationTest {

    @LocalServerPort
    int port;
    final static Gson gson = new Gson();

    @BeforeAll
    void setup(){
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;

        defaultAddress();
    }

    @Test
    void postRequest() {
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
    void postRequestInvalidId() {
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
    void patchRequest() {
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
    void patchRequestInvalidId() {
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
    void patchRequestInvalidIdCustomer() {
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
    void putRequest() {
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
    void putRequestInputFalse() {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(gson.toJson(AddressFactory.defaultAddressPutFalse()))
                .when()
                .put("/addresses/1/1")
                .then()
                .extract().response();

        Assertions.assertEquals(400, response.statusCode());
        Assertions.assertEquals("This principal address can not be changed.", response.jsonPath().getString("message"));
    }

    @Test
    void getIdRequest() {
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
    void getIdInvalidRequest() {
        Response response = given()
                .contentType(ContentType.JSON)
                .pathParam("id", "6")
                .when()
                .get("/addresses/{id}")
                .then()
                .extract().response();

        Assertions.assertEquals(404, response.statusCode());

    }

    @Test
    void notDeleteRequestIfPrincipalAddressEqualTrue() {

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
    void notDeleteRequestIfAddressNotBelongTheCustomer() {
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
    void notdeleteIdInvalidRequest() {
        Response response = given()
                .header("Content-type", "application/json")
                .when()
                .delete("/addresses/1/5")
                .then()
                .extract().response();

        Assertions.assertEquals(404, response.statusCode());
    }

    @Test
    void deleteRequest() {
        Response response = given()
                .header("Content-type", "application/json")
                .when()
                .delete("/addresses/1/3")
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
    }


    Response defaultAddress() {
        List<Address> addresses = new ArrayList<>();
        addresses.add(new Address("José Antônio","223", "Vila Nova", "Bananeira", "12345678","São Paulo" , true));
        addresses.add(new Address( "Maria da Silva","23", "Vila Nova", "Bananeira", "12345690","São Paulo", false));
        addresses.add(new Address( "Maria da Silva","23", "Vila Nova", "Bananeira", "12345690","São Paulo", false));
        Customer customer = new Customer("João Cesaro", "592.640.690-03", "joao@email.com", "12912342345", CustomerTypes.valueOf("PF"), addresses);

        Response response = given()
                .header("Content-Type", "application/json")
                .and()
                .body(gson.toJson(customer))
                .when()
                .post("/customers")
                .then()
                .extract().response();
        return response;
    }
}
