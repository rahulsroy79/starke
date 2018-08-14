package com.starke.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the EntityReviews entity.
 */
public class EntityReviewsDTO implements Serializable {

    private Long id;

    private Integer rating;

    @NotNull
    private Integer reviewid;

    @NotNull
    private Integer entityid;

    private LocalDate reviewdate;

    @NotNull
    private Integer userid;

    private String subject;

    private String detail;

    private Integer ismarked;

    @NotNull
    private Integer isdeleted;

    private Long starkeEntityId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getReviewid() {
        return reviewid;
    }

    public void setReviewid(Integer reviewid) {
        this.reviewid = reviewid;
    }

    public Integer getEntityid() {
        return entityid;
    }

    public void setEntityid(Integer entityid) {
        this.entityid = entityid;
    }

    public LocalDate getReviewdate() {
        return reviewdate;
    }

    public void setReviewdate(LocalDate reviewdate) {
        this.reviewdate = reviewdate;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getIsmarked() {
        return ismarked;
    }

    public void setIsmarked(Integer ismarked) {
        this.ismarked = ismarked;
    }

    public Integer getIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(Integer isdeleted) {
        this.isdeleted = isdeleted;
    }

    public Long getStarkeEntityId() {
        return starkeEntityId;
    }

    public void setStarkeEntityId(Long starkeEntityId) {
        this.starkeEntityId = starkeEntityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EntityReviewsDTO entityReviewsDTO = (EntityReviewsDTO) o;
        if (entityReviewsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), entityReviewsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EntityReviewsDTO{" +
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
            ", starkeEntity=" + getStarkeEntityId() +
            "}";
    }
}
