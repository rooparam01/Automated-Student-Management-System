package com.masai.entities;

import java.io.Serializable;
import java.util.Objects;

public class Feedback implements Serializable {
	private String nameOfStudent;
	private int studentId;
	private String batchOfStudent;
	private String feedbackNote;
	private int ratiingUs;
	public Feedback(String feedbackNote, int ratiingUs) {
		super();
		this.feedbackNote = feedbackNote;
		this.ratiingUs = ratiingUs;
	}
	
	
	public String getNameOfStudent() {
		return nameOfStudent;
	}
	public int getStudentId() {
		return studentId;
	}
	public String getBatchOfStudent() {
		return batchOfStudent;
	}
	public String getFeedbackNote() {
		return feedbackNote;
	}
	public int getRatiingUs() {
		return ratiingUs;
	}
	public void setNameOfStudent(String nameOfStudent) {
		this.nameOfStudent = nameOfStudent;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public void setBatchOfStudent(String batchOfStudent) {
		this.batchOfStudent = batchOfStudent;
	}
	public void setFeedbackNote(String feedbackNote) {
		this.feedbackNote = feedbackNote;
	}
	public void setRatiingUs(int ratiingUs) {
		this.ratiingUs = ratiingUs;
	}


	@Override
	public String toString() {
		return "**Feedback** \n nameOfStudent=" + nameOfStudent + "\n studentId=" + studentId + "\n batchOfStudent="
				+ batchOfStudent + "\n feedbackNote=" + feedbackNote + "\n ratiing=" + ratiingUs;
	}


	@Override
	public int hashCode() {
		return Objects.hash(batchOfStudent, feedbackNote, nameOfStudent, ratiingUs, studentId);
	}



	
	
}
