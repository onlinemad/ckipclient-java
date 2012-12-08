package tw.cheyingwu.ckip;

/**
 * Trem物件，包含詞與詞性<br/>
 * 
 * @author onlinemad
 * @version 0.1
 */
public class Term {
	private String term;
	private String tag;
	/**
	 * 設定詞
	 * @param term - 詞字串
	 */
	public void setTerm(String term) {
		this.term = term;
	}
	/**
	 * 取得詞
	 * @return 詞字串
	 */
	public String getTerm() {
		return term;
	}
	/**
	 * 設定詞性
	 * @param tag - 詞性字串
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}
	/**
	 * 取得詞性
	 * @return 詞性字串
	 */
	public String getTag() {
		return tag;
	}
	
}
