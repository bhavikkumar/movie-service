package io.bhavik.movie.model;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "movie")
public class Movie implements OnCreate, OnUpdate {
	@Id
	@Null(groups = { OnCreate.class, OnUpdate.class })
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
	@Column
	private UUID id;

	@NotNull(groups = { OnCreate.class, OnUpdate.class })
	@Column
	private String name;

	@NotNull(groups = { OnCreate.class, OnUpdate.class })
	@ElementCollection
	private List<UUID> genres;

	@CreatedDate
	@NotNull(groups = OnUpdate.class)
	@Null(groups = OnCreate.class)
	@Column
	private ZonedDateTime createdDateTime;

	@LastModifiedDate
	@Version
	@NotNull(groups = OnUpdate.class)
	@Null(groups = OnCreate.class)
	@Column
	private ZonedDateTime updatedDateTime;

	@Column
	private Boolean released;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<UUID> getGenres() {
		return genres;
	}

	public void setGenres(List<UUID> genres) {
		this.genres = genres;
	}

	public ZonedDateTime getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(ZonedDateTime createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public ZonedDateTime getUpdatedDateTime() {
		return updatedDateTime;
	}

	public void setUpdatedDateTime(ZonedDateTime updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}

	public boolean isReleased() {
		return released;
	}

	public void setReleased(boolean released) {
		this.released = released;
	}
}
