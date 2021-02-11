package com.yosefmoq.charlhie.models;

import androidx.core.app.NotificationCompat;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RigsterResponse {
    @SerializedName("message")
    @Expose
    String message;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    String status;

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status2) {
        this.status = status2;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message2) {
        this.message = message2;
    }
}
