package jpa.entitymodels;

import javax.persistence.*;


@Entity
@Table(name = "course")
public class Course {
	@Id
	@Column(name = "id")
	private int Id;
	@Column(name = "name")
	private String cName;

	@Column(name = "instructor")
	private String cInstructorName;


	@Override
	public String toString() {
		return "Course [Id=" + Id + ", cName=" + cName + ", cInstructorName=" + cInstructorName + "]";
	}

	public Course() {
	};

	public Course(String cName, String cInstructorName) {
		super();
		this.cName = cName;
		this.cInstructorName = cInstructorName;
	}

	public int getId() {
		return Id;
	}

	public void setId(int Id) {
		this.Id = Id;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getcInstructorName() {
		return cInstructorName;
	}

	public void setcInstructorName(String cInstructorName) {
		this.cInstructorName = cInstructorName;
	}

}
