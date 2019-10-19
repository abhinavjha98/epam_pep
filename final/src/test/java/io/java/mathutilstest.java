package io.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class mathutilstest {

	@Test
	void test() {
		mathutils test = new mathutils();
		int result = test.add(5, 10);
		assertEquals(10, result);
	}

}
