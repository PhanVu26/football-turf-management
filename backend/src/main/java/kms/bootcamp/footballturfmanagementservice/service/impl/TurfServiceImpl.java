package kms.bootcamp.footballturfmanagementservice.service.impl;

import kms.bootcamp.footballturfmanagementservice.constant.AppConstant;
import kms.bootcamp.footballturfmanagementservice.dto.OBPaging;
import kms.bootcamp.footballturfmanagementservice.dto.TurfDetail;
import kms.bootcamp.footballturfmanagementservice.dto.TurfRequest;
import kms.bootcamp.footballturfmanagementservice.dto.TurfResponse;
import kms.bootcamp.footballturfmanagementservice.dto.TurfTypeDetail;
import kms.bootcamp.footballturfmanagementservice.entity.TurfEntity;
import kms.bootcamp.footballturfmanagementservice.repository.TurfRepository;
import kms.bootcamp.footballturfmanagementservice.service.BaseService;
import kms.bootcamp.footballturfmanagementservice.service.TurfService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
public class TurfServiceImpl extends BaseService implements TurfService {

    @Autowired
    TurfRepository turfRepository;

    @Override
    public TurfEntity findById(Long id) {
        Optional<TurfEntity> entity = turfRepository.findById(id);
        return entity.orElseGet(entity::get);
    }

    @Override
    public TurfResponse retrieveTurfList(TurfRequest request) {
        TurfResponse response = new TurfResponse();
        TurfDetail detail = request.getTurfDetail();
        if (Objects.isNull(detail)) {
            log.error("turfDetail can not be null");
            mapFailResponseValue(response, "turfDetail can not be null");
            return response;
        }

        OBPaging obPaging = request.getObPaging();
        Pageable pageable = PageRequest.of(obPaging.getStartIndex(), obPaging.getMaxPerPage());
        Page<TurfEntity> turfEntityList = turfRepository.findAll(pageable);

        List<TurfDetail> turfDetailList = mapEntitiesToDetails(turfEntityList.toList());
        response.setTurfDetailList(turfDetailList);

        obPaging.setTotalRecords((int) turfEntityList.getTotalElements());
        obPaging.setTotalPages(turfEntityList.getTotalPages());
        response.setObPaging(request.getObPaging());
        mapSuccessResponseValue(response);
        return response;
    }

    @Override
    public TurfResponse performCreate(TurfRequest request) {
        TurfResponse response = new TurfResponse();
        TurfDetail detail = request.getTurfDetail();
        if (Objects.isNull(detail)) {
            mapFailResponseValue(response, "turfDetail cannot be empty");
            return response;
        }
        if (isDuplicateTurfCode(detail.getCode())) {
            mapFailResponseValue(response, "Duplicate code!");
            return response;
        }
        TurfEntity entity = new TurfEntity();
        BeanUtils.copyProperties(detail, entity);
        mapEntityAuditCreating(request, entity);
        turfRepository.save(entity);
        mapSuccessResponseValue(response);
        return response;
    }

    @Override
    public TurfResponse performUpdate(TurfRequest request) {
        TurfResponse response = new TurfResponse();
        TurfDetail detail = request.getTurfDetail();
        if (Objects.isNull(detail)) {
            mapFailResponseValue(response, "turfDetail cannot be empty");
            return response;
        }
        Optional<TurfEntity> entity = turfRepository.findById(detail.getId());
        if (entity.isEmpty()) {
            mapFailResponseValue(response, "Record not found");
            return response;
        }
        if (!entity.get().getCode().equalsIgnoreCase(detail.getCode())) {
            mapFailResponseValue(response, "code is not allowed to change!");
            return response;
        }
        BeanUtils.copyProperties(detail, entity.get(), AppConstant.IGNORE_MAPPING);
        mapEntityAuditUpdating(request, entity.get());
        turfRepository.save(entity.get());
        mapSuccessResponseValue(response);
        return response;
    }

    @Override
    public TurfResponse performDelete(TurfRequest request) {
        TurfResponse response = new TurfResponse();
        if (Objects.isNull(request.getId())) {
            mapFailResponseValue(response, "id cannot be empty");
            return response;
        }
        Optional<TurfEntity> entity = turfRepository.findById(request.getId());
        if (entity.isEmpty()) {
            mapFailResponseValue(response, "Record not found");
            return response;
        }
        turfRepository.delete(entity.get());
        mapSuccessResponseValue(response);
        return response;
    }

    @Override
    public List<TurfEntity> findAll() {
        List<TurfEntity> turfEntityList = turfRepository.findAll();
        if(!CollectionUtils.isEmpty(turfEntityList)) {
            return turfEntityList;
        }
        return new ArrayList<>();
    }

    private List<TurfDetail> mapEntitiesToDetails(List<TurfEntity> entities) {
        return Optional.ofNullable(entities).orElse(new ArrayList<>())
                .stream()
                .map(this::mapEntityToDetail)
                .collect(Collectors.toList());
    }

    private TurfDetail mapEntityToDetail(TurfEntity entity) {
        TurfDetail detail = new TurfDetail();
        TurfTypeDetail turfTypeDetail = new TurfTypeDetail();
        BeanUtils.copyProperties(entity.getTurfTypeEntity(), turfTypeDetail);
        BeanUtils.copyProperties(entity, detail);
        detail.setTurfTypeDetail(turfTypeDetail);
        return detail;
    }

    private boolean isDuplicateTurfCode(String code) {
        TurfEntity entity = turfRepository.findByCode(code);
        return Objects.nonNull(entity);
    }
}
