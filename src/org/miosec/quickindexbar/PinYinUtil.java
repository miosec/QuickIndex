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
		pFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);//��д
		pFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);//��������
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
					//������ȷ�ĺ���
					pinyin += arr[i];
				}
			if(arr[i]>127){
				//���Ե�����ת��,���ǲ�һ���Ǻ���
				
			}else{
				//�����ܺ���
				pinyin += arr[i];
			}
		}
		return pinyin;
	}
}
