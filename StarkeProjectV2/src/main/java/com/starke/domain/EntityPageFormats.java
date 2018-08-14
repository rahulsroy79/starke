package com.starke.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A EntityPageFormats.
 */
@Entity
@Table(name = "entity_page_formats")
public class EntityPageFormats implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "pageformatid", nullable = false)
    private Integer pageformatid;

    @Column(name = "pageformatname")
    private String pageformatname;

    @Column(name = "mainimagepath")
    private String mainimagepath;

    @Column(name = "image_2_path")
    private String image2path;

    @Column(name = "image_3_path")
    private String image3path;

    @OneToOne(mappedBy = "entityPageFormats")
    @JsonIgnore
    private EntityPageDetails entityPageDetails;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPageformatid() {
        return pageformatid;
    }

    public EntityPageFormats pageformatid(Integer pageformatid) {
        this.pageformatid = pageformatid;
        return this;
    }

    public void setPageformatid(Integer pageformatid) {
        this.pageformatid = pageformatid;
    }

    public String getPageformatname() {
        return pageformatname;
    }

    public EntityPageFormats pageformatname(String pageformatname) {
        this.pageformatname = pageformatname;
        return this;
    }

    public void setPageformatname(String pageformatname) {
        this.pageformatname = pageformatname;
    }

    public String getMainimagepath() {
        return mainimagepath;
    }

    public EntityPageFormats mainimagepath(String mainimagepath) {
        this.mainimagepath = mainimagepath;
        return this;
    }

    public void setMainimagepath(String mainimagepath) {
        this.mainimagepath = mainimagepath;
    }

    public String getImage2path() {
        return image2path;
    }

    public EntityPageFormats image2path(String image2path) {
        this.image2path = image2path;
        return this;
    }

    public void setImage2path(String image2path) {
        this.image2path = image2path;
    }

    public String getImage3path() {
        return image3path;
    }

    public EntityPageFormats image3path(String image3path) {
        this.image3path = image3path;
        return this;
    }

    public void setImage3path(String image3path) {
        this.image3path = image3path;
    }

    public EntityPageDetails getEntityPageDetails() {
        return entityPageDetails;
    }

    public EntityPageFormats entityPageDetails(EntityPageDetails entityPageDetails) {
        this.entityPageDetails = entityPageDetails;
        return this;
    }

    public void setEntityPageDetails(EntityPageDetails entityPageDetails) {
        this.entityPageDetails = entityPageDetails;
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
        EntityPageFormats entityPageFormats = (EntityPageFormats) o;
        if (entityPageFormats.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), entityPageFormats.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EntityPageFormats{" +
            "id=" + getId() +
            ", pageformatid=" + getPageformatid() +
            ", pageformatname='" + getPageformatname() + "'" +
            ", mainimagepath='" + getMainimagepath() + "'" +
            ", image2path='" + getImage2path() + "'" +
            ", image3path='" + getImage3path() + "'" +
            "}";
    }
}
