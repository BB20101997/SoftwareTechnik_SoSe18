import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Random;

import javax.naming.AuthenticationException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opentest4j.TestAbortedException;

import webstore.IWebstore;
import webstore.WebstoreImpl;

public class WebstoreTest {

	private static IWebstore webstore;
	private static Random rand;

	private String test_user;
	private String test_password;
	private String test_session;

	@BeforeAll
	public static void setup() {
		rand = new Random();
		webstore = WebstoreImpl.getWebstore();
	}

	@BeforeEach
	public void setupEach() {
		// new user for each test
		// should be random enough to not cause collisions
		test_user = randomString(20);
		test_password = randomString(20);
		webstore.register(test_user, test_password);
		test_session = webstore.login(test_user, test_password);
	}

	@AfterEach
	public void cleanUpEach() {
		// logout after each test
		webstore.logout(test_session);
	}

	private static String randomString(int length) {
		return rand.ints('A','z'+1).limit(length).mapToObj(d -> String.valueOf((char) d))
				.reduce((a, b) -> a + b)
				.orElseThrow(() -> new TestAbortedException("Couldn't generate random String!"));
	}

	@Test
	public void testSingelton() {
		// Test that the Singleton getter return the same Instance for multiple
		// invocations
		Assertions.assertEquals(webstore, WebstoreImpl.getWebstore(), "Singelton returned two different instances!");
	}

	@Test
	public void login() {
		// should be logged in, so session should not be null
		assertNotNull(test_session, "Login or register failed!");
	}

	@Test
	public void startOnEmptyCart() throws AuthenticationException {
		// since each test is run with a new user expecting empty cart,
		assertTrue(webstore.getCartItems(test_session, test_user).isEmpty(), "Initial Cart not empty!");
	}

	@Test
	public void addItemToCart() throws AuthenticationException {
		// is added Item in cart after adding
		String test_item = randomString(20);
		webstore.addItemToCart(test_session, test_user, test_item);
		assertTrue(webstore.getCartItems(test_session, test_user).contains(test_item), "Added Item missing from Cart!");
	}

	@Test
	public void removeItemFromCart() throws AuthenticationException {
		// is item gone after removing
		String test_item = randomString(20);
		webstore.addItemToCart(test_session, test_user, test_item);
		webstore.removeItemFromCart(test_session, test_user, test_item);
		assertFalse(webstore.getCartItems(test_session, test_user).contains(test_item), "Removed Item still in Cart");
	}

	@Test
	public void getCartInvalidSession() {
		// correct exception when using invalid session
		webstore.logout(test_session);
		assertThrows(AuthenticationException.class, () -> webstore.getCartItems(test_session, test_user),
				"Getting Cart with invalid Session didn't throw the correct Exception!");
	}

	@Test
	public void addCartInvalidSession() throws AuthenticationException {
		// correct exception when using invalid session and item not in cart
		webstore.logout(test_session);
		String test_item = randomString(20);
		assertThrows(AuthenticationException.class, () -> webstore.addItemToCart(test_session, test_user, test_item),
				"Adding to Cart with invalid Session didn't throw the correct Exception!");
		test_session = webstore.login(test_user, test_password);
		assertFalse(webstore.getCartItems(test_session, test_user).contains(test_item),
				"Adding to Cart with invalid Session did add Item to Cart!");
	}

	@Test
	public void removeCartInvalidSession() throws AuthenticationException {
		// correct exception when using invalid session and item still in cart
		String test_item = randomString(20);
		webstore.addItemToCart(test_session, test_user, test_item);
		webstore.logout(test_session);
		assertThrows(AuthenticationException.class,
				() -> webstore.removeItemFromCart(test_session, test_user, test_item),
				"Removing from Cart with invalid Session didn't throw the correct Exception!");
		test_session = webstore.login(test_user, test_password);
		assertTrue(webstore.getCartItems(test_session, test_user).contains(test_item),
				"Removing from Cart with invalid Session did remove Item from Cart!");
	}

	@Test
	public void getCartNullSession() {
		// correct exception when using invalid session
		try {
			webstore.getCartItems(null, test_user);
		} catch (Exception ignore) {
			return;
		}
		fail("null accepted as Session");
	}

	@Test
	public void addCartNullSession() throws AuthenticationException {
		// correct exception when using invalid session and item not in cart
		String test_item = randomString(20);
		try {
			webstore.addItemToCart(null, test_user, test_item);
		} catch (Exception ignore) {
			System.out.println(test_item);
			webstore.getCartItems(test_session, test_user).forEach(System.out::println);
			assertFalse(webstore.getCartItems(test_session, test_user).contains(test_item),"Adding to Cart with null Session did add Item to Cart!");
			return;
		}
		assertFalse(webstore.getCartItems(test_session, test_user).contains(test_item),"Adding to Cart with null Session did add Item to Cart!");
		fail("null accepted as Session");
	}

	@Test
	public void removeCartNullSession() throws AuthenticationException {
		// correct exception when using invalid session and item still in cart
		String test_item = randomString(20);
		webstore.addItemToCart(test_session, test_user, test_item);
		try {
			webstore.removeItemFromCart(null, test_user, test_item);
		} catch (Exception ignore) {
			assertTrue(webstore.getCartItems(test_session, test_user).contains(test_item),"Removing from Cart with null Session did remove Item from Cart!");
			return;
		}
		assertTrue(webstore.getCartItems(test_session, test_user).contains(test_item),"Removing from Cart with null Session did remove Item from Cart!");
		fail("null accepted as Session");
	}

}
