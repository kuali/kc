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
package org.kuali.coeus.sys.framework.controller.rest;


import org.springframework.beans.BeanWrapper;

public interface RestBeanWrapperFactory {

    /**
     * Creates a bean wrapper for an object and configuring the wrapper with converters and
     * enabling nested property support for our rest framework.
     * @param o the object. cannot be null
     * @return a bean wrapper
     * @throws IllegalArgumentException if o is null
     */
    BeanWrapper newInstance(Object o);
}
