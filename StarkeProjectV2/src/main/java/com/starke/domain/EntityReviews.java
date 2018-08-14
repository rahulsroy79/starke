package com.starke.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A EntityReviews.
 */
@Entity
@Table(name = "entity_reviews")
public class EntityReviews implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "rating")
    private Integer rating;

    @NotNull
    @Column(name = "reviewid", nullable = false)
    private Integer reviewid;

    @NotNull
    @Column(name = "entityid", nullable = false)
    private Integer entityid;

    @Column(name = "reviewdate")
    private LocalDate reviewdate;

    @NotNull
    @Column(name = "userid", nullable = false)
    private Integer userid;

    @Column(name = "subject")
    private String subject;

    @Column(name = "detail")
    private String detail;

    @Column(name = "ismarked")
    private Integer ismarked;

    @NotNull
    @Column(name = "isdeleted", nullable = false)
    private Integer isdeleted;

    @ManyToOne
    @JsonIgnoreProperties("entityReviews")
    private StarkeEntity starkeEntity;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRating() {
        return rating;
    }

    public EntityReviews rating(Integer rating) {
        this.rating = rating;
        return this;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getReviewid() {
        return reviewid;
    }

    public EntityReviews reviewid(Integer reviewid) {
        this.reviewid = reviewid;
        return this;
    }

    public void setReviewid(Integer reviewid) {
        this.reviewid = reviewid;
    }

    public Integer getEntityid() {
        return entityid;
    }

    public EntityReviews entityid(Integer entityid) {
        this.entityid = entityid;
        return this;
    }

    public void setEntityid(Integer entityid) {
        this.entityid = entityid;
    }

    public LocalDate getReviewdate() {
        return reviewdate;
    }

    public EntityReviews reviewdate(LocalDate reviewdate) {
        this.reviewdate = reviewdate;
        return this;
    }

    public void setReviewdate(LocalDate reviewdate) {
        this.reviewdate = reviewdate;
    }

    public Integer getUserid() {
        return userid;
    }

    public EntityReviews userid(Integer userid) {
        this.userid = userid;
        return this;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getSubject() {
        return subject;
    }

    public EntityReviews subject(String subject) {
        this.subject = subject;
        return this;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDetail() {
        return detail;
    }

    public EntityReviews detail(String detail) {
        this.detail = detail;
        return this;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getIsmarked() {
        return ismarked;
    }

    public EntityReviews ismarked(Integer ismarked) {
        this.ismarked = ismarked;
        return this;
    }

    public void setIsmarked(Integer ismarked) {
        this.ismarked = ismarked;
    }

    public Integer getIsdeleted() {
        return isdeleted;
    }

    public EntityReviews isdeleted(Integer isdeleted) {
        this.isdeleted = isdeleted;
        return this;
    }

    public void setIsdeleted(Integer isdeleted) {
        this.isdeleted = isdeleted;
    }

    public StarkeEntity getStarkeEntity() {
        return starkeEntity;
    }

    public EntityReviews starkeEntity(StarkeEntity starkeEntity) {
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
        EntityReviews entityReviews = (EntityReviews) o;
        if (entityReviews.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), entityReviews.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EntityReviews{" +
            "id=" + getId() +
            ", rating=" + getRating() +
            ", reviewid=" + getReviewid() +
            ", entityid=" + getEntityid() +
            ", reviewdate='" + getReviewdate() + "'" +
            ", userid=" + getUserid() +
            ", subject='" + getSubject() + "'" +
            ", detail='" + getDetail() + "'" +
            ", ismarked=" + getIsmarked() +
            ", isdeleted=" + getIsdeleted() +
            "}";
    }
}
