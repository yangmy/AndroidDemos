package com.ymy.views;

import com.example.demos.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class Animation1 extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.animation1);
		Button btn=(Button) findViewById(R.id.button);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				View view=findViewById(R.id.et);
				Animation anim=AnimationUtils.loadAnimation(Animation1.this, R.anim.shake);
				view.startAnimation(anim);
				
			}
		});
	}
}
