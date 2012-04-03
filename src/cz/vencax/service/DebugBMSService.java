package cz.vencax.service;

import java.util.ArrayList;
import java.util.List;

public class DebugBMSService extends AbstractBMSService {

	@Override
	public List<CellInfo> getCellInfo() {
		List<CellInfo> infos = new ArrayList<CellInfo>(16);
		for(int i=0; i<16; i++) {
			infos.add(new CellInfo());
		}
		for(CellInfo i : infos) {
			i.voltage = 3;
		}
		return infos;
	}

}
