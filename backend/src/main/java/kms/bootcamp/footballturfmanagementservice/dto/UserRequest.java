package kms.bootcamp.footballturfmanagementservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserRequest extends HttpBase{

    private Long id;

    private UserDetail userDetail;

    private List<UserDetail> userDetailList;
}
