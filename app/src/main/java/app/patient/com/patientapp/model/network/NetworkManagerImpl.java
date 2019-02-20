package app.patient.com.patientapp.model.network;

import java.util.List;
import app.patient.com.patientapp.api.Api;
import app.patient.com.patientapp.model.dto.Entry;
import app.patient.com.patientapp.model.dto.Root;
import io.reactivex.functions.Consumer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkManagerImpl implements NetworkManager {
    public void loadPatientsData(final Consumer<List<Entry>> onPatientsDataLoaded, final Consumer<Throwable> onPatientsDataLoadFailed) {
        // Setting up retrofit.
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        Api api = retrofit.create(Api.class);
        Call<Root> call = api.getPatients();
        call.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                Root patientEntries = response.body();
                List<Entry> entry = patientEntries.getEntries();
                try {
                    // Using RxJava for function callback.
                    onPatientsDataLoaded.accept(entry);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {

                try {
                    onPatientsDataLoadFailed.accept(t);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
