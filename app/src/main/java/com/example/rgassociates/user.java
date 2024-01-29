package com.example.rgassociates;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class user extends AppCompatActivity {
    private int[] slider = new int[]{
            R.drawable.rg, R.drawable.rgg, R.drawable.rggg
    };
    AlertDialog.Builder builder;
    String nm[];
    String mo[];
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityCompat.requestPermissions(user.this, new String[]{Manifest.permission.CALL_PHONE}, 1);


        nm = new String[]{getString(R.string.rushi)};
        mo = new String[]{getString(R.string.monum)};
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        CarouselView carouselview = findViewById(R.id.machine1);
        carouselview.setPageCount(slider.length);
        carouselview.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(slider[position]);
            }
        });
        builder = new AlertDialog.Builder(this);
button = findViewById(R.id.showccc);
button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent i = new Intent(getApplicationContext(), showcases.class);
        startActivity(i);
    }
});

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Contact Us");
        menu.add("About Us");
        menu.add("Log out");
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getTitle().equals("Log out")) {
            Intent i = new Intent(user.this, login.class);
            startActivity(i);
            Toast.makeText(this, "logging out", Toast.LENGTH_SHORT).show();
        }
        if (item.getTitle().equals("Contact Us")) {
            ActivityCompat.requestPermissions(user.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
            builder.setMessage(getString(R.string.sure) + nm[0])
                    .setCancelable(false)
                    .setPositiveButton(getString(R.string.call), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            String telno = mo[0];
                            Intent i = new Intent(Intent.ACTION_CALL);
                            i.setData(Uri.parse("tel:" + telno));
                            startActivity(i);

                        }
                    })
                    .setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();

                        }
                    });
            if (checkWriteExternalPermission())
            {
                AlertDialog alert = builder.create();
                alert.setTitle("call");
                alert.show();
            }
            else
            {
                Toast.makeText(user.this, "please grant the permission", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(user.this,new String[]{Manifest.permission.CALL_PHONE},1);
            }

        }
        if (item.getTitle().equals("About Us")) {
            Intent i = new Intent(user.this, about.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
    private boolean checkWriteExternalPermission()
    {
        String permission = Manifest.permission.CALL_PHONE;
        int res = getApplicationContext().checkCallingOrSelfPermission(permission);
        return (res == PackageManager.PERMISSION_GRANTED);
    }
}