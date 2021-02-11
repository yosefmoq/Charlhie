package com.yosefmoq.charlhie.models.Base;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.app.NotificationCompat;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseResponse implements Parcelable {
    public static final Creator<BaseResponse> CREATOR = new Creator<BaseResponse>() {
        /* class com.yosefmoq.charlhie.models.Base.BaseResponse.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public BaseResponse createFromParcel(Parcel source) {
            return new BaseResponse(source);
        }

        @Override // android.os.Parcelable.Creator
        public BaseResponse[] newArray(int size) {
            return new BaseResponse[size];
        }
    };
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    public boolean status;

    public BaseResponse() {
    }

    protected BaseResponse(Parcel in) {
        this.status = in.readByte() != 0;
        this.message = in.readString();
    }

    public boolean isStatus() {
        return this.status;
    }

    public void setStatus(boolean status2) {
        this.status = status2;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message2) {
        this.message = message2;
    }

    public String toString() {
        return "BaseResponse{status=" + this.status + ", message='" + this.message + '\'' + '}';
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.status ? (byte) 1 : 0);
        dest.writeString(this.message);
    }
}
