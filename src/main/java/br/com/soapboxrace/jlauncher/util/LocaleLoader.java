package br.com.soapboxrace.jlauncher.util;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleLoader {

	public static String getBundleString(String key) {
		ResourceBundle bundle = ResourceBundle.getBundle("locales.locale", Locale.getDefault());
		return bundle.getString(key);
	}

	public static void loadLocale(List<Object> windowObjects) {
		for (Object object : windowObjects) {
			if (object instanceof javax.swing.JButton) {
				loadObject((javax.swing.JButton) object);
			} else if (object instanceof javax.swing.JLabel) {
				loadObject((javax.swing.JLabel) object);
			} else if (object instanceof javax.swing.JCheckBox) {
				loadObject((javax.swing.JCheckBox) object);
			} else if (object instanceof javax.swing.JTabbedPane) {
				loadObject((javax.swing.JTabbedPane) object);
			}
		}
	}

	private static void loadObject(javax.swing.JButton button) {
		String bundleString = getBundleString(button.getText());
		button.setText(bundleString);
	}

	private static void loadObject(javax.swing.JLabel label) {
		String bundleString = getBundleString(label.getText());
		label.setText(bundleString);
	}

	private static void loadObject(javax.swing.JCheckBox checkBox) {
		String bundleString = getBundleString(checkBox.getText());
		checkBox.setText(bundleString);
	}

	private static void loadObject(javax.swing.JTabbedPane tab) {
		int tabCount = tab.getComponentCount();
		for (int i = 0; i < tabCount; i++) {
			String titleAt = tab.getTitleAt(i);
			tab.setTitleAt(i, getBundleString(titleAt));
		}
	}

}
