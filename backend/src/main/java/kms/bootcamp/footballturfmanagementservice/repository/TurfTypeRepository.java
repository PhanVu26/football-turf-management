package kms.bootcamp.footballturfmanagementservice.repository;

import kms.bootcamp.footballturfmanagementservice.entity.TurfTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TurfTypeRepository extends JpaRepository<TurfTypeEntity, Long> {

    @Query(value = "SELECT tt from TurfTypeEntity tt where tt.code = ?1")
    TurfTypeEntity findByCode(String code);
}
