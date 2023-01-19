package kms.bootcamp.footballturfmanagementservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HttpBase implements Serializable {
    private static final long serialVersionUID = 1L;

    private Header header;

    private OBPaging obPaging;


}
