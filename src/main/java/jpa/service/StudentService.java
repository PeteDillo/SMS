package jpa.service;

import java.util.HashSet;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.HibernateException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import jpa.dao.StudentDAO;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;

public class StudentService implements StudentDAO {

	@Override
	public List<Student> getAllStudents() {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		String hql = "FROM Student";
		TypedQuery<Student> query = session.createQuery(hql, Student.class);
		List<Student> results = query.getResultList();
		session.close();
		return results;
	}

	@Override
	public Student getStudentByEmail(String sEmail) {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		String hql = "FROM Student WHERE email = :sEmail";
		TypedQuery<Student> query = session.createQuery(hql, Student.class);
		query.setParameter("sEmail", sEmail);
		Student student = query.getSingleResult();
		session.close();
		return student;
	}

	@Override
	public boolean validateStudent(String sEmail, String sPassword) {
		try (SessionFactory factory = new Configuration().configure().buildSessionFactory();
				Session session = factory.openSession();) {
			String hql = "FROM Student s WHERE s.email = :email AND s.password = :password";
			TypedQuery<Student> query = session.createQuery(hql, Student.class);
			query.setParameter("email", sEmail);
			query.setParameter("password", sPassword);
			return query.getSingleResult() != null;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void registerStudentToCourse(String sEmail, int cId) {
		Transaction transaction = null;
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		try (Session session = factory.openSession();) {
			transaction = session.beginTransaction();
			Student student = session.get(Student.class, sEmail);
			Course course = session.get(Course.class, cId);
			if (student != null && course != null) {
				if (student.getCourses() == null) {
				    student.setCourses(new HashSet<Course>());
				}
				student.getCourses().add(course);
                session.saveOrUpdate(student);
				transaction.commit();
			} else {
				if (transaction != null && transaction.isActive()) {
					transaction.rollback();
				}
			}
		}
	}

	@Override
	public List<Course> getStudentCourses(String sEmail) {
		try (SessionFactory factory = new Configuration().configure().buildSessionFactory();
				Session session = factory.openSession();) {
			String hql = "SELECT c FROM Student s JOIN s.courses c WHERE s.email = :email";
			TypedQuery<Course> query = session.createQuery(hql, Course.class);
			query.setParameter("email", sEmail);
			return query.getResultList();
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}

	}

}
