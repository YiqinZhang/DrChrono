//package com.example.stepcalculation;
//package com.example.ecosavertest;
//import GPSLocation.java;

//Similar process for bicycle calculation, though we track distance traveled by GPS. 

public class StepCalculation {
    int steps = 0; //step counter initialization; need to
    double points = 0.0;

    com.example.ecosavertest.GPSLocation user = new com.example.ecosavertest.GPSLocation(); //creating the object for gps location to use associated functions

    public double step_counter() {
        user.GpsGetLatitude(); //returns current latitude from GPSLocation class
        user.GpsGetLongitude(); //returns current longitude from GPSLocation class
        user.GpsDistanceTo(finalLoc); //obtains destination's longitude and latitude; finalLoc is GPS object;

        //next block needs to compare user's current location (longitude and latitude) to the final location (longitude and latitude)

        while( user.GPSLocation(/*mContext*/) != user.GpsDistanceTo(finalLoc)) {
            steps++;
            if (steps % 5 == 0) {
                //increase points by arbitrary amount (5 points per step; subject to change)
                points += 25;
            }
        }
        return points;
    }
}
