package com.example.wanandroid.ui.home.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.LinearInterpolator;
import android.widget.Scroller;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.example.wanandroid.ui.home.viewholder.ArticleHolder;

/**
 * Created by Administrator on 2017/3/14 0014.
 * E-Mail：543441727@qq.com
 */

public class SwipeRecyclerView extends RecyclerView {

    private static final String TAG = "RecycleView";
    private int maxLength, mTouchSlop;
    private int xDown, yDown, xMove, yMove;
    /**
     * 当前选中的item索引（这个很重要）
     */
    private int curSelectPosition;
    private final Scroller mScroller;

    private ConstraintLayout mCurItemLayout, mLastItemLayout;
    private TextView mLlHidden;// 隐藏部分

    /**
     * 隐藏部分长度
     */
    private int mHiddenWidth;
    /**
     * 记录连续移动的长度
     */
    private int mMoveWidth = 0;
    /**
     * 是否是第一次touch
     */
    private boolean isFirst = true;

    /**
     * 删除的监听事件
     */
    private OnRightClickListener mRightListener;

    public void setRightClickListener(OnRightClickListener listener) {
        this.mRightListener = listener;
    }


    public SwipeRecyclerView(Context context) {
        this(context, null);
    }

    public SwipeRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwipeRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // 滑动到最小距离
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        // 滑动的最大距离
        maxLength = ((int) (180 * context.getResources().getDisplayMetrics().density + 0.5f));
        // 初始化Scroller
        mScroller = new Scroller(context, new LinearInterpolator(context, null));
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        int x = (int) e.getX();
        int y = (int) e.getY();
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN -> {
                // 记录当前按下的坐标
                xDown = x;
                yDown = y;
                // 计算选中哪个Item
                Rect itemRect = new Rect();
                final int count = getChildCount();
                for (int i = 1; i < count; i++) { // TODO: 2023/9/13 从1开始，跳过置顶文章
                    final View child = getChildAt(i);
                    if (child.getVisibility() == View.VISIBLE) {
                        child.getHitRect(itemRect);
                        if (itemRect.contains(x, y)) {
                            curSelectPosition = i;
                            break;
                        }
                    }
                }
                if (isFirst) {// 第一次时，不用重置上一次的Item
                    isFirst = false;
                } else {
                    // 屏幕再次接收到点击时，恢复上一次Item的状态
                    if (mLastItemLayout != null && mMoveWidth > 0) {
                        // 将Item右移，恢复原位
                        scrollRight(mLastItemLayout, (0 - mMoveWidth));
                        // 清空变量
                        mHiddenWidth = 0;
                        mMoveWidth = 0;
                    }
                }

                // 取到当前选中的Item，赋给mCurItemLayout，以便对其进行左移
                View item = getChildAt(curSelectPosition);
                if (item != null) {
                    // 获取当前选中的Item
                    ArticleHolder viewHolder = (ArticleHolder) getChildViewHolder(item);
                    mCurItemLayout = viewHolder.getRootView();
                    // 找到具体元素（这与实际业务相关了~~）
                    mLlHidden = viewHolder.getHiddenView();
                    // 这里将删除按钮的宽度设为可以移动的距离
                    mHiddenWidth = mLlHidden.getWidth();
                }
            }
            case MotionEvent.ACTION_MOVE -> {
                xMove = x;
                yMove = y;
                int dx = xMove - xDown;// 为负时：手指向左滑动；为正时：手指向右滑动。这与Android的屏幕坐标定义有关
                int dy = yMove - yDown;//

                // 左滑
                if (dx < 0 && Math.abs(dx) > mTouchSlop && Math.abs(dy) < mTouchSlop) {
                    int newScrollX = Math.abs(dx);
                    if (mMoveWidth >= mHiddenWidth) {// 超过了，不能再移动了
                        newScrollX = 0;
                    } else if (mMoveWidth + newScrollX > mHiddenWidth) {// 这次要超了，
                        newScrollX = mHiddenWidth - mMoveWidth;
                    }
                    // 左滑，每次滑动手指移动的距离
                    scrollLeft(mCurItemLayout, newScrollX);
                    // 对移动的距离叠加
                    mMoveWidth = mMoveWidth + newScrollX;
                } else if (dx > 0) {// 右滑
                    // 执行右滑，这里没有做跟随，瞬间恢复
                    scrollRight(mCurItemLayout, 0 - mMoveWidth);
                    mMoveWidth = 0;
                }
            }
            case MotionEvent.ACTION_UP -> {// 手抬起时
                int scrollX = mCurItemLayout.getScrollX();
                if (mHiddenWidth > mMoveWidth) {
                    int toX = (mHiddenWidth - mMoveWidth);
                    if (scrollX > mHiddenWidth / 2) {// 超过一半长度时松开，则自动滑到左侧
                        scrollLeft(mCurItemLayout, toX);
                        mMoveWidth = mHiddenWidth;
                    } else {// 不到一半时松开，则恢复原状
                        scrollRight(mCurItemLayout, 0 - mMoveWidth);
                        mMoveWidth = 0;
                    }
                }
                mLastItemLayout = mCurItemLayout;
            }
        }
        performClick();
        return super.onTouchEvent(e);
    }


    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {

            Log.e(TAG, "computeScroll getCurrX ->" + mScroller.getCurrX());
            mCurItemLayout.scrollBy(mScroller.getCurrX(), 0);
            invalidate();
        }
    }

    /**
     * 向左滑动
     */
    private void scrollLeft(View item, int scorllX) {
        Log.e(TAG, " scroll left -> " + scorllX);
        item.scrollBy(scorllX, 0);
    }

    /**
     * 向右滑动
     */
    private void scrollRight(View item, int scorllX) {
        Log.e(TAG, " scroll right -> " + scorllX);
        item.scrollBy(scorllX, 0);
    }

    public interface OnRightClickListener {
        void onRightClick(int position, String id);
    }
}