package cz.vencax.service;

import java.util.List;

public class DebugBMSService extends AbstractBMSService {

	public DebugBMSService(List<CellInfo> data) {
		super(data);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void obtainData() {
		synchronized(this.data) {
			for(CellInfo i : this.data) {
				i.voltage = 3;
				i.temp = 34;
			}
		}
	}

}
