package cz.vencax;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cz.vencax.service.AbstractBMSService;
import cz.vencax.service.CellInfo;
import cz.vencax.service.DebugBMSService;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

public class BmsActivity extends Activity {
	
	private AbstractBMSService _service = null;
	private Timer _autoUpdate;
	private List<CellInfo> mData = null;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mData = new ArrayList<CellInfo>();
		for(int i=0; i<16; i++) {
			mData.add(new CellInfo());
		}
		this._service = new DebugBMSService(mData);
		
		GridView gridview = (GridView) findViewById(R.id.gridView1);
		ImageAdapter ia = new ImageAdapter(this, mData);
	    gridview.setAdapter(ia);
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