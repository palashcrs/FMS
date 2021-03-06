package com.get.edgepay.fms.util;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public Timestamp currentTimestamp() {
		return new Timestamp(new Date().getTime());
	}

	public <T> List<T> mergeLists(List<T> list, List<T> list2) {
		List<T> mergedList = null;

		if ((list != null && list.size() > 0) && (list2 == null || list2.isEmpty())) {
			mergedList = new ArrayList<>(list);
		}
		if ((list == null || list.isEmpty()) && (list2 != null && list2.size() > 0)) {
			mergedList = new ArrayList<>(list2);
		}
		if ((list != null && list.size() > 0) && (list2 != null && list2.size() > 0)) {
			mergedList = new ArrayList<>(list);
			mergedList.addAll(1, list2);
		}

		return mergedList;
	}

	public <K, V> Map<K, V> mergeMaps(Map<K, V> map, Map<K, V> map2) {
		Map<K, V> mergedMap = null;

		if ((map != null && map.size() > 0) && (map2 == null || map2.isEmpty())) {
			mergedMap = new HashMap<>(map);
		}
		if ((map == null || map.isEmpty()) && (map2 != null && map2.size() > 0)) {
			mergedMap = new HashMap<>(map2);
		}
		if ((map != null && map.size() > 0) && (map2 != null && map2.size() > 0)) {
			mergedMap = new HashMap<>(map);
			mergedMap.putAll(map2);
		}

		return mergedMap;
	}

}
