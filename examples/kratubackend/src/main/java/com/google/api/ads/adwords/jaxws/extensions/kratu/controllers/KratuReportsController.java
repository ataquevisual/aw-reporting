package com.google.api.ads.adwords.jaxws.extensions.kratu.controllers;

import java.util.Date;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.api.ads.adwords.jaxws.extensions.AwReporting;
import com.google.api.ads.adwords.jaxws.extensions.kratu.data.StorageHelper;
import com.google.api.ads.adwords.jaxws.extensions.report.model.util.DateUtil;

/**
 * @author gustavomoreira
 */
@Controller
@RequestMapping("/generatereports")
public class KratuReportsController {

	@Autowired
	private StorageHelper storageHelper;

	/**
	 * 
	 * @param file
	 * @param dateStartString
	 * @param dateEndString
	 * @return
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public String runReports(@RequestParam("file") String file,
	    @RequestParam("dateStart") String dateStartString,
	    @RequestParam("dateEnd") String dateEndString) {

		DateTime minus30Days = new DateTime().minusDays(30);
		Date dateStart = dateStartString == null ? minus30Days.toDate() : DateUtil
		    .parseDateTime(dateStartString).toDate();
		Date dateEnd = dateEndString == null ? new Date() : DateUtil.parseDateTime(
		    dateEndString).toDate();

		AwReporting.main(new String[] { "-file", file, "-startDate",
		    DateUtil.formatYearMonthDayNoDash(dateStart), "-endDate",
		    DateUtil.formatYearMonthDayNoDash(dateEnd) });

		return "OK";
	}

}
