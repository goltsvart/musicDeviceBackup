package hello;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/uploadForm").setViewName("uploadForm");
        registry.addViewController("/tracks").setViewName("/tracks");
        registry.addViewController("/albums").setViewName("/albums");
        registry.addViewController("/edit").setViewName("/edit");
        registry.addViewController("/register").setViewName("/register");
    }
}