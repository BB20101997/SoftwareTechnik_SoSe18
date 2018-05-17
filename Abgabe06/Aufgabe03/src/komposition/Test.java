package komposition;

import java.util.Date;

public class Test {

	public static void main(String[] args) {

		Lecture l = new Lecture();
		
		if(l.isValid()) {
			System.err.println("Lecture with no Session was valid!");
		}
		
		Session s = new Session(new Date());
		
		l.addSession(s);
		
		if(!l.isValid()) {
			System.err.println("Lecture with Session was invalid!");
		}
		
		if(!l.getSessions().contains(s)) {
			System.err.println("Session add failed!");
		}
		
		l.removeSession(s);
		
		if(!l.getSessions().isEmpty()) {
			System.err.println("Session remove failed!");
		}
		
	}

}
