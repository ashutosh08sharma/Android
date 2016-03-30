package smartstreet.mobile.com.smartstreet;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

/*
this class is for comment functionality, user can rate and comment for the application
 */
public class CommentActivity extends AppCompatActivity {

    public static final String COMMENT_PREFERENCES = "Prefences";
    public static final String Rating = "ratingKey";
    public static final String Comment = "commentKey";
    RatingBar ratingbar;
    EditText commentText;
    DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_comment);
        addListenerOnButtonClick();
        databaseHandler = new DatabaseHandler(this);
        populateView();
    }


    private void addListenerOnButtonClick() {
        ratingbar = (RatingBar) findViewById(R.id.ratingBar);
        commentText = (EditText) findViewById(R.id.commentBox);
        Button button = (Button) findViewById(R.id.commentButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String comment = commentText.getText().toString(); // getting value from commentText
                String rating = String.valueOf(ratingbar.getRating()); // getting value form Rating bar and storin in variable

                // check for valid comment and star
                if(comment.equals("") || rating.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Field Required", Toast.LENGTH_LONG).show();
                }

                else{
                    databaseHandler.addUserComment(comment, rating);
                    populateView();
                    Intent intent = new Intent(getApplicationContext(), CommentActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                }
            }


        });

    }
    // will display the user comment in the ListView
         private void populateView() {
        Cursor cursor = (Cursor) databaseHandler.getAllComments();
        String[] fromTable = new String[]{DatabaseHandler.USER_COMMENT, DatabaseHandler.USER_Rating};
        int[] toViewID = new int[] {R.id.commentView1,R.id.ratingView1};
        SimpleCursorAdapter cursorAdapter ;
        cursorAdapter  = new SimpleCursorAdapter(getBaseContext(),R.layout.listview,cursor, fromTable,toViewID,0);
        ListView list = (ListView) findViewById(R.id.listView);
        list.setAdapter(cursorAdapter);


    }


}
