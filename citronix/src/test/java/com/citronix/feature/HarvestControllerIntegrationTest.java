package com.citronix.feature;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@Transactional
@Rollback(true)
public class HarvestControllerIntegrationTest {

    @Test
    void getAllHarvests_success() {
        RestAssured
                .given()
                .accept(ContentType.JSON)
                .when()
                .get("/api/harvests")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("size()", Matchers.equalTo(3));
    }

    @Test
    void getOneHarvest_success() {
        RestAssured
                .given()
                .accept(ContentType.JSON)
                .when()
                .get("/api/harvests/1")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("size()", Matchers.equalTo(1))
                .body("id", Matchers.equalTo(1));
    }

    @Test
    void createHarvest_success() {
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body("{\"season\": \"WINTER\",\"harvestYear\": 2024}")
                .when()
                .post("/api/harvests")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("season", Matchers.equalTo("WINTER"))
                .body("harvestYear", Matchers.equalTo(2024));
    }

    @Test
    void createHarvestDetails_success() {
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body("{\"harvestId\": 2,\"treeId\": 5,\"yield\": 100.0,\"harvestedAt\": \"19-08-2024\"}")
                .when()
                .post("/api/harvests/harvest-details")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("harvest.id", Matchers.equalTo(2))
                .body("tree.id", Matchers.equalTo(5))
                .body("yield", Matchers.equalTo(100.0));
    }

    @Test
    void updateHarvest_success() {
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body("{\"season\": \"WINTER\",\"harvestYear\": 2024}")
                .when()
                .post("/api/harvests")
                .then()
                .statusCode(HttpStatus.CREATED.value());

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body("{\"season\": \"SPRING\",\"harvestYear\": 2025}")
                .when()
                .put("/api/harvests/4")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("season", Matchers.equalTo("SPRING"))
                .body("harvestYear", Matchers.equalTo(2025));
    }

    @Test
    void deleteHarvest_success() {
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body("{\"season\": \"WINTER\",\"harvestYear\": 2024}")
                .when()
                .post("/api/harvests")
                .then()
                .statusCode(HttpStatus.CREATED.value());

        RestAssured
                .given()
                .when()
                .delete("/api/harvests/4")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    void getHarvest_notFound() {
        RestAssured
                .given()
                .accept(ContentType.JSON)
                .when()
                .get("/api/harvests/999")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body("message", Matchers.equalTo("Harvest not found"))
                .body("status", Matchers.equalTo(404));
    }

    @Test
    void createHarvest_invalidInput() {
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body("{\"season\": \"MARS\",\"harvestYear\": 2024}")
                .when()
                .post("/api/harvests")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("message", Matchers.notNullValue())
                .body("status", Matchers.equalTo(400));
    }

}
