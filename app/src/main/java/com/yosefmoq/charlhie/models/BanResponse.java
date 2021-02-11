package com.yosefmoq.charlhie.models;

import androidx.core.app.NotificationCompat;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BanResponse {
    @SerializedName(NotificationCompat.CATEGORY_MESSAGE)
    @Expose
    String msg;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    String status;

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status2) {
        this.status = status2;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg2) {
        this.msg = msg2;
    }
}
