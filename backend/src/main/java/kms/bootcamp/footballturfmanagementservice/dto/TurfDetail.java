package kms.bootcamp.footballturfmanagementservice.dto;

import lombok.Data;

@Data
public class TurfDetail extends BaseDetail{
    private Long id;

    private String turfTypeId;

    private String code;

    private String name;

    private String description;

    private TurfTypeDetail turfTypeDetail;
}
