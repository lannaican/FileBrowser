package com.qcwl.demo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.qcwl.filebrowser.Constants;
import com.qcwl.filebrowser.FileChooserHelper;
import com.qcwl.demo.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FileChooserHelper fileChooserHelper = new FileChooserHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fileChooserHelper.open(this, Constants.SELECTION_MODES.SINGLE_SELECTION, null, 11, new FileChooserHelper.CallBack() {
            @Override
            public void onChoose(ArrayList<Uri> files) {

            }

            @Override
            public void onCancel() {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        fileChooserHelper.onActivityResult(requestCode, resultCode, data);
    }
}
