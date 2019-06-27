package com.example.a2992pitscouting;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.a2992pitscouting.MainActivity.mDatabase;

public class SubmitFragment extends Fragment {
    //The fragment argument representing the section number for this
    //fragment.

    private Button submit;
    Spinner spinner;

    private static final String ARG_SECTION_NUMBER = "section_number";

    public SubmitFragment() {
    }


    //Returns a new instance of this fragment for the given section
    //number.

    public static SubmitFragment newInstance() {
        SubmitFragment fragment = new SubmitFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.submit, container, false);
        spinner = (Spinner) rootView.findViewById(R.id.teamSpinner);
        Log.d("Submit fragement","Has run");

        submit = (Button) rootView.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDataToFirebase();
                Toast.makeText(getActivity(), "Sent Data",
                        Toast.LENGTH_LONG).show();
            }
        });


        return rootView;
    }

    public void sendDataToFirebase(){
        DatabaseReference Database = mDatabase;
        String teamNum = MainActivity.teamNumber;
        Log.d("TeamNum to firebase", teamNum);
        Database.child("Teams").child(teamNum).child("PitScouting").child("Integers").setValue(MainActivity.integersMap);
        Database.child("Teams").child(teamNum).child("PitScouting").child("Strings").setValue(MainActivity.stringsMap);
        Database.child("Teams").child(teamNum).child("PitScouting").child("Booleans").setValue(MainActivity.booleansMap);

    }
}
