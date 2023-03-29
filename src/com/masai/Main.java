package com.masai;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.Scanner;

import com.masai.entities.Courses;
import com.masai.exception.CourseException;
import com.masai.exception.InvalidDetailsException;
import com.masai.services.CourseService;
import com.masai.services.CourseServiceImpl;
import com.masai.utility.admin;
import com.masai.utility.FileExist;
import com.masai.utility.IDGeneration;


public class Main {
	
	public static void adminFunctionality(Scanner sc,Map<Integer,Courses> courses) throws InvalidDetailsException {
		adminLogin(sc);
		
		CourseService courService = new CourseServiceImpl();
		int choice = 0;
		try {
			do {
				System.out.println("Press 1 add the course");
				System.out.println("Press 2 view all courses");
				System.out.println("Press 3 Search for information about courses");
				System.out.println("Press 4  Update details of course");
				System.out.println("Press 5 Create new batch Under Course");
//				System.out.println("Press 6 to view all transactions");
				System.out.println("Press 7 to log out");
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
					//adminCreateBatch(sc,courses, courService);

					break;
				case 6:
					//adminViewAllTransactions(transactions, trnsactionService);
					break;
				case 7:
					System.out.println("admin has successfully logout");
					break;
				default:
					throw new IllegalArgumentException("Unexpected value: " + choice);
				}

			} while (choice <= 6);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	


	public static void adminUpdateCourseDetail(Scanner sc, Map<Integer, Courses> courses, CourseService courService) {
		// TODO Auto-generated method stub
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
		//System.out.println(courses);
		
		Scanner sc = new Scanner(System.in);

		System.out.println("Welcome , in Student Registration System");
		try {
			int preference=0;
			do {
				System.out.println("Please enter your preference, " + " '1' --> Admin login ");
				preference=sc.nextInt();
				switch (preference) {
				case 1:
					adminFunctionality(sc, courses);
					break;
				case 2:
					//customerFunctionality(sc, customers, products, transactions);
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
			
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
		}
		
		
		
	}
	
}
