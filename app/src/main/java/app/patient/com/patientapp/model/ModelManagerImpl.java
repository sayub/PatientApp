package app.patient.com.patientapp.model;

import java.util.List;
import app.patient.com.patientapp.model.dto.Entry;
import app.patient.com.patientapp.model.network.NetworkManager;
import io.reactivex.functions.Consumer;

public class ModelManagerImpl implements ModelManager {
    private NetworkManager mNetworkManager;

    public ModelManagerImpl(NetworkManager networkManager) {
        mNetworkManager = networkManager;
    }
    public void loadPatientsData(Consumer<List<Entry>> onPatientsDataLoaded, Consumer<Throwable> onPatientsDataLoadFailed) {
        mNetworkManager.loadPatientsData(onPatientsDataLoaded, onPatientsDataLoadFailed);
    }
}
