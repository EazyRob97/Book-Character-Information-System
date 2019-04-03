package application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BookCharacterTests {

	BookCharacter character1 = null;
	
	

//	@AfterAll
//	static void tearDownAfterClass() throws Exception {
//	}

	@BeforeEach
	void setUp() throws Exception {
		character1 = new BookCharacter("Leo","Male","Horrors");
	
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	public void testCharConstructor() {
		assertEquals("Leo", character1.getName());
		assertEquals("Male", character1.getGender());
		assertEquals("Horrors", character1.getDescription());
	}
	
	@Test
	public void testSetName() {
		assertEquals("Leo", character1.getName());
		character1.setName("Rob");
		assertEquals("Rob", character1.getName());
	}

}
