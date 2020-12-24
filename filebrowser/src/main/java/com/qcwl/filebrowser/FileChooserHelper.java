package com.qcwl.filebrowser;

import android.app.Activity;
import android.content.Intent;
import android.net.ProxyInfo;
import android.net.Uri;
import android.text.TextUtils;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class FileChooserHelper {

    private int requestCode;
    private CallBack callBack;
    private Constants.SELECTION_MODES mode;

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
        this.mode = mode;
        Intent intent = new Intent(activity, FileChooser.class);
        intent.putExtra(Constants.SELECTION_MODE, mode.ordinal());
        if (!TextUtils.isEmpty(fileExtensions)) {
            intent.putExtra(Constants.ALLOWED_FILE_EXTENSIONS, fileExtensions);
        }
        activity.startActivityForResult(intent, requestCode);
    }

    public void onActivityResult(int requestCode, int result, Intent data) {
        if (this.requestCode == requestCode) {
            if (callBack != null) {
                if (result == RESULT_OK && data != null) {
                    if (mode == Constants.SELECTION_MODES.SINGLE_SELECTION) {
                        ArrayList<Uri> selectedFiles = new ArrayList<>();
                        selectedFiles.add(data.getData());
                        callBack.onChoose(selectedFiles);
                    } else {
                        ArrayList<Uri> selectedFiles = data.getParcelableArrayListExtra(Constants.SELECTED_ITEMS);
                        callBack.onChoose(selectedFiles);
                    }
                } else {
                    callBack.onCancel();
                }
                callBack = null;
            }
        }
    }

    public interface CallBack {
        void onChoose(ArrayList<Uri> files);
        void onCancel();
    }

}
