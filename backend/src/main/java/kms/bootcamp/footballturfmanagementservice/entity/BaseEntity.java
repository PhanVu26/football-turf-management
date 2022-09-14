package kms.bootcamp.footballturfmanagementservice.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@MappedSuperclass
public class BaseEntity {

    @Column(name = "updatedBy", length = 45)
    private String updatedBy;

    @Column(name = "updatedDatetime", length = 45)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDatetime;

    @Column(name = "createdBy", length = 45)
    private String createdBy;

    @Column(name = "createdDatetime", length = 45)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDatetime;

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedDatetime() {
        return updatedDatetime;
    }

    public void setUpdatedDatetime(Date updatedDatetime) {
        this.updatedDatetime = updatedDatetime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDatetime() {
        return createdDatetime;
    }

    public void setCreatedDatetime(Date createdDatetime) {
        this.createdDatetime = createdDatetime;
    }
}
