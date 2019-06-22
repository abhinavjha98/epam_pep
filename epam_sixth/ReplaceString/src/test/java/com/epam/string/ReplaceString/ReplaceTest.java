package com.epam.string.ReplaceString;

import static org.junit.Assert.*;

import org.junit.Test;

public class ReplaceTest {

	@Test
	public void test() {
		ReplaceString junit = new ReplaceString();
		String result = junit.ReplaceAA("AABAA");
		assertEquals("BAA", result);
	}

}
