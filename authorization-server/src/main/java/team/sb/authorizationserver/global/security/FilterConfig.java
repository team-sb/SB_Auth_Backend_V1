package team.sb.authorizationserver.global.security;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import team.sb.authorizationserver.global.error.ExceptionFilter;

public class FilterConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        ExceptionFilter exceptionFilter = new ExceptionFilter();

        builder.addFilterBefore(exceptionFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
