package tw.cheyingwu.ckip.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import tw.cheyingwu.ckip.Term;

public class CKIPMessageHelper {
	public static String warp(String username, String password, String text) {
		// create XML format message
		Document d = DocumentHelper.createDocument();

		Element root = d.addElement("wordsegmentation");
		root.addAttribute("version", "0.1");
		root.addElement("option").addAttribute("showcategory", "1");
		root.addElement("authentication").addAttribute("username", username)
										 .addAttribute("password", password);
		root.addElement("text").addText(text);
		
		return d.asXML();
	}
	
	public static List<String> parse(String returnMsg){
		List<String> wordList = new ArrayList<String>();
		try {

			Document d = DocumentHelper.parseText(returnMsg);
			Element root = d.getRootElement();
			Element n;

			for (Iterator<?> i = root.elementIterator("result"); i.hasNext();) {
				n = (Element) i.next();

				for (Iterator<?> j = n.elementIterator(); j.hasNext();) {
					Element element = (Element) j.next();

					splitSentence(element.getText(), wordList);

				}
			}

		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return wordList;
	}
	
	private static List<String> splitSentence(String sentence, List<String> wordList){
		Pattern p = Pattern.compile("(\\S*)\\((\\S*)\\)");
		Matcher m;
		for (String e : sentence.split("　")) {
			if (!"".equals(e)) {
				m = p.matcher(e);
				while (m.find()) {
					wordList.add(m.group(1));
				}
			}
		}
		return wordList;
	}
	
}
