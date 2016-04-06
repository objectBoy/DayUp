package com.zhaominglu.dayup.activity;

import com.zhaominglu.dayup.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Html5Activity extends Activity {

	private String mUrl;

	private WebView mWebView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web);

		Bundle bundle = getIntent().getBundleExtra("bundle");
		// mUrl = bundle.getString("url");
		mUrl = "http://www.baidu.com/";

		Log.d("Url:", mUrl);

		mWebView = (WebView) findViewById(R.id.web);

		WebSettings mWebSettings = mWebView.getSettings();
		mWebSettings.setSupportZoom(true);
		mWebSettings.setLoadWithOverviewMode(true);
		mWebSettings.setUseWideViewPort(true);
		mWebSettings.setDefaultTextEncodingName("utf-8");
		mWebSettings.setLoadsImagesAutomatically(true);

		// mWebSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

		// 调用JS方法.安卓版本大于17,加上注解 @JavascriptInterface
		mWebSettings.setJavaScriptEnabled(true);

		saveData(mWebSettings);

		newWin(mWebSettings);

		mWebView.setWebChromeClient(webChromeClient);
		mWebView.setWebViewClient(webViewClient);
		mWebView.loadUrl(mUrl);
	}

	/**
	 * 多窗口的问题
	 */
	private void newWin(WebSettings mWebSettings) {
		// html中的_bank标签就是新建窗口打开，有时会打不开，需要加以下
		// 然后 复写 WebChromeClient的onCreateWindow方法
		mWebSettings.setSupportMultipleWindows(true);
		mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
	}

	/**
	 * HTML5数据存储
	 */
	private void saveData(WebSettings mWebSettings) {
		// 有时候网页需要自己保存一些关键数据,Android WebView 需要自己设置
		mWebSettings.setDomStorageEnabled(true);
		mWebSettings.setDatabaseEnabled(true);
		mWebSettings.setAppCacheEnabled(true);
		String appCachePath = getApplicationContext().getCacheDir().getAbsolutePath();
		mWebSettings.setAppCachePath(appCachePath);
	}

	WebViewClient webViewClient = new WebViewClient() {

		/**
		 * 多页面在同一个WebView中打开，就是不新建activity或者调用系统浏览器打开
		 */
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}

	};

	WebChromeClient webChromeClient = new WebChromeClient() {

		// =========HTML5定位==========================================================
		// 需要先加入权限
		// <uses-permission android:name="android.permission.INTERNET"/>
		// <uses-permission
		// android:name="android.permission.ACCESS_FINE_LOCATION"/>
		// <uses-permission
		// android:name="android.permission.ACCESS_COARSE_LOCATION"/>
		@Override
		public void onReceivedIcon(WebView view, Bitmap icon) {
			super.onReceivedIcon(view, icon);
		}

		@Override
		public void onGeolocationPermissionsHidePrompt() {
			super.onGeolocationPermissionsHidePrompt();
		}

		@Override
		public void onGeolocationPermissionsShowPrompt(final String origin,
				final GeolocationPermissions.Callback callback) {
			callback.invoke(origin, true, false);// 注意个函数，第二个参数就是是否同意定位权限，第三个是是否希望内核记住
			super.onGeolocationPermissionsShowPrompt(origin, callback);
		}
		// =========HTML5定位==========================================================

		// =========多窗口的问题==========================================================
		@Override
		public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
			WebView.WebViewTransport transport = (WebView.WebViewTransport) resultMsg.obj;
			transport.setWebView(mWebView);
			resultMsg.sendToTarget();
			return true;
		}
		// =========多窗口的问题==========================================================
	};

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
			mWebView.goBack();
			return true;
		}

		return super.onKeyDown(keyCode, event);
	}

	// private void getWebData() {
	// new Thread() {
	// @Override
	// public void run() {
	// OkHttpClient okHttpClient =
	// HttpClientSslHelper.getSslOkHttpClient(getApplicationContext(),
	// AppApiContact.bServerDebug);//new OkHttpClient();
	// okHttpClient.setConnectTimeout(15, TimeUnit.SECONDS);
	// Request request = new Request.Builder()
	// .url(mWebUrl)
	// .build();
	// try {
	// Response response = okHttpClient.newCall(request).execute();
	// final String data = response.body().string();
	// LogUtils.d(TAG, "data = " + data);
	// runOnUiThread(new Runnable() {
	// @Override
	// public void run() {
	// if (StringHelper.notEmpty(data) && !isDestroy) {//页面销毁之后不做处理
	// // mWebView.loadData(data, "text/html; charset=UTF-8", null);
	// mWebView.loadDataWithBaseURL(null, data, "text/html", "UTF-8", null);
	// }
	// }
	// });
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	// }.start();
	// }
}
