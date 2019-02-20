package app.patient.com.patientapp.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import app.patient.com.patientapp.R;
import app.patient.com.patientapp.adapter.PatientAdapter;
import app.patient.com.patientapp.dependency.DependencyRegistry;
import app.patient.com.patientapp.model.dto.Entry;
import app.patient.com.patientapp.model.dto.Name;

public class MainActivity extends AppCompatActivity {
    private static final String LOAD_SUCCESS_MESSAGE = "Patients data loaded successfully.";
    private static final String LOAD_FAILURE_MESSAGE = "Patients data could not be loaded due to error.";
    private RecyclerView mPatientsRecyclerView;
    private MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPatientsRecyclerView = findViewById(R.id.recyclerViewPatients);
        mPatientsRecyclerView.setHasFixedSize(true);
        mPatientsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        DependencyRegistry.getInstance().inject(this);

        mPresenter.loadPatientsData(this::onPatientsDataLoaded, this::onPatientsDataLoadFailed);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void onPatientsDataLoaded(List<Entry> entries) {
        Collections.sort(entries, new Comparator<Entry>() {
            @Override
            public int compare(Entry lhs, Entry rhs) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
                Date lhsDate = null;
                Date rhsDate = null;
                try {
                    lhsDate = formatter.parse(lhs.getResource().getMeta().getLastUpdated());
                    rhsDate = formatter.parse(rhs.getResource().getMeta().getLastUpdated());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                return rhsDate.compareTo(lhsDate);
            }
        });

        PatientAdapter adapter = new PatientAdapter(this, entries);
        mPatientsRecyclerView.setAdapter(adapter);

        Toast.makeText(this, LOAD_SUCCESS_MESSAGE, Toast.LENGTH_SHORT).show();
    }

    private void onPatientsDataLoadFailed(Throwable t) {
        Toast.makeText(this, LOAD_FAILURE_MESSAGE + " (" + t.getMessage() + ")", Toast.LENGTH_SHORT).show();
    }

    public void configureWith(MainPresenter presenter) {
        mPresenter = presenter;
    }
}
