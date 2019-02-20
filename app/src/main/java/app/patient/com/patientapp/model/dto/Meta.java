package app.patient.com.patientapp.model.dto;

import com.google.gson.annotations.SerializedName;

public class Meta {
    @SerializedName("lastUpdated")
    String mLastUpdated;

    public String getLastUpdated(){
        return mLastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        mLastUpdated = lastUpdated;
    }
}
