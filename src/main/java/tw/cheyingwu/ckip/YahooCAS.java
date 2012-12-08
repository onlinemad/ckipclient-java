package tw.cheyingwu.ckip;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * Yahoo 斷章取義  API<br/>
 * @author onlinemad
 * @version 0.4
 */
public class YahooCAS extends WordSegmentationService{
	private String appid;

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	/**
	 * 建構子
	 * 
	 * @param appid
	 *            - Yahoo 應用程式授權碼
	 * 
	 */
	public YahooCAS(String appid) {
		this.appid = appid;
	}

	public void send() {
		try {
			URL url = new URL("http://asia.search.yahooapis.com/cas/v1/ws");
			HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
			httpConn.setRequestMethod("POST");
			httpConn.setDoOutput(true);
			httpConn.setUseCaches(false);
			DataOutputStream post = new DataOutputStream(httpConn.getOutputStream());
			String postData = "appid=" + this.appid + "&content=" + URLEncoder.encode(this.rawText, "UTF-8");
			post.writeBytes(postData);
			post.flush();
			post.close();
			BufferedReader response = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
			String s;
			while ((s = response.readLine()) != null) {
				this.returnText += s;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public List<Term> getTerm() {
		try {
			List<Term> tl = new ArrayList<Term>();
			Document d = DocumentHelper.parseText(this.getReturnText());
			Element root = d.getRootElement();
			Element n;

			for (Iterator<?> i = root.elementIterator("Segment"); i.hasNext();) {
				n = (Element) i.next();
				Term t = new Term();
				for (Iterator<?> j = n.elementIterator("token"); j.hasNext();) {
					Element element = (Element) j.next();
					t.setTerm(element.getText());

				}
				for (Iterator<?> j = n.elementIterator("pos"); j.hasNext();) {
					Element element = (Element) j.next();
					t.setTag(element.getText());
				}
				if(!t.getTerm().equals("")){
					tl.add(t);
				}
			}
			this.term = tl;
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		return this.term;
	}

}
