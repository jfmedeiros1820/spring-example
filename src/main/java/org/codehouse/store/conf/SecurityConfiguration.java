package org.codehouse.store.conf;

import org.codehouse.store.daos.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Enable secutiry for the application. WebSecurityConfigurerAdapter configures the resources.
 * 
 * @author Joao Felipe de Medeiros Moreira
 */
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserDAO userDAO;

  /**
   * Configure the security areas. The order matters, first block and than allow.
   * Obs: hasRole already expects to have a ROLE_* on the name of the role.
   * 
   * @author Joao Felipe de Medeiros Moreira
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/products/form").hasRole("ADMIN")
        .antMatchers("/cart", "/cart/**").permitAll()
        .antMatchers(HttpMethod.POST, "/products/", "/products").hasRole("ADMIN")
        .antMatchers(HttpMethod.GET, "/products/", "/products").hasRole("ADMIN")
        .antMatchers("/products/**").permitAll()
        .antMatchers("/").permitAll()
        .anyRequest().authenticated()
        .and().formLogin().loginPage("/login").permitAll()
        .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
  }

  /**
   * Ignore all folders of resources to be loaded.
   * 
   * @author Joao Felipe de Medeiros Moreira
   */
  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/resources/**");
  }

  /**
   * Specify whitch DAO is responsable for the authentication of the user.
   * 
   * @author Joao Felipe de Medeiros Moreira
   */
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDAO)
        .passwordEncoder(new BCryptPasswordEncoder());
  }
}
