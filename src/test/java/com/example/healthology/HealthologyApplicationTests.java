package com.example.healthology;

import com.example.healthology.models.User;
import com.example.healthology.repositories.UsersRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.HttpSession;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class HealthologyApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UsersRepository userDao;

    @Autowired
    private PasswordEncoder pen;

    private User testUser;

    private HttpSession session;

    public void setUpTestUser() {
        testUser = userDao.findByUsername("testUser");
        if (testUser == null){
            testUser = new User();
            testUser.setUsername("testUser");
            testUser.setEmail("test@mail.com");
            testUser.setPassword(pen.encode("password"));
            this.testUser = userDao.save(testUser);
        }

        System.out.println("===============" + testUser.getUsername());
        System.out.println("===============" + testUser.getPassword());

    }


//    private void setUpSession() throws Exception {
//        session = mvc.perform(post("/login").with(csrf())
//                .param("username", testUser.getUsername())
//                .param("password", "password")
//        )
//                .andExpect(status().isFound())
//                .andReturn()
//                .getRequest()
//                .getSession();
//    }

    @Test
    void contextLoads() {
    }

}
