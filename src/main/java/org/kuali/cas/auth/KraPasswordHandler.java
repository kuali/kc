/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.cas.auth;

import java.security.GeneralSecurityException;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.Person;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.rice.shim.UniversalUser;
import org.kuali.kra.rice.shim.UniversalUserService;
import org.kuali.kra.service.PersonService;
import org.kuali.rice.core.service.EncryptionService;
import org.kuali.rice.kew.user.AuthenticationUserId;
import org.kuali.rice.kim.service.IdentityManagementService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.service.KualiConfigurationService;

import edu.yale.its.tp.cas.auth.provider.WatchfulPasswordHandler;

public class KraPasswordHandler extends WatchfulPasswordHandler {
    protected static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(KraPasswordHandler.class);
    /**
     * Authenticates the given username/password pair, returning true on success
     * and false on failure.
     */
    public boolean authenticate(javax.servlet.ServletRequest request, String username, String password) {
        if (super.authenticate(request, username, password) != false) {
            try {
                if (username != null && !username.trim().equals( "" ) ) {
                    
                    username = username.trim();
                    
                    // check the username and password against the db
                    // return true if they are there and have a valid password
                    if ( LOG.isDebugEnabled() ) {
                           LOG.debug( "Attempting login for user id: " + 
                                      username + " and password hash: " + 
                                      (String) ((password==null) ? "null" : KNSServiceLocator.getEncryptionService().hash( password.trim())) );
                    }
                   
                    // obtain the user record
                    
                    UniversalUserService uus = KraServiceLocator.getService(UniversalUserService.class);
                    UniversalUser user;
                    try {
                        user = uus.getUniversalUser( new AuthenticationUserId( username ) );
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    if ( LOG.isDebugEnabled() ) {
                        LOG.debug( "Found user " + user.getPersonName() + " with password hash: " + user.getFinancialSystemsEncryptedPasswordText() );
                    }
                    
                    PersonService personService = KraServiceLocator.getService(PersonService.class);
                    Person person = personService.getPersonByName(username);
                    if (person == null) {
                        LOG.info( "User " + username + " was not found in the Person table." );
                        return false; // fail if user does not exist
                    }
                    if (!person.getActive()) {
                        LOG.info( "User " + username + " is inactive." );
                        return false; // fail if user does not exist
                    }
                    
                    // check if the password needs to be checked (if in a production environment or password turned on explicitly)
                    // TODO turn this on
                    KualiConfigurationService kcs = KraServiceLocator.getService(KualiConfigurationService.class);
                    //IdentityManagementService ims = KraServiceLocator.getService(IdentityManagementService.class);
                    if ( kcs.isProductionEnvironment()  ) { // || ims.authenticationServiceValidatesPassword()
                    
                        // if so, hash the passed in password and compare to the hash retrieved from the database
                        
                        String hashedPassword = user.getFinancialSystemsEncryptedPasswordText();
                        if ( hashedPassword == null ) {
                            hashedPassword = "";
                        }
                        
                        EncryptionService es = KraServiceLocator.getService(EncryptionService.class);
                        hashedPassword = StringUtils.stripEnd( hashedPassword, EncryptionService.HASH_POST_PREFIX );
                        if ( es.hash( password.trim() ).equals( hashedPassword ) ) {
                            return true; // password matched
                        }
                    } else {
                        LOG.warn( "WARNING: password checking is disabled - user " + username + " has been authenticated without a password." );
                        return true; // no need to check password - user's existence is enough 
                    }
                } 
            } catch ( GeneralSecurityException ex ) {
                LOG.error( "Error validating password", ex );
                return false; // fail if the hash function fails
            } 
        }
        LOG.warn( "CAS base password handler failed authenication for " + username + " based on number of attempts." );
        return false; // fail if we get to this point
    }
}