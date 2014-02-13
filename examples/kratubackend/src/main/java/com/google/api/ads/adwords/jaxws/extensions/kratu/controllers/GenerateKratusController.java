package com.google.api.ads.adwords.jaxws.extensions.kratu.controllers;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.api.ads.adwords.jaxws.extensions.kratu.data.Account;
import com.google.api.ads.adwords.jaxws.extensions.kratu.data.Kratu;
import com.google.api.ads.adwords.jaxws.extensions.kratu.data.StorageHelper;
import com.google.api.ads.adwords.jaxws.extensions.processors.ReportProcessor;
import com.google.api.ads.adwords.jaxws.extensions.report.model.util.DateUtil;
import com.google.common.collect.Lists;

/**
 * 
 * @author gustavomoreira
 * 
 */
@Controller
@RequestMapping("processkratus")
public class GenerateKratusController {

	@Autowired
	private StorageHelper storageHelper;
	
	@Autowired
	private ReportProcessor reportProcessor;

	/**
	 * 
	 * @param topAccountId
	 * @param dateStartString
	 * @param dateEndString
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public String generateKratus(@RequestParam("topAccountId") Long topAccountId,
	    @RequestParam("dateStart") String dateStartString,
	    @RequestParam("dateEnd") String dateEndString) throws Exception {

		this.updateAccounts();
		
		DateTime minus30Days = new DateTime().minusDays(30);
		Date dateStart = dateStartString == null ? minus30Days.toDate() : DateUtil
		    .parseDateTime(dateStartString).toDate();
		Date dateEnd = dateEndString == null ? new Date() : DateUtil.parseDateTime(
		    dateEndString).toDate();

		List<Account> listAccounts = storageHelper.getEntityPersister().get(
		    Account.class);

		// Get all the (not-MCC) Accounts under TopAccount
		int i = 0;
		for (Account account : listAccounts) {
			if (!account.getIsCanManageClients()) {
				System.out.println();
				System.out.print(i++);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(dateStart);
				while (calendar.getTime().compareTo(dateEnd) <= 0) {

					Kratu kratu = Kratu.createDailyKratuFromDB(this.storageHelper,
					    topAccountId, account, calendar.getTime());
					if (kratu != null) {
						storageHelper.saveKratu(kratu);
					}
					calendar.add(Calendar.DATE, 1);
					System.out.print(".");
				}
			}
		}

		List<String> indexes = Lists.newArrayList();
		indexes.add(Kratu._externalCustomerId);
		indexes.add(Kratu._day);
		storageHelper.getEntityPersister().createIndex(Kratu.class, indexes);

		return "OK";
	}

	/**
	 * Refreshes the Accounts by downloading the whole list using the API and
	 * refreshes the report indexes before heavily reading reports.
	 */
	private void updateAccounts() throws Exception {

		// Refresh Account List and refresh indexes
		System.out
		    .println("Updating Accounts from server... (may take few seconds)");
		this.storageHelper.getEntityPersister().save(
		    Account.fromList(this.reportProcessor.getAccounts()));
		System.out.println("Updating DB indexes... (may take few seconds)");
		this.storageHelper.createReportIndexes();
	}

}
