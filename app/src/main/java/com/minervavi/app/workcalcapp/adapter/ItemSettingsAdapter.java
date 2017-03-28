package com.minervavi.app.workcalcapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.minervavi.app.workcalcapp.R;

import java.util.ArrayList;

/**
 * Created by victo on 27/03/2017.
 */

public class ItemSettingsAdapter extends ArrayAdapter<String> {

    Context     mContext;
    private int lastPosition = -1;

    private static class ViewHolder {
        TextView tvItemList;
    }

    public ItemSettingsAdapter(ArrayList<String> data, Context context) {
        super(context, R.layout.row_listview, data);
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder  viewHolder;
        LayoutInflater inflater;
        final View  result;
        String      item = getItem(position);

        if (convertView == null) {
            viewHolder              = new ViewHolder();
            inflater                = LayoutInflater.from(getContext());
            convertView             = inflater.inflate(R.layout.row_listview, parent, false);
            viewHolder.tvItemList   = (TextView) convertView.findViewById(R.id.tvItemList);

            result                  = convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder  = (ViewHolder) convertView.getTag();
            result      = convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition        = position;

        viewHolder.tvItemList.setText(item);
        return convertView;
    }
}
