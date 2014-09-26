package com.javalabs.web.dao;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;




import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idTaskAction")
@Table(name = "t_taskaction")
public class TaskAction implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2555485155531996398L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idTaskAction")
	private long idTaskAction;
	@ManyToOne
	@JoinColumn(name = "idTask", nullable = false)
	private Task task;
	@Column(name = "date")
	private Date date;
	private String actionname;
	private String description;
	private int duration;
	@JsonInclude(Include.ALWAYS)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idUser", nullable = false)
	private User user;
	@Column(name = "timestamp")
	private Date timestamp;

	public TaskAction() {
	}

	/**
	 * @author Joe Sanchez
	 * @param task
	 * @param date
	 * @param action
	 * @param description
	 * @param user
	 */
	public TaskAction(Task task, Date date, String action, String description,
			User user) {
		this.task = task;
		this.date = new Date((date.getTime() / 1000) * 1000);
		this.actionname = action;
		this.description = description;
		this.user = user;
	}

	public long getIdTaskAction() {
		return idTaskAction;
	}

	public void setIdTaskAction(long idTaskAction) {
		this.idTaskAction = idTaskAction;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = new Date((date.getTime() / 1000) * 1000);
	}

	public String getActionname() {
		return actionname;
	}

	public void setActionname(String actionname) {
		this.actionname = actionname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(java.sql.Date tm) {
		this.timestamp = tm;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((actionname == null) ? 0 : actionname.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + duration;
		result = prime * result + ((task == null) ? 0 : task.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof TaskAction))
			return false;
		TaskAction other = (TaskAction) obj;
		if (actionname == null) {
			if (other.actionname != null)
				return false;
		} else if (!actionname.equals(other.actionname))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (duration != other.duration)
			return false;
		if (task == null) {
			if (other.task != null)
				return false;
		} else if (!task.equals(other.task))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TaskAction [idTaskAction=" + idTaskAction + ", task=" + task
				+ ", date=" + date + ", actionname=" + actionname
				+ ", description=" + description + ", duration=" + duration
				+ ", user=" + user + ", timestamp=" + timestamp + "]";
	}
}