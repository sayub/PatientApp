package app.patient.com.patientapp.model.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Resource {
    @SerializedName("meta")
    Meta mMeta;
    @SerializedName("name")
    List<Name> mName;
    @SerializedName("gender")
    String mGender;
    @SerializedName("birthDate")
    String mBirthDate;

    public Meta getMeta() {
        return mMeta;
    }

    public List<Name> getName() {
        return mName;
    }

    public String getGender() {
        return mGender;
    }

    public String getBirthDate() {
        return mBirthDate;
    }
}
