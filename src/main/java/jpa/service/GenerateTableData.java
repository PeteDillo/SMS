package jpa.service;

import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class GenerateTableData {

   public void createStudentAndCourses() {
       Session session = ConnectionService.getConnection();
       Transaction t = session.beginTransaction();

       Course course1= new Course("Java","Kay");

        session.persist(course1);

    Student s1 = new Student("pedro@mail.com", "Pedro Del", "1234");
       session.persist(s1);
       t.commit();

   }

}
