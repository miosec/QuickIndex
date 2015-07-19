package org.miosec.quickindexbar;

public class Friends implements Comparable<Friends> {
	private String name;
	private String pinyin;

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public Friends(String name) {
		super();
		this.name = name;
		pinyin = PinYinUtil.getPinYin(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(Friends another) {
		return this.pinyin.compareTo(another.getPinyin());
	}
}
