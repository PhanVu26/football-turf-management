package kms.bootcamp.footballturfmanagementservice.repository;

import kms.bootcamp.footballturfmanagementservice.entity.TurfEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TurfRepository extends JpaRepository<TurfEntity, Long> {

    @Query(value = "SELECT * from turfs  where code = ?1 " +
            "AND name like %?2% ",
            nativeQuery = true)
    Page<TurfEntity> findAllByFilter(String code, String name, Pageable pageable);

    @Query(value = "SELECT t from TurfEntity t where t.code = ?1")
    TurfEntity findByCode(String code);

    @Query(value = "SELECT * from turfs where turf_type_id = ?1", nativeQuery = true)
    List<TurfEntity> findByTurfTypeId(Long turfTypeId);
}
