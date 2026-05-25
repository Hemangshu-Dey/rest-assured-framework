package com.hemangshu.api.tests;

import com.hemangshu.api.payloads.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

    public static void main() {

        JsonPath js = new JsonPath(payload.CoursePrice());

        // Print the number of courses
        int count = js.getInt("courses.size()");
        System.out.println(count);

        //Print all course titles and respective prices
        for (int i = 0; i < count; i++) {
            String courseTitles = js.get("courses[" + i + "].title");
            System.out.println(courseTitles);
        }

//        int totalSum =0;
//        for (int i=0; i < count; i++){
//            totalSum = totalSum + js.get("courses["+i+"].price");
//        }

    }
}
