package kms.bootcamp.footballturfmanagementservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class TurfResponse extends HttpBase {

    private Long id;

    private TurfDetail turfDetail;

    private List<TurfDetail> turfDetailList;
}
