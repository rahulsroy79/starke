package com.starke.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the EntityDetails entity.
 */
public class EntityDetailsDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer entityid;

    private String entitydescription;

    private String entitycontent;

    private String entitycontent2;

    private String entitycontent3;

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

    public String getEntitydescription() {
        return entitydescription;
    }

    public void setEntitydescription(String entitydescription) {
        this.entitydescription = entitydescription;
    }

    public String getEntitycontent() {
        return entitycontent;
    }

    public void setEntitycontent(String entitycontent) {
        this.entitycontent = entitycontent;
    }

    public String getEntitycontent2() {
        return entitycontent2;
    }

    public void setEntitycontent2(String entitycontent2) {
        this.entitycontent2 = entitycontent2;
    }

    public String getEntitycontent3() {
        return entitycontent3;
    }

    public void setEntitycontent3(String entitycontent3) {
        this.entitycontent3 = entitycontent3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EntityDetailsDTO entityDetailsDTO = (EntityDetailsDTO) o;
        if (entityDetailsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), entityDetailsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EntityDetailsDTO{" +
            "id=" + getId() +
            ", entityid=" + getEntityid() +
            ", entitydescription='" + getEntitydescription() + "'" +
            ", entitycontent='" + getEntitycontent() + "'" +
            ", entitycontent2='" + getEntitycontent2() + "'" +
            ", entitycontent3='" + getEntitycontent3() + "'" +
            "}";
    }
}
