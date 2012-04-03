package cz.vencax;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import cz.vencax.service.AbstractBMSService;
import cz.vencax.service.AbstractBMSService.CellInfo;
import cz.vencax.service.DebugBMSService;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class BmsActivity extends Activity {

	private int _cellPerRow = 4;
	private int _cellCount = 16;

	private List<ImageView> _cellImages = null;
	
	private AbstractBMSService _service = null;
	private Timer _autoUpdate;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
//		initImageMapping();
//
//		_cellImages = new ArrayList<ImageView>(this._cellCount);
//		createControls((TableLayout) findViewById(R.id.tableLayout1),
//				_cellImages);
//
//		this._service = new DebugBMSService();
//
//		drawCells();
		
		GridView gridview = (GridView) findViewById(R.id.gridView1);
		ImageAdapter ia = new ImageAdapter(this);
		ia.mThumbIds = this.mThumbIds;
	    gridview.setAdapter(ia);
	}
	
	private Integer[] mThumbIds = {
            R.drawable.lev2, R.drawable.lev3,
            R.drawable.lev4, R.drawable.lev0,
            R.drawable.lev2, R.drawable.lev5,
            R.drawable.lev2, R.drawable.lev5,
    };

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

//	private void createControls(TableLayout table, List<ImageView> cellImages) {
//		TableRow tr = null;
//		
//		for (int curr = 0; curr < this._cellCount; curr++) {	
//
//			if ((curr % this._cellPerRow) == 0) {
//				// Create a TableRow and give it an ID
//				tr = new TableRow(this);
////				tr.setLayoutParams(new LayoutParams(
////						LayoutParams.FILL_PARENT,
////						LayoutParams.FILL_PARENT));
////				tr.setPadding(5, 5, 5, 5);
//				tr.setId(100 + curr);
//				table.addView(tr);
//			}
//			createCell(tr, cellImages);
//		}
//	}
//
//	private void createCell(TableRow row, List<ImageView> cellImages) {
//		LinearLayout l = new LinearLayout(this);
//		l.setOrientation(LinearLayout.VERTICAL);
//		row.addView(l);
//		
//		ImageView image;
//		image = new ImageView(getBaseContext());
////		image.setLayoutParams(new LayoutParams(
////						LayoutParams.WRAP_CONTENT,
////						LayoutParams.WRAP_CONTENT));
////		image.setPadding(5, 5, 5, 5);
//		cellImages.add(image);
//		l.addView(image);
//		
//		TextView tw = new TextView(this);
//		tw.setText(String.valueOf(44));
//		l.addView(tw);
//	}
}