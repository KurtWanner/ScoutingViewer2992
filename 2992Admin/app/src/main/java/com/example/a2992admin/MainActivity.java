package com.example.a2992admin;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText password;
    private Button schedule;
    private Button teams;
    private static final String pass = "Boat2992";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        password = findViewById(R.id.password);
        schedule = findViewById(R.id.button);
        teams = findViewById(R.id.button2);

        final DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        final FirebaseStorage storage = FirebaseStorage.getInstance();
        final StorageReference storageRef = storage.getReference();


        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!password.getText().toString().equals(pass)){
                    Log.d("Bytes", "Returned");
                    return;
                }
                StorageReference gsReference = storage.getReferenceFromUrl("gs://team-2992-scouting.appspot.com/HopperMatchSchedule.txt");
                final long ONE_MEGABYTE = 1024 * 1024;


                gsReference.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {

                        String team = new String(bytes);

                        team = team.replaceAll("\\s", "");

                        HashMap<String, List<String>> allMatches = new HashMap<>();

                        Log.d("Bytes", team);

                        String[] teamList = team.split("#");

                        String num = "";
                        String iSub;
                        String r1 = "";
                        String r2 = "";
                        String r3 = "";
                        String b1 = "";
                        String b2 = "";
                        String b3 = "";
                        String[] numList;

                        for(String i: teamList){
                            Log.d("teamList", i);

                            num = i.substring(0,2).replaceAll("_", "");

                            iSub = i.substring(4);

                            numList = iSub.split(",");

                            for(int q = 0; q < numList.length; q++){
                                if(q == 0){
                                    r1 = numList[q];
                                } else if(q == 1){
                                    r2 = numList[q];
                                }else if(q == 2){
                                    r3 = numList[q];
                                }else if(q == 3){
                                    b1 = numList[q];
                                }else if(q == 4){
                                    b2 = numList[q];
                                }else if(q == 5){
                                    b3 = numList[q];
                                }
                            }

                            database.child("Matches").child(num).child("r1").setValue(r1);
                            database.child("Matches").child(num).child("r2").setValue(r2);
                            database.child("Matches").child(num).child("r3").setValue(r3);
                            database.child("Matches").child(num).child("b1").setValue(b1);
                            database.child("Matches").child(num).child("b2").setValue(b2);
                            database.child("Matches").child(num).child("b3").setValue(b3);



                        }

                        


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle any errors
                    }
                });


            }
        });






        teams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!password.getText().toString().equals(pass)){
                    Log.d("Bytes", "Returned");
                    return;
                }

                StorageReference gsReference = storage.getReferenceFromUrl("gs://team-2992-scouting.appspot.com/HopperTeams.txt");

                final long ONE_MEGABYTE = 1024 * 1024;
                gsReference.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {

                        HashMap<String, String> allTeams = new HashMap<>();

                        String team = new String(bytes);

                        Log.d("Bytes", team);

                        String[] stringList = team.split(":");

                        List<String> newStringsList = new ArrayList<String>(Arrays.asList(stringList));


                        for(int q = 0; q < newStringsList.size() / 2; q++){
                            Log.d("Bytes Allteams", newStringsList.get(2 * q) + " " +newStringsList.get(2 * q + 1));
                            allTeams.put(newStringsList.get(2 * q), newStringsList.get(2 * q + 1));
                        }

                        for(String i: newStringsList){
                            Log.d("NewStringsList", i);
                        }

                        for(String i: allTeams.keySet()){
                            Log.d("allTeams key", i);
                            database.child("Teams").child(i).child("NickName").setValue(allTeams.get(i));
                        }


                        // Data for "images/island.jpg" is returns, use this as needed
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle any errors
                    }
                });



            }
        });

    }
}
