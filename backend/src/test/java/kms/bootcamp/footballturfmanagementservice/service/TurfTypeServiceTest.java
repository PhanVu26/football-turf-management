package kms.bootcamp.footballturfmanagementservice.service;

import kms.bootcamp.footballturfmanagementservice.dto.TurfTypeRequest;
import kms.bootcamp.footballturfmanagementservice.dto.TurfTypeResponse;
import kms.bootcamp.footballturfmanagementservice.entity.TurfTypeEntity;
import kms.bootcamp.footballturfmanagementservice.repository.TurfRepository;
import kms.bootcamp.footballturfmanagementservice.repository.TurfTypeRepository;
import kms.bootcamp.footballturfmanagementservice.service.impl.TurfTypeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
public class TurfTypeServiceTest {

    @TestConfiguration
    static class TurfTypeServiceTestConfiguration {
        @Bean
        TurfTypeService turfTypeService() {
            return new TurfTypeServiceImpl();
        }
    }

    @Autowired
    private TurfTypeService turfTypeService;

    @MockBean
    private TurfTypeRepository turfTypeRepository;

    @MockBean
    private TurfRepository turfRepository;

    @Test
    public void whenValidCode_thenTurfTypeShouldBeFound() {

        // 1. Mock data
        TurfTypeEntity turfTypeEntity = new TurfTypeEntity();
        turfTypeEntity.setCode("T2");
        Mockito.when(turfTypeRepository.findByCode(turfTypeEntity.getCode())).thenReturn(turfTypeEntity);

        // 2. Call service method
        String code = "T2";
        TurfTypeEntity turfType = turfTypeService.findByCode(code);

        // 3. Assert the result
        assertThat(turfType.getCode()).isEqualTo(code);

        // 4. ensure repository is called
        verify(turfTypeRepository).findByCode(code);

    }

    @Test
    public void whenValidRequest_thenTurfTypeListShouldBeFound() {

        // 1. Mock data
        List<TurfTypeEntity> turfTypeEntityList = new ArrayList<>();
        for (int i = 0 ; i < 5; i++) {
            TurfTypeEntity turfTypeEntity = new TurfTypeEntity();
            turfTypeEntity.setId((long)i);
            turfTypeEntity.setCode("A" + i);
            turfTypeEntity.setName("Turf type " + i);
            turfTypeEntityList.add(turfTypeEntity);
        }

        Mockito.when(turfTypeRepository.findAll()).thenReturn(turfTypeEntityList);

        // 2. call service
        TurfTypeRequest turfTypeRequest = new TurfTypeRequest();
        TurfTypeResponse turfTypeResponse = turfTypeService.retrieveTurfTypeList(turfTypeRequest);

        // 3. Assert the result
        assertThat(turfTypeResponse.getHeader().getSuccess()).isTrue();
        assertThat(turfTypeResponse.getTurfTypeDetailList()).hasSameSizeAs(turfTypeEntityList);

        // 4. ensure repository is called
        verify(turfTypeRepository).findAll();
    }

    @Test
    public void whenInvalidRequest_shouldThrowException() {

        Mockito.when(turfTypeRepository.findAll()).thenReturn(new ArrayList<>());

        TurfTypeRequest turfTypeRequest = new TurfTypeRequest();
        TurfTypeResponse turfTypeResponse = turfTypeService.retrieveTurfTypeList(turfTypeRequest);

        assertThat(turfTypeResponse.getHeader().getSuccess()).isFalse();

        // ensure repository is called
        verify(turfTypeRepository).findAll();
    }

}
