package com.hemangshu.api.tests;

import com.hemangshu.api.pojo.LoginRequest;
import com.hemangshu.api.pojo.LoginResponse;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

import java.io.File;

import static io.restassured.RestAssured.given;

public class EcommerceApiTest {
    public static void main() {

        RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .setContentType(ContentType.JSON).build();
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserEmail("hemangshu@yahoo.com");
        loginRequest.setUserPassword("1234@Asdf");

        RequestSpecification reqLogin = given().log().all().spec(req).body(loginRequest);
        LoginResponse loginResponse = reqLogin.when().post("api/ecom/auth/login").then().log().all().extract()
                .response().as(LoginResponse.class);
        System.out.println(loginResponse.getToken());
        String token = loginResponse.getToken();
        System.out.print(loginResponse.getUserId());
        String userId = loginResponse.getUserId();

        //Add  product

        RequestSpecification AddProductBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addHeader("authorization", token).build();
        RequestSpecification reqAddProduct = given().log().all().spec(AddProductBaseReq)
                .param("productName", "qwerty")
                .param("productAddedBy", userId)
                .param("productCategory", "fashion")
                .param("productSubCategory", "shirts")
                .param("productPrice", "11500")
                .param("productDescription", "Addi's Originals")
                .param("productFor", "women")
                .multiPart("productImage",new File("C:\\Users\\heman\\OneDrive\\Pictures\\Screenshots\\Screenshot 2023-09-30 224711.png"));

        String addProductResponse = reqAddProduct.when().post("api/ecom/product/add-product")
                .then().log().all().extract().response().asString();

        JsonPath js = new JsonPath(addProductResponse);
        String productId = js.get("productId");
        System.out.println(productId);
    }
}
