package br.com.soapboxrace.jlauncher;

import br.com.soapboxrace.jlauncher.util.JaxbJson;
import br.com.soapboxrace.jlauncher.vo.ServerInfoVO;

public class JsonExample {

	public static void main(String[] args) {
		ServerInfoVO serverInfoVO = new ServerInfoVO();
		serverInfoVO.setServerName("test");
		try {
			String marshal = JaxbJson.marshal(serverInfoVO);
			System.out.println(marshal);
			System.out.println("\n\n");
		} catch (Exception e) {
			e.printStackTrace();
		}

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("{\"messageSrv\":\"Closed beta server for developers, youtubers and testers\",\n");
		stringBuilder.append("\"homePageUrl\":\"https://soapboxrace.world/\",\n");
		stringBuilder.append("\"facebookUrl\":\"https://www.facebook.com/SoapBoxRaceWorld/\",\n");
		stringBuilder.append("\"discordUrl\":\"discord-example\",\n");
		stringBuilder.append("\"serverName\":\"SBRW-Closed Beta\",\n");
		stringBuilder.append("\"timezone\":-4,\n");
		stringBuilder.append("\"bannerUrl\":\"banner-example\",\n");
		stringBuilder.append("\"adminList\":\"Nilzao;Leorblx\",\n");
		stringBuilder.append("\"ownerList\":\"Nilzao\",\n");
		stringBuilder.append("\"numberOfRegistered\":2,\n");
		stringBuilder.append("\"onlineNumber\":2,\n");
		stringBuilder.append("\"country\":\"BR\"}");
		String json = stringBuilder.toString();
		try {
			ServerInfoVO serverInfo = (ServerInfoVO) JaxbJson.loadObject(json, ServerInfoVO.class);
			System.out.println(serverInfo.getServerName());
			System.out.println(serverInfo.getFacebookUrl());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
