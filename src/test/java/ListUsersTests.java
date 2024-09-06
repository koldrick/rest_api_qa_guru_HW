
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ListUsersTests extends TestBase {

    @Test
    void checkDataSizeTest () {
        given().
                when().
                get("/users?page=2").
                then().
                assertThat().
                statusCode(200).
                body("data.size()", is(6));
    }

    @Test
    void checkPageNumberTest () {
        given().
                when().
                get("/users?page=2").
                then().
                assertThat().
                statusCode(200).
                body("page", equalTo(2));
    }

    @Test
    void checkPerPageTest () {
        given().
                when().
                get("/users?page=2").
                then().
                assertThat().
                statusCode(200).
                body("per_page", equalTo(6));
    }

    @Test
    void checkSupportTextTest () {
        given().
                when().
                get("/users?page=2").
                then().
                assertThat().
                statusCode(200).
                body("support.text", equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));
    }

    @Test
    void checkFirstUserEmailTest () {
        given().
                when().
                get("/users?page=2").
                then().
                assertThat().
                statusCode(200).
                body("data[0].email", equalTo("michael.lawson@reqres.in"));
    }

}
