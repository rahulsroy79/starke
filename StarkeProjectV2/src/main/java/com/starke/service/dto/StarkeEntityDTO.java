package com.starke.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the StarkeEntity entity.
 */
public class StarkeEntityDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer entityid;

    @NotNull
    private String entityname;

    @NotNull
    private Integer entitytypeid;

    @NotNull
    private Integer istopentity;

    private Integer parententityid;

    @NotNull
    private Integer allowrating;

    @NotNull
    private Integer allowreview;

    @NotNull
    private LocalDate entitycreationdate;

    private String entitybriefdescription;

    @Lob
    private byte[] entityimage;
    private String entityimageContentType;

    private Long entityPageDetailsId;

    private String entityPageDetailsPagedetailid;

    private Long entityDetailsId;

    private String entityDetailsEntityid;

    private Long parentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getEntityid() {
        return entityid;
    }

    public void setEntityid(Integer entityid) {
        this.entityid = entityid;
    }

    public String getEntityname() {
        return entityname;
    }

    public void setEntityname(String entityname) {
        this.entityname = entityname;
    }

    public Integer getEntitytypeid() {
        return entitytypeid;
    }

    public void setEntitytypeid(Integer entitytypeid) {
        this.entitytypeid = entitytypeid;
    }

    public Integer getIstopentity() {
        return istopentity;
    }

    public void setIstopentity(Integer istopentity) {
        this.istopentity = istopentity;
    }

    public Integer getParententityid() {
        return parententityid;
    }

    public void setParententityid(Integer parententityid) {
        this.parententityid = parententityid;
    }

    public Integer getAllowrating() {
        return allowrating;
    }

    public void setAllowrating(Integer allowrating) {
        this.allowrating = allowrating;
    }

    public Integer getAllowreview() {
        return allowreview;
    }

    public void setAllowreview(Integer allowreview) {
        this.allowreview = allowreview;
    }

    public LocalDate getEntitycreationdate() {
        return entitycreationdate;
    }

    public void setEntitycreationdate(LocalDate entitycreationdate) {
        this.entitycreationdate = entitycreationdate;
    }

    public String getEntitybriefdescription() {
        return entitybriefdescription;
    }

    public void setEntitybriefdescription(String entitybriefdescription) {
        this.entitybriefdescription = entitybriefdescription;
    }

    public byte[] getEntityimage() {
        return entityimage;
    }

    public void setEntityimage(byte[] entityimage) {
        this.entityimage = entityimage;
    }

    public String getEntityimageContentType() {
        return entityimageContentType;
    }

    public void setEntityimageContentType(String entityimageContentType) {
        this.entityimageContentType = entityimageContentType;
    }

    public Long getEntityPageDetailsId() {
        return entityPageDetailsId;
    }

    public void setEntityPageDetailsId(Long entityPageDetailsId) {
        this.entityPageDetailsId = entityPageDetailsId;
    }

    public String getEntityPageDetailsPagedetailid() {
        return entityPageDetailsPagedetailid;
    }

    public void setEntityPageDetailsPagedetailid(String entityPageDetailsPagedetailid) {
        this.entityPageDetailsPagedetailid = entityPageDetailsPagedetailid;
    }

    public Long getEntityDetailsId() {
        return entityDetailsId;
    }

    public void setEntityDetailsId(Long entityDetailsId) {
        this.entityDetailsId = entityDetailsId;
    }

    public String getEntityDetailsEntityid() {
        return entityDetailsEntityid;
    }

    public void setEntityDetailsEntityid(String entityDetailsEntityid) {
        this.entityDetailsEntityid = entityDetailsEntityid;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long starkeEntityId) {
        this.parentId = starkeEntityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StarkeEntityDTO starkeEntityDTO = (StarkeEntityDTO) o;
        if (starkeEntityDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), starkeEntityDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "StarkeEntityDTO{" +
            "id=" + getId() +
            ", entityid=" + getEntityid() +
            ", entityname='" + getEntityname() + "'" +
            ", entitytypeid=" + getEntitytypeid() +
            ", istopentity=" + getIstopentity() +
            ", parententityid=" + getParententityid() +
            ", allowrating=" + getAllowrating() +
            ", allowreview=" + getAllowreview() +
            ", entitycreationdate='" + getEntitycreationdate() + "'" +
            ", entitybriefdescription='" + getEntitybriefdescription() + "'" +
            ", entityimage='" + getEntityimage() + "'" +
            ", entityPageDetails=" + getEntityPageDetailsId() +
            ", entityPageDetails='" + getEntityPageDetailsPagedetailid() + "'" +
            ", entityDetails=" + getEntityDetailsId() +
            ", entityDetails='" + getEntityDetailsEntityid() + "'" +
            ", parent=" + getParentId() +
            "}";
    }
}
