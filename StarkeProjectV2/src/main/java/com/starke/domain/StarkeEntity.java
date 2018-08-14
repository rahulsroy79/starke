package com.starke.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A StarkeEntity.
 */
@Entity
@Table(name = "starke_entity")
public class StarkeEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "entityid", nullable = false)
    private Integer entityid;

    @NotNull
    @Column(name = "entityname", nullable = false)
    private String entityname;

    @NotNull
    @Column(name = "entitytypeid", nullable = false)
    private Integer entitytypeid;

    @NotNull
    @Column(name = "istopentity", nullable = false)
    private Integer istopentity;

    @Column(name = "parententityid")
    private Integer parententityid;

    @NotNull
    @Column(name = "allowrating", nullable = false)
    private Integer allowrating;

    @NotNull
    @Column(name = "allowreview", nullable = false)
    private Integer allowreview;

    @NotNull
    @Column(name = "entitycreationdate", nullable = false)
    private LocalDate entitycreationdate;

    @Column(name = "entitybriefdescription")
    private String entitybriefdescription;

    @Lob
    @Column(name = "entityimage")
    private byte[] entityimage;

    @Column(name = "entityimage_content_type")
    private String entityimageContentType;

    @OneToOne
    @JoinColumn(unique = true)
    private EntityPageDetails entityPageDetails;

    @OneToOne
    @JoinColumn(unique = true)
    private EntityDetails entityDetails;

    @OneToMany(mappedBy = "starkeEntity")
    private Set<EntityReviews> entityReviews = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("")
    private StarkeEntity parent;

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

    public StarkeEntity entityid(Integer entityid) {
        this.entityid = entityid;
        return this;
    }

    public void setEntityid(Integer entityid) {
        this.entityid = entityid;
    }

    public String getEntityname() {
        return entityname;
    }

    public StarkeEntity entityname(String entityname) {
        this.entityname = entityname;
        return this;
    }

    public void setEntityname(String entityname) {
        this.entityname = entityname;
    }

    public Integer getEntitytypeid() {
        return entitytypeid;
    }

    public StarkeEntity entitytypeid(Integer entitytypeid) {
        this.entitytypeid = entitytypeid;
        return this;
    }

    public void setEntitytypeid(Integer entitytypeid) {
        this.entitytypeid = entitytypeid;
    }

    public Integer getIstopentity() {
        return istopentity;
    }

    public StarkeEntity istopentity(Integer istopentity) {
        this.istopentity = istopentity;
        return this;
    }

    public void setIstopentity(Integer istopentity) {
        this.istopentity = istopentity;
    }

    public Integer getParententityid() {
        return parententityid;
    }

    public StarkeEntity parententityid(Integer parententityid) {
        this.parententityid = parententityid;
        return this;
    }

    public void setParententityid(Integer parententityid) {
        this.parententityid = parententityid;
    }

    public Integer getAllowrating() {
        return allowrating;
    }

    public StarkeEntity allowrating(Integer allowrating) {
        this.allowrating = allowrating;
        return this;
    }

    public void setAllowrating(Integer allowrating) {
        this.allowrating = allowrating;
    }

    public Integer getAllowreview() {
        return allowreview;
    }

    public StarkeEntity allowreview(Integer allowreview) {
        this.allowreview = allowreview;
        return this;
    }

    public void setAllowreview(Integer allowreview) {
        this.allowreview = allowreview;
    }

    public LocalDate getEntitycreationdate() {
        return entitycreationdate;
    }

    public StarkeEntity entitycreationdate(LocalDate entitycreationdate) {
        this.entitycreationdate = entitycreationdate;
        return this;
    }

    public void setEntitycreationdate(LocalDate entitycreationdate) {
        this.entitycreationdate = entitycreationdate;
    }

    public String getEntitybriefdescription() {
        return entitybriefdescription;
    }

    public StarkeEntity entitybriefdescription(String entitybriefdescription) {
        this.entitybriefdescription = entitybriefdescription;
        return this;
    }

    public void setEntitybriefdescription(String entitybriefdescription) {
        this.entitybriefdescription = entitybriefdescription;
    }

    public byte[] getEntityimage() {
        return entityimage;
    }

    public StarkeEntity entityimage(byte[] entityimage) {
        this.entityimage = entityimage;
        return this;
    }

    public void setEntityimage(byte[] entityimage) {
        this.entityimage = entityimage;
    }

    public String getEntityimageContentType() {
        return entityimageContentType;
    }

    public StarkeEntity entityimageContentType(String entityimageContentType) {
        this.entityimageContentType = entityimageContentType;
        return this;
    }

    public void setEntityimageContentType(String entityimageContentType) {
        this.entityimageContentType = entityimageContentType;
    }

    public EntityPageDetails getEntityPageDetails() {
        return entityPageDetails;
    }

    public StarkeEntity entityPageDetails(EntityPageDetails entityPageDetails) {
        this.entityPageDetails = entityPageDetails;
        return this;
    }

    public void setEntityPageDetails(EntityPageDetails entityPageDetails) {
        this.entityPageDetails = entityPageDetails;
    }

    public EntityDetails getEntityDetails() {
        return entityDetails;
    }

    public StarkeEntity entityDetails(EntityDetails entityDetails) {
        this.entityDetails = entityDetails;
        return this;
    }

    public void setEntityDetails(EntityDetails entityDetails) {
        this.entityDetails = entityDetails;
    }

    public Set<EntityReviews> getEntityReviews() {
        return entityReviews;
    }

    public StarkeEntity entityReviews(Set<EntityReviews> entityReviews) {
        this.entityReviews = entityReviews;
        return this;
    }

    public StarkeEntity addEntityReviews(EntityReviews entityReviews) {
        this.entityReviews.add(entityReviews);
        entityReviews.setStarkeEntity(this);
        return this;
    }

    public StarkeEntity removeEntityReviews(EntityReviews entityReviews) {
        this.entityReviews.remove(entityReviews);
        entityReviews.setStarkeEntity(null);
        return this;
    }

    public void setEntityReviews(Set<EntityReviews> entityReviews) {
        this.entityReviews = entityReviews;
    }

    public StarkeEntity getParent() {
        return parent;
    }

    public StarkeEntity parent(StarkeEntity starkeEntity) {
        this.parent = starkeEntity;
        return this;
    }

    public void setParent(StarkeEntity starkeEntity) {
        this.parent = starkeEntity;
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
        StarkeEntity starkeEntity = (StarkeEntity) o;
        if (starkeEntity.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), starkeEntity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "StarkeEntity{" +
            "id=" + getId() +
            ", entityid=" + getEntityid() +
            ", entityname='" + getEntityname() + "'" +
            ", entitytypeid=" + getEntitytypeid() +
            ", istopentity=" + getIstopentity() +
            ", parententityid=" + getParententityid() +
            ", allowrating=" + getAllowrating() +
            ", allowreview=" + getAllowreview() +
            ", entitycreationdate='" + getEntitycreationdate() + "'" +
            ", entitybriefdescription='" + getEntitybriefdescription() + "'" +
            ", entityimage='" + getEntityimage() + "'" +
            ", entityimageContentType='" + getEntityimageContentType() + "'" +
            "}";
    }
}
