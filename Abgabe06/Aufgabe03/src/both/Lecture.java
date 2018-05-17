package both;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import aggregation.Student;
import komposition.Session;

public class Lecture {
	
	List<Student> participants = new ArrayList<>();
	private List<Session> sessions = new ArrayList<>();
	
	private boolean isValid() {
		return participants.size()>=5&&!sessions.isEmpty();
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
	
	public void addSession(Session ses) {
		sessions.add(ses);
	}
	
	public void removeSession(Session ses) {
		sessions.remove(ses);
	}
	
	public List<Session> getSessions(){
		return Collections.unmodifiableList(sessions);
	}
	
}
