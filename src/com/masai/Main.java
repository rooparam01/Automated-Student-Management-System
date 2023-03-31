package com.masai;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;

import com.masai.entities.Batch;
import com.masai.entities.Courses;
import com.masai.entities.Student;
import com.masai.exception.CourseException;
import com.masai.exception.InvalidDetailsException;
import com.masai.services.BatchService;
import com.masai.services.BatchServiceImpl;
import com.masai.services.CourseService;
import com.masai.services.CourseServiceImpl;
import com.masai.services.StudentService;
import com.masai.services.StudentServiceImpl;
import com.masai.utility.admin;
import com.masai.utility.FileExist;
import com.masai.utility.IDGeneration;


public class Main {

	private static void studentLogInFunctionallity(Scanner sc, Map<Integer, Courses> courses,
			Map<Integer, Batch> batches, Map<Integer, Student> students) throws InvalidDetailsException {
		int loginedStudentid=studentSignIn(sc,students);
		System.out.println(loginedStudentid);
		int choice = 0;
		try {
			do {
				System.out.println("Press 1 Update Personal Detail");
				System.out.println("Press 2 View Students Details");
				System.out.println("Press 3 Change Password");
				System.out.println("Press 4 Available course List");
				System.out.println("Press 5 Available All Batch List");
				System.out.println("Press 6 Student Enroll for Batch Course");
				System.out.println("Press 0 to LogOut");
				choice = sc.nextInt();

				switch (choice) {
				case 1:
					StudentChangePersonalDetails(sc,loginedStudentid,batches,courses,students);
					break;
				case 2:
					showStudentDetails(sc,loginedStudentid,batches,courses,students);
					break;
				case 3:
					changeStudentPassword(sc,loginedStudentid,batches,courses,students);
					break;
				case 4:
					studentSeeAllAvailbleCourses(sc,loginedStudentid,batches,courses,students);
					break;
				case 5:
					studentSeeAllAvailbleBatches(sc,loginedStudentid,batches,courses,students);
					break;
				case 6:
					studentEnrollForBatches(sc,loginedStudentid,batches,courses,students);
					break;
				case 0:
					//System.out.println("admin has successfully logout");
					break;
				default:
					throw new IllegalArgumentException("Unexpected value: " + choice);
				}

			} while (choice!=0);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	private static void studentEnrollForBatches(Scanner sc, int loginedStudentid, Map<Integer, Batch> batches,
			Map<Integer, Courses> courses, Map<Integer, Student> students) {
		studentSeeAllAvailbleBatches(sc,loginedStudentid,batches,courses,students);
		System.out.println("Please Enter Batch id For Enroll in course");
		int batchid = sc.nextInt();
		boolean flag=true;
		for(Map.Entry<Integer, Student> mp:students.entrySet()) {
			if(mp.getValue().getId()==loginedStudentid) {
				flag=false;
				if(mp.getValue().getBatchid()==0) {
					mp.getValue().setBatchid(batchid);
				}else {
				System.out.println("You have Already Enrolled for another course");
				}
			}
		}
		if(flag==false) {
			System.out.println("Enroll for desired course sucessfully");
		}else {
			System.out.println("Invalid Course Selection");
		}
		
	}
	private static void studentSeeAllAvailbleBatches(Scanner sc, int loginedStudentid, Map<Integer, Batch> batches,
			Map<Integer, Courses> courses, Map<Integer, Student> students) {
		boolean flag=true;
		for(Map.Entry<Integer, Courses> course:courses.entrySet()) {
			for(Map.Entry<Integer, Batch> batch:batches.entrySet()) {
				if(course.getValue().getId()==batch.getValue().getCourseId()) {
					System.out.println("Course:-"+course.getValue().getName()+"  id:-"+course.getValue().getId()+"  BatchName:-"+batch.getValue().getName()+"  BatchStrength:-"+batch.getValue().getStrength()+"  BatchStartDate:-"+batch.getValue().getStartDate()+"  BatchEndDate:-"+batch.getValue().getEndDate());
					flag=false;
				}
			}
		}
		if(flag==true) {
			System.out.println("There is no Batch Available");
		}
		
	}
	private static void studentSeeAllAvailbleCourses(Scanner sc, int loginedStudentid, Map<Integer, Batch> batches,
			Map<Integer, Courses> courses, Map<Integer, Student> students) {
		for(Map.Entry<Integer, Courses> mp:courses.entrySet()) {
			System.out.println(mp.getValue());
		}
	}
	private static void changeStudentPassword(Scanner sc, int loginedStudentid, Map<Integer, Batch> batches,
			Map<Integer, Courses> courses, Map<Integer, Student> students) {
		System.out.println("Enter the current Password");
		String oldPass = sc.next();
		for(Map.Entry<Integer, Student> mp:students.entrySet()) {
			if(mp.getValue().getId()==loginedStudentid) {
				if(mp.getValue().getPassword().equals(oldPass)) {
					System.out.println("Please Enter new Password");
					String newPass = sc.next();
					mp.getValue().setPassword(newPass);
					System.out.println("Your Password is Updated");
				}else {
					System.out.println("Your Password is not correct");
				}
			}
		}
		
	}
	private static void showStudentDetails(Scanner sc, int loginedStudentid, Map<Integer, Batch> batches,
			Map<Integer, Courses> courses, Map<Integer, Student> students) {
		for(Map.Entry<Integer, Student> mp:students.entrySet()) {
			if(mp.getValue().getId()==loginedStudentid) {
				System.out.println(mp.getValue());
			}
		}
		System.out.println("Mobile Number Updated sucessfully");
		
	}
	private static void StudentChangePersonalDetails(Scanner sc, int loginedStudentid, Map<Integer, Batch> batches,
			Map<Integer, Courses> courses, Map<Integer, Student> students) {
		int choice = 0;
		try {
			do {
				System.out.println("Press 1 Update First Name");
				System.out.println("Press 2 Update Last Name");
				System.out.println("Press 3 Update Mobile Number");
				System.out.println("Press 0 to Back");
				choice = sc.nextInt();

				switch (choice) {
				case 1:
					studentChangeFirstName(sc,loginedStudentid,batches,courses,students);
					break;
				case 2:
					studentChangeLastName(sc,loginedStudentid,batches,courses,students);
					break;
				case 3:
					studentChangeMobileNumber(sc,loginedStudentid,batches,courses,students);
					break;
				case 0:
					//System.out.println("admin has successfully logout");
					break;
				default:
					throw new IllegalArgumentException("Unexpected value: " + choice);
				}

			} while (choice!=0);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	private static void studentChangeMobileNumber(Scanner sc, int loginedStudentid, Map<Integer, Batch> batches,
			Map<Integer, Courses> courses, Map<Integer, Student> students) {
		System.out.println("Enter new Mobile Number");
		long mNumber = sc.nextLong();
		for(Map.Entry<Integer, Student> mp:students.entrySet()) {
			if(mp.getValue().getId()==loginedStudentid) {
				mp.getValue().setMobileNumber(mNumber);
			}
		}
		System.out.println("Mobile Number Updated sucessfully");
		
	}
	private static void studentChangeLastName(Scanner sc, int loginedStudentid, Map<Integer, Batch> batches,
			Map<Integer, Courses> courses, Map<Integer, Student> students) {
		System.out.println("Enter Last Name");
		String fName = sc.next();
		for(Map.Entry<Integer, Student> mp:students.entrySet()) {
			if(mp.getValue().getId()==loginedStudentid) {
				mp.getValue().setLastName(fName);;
			}
		}
		System.out.println("Last name Updated sucessfully");
		
	}
	private static void studentChangeFirstName(Scanner sc, int loginedStudentid, Map<Integer, Batch> batches,
			Map<Integer, Courses> courses, Map<Integer, Student> students) {
		System.out.println("Enter First Name");
		String fName = sc.next();
		for(Map.Entry<Integer, Student> mp:students.entrySet()) {
			if(mp.getValue().getId()==loginedStudentid) {
				mp.getValue().setFirstName(fName);
			}
		}
		System.out.println("First name Updated sucessfully");
	}
	private static int studentSignIn(Scanner sc, Map<Integer, Student> students) throws InvalidDetailsException {
		System.out.println("Please enter Your email");
		String email = sc.next();
		System.out.println("Please enter Your password");
		String password = sc.next();
		boolean flag=false;
		int loginStudentid=0;
		for(Map.Entry<Integer,Student> mp:students.entrySet()) {
			if(mp.getValue().getEmail().equals(email)&&mp.getValue().getPassword().equals(password)) {
				flag=true;
				loginStudentid=mp.getValue().getId();
			}
		}
		if(flag==false) {
			throw new InvalidDetailsException("Invalid Login Credentials");
		}else {
			System.out.println("Login Sucessfull");
		}
		return loginStudentid;
	}
	public static void adminFunctionality(Scanner sc,Map<Integer,Courses> courses, Map<Integer, Batch> batches) throws InvalidDetailsException {
		adminLogin(sc);
		
		CourseService courService = new CourseServiceImpl();
		BatchService batcServicec = new BatchServiceImpl();
		int choice = 0;
		try {
			do {
				System.out.println("Press 1 add the course");
				System.out.println("Press 2 view all courses");
				System.out.println("Press 3 Search for information about courses");
				System.out.println("Press 4  Update details of course");
				System.out.println("Press 5 Create new batch Under Course");
				System.out.println("Press 6 Search for information about batches");
				System.out.println("Press 7 Update details of batch");
				System.out.println("Press 8 to log out");
				choice = sc.nextInt();

				switch (choice) {
				case 1:
					String added = adminAddCourse(sc, courses, courService);
					System.out.println(added);
					break;
				case 2:

					adminViewAllCourses(courses, courService);
					break;
				case 3:

					adminSearchInformationCourse(sc, courses, courService);
					break;
				case 4:
					adminUpdateCourseDetail(sc, courses, courService);
					break;
				case 5:
					String adde = batcServicec.adminCreateBatch(sc,courses,batches,courService);
					System.out.println(adde);
					batcServicec.PrintAllBatches(batches);
					break;
				case 6:
					adminSearchInfoBatch(sc,batches,courses);
					break;
				case 7:
					adminUpdateBatchDetails(sc,batches,batcServicec);
					break;
				case 8:
					System.out.println("admin has successfully logout");
					break;
				default:
					throw new IllegalArgumentException("Unexpected value: " + choice);
				}

			} while (choice <= 7);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public static void adminUpdateBatchDetails(Scanner sc, Map<Integer, Batch> batches, BatchService batcServicec) {
		int choice = 0;
		try {
			do {
				System.out.println("Press 1 Change Batch Name");
				System.out.println("Press 2 Change Batch Strength");
				System.out.println("Press 0 to Back");
				choice = sc.nextInt();

				switch (choice) {
				case 1:
					adminChangeBatchByname(sc,batches,batcServicec);
					break;
				case 2:
					adminChangeStrengthBatch(sc,batches,batcServicec);
					break;
				case 0:
					//System.out.println("admin has successfully logout");
					break;
				default:
					throw new IllegalArgumentException("Unexpected value: " + choice);
				}

			} while (choice!=0);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	private static void adminChangeStrengthBatch(Scanner sc, Map<Integer, Batch> batches, BatchService batcServicec) {
		batcServicec.PrintAllBatches(batches);
		System.out.println("Please Enter Id of the Batch");
		int id = sc.nextInt();
		boolean cheak=true;
		for(Map.Entry<Integer, Batch> mp:batches.entrySet()) {
			if(mp.getValue().getId()==id) {
				System.out.println("Please Enter new Strength");
				int nam=sc.nextInt();
				mp.getValue().setStrength(nam);
				cheak=false;
			}
		}
		if(cheak==true) {
			System.out.println("Invalid Id");
		}else {
			System.out.println("Strength Updated Sucessfull");
		}
		
	}
	private static void adminChangeBatchByname(Scanner sc, Map<Integer, Batch> batches, BatchService batcServicec) {
		batcServicec.PrintAllBatches(batches);
		System.out.println("Please Enter Id of the Batch");
		int id = sc.nextInt();
		boolean cheak=true;
		for(Map.Entry<Integer, Batch> mp:batches.entrySet()) {
			if(mp.getValue().getId()==id) {
				System.out.println("Please Enter new name");
				String nam=sc.next();
				mp.getValue().setName(nam);
				cheak=false;
			}
		}
		if(cheak==true) {
			System.out.println("Invalid Id");
		}else {
			System.out.println("Name Updated Sucessfull");
		}
		
	}
	public static void adminSearchInfoBatch(Scanner sc, Map<Integer, Batch> batches, Map<Integer, Courses> courses) {
		int choice = 0;
		try {
			do {
				System.out.println("Press 1 Search Batch by its Name");
				System.out.println("Press 2 Search Batch by Course Name");
				System.out.println("Press 3 Search Batch by Start Date");
				System.out.println("Press 4 Search Batch by Start Date Between Date Range");
				System.out.println("Press 0 to Back");
				choice = sc.nextInt();

				switch (choice) {
				case 1:
					adminSearchBatchByname(sc,batches);
					break;
				case 2:
					adminSearchBatchByCourse(sc,batches,courses);
					break;
				case 3:
					adminSearchBatchByStartDateRange(sc,batches);
					break;
				case 4:
					adminSearchBatchByDateRange(sc,batches);
					break;
				case 0:
					//System.out.println("admin has successfully logout");
					break;
				default:
					throw new IllegalArgumentException("Unexpected value: " + choice);
				}

			} while (choice!=0);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
	}
	public static void adminSearchBatchByDateRange(Scanner sc, Map<Integer, Batch> batches) {
		System.out.println("Please Enter the  Date After Batch Start");
		String dateStart = sc.next();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate startDate = LocalDate.parse(dateStart, formatter);
		
		System.out.println("Please Enter the  Date Before Batch Start");
		String endStart = sc.next();
		
		LocalDate endDate = LocalDate.parse(endStart, formatter);
		boolean cheak=false;
		for(Map.Entry<Integer, Batch> mp:batches.entrySet()) {
			if(mp.getValue().getStartDate().isBefore(endDate)&&mp.getValue().getStartDate().isAfter(startDate)) {
				System.out.println(mp.getValue());
				cheak=true;
			}
		}
		if(cheak==false) {
			System.out.println("There is no batch start on this Date");
		}
		
	}
	public static void adminSearchBatchByStartDateRange(Scanner sc, Map<Integer, Batch> batches) {
		System.out.println("Please Enter the Date for Search batch");
		String dateStart = sc.next();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate startDate = LocalDate.parse(dateStart, formatter);
		boolean cheak=false;
		for(Map.Entry<Integer, Batch> mp:batches.entrySet()) {
			if(mp.getValue().getStartDate().isEqual(startDate)) {
				System.out.println(mp.getValue());
				cheak=true;
			}
		}
		if(cheak==false) {
			System.out.println("There is no batch start on this Date");
		}
		
	}
	public static void adminSearchBatchByCourse(Scanner sc, Map<Integer, Batch> batches, Map<Integer, Courses> courses) {
		System.out.println("Please Enter the Course Name");
		String nameSearched = sc.next();
		int courseid = 0;
		for(Map.Entry<Integer, Courses> mp:courses.entrySet()) {
			if(nameSearched.toLowerCase().equals(mp.getValue().getName())) {
				courseid=mp.getValue().getId();
			}
		}
		if(courseid==0) {
			System.out.println("There is no batch by this course");
		}else {
			for(Map.Entry<Integer, Batch> mv:batches.entrySet()) {
				if(mv.getValue().getCourseId()==courseid) {
					System.out.println(mv.getValue());
				}
			}
		}
		
	}
	public static void adminSearchBatchByname(Scanner sc, Map<Integer, Batch> batches) {
		System.out.println("Please Enter the Batch Name");
		String nameSearched = sc.next();
		for(Map.Entry<Integer, Batch> mp:batches.entrySet()) {
			if(nameSearched.toLowerCase().equals(mp.getValue().getName().toLowerCase())) {
				System.out.println(mp.getValue());
			}
		}
		
	}
	public static void adminUpdateCourseDetail(Scanner sc, Map<Integer, Courses> courses, CourseService courService) {
		
		System.out.println("Please Enter the id of Course");
		int idCourse = sc.nextInt();
		boolean cheak = false;
		Courses cr=null;
		for(Map.Entry<Integer,Courses> me:courses.entrySet()) {
			if(me.getValue().getId()==idCourse) {
				cr=me.getValue();
				cheak=true;
			}
		}
		if(cheak==false) {
			System.out.println("Invalid Id");
		}else {
			System.out.println("Please Enter 1 for Update Fee");
			System.out.println("Please Enter 2 for Update Duration");
			int cho=sc.nextInt();
			if(cho==1) {
				System.out.println("Please Enter New Fee");
				int newFee = sc.nextInt();
				cr.setFee(newFee);
				System.out.println("Fee is Succefully Updated");
			}else if(cho==2) {
				System.out.println("Please Enter New Duration");
				int newDuration = sc.nextInt();
				cr.setDuration(newDuration);
				System.out.println("Duration is Succefully Updated");
			}else {
				throw new IllegalArgumentException("Unexpected value: " + cho);
			}
		}
		
		
		
	}



	public static void adminSearchInformationCourse(Scanner sc, Map<Integer, Courses> courses,
			CourseService courService) {
		int choice = 0;
		try {
			do {
				System.out.println("Press 1 Search Course By name");
				System.out.println("Press 2 Search Course By Duration Range");
				System.out.println("Press 3 Search Course By Fee Range");
				System.out.println("Press 0 to Back");
				choice = sc.nextInt();

				switch (choice) {
				case 1:
					adminSerchByNameCourse(sc, courses, courService);
					//System.out.println(added);
					break;
				case 2:

					adminSerchByDurationCourse(sc,courses);
					break;
				case 3:

					adminSerchByFeeCourse(sc, courses);
					break;
			
				case 0:
//					System.out.println("admin has successfully logout");
					break;
				default:
					throw new IllegalArgumentException("Unexpected value: " + choice);
				}

			} while (choice!=0);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	public static void adminSerchByFeeCourse(Scanner sc, Map<Integer, Courses> courses) throws CourseException {
		// TODO Auto-generated method stub
		System.out.println("Please Enter the Minimium of Fee Range ");
		int a=sc.nextInt();
		System.out.println("Please Enter the Maximum of Fee Range ");
		int b=sc.nextInt();
		boolean cheak=false;
		if (courses != null && courses.size() > 0) {
			for (Map.Entry<Integer, Courses> me : courses.entrySet()) {
				if(me.getValue().getFee()>=a&&me.getValue().getFee()<=b) {
					System.out.println(me.getValue());
					cheak=true;
				}
			}
			if(cheak==false) {
				System.out.println("There is no Course is not found in this Fee Range");
			}

		} else {
			throw new CourseException("There is no courses Available");
		}
	}

	public static void adminSerchByDurationCourse(Scanner sc, Map<Integer, Courses> courses) throws CourseException {
		// TODO Auto-generated method stub
		System.out.println("Please Enter the Minimium(Months) of Duration Range ");
		int a=sc.nextInt();
		System.out.println("Please Enter the Maximum(Months) of Duration Range ");
		int b=sc.nextInt();
		boolean cheak=false;
		if (courses != null && courses.size() > 0) {
			for (Map.Entry<Integer, Courses> me : courses.entrySet()) {
				if(me.getValue().getDuration()>=a&&me.getValue().getDuration()<=b) {
					System.out.println(me.getValue());
					cheak=true;
				}
			}
			if(cheak==false) {
				System.out.println("There is no Course is not found in this Duration Range");
			}

		} else {
			throw new CourseException("There is no courses Available");
		}
		
		
	}

	public static void adminSerchByNameCourse(Scanner sc, Map<Integer, Courses> courses, CourseService courService) throws CourseException {
		// TODO Auto-generated method stub
		System.out.println("Please Enter the name of course");
		String name = sc.next();
		boolean cheak=false;
		if (courses != null && courses.size() > 0) {
			for (Map.Entry<Integer, Courses> me : courses.entrySet()) {
				if(me.getValue().getName().toLowerCase().equals(name.toLowerCase())) {
					System.out.println(me.getValue());
					cheak=true;
				}
			}
			if(cheak==false) {
				System.out.println("Course is not found");
			}

		} else {
			throw new CourseException("There is no courses Available");
		}
		
	}

	static void adminViewAllCourses(Map<Integer, Courses> courses, CourseService courService) throws CourseException {
		// TODO Auto-generated method stub
		courService.viewAllCourses(courses);
		
	}

	public static String adminAddCourse(Scanner sc, Map<Integer,Courses> courses, CourseService courService) {

		String str = null;
		System.out.println("please enter the course details");
		System.out.println("Enter the coures name");
		String name = sc.next();
		System.out.println("Enter the course Fees");
		int fee = sc.nextInt();
		System.out.println("Enter the Duration of the course");
		int duration = sc.nextInt();
		Courses cour  = new Courses(IDGeneration.generateId(),name,fee,duration);
		str = courService.addCoures(cour, courses);
		return str;
	}
	
	public static void adminLogin(Scanner sc) throws InvalidDetailsException  {

		System.out.println("Enter the user name");
		String userName = sc.next();
		System.out.println("Enter the password");
		String password = sc.next();
		if (userName.equals(admin.username) && password.equals(admin.password)) {

			System.out.println("successfully login");
		} else {
			throw new InvalidDetailsException("Invalid Admin Credentials");
		}
	}
	private static void studentForgotPassword(Scanner sc, Map<Integer, Courses> courses, Map<Integer, Batch> batches,
			Map<Integer, Student> students) {
		System.out.println("Enter Your Email id");
		String email = sc.next();
		System.out.println("Enter Your Mobile Number");
		long mNumber = sc.nextLong();
		boolean flag=false;
		for(Map.Entry<Integer, Student> mp:students.entrySet()) {
			
			if(mp.getValue().getEmail().equals(email)&&mp.getValue().getMobileNumber()==mNumber) {
				flag=true;
				System.out.println("Please Enter Your new Password");
				String pass=sc.next();
				mp.getValue().setPassword(pass);
				System.out.println("Password Updated Sucessfully");
				}
		}
		if(flag==false) {
			System.out.println("email or mobile not matched");
		}
		
	}
	

	public static void main(String[] args) throws ClassNotFoundException, IOException, InvalidDetailsException {
		Map<Integer,Courses> courses = FileExist.courseFile();
		Map<Integer,Batch> batches = FileExist.batchFile();
		Map<Integer,Student> students = FileExist.studentFile();
		//System.out.println(courses);
		StudentService stdService = new StudentServiceImpl();
		Scanner sc = new Scanner(System.in);

		System.out.println("Welcome , in Student Registration System");
		try {
			int preference=0;
			do {
				System.out.println("Please enter your preference, " + " '1' --> Admin login "+" '2' --> Student SignUp "+" '3' --> Student Login "+" '4' --> Student Forgot Password "+" '0' --> Existed from the system");
				preference=sc.nextInt();
				switch (preference) {
				case 1:
					adminFunctionality(sc, courses,batches);
					break;
				case 2:
					stdService.studentSignUpFunctionality(sc,courses,batches,students);
					break;
				case 3:
					studentLogInFunctionallity(sc,courses,batches,students);
					break;
				case 4:
					studentForgotPassword(sc,courses,batches,students);
					break;

				case 0:
					System.out.println("successfully existed from the system");

					break;

				default:
					throw new IllegalArgumentException("Invalid Selection");
				}
				
				
			}while(preference!=0);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			// serialization (finally always executed)
			try {
				ObjectOutputStream poos = new ObjectOutputStream(new FileOutputStream("Courses.ser"));
				poos.writeObject(courses);
				
				ObjectOutputStream boos = new ObjectOutputStream(new FileOutputStream("Batch.ser"));
				boos.writeObject(batches);
				
				ObjectOutputStream anna = new ObjectOutputStream(new FileOutputStream("Students.ser"));
				anna.writeObject(students);
				System.out.println("Serialized......................");
			
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
		}	
	}
}
