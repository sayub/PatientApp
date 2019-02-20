package app.patient.com.patientapp.dependency;

import app.patient.com.patientapp.main.MainActivity;
import app.patient.com.patientapp.main.MainPresenter;
import app.patient.com.patientapp.main.MainPresenterImpl;
import app.patient.com.patientapp.model.ModelManager;
import app.patient.com.patientapp.model.ModelManagerImpl;
import app.patient.com.patientapp.model.network.NetworkManager;
import app.patient.com.patientapp.model.network.NetworkManagerImpl;

public class DependencyRegistry {
    private static DependencyRegistry sInstance = new DependencyRegistry();

    private DependencyRegistry() {}

    public static DependencyRegistry getInstance() {
        return sInstance;
    }

    public ModelManager createModelManager()  {
        return new ModelManagerImpl(createNetworkManager());
    }

    public NetworkManager createNetworkManager(){
        return new NetworkManagerImpl();
    }

    public void inject(MainActivity mainActivity) {
        MainPresenter presenter = new MainPresenterImpl(createModelManager());
        mainActivity.configureWith(presenter);
    }
}