package tw.cheyingwu.ckip;

import java.net.*;
import java.io.*;
import java.util.*;
import java.util.regex.*;

import org.dom4j.*;

/**
 * 中研院斷詞服務 <br/>
 * 
 * @author onlinemad
 * @version 0.4
 */
public class CKIP extends WordSegmentationService {
	private String username;
	private String password;

	private String serverIP;
	private int serverPort;

	private List<String> sentence = new ArrayList<String>();

	/**
	 * 建構子，透過建構子產生CKIP物件。
	 * 
	 * @param serverIP
	 *            - CKIP斷詞服務伺服器IP
	 * @param serverPort
	 *            - CKIP斷詞服務伺服器Port
	 * @param username
	 *            - 使用者帳號
	 * @param password
	 *            - 使用者密碼
	 */
	public CKIP(String serverIP, int serverPort, String username, String password) {
		this.username = username;
		this.password = password;
		this.serverIP = serverIP;
		this.serverPort = serverPort;
	}

	/**
	 * 將字串送至CKIP服務伺服器。
	 */
	public void send() {
		try {
			// create XML format message
			Document d = DocumentHelper.createDocument();

			Element root = d.addElement("wordsegmentation");
			root.addAttribute("version", "0.1");
			root.addElement("option").addAttribute("showcategory", "1");
			root.addElement("authentication").addAttribute("username", this.getUsername()).addAttribute("password", this.getPassword());
			root.addElement("text").addText(this.getRawText());

			Socket s = new Socket(this.serverIP, this.serverPort);

			// send rawText to server
			OutputStreamWriter osw = new OutputStreamWriter(s.getOutputStream(), "Big5");
			PrintWriter pw = new PrintWriter(osw);
			pw.println(d.asXML());
			pw.flush();

			// get return text
			InputStreamReader isr = new InputStreamReader(s.getInputStream(), "Big5");
			BufferedReader br = new BufferedReader(isr);

			this.setReturnText(br.readLine());

			br.close();
			s.close();

		} catch (UnknownHostException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private String[] getSentence() {
		try {

			List<String> s = new ArrayList<String>();

			Document d = DocumentHelper.parseText(this.getReturnText());
			Element root = d.getRootElement();
			Element n;

			for (Iterator<?> i = root.elementIterator("result"); i.hasNext();) {
				n = (Element) i.next();

				for (Iterator<?> j = n.elementIterator(); j.hasNext();) {
					Element element = (Element) j.next();

					s.add(element.getText());

				}
			}

			this.sentence = s;
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		return this.sentence.toArray(new String[0]);
	}

	/**
	 * 將斷詞結果分解為詞
	 * 
	 * @return 詞陣列
	 */
	public List<Term> getTerm() {
		List<Term> tl = new ArrayList<Term>();

		for (String s : this.getSentence()) {
			for (String e : s.split("　")) {
				if (!"".equals(e)) {
					Pattern p;
					Matcher m;
					Term t = new Term();
					p = Pattern.compile("(\\S*)\\((\\S*)\\)");
					m = p.matcher(e);
					while (m.find()) {
						t.setTerm(m.group(1));
						t.setTag(m.group(2));
					}
					tl.add(t);

				}
			}
		}
		return tl;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getServerIP() {
		return serverIP;
	}

	public void setServerIP(String serverIP) {
		this.serverIP = serverIP;
	}

	public int getServerPort() {
		return serverPort;
	}

	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}

}
