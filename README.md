# FrameProgressBar
FrameProgressBar 是一个显示不规则图片的进度条控件，你可以设置一个图片资源数组，
进度的最大值就是这个数组的大小，一张图片代表一个进度。除了这些，你还可以设置进
度增长的方向，有上、下、左、右共四个方向，这样，当你触摸或拖动进度条时，进度就
会相对应的增或减。

# using
## xml
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:victor="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="#000"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    android:paddingBottom="@dimen/activity_vertical_margin">

    <victor.view.FrameProgressBar
        android:id="@+id/fpb"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true"
        victor:orientation="upward"
        android:background="@mipmap/vol_0"/>

</RelativeLayout>

## java
        FrameProgressBar frameProgressBar = (FrameProgressBar) findViewById(R.id.fpb);
        Integer[] arr = {
                R.mipmap.vol_0,R.mipmap.vol_1, R.mipmap.vol_2, R.mipmap.vol_3,
                R.mipmap.vol_4, R.mipmap.vol_5,R.mipmap.vol_6,R.mipmap.vol_7,
                R.mipmap.vol_8,R.mipmap.vol_9,R.mipmap.vol_10,R.mipmap.vol_11,
                R.mipmap.vol_12, R.mipmap.vol_13,R.mipmap.vol_14, R.mipmap.vol_15
        };
        frameProgressBar.setFrameResArray(arr);
        //设置当前进度
        //frameProgressBar.setLevel(0);
        //设置进度的增长方向(相对于屏幕)
        //frameProgressBar.setGrowingOrientation(FrameProgressBar.UPWARD);
        //进度更新回调
        frameProgressBar.setOnProgressChangedListener(new OnProgressChangedListener() {
            @Override
            public void onProgressChanged(View view, int currFrame, int totalFrame) {
                Log.e("onProgressChanged", "currFrame:" + currFrame + "totalFrame:" + totalFrame);
            }
        });

# preview
![FrameProgressBar](http://image.baidu.com/channel/listdownload?word=download&fr=detail&ie=utf8&countop=0&url=http%3A%2F%2Fg.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2Ffcfaaf51f3deb48f1a0d623ef61f3a292cf578ee.jpg&image_id=29275609494&col=&tag=undefined)
