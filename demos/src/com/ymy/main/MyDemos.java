package com.ymy.main;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MyDemos extends ListActivity{

	String category="android.intent.category.MY_SAMPLE_CODE";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        
        Intent intent = getIntent();
        String path = intent.getStringExtra("com.example.android.apis.Path");
        
        if (path == null) {
            path = "";
        }
        setListAdapter(new SimpleAdapter(this, getData(path),
                android.R.layout.simple_list_item_1, new String[] { "title" },
                new int[] { android.R.id.text1 }));
        getListView().setTextFilterEnabled(true);
	}
	
	
	public List<Map<String, Object>> getData(String prefix){
        List<Map<String, Object>> myData = new ArrayList<Map<String, Object>>();

        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(category);
        
        PackageManager pm=getPackageManager();
        List<ResolveInfo> list=pm.queryIntentActivities(mainIntent, 0);
		
        if(list==null){
        	return myData;
        }
        
        String [] prefixPath=null;
        String prefixwithslash=prefix;
        if(prefix.equals("")){
        	prefixPath=null;
        }
        else{
        	prefixPath=prefix.split("/");
        	prefixwithslash=prefix+"/";
        }
        
        int len=list.size();
        
        Map<String, Boolean> entries=new HashMap<String,Boolean>();
        
        for(int i=0;i<len;i++){
        	ResolveInfo info=list.get(i);
        	CharSequence labelseq=info.loadLabel(pm);
        	String label=labelseq!=null?labelseq.toString():info.activityInfo.name;
        	if(label.length()==0||label.startsWith(prefixwithslash)){
        		  String[] labelPath = label.split("/");

                  String nextLabel = prefixPath == null ? labelPath[0] : labelPath[prefixPath.length];
                  
                  if ((prefixPath != null ? prefixPath.length : 0) == labelPath.length - 1) {
                      addItem(myData, nextLabel, activityIntent(
                              info.activityInfo.applicationInfo.packageName,
                              info.activityInfo.name));
                  } else {
                      if (entries.get(nextLabel) == null) {
                          addItem(myData, nextLabel, browseIntent(prefix.equals("") ? nextLabel : prefix + "/" + nextLabel));
                          entries.put(nextLabel, true);
                      }
                  }
        	}
        }
        Collections.sort(myData, sDisplayNameComparator);
        
		
		return myData;
	}
	
	
	  private final static Comparator<Map<String, Object>> sDisplayNameComparator =
		        new Comparator<Map<String, Object>>() {
		        private final Collator   collator = Collator.getInstance();

		        public int compare(Map<String, Object> map1, Map<String, Object> map2) {
		            return collator.compare(map1.get("title"), map2.get("title"));
		        }
		    };

		    protected Intent activityIntent(String pkg, String componentName) {
		        Intent result = new Intent();
		        result.setClassName(pkg, componentName);
		        return result;
		    }
		    
		    protected Intent browseIntent(String path) {
		        Intent result = new Intent();
		        result.setClass(this, MyDemos.class);
		        result.putExtra("com.example.android.apis.Path", path);
		        return result;
		    }

		    protected void addItem(List<Map<String, Object>> data, String name, Intent intent) {
		        Map<String, Object> temp = new HashMap<String, Object>();
		        temp.put("title", name);
		        temp.put("intent", intent);
		        data.add(temp);
		    }

		    @Override
		    @SuppressWarnings("unchecked")
		    protected void onListItemClick(ListView l, View v, int position, long id) {
		        Map<String, Object> map = (Map<String, Object>)l.getItemAtPosition(position);

		        Intent intent = (Intent) map.get("intent");
		        startActivity(intent);
		    }
}
