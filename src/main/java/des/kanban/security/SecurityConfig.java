package des.kanban.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
		 authorizeRequests().antMatchers("./","/index","/js/**","/css/**","/images/**","/usuario/alta","/login").permitAll().
		 antMatchers("/perfil/**","/tareas/**","/proyecto/**").hasAuthority("manager").
		 antMatchers("/perfil/**","/tareas/**","/proyecto/**").hasAuthority("trabajador").
		 and().
		 formLogin().
		 loginPage("/login").
		 loginProcessingUrl("/login").
		 usernameParameter("usuario").
		 passwordParameter("password").
		 successHandler(myAuthenticationSuccessHandler()).
		 permitAll().
		 and().
		 logout().
		 invalidateHttpSession(true).
		 deleteCookies("JSESSIONID").
		 clearAuthentication(true).
		 logoutUrl("/logout").
		 logoutRequestMatcher(new AntPathRequestMatcher("/logout")).
		 logoutSuccessUrl("/login?logout").
		 permitAll();
		
	}
	
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@Bean
	public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
		return new AuthenticationSuccessHandlerImp();
	}
	 
}
