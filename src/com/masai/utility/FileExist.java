package com.masai.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import com.masai.entities.Courses;

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
	
}
