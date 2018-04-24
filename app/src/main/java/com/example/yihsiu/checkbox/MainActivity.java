package com.example.yihsiu.checkbox;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //製造100個checkbox的Item 存放在myDatest的ArrayList裡面
        ArrayList<Item> myDataset = new ArrayList<>();
        for (int i = 1; i < 99; i++) {
            Item item = new Item();
            item.setText(i + "");
            myDataset.add(item);
        }

        MyAdapter myAdapter = new MyAdapter(myDataset);
        //連到activity_main.xml裡面的list_view (RecyclerView)
        RecyclerView mList = (RecyclerView) findViewById(R.id.list_view);
        //建立LinearLayoutManage的物件
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //Orientation定位 應該是設定個參數吧
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mList.setLayoutManager(layoutManager);
        mList.setAdapter(myAdapter);
    }


    public class Item {
        String text;
        boolean check;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public boolean isCheck() {
            return check;
        }

        public void setCheck(boolean check) {
            this.check = check;
        }

    }

    public void buttonOnClick(View view) {
            //點擊後要產生什麼訊息
            Toast toast = Toast.makeText(this, "已選取"+MyAdapter.num, Toast.LENGTH_SHORT);
            //顯示出來
            toast.show();
            //此行可讓程式不crash
            //Button button = (Button) view;

        }

//以下皆為裝menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Within onCreateOptionsMenu, use getMenuInflater().inflate to inflate the menu
        //inflate充氣 充值
        getMenuInflater().inflate(R.menu.main, menu);
        //Return true to display your menu
        return true;
    }

    //複寫onOptionsItemSelected
    //Within onOptionsItemSelected, get the ID of the item that was selected
    //If the item's ID is R.id.action_search, show a Toast and return true to tell Android that you've handled this menu click
    //Don't forgot to call .show() on your Toast
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();

        if (itemThatWasClickedId == R.id.action_search) {
            //點擊後要產生什麼訊息
            Toast toast = Toast.makeText(this, "已選取"+MyAdapter.num, Toast.LENGTH_SHORT);
            //顯示出來
            toast.show();
            //此行可讓程式不crash
            //Button button = (Button) view;
        }
        //If you do NOT handle the menu click, return super.onOptionsItemSelected to let Android handle the menu click
        return super.onOptionsItemSelected(item);
    }
    }
