package com.WYJ.view;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.WYJ.activity.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.View.MeasureSpec;

public class mybutton extends View {
	/**
	 * �ı�
	 */
	private String mTitleText;
	/**
	 * �ı�����ɫ
	 */
	private int mTitleTextColor;
	/**
	 * ��������ɫ
	 */
	private int mbackgroundColor;
	/**
	 * �ı��Ĵ�С
	 */
	private int mTitleTextSize;

	/**
	 * ����ʱ�����ı����Ƶķ�Χ
	 */
	private Rect mBound;
	private Paint mPaint;
	
	public mybutton(Context context) {
		this(context,null);
		// TODO Auto-generated constructor stub
		
	}
	
	public mybutton(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		// TODO Auto-generated constructor stub
		
	}

	public mybutton(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		TypedArray a=context.getTheme().obtainStyledAttributes(attrs, R.styleable.mybutton, defStyleAttr, 0);
		int n = a.getIndexCount();
		for(int i=0;i<n;i++){
			int attr=a.getIndex(i);
			switch(attr){
			case R.styleable.mybutton_titleText:
				mTitleText=a.getString(attr);
				break;
			case R.styleable.mybutton_titleTextColor:
				mTitleTextColor =a.getColor(attr, Color.BLACK);
				break;
			//case R.styleable.mybutton_
			case R.styleable.mybutton_titleTextSize:
				mTitleTextSize=a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
						TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
				break;
			case R.styleable.mybutton_backgroundColor:
				mbackgroundColor=a.getColor(attr, Color.YELLOW);
			}
		}
		a.recycle();
		mPaint=new Paint();
		mPaint.setTextSize(mTitleTextSize);
		mBound=new Rect();
		mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);
		this.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mTitleText = randomText();
				postInvalidate();
			}
			
		});
	}
	
	
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		mPaint.setColor(mbackgroundColor);
		canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);
		mPaint.setColor(mTitleTextColor);
		canvas.drawText(mTitleText, getWidth() / 2 - mBound.width() / 2, getHeight() / 2 + mBound.height() / 2, mPaint);
		
	}
	
	

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		//super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int width = 0;
		int height = 0;

		/**
		 * ���ÿ��
		 */
		int specMode = MeasureSpec.getMode(widthMeasureSpec);
		int specSize = MeasureSpec.getSize(widthMeasureSpec);
		switch (specMode)
		{
		case MeasureSpec.EXACTLY:// ��ȷָ����
			width = getPaddingLeft() + getPaddingRight() + specSize;
			break;
		case MeasureSpec.AT_MOST:// һ��ΪWARP_CONTENT
			width = getPaddingLeft() + getPaddingRight() + mBound.width();
			break;
		}

		/**
		 * ���ø߶�
		 */
		specMode = MeasureSpec.getMode(heightMeasureSpec);
		specSize = MeasureSpec.getSize(heightMeasureSpec);
		switch (specMode)
		{
		case MeasureSpec.EXACTLY:// ��ȷָ����
			height = getPaddingTop() + getPaddingBottom() + specSize;
			break;
		case MeasureSpec.AT_MOST:// һ��ΪWARP_CONTENT
			height = getPaddingTop() + getPaddingBottom() + mBound.height();
			break;
		}

		setMeasuredDimension(width, height);

	}

	private String randomText()
	{
		Random random = new Random();
		Set<Integer> set = new HashSet<Integer>();
		while (set.size() < 4)
		{
			int randomInt = random.nextInt(10);
			set.add(randomInt);
		}
		StringBuffer sb = new StringBuffer();
		for (Integer i : set)
		{
			sb.append("" + i);
		}

		return sb.toString();
	}

}
