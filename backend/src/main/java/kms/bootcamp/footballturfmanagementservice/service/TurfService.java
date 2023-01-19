package kms.bootcamp.footballturfmanagementservice.service;

import kms.bootcamp.footballturfmanagementservice.dto.TurfRequest;
import kms.bootcamp.footballturfmanagementservice.dto.TurfResponse;
import kms.bootcamp.footballturfmanagementservice.entity.TurfEntity;

import java.util.List;

public interface TurfService {

    TurfEntity findById(Long id);

    List<TurfEntity> findAll();

    TurfResponse retrieveTurfList(TurfRequest request);

    TurfResponse performCreate(TurfRequest request);

    TurfResponse performUpdate(TurfRequest request);

    TurfResponse performDelete(TurfRequest request);
}
