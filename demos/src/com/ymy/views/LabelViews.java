package com.ymy.views;

import com.example.demos.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;

public class LabelViews extends View{

	private Paint mPaint;
	private String mText;
	private int mAscent;
	
	public LabelViews(Context context) {
		super(context);
		initView();
	}
	
	
	
	
	public LabelViews(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
		
		TypedArray t=context.obtainStyledAttributes(attrs,R.styleable.LabelView);
		CharSequence s=t.getString(R.styleable.LabelView_text);
		setText(s.toString());
	}




	public void initView(){
		mPaint=new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setTextSize(16*getResources().getDisplayMetrics().density);
		mPaint.setColor(0xFF000000);
		setPadding(3, 3, 3, 3);
	}
	
	public void setText(String text){
		mText=text;
		requestLayout();
		invalidate();
	}
	
	public void setTextsize(int size){
		mPaint.setTextSize(size);
		requestLayout();
		invalidate();
	}
	
	public void setColor(int color){
		mPaint.setColor(color);
		invalidate();
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawText(mText, getPaddingLeft(), getPaddingTop()-mPaint.ascent(), mPaint);
	}
	
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec),
                measureHeight(heightMeasureSpec));
    }

    /**
     * Determines the width of this view
     * @param measureSpec A measureSpec packed into an int
     * @return The width of the view, honoring constraints from measureSpec
     */
    private int measureWidth(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            // We were told how big to be
            result = specSize;
        } else {
            // Measure the text
            result = (int) mPaint.measureText(mText) + getPaddingLeft()
                    + getPaddingRight();
            if (specMode == MeasureSpec.AT_MOST) {
                // Respect AT_MOST value if that was what is called for by measureSpec
                result = Math.min(result, specSize);
            }
        }

        return result;
    }

    /**
     * Determines the height of this view
     * @param measureSpec A measureSpec packed into an int
     * @return The height of the view, honoring constraints from measureSpec
     */
    private int measureHeight(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        mAscent = (int) mPaint.ascent();
        if (specMode == MeasureSpec.EXACTLY) {
            // We were told how big to be
            result = specSize;
        } else {
            // Measure the text (beware: ascent is a negative number)
            result = (int) (-mAscent + mPaint.descent()) + getPaddingTop()
                    + getPaddingBottom();
            if (specMode == MeasureSpec.AT_MOST) {
                // Respect AT_MOST value if that was what is called for by measureSpec
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

	
	
	
	
}
