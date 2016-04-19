package com.zhaominglu.dayup.activity;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class Test extends Service{
	private String result;
	public void  startService(){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				result = "aaa";
				
			}
		}).start();
	}
	A a;
	public void setA(A tmpA){
		a = tmpA;
	}
	class tmpBind extends Binder{
		
		public Service getService(){
			return Test.this;
		}
	}
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return new tmpBind();
	}
	
	
}
interface A{
	public String getResult();
}

