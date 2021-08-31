package com.tlttest.notes.tlttestNotesApp.model;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "notes")
public class NotesDTO {
	@Id
	private String id;
	
	@NotNull(message="Title cannot be blank")
	@NotEmpty(message="Title cannot be blank")
	private String title;
	@NotNull(message="Text cannot be blank")
	@NotEmpty(message="Text cannot be blank")
	private String text;
	
	private String tags;
	
	private Date createdAt;
	
	private Date updatedAt;

	
	public NotesDTO() {}
	
	public NotesDTO(String id, String title, String text, String tags, Date createdAt, Date updatedAt) {
		super();
		this.id = id;
		this.title = title;
		this.text = text;
		this.tags = tags;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	
	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "NotesDTO [id=" + id + ", title=" + title + ", text=" + text + ", tags=" + tags + ", createdAt="
				+ createdAt + ", updatedAt=" + updatedAt + "]";
	}

	

}
