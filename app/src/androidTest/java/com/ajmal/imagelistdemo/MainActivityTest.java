package com.ajmal.imagelistdemo;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by ajmal on 9/3/16.
 */

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {
    MainActivity mainActivity;
    RecyclerView recyclerView;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        mainActivity = getActivity();

        recyclerView = (RecyclerView) mainActivity.findViewById(R.id.list);

    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Testing all images are loaded correctly.
     *
     */
    @SmallTest
    public void testRecyclerView(){

        //wait some time ( 15 sec ) to complete load data from flickr
        final CountDownLatch signal = new CountDownLatch(1);
        try {
            signal.await(15, TimeUnit.SECONDS);
        }catch (Exception e){
            e.printStackTrace();
        }
        //currently 8 images in the flickr album
        int expectedImages = 8;
        int actualCount = recyclerView.getAdapter().getItemCount();

        assertEquals(expectedImages,actualCount);
    }
}
