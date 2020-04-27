package com.example.paletteapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.Arrays;
import java.util.List;
//Add below gradles first:
   /*  implementation 'com.github.bumptech.glide:glide:4.11.0'
        implementation 'androidx.palette:palette:1.0.0'
        */
public class MainActivity extends AppCompatActivity {

    private List<Integer> storeImages= Arrays.asList(R.drawable.logo1,R.drawable.logo2,R.drawable.logo3,R.drawable.logo4);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView=findViewById(R.id.storeRec);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new StoreImagesAdapter(this,storeImages));
    }
}
