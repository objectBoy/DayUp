package com.zhaominglu.dayup.view;

import android.content.Context;
import android.graphics.Color;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

public class ViewTest extends View {
	public ViewTest(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		setBackgroundColor(Color.RED);
	}

	public ViewTest(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		setBackgroundColor(Color.RED);
	}

	public ViewTest(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		setBackgroundColor(Color.RED);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		setMeasuredDimension(onWidthMeasure(widthMeasureSpec), onHeightMeasure(heightMeasureSpec));
	}

	private int onHeightMeasure(int heightMeasureSpec) {
		// TODO Auto-generated method stub
		int result = 0 ;
		int specModel = MeasureSpec.getMode(heightMeasureSpec);
		int specSize = MeasureSpec.getSize(heightMeasureSpec);
		
		if(specModel == MeasureSpec.EXACTLY){
			result = specSize;
		}else if(specModel == MeasureSpec.AT_MOST){
			result = Math.min(200, specSize);
		}
		return result;
	}

	private int onWidthMeasure(int widthMeasureSpec) {
		// TODO Auto-generated method stub
		int result = 0 ;
		int specModel = MeasureSpec.getMode(widthMeasureSpec);
		int specSize = MeasureSpec.getSize(widthMeasureSpec);
		
		if(specModel == MeasureSpec.EXACTLY){
			result = specSize;
		}else if(specModel == MeasureSpec.AT_MOST){
			result = Math.min(200, specSize);
		}
		return result;
	}
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);
		
	} 
}
