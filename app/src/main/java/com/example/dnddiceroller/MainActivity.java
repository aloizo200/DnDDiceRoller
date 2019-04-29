package com.example.dnddiceroller;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static final Random RANDOM = new Random();
    private static final int PERMISSION_REQUEST_CODE = 1;
    private Button D4;
    private Button D6;
    private Button D8;
    private Button D12;
    private Button D20;
    private ImageView imageD;
    public EditText rSum;
    private TextView rIndividual;
    EditText editText;
    SpeechRecognizer SpeechListener;
    Intent SpeechListenerIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermission();
        requestPermission();
        D4 = findViewById(R.id.D4);
        imageD = findViewById(R.id.imageD);
        D6 = findViewById(R.id.D6);

        D8 = findViewById(R.id.D8);

        D12 = findViewById(R.id.D12);

        D20 = findViewById(R.id.D20);

        rSum = findViewById(R.id.rSum);

        rIndividual = findViewById(R.id.rIndividual);

        editText = findViewById(R.id.editText2);
        SpeechListener = SpeechRecognizer.createSpeechRecognizer(this);
        SpeechListenerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        SpeechListenerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        /** Set the language the app will listen to
         * Its set to listen to the default language of the user's mobile phone
         */
        SpeechListenerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        SpeechListener.setRecognitionListener(new RecognitionListener(){

            @Override
            public void onReadyForSpeech(Bundle params) {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float rmsdB) {

            }

            @Override
            public void onBufferReceived(byte[] buffer) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int error) {

            }

            @Override
            public void onResults(Bundle results) {

                final ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                if (matches != null)
                    editText.setText(matches.get(0).toLowerCase());

                if(matches.get(0).toLowerCase().contains("roll the dice 4")||matches.get(0).toLowerCase().contains("roll the dice 6")
                        ||matches.get(0).toLowerCase().contains("roll the dice 8")||matches.get(0).toLowerCase().contains("roll the dice 12")
                        ||matches.get(0).toLowerCase().contains("roll the dice 20")) {
                    randomizer(matches.get(0));
                }

            }

            @Override
            public void onPartialResults(Bundle partialResults) {

            }

            @Override
            public void onEvent(int eventType, Bundle params) {

            }
        });

        D4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageD.setVisibility(View.VISIBLE);
                rSum.setVisibility(View.INVISIBLE);
                rIndividual.setVisibility(View.INVISIBLE);
                int value = randomDice(4);
                int res = getResources().getIdentifier("d4" + value, "drawable", "com.example.dnddiceroller");
                imageD.setImageResource(res);
               // editText.setHint(matches.get(0));
            }

        });


        D6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageD.setVisibility(View.VISIBLE);
                rSum.setVisibility(View.INVISIBLE);
                rIndividual.setVisibility(View.INVISIBLE);
                int value = randomDice(6);
                int res = getResources().getIdentifier("d6" + value, "drawable", "com.example.dnddiceroller");
                imageD.setImageResource(res);
            }
        });

        D8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageD.setVisibility(View.VISIBLE);
                rSum.setVisibility(View.INVISIBLE);
                rIndividual.setVisibility(View.INVISIBLE);
                int value = randomDice(8);
                int res = getResources().getIdentifier("d8" + value, "drawable", "com.example.dnddiceroller");
                imageD.setImageResource(res);
            }
        });

        D12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageD.setVisibility(View.VISIBLE);
                rSum.setVisibility(View.INVISIBLE);
                rIndividual.setVisibility(View.INVISIBLE);
                int value = randomDice(12);
                int res = getResources().getIdentifier("d12" + value, "drawable", "com.example.dnddiceroller");
                imageD.setImageResource(res);
            }
        });


        D20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageD.setVisibility(View.VISIBLE);
                rSum.setVisibility(View.INVISIBLE);
                rIndividual.setVisibility(View.INVISIBLE);
                int value = randomDice(20);
                int res = getResources().getIdentifier("d20" + value, "drawable", "com.example.dnddiceroller");
                imageD.setImageResource(res);
            }
        });

        findViewById(R.id.button).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){

                    case MotionEvent.ACTION_UP:
                        editText.setHint("Just a sec");
                        break;
                    case MotionEvent.ACTION_DOWN:
                        editText.setHint("");
                        editText.setHint("Listening...");
                        SpeechListener.startListening(SpeechListenerIntent);
                        break;


                }
                return false;
            }
        });
    }

public static  int randomDice(int value){
    return RANDOM.nextInt(value) +1;
}

    public boolean checkPermission() {

        int PermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECORD_AUDIO);

        return PermissionResult == PackageManager.PERMISSION_GRANTED ;


    }
    private void requestPermission() {

        ActivityCompat.requestPermissions(MainActivity.this, new String[]
                {
                        Manifest.permission.RECORD_AUDIO

                }, PERMISSION_REQUEST_CODE);

    }

    public void randomizer(String info){

        String number = info.toLowerCase().replace(" ","");
        String numberIs = number.replace("rollthedice","");
        boolean isMultipleTimes=false;
        //the voice recognition when the user says the word times, it understands it as "x", so if the input contains "x" then the users wants
        // multible rolls
        System.out.println(numberIs);
        if(numberIs.contains("x")|| numberIs.contains("times")){
            isMultipleTimes=true;
        }

        if(!isMultipleTimes)
        {
            // if one time roll
            imageD.setVisibility(View.VISIBLE);
            rSum.setVisibility(View.INVISIBLE);
            rIndividual.setVisibility(View.INVISIBLE);
            int valueD = Integer.parseInt(numberIs);
            int value = randomDice(valueD);
            number = "d" + numberIs;
            //to avoid invalid dice values (e.g dice 43, dice 68..)
            if(valueD==4||valueD==6||valueD==8||valueD==12||valueD==20) {
            int res = getResources().getIdentifier(number + value, "drawable", "com.example.dnddiceroller");
            imageD.setImageResource(res);
        }
        }else{
            //if multible rolls
            imageD.setVisibility(View.INVISIBLE);
            rSum.setVisibility(View.VISIBLE);
            rIndividual.setVisibility(View.VISIBLE);
            List<Integer> responses = new ArrayList<>();
            String[] tokens = null;
            if (numberIs.contains("x") ) {
            tokens = numberIs.split("x");
            }
            else
            {
            tokens = numberIs.split("times");
            }
            String diceIndicator = tokens[0];
            String timesIndicator = tokens[1];
            int times=Integer.parseInt(timesIndicator);
            int valueD = Integer.parseInt(diceIndicator);
            String response="";
            int sum=0;
            if(valueD==4||valueD==6||valueD==8||valueD==12||valueD==20) {
                for (int i = 0; i < times; i++) {
                    int value = randomDice(valueD);
                    responses.add(value);
                }
                for (Integer integer : responses) {
                    response += integer + ", ";
                    sum += integer;
                }

                rSum.setText(String.valueOf(sum));
                rIndividual.setText(response);
            }


    }
    }
}
