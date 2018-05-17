package komposition;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lecture {
	
	private List<Session> sessions = new ArrayList<>();
	
	public boolean isValid() {
		return !sessions.isEmpty();
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
