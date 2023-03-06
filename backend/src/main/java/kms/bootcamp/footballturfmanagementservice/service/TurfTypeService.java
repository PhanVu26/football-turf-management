package kms.bootcamp.footballturfmanagementservice.service;

import kms.bootcamp.footballturfmanagementservice.dto.TurfTypeRequest;
import kms.bootcamp.footballturfmanagementservice.dto.TurfTypeResponse;
import kms.bootcamp.footballturfmanagementservice.entity.TurfTypeEntity;

import java.util.List;

public interface TurfTypeService {

    TurfTypeEntity findById(Long id);

    List<TurfTypeEntity> findAll();

    TurfTypeResponse retrieveTurfTypeList(TurfTypeRequest request);

    TurfTypeResponse performCreate(TurfTypeRequest request);

    TurfTypeResponse performUpdate(TurfTypeRequest request);

    TurfTypeResponse performDelete(TurfTypeRequest request);

    TurfTypeEntity findByCode(String code);
}
