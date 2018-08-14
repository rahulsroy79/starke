package com.starke.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the StarkeUserTypes entity.
 */
public class StarkeUserTypesDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer usertypeid;

    @NotNull
    private String typename;

    private String usertypedescription;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUsertypeid() {
        return usertypeid;
    }

    public void setUsertypeid(Integer usertypeid) {
        this.usertypeid = usertypeid;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getUsertypedescription() {
        return usertypedescription;
    }

    public void setUsertypedescription(String usertypedescription) {
        this.usertypedescription = usertypedescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StarkeUserTypesDTO starkeUserTypesDTO = (StarkeUserTypesDTO) o;
        if (starkeUserTypesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), starkeUserTypesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "StarkeUserTypesDTO{" +
            "id=" + getId() +
            ", usertypeid=" + getUsertypeid() +
            ", typename='" + getTypename() + "'" +
            ", usertypedescription='" + getUsertypedescription() + "'" +
            "}";
    }
}
