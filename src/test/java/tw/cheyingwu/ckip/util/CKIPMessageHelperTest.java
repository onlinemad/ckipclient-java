package tw.cheyingwu.ckip.util;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class CKIPMessageHelperTest {

	@Test
	public void testWarp() {
		String expectXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<wordsegmentation version=\"0.1\"><option showcategory=\"1\"/><authentication username=\"username\" password=\"password\"/><text>台新金控12月3日將召開股東臨時會進行董監改選。</text></wordsegmentation>";
		String warpedXML = CKIPMessageHelper.warp("username", "password", "台新金控12月3日將召開股東臨時會進行董監改選。");
		assertEquals(expectXML, warpedXML);
	}

	@Test
	public void testParse() {
		String sampleXML = "<?xml version=\"1.0\" ?><wordsegmentation version=\"0.1\"><processstatus code=\"0\">Success</processstatus><result><sentence>　台新(N)　金控(N)　12月(N)　3日(N)　將(ADV)　召開(Vt)　股東(N)　臨時會(N)　進行(Vt)　董監(N)　改選(Nv)　。(PERIODCATEGORY)</sentence></result></wordsegmentation>";
		String[] expectArr = {"台新", "金控", "12月", "3日", "將", "召開", "股東", "臨時會", "進行", "董監", "改選", "。"};
		List<String> parsedArr = CKIPMessageHelper.parse(sampleXML);
		assertArrayEquals(expectArr, parsedArr.toArray(new String[parsedArr.size()]));
	}
	
	@Test
	public void testParseMutisentence() {
		String sampleXML = "<?xml version=\"1.0\" ?><wordsegmentation version=\"0.1\"><processstatus code=\"0\">Success</processstatus><result><sentence>　台新(N)　金控(N)　12月(N)　3日(N)　將(ADV)　召開(Vt)　股東(N)　臨時會(N)　進行(Vt)　董監(N)　改選(Nv)　。(PERIODCATEGORY)</sentence><sentence>　中華隊(N)　的(T)　未來(N)　還是(ADV)　充滿(Vt)　了(ASP)　希望(Vt)　。(PERIODCATEGORY)</sentence><sentence>　美國(N)　經濟(N)　復甦(Vi)　速度(N)　減緩(Vt)</sentence></result></wordsegmentation>";
		String[] expectArr = {"台新", "金控", "12月", "3日", "將", "召開", "股東", "臨時會", "進行", "董監", "改選", "。", "中華隊", "的", "未來", "還是", "充滿", "了", "希望", "。", "美國", "經濟", "復甦", "速度", "減緩"};
		List<String> parsedArr = CKIPMessageHelper.parse(sampleXML);
		assertArrayEquals(expectArr, parsedArr.toArray(new String[parsedArr.size()]));
	}	
}
