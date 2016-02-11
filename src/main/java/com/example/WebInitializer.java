package com.example;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.SessionTrackingMode;

import org.apache.wicket.protocol.http.WicketFilter;
import org.apache.wicket.spring.SpringWebApplicationFactory;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebInitializer implements ServletContextInitializer {

  @Override
  public void onStartup(ServletContext context) throws ServletException {
    FilterRegistration filter = context.addFilter("wicket-filter", WicketFilter.class);
    filter.setInitParameter(WicketFilter.APP_FACT_PARAM, SpringWebApplicationFactory.class.getName());
    filter.setInitParameter("applicationBean", "wicketApplication");
    filter.setInitParameter(WicketFilter.FILTER_MAPPING_PARAM, "/*");
    filter.addMappingForUrlPatterns(null, false, "/*");
    filter.setInitParameter("configuration", "development");
  }

  @Bean
  public ServletContextInitializer servletContextInitializer() {
    return new ServletContextInitializer() {
      @Override
      public void onStartup(ServletContext ctx) throws ServletException {
        ctx.setSessionTrackingModes(Stream.of(SessionTrackingMode.COOKIE).collect(Collectors.toSet()));
      }
    };
  }

}
