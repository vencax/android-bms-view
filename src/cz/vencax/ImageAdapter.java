package cz.vencax;

import java.util.List;

import cz.vencax.service.AbstractBMSService.CellInfo;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    
    // references to our images
    private List<CellInfo> mData = null;
    
    private int [] _imageMapping = {
    	R.drawable.lev0, R.drawable.lev1,
    	R.drawable.lev2, R.drawable.lev3,
    	R.drawable.lev4, R.drawable.lev5,
    	R.drawable.lev6
    };

    public ImageAdapter(Context c, List<CellInfo> data) {
        mContext = c;
        this.mData = data;
    }

    public int getCount() {
        return mData.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return position;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
    	ImageView imageView;
    	LinearLayout l;
    	TextView tw;
    	
        if (convertView == null) {  // if it's not recycled, initialize some attributes
        	l = new LinearLayout(mContext);
    		l.setOrientation(LinearLayout.VERTICAL);
            
            imageView = new ImageView(mContext);
//            imageView.setLayoutParams(new GridView.LayoutParams(50, 200));
//            imageView.setScaleType(ImageView.);
            imageView.setPadding(5, 5, 5, 5);
            l.addView(imageView);
            
            tw = new TextView(mContext);
    		l.addView(tw);
        } else {
            imageView = (ImageView) ((LinearLayout) convertView).getChildAt(0);
            tw = (TextView) ((LinearLayout) convertView).getChildAt(1);
            l = (LinearLayout) convertView;
        }
        
        CellInfo cellInfo = mData.get(position);

        imageView.setImageResource(_imageMapping[cellInfo.voltage]);
        tw.setText(cellInfo.temp);
        return l;
    }
}
