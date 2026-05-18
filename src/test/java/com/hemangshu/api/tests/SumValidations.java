package com.hemangshu.api.tests;

import com.hemangshu.api.payloads.payload;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SumValidations {

    @Test
    public void sumOfCourses(){
        JsonPath js = new JsonPath(payload.CoursePrice());

        int count = js.getInt("courses.size()");

        int amount =0;
        for ( int i=0; i<count; i++){
            int prices = js.getInt("courses["+i+"].price");
            int copies = js.getInt("courses["+i+"].copies");
            amount = amount + (prices * copies);
        }
        System.out.print(amount);
        int purchaseAmount = js.getInt("dashboard.purchaseAmount");
        Assert.assertEquals(amount,purchaseAmount);
    }
}
