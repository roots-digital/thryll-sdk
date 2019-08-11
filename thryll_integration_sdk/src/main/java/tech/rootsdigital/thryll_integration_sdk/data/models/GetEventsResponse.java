package tech.rootsdigital.thryll_integration_sdk.data.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class GetEventsResponse implements Serializable {
    @SerializedName("responses")
    private List<Event> data;
    @SerializedName("message")
    private String message;
    @SerializedName("success")
    private boolean success;


    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Event> getData() {
        return this.data;
    }

    public void setData(List<Event> data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
