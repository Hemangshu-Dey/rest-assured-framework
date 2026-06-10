package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class SpecBuilder {

    private SpecBuilder() {
        // Prevent object creation
    }

    public static RequestSpecification getRequestSpec() {

        return new RequestSpecBuilder()
                .setBaseUri(ConfigReader.getProperty("baseUrl"))
                .addHeader("Content-Type", "application/json")
                .build();
    }
}