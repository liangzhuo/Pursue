package com.zyff.service;

import com.zyff.repository.AccountRepository;

public class TransferService {
	public TransferService(AccountRepository accountRepository) {
	}

	public void say(){
		System.out.println("Hello TransferService !");
	}
}
