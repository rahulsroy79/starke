package com.starke.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A EntityPageDetails.
 */
@Entity
@Table(name = "entity_page_details")
public class EntityPageDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "pagedetailid", nullable = false)
    private Integer pagedetailid;

    @NotNull
    @Column(name = "pagetypename", nullable = false)
    private String pagetypename;

    @OneToOne
    @JoinColumn(unique = true)
    private EntityPageFormats entityPageFormats;

    @OneToOne(mappedBy = "entityPageDetails")
    @JsonIgnore
    private StarkeEntity starkeEntity;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPagedetailid() {
        return pagedetailid;
    }

    public EntityPageDetails pagedetailid(Integer pagedetailid) {
        this.pagedetailid = pagedetailid;
        return this;
    }

    public void setPagedetailid(Integer pagedetailid) {
        this.pagedetailid = pagedetailid;
    }

    public String getPagetypename() {
        return pagetypename;
    }

    public EntityPageDetails pagetypename(String pagetypename) {
        this.pagetypename = pagetypename;
        return this;
    }

    public void setPagetypename(String pagetypename) {
        this.pagetypename = pagetypename;
    }

    public EntityPageFormats getEntityPageFormats() {
        return entityPageFormats;
    }

    public EntityPageDetails entityPageFormats(EntityPageFormats entityPageFormats) {
        this.entityPageFormats = entityPageFormats;
        return this;
    }

    public void setEntityPageFormats(EntityPageFormats entityPageFormats) {
        this.entityPageFormats = entityPageFormats;
    }

    public StarkeEntity getStarkeEntity() {
        return starkeEntity;
    }

    public EntityPageDetails starkeEntity(StarkeEntity starkeEntity) {
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
        EntityPageDetails entityPageDetails = (EntityPageDetails) o;
        if (entityPageDetails.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), entityPageDetails.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EntityPageDetails{" +
            "id=" + getId() +
            ", pagedetailid=" + getPagedetailid() +
            ", pagetypename='" + getPagetypename() + "'" +
            "}";
    }
}
