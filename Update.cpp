

GPSLocation user = new GPSLocation(); 

//GPSLocation finalLocation = new GPSLocation();
//GPSLocation finalLoc = new GPSLocation();
//Possible run Update() in loop in step_counter function
//would need Update(GPSLocation updateUser) within a class, create an object of that class, then call Update and pass GPSLocation object of user to represent it.
//Possible void to return the Updated Object

public static void Update(GPSLocation user) { 
  //Option 1: Create an entirely new object:

  GPSLocation updateUser = new GPSLocation(); //this should create a new object   with a new lat and long.

  //Option 2: Update user object that was passed.

  user.GpsGetLatitude();
  user.GpsGetLongitude();

  //For calculating steps, would need to check if user.latitude and longitude haare equal to the finalLocation's latitude and longitude. 
}
