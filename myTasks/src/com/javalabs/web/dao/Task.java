package com.javalabs.web.dao;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

//import org.springframework.format.annotation.DateTimeFormat;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idTask")
@Table(name = "t_task")
public class Task implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2477946304346094823L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idTask")
	private long idTask;
	@Size(min = 5, max = 100)
	@Column(name = "taskname")
	private String taskname;
	@Column(name = "description")
	private String description;
	@Column(name = "date")
	private Date date;
	@Column(name = "deadline")
	private Date deadline;
	@NotNull
	@ManyToOne
	@JoinColumn(name = "idTaskCategory")
	private Category category;
	@ManyToOne
	@JoinColumn(name = "idTaskPriority")
	private Priority priority;
	@ManyToOne
	@JoinColumn(name = "idTaskState")
	private State state;
	@ManyToOne
	@JoinColumn(name = "idUser")
	private User user;
	@ManyToOne
	@JoinColumn(name = "idUser_responsible", nullable = true)
	private User userResponsible;
	@Column(name = "evaluation")
	private String evaluation;
	@Column(name = "pending")
	private int pending;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "task", orphanRemoval = true)
	private List<TaskAction> actions = new ArrayList<TaskAction>();
	@Column(name = "timestamp")
	private Timestamp timestamp;

	public Task() {
		System.out.println("Task constructor loaded");
	}

	/**
	 * @author Jose Manuel Sanchez
	 * @param taskname
	 *            Title of the task
	 * @param description
	 *            Description details of the task
	 * @param date
	 *            Date of the task creation
	 * @param category
	 *            Category of the task
	 * @param user
	 *            User that creates the task
	 */
	public Task(String taskname, String description, Date date,
			Category category, Priority priority, User user) {
		this.taskname = taskname;
		this.description = description;
		this.date = new Date((date.getTime() / 1000) * 1000);
		this.deadline = null;// new Date((deadline.getTime()/1000)*1000);
		this.category = category;
		this.priority = priority;
		this.user = user;
		this.userResponsible = null;
	}

	/**
	 * @param task
	 * @param description
	 * @param date
	 * @param deadline
	 * @param idCategory
	 * @param idPriority
	 * @param idState
	 * @param idUser
	 * @param idUserResponsible
	 * @param evaluation
	 * @param pending
	 */
	public Task(String taskname, String description, Date date, Date deadline,
			Category category, Priority priority, State state, User user,
			User userResponsible, String evaluation, int pending) {
		this.taskname = taskname;
		this.description = description;
		this.date = new Date((date.getTime() / 1000) * 1000);// delete milis
		this.deadline = new Date((deadline.getTime() / 1000) * 1000);// delete
																		// milis
		this.category = category;
		this.priority = priority;
		this.state = state;
		this.user = user;
		this.userResponsible = userResponsible;
		this.evaluation = evaluation;
		this.pending = pending;
	}

	public void setIdTask(long id) {
		this.idTask = id;
	}

	public long getIdTask() {
		return idTask;
	}

	public String getTaskname() {
		return taskname;
	}

	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;// new Date((date.getTime()/1000)*1000);
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;// new Date((deadline.getTime()/1000)*1000);
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUserResponsible() {
		return userResponsible;
	}

	public void setUserResponsible(User userResponsible) {
		this.userResponsible = userResponsible;
	}

	public String getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}

	public int getPending() {
		return pending;
	}

	public void setPending(int pending) {
		this.pending = pending;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public List<TaskAction> getActions() {
		return actions;
	}

	public void setActions(List<TaskAction> actions) {
		this.actions = actions;
	}

	public void addAction(TaskAction a) {
		this.actions.add(a);
	}

	@Override
	public String toString() {
		return "Task [idTask=" + idTask + ", taskname=" + taskname
				+ ", description=" + description + ", date=" + date
				+ ", deadline=" + deadline + ", category=" + category
				+ ", priority=" + priority + ", state=" + state + ", user="
				+ user + ", userResponsible=" + userResponsible
				+ ", evaluation=" + evaluation + ", timestamp=" + timestamp
				+ ", pending=" + pending + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result
				+ ((deadline == null) ? 0 : deadline.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((evaluation == null) ? 0 : evaluation.hashCode());
		result = prime * result + pending;
		result = prime * result
				+ ((priority == null) ? 0 : priority.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result
				+ ((taskname == null) ? 0 : taskname.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result
				+ ((userResponsible == null) ? 0 : userResponsible.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Task))
			return false;
		Task other = (Task) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (deadline == null) {
			if (other.deadline != null)
				return false;
		} else if (!deadline.equals(other.deadline))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (evaluation == null) {
			if (other.evaluation != null)
				return false;
		} else if (!evaluation.equals(other.evaluation))
			return false;
		if (pending != other.pending)
			return false;
		if (priority == null) {
			if (other.priority != null)
				return false;
		} else if (!priority.equals(other.priority))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (taskname == null) {
			if (other.taskname != null)
				return false;
		} else if (!taskname.equals(other.taskname))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		if (userResponsible == null) {
			if (other.userResponsible != null)
				return false;
		} else if (!userResponsible.equals(other.userResponsible))
			return false;
		return true;
	}
}
