package com.example.studentsmanagementsystem;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.studentsmanagementsystem.model.Classroom;
import com.example.studentsmanagementsystem.model.Student;
import com.fasterxml.jackson.databind.ObjectMapper;




@RunWith(SpringRunner.class)
@SpringBootTest(classes = StudentsManagementSystemApplication.class)
@WebAppConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class StudentControllerTest {


    private static final String STUDENT_NOT_FOUND_REASON = "The student was not found in the system";
    private static final String CLASSROOM_NOT_FOUND_REASON = "The classroom was not found in the system";
    private static final String DELETE_ERROR_REASON = "There is no item to delete";
	private static final String APPLICATION_JSON_UTF8 = null;
    
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;
    private JacksonTester<Student> studentJacksonTester;
    private JacksonTester<Classroom> classroomJacksonTester;
    
    private MockMvc mockMvc;
    
    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        JacksonTester.initFields(this, objectMapper);
    }

    @Test
    public void testCreateStudent() throws Exception {
        Student student = new Student();
        student.setFirstName("Divij");
        student.setLastName("Tyagi");
        mockMvc.perform(post("/students").contentType(APPLICATION_JSON_UTF8)
                                         .content(studentJacksonTester.write(student).getJson()))
               .andExpect(status().isOk())
               .andExpect(content().contentType(APPLICATION_JSON_UTF8))
               .andExpect(jsonPath("$.id", is(1)))
               .andExpect(jsonPath("$.firstName", is("Divij")))
               .andExpect(jsonPath("$.lastName", is("Tyagi")))
               .andExpect(jsonPath("$.classrooms", nullValue()));
    }

}
