package cz.vencax;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import cz.vencax.service.BMSService;
import cz.vencax.service.CellInfo;

public class BmsActivity extends Activity {
	
	private Timer _autoUpdate;
	private List<CellInfo> mData = null;
	
	private static final int ONGOING_NOTIFICATION = 143242;
	private static final String TAG = BmsActivity.class.getName();
	
//	final Messenger mMessenger = new Messenger(new IncomingHandler());
	TextView textStatus, textIntValue, maxLoaderVoltage;
    boolean mIsBound;
    private BMSService mBoundService;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mData = new ArrayList<CellInfo>();
		for(int i=0; i<16; i++) {
			mData.add(new CellInfo());
		}
		
		textStatus = (TextView)findViewById(R.id.textView1);
		textIntValue = (TextView)findViewById(R.id.textView2);
		maxLoaderVoltage = (TextView)findViewById(R.id.maxLoaderVoltage);
		
		startBMSService();
		
		GridView gridview = (GridView) findViewById(R.id.gridView1);
		ImageAdapter ia = new ImageAdapter(this, mData);
	    gridview.setAdapter(ia);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		doBindService();
	}

	@Override
	public void onPause() {
		doUnbindService();
		super.onPause();
	}
	
	public static final int BUMP_MSG = 0;
	
	private Handler mHandler = new Handler() {
        @Override public void handleMessage(Message msg) {
            switch (msg.what) {
                case BUMP_MSG:
                	textStatus.setText("Received from service: " + msg.arg1);
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    };
    
    private IRemoteServiceCallback mCallback = new IRemoteServiceCallback.Stub() {
        /**
         * This is called by the remote service regularly to tell us about
         * new values.  Note that IPC calls are dispatched through a thread
         * pool running in each process, so the code executing here will
         * NOT be running in our main thread like most other things -- so,
         * to update the UI, we need to use a Handler to hop over there.
         */
        public void valueChanged(int value) {
            mHandler.sendMessage(mHandler.obtainMessage(BUMP_MSG, value, 0));
        }
    };
    
    IRemoteService mIRemoteService;
    
    private ServiceConnection mConnection = new ServiceConnection() {
    	// Called when the connection with the service is established
        public void onServiceConnected(ComponentName className, IBinder service) {
            // Following the example above for an AIDL interface,
            // this gets an instance of the IRemoteInterface, which we can use to call on the service
            mIRemoteService = IRemoteService.Stub.asInterface(service);
            
            // We want to monitor the service for as long as we are connected to it.
            try {
            	mIRemoteService.registerCallback(mCallback);
            } catch (RemoteException e) {
                // In this case the service has crashed before we could even
                // do anything with it; we can count on soon being
                // disconnected (and then reconnected if it can be restarted)
                // so there is no need to do anything here.
            }
        }

        // Called when the connection with the service disconnects unexpectedly
        public void onServiceDisconnected(ComponentName className) {
            Log.e(TAG, "Service has unexpectedly disconnected");
            mIRemoteService = null;
        }
    };
    
    void doBindService() {
        // Establish a connection with the service.  We use an explicit
        // class name because we want a specific service implementation that
        // we know will be running in our own process (and thus won't be
        // supporting component replacement by other applications).
        bindService(new Intent(BmsActivity.this, BMSService.class), 
        		mConnection, Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }

    void doUnbindService() {
        if (mIsBound) {
            // Detach our existing connection.
            unbindService(mConnection);
            mIsBound = false;
        }
    }
	
//	private void CheckIfServiceIsRunning() {
//        //If the service is running when the activity starts, we want to automatically bind to it.
//        if(BMSService.isRunning()) {
//            doBindService();
//        } else {
//        	startService(new Intent(BmsActivity.this, BMSService.class));
//        }
//    }
	
	private void startBMSService() {
		startService(new Intent(BmsActivity.this, BMSService.class));
	}
}