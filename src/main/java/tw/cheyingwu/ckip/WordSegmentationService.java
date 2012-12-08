package tw.cheyingwu.ckip;

import java.util.ArrayList;
import java.util.List;

public abstract class WordSegmentationService {
	protected String rawText;
	protected String returnText;
	protected List<Term> term;

	public WordSegmentationService() {
		this.rawText = "";
		this.returnText = "";
		this.term = new ArrayList<Term>();
	}

	/**
	 * 取得原始字串。
	 * 
	 * @return 原始字串
	 */
	public String getRawText() {
		return rawText;
	}

	/**
	 * 設定原始字串
	 * 
	 * @param rawText
	 *            - 原始字串，請使用UTF-8編碼。
	 */
	public void setRawText(String rawText) {
		this.rawText = rawText;
	}

	/**
	 * 取得斷詞結果字串。
	 * 
	 * @return 斷詞結果字串，包含XML標記。
	 */
	public String getReturnText() {
		return returnText;
	}

	protected void setReturnText(String returnText) {
		this.returnText = returnText;
	}

	/**
	 * 將斷詞結果分解為詞
	 * 
	 * @return 詞陣列
	 */
	public abstract List<Term> getTerm();

	protected void setTerm(List<Term> term) {
		this.term = term;
	}

	/**
	 * 將字串送至CKIP服務伺服器。
	 */
	public abstract void send();

}
