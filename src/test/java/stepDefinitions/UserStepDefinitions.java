package stepDefinitions;

import api.APIException;
import api.HttpResponse;
import api.JsonPlaceHolder;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import models.User;

import java.io.IOException;
import java.net.HttpURLConnection;

public class UserStepDefinitions {
    private User tempUser = null;
    private HttpResponse tempResp = null;

    JsonPlaceHolder js = new JsonPlaceHolder();

    String strNewUserInfo = "{\"id\":11,\"name\":\"Sam Vo\",\"username\":\"samvo\",\"email\":\"Sincere@april.biz\",\"address\":{\"street\":\"Kulas Light\",\"suite\":\"Apt. 556\",\"city\":\"Gwenborough\",\"zipcode\":\"92998-3874\",\"geo\":{\"lat\":\"-37.3159\",\"lng\":\"81.1496\"}},\"phone\":\"1-770-736-8031 x56442\",\"website\":\"hildegard.org\",\"company\":{\"name\":\"Romaguera-Crona\",\"catchPhrase\":\"Multi-layered client-server neural-net\",\"bs\":\"harness real-time e-markets\"}}";

    @Given("user info with Id (\\d+) is retrieved using API")
    public void getCorrectUserInformation(int userId){
        try {
            tempUser = js.getSingleUser(userId);
        } catch (APIException ex) {
            HttpResponse resp = ex.getHttpResponse();
            System.out.println("Unable to get user infomation. Error code: " + resp.getHttpCode());
            System.out.println("Response: " + resp.getHttpResponse());
            assert (false);
        }
    }

    @Then("information of user with Id 1 is correct")
    public void VerifyUserInfo() throws Exception {
        String expectedUserInfo = "{\"id\":1,\"name\":\"Leanne Graham\",\"username\":\"Bret\",\"email\":\"Sincere@april.biz\",\"address\":{\"street\":\"Kulas Light\",\"suite\":\"Apt. 556\",\"city\":\"Gwenborough\",\"zipcode\":\"92998-3874\",\"geo\":{\"lat\":\"-37.3159\",\"lng\":\"81.1496\"}},\"phone\":\"1-770-736-8031 x56442\",\"website\":\"hildegard.org\",\"company\":{\"name\":\"Romaguera-Crona\",\"catchPhrase\":\"Multi-layered client-server neural-net\",\"bs\":\"harness real-time e-markets\"}}";
        User expectedUser = User.UserParser.parseUserFromJson(expectedUserInfo);
        String errorMsg = expectedUser.compareUserInfo(tempUser);
        if (!errorMsg.equals("")) {
            System.out.println(errorMsg);
            tempUser = null;
            assert (false);
        }
    }

    @Given("new user info is submitted to API")
    public void CreateNewUser() throws IOException {
        User newUser = User.UserParser.parseUserFromJson(strNewUserInfo);
        try {
            tempUser = js.createUser(newUser);
        } catch (APIException ex) {
            HttpResponse resp = ex.getHttpResponse();
            System.out.println("Unable to create new user. Error code: " + resp.getHttpCode());
            System.out.println("Response: " + resp.getHttpResponse());
            assert (false);
        }
    }

    @Then("API should return submitted user information")
    public void VerifyReturnedUser() throws IOException {
        User expectedUser = User.UserParser.parseUserFromJson(strNewUserInfo);
        String errorMsg = expectedUser.compareUserInfo(tempUser);
        if (!errorMsg.equals("")) {
            System.out.println(errorMsg);
            tempUser = null;
            assert (false);
        }
    }

    @Given("user info with existing user Id is submitted to API")
    public void CreateUserWithExistingId() throws IOException {
        String strNewUserInfoWithExistingId = "{\"id\":1,\"name\":\"Sam Vo\",\"username\":\"samvo\",\"email\":\"Sincere@april.biz\",\"address\":{\"street\":\"Kulas Light\",\"suite\":\"Apt. 556\",\"city\":\"Gwenborough\",\"zipcode\":\"92998-3874\",\"geo\":{\"lat\":\"-37.3159\",\"lng\":\"81.1496\"}},\"phone\":\"1-770-736-8031 x56442\",\"website\":\"hildegard.org\",\"company\":{\"name\":\"Romaguera-Crona\",\"catchPhrase\":\"Multi-layered client-server neural-net\",\"bs\":\"harness real-time e-markets\"}}";
        User newUser = User.UserParser.parseUserFromJson(strNewUserInfoWithExistingId);
        try {
            tempUser = js.createUser(newUser);
        } catch (APIException ex) {
            tempResp = ex.getHttpResponse();
        }
    }

    @Then("API should not accept the request and should return error")
    public void VerifyDuplicateIdError() {
        if (tempResp.equals(null))
            assert (false);
        else {
            if (tempResp.getHttpCode() != HttpURLConnection.HTTP_INTERNAL_ERROR)
                assert (false);
        }
    }
}
