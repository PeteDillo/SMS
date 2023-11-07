package jpa.mainrunner;

import jpa.service.GenerateTableData;
import jpa.service.StudentService;
import jpa.dao.StudentDAO;

public class AppMethod {
	public static void main(String[] args) {
		GenerateTableData obj = new GenerateTableData();

		StudentDAO obj1 = new StudentService();

		obj.createStudentAndCourses();
		obj1.registerStudentToCourse("pedro@mail.com", 5);

	}
}
