package api.apiTest.filaB;

import api.config.Configuration;
import api.factoryRequest.FactoryRequest;
import api.factoryRequest.RequestInfo;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Base64;

import static org.hamcrest.Matchers.equalTo;

public class Ejercicio1 {
    private RequestInfo requestInfo;
    private Response response;
    private JSONObject body;
    private String auth;

    @BeforeEach
    public void setup() {
        requestInfo = new RequestInfo();
        body = new JSONObject();
        auth = Base64.getEncoder().encodeToString((Configuration.user2 + ":" + Configuration.password).getBytes());
    }

    @Test
    public void verifyCreateUserAndProject() {
        createUser();
        String token = getToken();
        createProject("ProjectIgnacio", 2, token);
        deleteToken(token);
        createProjectWithDeletedToken("NuevoProjectIgnacio", 3, token);
    }

    private void createUser() {
        body.put("Email", Configuration.user2);
        body.put("Password", Configuration.password);
        body.put("FullName", "ignacio lizarazu");
        requestInfo.setHost(Configuration.host + "api/user.json").setBody(body.toString());
        response = FactoryRequest.make("post").send(requestInfo);
        response.then()
                .log().all()
                .statusCode(200)
                .body("Email", equalTo(body.get("Email")))
                .body("FullName", equalTo(body.get("FullName")));
    }

    private String getToken() {
        body.clear();
        requestInfo.setHost(Configuration.host + "api/authentication/token.json").setHeader("Authorization", "Basic " + auth);
        response = FactoryRequest.make("get").send(requestInfo);
        response.then()
                .log().all()
                .statusCode(200)
                .body("UserEmail", equalTo(Configuration.user2));

        return response.then().extract().path("TokenString");
    }

    private void createProject(String content, int icon, String token) {
        body.put("Content", content);
        body.put("Icon", icon);
        requestInfo.removeHeader("Authorization").setHost(Configuration.host + "api/projects.json")
                .setBody(body.toString()).setHeader("Token", token);
        response = FactoryRequest.make("post").send(requestInfo);
        response.then()
                .log().all()
                .statusCode(200)
                .body("Content", equalTo(body.get("Content")))
                .body("Icon", equalTo(body.get("Icon")));
    }

    private void deleteToken(String token) {
        requestInfo.setHost(Configuration.host + "api/authentication/token.json").setHeader("Token", token);
        response = FactoryRequest.make("delete").send(requestInfo);
        response.then()
                .log().all()
                .statusCode(200)
                .body("TokenString", equalTo(token));
    }

    private void createProjectWithDeletedToken(String content, int icon, String token) {
        body.clear();
        body.put("Content", content);
        body.put("Icon", icon);
        requestInfo.setHost(Configuration.host + "api/projects.json").setBody(body.toString()).setHeader("Token", token);
        response = FactoryRequest.make("post").send(requestInfo);
        response.then()
                .log().all()
                .statusCode(200)
                .body("ErrorCode", equalTo(102));
    }
}
