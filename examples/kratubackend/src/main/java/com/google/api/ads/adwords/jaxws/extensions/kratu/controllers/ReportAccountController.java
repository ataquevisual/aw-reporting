package com.google.api.ads.adwords.jaxws.extensions.kratu.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.api.ads.adwords.jaxws.extensions.report.model.entities.ReportAccount;

/**
 * @author gustavomoreira
 */
@Controller
@RequestMapping(value = "/reportaccount")
public class ReportAccountController extends
    AbstractReportController<ReportAccount> {

	/**
	 * 
	 */
	public ReportAccountController() {
		super(ReportAccount.class);
	}

}
