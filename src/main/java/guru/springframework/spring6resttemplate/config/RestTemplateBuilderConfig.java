package guru.springframework.spring6resttemplate.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.client.RestTemplateBuilderConfigurer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class RestTemplateBuilderConfig {

    @Value("${rest.template.rootUrl}")
    String rootUrl;


    @Value("${spring.security.user.name}")
    public static final String USERNAME = "user1";
    @Value("${spring.security.user.password}")
    public static final  String PASSWORD = "password";


    @Bean
    RestTemplateBuilder restTemplateBuilder(RestTemplateBuilderConfigurer configurer) {

        assert rootUrl !=null;

        RestTemplateBuilder builder = configurer.configure(new RestTemplateBuilder());
        DefaultUriBuilderFactory uriBuilderFactory = new DefaultUriBuilderFactory(rootUrl);

        RestTemplateBuilder builderWithAuth = builder.basicAuthentication(USERNAME, PASSWORD);
        /*
        Returns new instance that way
        * */
        return builderWithAuth.uriTemplateHandler(uriBuilderFactory);
    }

}
