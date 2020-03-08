package com.harambase.hackathon.application;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Controller
@CrossOrigin
public class ResourceController extends WebMvcConfigurationSupport {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

//        registry.addResourceHandler("./static/pcp/**").addResourceLocations("classpath:/static/pcp/");
//        registry.addResourceHandler("/static/pcp/**").addResourceLocations("classpath:/static/pcp/");
//        registry.addResourceHandler("/static/static/pcp/**").addResourceLocations("classpath:/static/pcp/");
//        registry.addResourceHandler("/static/pioneer/**").addResourceLocations("classpath:/static/pioneer/");
//        registry.addResourceHandler("/static/images/**").addResourceLocations("classpath:/static/home/images/");
//        registry.addResourceHandler("/static/img/**").addResourceLocations("classpath:/static/pcp/img/");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/home/js/");
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/home/css/");
        registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/home/img/");
        registry.addResourceHandler("/fonts/**").addResourceLocations("classpath:/static/home/fonts/");
        registry.addResourceHandler("/images/**").addResourceLocations("classpath:/static/home/images/");

        super.addResourceHandlers(registry);
    }
}
