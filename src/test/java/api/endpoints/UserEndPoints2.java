package api.endpoints;

import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;
/*
 *@className -UserEndPoints2
 * @Objective- The class created for performing create ,update, delete ,read requests in the user API for properties file .
 */

public class UserEndPoints2 {
        //method created for getting URL's from properties file
        static ResourceBundle getURL(){
            //to load resource from properties file
            ResourceBundle routes = ResourceBundle.getBundle("routes");
            return routes;
        }

        //create user
        public static Response createUser(User payload){
            //stored in key value pairs in properties file so for key value is get in string form and stored in var
            String post_url=getURL().getString("post_url");
            Response response =given()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(payload)
                    .when()
                    .post(post_url);
            return response;
        }

        //read user
        public static Response readUser(String userName){
            String get_url = getURL().getString("get_url");
            Response response =given()
                    .pathParam("username",userName)

                    .when()
                    .get(get_url);
            return response;
        }

        //update user
        public static Response updateUser(String userName, User payload){
            String update_url = getURL().getString("update_url");
            Response response =given()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .pathParam("username",userName)
                    .body(payload)
                    .when()
                    .put(update_url);
            return response;
        }

        //delete user
        public static Response deleteUser(String userName){
            String delete_url = getURL().getString("delete_url");
            Response response = given()
                    .pathParam("username",userName)
                    .when()
                    .delete(delete_url);
            return response;
        }


}
