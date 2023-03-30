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
	private Courses course;
	private LocalDate startDate;
	private LocalDate endDate;
	public Batch(String name, int strength, Courses course, LocalDate startDate, LocalDate endDate) {
		super();
		this.id=IDGeneration.generateId();
		this.name = name;
		Strength = strength;
		this.course = course;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Batch() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}
	public int getStrength() {
		return Strength;
	}
	public Courses getCourse() {
		return course;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setStrength(int strength) {
		Strength = strength;
	}
	public void setCourse(Courses course) {
		this.course = course;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	
	@Override
	public String toString() {
		return "Batch [id=" + id + ", name=" + name + ", Strength=" + Strength + ", course=" + course + ", startDate="
				+ startDate + ", endDate=" + endDate + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(Strength, course, endDate, name, startDate);
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
		return Strength == other.Strength && Objects.equals(course, other.course)
				&& Objects.equals(endDate, other.endDate) && Objects.equals(name, other.name)
				&& Objects.equals(startDate, other.startDate);
	}
	
}
