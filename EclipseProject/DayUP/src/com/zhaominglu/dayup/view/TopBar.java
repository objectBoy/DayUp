package com.zhaominglu.dayup.view;

import com.zhaominglu.dayup.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TopBar extends RelativeLayout {
	private String title = "";
	private float titleTextSize;
	private int titleTextColor;

	private int leftTextColor;
	private Drawable leftBackground;
	private String leftText = "";

	private int rightTextColor;
	private Drawable rightBackground;
	private String rightText = "";

	private Button mLeftButton;
	private Button mRightButton;
	private TextView mTitle;

	private TopBarClickListener mListener;

	public TopBar(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public TopBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		// 获取XML文件中自定义的属性
		TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TopBar);

		title = ta.getString(R.styleable.TopBar_title);
		titleTextSize = ta.getDimension(R.styleable.TopBar_titleTextSize, 17);
		titleTextColor = ta.getColor(R.styleable.TopBar_titleTextColor, 0);

		leftTextColor = ta.getColor(R.styleable.TopBar_leftTextColor, 0);
		leftBackground = ta.getDrawable(R.styleable.TopBar_leftBackground);
		leftText = ta.getString(R.styleable.TopBar_leftText);

		rightTextColor = ta.getColor(R.styleable.TopBar_rightTextColor, 0);
		rightBackground = ta.getDrawable(R.styleable.TopBar_rightBackground);
		rightText = ta.getString(R.styleable.TopBar_rightText);
		// 一般要调用recyle方法完成资源回收，避免重新创建的时候的错误
		ta.recycle();

		initView(context);
	}

	private void initView(Context context) {
		// TODO Auto-generated method stub
		mLeftButton = new Button(context);
		mRightButton = new Button(context);
		mTitle = new TextView(context);

		mLeftButton.setTextColor(leftTextColor);
		mLeftButton.setBackground(leftBackground);
		mLeftButton.setText(leftText);

		mRightButton.setTextColor(rightTextColor);
		mRightButton.setBackground(rightBackground);
		mRightButton.setText(rightText);

		mTitle.setTextColor(titleTextColor);
		mTitle.setTextSize(titleTextSize);
		mTitle.setText(title);
		// 布局
		LayoutParams leftLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
		leftLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
		mLeftButton.setLayoutParams(leftLayoutParams);

		LayoutParams rightLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
		rightLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
		mRightButton.setLayoutParams(rightLayoutParams);

		LayoutParams titleLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		titleLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
		mTitle.setLayoutParams(titleLayoutParams);

		addView(mLeftButton);
		addView(mRightButton);
		addView(mTitle);

		// 点击事件
		mLeftButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (mListener != null)
					mListener.leftClick();
			}
		});
		mRightButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (mListener != null)
					mListener.rightClick();
			}
		});
	}

	public TopBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	// 通过接口回调实现点击事件
	public interface TopBarClickListener {
		// 左侧按钮点击
		void leftClick();

		// 右侧按钮点击
		void rightClick();
	}

	public void setonTopBarClickListener(TopBarClickListener topBarClickListener) {
		mListener = topBarClickListener;
	}

	/**
	 * 设置是否显示
	 * 
	 * @param id
	 *            0左边 1 右边
	 * @param flag
	 *            是否显示
	 */
	public void setButtonVisiable(int id, boolean flag) {
		if (flag) {
			if (id == 0) {
				mLeftButton.setVisibility(View.VISIBLE);
			} else {
				mRightButton.setVisibility(View.VISIBLE);
			}
		} else {
			if (id == 0) {
				mLeftButton.setVisibility(View.GONE);
			} else {
				mRightButton.setVisibility(View.GONE);
			}
		}
	}

}
