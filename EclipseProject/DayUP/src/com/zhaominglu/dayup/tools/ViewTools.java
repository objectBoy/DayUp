package com.zhaominglu.dayup.tools;

import java.util.concurrent.TimeUnit;

import android.database.Observable;
import android.view.View;

public class ViewTools {
	/**
	 * 双击检测
	 */
	// public void doubleClickDetect(View view){
	// Observable<Void> observable = RxView.clicks(view).share();
	// observable.buffer(observable.debounce(200, TimeUnit.MILLISECONDS))
	// .observeOn(AndroidSchedulers.mainThread())
	// .subscribe(new Action1<List<Void>>() {
	// @Override
	// public void call(List<Void> voids) {
	// if(voids.size() >= 2){
	// //double click detected
	// }
	// }
	// }, new Action1<Throwable>() {
	// @Override
	// public void call(Throwable throwable) {
	// Timber.e(throwable, "error");
	// }
	// });
	// }
}
