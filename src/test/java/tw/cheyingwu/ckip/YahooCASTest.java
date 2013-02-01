package tw.cheyingwu.ckip;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * DEPRECATED
 * 
 * Will delete at next release.
 * 
 * @author onlinemad
 * @deprecated
 */
public class YahooCASTest {

	private WordSegmentationService service;

	@Before
	public void init() {

		//TODO You should change this before you run a test.
		this.service = new YahooCAS("appid");
		
		String testString = "台新金控12月3日將召開股東臨時會進行董監改選。";

		this.service.setRawText(testString);

		System.out.println("=== Getting start test ===");
	}

	@Test
	public void testGetTerm() {
		System.out.println("=== testGetTerm() ===");
		this.service.send();
		assertNotNull(this.service.getReturnText());
		for (Term t : this.service.getTerm()) {
			System.out.println(t.getTerm() + "\t" + t.getTag());
		}

	}

	@Test
	public void testSend() {
		System.out.println("=== testSend() ===");
		this.service.send();
		assertNotNull(this.service.getReturnText());
		System.out.println("send OK");
	}

	@Test
	public void testgetReturnText() {
		System.out.println("=== testgetReturnText() ===");
		this.service.send();
		assertNotNull(this.service.getReturnText());
		System.out.println(this.service.getReturnText());
	}
	
}
