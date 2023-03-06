package kms.bootcamp.footballturfmanagementservice.service;

import kms.bootcamp.footballturfmanagementservice.entity.TurfEntity;
import kms.bootcamp.footballturfmanagementservice.repository.TurfRepository;
import kms.bootcamp.footballturfmanagementservice.service.impl.TurfServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TurfServiceTest {

    @InjectMocks
    private TurfServiceImpl turfService;

    @Mock
    private TurfRepository turfRepository;

    @Test
    void getAllTest() {
        List<TurfEntity> mockTurfs = new ArrayList<>();
        for (int i = 0 ; i < 5; i++) {
            TurfEntity turfEntity = new TurfEntity();
            turfEntity.setId((long)i);
            turfEntity.setCode("A" + i);
            turfEntity.setName("Turf " + i);
            mockTurfs.add(turfEntity);
        }

        when(turfRepository.findAll()).thenReturn(mockTurfs);

        List<TurfEntity> turfEntityList = turfService.findAll();

        assertThat(turfEntityList.size()).hasSameClassAs(mockTurfs.size());

        verify(turfRepository).findAll();
    }
}
