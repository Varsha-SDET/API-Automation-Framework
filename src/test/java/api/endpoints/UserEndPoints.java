package api.endpoints;

import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.post;

/*
 *@className -UserEndPoints
 * @Objective- The class created for performing create ,update, delete ,read requests in the user API.
 */
public class UserEndPoints {
    //create user
   public static Response createUser(User payload){
       Response response =given()
               .contentType(ContentType.JSON)
               .accept(ContentType.JSON)
               .body(payload)
               .when()
               .post(Routes.post_url);
       return response;
    }

    //read user
    public static Response readUser(String userName){
        Response response =given()
                .pathParam("username",userName)

                .when()
                .get(Routes.get_url);
        return response;
    }

    //update user
    public static Response updateUser(String userName, User payload){
        Response response =given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username",userName)
                .body(payload)
                .when()
                .put(Routes.update_url);
        return response;
    }

    //delete user
    public static Response deleteUser(String userName){
       Response response = given()
               .pathParam("username",userName)
               .when()
               .delete(Routes.delete_url);
       return response;
    }
}
