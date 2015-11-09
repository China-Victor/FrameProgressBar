package victor.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageButton;

import victor.view.victor.view.impl.OnProgressChangedListener;

/**
 * Author: Victor Wu
 * Email: victor-wu*foxmail.com
 * Time: 2015/11/8 14:20
 * Description: 帧进度条， 多图片进度条， 不规则图形进度条
 */
public class FrameProgressBar extends ImageButton{

    private String TAG = "FrameProgressBar";

    public static final int LEFTWARD = 0;
    public static final int UPWARD = 1;
    public static final int RIGHTWARD = 2;
    public static final int DOWNWARD = 3;

    private Integer[] frameArr;
    private Drawable[] frameDrawableArr;

    private int level;

    private float interval;

    private boolean isChanged;

    private int orientation = UPWARD;

    private OnProgressChangedListener mChangedListener;

    public FrameProgressBar(Context context) {
        super(context);
    }

    public FrameProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public FrameProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        /**
         * 获得自定义样式属性
         */
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, victor.view.R.styleable.FrameProgressBar, defStyleAttr, 0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attrId = a.getIndex(i);
            if (attrId == victor.view.R.styleable.FrameProgressBar_orientation) {
                this.orientation = a.getInt(attrId, UPWARD);
                break;
            }
        }
        a.recycle();
    }

    public void setFrameResArray(Integer[] frameResArr){
        this.frameArr = frameResArr;
        this.frameDrawableArr = null;
        updateInterval();
        setLevel(0);
    }

    public void setFrameDrawableArray(Drawable[] frameDrawableArr){
        this.frameDrawableArr = frameDrawableArr;
        this.frameArr = null;
        updateInterval();
        setLevel(0);
    }

    public Integer[] getFrameResArr() {
        return this.frameArr;
    }

    public Drawable[] getFrameDrawableArr() {
        return this.frameDrawableArr;
    }

    float startY;
    float startX;

    float mY;
    float mX;

    int mWidth;
    int mHeight;

    float middleX;
    float middleY;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    /**
     * 更新区间大小
     */
    private void updateInterval() {
        int length = 0;
        if(frameArr != null &&  frameArr.length > 0){
            length = frameArr.length;
        }else if(frameDrawableArr != null && frameDrawableArr.length > 0){
            length = frameDrawableArr.length;
        }

        if(length > 0){
            //计算区间大小
            if(orientation == UPWARD || orientation == DOWNWARD){
                interval = mHeight / length + 0.5f;
            }else{
                interval = mWidth / length + 0.5f;
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if((frameArr == null ||  frameArr.length < 1) &&
                (frameDrawableArr == null || frameDrawableArr.length < 1) ) return false;
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                startY = event.getY();
                startX = event.getX();

                mX = getX();
                mY = getY();
                mWidth = getWidth();
                mHeight = getHeight();
                middleX = mX + (mWidth / 2) + 0.5f;
                middleY = mY + (mHeight / 2) + 0.5f;

                updateInterval();
                break;
            case MotionEvent.ACTION_MOVE:
                float moveY = event.getY();
                float moveX = event.getX();

                float dY = moveY - startY;
                float dX = moveX - startX;

                if(orientation == UPWARD ){
                    if(dY > interval){
                        //向下拖
                        setLevel(this.level - 1);
                        //更新开始位置
                        startY = moveY;
                        this.isChanged = true;
                    }else if(dY < -interval){
                        //向上拖
                        setLevel(this.level + 1);
                        //更新开始位置
                        startY = moveY;
                        this.isChanged = true;
                    }
                }else if(orientation == DOWNWARD){
                    if(dY > interval){
                        //向下拖
                        setLevel(this.level + 1);
                        //更新开始位置
                        startY = moveY;
                        this.isChanged = true;
                    }else if(dY < -interval){
                        //向上拖
                        setLevel(this.level - 1);
                        //更新开始位置
                        startY = moveY;
                        this.isChanged = true;
                    }
                }else if(orientation == LEFTWARD){
                    if(dX > interval){
                        //向左拖
                        setLevel(this.level - 1);
                        //更新开始位置
                        startX = moveX;
                        this.isChanged = true;
                    }else if(dY < -interval){
                        //向上拖
                        setLevel(this.level + 1);
                        //更新开始位置
                        startX = moveX;
                        this.isChanged = true;
                    }
                }else if(orientation == RIGHTWARD){
                    if(dX > interval){
                        //向左拖
                        setLevel(this.level + 1);
                        //更新开始位置
                        startX = moveX;
                        this.isChanged = true;
                    }else if(dY < -interval){
                        //向上拖
                        setLevel(this.level - 1);
                        //更新开始位置
                        startX = moveX;
                        this.isChanged = true;
                    }
                }

                break;
            case MotionEvent.ACTION_UP:
                if(!isChanged){
                    float upY = event.getRawY();
                    float upX = event.getRawX();
                    Log.e("TAG", "UP --> upY:" + upY);
                    //向上方
                    if(orientation == UPWARD){
                        //只关心 y 轴 位置，相对于自身 y 轴坐标的上方还是下方
                        if(upY > middleY){
                            //在下方
                            setLevel(this.level - 1);
                        }else{//middleY <= upY
                            //在下方
                            setLevel(this.level + 1);
                        }
                    }
                    //向下方
                    else if(orientation == DOWNWARD){
                        //只关心 y 轴 位置，相对于自身 y 轴坐标的上方还是下方
                        if(upY > middleY){
                            //在下方
                            setLevel(this.level + 1);
                        }else{//middleY <= upY
                            //在上方
                            setLevel(this.level - 1);
                        }
                    }
                    //向左方
                    else if(orientation == LEFTWARD){
                        //只关心 x 轴 位置，相对于自身 x 轴坐标的左边还是右边
                        if(upX < middleX){
                            //在左边
                            setLevel(this.level + 1);
                        }else{//middleX <= upX
                            //在右边
                            setLevel(this.level - 1);
                        }
                    }
                    //向右方
                    else if(orientation == RIGHTWARD){
                        //只关心 x 轴 位置，相对于自身 x 轴坐标的左边还是右边
                        if(upX < middleX){
                            //在左边
                            setLevel(this.level - 1);
                        }else{//middleX <= upX
                            //在右边
                            setLevel(this.level + 1);
                        }
                    }
                }
                //松手重置
                this.isChanged = false;
                break;
        }

        return super.onTouchEvent(event);
    }

    /**
     * 更新进度
     * @param level
     */
    public void setLevel(int level){
        if(level >=0){
            if(this.frameArr != null && level < this.frameArr.length){
                setBackgroundResource(this.frameArr[level]);
                this.level = level;
                if(this.mChangedListener != null){
                    this.mChangedListener.onProgressChanged(this, this.level, this.frameArr.length);
                }
            }else if(this.frameDrawableArr != null && level < this.frameDrawableArr.length){
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){ //api-16
                    setBackground(this.frameDrawableArr[level]);
                }else{
                    setImageDrawable(this.frameDrawableArr[level]);
                }
                this.level = level;
                if(this.mChangedListener != null){
                    this.mChangedListener.onProgressChanged(this, this.level, this.frameDrawableArr.length);
                }
            }
        }
    }

    /**
     * 设置进度增长方向
     * @param orientation
     */
    public void setGrowingOrientation(int orientation){
        this.orientation = orientation;
    }

    /**
     * 获取进度增长方向
     */
    public int getGrowingOrientation(){
        return this.orientation;
    }

    /**
     * 获取当前进度
     * @return
     */
    public int getLevel(){
        return this.level;
    }

    public OnProgressChangedListener getOnProgressChangedListener() {
        return mChangedListener;
    }

    public void setOnProgressChangedListener(OnProgressChangedListener listener) {
        this.mChangedListener = listener;
    }
}
