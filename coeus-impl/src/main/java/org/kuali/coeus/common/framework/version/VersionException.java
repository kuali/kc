/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
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
package org.kuali.coeus.common.framework.version;

/**
 * This exception will be thrown when an attempted versioning fails
 */
public class VersionException extends RuntimeException {
    private static final long serialVersionUID = 4329253003064449628L;

    public VersionException() {
        super();
    }

    public VersionException(String message, Throwable cause) {
        super(message, cause);
    }

    public VersionException(String message) {
        super(message);
    }

    public VersionException(Throwable cause) {
        super(cause);
    }
    
}
