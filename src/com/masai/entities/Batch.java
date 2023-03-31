package com.masai.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

import com.masai.utility.IDGeneration;

public class Batch implements Serializable {
	private int id;
	private String name;
	private int Strength;
	private int courseId;
	private LocalDate startDate;
	private LocalDate endDate;

	public Batch(String name, int strength, int courseId, LocalDate startDate, LocalDate endDate) {
		super();
		this.id = IDGeneration.generateId();
		this.name = name;
		Strength = strength;
		this.courseId = courseId;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	
	
	public int getId() {
		return id;
	}



	public String getName() {
		return name;
	}



	public int getStrength() {
		return Strength;
	}



	public int getCourseId() {
		return courseId;
	}



	public LocalDate getStartDate() {
		return startDate;
	}



	public LocalDate getEndDate() {
		return endDate;
	}



	public void setId(int id) {
		this.id = id;
	}



	public void setName(String name) {
		this.name = name;
	}



	public void setStrength(int strength) {
		Strength = strength;
	}



	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}



	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}



	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}



	@Override
	public String toString() {
		return "Batch [id=" + id + ", name=" + name + ", Strength=" + Strength + ", courseId=" + courseId
				+ ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}



	@Override
	public int hashCode() {
		return Objects.hash(Strength, courseId, endDate, id, name, startDate);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Batch other = (Batch) obj;
		return Strength == other.Strength && courseId == other.courseId && Objects.equals(endDate, other.endDate)
				&& id == other.id && Objects.equals(name, other.name) && Objects.equals(startDate, other.startDate);
	}


	
}
