package com.google.api.ads.adwords.jaxws.extensions.kratu.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.api.ads.adwords.jaxws.extensions.report.model.entities.ReportCampaignNegativeKeyword;

/**
 * 
 * @author gustavomoreira
 * 
 */
@Controller
@RequestMapping("/reportcampaignnegativekeyword")
public class ReportCampaignNegativeKeywordController extends
    AbstractReportController<ReportCampaignNegativeKeyword> {

	/**
	 * 
	 */
	public ReportCampaignNegativeKeywordController() {
		super(ReportCampaignNegativeKeyword.class);
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
	public List<ReportCampaignNegativeKeyword> getReportByCampaignId(
	    @PathVariable(value = "campaignId") Long campaignId,
	    @RequestParam("dateStart") String dateStart,
	    @RequestParam("dateEnd") String dateEnd) {

		return this.listReportByCampaignId(campaignId, dateStart, dateEnd);
	}

	/**
	 * 
	 * @param adId
	 * @param dateStart
	 * @param dateEnd
	 * @return
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/keyword/{criterionId}")
	public List<ReportCampaignNegativeKeyword> getReportByAdId(
	    @PathVariable(value = "criterionId") Long criterionId,
	    @RequestParam("dateStart") String dateStart,
	    @RequestParam("dateEnd") String dateEnd) {

		return this.listReportByCriterionId(criterionId, dateStart, dateEnd);
	}

}
