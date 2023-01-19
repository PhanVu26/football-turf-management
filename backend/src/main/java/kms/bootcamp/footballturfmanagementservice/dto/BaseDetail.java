package kms.bootcamp.footballturfmanagementservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    private String updatedBy;

    private Date updatedDatetime;

    private String createdBy;

    private Date createdDatetime;

}
