package io.github.dev_alan87.sales.api.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class TokenDTO {

    private String username;
    private String token;
    
}
