package kms.bootcamp.footballturfmanagementservice.dto;

import lombok.Data;

@Data
public class UserDetail extends BaseDetail{

    private Long id;

    private String username;

    private String password;

}
