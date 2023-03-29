package com.masai.entities;

import java.io.Serializable;
import java.util.Objects;

public class Courses implements Serializable{
	private int id;
	private String name;
	private int fee;
	private int duration;
	
	public Courses() {
		super();
	}

	public Courses(int id, String name, int fee, int duration) {
		super();
		this.id = id;
		this.name = name;
		this.fee = fee;
		this.duration = duration;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getFee() {
		return fee;
	}

	public int getDuration() {
		return duration;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "Courses [id=" + id + ", name=" + name + ", fee=" + fee + ", duration(months)=" + duration + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(duration, fee, id, name);
	}

	@Override
	public boolean equals(Object obj) { 
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Courses other = (Courses) obj;
		return duration == other.duration && fee == other.fee && id == other.id && Objects.equals(name, other.name);
	}
	
	
}
