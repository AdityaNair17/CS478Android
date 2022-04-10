package com.example.aditya_prakash_nair_project2;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class ImageAdapter extends BaseAdapter{

    private static final int PADDING = 4;
    private static final int WIDTH = 350;
    private static final int HEIGHT = 350;
    private Context context;
    private List<Integer> imageList;
    private HashMap<Integer,String> map;


    public ImageAdapter(Context c, List<Integer> imgList, HashMap<Integer,String> map){
        this.context = c;
        this.imageList = imgList;
        this.map = map;
        Log.i("Adapter Activity", "First Nmae = " + this.map.get(imageList.get(0)));
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public Object getItem(int index) {
        return imageList.get(index);
    }

    @Override
    public long getItemId(int i) {
        return imageList.get(i);
    }

    @Override
    public View getView(int index, View view, ViewGroup viewGroup) {
//        ImageView imageView = (ImageView) view;

//        if(imageView == null){
//            imageView = new ImageView(context);
//            imageView.setLayoutParams(new GridView.LayoutParams(WIDTH, HEIGHT));
//            imageView.setPadding(PADDING, PADDING, PADDING, PADDING);
//            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
//        }
//
//        imageView.setImageResource(imageList.get(index));
//        return imageView;

        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if(view == null){
            view = inflater.inflate(R.layout.image_text_layout,null);
            ImageView imageView = view.findViewById(R.id.image);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(WIDTH, HEIGHT));
//            imageView.setPadding(PADDING, PADDING, PADDING, PADDING);
            imageView.setScaleType(ImageView.ScaleType.FIT_START);
        }

        ImageView imageView = view.findViewById(R.id.image);
        imageView.setImageResource(imageList.get(index));
        TextView textView = view.findViewById(R.id.animalName);
        textView.setText(this.map.get(imageList.get(index)));
//        Log.i("Adapter","Name = "+ this.map.get(imageList.get(index)));
//        Log.i("Adapter Activity", "First Nmae = " + this.map.get(imageList.get(0)));
        return view;
    }



}
