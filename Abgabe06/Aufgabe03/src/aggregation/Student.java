package aggregation;

import java.util.ArrayList;
import java.util.List;

import both.Lecture;

public class Student {
	
	private List<Lecture> lectures = new ArrayList<>();

	public void addLecture(Lecture lecture) {

		if(!lectures.contains(lecture)) {
			lectures.add(lecture);
			lecture.addStudent(this);
		}
		
	}
	
	public void removeLecture(Lecture lecture) {
		if(lectures.contains(lecture)) {
			lectures.remove(lecture);
			lecture.removeStudent(this);
		}
	}
	
	

}
