package com.zhaominglu.dayup.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

public class LinearGradientText extends TextView{
	private int mViewWidth;
	private Paint mPaint;
	private LinearGradient mLinearGradient;
	private Matrix mGradientMatrix;
	private int mTranslate;
	public LinearGradientText(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	public LinearGradientText(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	public LinearGradientText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);
		if(mViewWidth==0){
			mViewWidth= getMeasuredWidth();
			if(mViewWidth>0){
				mPaint = getPaint();//获得textView的Paint
				mLinearGradient = new LinearGradient(0, 0, mViewWidth, 0, new int[]{Color.BLUE,0xffffffff,Color.BLUE}, null, Shader.TileMode.CLAMP);
				mPaint.setShader(mLinearGradient);
				mGradientMatrix = new Matrix();
			}
		}
	}
@Override
protected void onDraw(Canvas canvas) {
	// TODO Auto-generated method stub
	super.onDraw(canvas);
	if(mGradientMatrix!=null){
		mTranslate += mViewWidth/5;
		if(mTranslate>2*mViewWidth){
			mTranslate = -mViewWidth;
		}
		mGradientMatrix.setTranslate(mTranslate, 0);
		mLinearGradient.setLocalMatrix(mGradientMatrix);
		postInvalidateDelayed(100);
	}
}
	

}
