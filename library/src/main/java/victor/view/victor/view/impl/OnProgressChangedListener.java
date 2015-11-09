package victor.view.victor.view.impl;

import android.view.View;

/**
 * Author: Victor Wu
 * Email: victor-wu*foxmail.com
 * Time: 2015/11/8 14:20
 * Description: FrameProgressBar进度会掉接口
 */
public interface OnProgressChangedListener {
    void onProgressChanged(View view, int currFrame, int totalFrame);
}
