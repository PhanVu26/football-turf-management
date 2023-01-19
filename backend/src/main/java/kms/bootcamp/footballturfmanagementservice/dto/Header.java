package kms.bootcamp.footballturfmanagementservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Header {
    private String statusCode;

    private Boolean success;

    private String statusMessage;

    private String userId;

    private String userName;
}
