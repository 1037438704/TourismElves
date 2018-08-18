package com.tourismelves.utils.pinyin;

import com.tourismelves.model.res.SelectCityRes;

import java.util.Comparator;

public class SelectCityComparator implements Comparator<SelectCityRes> {

	public int compare(SelectCityRes o1, SelectCityRes o2) {
		if (o1.getRemark().equals("@")
				|| o2.getRemark().equals("#")) {
			return -1;
		} else if (o1.getRemark().equals("#")
				|| o2.getRemark().equals("@")) {
			return 1;
		} else {
			return o1.getRemark().compareTo(o2.getRemark());
		}
	}

}
