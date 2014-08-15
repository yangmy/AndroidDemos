package com.ymy.views;

import java.util.List;

import com.example.demos.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Checkable;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TableLayout.LayoutParams;

public class Grid1 extends Activity{

	GridView gridView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		loadICON();
		setContentView(R.layout.grid1);
		gridView=(GridView)findViewById(R.id.gridView);
		gridView.setChoiceMode(GridView.CHOICE_MODE_MULTIPLE_MODAL);
		gridView.setMultiChoiceModeListener(new MultiChoiceModeListener());
		gridView.setAdapter(new AppsAdapter());
		
	}
	
	private List<ResolveInfo> mapps;
	public void loadICON(){
		Intent mainIntent=new Intent(Intent.ACTION_MAIN,null);
		mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		mapps=  getPackageManager().queryIntentActivities(mainIntent,0);
	}
	
	
	public class AppsAdapter extends BaseAdapter{
		public AppsAdapter(){
			
		}

		@Override
		public int getCount() {
			return mapps.size();
		}

		@Override
		public Object getItem(int position) {
			return mapps.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imageView=new ImageView(Grid1.this);
			imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setLayoutParams(new GridView.LayoutParams(50, 50));
            ResolveInfo info=(ResolveInfo) getItem(position);
			imageView.setImageDrawable(info.activityInfo.loadIcon(getPackageManager()));;
			CheckableLayout l=new CheckableLayout(Grid1.this);
			l.setLayoutParams(new GridView.LayoutParams(GridView.LayoutParams.WRAP_CONTENT,GridView.LayoutParams.WRAP_CONTENT));
			l.addView(imageView);
			return l;
		}
	}
	
	
	public class CheckableLayout extends FrameLayout implements Checkable{

		public CheckableLayout(Context context) {
			super(context);
		}

		boolean mChecked;
		@Override
		public void setChecked(boolean checked) {
			mChecked=checked;
			setBackgroundDrawable(checked?getResources().getDrawable(R.drawable.blue):null);
		}

		@Override
		public boolean isChecked() {
			return mChecked;
		}

		@Override
		public void toggle() {
			setChecked(!mChecked);
		}
		
		
		
	}
	
    public class MultiChoiceModeListener implements GridView.MultiChoiceModeListener {
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.setTitle("Select Items");
            mode.setSubtitle("One item selected");
            return true;
        }

        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return true;
        }

        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            return true;
        }

        public void onDestroyActionMode(ActionMode mode) {
        }

        public void onItemCheckedStateChanged(ActionMode mode, int position, long id,
                boolean checked) {
            int selectCount = gridView.getCheckedItemCount();
            switch (selectCount) {
            case 1:
                mode.setSubtitle("One item selected");
                break;
            default:
                mode.setSubtitle("" + selectCount + " items selected");
                break;
            }
        }

    }
}
