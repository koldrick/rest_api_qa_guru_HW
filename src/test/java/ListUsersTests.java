import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ListUsersTests {
    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api";
    }


    @Test
    void checkDataSize() {
        given().
                when().
                get("/users?page=2").
                then().
                assertThat().
                statusCode(200).
                body("data.size()", is(6));
    }

    @Test
    void checkPageNumber() {
        given().
                when().
                get("/users?page=2").
                then().
                assertThat().
                statusCode(200).
                body("page", equalTo(2));
    }

    @Test
    void checkPerPage() {
        given().
                when().
                get("/users?page=2").
                then().
                assertThat().
                statusCode(200).
                body("per_page", equalTo(6));
    }

    @Test
    void checkSupportText() {
        given().
                when().
                get("/users?page=2").
                then().
                assertThat().
                statusCode(200).
                body("support.text", equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));
    }

    @Test
    void checkFirstUserEmail() {
        given().
                when().
                get("/users?page=2").
                then().
                assertThat().
                statusCode(200).
                body("data[0].email", equalTo("michael.lawson@reqres.in"));
    }

}
