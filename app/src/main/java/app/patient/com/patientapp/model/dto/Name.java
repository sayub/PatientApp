package app.patient.com.patientapp.model.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Name {
    @SerializedName("family")
    String mFamily;
    @SerializedName("given")
    List<String> mGiven;
    @SerializedName("text")
    String mText;

    public String getFamily(){
        return mFamily;
    }
    public List<String> getGiven(){
        return mGiven;
    }
    public String getText(){
        return mText;
    }
}
