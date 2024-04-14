package api.tests;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.payloads.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.logging.Logger;

/*
 *@className -UserTests2
 * @Objective- The class have the testcases for the user related tests and endpoints are retrieved through properties file.
 */
public class UserTests2 {
    Faker faker;
    User userPayload;
    public Logger logger;
    //set random data in user payload
    @BeforeClass
    public void setupData(){
        faker = new Faker();
        userPayload = new User();
        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password());
        userPayload.setPhone(faker.phoneNumber().cellPhone());

        //logs
        logger = Logger.getLogger(UserTests.class.getName());

    }

    //Testcase :- user created -> 200 status tested
    @Test(priority = 1)
    public void testPostUser(){
        logger.info("******************************** Creating User************************************");
        Response response = UserEndPoints2.createUser(userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
        logger.info("******************************** User is Created ************************************");
    }

    //Testcase :- get user created in payload->200 status code verified
    @Test(priority = 2)
    public void testGetUser(){
        logger.info("******************************** Reading User Info************************************");
        Response response= UserEndPoints2.readUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
        logger.info("******************************** User Info Displayed ************************************");
    }

    //Testcase :- user updated -> 200 status tested
    @Test(priority = 3)
    public void testUpdateUserByName(){
        logger.info("******************************** Updating User************************************");
        //update data using payload
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());

        Response response = UserEndPoints2.updateUser(this.userPayload.getUsername(),userPayload);

        //another way of validation assertion ->200)
        // response.then().log().body().statusCode(200);

        response.then().log().body();
        Assert.assertEquals(response.getStatusCode(),200);

        //checking data after update
        Response responseAfterUpdate= UserEndPoints2.readUser(this.userPayload.getUsername());
        Assert.assertEquals(responseAfterUpdate.getStatusCode(),200);
        logger.info("******************************** User Info Updated ***********************************");

    }


    //Testcase :- user deleted-> 200 status tested
    @Test(priority = 4)
    public void testDeleteUserByName(){
        logger.info("******************************** Deleting User************************************");
        Response response = UserEndPoints2.deleteUser(this.userPayload.getUsername());
        Assert.assertEquals(response.getStatusCode(),200);
        logger.info("******************************** User Deleted ************************************");

    }
}
