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
import com.masai.utility.admin;
import com.masai.utility.FileExist;
import com.masai.utility.IDGeneration;


public class Main {
	private static void studentSignUpFunctionality(Scanner sc, Map<Integer, Courses> courses,
			Map<Integer, Batch> batches, Map<Integer, Student> students) {
		// TODO Auto-generated method stub
		
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
					adminSearchInfoBatch(sc,batches);
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
	public static void adminSearchInfoBatch(Scanner sc, Map<Integer, Batch> batches) {
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
					adminSearchBatchByCourse(sc,batches);
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
	public static void adminSearchBatchByCourse(Scanner sc, Map<Integer, Batch> batches) {
		System.out.println("Please Enter the Course Name");
		String nameSearched = sc.next();
		for(Map.Entry<Integer, Batch> mp:batches.entrySet()) {
			if(nameSearched.toLowerCase().equals(mp.getValue().getCourse().getName())) {
				System.out.println(mp.getValue());
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

	public static void main(String[] args) throws ClassNotFoundException, IOException, InvalidDetailsException {
		Map<Integer,Courses> courses = FileExist.courseFile();
		Map<Integer,Batch> batches = FileExist.batchFile();
		Map<Integer,Student> students = FileExist.studentFile();
		//System.out.println(courses);
		
		Scanner sc = new Scanner(System.in);

		System.out.println("Welcome , in Student Registration System");
		try {
			int preference=0;
			do {
				System.out.println("Please enter your preference, " + " '1' --> Admin login "+" '2' --> Student SignUp "+" '3' --> Student Login "+" '0' --> Existed from the system");
				preference=sc.nextInt();
				switch (preference) {
				case 1:
					adminFunctionality(sc, courses,batches);
					break;
				case 2:
					studentSignUpFunctionality(sc,courses,batches,students);
					break;
				case 3:
					//customerSignup(sc, customers);
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
				System.out.println("Products Added to list finaly");
				ObjectOutputStream boos = new ObjectOutputStream(new FileOutputStream("Batch.ser"));
				boos.writeObject(batches);
				
				ObjectOutputStream anna = new ObjectOutputStream(new FileOutputStream("Students.ser"));
				anna.writeObject(students);
				
			
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
		}
		
		
		
	}
	
	
}
