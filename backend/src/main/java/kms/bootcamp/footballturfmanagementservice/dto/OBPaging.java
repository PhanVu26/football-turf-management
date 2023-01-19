package kms.bootcamp.footballturfmanagementservice.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class OBPaging implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer startIndex;

    private Integer maxPerPage;

    private Integer totalRecords;

    private Integer totalPages;
}
