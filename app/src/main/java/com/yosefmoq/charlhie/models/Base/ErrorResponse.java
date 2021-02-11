package com.yosefmoq.charlhie.models.Base;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class ErrorResponse extends BaseResponse implements Parcelable {
    public static final Creator<ErrorResponse> CREATOR = new Creator<ErrorResponse>() {
        /* class com.yosefmoq.charlhie.models.Base.ErrorResponse.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public ErrorResponse createFromParcel(Parcel source) {
            return new ErrorResponse(source);
        }

        @Override // android.os.Parcelable.Creator
        public ErrorResponse[] newArray(int size) {
            return new ErrorResponse[size];
        }
    };
    @SerializedName("errors")
    @Expose
    private ErrorsMessages errorsMessages;
    @SerializedName("password")
    @Expose
    private ArrayList<String> passwords;
    @SerializedName("phone")
    @Expose
    private ArrayList<String> phones;

    public ArrayList<String> getPhones() {
        return this.phones;
    }

    public void setPhones(ArrayList<String> phones2) {
        this.phones = phones2;
    }

    public ArrayList<String> getPasswords() {
        return this.passwords;
    }

    public void setPasswords(ArrayList<String> passwords2) {
        this.passwords = passwords2;
    }

    public ErrorResponse() {
    }

    protected ErrorResponse(Parcel in) {
        this.errorsMessages = (ErrorsMessages) in.readParcelable(ErrorsMessages.class.getClassLoader());
    }

    public ErrorsMessages getErrorsMessages() {
        return this.errorsMessages;
    }

    public void setErrorsMessages(ErrorsMessages errorsMessages2) {
        this.errorsMessages = errorsMessages2;
    }

    @Override // com.yosefmoq.charlhie.models.Base.BaseResponse
    public String toString() {
        return "ErrorResponse{errorsMessages=" + this.errorsMessages + ", status=" + this.status + ", message='" + this.message + '\'' + '}';
    }

    @Override // com.yosefmoq.charlhie.models.Base.BaseResponse
    public int describeContents() {
        return 0;
    }

    @Override // com.yosefmoq.charlhie.models.Base.BaseResponse
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.errorsMessages, flags);
    }
}
