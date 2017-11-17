package com.get.edgepay.fms.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fms")
public class FMSMainController {

	private static final Logger log = LoggerFactory.getLogger(FMSMainController.class);

	@RequestMapping(value = { "/", "/ping" }, method = RequestMethod.GET)
	public String ping() {
		return "FMS is up and running!...";
	}

	@RequestMapping(value = "/version", method = RequestMethod.GET)
	public @ResponseBody String getJarVersion() {
		String retStr = "Unknown_Version";
		Properties prop = new Properties();
		String buildVersion = "";
		String buildDate = "";

		try {
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("version.properties");
			log.info("InputStream = " + inputStream);
			prop.load(inputStream);
			buildVersion = prop.getProperty("build.version");
			buildDate = prop.getProperty("build.date");
			retStr = "build.version= " + buildVersion + "  &&  " + "build.date= " + buildDate;
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}

		return retStr;
	}

}
