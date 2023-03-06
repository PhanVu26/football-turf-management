package kms.bootcamp.footballturfmanagementservice.service.impl;

import kms.bootcamp.footballturfmanagementservice.constant.AppConstant;
import kms.bootcamp.footballturfmanagementservice.dto.TurfTypeDetail;
import kms.bootcamp.footballturfmanagementservice.dto.TurfTypeRequest;
import kms.bootcamp.footballturfmanagementservice.dto.TurfTypeResponse;
import kms.bootcamp.footballturfmanagementservice.entity.TurfEntity;
import kms.bootcamp.footballturfmanagementservice.entity.TurfTypeEntity;
import kms.bootcamp.footballturfmanagementservice.repository.TurfRepository;
import kms.bootcamp.footballturfmanagementservice.repository.TurfTypeRepository;
import kms.bootcamp.footballturfmanagementservice.service.BaseService;
import kms.bootcamp.footballturfmanagementservice.service.TurfTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TurfTypeServiceImpl extends BaseService implements TurfTypeService {

    @Autowired
    TurfTypeRepository turfTypeRepository;

    @Autowired
    TurfRepository turfRepository;

    @Override
    public TurfTypeEntity findById(Long id) {
        Optional<TurfTypeEntity> entity = turfTypeRepository.findById(id);
        return entity.orElseGet(entity::get);
    }

    @Override
    public List<TurfTypeEntity> findAll() {
        List<TurfTypeEntity> turfTypeEntityList = turfTypeRepository.findAll();
        if(!CollectionUtils.isEmpty(turfTypeEntityList)) {
            return turfTypeEntityList;
        }
        return new ArrayList<>();
    }

    @Override
    public TurfTypeResponse retrieveTurfTypeList(TurfTypeRequest request) {
        TurfTypeResponse response = new TurfTypeResponse();
        List<TurfTypeEntity> turfTypeEntity = turfTypeRepository.findAll();
        if (CollectionUtils.isEmpty(turfTypeEntity)) {
            mapFailResponseValue(response, "Record is not found");
            return response;
        }
        List<TurfTypeDetail> turfTypeDetailList = mapEntitiesToDetails(turfTypeEntity);
        response.setTurfTypeDetailList(turfTypeDetailList);
        mapSuccessResponseValue(response);
        return response;
    }

    @Override
    public TurfTypeResponse performCreate(TurfTypeRequest request) {
        TurfTypeResponse response = new TurfTypeResponse();
        TurfTypeDetail detail = request.getTurfTypeDetail();
        if (Objects.isNull(detail)) {
            mapFailResponseValue(response, "turfTypeDetail cannot be empty");
            return response;
        }
        if (isDuplicateTurfTypeCode(detail.getCode())) {
            mapFailResponseValue(response, "Duplicate code!");
            return response;
        }
        TurfTypeEntity entity = new TurfTypeEntity();
        BeanUtils.copyProperties(detail, entity);
        mapEntityAuditCreating(request, entity);
        turfTypeRepository.save(entity);
        mapSuccessResponseValue(response);
        return response;
    }

    @Override
    public TurfTypeResponse performUpdate(TurfTypeRequest request) {
        TurfTypeResponse response = new TurfTypeResponse();
        TurfTypeDetail detail = request.getTurfTypeDetail();
        if (Objects.isNull(detail)) {
            mapFailResponseValue(response, "turfTypeDetail cannot be empty");
            return response;
        }
        Optional<TurfTypeEntity> entity = turfTypeRepository.findById(detail.getId());
        if (entity.isEmpty()) {
            mapFailResponseValue(response, "Record not found - id: " + detail.getId());
            return response;
        }
        if (!entity.get().getCode().equalsIgnoreCase(detail.getCode())) {
            mapFailResponseValue(response, "code is not allowed to change!" );
            return response;
        }
        BeanUtils.copyProperties(detail, entity.get(), AppConstant.IGNORE_MAPPING);
        mapEntityAuditUpdating(request, entity.get());
        turfTypeRepository.save(entity.get());
        mapSuccessResponseValue(response);
        return response;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TurfTypeResponse performDelete(TurfTypeRequest request) {
        TurfTypeResponse response = new TurfTypeResponse();
        if (Objects.isNull(request.getId())) {
            mapFailResponseValue(response, "id cannot be empty");
            return response;
        }
        Optional<TurfTypeEntity> entity = turfTypeRepository.findById(request.getId());
        if (entity.isEmpty()) {
            mapFailResponseValue(response, "Record not found - id: " + request.getId());
            return response;
        }
        List<TurfEntity> turfEntityList = turfRepository.findByTurfTypeId(request.getId());
        if (!CollectionUtils.isEmpty(turfEntityList)) {
            turfRepository.deleteAll(turfEntityList);
        }
        turfTypeRepository.delete(entity.get());
        mapSuccessResponseValue(response);
        return response;
    }

    @Override
    public TurfTypeEntity findByCode(String code) {
        return turfTypeRepository.findByCode(code);
    }

    private List<TurfTypeDetail> mapEntitiesToDetails(List<TurfTypeEntity> entities) {
        return Optional.ofNullable(entities).orElse(new ArrayList<>())
                .stream()
                .map(this::mapEntityToDetail)
                .collect(Collectors.toList());
    }

    private TurfTypeDetail mapEntityToDetail(TurfTypeEntity entity) {
        TurfTypeDetail detail = new TurfTypeDetail();
        BeanUtils.copyProperties(entity, detail);
        return detail;
    }

    private boolean isDuplicateTurfTypeCode(String code) {
        TurfTypeEntity entity = turfTypeRepository.findByCode(code);
        return Objects.nonNull(entity);
    }
}
