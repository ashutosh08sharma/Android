package smartstreet.mobile.com.smartstreet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

// Main page of app,, first activity to load when application is opened, contains six imagebutton and barcode in the bottom which redirects to Smart Street website

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    /*
        methods to redirect to different activity  by passing intent object in StartActivity for different activity
     */
    private void showMap()
    {
        Intent i = new Intent(this,nearbyPlaces.class);
        startActivity(i);
    }


    private void scanTree()
    {
        Intent i = new Intent(this, BarcodeScanner.class);
        startActivity(i);
    }

    private void viewPhoto()
    {
        Intent i = new Intent(this, PhotoActivity.class);
        startActivity(i);
    }

    private void showComment()
    {
        Intent i = new Intent(this, CommentActivity.class);
        startActivity(i);
    }


    private void viewShare()
    {
        Intent i = new Intent(this, shareActivity.class);
        startActivity(i);
    }

    private void viewInteract() {
        Intent i = new Intent(this, interactActivity.class);

        startActivity(i);
    }







/*
onClick method based on  button id it will redirect it to that particular activity on click event

 */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mapButton:
                showMap();
                break;
            case R.id.aboutButton:
                scanTree();
                break;
            case R.id.photoButton:
                viewPhoto();
                break;
            case R.id.commentButton:
                showComment();
                break;
            case R.id.shareButton:
                viewShare();
                break;
            case R.id.interactButton:
                viewInteract();
                break;


        }
    }

}
