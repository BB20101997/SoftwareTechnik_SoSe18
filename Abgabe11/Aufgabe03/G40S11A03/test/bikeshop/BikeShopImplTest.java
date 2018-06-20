package bikeshop;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.mockito.Mockito;

import persistence.IPersistence;

public final class BikeShopImplTest {

	/**
	 * The bike shop component should not re-throw exceptions from the persistence component. Instead the bike shop should simply return an "Error" string.
	 * 
	 */
	@Test
	public void exceptionFromPersistenceShouldResultInErrorString() throws SQLException {
		IPersistence mock = Mockito.mock(IPersistence.class);
		Mockito.when(mock.getAvailableBikes()).thenThrow(new SQLException());
		IBikeShop shop = new BikeShopImpl(mock);
		
		assertEquals("Error", shop.getAvailableBikesAsHTML());
	}

	/**
	 * The bike shop component should deliver "N/A" when the bike list is empty. 
	 */
	@Test
	public void emptyListFromPersistenceShouldResultInNAString() throws SQLException {
		IPersistence mock = Mockito.mock(IPersistence.class);
		Mockito.when(mock.getAvailableBikes()).thenReturn(Collections.emptyList());
		
		IBikeShop shop = new BikeShopImpl(mock);
		assertEquals("N/A", shop.getAvailableBikesAsHTML());}

	/**
	 * The bike shop component should deliver a valid HTML list for a common case (e.g., a single bike in the list).
	 */
	@Test
	public void commonCaseShouldWork() throws SQLException {
		Random rand = new Random();
		
		IPersistence mock = Mockito.mock(IPersistence.class);
		
		List<String> bikes = Stream.generate(()->rand.ints('A','Z'+1).limit(20).mapToObj(i->String.valueOf((char)i)).collect(Collectors.joining()))
				.limit(rand.nextInt(10)+1).collect(Collectors.toList());
		
		Mockito.when(mock.getAvailableBikes()).thenReturn(bikes);
		String html = new BikeShopImpl(()->bikes).getAvailableBikesAsHTML();
		
		assertTrue(html.startsWith("<ul>"));
		assertTrue(html.endsWith("</ul>"));
		
		for(String bike : bikes) {
			if(!html.matches(".*<li>\\s*"+Pattern.quote(bike)+"\\s*<\\/li>.*")) {
				fail("Expected Bike \""+bike+"\" in HTML");
			}
		}
		
	}

}
