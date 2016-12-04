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
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@EnableWebMvc
@ComponentScan(basePackageClasses = {HomeController.class, ProductDAO.class, FileSaver.class, Cart.class})
@EnableCaching
public class AppWebConfiguration extends WebMvcConfigurerAdapter {

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

  @Override
  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    configurer.enable();
  }

  @Bean
  public MessageSource messageSource() {
    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
    messageSource.setBasename("/WEB-INF/message");
    messageSource.setDefaultEncoding("UTF-8");
    messageSource.setCacheSeconds(1);
    return messageSource;
  }

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

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @Bean
  public CacheManager cacheManager() {
    CacheBuilder<Object, Object> builder =
        CacheBuilder.newBuilder().maximumSize(100).expireAfterAccess(5, TimeUnit.MINUTES);
    GuavaCacheManager manager = new GuavaCacheManager();
    manager.setCacheBuilder(builder);
    return manager;
  }

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
}
