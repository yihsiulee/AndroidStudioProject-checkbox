package com.example.yihsiu.checkbox;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

//繼承RecyclerView.Adapter
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    //建立一個num ArrayList 來儲存點擊之後判斷為true的值
    public static ArrayList num = new ArrayList<>();
    //把MainActivity.Item的List 命名為mData
    private List<MainActivity.Item> mData;

    //繼承RecyclerView.ViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public CheckBox mCheckBox;

        //ViewHoder可以暫存已選取的檔案 adapter叫viewholder （暫存已選取的檔案）
        public ViewHolder(View v) {
            super(v);
            //連到item_view.xml裡面的info_text＆info_chcekbox
            mTextView = (TextView) v.findViewById(R.id.info_text);
            mCheckBox = (CheckBox) v.findViewById(R.id.info_chcekbox);

        }
    }

//?
    public MyAdapter(List<MainActivity.Item> data) {
        //把data存入mData
        mData = data;
    }


    @Override
    //複寫 onCreateViewHolder 來建立自己的viewholder
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    //onClick(View v) 是 OnClickListener 實作方法，它回傳的 View v 的意思是
    //它給予一個類型 View 的東西，名稱為v，v名稱是可更改的。
    @Override
    //onBindViewHolder由adapter把數值暫存 當捲動時 叫出來顯示在viewHoler上面
    //holder(暫存、hold住)
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        //MainActivity.Item item= position（第幾項）
        MainActivity.Item item = mData.get(position);
        // setText：顯示text
        holder.mTextView.setText(item.getText());
        //用 setChecked() 來設定點選的狀態
        //可以透過 isChecked() 來判斷是否點選
        holder.mCheckBox.setChecked(item.isCheck());
        //按了之後的動作
        holder.mCheckBox.setOnClickListener
                (new View.OnClickListener() {
            @Override//點擊才觸發事件改變
            public void onClick(View view) {

                boolean b = ((CheckBox) view).isChecked();
                holder.mCheckBox.setChecked(b);
                mData.get(position).setCheck(b);
                //點擊之後判斷是不是true 是的話存入num
                if(holder.mCheckBox.isChecked()==true) {
                    //抓位置
                    int a=holder.getAdapterPosition();
                    num.add(a+1);
                }
                //點擊之後判斷是不是false 是的話刪除num裡面的值
                if(holder.mCheckBox.isChecked()==false) {
                    //抓位置
                    int aa=holder.getAdapterPosition();
                    //當取消勾選時，刪除內部的值
                    for(int i = 0, len = num.size(); i < len; i++){
                        if(num.get(i).equals(aa+1)){
                            num.remove(i);
                            len--;
                            i--;
                        }
                    }
                }
            }
        });



    }



    @Override
    public int getItemCount() {
        return mData.size();
    }

}
