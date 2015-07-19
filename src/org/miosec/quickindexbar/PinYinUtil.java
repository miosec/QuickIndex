package org.miosec.quickindexbar;

import java.text.Format;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import android.text.TextUtils;

public class PinYinUtil {
	public static String getPinYin(String hanzi){
		String pinyin = "";
		if(TextUtils.isEmpty(hanzi))return pinyin;
		HanyuPinyinOutputFormat pFormat = new HanyuPinyinOutputFormat();
		pFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);//大写
		pFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);//不带音标
		char[] arr = hanzi.toCharArray();
		for (int i = 0; i < arr.length; i++) {
			if(Character.isWhitespace(arr[i])) continue;
				try {
					String[] hanyuPinyinStringArray = PinyinHelper.toHanyuPinyinStringArray(arr[i], pFormat);
					if(hanyuPinyinStringArray == null){
						pinyin += arr[i];
					}else{
						pinyin += hanyuPinyinStringArray[0];
					}
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
					//不是正确的汉字
					pinyin += arr[i];
				}
			if(arr[i]>127){
				//可以当汉字转换,但是不一定是汉字
				
			}else{
				//不可能汉字
				pinyin += arr[i];
			}
		}
		return pinyin;
	}
}
