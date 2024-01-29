package com.example.rgassociates;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.viewpager2.widget.ViewPager2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<String> listConversation = new ArrayList<>();
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ArrayAdapter arrayAdpt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //        TabLayout tabLayout = findViewById(R.id.tabs);
//        ViewPager2 viewPager2 = findViewById(R.id.view_pager);
//        //</ get elements >
        listView = findViewById(R.id.idLVCourses);

        arrayAdpt = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listConversation);
        listView.setAdapter(arrayAdpt);
        databaseReference = FirebaseDatabase.getInstance().getReference();


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                updateCoversation(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String name = arrayAdpt.getItem(i).toString();
        Intent j = new Intent(getApplicationContext(), details.class);
        j.putExtra("name", name);
        startActivity(j);
    }
});
        // calling a method to get data from
        // Firebase and set data to list view
       // initializeListView();

//        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
//        viewPager2.setAdapter(adapter);
//
//        new TabLayoutMediator(tabLayout,
//                viewPager2,
//                new TabLayoutMediator.TabConfigurationStrategy() {
//                    @Override
//                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
//                        if(position == 0) {
//                            tab.setText("current cases");
//                        }
//                        else {
//                            tab.setText("solved cases");
//                        }
//
//                    }
//                }).attach();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, addcase.class);
                startActivity(i);
//                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

            }
        });
    getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Completed cases");
        return super.onCreateOptionsMenu(menu);
    }
//
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getTitle().equals("Completed cases")) {
            Intent i = new Intent(MainActivity.this, showcases.class);
            startActivity(i);
            Toast.makeText(this, "Fetching cases", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
//    private void initializeListView() {
//
//        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, coursesArrayList);
//        reference = FirebaseDatabase.getInstance().getReference();
//        reference.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                coursesArrayList.add(dataSnapshot.getValue(String.class));
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//                coursesArrayList.remove(dataSnapshot.getValue(String.class));
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//        coursesLV.setAdapter(adapter);
//    }
public void updateCoversation(DataSnapshot dataSnapshot) {
    String msg, conversation;
    Iterator i = dataSnapshot.getChildren().iterator();
    while (i.hasNext()) {
        msg = (String) ((DataSnapshot) i.next()).getKey();
        conversation = msg;
        arrayAdpt.insert(conversation, arrayAdpt.getCount());
        arrayAdpt.notifyDataSetChanged();
    }
}
}