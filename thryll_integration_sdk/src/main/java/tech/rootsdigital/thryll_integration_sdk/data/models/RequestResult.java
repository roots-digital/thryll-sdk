package tech.rootsdigital.thryll_integration_sdk.data.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RequestResult implements Serializable {

    private List<Event> data;
    private String message;
    private String errorMessage;
    private boolean successful;

    public RequestResult(boolean s, String message){
        successful = s;
        errorMessage = message;
        data = new ArrayList<>();
    }

    public RequestResult(boolean s, String message, List<Event> data){
        this.successful = s;
        this.message = message;
        this.data = data;
    }

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

    public boolean isSuccessful() {
        return successful;
    }
    public void setSuccess(boolean success) {
        this.successful = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
