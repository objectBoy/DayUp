package com.zhaominglu.dayup.activity;

import java.util.Collections;
import java.util.List;

import com.zhaominglu.dayup.R;
import com.zhaominglu.dayup.doman.AppInfo;

import android.R.color;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PackageManagerActivity extends Activity {
	LinearLayout rootView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_package_manager);
		rootView = (LinearLayout) findViewById(R.id.rootView);
		PackageManager pm = getPackageManager();
		// getLaunchAllApp(pm); 
		getAllApps(pm);
		//getSysApps(pm);
		//getAutonomouslyApps(pm);
	}

	private void getAllApps(PackageManager pm) {
		List<ApplicationInfo> listApplications = pm
				.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
		Collections.sort(listApplications,
				new ApplicationInfo.DisplayNameComparator(pm));
		LinearLayout rootView = (LinearLayout) findViewById(R.id.rootView);
		int appCount = listApplications.size();
		for (int i = 0; i < appCount; i++) {
			ApplicationInfo ai = listApplications.get(i);
			AppInfo appInfo = new AppInfo();
			appInfo.setIcon(ai.loadIcon(pm));
			appInfo.setAppName(ai.loadLabel(pm).toString());
			appInfo.setPackageName(ai.packageName);
			appInfo.setIntent(pm.getLaunchIntentForPackage(appInfo
					.getPackageName()));
			rootView.addView(instanceAppIconView(appInfo));
		}
	}

	private void getSysApps(PackageManager pm) {
		List<ApplicationInfo> listApplications = pm
				.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
		Collections.sort(listApplications,
				new ApplicationInfo.DisplayNameComparator(pm));
		LinearLayout rootView = (LinearLayout) findViewById(R.id.rootView);
		int appCount = listApplications.size();
		for (int i = 0; i < appCount; i++) {
			ApplicationInfo ai = listApplications.get(i);
			if ((ai.flags & ApplicationInfo.FLAG_SYSTEM) > 0) {
				AppInfo appInfo = new AppInfo();
				appInfo.setIcon(ai.loadIcon(pm));
				appInfo.setAppName(ai.loadLabel(pm).toString());
				appInfo.setPackageName(ai.packageName);
				appInfo.setIntent(pm.getLaunchIntentForPackage(appInfo
						.getPackageName()));
				rootView.addView(instanceAppIconView(appInfo));
			}
		}
	}

	private void getAutonomouslyApps(PackageManager pm) {
		List<ApplicationInfo> listApplications = pm
				.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
		Collections.sort(listApplications,
				new ApplicationInfo.DisplayNameComparator(pm));
		LinearLayout rootView = (LinearLayout) findViewById(R.id.rootView);
		int appCount = listApplications.size();
		for (int i = 0; i < appCount; i++) {
			ApplicationInfo ai = listApplications.get(i);
			if ((ai.flags & ApplicationInfo.FLAG_SYSTEM) <= 0) {
				AppInfo appInfo = new AppInfo();
				appInfo.setIcon(ai.loadIcon(pm));
				appInfo.setAppName(ai.loadLabel(pm).toString());
				appInfo.setPackageName(ai.packageName);
				appInfo.setIntent(pm.getLaunchIntentForPackage(appInfo
						.getPackageName()));
				rootView.addView(instanceAppIconView(appInfo));
			}
		}
	}

	private void getLaunchAllApp(PackageManager pm) {
		Intent queryIntent = new Intent(Intent.ACTION_MAIN);
		queryIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		List<ResolveInfo> resolveInfos = pm.queryIntentActivities(queryIntent,
				0);
		Collections.sort(resolveInfos,
				new ResolveInfo.DisplayNameComparator(pm));
		for (int i = 0; i < resolveInfos.size(); i++) {
			ResolveInfo resolveInfo = resolveInfos.get(i);
			ActivityInfo activityInfo = resolveInfo.activityInfo;
			AppInfo appInfo = new AppInfo();
			appInfo.setLaunchActivityName(activityInfo.name);
			appInfo.setPackageName(activityInfo.packageName);
			appInfo.setIcon(activityInfo.loadIcon(pm));
			appInfo.setAppName(activityInfo.loadLabel(pm).toString());
			Intent launchIntent = new Intent();
			launchIntent.setComponent(new ComponentName(appInfo
					.getPackageName(), appInfo.getLaunchActivityName()));
			appInfo.setIntent(launchIntent);
			rootView.addView(instanceAppIconView(appInfo));
		}
	}

	@SuppressLint("InlinedApi")
	private View instanceAppIconView(final AppInfo appInfo) {
		LinearLayout layout = new LinearLayout(this);
		layout.setLayoutParams(new LayoutParams(
				android.widget.LinearLayout.LayoutParams.MATCH_PARENT,
				android.widget.LinearLayout.LayoutParams.WRAP_CONTENT));
		layout.setOrientation(LinearLayout.HORIZONTAL);

		ImageView icon = new ImageView(this);
		icon.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		icon.setScaleType(ScaleType.FIT_XY);
		icon.setBackgroundColor(color.holo_red_light);
		icon.setImageDrawable(appInfo.getIcon());
		icon.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				PackageManagerActivity.this.startActivity(appInfo.getIntent());
			}
		});
		layout.addView(icon);

		TextView describeView = new TextView(this);
		describeView.setLayoutParams(new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		describeView.setTextColor(Color.BLACK);
		String describeStr = appInfo.getAppName() + "\n"
				+ appInfo.getPackageName() + "\n"
				+ appInfo.getLaunchActivityName();
		describeView.setText(describeStr);
		layout.addView(describeView);
		return layout;
	}
}
