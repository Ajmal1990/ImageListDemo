package com.ajmal.imagelistdemo;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.ajmal.imagelistdemo.flickrutilities.ApiResponse;
import com.ajmal.imagelistdemo.flickrutilities.Constants;
import com.ajmal.imagelistdemo.flickrutilities.FlickerAPI;
import com.ajmal.imagelistdemo.flickrutilities.GridSpacingItemDecoration;
import com.ajmal.imagelistdemo.flickrutilities.Photo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;

    private ImageAdapter imageAdapter;
    private List<Photo> photos = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.list);
        gridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        int spanCount = 2;
        int spacing = 10;
        boolean includeEdge = false;
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));

        getPhotoSet();
    }

    /**
     *  Retrive the images from flickr album
     *
     */
    public void getPhotoSet(){

        final ProgressDialog loading = ProgressDialog.show(this,"Fetching Data","Please wait...",false,false);

        Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl(Constants.FLICKR_BASE_URL)
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();

        FlickerAPI api = retrofit.create(FlickerAPI.class);

        Call<ApiResponse> photoSet = api.getPhotoSet(Constants.GET_PHOTOS_METHOD, Constants.API_KEY,
                Constants.RESPONSE_FORMAT, Constants.PHOTOSET_ID, "true");

       photoSet.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

                ApiResponse apiResponse = response.body();
                photos = apiResponse.getPhotoset().getPhoto();
                loading.dismiss();
                showPhotos();
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                loading.dismiss();
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("Connection Error");
                alertDialog.setMessage("Please check your network connection");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                MainActivity.this.finish();
                            }
                        });
                alertDialog.show();
            }
        });
    }

    /**
     * Fill the recyclerview with images from flickr
     */
    public void showPhotos(){
        imageAdapter = new ImageAdapter(MainActivity.this,photos);
        recyclerView.setAdapter(imageAdapter);
    }
}
