package jpa.entitymodels;

import java.util.HashSet;
import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student {
	@Id
	@Column(name = "email", length = 50, nullable = false)
	private String email;
	@Column(name = "name", length = 50, nullable = false)
	private String sName;
	@Column(name = "password", length = 50, nullable = false)
	private String password;

	
    @JoinTable(
        name = "student_course",
        joinColumns = {@JoinColumn(name = "email")},
        inverseJoinColumns = {@JoinColumn(name = "course_id")}
    )
	private HashSet<Course> courses;

	public Student() {

	}

	public Student(String email, String sName, String password) {
		super();
		this.email = email;
		this.sName = sName;
		this.password = password;
		this.courses = new HashSet<>();
	}


	@Override
	public String toString() {
		return "Student [email=" + email + ", sName=" + sName + ", password=" + password + ", courses=" + courses + "]";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return sName;
	}

	public void setName(String sName) {
		this.sName = sName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String sPass) {
		this.password = sPass;
	}

	public HashSet<Course> getCourses() {
		return courses;
	}

	public void setCourses(HashSet<Course> courses) {
		this.courses = courses;
	}

}
