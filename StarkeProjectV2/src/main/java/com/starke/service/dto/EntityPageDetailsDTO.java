package com.starke.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the EntityPageDetails entity.
 */
public class EntityPageDetailsDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer pagedetailid;

    @NotNull
    private String pagetypename;

    private Long entityPageFormatsId;

    private String entityPageFormatsPageformatid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPagedetailid() {
        return pagedetailid;
    }

    public void setPagedetailid(Integer pagedetailid) {
        this.pagedetailid = pagedetailid;
    }

    public String getPagetypename() {
        return pagetypename;
    }

    public void setPagetypename(String pagetypename) {
        this.pagetypename = pagetypename;
    }

    public Long getEntityPageFormatsId() {
        return entityPageFormatsId;
    }

    public void setEntityPageFormatsId(Long entityPageFormatsId) {
        this.entityPageFormatsId = entityPageFormatsId;
    }

    public String getEntityPageFormatsPageformatid() {
        return entityPageFormatsPageformatid;
    }

    public void setEntityPageFormatsPageformatid(String entityPageFormatsPageformatid) {
        this.entityPageFormatsPageformatid = entityPageFormatsPageformatid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EntityPageDetailsDTO entityPageDetailsDTO = (EntityPageDetailsDTO) o;
        if (entityPageDetailsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), entityPageDetailsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EntityPageDetailsDTO{" +
            "id=" + getId() +
            ", pagedetailid=" + getPagedetailid() +
            ", pagetypename='" + getPagetypename() + "'" +
            ", entityPageFormats=" + getEntityPageFormatsId() +
            ", entityPageFormats='" + getEntityPageFormatsPageformatid() + "'" +
            "}";
    }
}
