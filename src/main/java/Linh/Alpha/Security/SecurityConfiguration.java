package Linh.Alpha.Security;

import org.hibernate.sql.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.DELETE;
import static Linh.Alpha.Modell.Roles.ADMIN;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
		@Autowired
		private final JwtAuthenticationFilter jwtAuthFilter;
		@Autowired
		private final AuthenticationProvider authenticationProvider;

		//configuring all http security of the application
		@Bean
		public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
			http
				.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(req ->
				req.requestMatchers("/auth/**") // white list //remove test later as i was testing with angular, "/alpha/**"
						//implement the login function from front end
				.permitAll()
				//.requestMatchers(DELETE, "alpha/user/**").hasAnyRole(ADMIN.name()
						//.requestMatchers("/alpha/").hasAnyRole(ADMIN.name())
				.anyRequest()
				.authenticated()
				)
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // create new session for each request
				.authenticationProvider(authenticationProvider)
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
				
			return http.build();
		}
}
