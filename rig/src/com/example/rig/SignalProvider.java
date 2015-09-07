package com.example.rig;

 import java.util.ArrayList;

import com.example.rig.GestureRecorder.RecordMode;



import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SignalProvider extends Service implements  GestureRecorderListener  {
	GestureRecorder recorder;
 GestureClassifier classifier;
 ArrayList<float[]> value;
 
boolean commit=true, test=false;
 
 
	
	
	
 
	public float accel;
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
        recorder.registerListener(this);
        

		return SignalProviderStub;
	}
	
	@Override
	public boolean onUnbind(Intent intent) {
		recorder.unregisterListener(this);
		return super.onUnbind(intent);
	}
	
	@Override
	public void onCreate() {
		recorder = new GestureRecorder(this);
		classifier = new GestureClassifier(this);
		ArrayList<float[]> value= new ArrayList<float[]>();

 		super.onCreate();
	}
	
	
	IBinder SignalProviderStub = new ISignalProvider.Stub(){

		@Override
		public void start() throws RemoteException {
			
			// TODO Auto-generated method stub
			//do stuff
			
			
					
 				       Log.d("recorder.start", "hit");
 recorder.start();
				}

		@Override
		public void setCommit(boolean a) throws RemoteException {
			// TODO Auto-generated method stub
			
			
			
				commit=a;
	
		
			
			
		}

		@Override
		public void setTest(boolean a) throws RemoteException {
			// TODO Auto-generated method stub
			test=a;
			double results=classifier.Classifysignal(new Gesture(value,"TEST"));
			Toast.makeText( getApplicationContext(), "DTW DIST="+Double.toString(results), Toast.LENGTH_SHORT).show();
			
		}

		
		
		
		
		
	};

	
	    
	 


	public void onDestroy(){
		
		
		
		super.onDestroy();
		
	}


	   
 	   
		@Override
		public void onGestureRecorded(ArrayList<float[]> value1) {
			value=value1;
			Log.d("ongesture", "array iss here");
if(commit){

			classifier.commitData(new Gesture(value,"walk"));
			float a=value.get(3)[2];
			// TODO Auto-generated method stub
			Toast.makeText(getApplicationContext(), "intent"+Float.toString(a), Toast.LENGTH_SHORT).show();
for(int i=0;i<48;i++){
				   Log.d("OnGesture", " Val: "+value.get(i)[0]+" "+value.get(i)[1]+" "+value.get(i)[2]);
 			}
}
//if(test){
			//classifier.loadTrainingSet("walk");
			
		

}
		
		
	
		
		}

