package com.qcwl.filebrowser;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class FileChooserHelper {

    private int requestCode;
    private CallBack callBack;

    /**
     * 打开文件选择器
     * @param activity
     * @param mode        选择模式：单选或多选
     * @param fileExtensions    文件类型限定
     * @param requestCode
     */
    public void open(Activity activity, Constants.SELECTION_MODES mode, String fileExtensions, int requestCode, CallBack callBack) {
        this.requestCode = requestCode;
        this.callBack = callBack;
        Intent intent = new Intent(activity, FileChooser.class);
        intent.putExtra(Constants.SELECTION_MODE, mode.ordinal());
        if (!TextUtils.isEmpty(fileExtensions)) {
            intent.putExtra(Constants.ALLOWED_FILE_EXTENSIONS, fileExtensions);
        }
        activity.startActivityForResult(intent, requestCode);
    }

    public void onActivityResult(int requestCode, int result, Intent data) {
        if (this.requestCode == requestCode && result == RESULT_OK && data != null) {
            if (callBack != null) {
                ArrayList<Uri> selectedFiles = data.getParcelableArrayListExtra(Constants.SELECTED_ITEMS);
                callBack.onChoose(selectedFiles);
                callBack = null;
            }
        }
    }

    public interface CallBack {
        void onChoose(ArrayList<Uri> files);
    }

}
