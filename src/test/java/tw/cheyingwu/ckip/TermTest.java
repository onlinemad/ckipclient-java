package tw.cheyingwu.ckip;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TermTest {
	
	private Term t;
	
	@Before
	public void init(){
		t = new Term();
		t.setTerm("測試");
		t.setTag("n");
	}
	
	@Test
	public void testSetTerm() {
		t.setTerm("活動");
		assertEquals("活動",t.getTerm());
	}

	@Test
	public void testGetTerm() {
		assertEquals("測試",t.getTerm());
	}

	@Test
	public void testSetTag() {
		t.setTag("v");
		assertEquals("v",t.getTag());
	}

	@Test
	public void testGetTag() {
		assertEquals("n",t.getTag());
	}

}
