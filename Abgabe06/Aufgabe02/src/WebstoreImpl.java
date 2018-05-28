import java.util.List;

import javax.naming.AuthenticationException;

import auth.AuthImpl;
import cart.CartImpl;
import persistence.PersistenceImpl;
import user.UserImpl;
import webstore.IWebstore;

public class WebstoreImpl implements IWebstore{
	
	private static WebstoreImpl instance = null;
	
	/**
	 * Is there no way to check if a Session belongs to a user?
	 * If no this means every user can edit and see every other users cart
	 * */
	
	public static IWebstore getWebstore() {
		if(instance==null) {
			synchronized (WebstoreImpl.class) {
				//test again another threat may have done this already
				if(instance==null) {
					instance = new WebstoreImpl();
					AuthImpl.setPersistence(PersistenceImpl.getPersistence());
					UserImpl.setPersistence(PersistenceImpl.getPersistence());
					CartImpl.setPersistence(PersistenceImpl.getPersistence());
				}
			}
		}
		return instance;
	}
	
	private WebstoreImpl() {}

	@Override
	public void addItemToCart(String sessionID, String username, String item) throws AuthenticationException {
		if(!AuthImpl.getAuth().isUserLoggedIn(sessionID)) {
			throw new AuthenticationException("Login Required!");
		}
		CartImpl.getCart().addItemToCart(username, item);
	}

	@Override
	public List<String> getCartItems(String sessionID, String username) throws AuthenticationException {
		if(!AuthImpl.getAuth().isUserLoggedIn(sessionID)) {
			throw new AuthenticationException("Login Required!");
		}
		
		return CartImpl.getCart().getCartItems(username);
	}

	@Override
	public String login(String username, String password) {
		return AuthImpl.getAuth().login(username, password);	
	}

	@Override
	public void logout(String username) {
		AuthImpl.getAuth().logout(username);
	}

	@Override
	public void register(String username, String password) {
		UserImpl.getUser().register(username, password);
	}

	@Override
	public void removeItemFromCart(String sessionID, String username, String item) throws AuthenticationException {
		if(!AuthImpl.getAuth().isUserLoggedIn(sessionID)) {
			throw new AuthenticationException("Login required!");
		}
		CartImpl.getCart().removeItemFromCart(username, item);
	}
	
}
