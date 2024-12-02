package com.microboxlabs;

import com.microboxlabs.service.contract.form.FileUploadForm;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static io.restassured.RestAssured.given;

@QuarkusTest
class GreetingResourceTest {
    @Test
    @TestSecurity(user = "admin", roles = {"admin"})
    void testUploadLog() {
        FileUploadForm form = new FileUploadForm();
        form.setFile(new ByteArrayInputStream("test-log-file-content".getBytes())); // Mocked file content

        given()
                .multiPart("file", "test.log", form.getFile())
                .when()
                .post("/api/logs/upload")
                .then()
                .statusCode(200);
    }

}