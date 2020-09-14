package org.smartframework.cloud.starter.core.business.autoconfigure;

import org.smartframework.cloud.starter.core.business.filter.ServletFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
@ConditionalOnClass(name = {"javax.servlet.Filter"})
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class ServletFilterAutoConfigure {

    @Bean
    public ServletFilter servletFilter() {
        return new ServletFilter();
    }

    @Bean
    public FilterRegistrationBean<Filter> registFilter(@Autowired ServletFilter servletFilter) {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
        registration.setFilter(servletFilter);
        registration.addUrlPatterns("/*");
        registration.setName(ServletFilter.class.getSimpleName());
        registration.setOrder(1);
        return registration;
    }

}