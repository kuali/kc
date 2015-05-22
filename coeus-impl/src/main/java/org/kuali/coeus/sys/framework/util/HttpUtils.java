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
package org.kuali.coeus.sys.framework.util;

import javax.servlet.http.HttpServletResponse;

public final class HttpUtils {

    private HttpUtils() {
        throw new RuntimeException("do not call");
    }

    /**
     * This method sets headers on a response to disable caching. If the response has already been committed this method
     * will do nothing.
     *
     * @param response the http response. cannot be null.
     * @throws IllegalArgumentException if the response is null.
     */
    public static void disableCache(HttpServletResponse response) {
        if (response == null) {
            throw new IllegalArgumentException("response is null");
        }

        if (!response.isCommitted()) {
            response.setHeader("Expires", "-1");
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            response.addHeader("Cache-Control", "post-check=0, pre-check=0");
            response.setHeader("Pragma", "no-cache");
        }
    }
}
