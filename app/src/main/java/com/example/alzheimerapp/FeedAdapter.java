package com.example.alzheimerapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class   FeedAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Feed> feedList;

    public FeedAdapter(Context context, int layout, ArrayList<Feed> feedList) {
        this.context = context;
        this.layout = layout;
        this.feedList = feedList;
    }

    @Override
    public int getCount() {
        return feedList.size();
    }

    @Override
    public Object getItem(int position) {
        return feedList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        ImageView imageView;
        TextView tvSubject, tvThoughts;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        ViewHolder holder = new ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.tvSubject = (TextView) row.findViewById(R.id.textView);
            holder.tvThoughts = (TextView) row.findViewById(R.id.textView2);
            holder.imageView = (ImageView) row.findViewById(R.id.imageView);
            row.setTag(holder);
        }
        else{
            holder = (ViewHolder) row.getTag();
        }

        Feed feed = feedList.get(position);

        
        holder.tvSubject.setText(feed.getSubject());
        holder.tvThoughts.setText(feed.getThoughts());

        byte[] feedImage = feed.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(feedImage, 0, feedImage.length);
        holder.imageView.setImageBitmap(bitmap);

        return row;
    }
}
