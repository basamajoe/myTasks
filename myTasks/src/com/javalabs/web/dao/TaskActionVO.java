package com.javalabs.web.dao;

import java.util.Date;

public class TaskActionVO {

	private long idTaskAction;
	private long idTask;
	private Date date;
	private String actionname;
	private String description;
	private int duration;
	private long idUser;
	private Date timestamp;

	public TaskActionVO() {
	}

	public TaskActionVO(long idTask, Date date, String action,
			String description, long idUser) {
		this.idTask = idTask;
		this.date = new Date((date.getTime() / 1000) * 1000);
		this.actionname = action;
		this.description = description;
		this.idUser = idUser;
	}

	public TaskActionVO(long idTask, long idTaskAction, String actionname,
			String description) {
		this.idTask = idTask;
		this.idTaskAction = idTaskAction;
		this.date = new Date((date.getTime() / 1000) * 1000);
		this.actionname = actionname;
		this.description = description;
	}

	public long getIdTaskAction() {
		return idTaskAction;
	}

	public void setIdTaskAction(long idTaskAction) {
		this.idTaskAction = idTaskAction;
	}

	public long getIdTask() {
		return idTask;
	}

	public void setIdTask(long idTask) {
		this.idTask = idTask;
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

	public long getUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
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
		result = prime * result + (int) (idTask ^ (idTask >>> 32));
		result = prime * result + (int) (idTaskAction ^ (idTaskAction >>> 32));
		result = prime * result + (int) (idUser ^ (idUser >>> 32));
		result = prime * result
				+ ((timestamp == null) ? 0 : timestamp.hashCode());
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
		TaskActionVO other = (TaskActionVO) obj;
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
		if (idTask != other.idTask)
			return false;
		if (idTaskAction != other.idTaskAction)
			return false;
		if (idUser != other.idUser)
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TaskActionVO [idTaskAction=" + idTaskAction + ", idTask="
				+ idTask + ", date=" + date + ", actionname=" + actionname
				+ ", description=" + description + ", duration=" + duration
				+ ", idUser=" + idUser + ", timestamp=" + timestamp + "]";
	}
}