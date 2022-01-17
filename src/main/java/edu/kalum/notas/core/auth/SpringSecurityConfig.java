package edu.kalum.notas.core.auth;


import edu.kalum.notas.core.models.services.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UsuarioServiceImpl userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService((UserDetailsService) this.userDetailsService).passwordEncoder((PasswordEncoder) passwordEncoder());
    }

    @Bean({"authenticationManager"})
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    public void configure(HttpSecurity http) throws Exception {
        ((HttpSecurity) ((HttpSecurity) ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl) http.authorizeRequests()
                .anyRequest()).authenticated()
                .and())
                .csrf().disable())
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
