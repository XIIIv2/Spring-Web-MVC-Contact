package icu.xiii.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"icu.xiii.app"})
public class WebMvcConfig implements WebMvcConfigurer {

    private final String CHARACTER_ENCODING = "UTF-8";

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver sRTR = new SpringResourceTemplateResolver();
        sRTR.setApplicationContext(this.applicationContext);
        sRTR.setPrefix("/WEB-INF/templates/");
        sRTR.setSuffix(".html");
        sRTR.setTemplateMode(TemplateMode.HTML);
        sRTR.setCacheable(true);
        sRTR.setCharacterEncoding(CHARACTER_ENCODING);
        return sRTR;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine sTE = new SpringTemplateEngine();
        sTE.setTemplateResolver(templateResolver());
        sTE.setEnableSpringELCompiler(true);
        return sTE;
    }

    @Bean
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver tVR = new ThymeleafViewResolver();
        tVR.setCharacterEncoding(CHARACTER_ENCODING);
        tVR.setTemplateEngine(templateEngine());
        return tVR;
    }
}
