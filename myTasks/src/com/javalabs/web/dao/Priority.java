package com.javalabs.web.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.None.class, property = "idTaskPriority")
@Table(name = "a_taskpriority")
public class Priority {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "idTaskPriority")
	private long idTaskPriority = 0;
    private long sortOrder = 0;
    private String aka = "";
    @Column(name="priorityname")
    private String priorityname = "";
    private Date timestamp;

    public Priority() {
    }

    /**
     * @param priority
     */
    public Priority(String priority) {
        this.priorityname = priority;
    }
    
    
    /**
     * @param aka
     * @param priority
     */
    public Priority(String aka, String priority) {
        this.aka = aka;
        this.priorityname = priority;
    }

    /**
     * @param order
     * @param aka
     * @param priority
     */
    public Priority(long sortOrder, String aka, String priority) {
        this.sortOrder = sortOrder;
        this.aka = aka;
        this.priorityname = priority;
    }
    
    /**
     * @param order
     * @param priority
     */
    public Priority(long sortOrder, String priority) {
        this.sortOrder = sortOrder;
        this.priorityname = priority;
    }
    
    public void setIdTaskPriority(long idTaskPriority) {
        this.idTaskPriority = idTaskPriority;
    }

    public long getIdTaskPriority() {
        return idTaskPriority;
    }

    public long getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(long sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getAka() {
        return aka;
    }

    public void setAka(String aka) {
        this.aka = aka;
    }

    public String getPriorityname() {
        return priorityname;
    }

    public void setPriorityname(String priorityname) {
        this.priorityname = priorityname;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aka == null) ? 0 : aka.hashCode());
		result = prime * result
				+ ((priorityname == null) ? 0 : priorityname.hashCode());
		result = prime * result + (int) (sortOrder ^ (sortOrder >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Priority))
			return false;
		Priority other = (Priority) obj;
		if (aka == null) {
			if (other.aka != null)
				return false;
		} else if (!aka.equals(other.aka))
			return false;
		if (priorityname == null) {
			if (other.priorityname != null)
				return false;
		} else if (!priorityname.equals(other.priorityname))
			return false;
		if (sortOrder != other.sortOrder)
			return false;
		return true;
	}

	@Override
    public String toString() {
        return "Priority [idTaskPriority=" + idTaskPriority + ", sortOrder=" + sortOrder + ", aka=" + aka
                + ", priority=" + priorityname + ", timestamp=" + timestamp + "]";
    }

}
