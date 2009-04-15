/*
 * Copyright 2006-2009 The Kuali Foundation
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
package edu.yale.its.tp.cas.auth.provider;

import edu.yale.its.tp.cas.auth.*;

/** A simple, dummy authenticator. */
public class SampleHandler extends WatchfulPasswordHandler {

    public boolean authenticate(javax.servlet.ServletRequest request,
                                String username,
                                String password) {

	/*
         * As a demonstration, accept any username/password combination
         * where the username matches the password.
         */
	if (username.equals(password))
	    return true;

	return false;
    }
}
