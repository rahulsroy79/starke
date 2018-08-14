package com.starke.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the StarkeUsers entity.
 */
public class StarkeUsersDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer userid;

    @NotNull
    private String username;

    @NotNull
    private String useremail;

    private Integer isactive;

    @NotNull
    private Integer usertypeid;

    @NotNull
    private LocalDate activesince;

    private Integer registrationid;

    private Long starkeUserTypesId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public Integer getIsactive() {
        return isactive;
    }

    public void setIsactive(Integer isactive) {
        this.isactive = isactive;
    }

    public Integer getUsertypeid() {
        return usertypeid;
    }

    public void setUsertypeid(Integer usertypeid) {
        this.usertypeid = usertypeid;
    }

    public LocalDate getActivesince() {
        return activesince;
    }

    public void setActivesince(LocalDate activesince) {
        this.activesince = activesince;
    }

    public Integer getRegistrationid() {
        return registrationid;
    }

    public void setRegistrationid(Integer registrationid) {
        this.registrationid = registrationid;
    }

    public Long getStarkeUserTypesId() {
        return starkeUserTypesId;
    }

    public void setStarkeUserTypesId(Long starkeUserTypesId) {
        this.starkeUserTypesId = starkeUserTypesId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StarkeUsersDTO starkeUsersDTO = (StarkeUsersDTO) o;
        if (starkeUsersDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), starkeUsersDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "StarkeUsersDTO{" +
            "id=" + getId() +
            ", userid=" + getUserid() +
            ", username='" + getUsername() + "'" +
            ", useremail='" + getUseremail() + "'" +
            ", isactive=" + getIsactive() +
            ", usertypeid=" + getUsertypeid() +
            ", activesince='" + getActivesince() + "'" +
            ", registrationid=" + getRegistrationid() +
            ", starkeUserTypes=" + getStarkeUserTypesId() +
            "}";
    }
}
