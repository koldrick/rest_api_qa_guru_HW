import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class LoginTests {

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api";
    }

    @Test
    void successLogin() {
        String authData = "{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"cityslicka\"\n" +
                "}";
        given()
                .body(authData)
                .contentType(JSON)
                .log().uri()
                .when()
                .post("/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("token", notNullValue())
                .body("token", is("QpwL5tke4Pnpja7X4"));


    }

    @Test
    void unSuccessLoginUserNotFound() {
        String authData = "{\n" +
                "    \"email\": \"eve.holt@test.in\",\n" +
                "    \"password\": \"cityslicka\"\n" +
                "}";
        given()
                .body(authData)
                .contentType(JSON)
                .log().uri()
                .when()
                .post("/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("user not found"));


    }

    @Test
    void unSuccessLoginNoData() {
        given()
                .log().uri()
                .post("/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(415);

    }
    @Test
    void unSuccessLoginMissingPassword() {
        String authData = "{\n" +
                "    \"email\": \"eve.holt@test.in\",\n" +
                "    \"password\": \"\"\n" +
                "}";
        given()
                .body(authData)
                .contentType(JSON)
                .log().uri()
                .when()
                .post("/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));


    }

    @Test
    void unSuccessLoginMissingEmail() {
        String authData = "{\n" +
                "    \"email\": \"\",\n" +
                "    \"password\": \"cityslicka\"\n" +
                "}";
        given()
                .body(authData)
                .contentType(JSON)
                .log().uri()
                .when()
                .post("/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing email or username"));


    }
}
