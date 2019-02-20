package app.patient.com.patientapp.model.dto;

import com.google.gson.annotations.SerializedName;

public class Entry {
    @SerializedName("resource")
    private Resource mResource;

    public Resource getResource(){
        return mResource;
    }
}
