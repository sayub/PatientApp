package app.patient.com.patientapp.api;

import app.patient.com.patientapp.model.dto.Root;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    public static final String BASE_URL = "http://fhirtest.uhn.ca/baseDstu3/";

    @GET("Patient?_pretty=true")
    Call<Root> getPatients();
}
