package com.logibeat.cloud.common.utils;

public class GeoDistanceUtil {
	private GeoDistanceUtil(){}

	private static final double EARTH_RADIUS = 6378137;

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	/**
	 * google maps里面实现的算法
	 */
	public static double distance(double lat1, double lng1, double lat2, double lng2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000) / 10000;
		return s;
	}
	
	/**
	 * 计算地球上任意两点(经纬度)距离
	 * @return 返回距离 单位：米
	 */
	public static double distance2(double lng, double lat, double lng2, double lat2) {
		double a, b, R;
		R = EARTH_RADIUS; // 地球半径
		lat = lat * Math.PI / 180.0;
		lat2 = lat2 * Math.PI / 180.0;
		a = lat - lat2;
		b = (lng - lng2) * Math.PI / 180.0;
		double d;
		double sa2, sb2;
		sa2 = Math.sin(a / 2.0);
		sb2 = Math.sin(b / 2.0);
		d = 2 * R * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat) * Math.cos(lat2) * sb2 * sb2));
		return d;
	}

}
