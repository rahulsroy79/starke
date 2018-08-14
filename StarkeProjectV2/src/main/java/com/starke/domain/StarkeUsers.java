package com.starke.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A StarkeUsers.
 */
@Entity
@Table(name = "starke_users")
public class StarkeUsers implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "userid", nullable = false)
    private Integer userid;

    @NotNull
    @Column(name = "username", nullable = false)
    private String username;

    @NotNull
    @Column(name = "useremail", nullable = false)
    private String useremail;

    @Column(name = "isactive")
    private Integer isactive;

    @NotNull
    @Column(name = "usertypeid", nullable = false)
    private Integer usertypeid;

    @NotNull
    @Column(name = "activesince", nullable = false)
    private LocalDate activesince;

    @Column(name = "registrationid")
    private Integer registrationid;

    @OneToOne
    @JoinColumn(unique = true)
    private StarkeUserTypes starkeUserTypes;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public StarkeUsers userid(Integer userid) {
        this.userid = userid;
        return this;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public StarkeUsers username(String username) {
        this.username = username;
        return this;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUseremail() {
        return useremail;
    }

    public StarkeUsers useremail(String useremail) {
        this.useremail = useremail;
        return this;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public Integer getIsactive() {
        return isactive;
    }

    public StarkeUsers isactive(Integer isactive) {
        this.isactive = isactive;
        return this;
    }

    public void setIsactive(Integer isactive) {
        this.isactive = isactive;
    }

    public Integer getUsertypeid() {
        return usertypeid;
    }

    public StarkeUsers usertypeid(Integer usertypeid) {
        this.usertypeid = usertypeid;
        return this;
    }

    public void setUsertypeid(Integer usertypeid) {
        this.usertypeid = usertypeid;
    }

    public LocalDate getActivesince() {
        return activesince;
    }

    public StarkeUsers activesince(LocalDate activesince) {
        this.activesince = activesince;
        return this;
    }

    public void setActivesince(LocalDate activesince) {
        this.activesince = activesince;
    }

    public Integer getRegistrationid() {
        return registrationid;
    }

    public StarkeUsers registrationid(Integer registrationid) {
        this.registrationid = registrationid;
        return this;
    }

    public void setRegistrationid(Integer registrationid) {
        this.registrationid = registrationid;
    }

    public StarkeUserTypes getStarkeUserTypes() {
        return starkeUserTypes;
    }

    public StarkeUsers starkeUserTypes(StarkeUserTypes starkeUserTypes) {
        this.starkeUserTypes = starkeUserTypes;
        return this;
    }

    public void setStarkeUserTypes(StarkeUserTypes starkeUserTypes) {
        this.starkeUserTypes = starkeUserTypes;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StarkeUsers starkeUsers = (StarkeUsers) o;
        if (starkeUsers.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), starkeUsers.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "StarkeUsers{" +
            "id=" + getId() +
            ", userid=" + getUserid() +
            ", username='" + getUsername() + "'" +
            ", useremail='" + getUseremail() + "'" +
            ", isactive=" + getIsactive() +
            ", usertypeid=" + getUsertypeid() +
            ", activesince='" + getActivesince() + "'" +
            ", registrationid=" + getRegistrationid() +
            "}";
    }
}
