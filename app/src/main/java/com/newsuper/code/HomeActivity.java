package com.newsuper.code;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.newsuper.code.blurimg.BlurImgSwitcherActivity;
import com.newsuper.code.bollon.BollonActivity;
import com.newsuper.code.bottompop.BottomPopActivity;
import com.newsuper.code.cardswipe.CardSwipeActivity;
import com.newsuper.code.combine.CombineBitmapActivity;
import com.newsuper.code.dianzan.DianzanActivity;
import com.newsuper.code.dianzan.pop.PopLikeActivity;
import com.newsuper.code.downloader.DownloadActivity;
import com.newsuper.code.fitimg.FitImgActivity;
import com.newsuper.code.flibtab.FlibTabActivity;
import com.newsuper.code.flourish.FlourishActivity;
import com.newsuper.code.gank.ui.GankActivity;
import com.newsuper.code.goaway.GoAwayActivity;
import com.newsuper.code.home.BaseExpandableRecyclerViewAdapter;
import com.newsuper.code.jinyun.JinyunActivity;
import com.newsuper.code.loadAnimation.LoadAnimationActivity;
import com.newsuper.code.matisse.MatissePhotoActivity;
import com.newsuper.code.search.JianShuSearchActivity;
import com.newsuper.code.slitab.LadderTextActivity;
import com.newsuper.code.supertextview.SuperTextActivity;
import com.newsuper.code.swipe.LiquidSwipeActivity;
import com.newsuper.code.swipeback.SwipeBackActivity;
import com.newsuper.code.tabscroll.TabScrollActivity;
import com.newsuper.code.textpathview.PathActivity;
import com.newsuper.code.tree.TreeActivity;
import com.newsuper.code.wechat.WechatShouyeActivity;
import com.newsuper.code.whiteboard.WhiteBoardActivity;
import com.newsuper.code.xuanfu.XuanFuWindowAty;
import com.newsuper.code.zhihu.ZhiHuViewActivity;
import com.newsuper.code.zhihu.ZhihuKaipingActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView mRvList;
    private MainExpandableAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mRvList = (RecyclerView) findViewById(R.id.rvList);
        List<GroupBean> groupBeans = initGroupData();
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MainExpandableAdapter(groupBeans);
        mAdapter.setListener(new BaseExpandableRecyclerViewAdapter.ExpandableRecyclerViewOnClickListener<GroupBean, ChildBean>() {
            @Override
            public boolean onGroupLongClicked(GroupBean groupItem) {
                return false;
            }

            @Override
            public boolean onInterceptGroupExpandEvent(GroupBean groupItem, boolean isExpand) {
                return false;
            }

            @Override
            public void onGroupClicked(GroupBean groupItem) {
                mAdapter.setSelectedChildBean(groupItem);
            }

            @Override
            public void onChildClicked(GroupBean groupItem, ChildBean childItem) {
//                if (childItem.mIconId == R.drawable.xiaoshipin) {
//                    Intent intent = new Intent(Intent.ACTION_VIEW);
//                    intent.setData(Uri.parse("http://dldir1.qq.com/hudongzhibo/xiaozhibo/XiaoShiPin.apk"));
//                    startActivity(intent);
//                    return;
//                } else if (childItem.mIconId == R.drawable.xiaozhibo) {
//                    Intent intent = new Intent(Intent.ACTION_VIEW);
//                    intent.setData(Uri.parse("http://dldir1.qq.com/hudongzhibo/xiaozhibo/xiaozhibo.apk"));
//                    startActivity(intent);
//                    return;
//                }
                Intent intent = new Intent(HomeActivity.this, childItem.getTargetClass());
                intent.putExtra("TITLE", childItem.mName);
                intent.putExtra("TYPE", childItem.mType);
                HomeActivity.this.startActivity(intent);
            }
        });
        mRvList.setAdapter(mAdapter);
    }

    private List<GroupBean> initGroupData() {
        List<GroupBean> groupList = new ArrayList<>();

//
//        // 直播
//        List<ChildBean> pusherChildList = new ArrayList<>();
//        pusherChildList.add(new ChildBean("MLVBLiveRoom", R.drawable.room_live, 0, LiveRoomActivity.class));
//        pusherChildList.add(new ChildBean("摄像头推流", R.drawable.push, 0, CameraPushEntranceActivity.class));
//        pusherChildList.add(new ChildBean("直播播放器", R.drawable.live, 0, LivePlayerEntranceActivity.class));
//        if (pusherChildList.size() != 0) {
//            // 这个是网页链接，配合build.sh避免在如ugc_smart版中出现
//            pusherChildList.add(new ChildBean("小直播", R.drawable.xiaozhibo, 0, null));
//            GroupBean pusherGroupBean = new GroupBean("移动直播", R.drawable.room_live, pusherChildList);
//            groupList.add(pusherGroupBean);
//        }
//
//        // 初始化播放器
//        List<ChildBean> playerChildList = new ArrayList<>();
//        playerChildList.add(new ChildBean("超级播放器", R.drawable.play, 3, SuperPlayerActivity.class));
//        if (playerChildList.size() != 0) {
//            GroupBean playerGroupBean = new GroupBean("播放器", R.drawable.composite, playerChildList);
//            groupList.add(playerGroupBean);
//        }
//
//        // 短视频
//        List<ChildBean> shortVideoChildList = new ArrayList<>();
//        shortVideoChildList.add(new ChildBean("视频录制", R.drawable.video, 0, TCVideoSettingActivity.class));
//        shortVideoChildList.add(new ChildBean("特效编辑", R.drawable.cut, 0, TCVideoPickerActivity.class));
//        shortVideoChildList.add(new ChildBean("视频拼接", R.drawable.composite, TCVideoJoinChooseActivity.TYPE_MULTI_CHOOSE, TCVideoJoinChooseActivity.class));
//        shortVideoChildList.add(new ChildBean("图片转场", R.drawable.short_video_picture, TCVideoJoinChooseActivity.TYPE_MULTI_CHOOSE_PICTURE, TCVideoJoinChooseActivity.class));
//        shortVideoChildList.add(new ChildBean("视频上传", R.drawable.update, TCVideoJoinChooseActivity.TYPE_PUBLISH_CHOOSE, TCVideoJoinChooseActivity.class));
//
//        if (shortVideoChildList.size() != 0) {
//            // 这个是网页链接，配合build.sh避免在其他版本中出现
//            shortVideoChildList.add(new ChildBean("小视频", R.drawable.xiaoshipin, 0, null));
//            GroupBean shortVideoGroupBean = new GroupBean("短视频", R.drawable.video, shortVideoChildList);
//            groupList.add(shortVideoGroupBean);
//        }

        List<ChildBean> videoConnectChildList = new ArrayList<>();
        videoConnectChildList.add(new ChildBean("高仿网易严选底部弹出菜单", R.drawable.room_multi, 1, BottomPopActivity.class));
        videoConnectChildList.add(new ChildBean("梯形textview", R.drawable.room_multi, 1, LadderTextActivity.class));
        videoConnectChildList.add(new ChildBean("仿支付宝tab选择滑动子View效果", R.drawable.room_multi, 1, TabScrollActivity.class));
        videoConnectChildList.add(new ChildBean("页面翻转动画", R.drawable.room_multi, 1, FlourishActivity.class));
        videoConnectChildList.add(new ChildBean("blurimg", R.drawable.room_multi, 1, BlurImgSwitcherActivity.class));
        videoConnectChildList.add(new ChildBean("Flibtab", R.drawable.room_multi, 1, FlibTabActivity.class));
        videoConnectChildList.add(new ChildBean("Viewpager滑动波浪动画", R.drawable.room_multi, 1, LiquidSwipeActivity.class));
        videoConnectChildList.add(new ChildBean("防沉迷", R.drawable.room_multi, 1, GoAwayActivity.class));
        videoConnectChildList.add(new ChildBean("释放内存", R.drawable.room_multi, 1, BollonActivity.class));
        videoConnectChildList.add(new ChildBean("扩展Textview", R.drawable.room_multi, 1, SuperTextActivity.class));
        videoConnectChildList.add(new ChildBean("文字转路径动画", R.drawable.room_multi, 0, PathActivity.class));
        videoConnectChildList.add(new ChildBean("滑动关闭", R.drawable.room_multi, 0, SwipeBackActivity.class));
        videoConnectChildList.add(new ChildBean("知乎view", R.drawable.room_multi, 0, ZhiHuViewActivity.class));
        videoConnectChildList.add(new ChildBean("知乎开屏效果", R.drawable.room_multi, 0, ZhihuKaipingActivity.class));
        videoConnectChildList.add(new ChildBean("简书搜索框", R.drawable.room_multi, 0, JianShuSearchActivity.class));
        videoConnectChildList.add(new ChildBean("微信首页下拉效果", R.drawable.room_multi, 0, WechatShouyeActivity.class));
        videoConnectChildList.add(new ChildBean("仿Instagram头像点击加载动画", R.drawable.room_multi, 0, LoadAnimationActivity.class));
        videoConnectChildList.add(new ChildBean("仿探探卡片式滑动效果", R.drawable.room_multi, 0, CardSwipeActivity.class));
        videoConnectChildList.add(new ChildBean("仿小红书登录页自动滚动效果", R.drawable.room_multi, 0, FitImgActivity.class));
        videoConnectChildList.add(new ChildBean("知乎matisse", R.drawable.room_multi, 0, MatissePhotoActivity.class));
        videoConnectChildList.add(new ChildBean("自定义层次组织架构图", R.drawable.room_multi, 0, TreeActivity.class));
        videoConnectChildList.add(new ChildBean("点赞动画", R.drawable.room_multi, 0, DianzanActivity.class));
        videoConnectChildList.add(new ChildBean("点赞pop", R.drawable.room_multi, 0, PopLikeActivity.class));
        videoConnectChildList.add(new ChildBean("涂鸦画板", R.drawable.room_multi, 0, WhiteBoardActivity.class));
        videoConnectChildList.add(new ChildBean("悬浮窗效果", R.drawable.room_multi, 0, XuanFuWindowAty.class));
        videoConnectChildList.add(new ChildBean("Gank", R.drawable.multi_meeting, 0, GankActivity.class));
        videoConnectChildList.add(new ChildBean("仿网易鲸云动效", R.drawable.room_multi, 0, JinyunActivity.class));
        videoConnectChildList.add(new ChildBean("断点续传下载器", R.drawable.room_multi, 0, DownloadActivity.class));
        videoConnectChildList.add(new ChildBean("仿钉钉群聊头像", R.drawable.room_multi, 0, CombineBitmapActivity.class));

        //videoConnectChildList.add(new ChildBean("滑动tab转盘未写", R.drawable.room_multi, 0, CombineBitmapActivity.class));
        // videoConnectChildList.add(new ChildBean("Lottie动画", R.drawable.room_multi, 0, .class));
        if (videoConnectChildList.size() != 0) {
            GroupBean videoConnectGroupBean = new GroupBean("主入口", R.drawable.room_multi, videoConnectChildList);
            groupList.add(videoConnectGroupBean);
        }

        return groupList;
    }


    private static class MainExpandableAdapter extends BaseExpandableRecyclerViewAdapter<GroupBean, ChildBean, GroupVH, ChildVH> {
        private List<GroupBean> mListGroupBean;
        private GroupBean mGroupBean;

        public void setSelectedChildBean(GroupBean groupBean) {
            boolean isExpand = isExpand(groupBean);
            if (mGroupBean != null) {
                GroupVH lastVH = getGroupViewHolder(mGroupBean);
                if (!isExpand)
                    mGroupBean = groupBean;
                else
                    mGroupBean = null;
                notifyItemChanged(lastVH.getAdapterPosition());
            } else {
                if (!isExpand)
                    mGroupBean = groupBean;
                else
                    mGroupBean = null;
            }
            if (mGroupBean != null) {
                GroupVH currentVH = getGroupViewHolder(mGroupBean);
                notifyItemChanged(currentVH.getAdapterPosition());
            }
        }

        public MainExpandableAdapter(List<GroupBean> list) {
            mListGroupBean = list;
        }

        @Override
        public int getGroupCount() {
            return mListGroupBean.size();
        }

        @Override
        public GroupBean getGroupItem(int groupIndex) {
            return mListGroupBean.get(groupIndex);
        }

        @Override
        public GroupVH onCreateGroupViewHolder(ViewGroup parent, int groupViewType) {
            return new GroupVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.module_entry_item, parent, false));
        }

        @Override
        public void onBindGroupViewHolder(GroupVH holder, GroupBean groupBean, boolean isExpand) {
            holder.textView.setText(groupBean.mName);
            holder.ivLogo.setImageResource(groupBean.mIconId);
            if (mGroupBean == groupBean) {
                holder.itemView.setBackgroundResource(R.color.main_item_selected_color);
            } else {
                holder.itemView.setBackgroundResource(R.color.main_item_unselected_color);
            }
        }

        @Override
        public ChildVH onCreateChildViewHolder(ViewGroup parent, int childViewType) {
            return new ChildVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.module_entry_child_item, parent, false));
        }

        @Override
        public void onBindChildViewHolder(ChildVH holder, GroupBean groupBean, ChildBean childBean) {
            holder.textView.setText(childBean.getName());

            if (groupBean.mChildList.indexOf(childBean) == groupBean.mChildList.size() - 1) {//说明是最后一个
                holder.divideView.setVisibility(View.GONE);
            } else {
                holder.divideView.setVisibility(View.VISIBLE);
            }

        }
    }


    public static class GroupVH extends BaseExpandableRecyclerViewAdapter.BaseGroupViewHolder {
        ImageView ivLogo;
        TextView textView;

        GroupVH(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.name_tv);
            ivLogo = (ImageView) itemView.findViewById(R.id.icon_iv);
        }

        @Override
        protected void onExpandStatusChanged(RecyclerView.Adapter relatedAdapter, boolean isExpanding) {
        }

    }

    public static class ChildVH extends RecyclerView.ViewHolder {
        TextView textView;
        View divideView;

        ChildVH(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.name_tv);
            divideView = itemView.findViewById(R.id.item_view_divide);
        }

    }

    private class GroupBean implements BaseExpandableRecyclerViewAdapter.BaseGroupBean<ChildBean> {
        private String mName;
        private List<ChildBean> mChildList;
        private int mIconId;

        public GroupBean(String name, int iconId, List<ChildBean> list) {
            mName = name;
            mChildList = list;
            mIconId = iconId;
        }

        @Override
        public int getChildCount() {
            return mChildList.size();
        }

        @Override
        public ChildBean getChildAt(int index) {
            return mChildList.size() <= index ? null : mChildList.get(index);
        }

        @Override
        public boolean isExpandable() {
            return getChildCount() > 0;
        }

        public String getName() {
            return mName;
        }

        public List<ChildBean> getChildList() {
            return mChildList;
        }

        public int getIconId() {
            return mIconId;
        }
    }

    private class ChildBean {
        public String mName;
        public int mIconId;
        public Class mTargetClass;
        public int mType;

        public ChildBean(String name, int iconId, int type, Class targetActivityClass) {
            this.mName = name;
            this.mIconId = iconId;
            this.mTargetClass = targetActivityClass;
            this.mType = type;
        }

        public String getName() {
            return mName;
        }


        public int getIconId() {
            return mIconId;
        }


        public Class getTargetClass() {
            return mTargetClass;
        }
    }

}
