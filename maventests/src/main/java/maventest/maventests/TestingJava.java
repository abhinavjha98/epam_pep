package maventest.maventests;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
 
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
public class TestingJava {
	public int square(int x) {
		return x*x;
	}
	private String developer = "Vinod";
	 
    @Test
    public void instanceOfTest() {
        assertThat(new ArrayList(), instanceOf(List.class));
    }
 
    @Test
    public void assertTrueTest() {
        assertTrue(true);
    }
 
    @Test
    public void equalToTest() {
        assertThat(developer, is("Vinod"));
    }
     
    @Test
    public void failTest() {
        assertThat(developer, is("Any"));
    }
}
