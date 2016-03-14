package com.ichoice.egan.eganview.listview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.ichoice.egan.eganview.R;
import com.ichoice.egan.eganview.base.BaseActivity;

import java.util.ArrayList;

public class ListViewActivity extends BaseActivity {

    private ListView listView;
    private ListViewAdapter listViewAdapter;
    private ArrayList<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add("item: " + (i + 1));
        }

        listView = ((ListView) findViewById(R.id.listView));

        listViewAdapter = new ListViewAdapter(this, data, getWindowManager().getDefaultDisplay().getWidth());
        listView.setAdapter(listViewAdapter);
    }

}
