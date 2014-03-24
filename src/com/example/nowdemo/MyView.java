package com.example.nowdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MyView extends View {
	
	private float cR = 100;
	private Paint paint;
	private int height;
	private int width;
	
	private boolean isRun = false;

	public MyView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		paint = new Paint();
		paint.setAntiAlias(true);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		height = getHeight();
		width = getWidth();
		
		if(isRun){
			paint.setColor(Color.LTGRAY);
			canvas.drawCircle((float)width / 2, (float)height/ 2, dip2px(getContext(), 30) + cR, paint);
			paint.setColor(Color.GRAY);
		} else {
			paint.setColor(Color.RED);
		}
		
		canvas.drawCircle((float)width / 2, (float)height/ 2, dip2px(getContext(), 30), paint);
		
		super.onDraw(canvas);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		float x = event.getX();
		float y = event.getY();
		
//		System.out.println(x + ", " + y);
//		System.out.println(height/2);
//		System.out.println(width/2);
		
		int r = dip2px(getContext(), 30);
		
		if ((x - width/2) * (x - width/2) + (y - height/2) * (y - height/2) <= r * r) {
			if(event.getAction() == MotionEvent.ACTION_DOWN){
				if(onClickListener != null){
					onClickListener.onClick();
					isRun = !isRun;
				}
			}
		}
		return super.onTouchEvent(event);
	}

	
	public static int dip2px(Context context, float dpValue) {
	  final float scale = context.getResources().getDisplayMetrics().density;
	  return (int) (dpValue * scale + 0.5f);
	}

	public static int px2dip(Context context, float pxValue) {
	  final float scale = context.getResources().getDisplayMetrics().density;
	  return (int) (pxValue / scale + 0.5f);
	}
	
	public void setCr(float cR){
		this.cR = cR;
	}
	
	public interface OnClickListener{
		void onClick();
	}
	
	private OnClickListener onClickListener = null;

	public void setOnClickListener(OnClickListener onClickListener) {
		this.onClickListener = onClickListener;
	}

	public boolean isRun() {
		return isRun;
	}

	public void setRun(boolean isRun) {
		this.isRun = isRun;
	}

}
