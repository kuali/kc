/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.s2s;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.kuali.kra.infrastructure.KraServiceLocator;

import edu.mit.coeus.utils.dbengine.DBConnection;

/**
 * @author geo
 *
 */
public class KualiDataSourceConnectionManager implements DBConnection {
    private static final String DATA_SOURCE = "dataSource";
    private static final Logger LOG = Logger.getLogger(KualiDataSourceConnectionManager.class);
	
	/* (non-Javadoc)
	 * @see edu.mit.coeus.utils.dbengine.DBConnection#freeConnection(java.lang.String, java.sql.Connection)
	 */
	public void freeDatabaseConnection(Connection conn)
			throws SQLException {
	    try{
	        if(conn!=null){
	            conn.close();
	        }
	    }catch(Throwable ex){
	        LOG.error(ex);
	    }
	}

	/* (non-Javadoc)
	 * @see edu.mit.coeus.utils.dbengine.DBConnection#getConnection(java.lang.String)
	 */
	public Connection getDatabaseConnection() throws SQLException {
	    DataSource ds = (DataSource)KraServiceLocator.getService(DATA_SOURCE);
		return ds==null?null:ds.getConnection();
	}
}
