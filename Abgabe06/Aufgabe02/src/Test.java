import javax.naming.AuthenticationException;

import auth.AuthImpl;

public class Test {

	public static void main(String[] args) {
		
		//If a test fails further test may produce incorrect results
		boolean success = true;
		
		//test singelton
		if(WebstoreImpl.getWebstore()!=WebstoreImpl.getWebstore()) {
			System.err.println("Singelton Pattern returnt multiple instances!");
			success = false;
		}
		
		//register user
		WebstoreImpl.getWebstore().register("test", "passwd");
		
		if(null!=WebstoreImpl.getWebstore().login("test", "wrong")) {
			System.err.println("Login succeded with wrong password!");
			success = false;
		}
		
		String session = WebstoreImpl.getWebstore().login("test","passwd");
		
		if(null==session) {
			System.err.println("Login failed with correct password!");
			success = false;
		}
		
		//test cart with valid session
		
		try {
			WebstoreImpl.getWebstore().addItemToCart(session, "test", "42");
			if(!WebstoreImpl.getWebstore().getCartItems(session, "test").contains("42")) {
				System.err.println("Item not added to Cart!");
				success = false;
			}
			WebstoreImpl.getWebstore().removeItemFromCart(session, "test", "42");
			if(WebstoreImpl.getWebstore().getCartItems(session, "test").contains("42")) {
				System.err.println("Item not removed from Cart!");
				success = false;
			}
		}catch(AuthenticationException ignore){
			System.err.println("Valid session caused AuthenticationException!");
			success = false;
		}
		
		//test logout
		
		WebstoreImpl.getWebstore().logout("test");
		if(AuthImpl.getAuth().isUserLoggedIn(session)) {
			System.err.println("Session still valid after logout!");
			success = false;
		}
		
		//test cart with invalid session
		try {
			WebstoreImpl.getWebstore().addItemToCart(session, "test", "42");
			System.err.println("Add to Cart sussesfull with invalid session!");
			success = false;
		}catch(AuthenticationException ignore){}
		
		try {
			WebstoreImpl.getWebstore().getCartItems(session, "test");
			System.err.println("Get Cart sussesfull with invalid session!");
			success = false;
		}catch(AuthenticationException ignore){}
		
		try {
			WebstoreImpl.getWebstore().removeItemFromCart(session, "test", "42");
			System.err.println("Remove from Cart sussesfull with invalid session!");
			success = false;
		}catch(AuthenticationException ignore){}
		
		
		if(!success) {
			//exit with none 0 exit value to indicate failure
			System.exit(1);
		}

	}

}
