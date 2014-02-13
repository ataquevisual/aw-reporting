package com.google.api.ads.adwords.jaxws.extensions.kratu.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.api.ads.adwords.jaxws.extensions.report.model.entities.ReportAdGroup;

/**
 * 
 * @author gustavomoreira
 * 
 */
@Controller
@RequestMapping(value = "/reportadgroup")
public class ReportAdGroupController extends
    AbstractReportController<ReportAdGroup> {

	/**
	 * 
	 */
	public ReportAdGroupController() {
		super(ReportAdGroup.class);
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
	public List<ReportAdGroup> getReportByCampaignId(
	    @PathVariable(value = "campaignId") Long campaignId,
	    @RequestParam("dateStart") String dateStart,
	    @RequestParam("dateEnd") String dateEnd) {

		return this.listReportByCampaignId(campaignId, dateStart, dateEnd);
	}

	/**
	 * 
	 * @param campaignId
	 * @param dateStart
	 * @param dateEnd
	 * @return
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/adgroup/{adGroupId}")
	public List<ReportAdGroup> getReportByAdGroupId(
	    @PathVariable(value = "adGroupId") Long adgroupId,
	    @RequestParam("dateStart") String dateStart,
	    @RequestParam("dateEnd") String dateEnd) {

		return this.listReportByAdGroupId(adgroupId, dateStart, dateEnd);
	}

}
