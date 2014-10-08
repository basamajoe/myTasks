package com.javalabs.web.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.None.class, property = "idTaskCategory")
@Table(name = "a_taskCategory")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idTaskCategory")
	private long idTaskCategory;
	private long sortOrder;
	@NotBlank
	@Column(name = "categoryname")
	private String categoryname;
	private Date timestamp;

	public Category() {
	}

	public Category(long idTaskCategory) {
		this.idTaskCategory = idTaskCategory;
	}

	public Category(String categoryname) {
		this.categoryname = categoryname;
	}

	public Category(long sortOrder, String categoryname) {
		this.sortOrder = sortOrder;
		this.categoryname = categoryname;
	}

	public void setIdTaskCategory(long idTaskCategory) {
		this.idTaskCategory = idTaskCategory;
	}

	public long getIdTaskCategory() {
		return idTaskCategory;
	}

	public long getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	public void setTimestamp(Date tm) {
		this.timestamp = tm;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((categoryname == null) ? 0 : categoryname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Category))
			return false;
		Category other = (Category) obj;
		if (categoryname == null) {
			if (other.categoryname != null)
				return false;
		} else if (!categoryname.equals(other.categoryname))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Category [idTaskCategory=" + idTaskCategory + ", sortOrder="
				+ sortOrder + ", category=" + categoryname + ", timestamp="
				+ timestamp + "]";
	}

}