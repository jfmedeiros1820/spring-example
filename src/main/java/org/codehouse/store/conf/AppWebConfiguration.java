package org.codehouse.store.conf;

import com.google.common.cache.CacheBuilder;

import org.codehouse.store.controllers.HomeController;
import org.codehouse.store.daos.ProductDAO;
import org.codehouse.store.infra.FileSaver;
import org.codehouse.store.models.Cart;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@EnableWebMvc
@ComponentScan(basePackageClasses = {HomeController.class, ProductDAO.class, FileSaver.class, Cart.class})
@EnableCaching
public class AppWebConfiguration extends WebMvcConfigurerAdapter {

  /**
   * Method to find where the views folder are and witch suffix will be used. It`s possible to specify witch bean will be exposed to the view.
   * 
   * @author Joao Felipe de Medeiros Moreira
   */
  @Bean
  public InternalResourceViewResolver internalResourceViewResolver() {
    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
    resolver.setPrefix("/WEB-INF/views/");
    resolver.setSuffix(".jsp");
    resolver.setExposedContextBeanNames("cart");
    return resolver;
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/views/**").addResourceLocations("/views/");
  }

  /**
   * Configure the default servlet to handle requests to CSS and JS files.
   * 
   * @author Joao Felipe de Medeiros Moreira
   * @since 0.0.1
   *
   * @param configurer
   */
  @Override
  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    configurer.enable();
  }

  /**
   * Load message files and specify the encoding used. The cache is used to reload the file.
   * 
   * @author Joao Felipe de Medeiros Moreira
   */
  @Bean
  public MessageSource messageSource() {
    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
    messageSource.setBasename("/WEB-INF/message");
    messageSource.setDefaultEncoding("UTF-8");
    messageSource.setCacheSeconds(1);
    return messageSource;
  }

  /**
   * Servide responsable for formatting properties. In this case will format every date.
   * 
   * @author Joao Felipe de Medeiros Moreira
   */
  @Bean
  public FormattingConversionService mvcConversionService() {
    DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
    DateFormatterRegistrar register = new DateFormatterRegistrar();
    register.setFormatter(new DateFormatter("dd/MM/yyyy"));
    register.registerFormatters(conversionService);
    return conversionService;
  }

  @Bean
  public MultipartResolver multipartResolver() {
    return new StandardServletMultipartResolver();
  }

  /**
   * Configuration to handle with Rest request using Spring.
   */
  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  /**
   * Manages the cache of the application.
   * 
   * @author Joao Felipe de Medeiros Moreira
   */
  @Bean
  public CacheManager cacheManager() {
    CacheBuilder<Object, Object> builder =
        CacheBuilder.newBuilder().maximumSize(100).expireAfterAccess(5, TimeUnit.MINUTES);
    GuavaCacheManager manager = new GuavaCacheManager();
    manager.setCacheBuilder(builder);
    return manager;
  }

  /**
   * Specify with resources can be resolved by the application, in this case, can be a JSP files and JSON files.
   * 
   * @author Joao Felipe de Medeiros Moreira
   */
  @Bean
  public ViewResolver contentNegotiationViewResolver(ContentNegotiationManager manager) {
    List<ViewResolver> viewResolvers = new ArrayList<>();
    viewResolvers.add(internalResourceViewResolver());
    viewResolvers.add(new JsonViewResolver());

    ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
    resolver.setViewResolvers(viewResolvers);
    resolver.setContentNegotiationManager(manager);

    return resolver;
  }

  /**
   * Method to handle with the location changed on the page, this interceptor is to do this.
   * 
   * @author Joao Felipe de Medeiros Moreira
   */
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new LocaleChangeInterceptor());
  }

  /**
   * Set a cookie to keep the chosen made by the user.
   * 
   * @author Joao Felipe de Medeiros Moreira
   */
  @Bean
  public LocaleResolver localeResolver() {
    return new CookieLocaleResolver();
  }

  @Bean
  public MailSender mailSender() {
    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

    mailSender.setHost("smtp.gmail.com");
    mailSender.setUsername("alura.springmvc@gmail.com");
    mailSender.setPassword("alura2015");
    mailSender.setPort(587);

    Properties mailProperties = new Properties();
    mailProperties.put("mail.smtp.auth", true);
    mailProperties.put("mail.smpt.starttls.enable", true);

    mailSender.setJavaMailProperties(mailProperties);
    return mailSender;
  }
}
