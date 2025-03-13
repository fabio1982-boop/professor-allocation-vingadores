package com.project.professor.allocation.entity;

import java.sql.Time;
import java.time.DayOfWeek;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;


@Entity
@Data
@AllArgsConstructor
public class Allocation {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	@Column (nullable = false )
	@Enumerated (EnumType.STRING)
	private DayOfWeek day;
	@Column (nullable = false)
	private Time start;
	@Column (nullable = false)
	private Time end;
	@ManyToOne (optional = false)
	private Professor professor;
	@ManyToOne (optional = false)
	private Course course;
}
