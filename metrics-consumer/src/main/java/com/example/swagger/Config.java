package com.example.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Metrics consumer Api",
                description = "Metrics consumer", version = "1.0.0",
                contact = @Contact(
                        name = "Kozlova Julia"
                )
        )
)
public class Config {

}