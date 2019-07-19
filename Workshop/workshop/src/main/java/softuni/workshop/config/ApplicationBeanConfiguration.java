package softuni.workshop.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import softuni.workshop.util.*;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public FileUtil fileUtil() { return new FileUtilImpl(); }

    @Bean
    public XmlParser xmlParser() { return new XmlParserImpl(); }

    @Bean
    public ValidatorUtil validatorUtil() { return new ValidatorUtilImpl(); }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
