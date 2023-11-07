package com.dillo.SMS;

import static org.junit.Assert.*;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import jpa.entitymodels.Student;
 

public class StudentServiceTest {

    @Test
    public void createStudent_shouldSuccessfullyCreateStudent() {
        // Create a mock SessionFactory object
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();

        // Create a new student object
        Student student = new Student("pedro@mail.com", "Pedro Delgadillo", "1234");

        // Save the student to the database
        session.beginTransaction();
        session.save(student);
        session.getTransaction().commit();

        // Retrieve the student from the database
        Student retrievedStudent = session.get(Student.class, student.getEmail());

        // Verify that the retrieved student is equal to the created student
        assertNotNull(retrievedStudent);
        assertEquals(student.getEmail(), retrievedStudent.getEmail());
        assertEquals(student.getName(), retrievedStudent.getName());
        assertEquals(student.getPassword(), retrievedStudent.getPassword());

        // Close the session
        session.close();
    }
}