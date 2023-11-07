package jpa.mainrunner;

import java.util.List;
import java.util.Scanner;

import jpa.entitymodels.Course;
import jpa.service.CourseService;
import jpa.service.StudentService;

public class SMSRunner {
	/*
	 * having issues with class registration creating new column in student table and saving courses there
	 * */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		StudentService sS = new StudentService();
		CourseService cS = new CourseService();
		int cId=0;

		System.out.println("Are you a\n1. Student\n2. Quit\n");
		int selection = scanner.nextInt();
		switch (selection) {
		case 1:
			System.out.println("Enter your Email");
			String email = scanner.next();
			System.out.println("Enter your Password");
			String password = scanner.next();
			if (sS.validateStudent(email, password) == true) {
				List<Course> courses = cS.getAllCourses();
				for(Course course : courses) {
					System.out.println(" ID "+course.getId()+ " Course Name "+ course.getcName()+ " Instructor "+ course.getcInstructorName());
				}
				System.out.println("Which Course for registration?");
				cId = scanner.nextInt();
				sS.registerStudentToCourse(email, cId);
				System.out.println("you have been registered");
				sS.getStudentCourses(email);
				System.out.println("You have been logged out!");
			} else {
				System.out.println("Invalid credentials, Try Again!");
			}
			break;
		case 2:
			System.out.println("Goodbye!");
			scanner.close();
			break;
		default:
			System.out.println("Closing Program, Try Again!");
			scanner.close();
			break;
		}
	}

}
