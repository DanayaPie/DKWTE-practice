package com.revature.dkwte.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenerationTime;

@Entity
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int reviewId;

	@Column(name = "rating", nullable = false)
	private String rating;

	@Column(name = "review", nullable = false)
	private String review;

	@Column(name = "submit_date", updatable = false, insertable = false)
	private Date submitDate;

	@ManyToOne
	private Restaurant restaurantId;

	@ManyToOne
	private User authorId;

	public Review() {
		super();
	}

	public Review(String rating, String review, Date submitDate, Restaurant restaurantId, User authorId) {
		super();
		this.rating = rating;
		this.review = review;
		this.submitDate = submitDate;
		this.restaurantId = restaurantId;
		this.authorId = authorId;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public Date getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}

	public Restaurant getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Restaurant restaurantId) {
		this.restaurantId = restaurantId;
	}

	public User getAuthorId() {
		return authorId;
	}

	public void setAuthorId(User authorId) {
		this.authorId = authorId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authorId == null) ? 0 : authorId.hashCode());
		result = prime * result + ((rating == null) ? 0 : rating.hashCode());
		result = prime * result + ((restaurantId == null) ? 0 : restaurantId.hashCode());
		result = prime * result + ((review == null) ? 0 : review.hashCode());
		result = prime * result + reviewId;
		result = prime * result + ((submitDate == null) ? 0 : submitDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Review other = (Review) obj;
		if (authorId == null) {
			if (other.authorId != null)
				return false;
		} else if (!authorId.equals(other.authorId))
			return false;
		if (rating == null) {
			if (other.rating != null)
				return false;
		} else if (!rating.equals(other.rating))
			return false;
		if (restaurantId == null) {
			if (other.restaurantId != null)
				return false;
		} else if (!restaurantId.equals(other.restaurantId))
			return false;
		if (review == null) {
			if (other.review != null)
				return false;
		} else if (!review.equals(other.review))
			return false;
		if (reviewId != other.reviewId)
			return false;
		if (submitDate == null) {
			if (other.submitDate != null)
				return false;
		} else if (!submitDate.equals(other.submitDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Review [reviewId=" + reviewId + ", rating=" + rating + ", review=" + review + ", submitDate="
				+ submitDate + ", restaurantId=" + restaurantId + ", authorId=" + authorId + "]";
	}

}
