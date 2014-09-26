package com.javalabs.web.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idTaskState")
@Table(name = "a_taskstate")
public class State {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "idTaskState")
	private long idTaskState;
	@JsonIgnore
	private long sortOrder;
	@Column(name = "statename")
	private String statename;
	@JsonIgnore
	private Date timestamp;

	public State() {
	}

	public State(long idTaskState) {
		this.idTaskState = idTaskState;
	}

	public State(String statename) {
		this.statename = statename;
	}

	public State(long sortOrder, String statename) {
		this.sortOrder = sortOrder;
		this.statename = statename;
	}

	public void setIdTaskState(long idTaskState) {
		this.idTaskState = idTaskState;
	}

	public long getIdTaskState() {
		return idTaskState;
	}

	public long getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(long sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getStatename() {
		return statename;
	}

	public void setTimestamp(Date tm) {
		this.timestamp = tm;
	}

	public void setStatename(String statename) {
		this.statename = statename;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((statename == null) ? 0 : statename.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof State))
			return false;
		State other = (State) obj;
		if (statename == null) {
			if (other.statename != null)
				return false;
		} else if (!statename.equals(other.statename))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "State [idTaskState=" + idTaskState + ", sortOrder=" + sortOrder
				+ ", state=" + statename + ", timestamp=" + timestamp + "]";
	}

}
