package com.qcwl.filebrowser.interfaces;

import com.qcwl.filebrowser.Constants;

/**
 * Created by Aditya on 4/18/2017.
 */
public interface IContextSwitcher {
    public void changeBottomNavMenu(Constants.CHOICE_MODE multiChoice);
    public void setNullToActionMode();
    public void reDrawFileList();
    public void switchMode(Constants.CHOICE_MODE mode);
}
