package vn.funix.ccdn.config;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import vn.funix.ccdn.service.UserServiceImpl;


@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource securityDataSource;
	
	@Resource
    private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//add our users for in memory authentication
		
//		UserBuilder users = User.withDefaultPasswordEncoder();
//		
//		auth.inMemoryAuthentication()
//			.withUser(users.username("john").password("test123").roles("EMPLOYEE"))
//			.withUser(users.username("mary").password("test123").roles("MANAGER"))
//			.withUser(users.username("susan").password("test123").roles("ADMIN"));
		auth.jdbcAuthentication().dataSource(securityDataSource)
		.usersByUsernameQuery("SELECT email, password, status as enabled FROM user WHERE email=?")
		.authoritiesByUsernameQuery("SELECT email, authority FROM authorities WHERE email=?")
        ;
//		auth.userDetailsService(userService);
		
		auth.authenticationProvider(authProvider());
	}
	
	@SuppressWarnings("deprecation")
	@Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        //authProvider.setPasswordEncoder(passwordEncoder());
        authProvider.setPasswordEncoder(passwordEncoderBCrypt2()); //failed
        
        return authProvider;
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()//we're going to restrict access based on the HttpServletRequest coming in
				.antMatchers("/resources/**").permitAll() //enable static resources loaded without authentication
				.antMatchers("/auth/**").permitAll()
				.antMatchers("/index").permitAll()
				.antMatchers("/").permitAll()//enable root path to redirect to public view homepage
				.anyRequest().authenticated() //any request to the app must be authenticated (ie logged in)
				.and()
			.sessionManagement()
		        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
		        .and()
			.formLogin()
				.loginPage("/auth/login") //show our custom form at the request mapping
				.usernameParameter("email") //use email in replace of username
				.loginProcessingUrl("/authenticateTheUser") //Login form should POST data to this URL for processing - no need to implement this endpoint, Spring supply it freely
				.failureHandler(new CustomAuthenticationFailureHandler()) // Set the custom failure handler
			     .defaultSuccessUrl("/", true)
				.permitAll()//allow everyone to see login page
				.and()
			.logout()
	            .logoutUrl("/logout")  // Đường dẫn để thực hiện logout
	            .deleteCookies("JSESSIONID") //delete cookies by name
	            .logoutSuccessUrl("/?logout") // Đường dẫn sau khi logout thành công
	            .permitAll()
	            .and()
            .exceptionHandling().accessDeniedPage("/access-denied");
	}

	/* plain text */
	@Bean
    public PasswordEncoder passwordEncoder() {
		Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("noop", NoOpPasswordEncoder.getInstance());

        return new DelegatingPasswordEncoder("noop", encoders);
    }
    /**/
	
	
	/* BCrypt: không có prefix {bcrypt} trong data column */
	@Bean
    public BCryptPasswordEncoder passwordEncoderBCrypt1() {
        return new BCryptPasswordEncoder();
    }
	/**/
	
	
	/* BCrypt: có prefix {bcrypt} trong data column */
	@Bean
    public PasswordEncoder passwordEncoderBCrypt2() {
		Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("bcrypt", new BCryptPasswordEncoder());

        return new DelegatingPasswordEncoder("bcrypt", encoders);
    }
	
}
