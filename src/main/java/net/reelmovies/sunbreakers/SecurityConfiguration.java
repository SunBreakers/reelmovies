package net.reelmovies.sunbreakers;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// Configures AWS Cognito login and logout
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter 
{
    @Override
    protected void configure(HttpSecurity http) throws Exception 
    {
        http.csrf()
            .and()
            /* Causes error: Cross-Origin Read Blocking (CORB) blocked cross-origin response with MIME type text/html
            .authorizeRequests(authz -> authz.mvcMatchers("/")
                .permitAll()
                .anyRequest()
                .authenticated())
            */
            .oauth2Login()
            .and()
            .logout()
            .logoutSuccessUrl("/");
    }
}