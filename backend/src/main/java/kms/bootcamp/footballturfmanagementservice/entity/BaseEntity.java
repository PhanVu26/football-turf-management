package kms.bootcamp.footballturfmanagementservice.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@MappedSuperclass
@Data
public class BaseEntity {

    @Column(name = "updated_by", length = 45)
    private String updatedBy;

    @Column(name = "updated_datetime", length = 45)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDatetime;

    @Column(name = "created_by", length = 45)
    private String createdBy;

    @Column(name = "created_datetime", length = 45)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDatetime;

}
