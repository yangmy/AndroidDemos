package com.ymy.views;

import java.sql.NClob;


import android.app.ExpandableListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class ExpandList extends ExpandableListActivity{

	String []parent=new String []{
		"Group1","Group2","Group3"
	};
	String [][]child=new String[][]{
		{"Child1","Child2","Child3"},
		{"Child1","Child2","Child3"},
		{"Child1","Child2","Child3"}
	};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setListAdapter(new MyExpandListAdapter(parent, child, this));
	}
	
	
	public class MyExpandListAdapter extends BaseExpandableListAdapter{

		
		String [] parent;
		String [][] child;
		Context mContext;
		
		
		public MyExpandListAdapter() {
			super();
		}
		
		public MyExpandListAdapter(String[] parent,String[][]child,Context context){
			this.parent=parent;
			this.child=child;
			mContext=context;
		}

		@Override
		public int getGroupCount() {
			return parent.length;
		}

		@Override
		public int getChildrenCount(int groupPosition) {
			return child.length;
		}

		@Override
		public Object getGroup(int groupPosition) {
			return parent[groupPosition];
		}

		@Override
		public Object getChild(int groupPosition, int childPosition) {
			return child[groupPosition][childPosition];
		}

		@Override
		public long getGroupId(int groupPosition) {
			return groupPosition;
		}

		@Override
		public long getChildId(int groupPosition, int childPosition) {
			return childPosition;
		}

		@Override
		public boolean hasStableIds() {
			return true;
		}
	
		public TextView getGenericView() {
	            // Layout parameters for the ExpandableListView
	            AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
	                    ViewGroup.LayoutParams.MATCH_PARENT, 64);

	            TextView textView = new TextView(mContext);
	            textView.setLayoutParams(lp);
	            // Center the text vertically
	            textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
	            // Set the text starting position
	            textView.setPadding(36, 0, 0, 0);
	            return textView;
	     }

		@Override
		public View getGroupView(int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {
			TextView parentText=getGenericView();
			parentText.setText(getGroup(groupPosition).toString());
			return parentText;
		}

		@Override
		public View getChildView(int groupPosition, int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {
			TextView childText =getGenericView();
			childText.setText(getChild(groupPosition, childPosition).toString());
			return childText;
		}

		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {
			return true;
		}
		
	}
}
