package com.newsuper.code.goaway;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.newsuper.code.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class UsageListAdapter extends RecyclerView.Adapter<UsageListAdapter.ViewHolder> {

    private List<MyUsageStats> mCustomUsageStatsList = new ArrayList<>();
    private Random mRandom = new Random();
    private Context mContext;
    private int[] mColors;

    public UsageListAdapter(Context context) {
        this.mContext = context;
        this.mColors = TimeUtil.getColorArray(context);
    }

    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    static class ViewHolder extends RecyclerView.ViewHolder {
        private final CardView mCardView;
        private final TextView mPackageName;
        private final TextView mUsageInfo;
        private final ImageView mAppIcon;

        private ViewHolder(View v) {
            super(v);
            mCardView = v.findViewById(R.id.cardview);
            mPackageName = v.findViewById(R.id.package_text);
            mUsageInfo = v.findViewById(R.id.usage_text);
            mAppIcon = v.findViewById(R.id.app_icon);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.usage_card, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        MyUsageStats myUsageStats = mCustomUsageStatsList.get(position);
        viewHolder.mCardView.setCardBackgroundColor(mColors[mRandom.nextInt(5)]);
        viewHolder.mPackageName.setText(AppsUtil.getAppName(mContext, myUsageStats.usageStats.getPackageName()));
        String time = TimeUtil.timeToString(myUsageStats.usageStats.getTotalTimeInForeground());
        viewHolder.mUsageInfo.setText(time);
        viewHolder.mAppIcon.setImageDrawable(myUsageStats.appIcon);
    }

    @Override
    public int getItemCount() {
        return mCustomUsageStatsList.size();
    }

    public void setCustomUsageStatsList(List<MyUsageStats> customUsageStats) {
        mCustomUsageStatsList = customUsageStats;
    }
}