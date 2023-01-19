package kms.bootcamp.footballturfmanagementservice.dto;

import lombok.Data;

@Data
public class SearchCriteria {

    private String key;
    private String operation;
    private Object value;
}
