package app.patient.com.patientapp.model.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Root {
    @SerializedName("entry")
    List<Entry> mEntry;

    public List<Entry> getEntries() {
        return mEntry;
    }
}
