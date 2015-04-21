package com.razborka.configuration;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.security.config.BeanIds;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

public class AppInitializer implements WebApplicationInitializer {

    @Override
	public void onStartup(ServletContext container) throws ServletException {

        registerEncodingFilter(container, "encodingFilter");
        registerSpringSecurityFilterChain(container);

        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(AppConfig.class);
        ctx.register(SecurityConfig.class);
		ctx.setServletContext(container);

        container.addListener(new ContextLoaderListener(ctx));
		ServletRegistration.Dynamic servlet = container.addServlet(
                "dispatcher", new DispatcherServlet(ctx));

        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
	}

    private void registerEncodingFilter(ServletContext servletContext, String name) {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setForceEncoding(true);
        filter.setEncoding("UTF-8");
        servletContext.addFilter(name, filter).addMappingForUrlPatterns(null, false, "/*");
    }

    private void registerSpringSecurityFilterChain(ServletContext servletContext) {
        FilterRegistration.Dynamic springSecurityFilterChain = servletContext.addFilter(
                BeanIds.SPRING_SECURITY_FILTER_CHAIN,
                new DelegatingFilterProxy());
        springSecurityFilterChain.addMappingForUrlPatterns(null, false, "/*");
    }
}