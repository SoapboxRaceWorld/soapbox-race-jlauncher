package br.com.soapboxrace.jlauncher.util;

import java.io.File;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.stream.Stream;

import br.com.soapboxrace.jlauncher.vo.LocaleVO;

public class LocaleList {

	public ArrayList<LocaleVO> getLocaleList() {
		ArrayList<LocaleVO> localeList = new ArrayList<>();
		try {
			localeList = getLocales();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return localeList;
	}

	private ArrayList<LocaleVO> getLocales() throws Exception {
		URI uri = LocaleList.class.getResource("/locales").toURI();
		Path myPath = null;
		boolean inJar = false;
		if (uri.getScheme().equals("jar")) {
			FileSystem fileSystem = FileSystems.newFileSystem(uri, Collections.<String, Object>emptyMap());
			myPath = fileSystem.getPath("/locales");
			inJar = true;
		} else {
			myPath = Paths.get(uri);
		}
		ArrayList<LocaleVO> localeList = new ArrayList<>();
		try (Stream<Path> walk = Files.walk(myPath, 1)) {
			for (Iterator<Path> it = walk.iterator(); it.hasNext();) {
				Path next = it.next();
				String name = next.toString();
				if (!inJar) {
					File file = next.toFile();
					name = "/" + file.getName();
				}
				if (!name.equals("/locales") && !name.contains("/locale.properties")) {
					String localeName = name.replaceAll(".properties", "");
					localeName = localeName.replaceAll("/locales/", "");
					localeName = localeName.replaceAll("/", "");
					localeName = localeName.replaceAll("locale_", "");
					LocaleVO localeVO = new LocaleVO(localeName);
					localeList.add(localeVO);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Collections.sort(localeList);
		return localeList;
	}
}
