package kms.bootcamp.footballturfmanagementservice.dto;

import lombok.Data;

@Data
public class TurfTypeDetail extends BaseDetail{
    private Long id;

    private String code;

    private String name;

    private String description;
}
