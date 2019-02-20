package app.patient.com.patientapp.main;

import java.util.List;

import app.patient.com.patientapp.model.ModelManager;
import app.patient.com.patientapp.model.dto.Entry;
import io.reactivex.functions.Consumer;

public class MainPresenterImpl implements MainPresenter {
    private ModelManager mModelManager;

    public MainPresenterImpl(ModelManager modelManager) {
        mModelManager = modelManager;
    }

    public void loadPatientsData(Consumer<List<Entry>> onPatientsDataLoaded, Consumer<Throwable> onPatientsDataLoadFailed) {
        mModelManager.loadPatientsData(onPatientsDataLoaded, onPatientsDataLoadFailed);
    }
}
