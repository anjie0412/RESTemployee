package employee.api;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
public class WebSecurity extends WebSecurityConfigurerAdapter{

	protected void configure(HttpSecurity http) throws Exception {
    	
    	http.authorizeRequests().antMatchers("Get").anonymous();
    	http.authorizeRequests().antMatchers("Delete").hasIpAddress("121.46.86.107");
    }
}
 