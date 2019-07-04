package com.integrationtest.springboot.studentservices;

import com.integrationtest.springboot.StudentServicesApplication;
import com.integrationtest.springboot.model.Course;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StudentServicesApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class StudentControllerIT {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();


    @Test
    public void testRetrieveStudentCourse() throws JSONException {
        HttpEntity<String> entity = new HttpEntity<String>(null,headers);
        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/students/Student1/courses/Course1"),
                HttpMethod.GET, entity, String.class);

        String expected = "{course_Id:Course1,course_Name:Spring,course_Discription:\"10 Steps\"}";
        JSONAssert.assertEquals(expected,response.getBody(),false);
    }

    @Test
    public void addCourse() {
        Course course = new Course("Course1", "Spring", "10 Steps",
                Arrays.asList("Learn Maven", "Import Project", "First Example",
                        "Second Example"));

        HttpEntity<Course> entity = new HttpEntity<Course>(course,headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                createURLWithPort("/students/Student1/courses"),
                HttpMethod.POST, entity, String.class);

        String actual = responseEntity.getHeaders().get(HttpHeaders.LOCATION).get(0);
        assertTrue(actual.contains("/students/Student1/courses/"));

    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
