package com.zhaominglu.dayup.view;

import android.R.integer;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.InputFilter.LengthFilter;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;

public class ArcStatemnt extends View {
	private int lenght; // 屏幕寬度
	private int mCircleXY;
	private float mRadius;
	private RectF mArcRectF;
	private float mSweepAngle = 20;
	private String mShowText = "test";
	private int mShowTextSize = 4;

	public ArcStatemnt(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
		int lenght = wm.getDefaultDisplay().getWidth();
		mCircleXY = lenght / 2;
		mRadius = (float) (lenght * 0.5 / 2);
		mArcRectF = new RectF((float) (lenght * 0.1), (float) (lenght * 0.1), (float) (lenght * 0.9),
				(float) (lenght * 0.9));
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		Paint mCirclePaint = new Paint();
		mCirclePaint.setColor(Color.RED);
		Paint mArcPaint = new Paint();
		mArcPaint.setColor(Color.RED);
		Paint mTextPaint = new Paint();
		mTextPaint.setColor(Color.WHITE);
		mTextPaint.setTextSize(17);
		canvas.drawCircle(mCircleXY, mCircleXY, mRadius, mCirclePaint);
		canvas.drawArc(mArcRectF, 270, mSweepAngle, false, mArcPaint);

		canvas.drawText(mShowText, 0, mShowText.length(), mCircleXY, mCircleXY + (mShowTextSize / 4), mTextPaint);
	}

	public void setSweepValue(float sweetValue) {
		if (sweetValue != 0) {
			mSweepAngle = sweetValue;
		} else {
			mSweepAngle = 25;
		}
		this.invalidate();
	}
}
