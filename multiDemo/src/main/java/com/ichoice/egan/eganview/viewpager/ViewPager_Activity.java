package com.ichoice.egan.eganview.viewpager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ichoice.egan.eganview.R;

public class ViewPager_Activity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_);
        getSupportActionBar().hide();
        String s = "2.1.0";
        Toast.makeText(this, s.replace(".", ""), Toast.LENGTH_SHORT).show();

        findViewById(R.id.tv_1).setOnClickListener(this);
        findViewById(R.id.tv_2).setOnClickListener(this);
        findViewById(R.id.tv_3).setOnClickListener(this);
        findViewById(R.id.tv_4).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, ViewPagerActivity.class);
        switch (v.getId()) {
            case R.id.tv_1:
                intent.putExtra("flag", 1);
                break;
            case R.id.tv_2:
                intent.putExtra("flag", 2);
                break;
            case R.id.tv_3:
                intent.putExtra("flag", 3);
                break;
            case R.id.tv_4:
                intent.putExtra("flag", 4);
                break;
        }
        startActivity(intent);
    }
}
