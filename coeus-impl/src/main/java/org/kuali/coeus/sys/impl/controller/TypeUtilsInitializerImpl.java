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
package org.kuali.coeus.sys.impl.controller;

import org.kuali.coeus.sys.api.model.ScaleThreeDecimal;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.controller.TypeUtilsInitializer;
import org.kuali.rice.core.api.util.type.TypeUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component("typeUtilsInitializer")
public class TypeUtilsInitializerImpl implements TypeUtilsInitializer {

    @PostConstruct
    public void addToTypeUtils() {
        TypeUtils.addToDecimalType(ScaleThreeDecimal.class);
        TypeUtils.addToDecimalType(ScaleTwoDecimal.class);
    }
}
