package com.starke.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A StarkeUserTypes.
 */
@Entity
@Table(name = "starke_user_types")
public class StarkeUserTypes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "usertypeid", nullable = false)
    private Integer usertypeid;

    @NotNull
    @Column(name = "typename", nullable = false)
    private String typename;

    @Column(name = "usertypedescription")
    private String usertypedescription;

    @OneToOne(mappedBy = "starkeUserTypes")
    @JsonIgnore
    private StarkeUsers starkeUser;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUsertypeid() {
        return usertypeid;
    }

    public StarkeUserTypes usertypeid(Integer usertypeid) {
        this.usertypeid = usertypeid;
        return this;
    }

    public void setUsertypeid(Integer usertypeid) {
        this.usertypeid = usertypeid;
    }

    public String getTypename() {
        return typename;
    }

    public StarkeUserTypes typename(String typename) {
        this.typename = typename;
        return this;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getUsertypedescription() {
        return usertypedescription;
    }

    public StarkeUserTypes usertypedescription(String usertypedescription) {
        this.usertypedescription = usertypedescription;
        return this;
    }

    public void setUsertypedescription(String usertypedescription) {
        this.usertypedescription = usertypedescription;
    }

    public StarkeUsers getStarkeUser() {
        return starkeUser;
    }

    public StarkeUserTypes starkeUser(StarkeUsers starkeUsers) {
        this.starkeUser = starkeUsers;
        return this;
    }

    public void setStarkeUser(StarkeUsers starkeUsers) {
        this.starkeUser = starkeUsers;
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
        StarkeUserTypes starkeUserTypes = (StarkeUserTypes) o;
        if (starkeUserTypes.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), starkeUserTypes.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "StarkeUserTypes{" +
            "id=" + getId() +
            ", usertypeid=" + getUsertypeid() +
            ", typename='" + getTypename() + "'" +
            ", usertypedescription='" + getUsertypedescription() + "'" +
            "}";
    }
}
