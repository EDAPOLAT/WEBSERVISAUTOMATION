package step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;;
public class StepImplementation {


  @BeforeTest
  public void setUp(){
      RestAssured.baseURI="https://petstore.swagger.io";

  }

   @Test
   public void getRequest() {
       RestAssured.baseURI = "https://petstore.swagger.io";
       RequestSpecification httpRequest = given();
       Response response = httpRequest.get("/v2/pet/12");
       ResponseBody body = response.getBody();
       String bodyAsString = body.asString();
       System.out.println(bodyAsString);
       Assert.assertTrue(bodyAsString.contains("dog"), "Doğru eşleşme");
 }


    @Test
    public void postRequestSuccess() {

        String postData = "{\"id\": 1,\n" +
                "  \"petId\": 1,\n" +
                "  \"quantity\": 0,\n" +
                "  \"shipDate\": \"2022-07-30T15:46:41.612Z\",\n" +
                "  \"status\": \"placed\",\n" +
                "  \"complete\": true}";
                given().
                contentType(ContentType.JSON).
                body(postData).
                when().
                post("https://petstore.swagger.io/v2/store/order").
                then().
                statusCode(200).
                log().all();

    }

    @Test
    public void deleteRequestSuccess(){
            given().
            when().
            delete("https://petstore.swagger.io/v2/store/order/30").
            then().
            statusCode(200).
            log().all();
            System.out.println("Kullanıcı Silindi");
}
    @Test
    public void putRequestSuccess() {
            String requestBody = "{\"id\": 0,\n" +
                "  \"username\": \"eda\",\n" +
                "  \"firstName\": \"string\",\n" +
                "  \"lastName\": \"string\",\n" +
                "  \"email\": \"string\",\n" +
                "  \"password\": \"string\",\n" +
                "  \"phone\": \"string\",\n" +
                "  \"userStatus\": 0}";
            Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .put("https://petstore.swagger.io/v2/user/eda")
                .then()
                .statusCode(200)
                .extract().response();
                System.out.println("Kullanıcı Güncellendi");

  }

}
