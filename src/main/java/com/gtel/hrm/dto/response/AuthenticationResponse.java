package com.gtel.hrm.dto.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class AuthenticationResponse {
    private boolean authenticated;


}
