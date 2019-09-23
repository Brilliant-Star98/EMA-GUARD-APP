package com.optisoft.emaguard.Activity;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.github.chrisbanes.photoview.PhotoView;
import com.optisoft.emaguard.R;
import com.squareup.picasso.Picasso;

import static com.optisoft.emaguard.HelperClasses.ApiConstant.IMAGE_URL;

public class ImageActivity extends AppCompatActivity {

    private PhotoView photoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        PhotoView photoView = (PhotoView) findViewById(R.id.photoView_image_activity);

        if (getIntent().hasExtra("image")){
            String url = getIntent().getStringExtra("image");
            Picasso.with(this).load(IMAGE_URL + url).placeholder(R.drawable.user).error(R.drawable.user).into(photoView);
        }
    }
}
