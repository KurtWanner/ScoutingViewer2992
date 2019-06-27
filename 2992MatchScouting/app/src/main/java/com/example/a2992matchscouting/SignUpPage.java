package com.example.a2992matchscouting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import static com.example.a2992matchscouting.HomePage.mDatabase;

public class SignUpPage extends AppCompatActivity {

    private TextView name;
    private TextView nickname;

    private Button back;
    private Button create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        name = (TextView) findViewById(R.id.name);
        nickname = (TextView) findViewById(R.id.nickname);

        back = (Button) findViewById(R.id.backButton);
        create = (Button) findViewById(R.id.createUser);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpPage.this, HomePage.class));
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().equals("")){
                    Toast.makeText(SignUpPage.this, "Needs a Name", Toast.LENGTH_SHORT).show();
                }
                if(nickname.getText().toString().equals("")){
                    Toast.makeText(SignUpPage.this, "Need a Nickname", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(SignUpPage.this, "Made New User", Toast.LENGTH_SHORT).show();

                DatabaseReference Database = mDatabase;

                Database.child("Users").child(name.getText().toString())
                        .child("Nickname").setValue(nickname.getText().toString());

                Intent intent = new Intent(SignUpPage.this, HomePage.class);
                //Because there will be bundle info in the HomePage Activity, it wont run the firebase command again
                intent.putExtra("Data", "Data");
                startActivity(intent);
            }
        });




    }
}
