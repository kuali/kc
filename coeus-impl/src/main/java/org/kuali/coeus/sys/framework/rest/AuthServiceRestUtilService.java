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
package org.kuali.coeus.sys.framework.rest;

import org.springframework.http.HttpHeaders;

public interface AuthServiceRestUtilService {

	/**
	 * Gets auth server style headers for the given version. Depends on there being an active usersession that was authenticated via AuthServiceFilter.
	 * @return Headers for connecting to auth service style rest endpoints. Will contain invalid/blank authentication token in the case that
	 * a valid user session does not exist.
	 */
	HttpHeaders getAuthServiceStyleHttpHeadersForUser();
	
	HttpHeaders getAuthServiceStyleHttpHeadersForToken(String authToken);
}
