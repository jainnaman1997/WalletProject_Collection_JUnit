package com.cg.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.cg.bean.Account;
import com.cg.exception.InsuffecientFundException;
import com.cg.service.AccountService;
import com.cg.service.Validator;

class ServiceTest {

	AccountService service=new AccountService();
	@Test	//addAccount
	void UTC01() {
		Account ob=new Account(101, 945852514, "Ram", 4000.00);		
		assertTrue(service.addAccount(ob));
		
	}
	
	@Test	// validate aid
	void UTC02() {
		Account ob=new Account(11, 945852514, "Ram", 4000.00);
		String s_id=Integer.toString(ob.getAid());
		assertFalse(Validator.validatedata(s_id, Validator.aidpattern));
		
	}
	
	@Test	// validate name
	void UTC03() {
		Account ob=new Account(101, 894585251, "ram", 4000.00);
		String s_id=ob.getAccountholder();
		assertFalse(Validator.validatedata(s_id, Validator.namepattern));
		
	}
	
	@Test	// validate balance
	void UTC04() {
		Account ob=new Account(11, 945852514, "Ram", 100.00);
		String s_id=Double.toString(ob.getBalance());
		assertFalse(Validator.validatedata(s_id, Validator.balancepatter));
		
	}
	
	@Test	//withdraw throw
	void UTC05() {
		Account ob=new Account(101, 945852514, "Ram", 4000.00);
		assertThrows(InsuffecientFundException.class, ()->service.withdraw(ob, 4000.00));
	}
	
	@Test	//null name
	void UTC06() {
		Account ob=new Account(101, 945852514, null, 4000.00);
		String s_id=ob.getAccountholder();
		assertNull(s_id);
	}
	
	@Test	//deposit
	void UTC07() {
		Account ob=new Account(101, 945852514, "Ram", 4000.00);
		assertEquals(5000, service.deposite(ob, 1000.00));
	}
	
	@Test	//transfer throw
	void UTC08() {
		Account from=new Account(101, 945852514, "Ram", 8000.00);
		Account to=new Account(102, 945852513, "Sham", 3000.00);
		assertThrows(InsuffecientFundException.class, ()->service.transferMoney(from, to, 7500));
	}
	
	@Test	//transfer
	void UTC09() throws InsuffecientFundException {
		Account from=new Account(101, 945852514, "Ram", 8000.00);
		Account to=new Account(102, 945852513, "Sham", 3000.00);
		assertEquals(1, service.transferMoney(from, to, 5000));
	}
	
	@Test	//delete Account
	void UTC10() {
		Account ob=new Account(101, 945852514, "Ram", 4000.00);		
		service.addAccount(ob);
		assertTrue(service.deleteAccount(ob));
		
	}
	
	@Test	//update Account
	void UTC11() {
		Account ob=new Account(101, 945852514, "Ram", 4000.00);		
		service.addAccount(ob);
		ob=new Account(101, 945852514, "Sham", 4000.00);
		assertTrue(service.updateAccount(ob));
		
	}
	
	@Test	//find Account
	void UTC12() {
		Account ob=new Account(101, 945852514, "Ram", 4000.00);		
		service.addAccount(ob);
		assertEquals(ob, service.findAccount(ob.getMobile()));
		assertNotNull(service.findAccount(ob.getMobile()));
	}

}
