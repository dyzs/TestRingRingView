package com.dyzs.testringringview;

import android.content.Context;
import android.graphics.Color;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by maidou on 2015/12/31.
 */
public class CloudTagAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> mList;
    private int mCurTimeMillis = -100;
    private int mPosition = 0;

    private int millis_1 = -200;
    private int millis_2 = 0;
    private int millis_3 = 200;
    private int millis_4 = 300;

    public CloudTagAdapter(Context context, ArrayList<String> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public String getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        int color = mList.get(position).hashCode() | 0xF0000000 & 0xFFF5F5F5;

        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_cloud_tag, null);
            RingRingViewVer2 ll_ring = (RingRingViewVer2) convertView.findViewById(R.id.ll_cloud_tag_ring);

            ll_ring.setTextColor(color);
            ll_ring.setRingColor(color);
            ll_ring.setTextSize(mContext.getResources().getDimension(R.dimen.ringview_textsize));
            ll_ring.setRingWidth(2f);
            ll_ring.setIsDrawRingProgress(false);

            viewHolder = new ViewHolder();
            viewHolder.ll_cloud_tag_ring = ll_ring;
            viewHolder.tv_cloud_tag_name = (TextView) convertView.findViewById(R.id.tv_cloud_tag_name);
            convertView.setTag(viewHolder);
        } else {
            // mCurTimeMillis = 100;
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.ll_cloud_tag_ring.setProgress(new Random().nextInt(100) + 1);// 设置当前进度
        viewHolder.tv_cloud_tag_name.setText(mList.get(position));
        viewHolder.tv_cloud_tag_name.setTextColor(color);
//      为什么这个才是第四列
//        if (mPosition == (1 + 4*((mPosition - 1) / 4))) {
//            millis_1 += 200;
//            actAnimation1(convertView, millis_1);
//        }
//      为什么这个才是第一列
        if (mPosition == (2 + 4*((mPosition - 1) / 4))) {
            millis_2 += 200;
            actAnimation1(convertView, millis_2);
        }
//      为什么这个才是第二列
        if (mPosition == (3 + 4*((mPosition - 1) / 4))) {
            millis_3 += 200;
            actAnimation1(convertView, millis_3);
        }
//      为什么这个才是第三列
        if (mPosition == (4 + 4*((mPosition - 1) / 4))) {
            millis_4 += 200;
            actAnimation1(convertView, millis_4);
        }
//
        mPosition += 1;


//        mPosition += 1;
//        mCurTimeMillis += 100;
//        if (mPosition % 2 == 0) {
//            mCurTimeMillis = 50;
//        }
//
//        actAnimation(convertView);
        return convertView;

//        121212的方法
//        mPosition += 1;
//        mCurTimeMillis += 100;
//        if (mPosition % 2 == 0) {
//            mCurTimeMillis = 50;
//        }

        // 200 millis   %2              2
        // 200 millis   %4              最佳效果(表示当前的列数 columns)

//        if (convertView == null) {
//            mCurTimeMillis += 100;
//            mPosition += 1;
//            convertView = View.inflate(mContext, R.layout.item_cloud_tag, null);
//            // 得到 RingView 对象
//            RingRingViewVer2 ll_ring = (RingRingViewVer2) convertView.findViewById(R.id.ll_cloud_tag_ring);
//
//            ll_ring.setTextColor(color);
//            ll_ring.setRingColor(color);
////            ll_ring.setTextSize(DensityUtils.sp2px(mContext, 40f));
//            ll_ring.setTextSize(mContext.getResources().getDimension(R.dimen.ringview_textsize));
//            ll_ring.setRingWidth(2f);
//            ll_ring.setIsDrawRingProgress(false);
//
//
//            viewHolder = new ViewHolder();
//            viewHolder.ll_cloud_tag_ring = ll_ring;
//            viewHolder.tv_cloud_tag_name = (TextView) convertView.findViewById(R.id.tv_cloud_tag_name);
//            convertView.setTag(viewHolder);
//        } else {
//            mCurTimeMillis = 100;
//            viewHolder = (ViewHolder)convertView.getTag();
//        }
//        viewHolder.ll_cloud_tag_ring.setProgress(new Random().nextInt(100) + 1);// 设置当前进度
//        viewHolder.tv_cloud_tag_name.setText(mList.get(position));
//        viewHolder.tv_cloud_tag_name.setTextColor(color);
//
//
////        final RingRingViewVer2 rrrr = ((RingRingViewVer2)(viewHolder.ll_cloud_tag_ring.getChildAt(0)));
////        new Thread(new Runnable() {
////            @Override
////            public void run() {
////                SystemClock.sleep(200);
////                int progress = 0;
////                int random = new Random().nextInt(100);
////                while (progress < random) {
////                    progress += 1;
////                    rrrr.setProgress(progress);
////                    SystemClock.sleep(40);
////                }
////            }
////        }).start();
//        actAnimation(convertView);
//        System.out.println("curTimeMill:" + mCurTimeMillis);
//        return convertView;
    }

    static class ViewHolder{
        TextView tv_cloud_tag_name;
        RingRingViewVer2 ll_cloud_tag_ring;
    }

    public static RingRingViewVer2 getRingView(Context context,int color) {
        RingRingViewVer2 rrv = new RingRingViewVer2(context);
        rrv.setTextColor(color);
        rrv.setRingColor(color);
        rrv.setTextSize(DensityUtils.px2sp(context, 120));
        rrv.setRingWidth(3f);
        rrv.setIsDrawRingProgress(false);
        return rrv;
    }

    private void actAnimation(View convertView) {
//        ViewHelper.setTranslationX(convertView, -100f);
//        ViewPropertyAnimator.animate(convertView).setInterpolator(new OvershootInterpolator()).translationX(0f).setDuration(300).setStartDelay(mCurTimeMillis);
        ViewHelper.setScaleX(convertView, 0.0f);    // 初始缩放为0.1f
        ViewHelper.setScaleY(convertView, 0.0f);    // 初始缩放为0.1f
        ViewHelper.setAlpha(convertView, 0.0f);     // 初始透明为0.1f
        // 执行 0.2 秒从 0.1f 透明度到完全显示
        ViewPropertyAnimator.animate(convertView).setInterpolator(new OvershootInterpolator(2)).alpha(1f).setDuration(700).setStartDelay(mCurTimeMillis);
        // 执行 0.2 秒动画插入器
        ViewPropertyAnimator.animate(convertView).setInterpolator(new OvershootInterpolator(2)).scaleX(1f).setDuration(700).setStartDelay(mCurTimeMillis);
        ViewPropertyAnimator.animate(convertView).setInterpolator(new OvershootInterpolator(2)).scaleY(1f).setDuration(700).setStartDelay(mCurTimeMillis);
//      ----------------------
//        ViewHelper.setScaleX(convertView, 0.5f);    // 初始缩放为0.5f
//        ViewHelper.setScaleY(convertView, 0.5f);    // 初始缩放为0.5f
//        ViewHelper.setAlpha(convertView, 0.5f);
//        // 执行 0.3 秒动画插入器
//        ViewPropertyAnimator.animate(convertView).setInterpolator(new OvershootInterpolator(2)).alpha(1f).setDuration(600).setStartDelay(mCurTimeMillis + 100);
//        ViewPropertyAnimator.animate(convertView).setInterpolator(new OvershootInterpolator(2)).scaleX(1f).setDuration(600).setStartDelay(mCurTimeMillis + 100);
//        ViewPropertyAnimator.animate(convertView).setInterpolator(new OvershootInterpolator(2)).scaleY(1f).setDuration(600).setStartDelay(mCurTimeMillis + 100);
    }

    private void actAnimation1(View convertView, int millis) {
        ViewHelper.setScaleX(convertView, 0.0f);    // 初始缩放为0.1f
        ViewHelper.setScaleY(convertView, 0.0f);    // 初始缩放为0.1f
        ViewHelper.setAlpha(convertView, 0.0f);     // 初始透明为0.1f
        ViewPropertyAnimator.animate(convertView).setInterpolator(new OvershootInterpolator(2)).alpha(1f).setDuration(700).setStartDelay(millis);
        ViewPropertyAnimator.animate(convertView).setInterpolator(new OvershootInterpolator(2)).scaleX(1f).setDuration(700).setStartDelay(millis);
        ViewPropertyAnimator.animate(convertView).setInterpolator(new OvershootInterpolator(2)).scaleY(1f).setDuration(700).setStartDelay(millis);
    }

    private void actAnimation2(View convertView) {
        ViewHelper.setScaleX(convertView, 0.0f);    // 初始缩放为0.1f
        ViewHelper.setScaleY(convertView, 0.0f);    // 初始缩放为0.1f
        ViewHelper.setAlpha(convertView, 0.0f);     // 初始透明为0.1f
        ViewPropertyAnimator.animate(convertView).setInterpolator(new OvershootInterpolator(2)).alpha(1f).setDuration(700).setStartDelay(mCurTimeMillis);
        ViewPropertyAnimator.animate(convertView).setInterpolator(new OvershootInterpolator(2)).scaleX(1f).setDuration(700).setStartDelay(mCurTimeMillis);
        ViewPropertyAnimator.animate(convertView).setInterpolator(new OvershootInterpolator(2)).scaleY(1f).setDuration(700).setStartDelay(mCurTimeMillis);
    }

    private void actAnimation3(View convertView) {
        ViewHelper.setScaleX(convertView, 0.0f);    // 初始缩放为0.1f
        ViewHelper.setScaleY(convertView, 0.0f);    // 初始缩放为0.1f
        ViewHelper.setAlpha(convertView, 0.0f);     // 初始透明为0.1f
        ViewPropertyAnimator.animate(convertView).setInterpolator(new OvershootInterpolator(2)).alpha(1f).setDuration(700).setStartDelay(mCurTimeMillis);
        ViewPropertyAnimator.animate(convertView).setInterpolator(new OvershootInterpolator(2)).scaleX(1f).setDuration(700).setStartDelay(mCurTimeMillis);
        ViewPropertyAnimator.animate(convertView).setInterpolator(new OvershootInterpolator(2)).scaleY(1f).setDuration(700).setStartDelay(mCurTimeMillis);
    }

    private void actAnimation4(View convertView) {
        ViewHelper.setScaleX(convertView, 0.0f);    // 初始缩放为0.1f
        ViewHelper.setScaleY(convertView, 0.0f);    // 初始缩放为0.1f
        ViewHelper.setAlpha(convertView, 0.0f);     // 初始透明为0.1f
        ViewPropertyAnimator.animate(convertView).setInterpolator(new OvershootInterpolator(2)).alpha(1f).setDuration(700).setStartDelay(mCurTimeMillis);
        ViewPropertyAnimator.animate(convertView).setInterpolator(new OvershootInterpolator(2)).scaleX(1f).setDuration(700).setStartDelay(mCurTimeMillis);
        ViewPropertyAnimator.animate(convertView).setInterpolator(new OvershootInterpolator(2)).scaleY(1f).setDuration(700).setStartDelay(mCurTimeMillis);
    }
}
