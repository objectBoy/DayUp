package com.zhaominglu.dayup.doman;

import android.content.Intent;
import android.graphics.drawable.Drawable;

public class AppInfo {
	private String appName = null;
	private String packageName = null;
	private String launchActivityName = null;
	private Drawable icon = null;
	private Intent intent = null;

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getLaunchActivityName() {
		return launchActivityName;
	}

	public void setLaunchActivityName(String launchActivityName) {
		this.launchActivityName = launchActivityName;
	}

	public Drawable getIcon() {
		return icon;
	}

	public void setIcon(Drawable icon) {
		this.icon = icon;
	}

	public Intent getIntent() {
		return intent;
	}

	public void setIntent(Intent intent) {
		this.intent = intent;
	}

}
