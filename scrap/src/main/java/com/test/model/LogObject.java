package com.test.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "log_table")
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LogObject {


	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Getter
	long dbId;
	
	@NotNull
	@Setter
	@Getter
	@Column(name= "eventId")
	String id;

	@Getter
	@Setter
	@Transient
	String state;

	@Setter
	String type;

	@Setter
	String host;

	@Getter
	@Setter
	@Transient
	long timestamp;
	
	@Getter
	@Setter
	@Nullable
	long eventDuration;

	
	@Getter
	@Setter
	@Nullable
	boolean alert;

}
