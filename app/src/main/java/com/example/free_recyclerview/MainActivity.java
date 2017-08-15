package com.example.free_recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.free_recyclerview.adapter.HeaderWrapper;
import com.example.free_recyclerview.adapter.TabAdapter;
import com.example.free_recyclerview.bean.Data;
import com.example.free_recyclerview.view.DividerItemDecoration;
import com.example.free_recyclerview.view.FreeRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    FreeRecyclerView rv;
    private List<Data> objects;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wide_main);
        rv = (FreeRecyclerView) findViewById(R.id.rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        rv.setLayoutManager(layoutManager);

        initContent();//

        TabAdapter adapter = new TabAdapter(objects,rv);
        HeaderWrapper headerWrapper = new HeaderWrapper(rv,adapter);//addHeaderView adapter

        View titleView = getTitleView();
        addTitle(titleView);

        headerWrapper.addHeaderView(titleView);
        rv.setAdapter(headerWrapper);
        rv.addItemDecoration(new DividerItemDecoration(
                this, DividerItemDecoration.HORIZONTAL_LIST));
    }

    //设置title内容
    private void addTitle(View view){

        LinearLayout tab_root = (LinearLayout) view.findViewById(R.id.tab_root);//填充标题(除第一列 第一行之外)
        int len = 11;
        String[] strs = {"cpu1","cpu2","cpu3","cpu4","cpu5","cpu6","cpu7","cpu8","cpu9","cpu10","cpu11"};
        for (int i = 0;i< len;i++){
            View v = View.inflate(this,R.layout.wide_table,null);
            TextView textView = (TextView) v.findViewById(R.id.wide_content);
            textView.setText(strs[i]);
            tab_root.addView(v);
        }
    }
    private void initContent(){
        objects = new ArrayList<Data>();
        for (int i = 0;i <100; i++){
            Data data = new Data();
            data.setName("iphone "+ (i+1));//左边的一列
            String[] strs = {"A1","A2","A3","A4","A5","A6","A7","A8","A9","A10","A11"};//一行
            data.setArray(strs);
            objects.add(data);
        }
    }
    private View getTitleView(){
        View view = View.inflate(this,R.layout.wide_item,null);
        view.setBackgroundColor(getResources().getColor(R.color.colorGary_1));
        TextView textView = (TextView) view.findViewById(R.id.tv);// 第一列 第一行
        textView.setText("设备");
        textView.setBackgroundColor(getResources().getColor(R.color.colorGary_1));

        LinearLayout tab_root = (LinearLayout) view.findViewById(R.id.tab_root);//填充标题(除第一列 第一行之外)
        return view;
    }
}
