package com.inkwell.archives.dto.mappings;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserAuthoritiesDtoConfig {

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }


}
