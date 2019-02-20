package app.patient.com.patientapp.model;

import java.util.List;
import app.patient.com.patientapp.model.dto.Entry;
import io.reactivex.functions.Consumer;

public interface ModelManager {
    void loadPatientsData(Consumer<List<Entry>> onPatientsDataLoaded, Consumer<Throwable> onPatientsDataLoadFailed);
}
