package com.google.api.ads.adwords.jaxws.extensions.kratu.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.api.ads.adwords.jaxws.extensions.report.model.entities.ReportAd;

/**
 * 
 * @author gustavomoreira
 * 
 */
@Controller
@RequestMapping("/reportad")
public class ReportAdController extends AbstractReportController<ReportAd> {

	/**
	 * 
	 */
	public ReportAdController() {
		super(ReportAd.class);
	}

	/**
	 * 
	 * @param campaignId
	 * @param dateStart
	 * @param dateEnd
	 * @return
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/campaign/{campaignId}")
	public List<ReportAd> getReportByCampaignId(
	    @PathVariable(value = "campaignId") Long campaignId,
	    @RequestParam("dateStart") String dateStart,
	    @RequestParam("dateEnd") String dateEnd) {

		return this.listReportByCampaignId(campaignId, dateStart, dateEnd);
	}

	/**
	 * 
	 * @param adGroupId
	 * @param dateStart
	 * @param dateEnd
	 * @return
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/adgroup/{adGroupId}")
	public List<ReportAd> getReportByAdGroupId(
	    @PathVariable(value = "adGroupId") Long adgroupId,
	    @RequestParam("dateStart") String dateStart,
	    @RequestParam("dateEnd") String dateEnd) {

		return this.listReportByAdGroupId(adgroupId, dateStart, dateEnd);
	}
	
	/**
	 * 
	 * @param adId
	 * @param dateStart
	 * @param dateEnd
	 * @return
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/ad/{adId}")
	public List<ReportAd> getReportByAdId(
	    @PathVariable(value = "adId") Long adId,
	    @RequestParam("dateStart") String dateStart,
	    @RequestParam("dateEnd") String dateEnd) {

		return this.listReportByAdId(adId, dateStart, dateEnd);
	}

}
