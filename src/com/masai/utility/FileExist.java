package com.masai.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.masai.entities.Batch;
import com.masai.entities.Courses;
import com.masai.entities.Feedback;
import com.masai.entities.Student;

public class FileExist {

	public static Map<Integer,Courses> courseFile() throws IOException, ClassNotFoundException{
		
		Map<Integer,Courses> cFile = null;
		
		File f = new File("Courses.ser");
		boolean flag = false;
		if(!f.exists()) {
			f.createNewFile();
			flag=true;
		}
		if(flag) {
			cFile = new LinkedHashMap<>();
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
			oos.writeObject(cFile);
			return cFile;
		}else {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			cFile=(Map<Integer, Courses>) ois.readObject();
			return cFile;
		}
	}

	
	public static Map<Integer, Batch> batchFile() throws IOException, ClassNotFoundException {
	Map<Integer,Batch> mFile = null;
		
		File i = new File("Batch.ser");
		if(i.exists()) {
			ObjectInputStream ooos = new ObjectInputStream(new FileInputStream(i));
			mFile= (Map<Integer, Batch>) ooos.readObject();
			return mFile;
		}else {
			i.createNewFile();
			mFile = new LinkedHashMap<>();
			ObjectOutputStream oiis = new ObjectOutputStream(new FileOutputStream(i));
			oiis.writeObject(mFile);
			return mFile;
			
		}
	}


	public static Map<Integer, Student> studentFile() throws FileNotFoundException, IOException, ClassNotFoundException {
		
		Map<Integer,Student> mFile = null;
		File i = new File("Students.ser");
		if(i.exists()) {
			ObjectInputStream ooos = new ObjectInputStream(new FileInputStream(i));
			mFile= (Map<Integer, Student>) ooos.readObject();
			return mFile;
		}else {
			i.createNewFile();
			mFile = new LinkedHashMap<>();
			ObjectOutputStream oiis = new ObjectOutputStream(new FileOutputStream(i));
			oiis.writeObject(mFile);
			return mFile;
			
		}
	}


	public static List<Feedback> feedbackFile() throws FileNotFoundException, IOException, ClassNotFoundException {
		List<Feedback>  fFile = null;
		File ff = new File("Feedback.ser");
		if(ff.exists()) {
			ObjectInputStream oisf = new ObjectInputStream(new FileInputStream(ff));
			fFile = (List<Feedback>) oisf.readObject();
			return fFile;
		}else {
			ff.createNewFile();
			fFile = new ArrayList<Feedback>();
			ObjectOutputStream oosf = new ObjectOutputStream(new FileOutputStream(ff));
			oosf.writeObject(fFile);
			return fFile;
		}
	}
	}
	

