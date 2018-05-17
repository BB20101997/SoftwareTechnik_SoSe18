package aggregation;

public class Test {

	public static void main(String[] args) {
		Lecture l = new Lecture();
		
		for(int i = 0;i<4;i++) {
			l.addStudent(new Student());
			//test if lecture with less than 5 students is invalid
			if(l.isValid()) {
				System.err.println("Lecture with to few ("+l.getParticipants().size()+") Students is valid!");
			}
		}
		
		Student s = new Student();
		l.addStudent(s);
		//test if lecture with 5 or more Students is valid
		if(!l.isValid()) {
			System.err.println("Lecture with enought Students is invalid!");
		}
		
		//check if all participants take the lecture
		for(Student stu:l.getParticipants()) {
			if(!stu.isInLecture(l)) {
				System.err.println("1)Missmatch between Lecture and Student!");
			}
		}
		
		l.removeStudent(s);
		//has s been updated on remove
		if(s.isInLecture(l)) {
			System.err.println("2)Missmatch between Lecture and Student!");
		}
		//has l been updated on add
		s.addLecture(l);
		if(!l.getParticipants().contains(s)) {
			System.err.println("3)Missmatch between Lecture and Student!");
		}
		
		//has l been updated on remove
		s.removeLecture(l);
		if(l.getParticipants().contains(s)) {
			System.err.println("4)Missmatch between Lecture and Student!");
		}
		
		
	}

}
