package jpa.entitymodels;

import java.util.Set;

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

	
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "student_course",
        joinColumns = @JoinColumn(name = "email"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
	private Set<Course> courses;

	public Student() {

	}

	public Student(String email, String sName, String password, Set<Course> courses) {
		super();
		this.email = email;
		this.sName = sName;
		this.password = password;
		this.courses = courses;
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

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

}
