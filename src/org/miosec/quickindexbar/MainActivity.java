package org.miosec.quickindexbar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.security.auth.PrivateCredentialPermission;

import org.miosec.quickindexbar.QuickIndexBar.OnTouchIndexListener;

import android.R.integer;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	protected static final String TAG = "MainActivity";
	private QuickIndexBar quickIndexBar;
	private List<Friends> list = new ArrayList<Friends>();
	private ListView listview;
	private TextView currentIndex;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		quickIndexBar = (QuickIndexBar) findViewById(R.id.quickIndexBar);
		currentIndex = (TextView) findViewById(R.id.currentIndex);
		quickIndexBar.setOnTouchIndexListener(new OnTouchIndexListener() {

			@Override
			public void onTouchIndex(String word) {
				Log.e(TAG, word);
				for (int i = 0; i < list.size(); i++) {
					String firstWord = list.get(i).getPinyin().charAt(0) + "";
					if (firstWord.equals(word)) {
						listview.setSelection(i);
						break;
					}
					showIndex(word);
				}
			}
		});
		fillList();
		Collections.sort(list);
		listview = (ListView) findViewById(R.id.listview);
		listview.setAdapter(new MyAdapter());
	}

	private Handler handler = new Handler();

	protected void showIndex(String word) {
		currentIndex.setVisibility(View.VISIBLE);
		currentIndex.setText(word);
		handler.removeCallbacksAndMessages(null);
		handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				currentIndex.setVisibility(View.GONE);
			}
		}, 1000);
	}

	private class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView != null) {
				holder = (ViewHolder) convertView.getTag();
			} else {
				convertView = View.inflate(MainActivity.this,
						R.layout.adapter_list, null);
				holder = new ViewHolder();
				holder.index = (TextView) convertView.findViewById(R.id.index);
				holder.name = (TextView) convertView.findViewById(R.id.name);
				convertView.setTag(holder);
			}
			Friends friend = list.get(position);
			String currentWord = friend.getPinyin().charAt(0) + "";
			if (position > 0) {
				String lastword = list.get(position - 1).getPinyin().charAt(0)
						+ "";
				if (currentWord.equals(lastword)) {
					holder.index.setVisibility(View.GONE);
				} else {
					holder.index.setVisibility(View.VISIBLE);
					holder.index.setText(currentWord);
				}
			} else {
				holder.index.setVisibility(View.VISIBLE);
				holder.index.setText(currentWord);
			}
			holder.name.setText(friend.getName());
			return convertView;
		}

	}

	class ViewHolder {
		private TextView index;
		private TextView name;
	}

	private void fillList() {
		// ��������
		list.add(new Friends("��ΰ"));
		list.add(new Friends("����"));
		list.add(new Friends("����"));
		list.add(new Friends("����"));
		list.add(new Friends("����"));
		list.add(new Friends("������"));
		list.add(new Friends("������"));
		list.add(new Friends("����"));
		list.add(new Friends("�ֿ���1"));
		list.add(new Friends("����2"));
		list.add(new Friends("����a"));
		list.add(new Friends("�ֿ���a"));
		list.add(new Friends("����"));
		list.add(new Friends("�ֿ���"));
		list.add(new Friends("����"));
		list.add(new Friends("����b"));
		list.add(new Friends("����"));
		list.add(new Friends("����"));
		list.add(new Friends("������"));
		list.add(new Friends("����1"));
		list.add(new Friends("��ΰ1"));
		list.add(new Friends("�ν�"));
		list.add(new Friends("�ν�1"));
		list.add(new Friends("��ΰ3"));
	}
}
