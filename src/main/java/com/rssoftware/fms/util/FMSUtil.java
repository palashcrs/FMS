package com.rssoftware.fms.util;

import java.util.ArrayList;
import java.util.List;

public class FMSUtil {

	private static FMSUtil fmsUtil = new FMSUtil();

	private FMSUtil() {

	}

	public static FMSUtil getInstance() {
		return fmsUtil;
	}

	public boolean isAnyObjectNull(Object... obj) {
		for (Object ob : obj) {
			if (ob == null) {
				return Boolean.FALSE;
			}
		}
		return Boolean.TRUE;
	}

	public boolean isEqual(String arg1, String arg2) {
		boolean response = false;
		if (arg1 != null && arg2 != null) {
			response = arg1.equals(arg2);
		}

		return response;
	}

	public <T> List<T> mergeLists(List<T> list, List<T> list2) {
		List<T> mergedList = new ArrayList<>();

		if (list != null && list2 == null) {
			mergedList.addAll(list);
		}
		if (list == null && list2 != null) {
			mergedList.addAll(list2);
		}
		if (list != null && list2 != null) {
			mergedList.addAll(list);
			mergedList.addAll(2, list2);
		}

		return mergedList;
	}

}
