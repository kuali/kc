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
package org.kuali.cas.auth;

import java.security.GeneralSecurityException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.kuali.core.exceptions.UserNotFoundException;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.kra.bo.Person;
import org.kuali.kra.infrastructure.KraServiceLocator;

import edu.yale.its.tp.cas.auth.provider.WatchfulPasswordHandler;

public class KraPasswordHandler extends WatchfulPasswordHandler {
    protected static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(KraPasswordHandler.class);
    /**
     * Authenticates the given username/password pair, returning true on success
     * and false on failure.
     */
    public boolean authenticate(javax.servlet.ServletRequest request, String username, String password) {
        if (super.authenticate(request, username, password) != false) {
            //try {
                if (username != null && !username.trim().equals( "" ) ) {
                    // check the username and password against the db
                    // return true if they are there and have a valid password
                    //if ( LOG.isDebugEnabled() ) {
                    //    LOG.debug( "Attempting login for user id: " + username + " and password hash: " + SpringServiceLocator.getEncryptionService().hash( password.trim() ) );
                    //}
                    // obtain the universal user record
                    BusinessObjectService businessObjectService = (BusinessObjectService) KraServiceLocator.getService("businessObjectService");
                    Map personMap = new HashMap();
                    personMap.put("userName", username.trim());
                    Collection coll = businessObjectService.findMatching(Person.class, personMap);
                    if (coll.isEmpty()) {
                        LOG.info( "User " + username + " was not found in the Person table." );
                        return false; // fail if user does not exist
                    } else if (coll.size() > 1) {
                        LOG.info( "User " + username + ": multiple records found in the Person table!" );
                        return false; // fail if multiple users present (bad data; shouldn't happen)
                    }
                    // UniversalUser user = SpringServiceLocator.getUniversalUserService().getUniversalUser( new AuthenticationUserId( username.trim() ) );
                    //if ( LOG.isDebugEnabled() ) {
                    //    LOG.debug( "Found user " + user.getPersonName() + " with password hash: " + user.getFinancialSystemsEncryptedPasswordText() );
                    //}
                    // check if the password needs to be checked (if in a production environment or password turned on explicitly)
                    // TODO turn this on
//                    if ( SpringServiceLocator.getKualiConfigurationService().isProductionEnvironment() || SpringServiceLocator.getWebAuthenticationService().isValidatePassword() ) {
//                        // if so, hash the passed in password and compare to the hash retrieved from the database
//                        String hashedPassword = user.getFinancialSystemsEncryptedPasswordText();
//                        if ( hashedPassword == null ) {
//                            hashedPassword = "";
//                        }
//                        hashedPassword = StringUtils.stripEnd( hashedPassword, EncryptionService.HASH_POST_PREFIX );
//                        if ( SpringServiceLocator.getEncryptionService().hash( password.trim() ).equals( hashedPassword ) ) {
//                            return true; // password matched
//                        }
//                    } else {
                        LOG.warn( "WARNING: password checking is disabled - user " + username + " has been authenticated without a password." );
                        return true; // no need to check password - user's existence is enough 
//                    }
                }
//            } catch ( GeneralSecurityException ex ) {
//                LOG.error( "Error validating password", ex );
//                return false; // fail if the hash function fails
//            } catch ( UserNotFoundException ex ) {
//                LOG.info( "User " + username + " was not found in the Person table." );
//                return false; // fail if user does not exist
//            }

        }
        LOG.warn( "CAS base password handler failed authenication for " + username + " based on number of attempts." );
        return false; // fail if we get to this point
    }
}