package com.example.fwmp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Displaynext extends AppCompatActivity {


    private EditText uname, fname, quantity, dname,numb;
    private DatabaseReference reff;
    private receive secondmember;
    private long maxid = 0;
    private Double lat,lng;


    @RequiresApi(api = Build.VERSION_CODES.M)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displaynext);



        uname = (EditText) findViewById(R.id.name);
        fname = (EditText) findViewById(R.id.foodname);
        quantity = (EditText) findViewById(R.id.quantity);
        numb=(EditText) findViewById(R.id.num);
        dname = (EditText) findViewById(R.id.donorname);

        Button btn = (Button) findViewById(R.id.btnsubmit);

        secondmember = new receive();

        reff = FirebaseDatabase.getInstance().getReference().child("member2");

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    maxid = dataSnapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String un, fn, qt,nm, dn;

                un = uname.getText().toString().trim();
                fn = fname.getText().toString().trim();
                qt = quantity.getText().toString().trim();
                nm= numb.getText().toString().trim();
                dn = dname.getText().toString().trim();

                if (TextUtils.isEmpty(un) || TextUtils.isEmpty(fn) || TextUtils.isEmpty(qt) || TextUtils.isEmpty(dn) || TextUtils.isEmpty(nm) ) {
                    Toast.makeText(Displaynext.this, "Fields must not be empty", Toast.LENGTH_SHORT).show();
                } else {
                    secondmember.setUserName(un);
                    secondmember.setFoodName(fn);
                    secondmember.setQuantity(qt);
                    secondmember.setNumber(nm);
                    secondmember.setDonorName(dn);


                    reff.child(String.valueOf(maxid + 1)).setValue(secondmember);

                    Toast.makeText(Displaynext.this, "Receiver information submitted", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(Displaynext.this, FirstActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

        });
    }

}