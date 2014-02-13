package com.google.api.ads.adwords.jaxws.extensions.kratu.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.api.ads.adwords.jaxws.extensions.report.model.entities.ReportAdExtension;

/**
 * 
 * @author gustavomoreira
 * 
 */
@Controller
@RequestMapping("/reportadextension")
public class ReportAdExtensionController extends
    AbstractReportController<ReportAdExtension> {

	/**
	 * 
	 */
	public ReportAdExtensionController() {
		super(ReportAdExtension.class);
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
	public List<ReportAdExtension> getReportByCampaignId(
	    @PathVariable(value = "campaignId") Long campaignId,
	    @RequestParam("dateStart") String dateStart,
	    @RequestParam("dateEnd") String dateEnd) {

		return this.listReportByCampaignId(campaignId, dateStart, dateEnd);
	}

	/**
	 * 
	 * @param adExtensionId
	 * @param dateStart
	 * @param dateEnd
	 * @return
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/adextension/{adExtensionId}")
	public List<ReportAdExtension> getReportByAdExtensionId(
	    @PathVariable(value = "adExtensionId") Long adExtensionId,
	    @RequestParam("dateStart") String dateStart,
	    @RequestParam("dateEnd") String dateEnd) {

		return this.listReportByAdExtensionId(adExtensionId, dateStart, dateEnd);
	}

}
