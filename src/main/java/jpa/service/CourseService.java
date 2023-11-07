package jpa.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import jpa.dao.CourseDAO;
import jpa.entitymodels.Course;

public class CourseService implements CourseDAO{

    @Override
    public List<Course> getAllCourses() {
    	SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
        try {
            session.beginTransaction();

            String hql = "FROM Course";
            List<Course> courses = session.createQuery(hql, Course.class).list();

            session.getTransaction().commit();

            return courses;
        } finally {
            if (session != null && session.isOpen()) {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
                session.close();
            }
        }
    }


}
