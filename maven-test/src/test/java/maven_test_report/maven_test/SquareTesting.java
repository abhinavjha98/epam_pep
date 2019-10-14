package maven_test_report.maven_test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SquareTesting {

	@Test
	void test() {
		SquareTest test = new SquareTest();
		int output = test.square(5);
		assertEquals(25, output);
	}

}
