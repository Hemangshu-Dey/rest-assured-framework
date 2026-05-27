package com.hemangshu.api.tests;

import com.hemangshu.api.pojo.LoginRequest;
import com.hemangshu.api.pojo.LoginResponse;
import com.hemangshu.api.pojo.OrderDetails;
import com.hemangshu.api.pojo.Orders;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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

        //Place order

        RequestSpecification  createOrderBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addHeader("authorization", token).setContentType(ContentType.JSON).build();

        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setCountry("India");
        orderDetails.setProductOrderedId(productId);

        List<OrderDetails> orderDetailsList = new ArrayList<OrderDetails>();
        orderDetailsList.add(orderDetails);

        Orders orders = new Orders();
        orders.setOrders(orderDetailsList);

        RequestSpecification createOrderReq = given().log().all().spec(createOrderBaseReq).body(orders);

       String responseAddOrder =  createOrderReq.when().post("api/ecom/order/create-order").then().log().all().extract().asString();

       System.out.println(responseAddOrder);

       //Delete order

        RequestSpecification  deleteOrderBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addHeader("authorization", token).setContentType(ContentType.JSON).build();

        RequestSpecification deleteProdReq = given().log().all().spec(deleteOrderBaseReq).pathParam("productId",productId);
        String deleteResponse = deleteProdReq.when().delete("api/ecom/product/delete-product/{productId}")
                .then().log().all().extract().response().asString();

        System.out.println(deleteResponse);
    }
}
