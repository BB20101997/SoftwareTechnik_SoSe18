package aggregation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lecture {
	
	private 
	List<Student> participants = new ArrayList<>();
	
	public boolean isValid() {
		return participants.size()>=5;
	}
	
	public void addStudent(Student stu) {
		if(!participants.contains(stu)) {
			participants.add(stu);
			stu.addLecture(this);
		}
	}

	public void removeStudent(Student student) {
		if(participants.contains(student)) {
			participants.remove(student);
			student.removeLecture(this);
		}
	}

	List<Student> getParticipants() {
		return Collections.unmodifiableList(participants);
	}
	
}
