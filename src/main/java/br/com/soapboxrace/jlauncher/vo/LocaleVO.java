package br.com.soapboxrace.jlauncher.vo;

import br.com.soapboxrace.jlauncher.util.LocaleLoader;

public class LocaleVO implements Comparable<LocaleVO> {

	private String locale;
	private String bundleString;

	public LocaleVO(String locale) {
		this.locale = locale;
		bundleString = LocaleLoader.getBundleString("locale_" + locale);
	}

	@Override
	public int compareTo(LocaleVO o) {
		return this.toString().compareTo(o.toString());
	}

	@Override
	public String toString() {
		return bundleString;
	}

	public String getLocale() {
		return locale;
	}

	public String getBundleString() {
		return bundleString;
	}

}
