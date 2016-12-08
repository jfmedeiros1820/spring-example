package org.codehouse.store.conf;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

public class ServletSpringMVC extends AbstractAnnotationConfigDispatcherServletInitializer {

  /**
   * Load the configurations during the application start.
   * 
   * @author Joao Felipe de Medeiros Moreira
   */
  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class[] {
        SecurityConfiguration.class,
        AppWebConfiguration.class,
        JPAConfiguration.class,
        JPAProductionConfiguration.class};
  }

  /**
   * Which class will take care of the configuration about the application.
   * 
   * @author Joao Felipe de Medeiros Moreira
   */
  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class[] {};
  }

  /**
   * Specify witch URI Spring will take care. After the / everything will be controlled by Spring
   * 
   * @author Joao Felipe de Medeiros Moreira
   */
  @Override
  protected String[] getServletMappings() {
    return new String[] {"/"};
  }

  /**
   * Configure the filters for the application. Specify the encoding used by the server. UTF-8
   * 
   * @author Joao Felipe de Medeiros Moreira
   */
  @Override
  protected Filter[] getServletFilters() {
    CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
    encodingFilter.setEncoding("UTF-8");
    return new Filter[] {encodingFilter, new OpenEntityManagerInViewFilter()};
  }

  @Override
  protected void customizeRegistration(Dynamic registration) {
    registration.setMultipartConfig(new MultipartConfigElement(""));
  }

  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {
    super.onStartup(servletContext);
    servletContext.addListener(RequestContextListener.class);
    servletContext.setInitParameter("spring.profiles.active", "dev");
  }
}
