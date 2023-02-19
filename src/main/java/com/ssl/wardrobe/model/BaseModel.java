package com.ssl.wardrobe.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@MappedSuperclass
public abstract class BaseModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter
	@Column(name = "pk", updatable = false)
	private Long pk;

	@Column(name = "creationtime", nullable = false,updatable = false)
	@CreationTimestamp
	private LocalDateTime creationTime = LocalDateTime.now();

	@Column(name = "modifiedtime", nullable = false)
	@UpdateTimestamp
	private LocalDateTime modifiedTime;

	@Column(name = "bitreference", nullable = false)
	@Setter
	private String bitReference;

	@Column(name = "memeberid", nullable = false, insertable = true, updatable = false)
	@Setter
	private String memberId;
}
