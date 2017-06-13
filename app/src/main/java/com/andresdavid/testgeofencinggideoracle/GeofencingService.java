package com.andresdavid.testgeofencinggideoracle;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

import java.util.List;
//creating the intent service
public class GeofencingService extends IntentService {

    protected static final String LOG_TAG = GeofencingService.class.getName();

    //constructor
    public GeofencingService() {
        super(LOG_TAG);
    }

    //handling the event(entering or exiting the geofence
    @Override
    protected void onHandleIntent(Intent intent) {
        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
        if(geofencingEvent.hasError())
        {
            Log.e(LOG_TAG,"geofencingEvent hasError");
        }
        else{
            int transition = geofencingEvent.getGeofenceTransition();
            List<Geofence> geofenceList = geofencingEvent.getTriggeringGeofences();
            Geofence geofence = geofenceList.get(0);
            String requestId = geofence.getRequestId();
            if(transition == Geofence.GEOFENCE_TRANSITION_ENTER){
                EnteringGeofence.notify(this,"You are withing the radius of the Geofence" ,2 );
                Log.d(LOG_TAG,String.format("GEOFENCE_TRANSITION_ENTER on %s",requestId));
            }
            else if(transition == Geofence.GEOFENCE_TRANSITION_EXIT){
                ExitingGeofence.notify(this, "You are our of the radius of the Geofence",2);
                Log.d(LOG_TAG,String.format("GEOFENCE_TRANSITION_EXIT  on %s",requestId));
            }
        }

    }
}
