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
![FrameProgressBar](https://lh3.googleusercontent.com/rQSIoRJuXtWiMx3rCZpQo7kcNJXtLjIAUblD3WE6VZmWi8gurpgASHIKh_6lyfl4_asJbTCTe4zSqJy_oCBQfqVPC6wLM4P43n5s7FfafsTz2fC0jQncHnYMw-1K124EHpgJqV29AQhNnXaqHI9BSyWUqDYLQa-7uHGfKVppyZyrg7eYCUJTz3B1FamfmpAKMOi7zxM_cidZRewQ3qQMQkI8lMX9Dv-7IJwfW0XmSV__HR6rhhXiKEmcD8oy-h1pOlvVfJ2P6pBuQaAVUVdZnghMy6QUWF8DNKyvd-l68XzFTc5J2TitdbNmqgHg4sPWZBcV5tMnVNalGtsU8gU2conK2miuigrekQUdBeHQb0g8RfsBKLx_qnwOQmWDdSOGdAVQe8YnFmx3m7JbYNFN4RYOEVYG41udhid7TieJ9sdoHMYxnIKtoTka8ay4BqyMv6vbOgCdoLL_GxSx-ouZ_p04uJBBMAqHMFKa0_qct3v6ZoRrhM1uGuYjjbqoKVz-CKyxZGv_kyqkMHQJbllmlT_WZMILDi7JLzbFSWutJw=w411-h760-no)
