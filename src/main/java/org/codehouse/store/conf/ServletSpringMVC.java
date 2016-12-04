package org.codehouse.store.conf;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

public class ServletSpringMVC extends AbstractAnnotationConfigDispatcherServletInitializer {

  @Override
  protected Class<?>[] getRootConfigClasses() {
    return null;
  }

  /**
   * Which class will take care of the configuration about the application.
   * 
   * @author Joao Felipe de Medeiros Moreira
   */
  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class[] {AppWebConfiguration.class, JPAConfiguration.class};
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
    return new Filter[] {encodingFilter};
  }

  @Override
  protected void customizeRegistration(Dynamic registration) {
    registration.setMultipartConfig(new MultipartConfigElement(""));
  }
}
