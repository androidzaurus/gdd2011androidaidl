package com.gmail.mizmit1222.android.gdd2011jp;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.google.android.apps.gddquiz.IQuizService;

public class GDD2011JPActivity extends Activity {

	IQuizService mService = null;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        bindService(new Intent(IQuizService.class.getName()),
        		mConnection,
        		Context.BIND_AUTO_CREATE);
    }
    
    private ServiceConnection mConnection = new ServiceConnection() {

		public void onServiceConnected(ComponentName name, IBinder service) {
			mService = IQuizService.Stub.asInterface(service);
			try {
				String s = mService.getCode();
				Log.d("gddquiz", s);
			}
			catch (RemoteException e) {
				
			}
		}

		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			mService = null;
		}
    	
    };
    
	@Override
	protected void onPause() {
		super.onPause();
		if (mConnection != null)
			unbindService(mConnection);
		mConnection = null;
		mService = null;
	}
}
