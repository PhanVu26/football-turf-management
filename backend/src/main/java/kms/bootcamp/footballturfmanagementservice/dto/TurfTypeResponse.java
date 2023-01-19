package kms.bootcamp.footballturfmanagementservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class TurfTypeResponse extends HttpBase {

    private Long id;

    private TurfTypeDetail turfTypeDetail;

    private List<TurfTypeDetail> turfTypeDetailList;
}
