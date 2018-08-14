package com.starke.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the EntityPageFormats entity.
 */
public class EntityPageFormatsDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer pageformatid;

    private String pageformatname;

    private String mainimagepath;

    private String image2path;

    private String image3path;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPageformatid() {
        return pageformatid;
    }

    public void setPageformatid(Integer pageformatid) {
        this.pageformatid = pageformatid;
    }

    public String getPageformatname() {
        return pageformatname;
    }

    public void setPageformatname(String pageformatname) {
        this.pageformatname = pageformatname;
    }

    public String getMainimagepath() {
        return mainimagepath;
    }

    public void setMainimagepath(String mainimagepath) {
        this.mainimagepath = mainimagepath;
    }

    public String getImage2path() {
        return image2path;
    }

    public void setImage2path(String image2path) {
        this.image2path = image2path;
    }

    public String getImage3path() {
        return image3path;
    }

    public void setImage3path(String image3path) {
        this.image3path = image3path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EntityPageFormatsDTO entityPageFormatsDTO = (EntityPageFormatsDTO) o;
        if (entityPageFormatsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), entityPageFormatsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EntityPageFormatsDTO{" +
            "id=" + getId() +
            ", pageformatid=" + getPageformatid() +
            ", pageformatname='" + getPageformatname() + "'" +
            ", mainimagepath='" + getMainimagepath() + "'" +
            ", image2path='" + getImage2path() + "'" +
            ", image3path='" + getImage3path() + "'" +
            "}";
    }
}
