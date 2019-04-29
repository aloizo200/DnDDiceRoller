package com.example.dnddiceroller;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

import static com.example.dnddiceroller.MainActivity.RANDOM;

public class DummyClass {
    public static  int randomDice(int value){

        return RANDOM.nextInt(value) +1;
    }
    public List<Integer> randomizer(String info){
        ArrayList<Integer> results = new ArrayList<>();
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
            int valueD = Integer.parseInt(numberIs);
            int value = randomDice(valueD);
            number = "d" + numberIs;
            //to avoid invalid dice values (e.g dice 43, dice 68..)
            if(valueD==4||valueD==6||valueD==8||valueD==12||valueD==20) {
                results.add(value);
            }
        }else{
            //if multible rolls
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
                    results.add(integer);
                    sum += integer;
                }


            }


        }
        return  results;
    }
}
