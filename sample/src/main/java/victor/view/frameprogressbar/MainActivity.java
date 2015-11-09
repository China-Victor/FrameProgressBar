package victor.view.frameprogressbar;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import victor.view.FrameProgressBar;
import victor.view.victor.view.impl.OnProgressChangedListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        initView();

    }

    private void initView() {
        FrameProgressBar frameProgressBar = (FrameProgressBar) findViewById(R.id.fpb);
        Integer[] arr = {
                R.mipmap.vol_0,
                R.mipmap.vol_1,
                R.mipmap.vol_2,
                R.mipmap.vol_3,
                R.mipmap.vol_4,
                R.mipmap.vol_5,
                R.mipmap.vol_6,
                R.mipmap.vol_7,
                R.mipmap.vol_8,
                R.mipmap.vol_9,
                R.mipmap.vol_10,
                R.mipmap.vol_11,
                R.mipmap.vol_12,
                R.mipmap.vol_13,
                R.mipmap.vol_14,
                R.mipmap.vol_15
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
    }

}
