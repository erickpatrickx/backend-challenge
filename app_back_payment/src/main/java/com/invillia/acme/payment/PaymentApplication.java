package com.invillia.acme.payment;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(value="com.invillia.acme.payment")
public class PaymentApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
	
	@Bean
    public ObjectMapper objectMapper() {
        
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder(); 
        
        builder.serializationInclusion(Include.NON_NULL);
        builder.featuresToDisable(
        		SerializationFeature.FAIL_ON_EMPTY_BEANS,
                DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES,
                DeserializationFeature.FAIL_ON_INVALID_SUBTYPE,
                SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        
        builder.featuresToEnable(
        		DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY,
        		DeserializationFeature.USE_BIG_INTEGER_FOR_INTS,
        		JsonParser.Feature.ALLOW_SINGLE_QUOTES,
        		JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS,
        		JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES);
        builder.modulesToInstall(new JavaTimeModule());
        return builder.build();
    }
	
}
