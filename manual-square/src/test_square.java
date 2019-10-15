import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class test_square {

	@Test
	void test() {
		square a = new square();
		int result = a.square_number(5);
		assertEquals(26,result );
	}

}
