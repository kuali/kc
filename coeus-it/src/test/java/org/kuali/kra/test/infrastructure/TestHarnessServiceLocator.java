/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.test.infrastructure;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.sql.DataSource;
import javax.transaction.UserTransaction;

/**
 * Locator that sits on the testharness SpringContext.
 */
public class TestHarnessServiceLocator {

	private static ConfigurableApplicationContext context;
	
	public static final String USER_TRANSACTION = "userTransaction";
	public static final String TRANSACTON_MANAGER = "transactionManager";
	public static final String DATA_SOURCE = "dataSource";
	
	public static Object getService(String serviceName) {
		return getContext().getBean(serviceName);
	}
	
	public static DataSource getDataSource() {
		return (DataSource)getService(DATA_SOURCE);
	}
	
	public static JtaTransactionManager getJtaTransactionManager() {
		return (JtaTransactionManager)getService(TRANSACTON_MANAGER);
	}
	
	public static UserTransaction getUserTransaction() {
		return (UserTransaction)getService(USER_TRANSACTION);
	}
	
	public static ConfigurableApplicationContext getContext() {
		return context;
	}

	public static void setContext(ConfigurableApplicationContext context) {
		TestHarnessServiceLocator.context = context;
	}	
}
