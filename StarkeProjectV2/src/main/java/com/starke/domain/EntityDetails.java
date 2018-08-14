package com.starke.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A EntityDetails.
 */
@Entity
@Table(name = "entity_details")
public class EntityDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "entityid", nullable = false)
    private Integer entityid;

    @Column(name = "entitydescription")
    private String entitydescription;

    @Column(name = "entitycontent")
    private String entitycontent;

    @Column(name = "entitycontent_2")
    private String entitycontent2;

    @Column(name = "entitycontent_3")
    private String entitycontent3;

    @OneToOne(mappedBy = "entityDetails")
    @JsonIgnore
    private StarkeEntity starkeEntity;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getEntityid() {
        return entityid;
    }

    public EntityDetails entityid(Integer entityid) {
        this.entityid = entityid;
        return this;
    }

    public void setEntityid(Integer entityid) {
        this.entityid = entityid;
    }

    public String getEntitydescription() {
        return entitydescription;
    }

    public EntityDetails entitydescription(String entitydescription) {
        this.entitydescription = entitydescription;
        return this;
    }

    public void setEntitydescription(String entitydescription) {
        this.entitydescription = entitydescription;
    }

    public String getEntitycontent() {
        return entitycontent;
    }

    public EntityDetails entitycontent(String entitycontent) {
        this.entitycontent = entitycontent;
        return this;
    }

    public void setEntitycontent(String entitycontent) {
        this.entitycontent = entitycontent;
    }

    public String getEntitycontent2() {
        return entitycontent2;
    }

    public EntityDetails entitycontent2(String entitycontent2) {
        this.entitycontent2 = entitycontent2;
        return this;
    }

    public void setEntitycontent2(String entitycontent2) {
        this.entitycontent2 = entitycontent2;
    }

    public String getEntitycontent3() {
        return entitycontent3;
    }

    public EntityDetails entitycontent3(String entitycontent3) {
        this.entitycontent3 = entitycontent3;
        return this;
    }

    public void setEntitycontent3(String entitycontent3) {
        this.entitycontent3 = entitycontent3;
    }

    public StarkeEntity getStarkeEntity() {
        return starkeEntity;
    }

    public EntityDetails starkeEntity(StarkeEntity starkeEntity) {
        this.starkeEntity = starkeEntity;
        return this;
    }

    public void setStarkeEntity(StarkeEntity starkeEntity) {
        this.starkeEntity = starkeEntity;
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
        EntityDetails entityDetails = (EntityDetails) o;
        if (entityDetails.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), entityDetails.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EntityDetails{" +
            "id=" + getId() +
            ", entityid=" + getEntityid() +
            ", entitydescription='" + getEntitydescription() + "'" +
            ", entitycontent='" + getEntitycontent() + "'" +
            ", entitycontent2='" + getEntitycontent2() + "'" +
            ", entitycontent3='" + getEntitycontent3() + "'" +
            "}";
    }
}
