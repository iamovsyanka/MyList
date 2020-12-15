package by.ovsyanka.mylist.Config;

import by.ovsyanka.mylist.Security.jwt.JwtConfigurer;
import by.ovsyanka.mylist.Security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    private static final String TASK_ENDPOINT = "/api/tasks/**";
    private static final String ADMIN_ENDPOINT = "/api/admin/**";

//    @Autowired
    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                    .httpBasic().disable()
                    .cors().
                and()
                    .csrf().disable()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .authorizeRequests()
                    .antMatchers(TASK_ENDPOINT).authenticated()
                    .antMatchers(ADMIN_ENDPOINT).hasRole("admin")
                    .anyRequest().permitAll()
                .and()
                    .apply(new JwtConfigurer(jwtTokenProvider));
    }
}
