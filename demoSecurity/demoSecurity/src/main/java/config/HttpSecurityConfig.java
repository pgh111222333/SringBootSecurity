package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class HttpSecurityConfig {

    @SuppressWarnings("removal")
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/hello").permitAll()    // Không yêu cầu authentication
                .requestMatchers("/customer/all").authenticated()  // Yêu cầu authentication
                .anyRequest().denyAll()   // Mặc định chặn các request khác
            )
            .httpBasic(); // Sử dụng HTTP Basic Authentication (hoặc cấu hình khác)
        return http.build();
    }
}
