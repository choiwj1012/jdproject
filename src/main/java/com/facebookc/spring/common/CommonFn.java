package com.facebookc.spring.common;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonFn {

	public boolean isEmpty(Object s) {
		if (s == null) {
			return true;
		}
		if ((s instanceof String) && (((String) s).trim().length() == 0)) {
			return true;
		}
		if (s instanceof Map) {
			return ((Map<?, ?>) s).isEmpty();
		}
		if (s instanceof List) {
			return ((List<?>) s).isEmpty();
		}
		if (s instanceof Object[]) {
			return (((Object[]) s).length == 0);
		}
		return false;
	}

	public Object isEmptyR(Object s, Object r) {
		if (s == null) {
			return r;
		}

		if ((s instanceof String) && (((String) s).trim().length() == 0)) {
			return r;
		}
		if (s instanceof Map) {
			return r;
		}
		if (s instanceof List) {
			return r;
		}
		if (s instanceof Object[]) {
			return r;
		}
		return s;
	}

	public String urlPattern(String url, int count) {
		// ex ) group(0) =
		// https://goodidea.tistory.com:8888/qr/aaa/ddd.html?abc=def&ddd=fgf#sharp
		// group(1) = https
		// group(2) = goodidea.tistory.com
		// group(3) = :8888
		// group(4) = 8888
		// group(5) = /qr/aaa
		// group(6) = /aaa
		// group(7) = ddd.html
		// group(8) = ?abc=def&ddd=fgf
		// group(9) = abc=def&ddd=fgf
		// group(10) = #sharp
		// group(11) = sharp
		Pattern urlPattern = Pattern.compile(
				"^(https?):\\/\\/([^:\\/\\s]+)(:([^\\/]*))?((\\/[^\\s/\\/]+)*)?\\/([^#\\s\\?]*)(\\?([^#\\s]*))?(#(\\w*))?$");

		Matcher mc = urlPattern.matcher(url);

		if (mc.matches()) {
			for (int i = 0; i <= mc.groupCount(); i++) {
				if (i == count) {
					return mc.group(i);
				}
			}
		}

		return "not found";
	}

	public boolean tokenDayChk(String ttl) throws Exception {

		SimpleDateFormat dayTime = new SimpleDateFormat("yyyyMMdd");
		String aDate = dayTime.format(ttl);
		String bDate = dayTime.format(System.currentTimeMillis());

		if (!aDate.equals(bDate)) {
			return false;
		}

		return true;
	}

	public long diffOfDate(String end) throws Exception {

		SimpleDateFormat dayTime = new SimpleDateFormat("yyyyMMdd");
		String currentDate = dayTime.format(System.currentTimeMillis());

		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

		Date beginDate = formatter.parse(currentDate);
		Date endDate = formatter.parse(end);

		long diff = endDate.getTime() - beginDate.getTime();
		long diffDays = diff / (24 * 60 * 60 * 1000);

		return diffDays;
	}

	public long diffOfDate(String begin, String end) throws Exception {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

		Date beginDate = formatter.parse(begin);
		Date endDate = formatter.parse(end);

		long diff = endDate.getTime() - beginDate.getTime();
		long diffDays = diff / (24 * 60 * 60 * 1000);

		return diffDays;
	}

	public String ellipsis(String text, int length) {
		String ellipsisString = "...";
		String outputString = text;

		if (text.length() > 0 && length > 0) {
			if (text.length() > length) {
				outputString = text.substring(0, length);
				outputString += ellipsisString;
			}
		}
		return outputString;
	}

	public boolean isValidURL(String url) {

		URL u = null;

		try {
			u = new URL(url);
		} catch (MalformedURLException e) {
			return false;
		}

		try {
			u.toURI();
		} catch (URISyntaxException e) {
			return false;
		}

		return true;
	}

	public String getMD5(String str) throws NoSuchAlgorithmException {
		MessageDigest md5 = null;

		try {

			md5 = MessageDigest.getInstance("MD5");

		} catch (Exception e) {

			e.printStackTrace();

		}

		String eip;

		byte[] bip;

		String temp = "";

		String tst = str;

		bip = md5.digest(tst.getBytes());

		for (int i = 0; i < bip.length; i++) {

			eip = "" + Integer.toHexString((int) bip[i] & 0x000000ff);

			// System.out.println(i + " : " + eid);

			if (eip.length() < 2)
				eip = "0" + eip;

			temp = temp + eip;

		}

		return temp;
	}

	public String urlEncodeUTF8(String s) {
		try {
			return URLEncoder.encode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public String urlEncodeUTF8(Map<?, ?> map) {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<?, ?> entry : map.entrySet()) {
			if (sb.length() > 0) {
				sb.append("&");
			}
			sb.append(String.format("%s=%s", urlEncodeUTF8(entry.getKey().toString()),
					urlEncodeUTF8(entry.getValue().toString())));
		}
		return sb.toString();
	}

	public String phoneNumber(String phoneNumber) {

		String regEx = "(\\d{3})(\\d{3,4})(\\d{4})";

		if (!Pattern.matches(regEx, phoneNumber))
			return null;

		return phoneNumber.replaceAll(regEx, "$1-$2-$3");

	}
}
