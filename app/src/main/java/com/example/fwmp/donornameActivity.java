package com.example.fwmp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;


public class donornameActivity extends AppCompatActivity {

    private DatabaseReference reff;
    private ListView mListView;
    private ArrayAdapter adapter;
    private ArrayList<String> arrayList;
    private long N=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donorname);

        Toast.makeText(donornameActivity.this,"Please Wait.....",Toast.LENGTH_SHORT).show();

        mListView = (ListView) findViewById(R.id.listview);


        reff = FirebaseDatabase.getInstance().getReference().child("member");
        arrayList = new ArrayList<String>();


        reff.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                arrayList.clear();
                N=dataSnapshot.getChildrenCount();
                for( int i = 1 ; i <= N ; i++ ){
                    arrayList.add(dataSnapshot.child(String.valueOf(i)).child("userName").getValue(String.class));
                }

                adapter = new ArrayAdapter(donornameActivity.this, android.R.layout.simple_expandable_list_item_1,arrayList);
                mListView.setAdapter(adapter);

                

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

}
