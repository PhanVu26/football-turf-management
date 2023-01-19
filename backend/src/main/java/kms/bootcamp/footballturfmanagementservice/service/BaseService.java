package kms.bootcamp.footballturfmanagementservice.service;

import kms.bootcamp.footballturfmanagementservice.constant.StatusCodeConstant;
import kms.bootcamp.footballturfmanagementservice.dto.Header;
import kms.bootcamp.footballturfmanagementservice.dto.HttpBase;
import kms.bootcamp.footballturfmanagementservice.entity.BaseEntity;

import java.util.Date;
import java.util.Objects;

public abstract class BaseService {

    protected BaseService(){}

    protected static void mapSuccessResponseValue(HttpBase response) {
        Header header = new Header();
        header.setSuccess(true);
        header.setStatusCode(StatusCodeConstant.SUCCESS);
        response.setHeader(header);
    }

    protected static void mapFailResponseValue(HttpBase response, String message) {
        Header header = new Header();
        header.setSuccess(false);
        header.setStatusCode(StatusCodeConstant.FAIL);
        header.setStatusMessage(message);
        response.setHeader(header);
    }

    protected static void mapEntityAuditCreating(HttpBase request, BaseEntity entity) {
        if (Objects.nonNull(request.getHeader())) {
            entity.setCreatedBy(request.getHeader().getUserName());
            entity.setCreatedDatetime(new Date());
        }
    }
    protected static void mapEntityAuditUpdating(HttpBase request, BaseEntity entity) {
        if (Objects.nonNull(request.getHeader())) {
            entity.setUpdatedBy(request.getHeader().getUserName());
            entity.setUpdatedDatetime(new Date());
        }
    }
}
