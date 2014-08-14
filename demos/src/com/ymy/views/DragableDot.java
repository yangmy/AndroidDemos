package com.ymy.views;


import com.example.demos.R;

import android.content.ClipData;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.View;

public class DragableDot extends View{
	 private boolean mDragInProgress;
	    private boolean mHovering;
	    private boolean mAcceptsDrag;
	Paint mpaint;
	Paint mlegendpaint;
	Paint mGlow;
	
	int mRadius;
	CharSequence mLegend;
	int mAnrType;
	
	 private static final int NUM_GLOW_STEPS = 10;
	    private static final int GREEN_STEP = 0x0000FF00 / NUM_GLOW_STEPS;
	    private static final int WHITE_STEP = 0x00FFFFFF / NUM_GLOW_STEPS;
	    private static final int ALPHA_STEP = 0xFF000000 / NUM_GLOW_STEPS;
	
	
	
	public DragableDot(Context context) {
		super(context);
	}

	public DragableDot(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		mpaint=new Paint();
		mpaint.setAntiAlias(true);
		mpaint.setStrokeWidth(6);
		mpaint.setColor(0xFFD00000);
		
		mlegendpaint=new Paint();
		mlegendpaint.setAntiAlias(true);
		mlegendpaint.setTextAlign(Paint.Align.CENTER);
		mlegendpaint.setTextSize(50);
		mlegendpaint.setColor(0xFFF0F0FF);
		
        mGlow = new Paint();
        mGlow.setAntiAlias(true);
        mGlow.setStrokeWidth(1);
        mGlow.setStyle(Paint.Style.STROKE);
		
	      TypedArray a = context.obtainStyledAttributes(attrs,
	                R.styleable.DragableDot);

	        final int N = a.getIndexCount();
	        for (int i = 0; i < N; i++) {
	            int attr = a.getIndex(i);
	            switch (attr) {
	            case R.styleable.DragableDot_radius: {
	                mRadius = a.getDimensionPixelSize(attr, 0);
	            } break;

	            case R.styleable.DragableDot_legend: {
	                mLegend = a.getText(attr);
	            } break;

	            case R.styleable.DragableDot_anr: {
	                mAnrType = a.getInt(attr, 0);
	            } break;
	            }
	        }
	        
	   
	        setOnLongClickListener(new View.OnLongClickListener() {
				
				@Override
				public boolean onLongClick(View v) {
					ClipData data=ClipData.newPlainText("legend", mLegend);
					v.startDrag(data, new DragShadowBuilder(v), (Object)v, 0);
					return true;
				}
			});
	        
	        
	        setOnDragListener(new View.OnDragListener() {
				
				@Override
				public boolean onDrag(View v, DragEvent event) {
					boolean result=false;
					int action=event.getAction();
					ClipData data=event.getClipData();
					switch (action) {
					case DragEvent.ACTION_DRAG_STARTED:
						mAcceptsDrag=true;
						mDragInProgress=true;
						invalidate();
						result=true;
						break;

					case DragEvent.ACTION_DRAG_LOCATION:
						result=true;
						break;
					case DragEvent.ACTION_DRAG_ENTERED:
						mHovering=true;
						result=true;
						invalidate();
						break;
					case DragEvent.ACTION_DRAG_EXITED:
						mHovering=false;
						invalidate();
						break;
					case DragEvent.ACTION_DROP:
			            result = true;
			            ((DragableDot)v).precData(event);
						break;
					case DragEvent.ACTION_DRAG_ENDED:
						   mAcceptsDrag=false;
				            mDragInProgress=false;
				            mHovering=false;
				            invalidate();
						break;
					}
					return result;
				}
			});
	}
	
	public class MyShadowBuilder extends DragShadowBuilder{

		@Override
		public void onProvideShadowMetrics(Point shadowSize,
				Point shadowTouchPoint) {
			
		}

		@Override
		public void onDrawShadow(Canvas canvas) {

		}
		
		
	}
	
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int totalDiameter = 2*mRadius + getPaddingLeft() + getPaddingRight();
        setMeasuredDimension(totalDiameter, totalDiameter);
	}
	
	
	@Override
	protected void onDraw(Canvas canvas) {
		 float wf = getWidth();
	      float hf = getHeight();
	        final float cx = wf/2;
	        final float cy = hf/2;
	        wf -= getPaddingLeft() + getPaddingRight();
	        hf -= getPaddingTop() + getPaddingBottom();
	        float rad = (wf < hf) ? wf/2 : hf/2;
		canvas.drawCircle(cx, cy, rad, mpaint);
		
		if(mLegend!=null&&mLegend.length()>0){
			canvas.drawText(mLegend, 0, mLegend.length(), cx, cy+mlegendpaint.getTextSize()/2, mlegendpaint);
		}
		
	     if (mDragInProgress && mAcceptsDrag) {
	            for (int i = NUM_GLOW_STEPS; i > 0; i--) {
	                int color = (mHovering) ? WHITE_STEP : GREEN_STEP;
	                color = i*(color | ALPHA_STEP);
	                mGlow.setColor(color);
	                canvas.drawCircle(cx, cy, rad, mGlow);
	                rad -= 0.5f;
	                canvas.drawCircle(cx, cy, rad, mGlow);
	                rad -= 0.5f;
	            }
	        }
	}
	
	public void precData(DragEvent event){
		ClipData data= event.getClipData();
		ClipData.Item item=data.getItemAt(0);
		mLegend=item.getText();
	}
}
