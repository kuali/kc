/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.proposaldevelopment.service.impl;

import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.ojb.broker.PersistenceBroker;
import org.kuali.kra.proposaldevelopment.service.StateDao;
import org.kuali.rice.core.api.resourceloader.GlobalResourceLoader;
import org.kuali.rice.core.framework.persistence.dao.PlatformAwareDao;
import org.kuali.rice.core.framework.persistence.platform.DatabasePlatform;
import org.springmodules.orm.ojb.support.PersistenceBrokerDaoSupport;

public class StateDaoOjbImpl extends PersistenceBrokerDaoSupport implements PlatformAwareDao, StateDao {
    
    private DatabasePlatform dbPlatform;
    
    public synchronized DatabasePlatform getDbPlatform(){
        if (this.dbPlatform == null) {
            //this.dbPlatform = KRADServiceLocatorInternal.getDatabasePlatform();
            this.dbPlatform = GlobalResourceLoader.<DatabasePlatform>getService("dbPlatform");
        }
        return dbPlatform;
    }
    
    public synchronized void setDbPlatform(DatabasePlatform dbPlatform) {
        this.dbPlatform = dbPlatform;
    }
    
    @Override
    public String convertAltCountryCodeToRealCountryCode(String currentCountryCode) {
        try {
            Statement stmt = null;
            PersistenceBroker pbInstance = getPersistenceBroker(true);
            
            StringBuffer query = new StringBuffer("SELECT POSTAL_CNTRY_CD FROM KRLC_CNTRY_T WHERE ALT_POSTAL_CNTRY_CD = '");
            query.append(currentCountryCode).append("'");
        
            stmt = pbInstance.serviceConnectionManager().getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(query.toString());
            while(rs.next()){
                return rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return "";
    }
}