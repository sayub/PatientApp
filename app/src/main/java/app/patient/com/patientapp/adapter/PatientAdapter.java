package app.patient.com.patientapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import app.patient.com.patientapp.R;
import app.patient.com.patientapp.model.dto.Entry;
import app.patient.com.patientapp.model.dto.Name;

public class PatientAdapter extends
        RecyclerView.Adapter<PatientAdapter.ViewHolder> {
    private Context mContext;
    private List<Entry> mEntries;

    public PatientAdapter(Context context, List<Entry> entries) {
        mContext = context;
        mEntries = entries;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mNameTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            mNameTextView = (TextView) itemView.findViewById(R.id.name_view);
            mNameTextView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            String details = "Family Name: ";

            List<Name> name = mEntries.get(getPosition()).getResource().getName();
            if (name != null) {
                Name n = name.get(0);
                if (n != null) {
                    if (n.getFamily() != null) {
                        details += n.getFamily();
                    } else {
                        details += "Not available";
                    }
                } else {
                    details += "Not available";
                }
            }

            details += ", Gender: ";
            String gender = mEntries.get(getPosition()).getResource().getGender();
            if (gender != null) {
                details += gender;
            } else {
                details += "Not available";
            }

            details += ", Date of Birth: ";
            String dateOfBirth = mEntries.get(getPosition()).getResource().getBirthDate();
            if (dateOfBirth != null) {
                details += dateOfBirth;
            } else {
                details += "Not available";
            }

            Toast.makeText(mContext, details, Toast.LENGTH_LONG).show();
        }
    }

    // Inflating a layout from XML and returning the holder
    @Override
    public PatientAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View profileView = inflater.inflate(R.layout.recycler_view_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(profileView);

        return viewHolder;
    }

    // Populating the item through holder
    @Override
    public void onBindViewHolder(PatientAdapter.ViewHolder viewHolder, int position) {
        String fullName = "";

        List<Name> name = mEntries.get(position).getResource().getName();
        if (name != null) {
            Name n = name.get(0);
            if (n != null) {
                List<String> given = n.getGiven();
                if (given != null && given.get(0) != null) {
                    fullName += given.get(0);
                }

                if (n.getFamily() != null) {
                    fullName += " " + n.getFamily();
                }
            }
        }

        TextView textView = viewHolder.mNameTextView;
        if (!fullName.equals("")) {
            textView.setText("Patient Name: " + fullName + "\nLast Updated: " + mEntries.get(position).getResource().getMeta().getLastUpdated());
        } else {
            textView.setText("Name not available");
        }
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        if (mEntries.size() < 10) {
            return mEntries.size();
        }

        return 10;
    }
}
