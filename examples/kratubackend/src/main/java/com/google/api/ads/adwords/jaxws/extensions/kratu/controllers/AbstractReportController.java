package com.google.api.ads.adwords.jaxws.extensions.kratu.controllers;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.api.ads.adwords.jaxws.extensions.kratu.data.StorageHelper;
import com.google.api.ads.adwords.jaxws.extensions.report.model.entities.Report;
import com.google.api.ads.adwords.jaxws.extensions.report.model.util.DateUtil;

public abstract class AbstractReportController<R extends Report> {

	@Autowired
	private StorageHelper storageHelper;

	private final Class<R> reportClass;

	/**
	 * 
	 * @param reportClass
	 */
	public AbstractReportController(Class<R> reportClass) {
		this.reportClass = reportClass;
	}

	/**
	 * 
	 * @param dateStartString
	 * @param dateEndString
	 * @return
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public List<R> getReport(@RequestParam("dateStart") String dateStart,
	    @RequestParam("dateEnd") String dateEnd) {

		return this.listReportByDates(dateStart, dateEnd);
	}

	/**
	 * 
	 * @param accountId
	 * @param dateStart
	 * @param dateEnd
	 * @return
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/account/{accountId}")
	public List<R> getReportByAccountId(
	    @PathVariable(value = "accountId") Long accountId,
	    @RequestParam("dateStart") String dateStart,
	    @RequestParam("dateEnd") String dateEnd) {

		return this.listReportByAccountId(accountId, dateStart, dateEnd);
	}


	/**
	 * 
	 * @param accountId
	 * @param dateStart
	 * @param dateEnd
	 * @return
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/{topAccountId}")
	public List<R> getReportByTopAccountId(
	    @PathVariable(value = "topAccountId") Long topAccountId,
	    @RequestParam("dateStart") String dateStart,
	    @RequestParam("dateEnd") String dateEnd) {

		return this.listReportByTopAccountId(topAccountId, dateStart, dateEnd);
	}
	
	/**
	 * 
	 * @param accountId
	 * @param dateStartString
	 * @param dateEndString
	 * @return
	 */
	protected List<R> listReportByAccountId(Long accountId,
	    String dateStartString, String dateEndString) {

		Date dateStart = this.parseDateStart(dateStartString);
		Date dateEnd = this.parseDateEnd(dateEndString);

		return this.getStorageHelper().getReportByAccountId(this.reportClass,
		    accountId, dateStart, dateEnd);
	}
	
	/**
	 * 
	 * @param topAccountId
	 * @param dateStartString
	 * @param dateEndString
	 * @return
	 */
	protected List<R> listReportByTopAccountId(Long topAccountId,
	    String dateStartString, String dateEndString) {

		Date dateStart = this.parseDateStart(dateStartString);
		Date dateEnd = this.parseDateEnd(dateEndString);

		return this.getStorageHelper().getReportByTopAccountId(this.reportClass,
		    topAccountId, dateStart, dateEnd);
	}

	/**
	 * 
	 * @param campaignId
	 * @param dateStartString
	 * @param dateEndString
	 * @return
	 */
	protected List<R> listReportByCampaignId(Long campaignId,
	    String dateStartString, String dateEndString) {

		Date dateStart = this.parseDateStart(dateStartString);
		Date dateEnd = this.parseDateEnd(dateEndString);

		return this.getStorageHelper().getReportByCampaignId(this.reportClass,
		    campaignId, dateStart, dateEnd);
	}

	/**
	 * 
	 * @param adGroupId
	 * @param dateStartString
	 * @param dateEndString
	 * @return
	 */
	protected List<R> listReportByAdGroupId(Long adGroupId,
	    String dateStartString, String dateEndString) {

		Date dateStart = this.parseDateStart(dateStartString);
		Date dateEnd = this.parseDateEnd(dateEndString);

		return this.getStorageHelper().getReportByAdGroupId(this.reportClass,
		    adGroupId, dateStart, dateEnd);
	}

	/**
	 * 
	 * @param adId
	 * @param dateStartString
	 * @param dateEndString
	 * @return
	 */
	protected List<R> listReportByAdId(Long adId, String dateStartString,
	    String dateEndString) {

		Date dateStart = this.parseDateStart(dateStartString);
		Date dateEnd = this.parseDateEnd(dateEndString);

		return this.getStorageHelper().getReportByAdId(this.reportClass, adId,
		    dateStart, dateEnd);
	}

	/**
	 * 
	 * @param criterionId
	 * @param dateStartString
	 * @param dateEndString
	 * @return
	 */
	protected List<R> listReportByCriterionId(Long criterionId,
	    String dateStartString, String dateEndString) {

		Date dateStart = this.parseDateStart(dateStartString);
		Date dateEnd = this.parseDateEnd(dateEndString);

		return this.getStorageHelper().getReportByCriterionId(this.reportClass,
		    criterionId, dateStart, dateEnd);
	}

	/**
	 * 
	 * @param adExtensionId
	 * @param dateStartString
	 * @param dateEndString
	 * @return
	 */
	protected List<R> listReportByAdExtensionId(Long adExtensionId,
	    String dateStartString, String dateEndString) {

		Date dateStart = this.parseDateStart(dateStartString);
		Date dateEnd = this.parseDateEnd(dateEndString);

		return this.getStorageHelper().getReportByAdExtensionId(this.reportClass,
		    adExtensionId, dateStart, dateEnd);
	}

	/**
	 * 
	 * @param dateStartString
	 * @param dateEndString
	 * @return
	 */
	protected List<R> listReportByDates(String dateStartString,
	    String dateEndString) {

		Date dateStart = this.parseDateStart(dateStartString);
		Date dateEnd = this.parseDateEnd(dateEndString);

		return this.getStorageHelper().getReportByDates(this.reportClass,
		    dateStart, dateEnd);
	}

	/**
	 * 
	 * @param dateEndString
	 * @return
	 */
	protected Date parseDateEnd(String dateEndString) {
		return dateEndString == null ? new Date() : DateUtil.parseDateTime(
		    dateEndString).toDate();
	}

	/**
	 * 
	 * @param dateStartString
	 * @return
	 */
	protected Date parseDateStart(String dateStartString) {
		DateTime minus30Days = new DateTime().minusDays(30);
		return dateStartString == null ? minus30Days.toDate() : DateUtil
		    .parseDateTime(dateStartString).toDate();
	}

	/**
	 * 
	 * @return
	 */
	protected StorageHelper getStorageHelper() {
		return storageHelper;
	}
}
