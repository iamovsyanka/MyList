package by.ovsyanka.mylist.IntegrationTests;

import by.ovsyanka.mylist.Entity.Role;
import by.ovsyanka.mylist.Security.jwt.JwtTokenProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {
    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    JwtTokenProvider jwtTokenProvider;
    private MockMvc mockMvc;

    protected void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void shouldNotAllowedAccessToUnauthenticatedUsers() throws Exception {
        setUp();
        mockMvc.perform(MockMvcRequestBuilders.get("/api/tasks/"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void shouldGenerateAuthToken() throws Exception {
        setUp();
        List<Role> roles = new ArrayList<>();
        Role role = new Role();
        role.setRole("ROLE_ADMIN");
        roles.add(role);
        String token = jwtTokenProvider.createToken("Admin", roles);
        Authentication authentication = jwtTokenProvider.getAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/tasks/").header("Authorization", "Bearer " + token))
                .andExpect(status().is4xxClientError());
    }
}

