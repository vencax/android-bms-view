package cz.vencax;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Messenger;
import android.widget.GridView;
import android.widget.TextView;
import cz.vencax.service.BMSService;
import cz.vencax.service.CellInfo;

public class BmsActivity extends Activity {
	
	private Timer _autoUpdate;
	private List<CellInfo> mData = null;
	
	private static final int ONGOING_NOTIFICATION = 143242;
	
	Messenger mService = null;
//	final Messenger mMessenger = new Messenger(new IncomingHandler());
	TextView textStatus, textIntValue, maxLoaderVoltage;
    boolean mIsBound;

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
	
//	private void CheckIfServiceIsRunning() {
//        //If the service is running when the activity starts, we want to automatically bind to it.
//        if(BMSService.isRunning()) {
//            doBindService();
//        } else {
//        	startService(new Intent(BmsActivity.this, BMSService.class));
//        }
//    }
	
	
//	class IncomingHandler extends Handler {
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//            case BMSService.MSG_SET_INT_VALUE:
//                textIntValue.setText("Int Message: " + msg.arg1);
//                break;
//            case BMSService.MSG_SET_STRING_VALUE:
//                String str1 = msg.getData().getString("str1");
//                textIntValue.setText("Str Message: " + str1);
//                break;
//            default:
//                super.handleMessage(msg);
//            }
//        }
//    }
//    private ServiceConnection mConnection = new ServiceConnection() {
//        public void onServiceConnected(ComponentName className, IBinder service) {
//            mService = new Messenger(service);
//            textStatus.setText("Attached.");
//            try {
//                Message msg = Message.obtain(null, BMSService.MSG_REGISTER_CLIENT);
//                msg.replyTo = mMessenger;
//                mService.send(msg);
//            } catch (RemoteException e) {
//                // In this case the service has crashed before we could even do anything with it
//            }
//        }
//
//        public void onServiceDisconnected(ComponentName className) {
//            // This is called when the connection with the service has been unexpectedly disconnected - process crashed.
//            mService = null;
//            textStatus.setText("Disconnected.");
//        }
//    };

	
//	void doBindService() {
//        bindService(new Intent(this, BMSService.class), mConnection, Context.BIND_AUTO_CREATE);
//        mIsBound = true;
//        textStatus.setText("Binding.");
//    }
//	
//    void doUnbindService() {
//        if (mIsBound) {
//            // If we have received the service, and hence registered with it, then now is the time to unregister.
//            if (mService != null) {
//                try {
//                    Message msg = Message.obtain(null, BMSService.MSG_UNREGISTER_CLIENT);
//                    msg.replyTo = mMessenger;
//                    mService.send(msg);
//                } catch (RemoteException e) {
//                    // There is nothing special we need to do if the service has crashed.
//                }
//            }
//            // Detach our existing connection.
//            unbindService(mConnection);
//            mIsBound = false;
//            textStatus.setText("Unbinding.");
//        }
//    }
	
	private void startBMSService() {
		startService(new Intent(BmsActivity.this, BMSService.class));
	}

//	@Override
//	public void onResume() {
//		super.onResume();
//		_autoUpdate = new Timer();
//		_autoUpdate.schedule(new TimerTask() {
//			@Override
//			public void run() {
//				runOnUiThread(new Runnable() {
//					public void run() {
//						drawCells();
//					}
//				});
//			}
//		}, 0, 2000); // updates each 40 secs
//	}
//
//	@Override
//	public void onPause() {
//		_autoUpdate.cancel();
//		super.onPause();
//	}

//	private void drawCells() {
//		int pos = 0;
//		List<CellInfo> l = this._service.getCellInfo();
//		for (CellInfo i : l) {
//			ImageView image = this._cellImages.get(pos);
//			image.setImageDrawable(this._imageMapping.get((int) i.voltage));
//			pos++;
//		}
//	}
//

}