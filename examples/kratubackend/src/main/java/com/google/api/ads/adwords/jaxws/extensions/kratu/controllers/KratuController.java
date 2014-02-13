package com.google.api.ads.adwords.jaxws.extensions.kratu.controllers;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.api.ads.adwords.jaxws.extensions.kratu.data.Kratu;
import com.google.api.ads.adwords.jaxws.extensions.kratu.data.StorageHelper;
import com.google.api.ads.adwords.jaxws.extensions.report.model.util.DateUtil;

/**
 * 
 * @author gustavomoreira
 * 
 */
@Controller
@RequestMapping("/kratu")
public class KratuController {

	@Autowired
	private StorageHelper storageHelper;

	/**
	 * 
	 * @param dateStart
	 * @param dateEnd
	 * @return
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public List<Kratu> listKratus(
	    @RequestParam("dateStart") String dateStartString,
	    @RequestParam("dateEnd") String dateEndString) {

		Date dateEnd = dateEndString == null ? new Date() : DateUtil.parseDateTime(
		    dateEndString).toDate();
		DateTime minus30Days = new DateTime().minusDays(30);
		Date dateStart = dateStartString == null ? minus30Days.toDate() : DateUtil
		    .parseDateTime(dateStartString).toDate();

		return this.storageHelper.getKratus(dateStart, dateEnd);
	}

	/**
	 * 
	 * @param accountId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/{accountId}", method = RequestMethod.GET)
	public List<Kratu> listKratus(@PathVariable("accountId") Long accountId) {
		return this.storageHelper.getKratus(accountId);
	}

}
