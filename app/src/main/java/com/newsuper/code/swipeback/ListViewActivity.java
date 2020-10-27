package com.newsuper.code.swipeback;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.newsuper.code.R;

import java.util.ArrayList;
import java.util.List;


public class ListViewActivity extends BaseSwipeActivity {
    private ListView mListView;
    private List<String> dataList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_listview;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListView = (ListView) findViewById(R.id.listView);
        dataList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            dataList.add("Test" + i);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter(this,
                android.R.layout.simple_expandable_list_item_1,
                dataList);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getApplicationContext(),
                        dataList.get(position), Toast.LENGTH_LONG)
                        .show();
            }
        });
    }
}