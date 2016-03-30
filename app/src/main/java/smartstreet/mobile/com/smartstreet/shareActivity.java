package smartstreet.mobile.com.smartstreet;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

//Share Activity for taking photo, video and sharing it on facebook and other social media platforms
public class shareActivity extends AppCompatActivity {
    ImageButton audioBttn;
    ImageButton playBtn;
    ImageButton stopBtn;
    ImageButton recordBttn;
    ImageButton shareBtn;
    private MediaRecorder mediaRecorder;
    private MediaPlayer mediaPlayer;
    private static String audioFileLocation;
    private boolean isRecording = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        // path for audio file location
        audioFileLocation = Environment.getExternalStorageDirectory().
                getAbsolutePath() + "/Recording1.3gpp";
        //
        audioBttn =(ImageButton) findViewById(R.id.audioButton);
        audioBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recordBttn.setVisibility(View.VISIBLE);
                stopBtn.setVisibility(View.VISIBLE);
                playBtn.setVisibility(View.VISIBLE);
                shareBtn.setVisibility(View.VISIBLE);
            }
        });
            recordBttn = (ImageButton) findViewById(R.id.recorderButton);
            recordBttn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startRecorder(v);
                }
            });

                stopBtn = (ImageButton) findViewById(R.id.stopBttn);
                stopBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                    public void onClick(View v) {
                    stopRecorder(v);
                    }

                });
                     playBtn = (ImageButton) findViewById(R.id.playButton);
                     playBtn.setOnClickListener(new View.OnClickListener()
                     {
                        @Override
                            public void onClick(View v) {
                                try {
                                    playRecording(v);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                        }
                                    }
                      });
                                        shareBtn =(ImageButton) findViewById(R.id.sharebttn1);
                                        shareBtn.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                                public void onClick(View v) {
                                                shareAudio();
                                                }
                                        });

    }
            // Method is used to play the recording
             private void playRecording(View v) throws IOException
                {
                    playBtn.setEnabled(false);
                    stopBtn.setEnabled(true);
                    recordBttn.setEnabled(false);
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setDataSource(audioFileLocation);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    Toast.makeText(getApplicationContext(), "Playing...",
                        Toast.LENGTH_SHORT).show();
                }
                // when stop is clicked stopRecorder is called
                private void stopRecorder(View v) {

                    stopBtn.setEnabled(false);
                    playBtn.setEnabled(true);

                    if(isRecording){
                        recordBttn.setEnabled(false);
                        mediaRecorder.stop();
                        mediaRecorder.release();
                        mediaRecorder =null;
                        isRecording = false;
                    }
                    Toast.makeText(getApplicationContext(), "recording stopped",
                            Toast.LENGTH_SHORT).show();
                }
                    // method is called when audio recording button is clicked
                    private void startRecorder(View v){
                      // checking if recording is on going
                       isRecording=true;
                        recordBttn.setEnabled(false);
                        stopBtn.setEnabled(true);
                        playBtn.setEnabled(false);

                        try
                        {   // create a object of MediaRecoder
                            mediaRecorder = new MediaRecorder();
                            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                            mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
                            mediaRecorder.setOutputFile(audioFileLocation);
                            mediaRecorder.prepare();
                            Toast.makeText(getApplicationContext()," Started recording...",
                                    Toast.LENGTH_SHORT).show();

                        }
                            catch (IOException e) {
                                e.printStackTrace();
                            }
                                // start recording
                                 mediaRecorder.start();

                    }
                            // share audio method
                            private void shareAudio()
                            {
                                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                                sharingIntent.setType("audio/3gp");
                                File audiopath = new File(audioFileLocation);
                                Uri uri = Uri.fromFile(audiopath);
                                sharingIntent.putExtra(Intent.EXTRA_STREAM, uri);
                                startActivity(Intent.createChooser(sharingIntent, "Share via!"));
                            }






}
