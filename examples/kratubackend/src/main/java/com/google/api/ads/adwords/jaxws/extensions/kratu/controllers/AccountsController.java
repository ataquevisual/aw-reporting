package com.google.api.ads.adwords.jaxws.extensions.kratu.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.api.ads.adwords.jaxws.extensions.kratu.data.Account;
import com.google.api.ads.adwords.jaxws.extensions.kratu.data.StorageHelper;

/**
 * 
 * @author gustavomoreira
 * 
 */
@Controller
@RequestMapping("/accounts")
public class AccountsController {

	@Autowired
	private StorageHelper storageHelper;

	/**
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public List<Account> listAccounts() {
		return this.storageHelper.getEntityPersister().get(Account.class);
	}

}
