package no.frodo.dd.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DDConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
