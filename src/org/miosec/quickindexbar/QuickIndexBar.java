package org.miosec.quickindexbar;

import android.R.integer;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class QuickIndexBar extends View {
	private Paint paint;
	private int cellWidth, cellHeight;
	private String TAG = "QuickIndex";
	private String[] indexArr = { "A", "B", "C", "D", "E", "F", "G", "H", "I",
			"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
			"W", "X", "Y", "Z" };
	private int touchIndex = -1;

	public QuickIndexBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
		paint = new Paint();
		paint.setColor(Color.parseColor("#ffffff"));
		paint.setTypeface(Typeface.DEFAULT_BOLD);// 字体为粗体
		paint.setTextSize(35);
		paint.setAntiAlias(true);// 抗锯齿
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (cellWidth == 0)
			cellWidth = getMeasuredWidth();
		System.out.println(cellWidth);

		if (cellHeight == 0)
			cellHeight = getMeasuredHeight() / indexArr.length;
		// 将26个字母等分绘制到对应位置
		for (int i = 0; i < indexArr.length; i++) {// 根据字母数组尺寸,循环绘制字母
			float x = cellWidth / 2 - paint.measureText(indexArr[i]) / 2;
			Rect bounds = new Rect();
			paint.getTextBounds(indexArr[i], 0, indexArr[i].length(), bounds);// 只要执行完,bounds里面就有了数据
			float y = cellHeight / 2 + bounds.height() / 2 + i * cellHeight;
			paint.setColor(i == touchIndex ? Color.parseColor("#666666")
					: Color.parseColor("#ffffff"));
			canvas.drawText(indexArr[i], x, y, paint);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int y = (int) event.getY();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:

		case MotionEvent.ACTION_MOVE:
			if (isSameIndex(y / cellHeight))
				break;
			touchIndex = y / cellHeight;
			if (touchIndex >= 0 && touchIndex < indexArr.length) {
				String word = indexArr[touchIndex];
				if (onTouchIndexListener != null) {
					onTouchIndexListener.onTouchIndex(word);
				}
			}

			break;
		case MotionEvent.ACTION_UP:
			touchIndex = -1;
			break;

		default:
			break;
		}
		invalidate();
		return true;
	}

	public boolean isSameIndex(int currentTouchIndex) {
		return touchIndex == currentTouchIndex;
	}

	public OnTouchIndexListener onTouchIndexListener;

	public OnTouchIndexListener getOnTouchIndexListener() {
		return onTouchIndexListener;
	}

	public void setOnTouchIndexListener(
			OnTouchIndexListener onTouchIndexListener) {
		this.onTouchIndexListener = onTouchIndexListener;
	}

	public int getTouchIndex() {
		return touchIndex;
	}

	public void setTouchIndex(int touchIndex) {
		this.touchIndex = touchIndex;
	}

	public interface OnTouchIndexListener {
		void onTouchIndex(String word);
	}
}
