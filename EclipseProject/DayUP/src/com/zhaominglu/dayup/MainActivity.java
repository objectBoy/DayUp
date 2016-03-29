package com.zhaominglu.dayup;

import com.zhaominglu.dayup.view.TopBar;
import com.zhaominglu.dayup.view.TopBar.TopBarClickListener;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {
	private TopBar mTopBar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mTopBar = (TopBar) findViewById(R.id.topbar);
		mTopBar.setonTopBarClickListener(new TopBarClickListener() {
			
			@Override
			public void rightClick() {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "right", Toast.LENGTH_SHORT).show();
			}
			
			@Override
			public void leftClick() {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "left", Toast.LENGTH_SHORT).show();
			}
		});
		mTopBar.setButtonVisiable(0, false);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
