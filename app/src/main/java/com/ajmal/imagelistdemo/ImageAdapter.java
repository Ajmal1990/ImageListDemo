package com.ajmal.imagelistdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.ajmal.imagelistdemo.flickrutilities.Photo;
import com.ajmal.imagelistdemo.flickrutilities.SquareImageView;
import com.squareup.picasso.Picasso;
import com.tekle.oss.android.animation.AnimationFactory;

import java.util.ArrayList;
import java.util.List;

/**
 *      Adapter for recyclerview
 *      Created by ajmal on 7/3/16.
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    Context context;


    private List<Photo> photoList = new ArrayList<Photo>();

    /**
     *
     * @param context
     * @param photoList
     */
    public ImageAdapter(Context context, List<Photo> photoList) {
        this.context = context;
        this.photoList = photoList;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public SquareImageView flickrImage;
        public ViewFlipper viewFlipper;
        public TextView titleText;

        public ViewHolder(View itemView) {
            super(itemView);
            flickrImage = (SquareImageView) itemView.findViewById(R.id.square_image_view);
            viewFlipper = (ViewFlipper)itemView.findViewById(R.id.image_view_flipper);
            titleText = (TextView)itemView.findViewById(R.id.image_title);
        }
    }

    @Override
    public int getItemCount() {
        return photoList.size();
     }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.flipview_layout, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        Photo currentImage = photoList.get(position);
        //The image url from deatils provided in methode flickr.photosets.getPhotos
        String flickrImageurl = "https://farm"+currentImage.getFarm()+".staticflickr.com/"+
                currentImage.getServer()+"/"+currentImage.getId()+"_"+
                currentImage.getSecret()+".jpg";

        Picasso.with(context).load(flickrImageurl).placeholder(R.anim.progress_anim)
                .into(holder.flickrImage);

        holder.titleText.setText(currentImage.getTitle());

        holder.viewFlipper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipViewFlipper(holder.viewFlipper);
            }
        });
    }

    private void flipViewFlipper(ViewFlipper flipper){

        //Flip using Android flip 3D transition lib ( https://github.com/genzeb/flip )
        AnimationFactory.flipTransition(flipper, AnimationFactory.FlipDirection.LEFT_RIGHT);

       /*flipper.setInAnimation(AnimationUtils.loadAnimation(context, R.anim.left_in));
         flipper.setOutAnimation(AnimationUtils.loadAnimation(context, R.anim.left_out));

       if(flipper.getDisplayedChild() == 0){
            flipper.setDisplayedChild(1);
        }
        else{
            flipper.setDisplayedChild(0);
        }*/
    }
}
