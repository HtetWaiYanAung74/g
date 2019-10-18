package com.raywenderlich.allthenews;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by brandonmanson on 9/7/16.
 */
public class NewsAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<News> mDataSource;
    private static final HashMap<String, Integer> LABEL_COLORS = new HashMap<String, Integer>() {{
        put("Megan Rose Dickey", R.color.colorLowCarb);
        put("Brian Heater", R.color.colorLowFat);
        put("Kirsten Korosec", R.color.colorLowSodium);
        put("Anthony Ha", R.color.colorMediumCarb);
        put("Danny Crichton", R.color.colorVegetarian);
        put("Darrell Etherington", R.color.colorBalanced);
        put("Sarah Perez",R.color.colorLowCarb);
        put("Ron Miller",R.color.colorLowFat);
        put("Zack Whittaker",R.color.colorLowSodium);
        put("Darrell Etherington",R.color.colorMediumCarb);
    }};

    public NewsAdapter(Context context, ArrayList<News> items) {
        mContext = context;
        mDataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mDataSource.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item_news, parent, false);

            holder = new ViewHolder();
            holder.thumbnailImageView = (ImageView) convertView.findViewById(R.id.news_list_thumbnail);
            holder.titleTextView = (TextView) convertView.findViewById(R.id.news_list_title);
            holder.subtitleTextView = (TextView) convertView.findViewById(R.id.news_list_subtitle);
            holder.detailTextView = (TextView) convertView.findViewById(R.id.news_list_detail);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        TextView titletextView = holder.titleTextView;
        TextView subtitleView = holder.subtitleTextView;
        TextView detailTextView = holder.detailTextView;
        ImageView thumbnailImageView = holder.thumbnailImageView;

        News news = (News) getItem(position);


        titletextView.setText(news.title);
        subtitleView.setText(news.description);
        detailTextView.setText(news.label);

        Picasso.with(mContext).load(news.imageUrl).placeholder(R.mipmap.ic_launcher).into(thumbnailImageView);

        Typeface titleTypeFace = Typeface.createFromAsset(mContext.getAssets(), "fonts/JosefinSans-Bold.ttf");
        titletextView.setTypeface(titleTypeFace);

        Typeface subtitleTypeFace = Typeface.createFromAsset(mContext.getAssets(), "fonts/JosefinSans-SemiBoldItalic.ttf");
        subtitleView.setTypeface(subtitleTypeFace);

        Typeface detailTypeFace = Typeface.createFromAsset(mContext.getAssets(), "fonts/Quicksand-Bold.otf");
        detailTextView.setTypeface(detailTypeFace);

        detailTextView.setTextColor(ContextCompat.getColor(mContext, LABEL_COLORS.get(news.label)));

        return convertView;
    }

    private static class ViewHolder {
        public TextView titleTextView;
        public TextView subtitleTextView;
        public TextView detailTextView;
        public ImageView thumbnailImageView;
    }

}
