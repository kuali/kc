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
package org.kuali.coeus.sys.impl.auth;

import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.rice.kns.kim.permission.PermissionTypeServiceBase;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component("classNameTypeService")
public class ClassNameTypeServiceImpl extends PermissionTypeServiceBase {

    @Override
    protected List<String> getRequiredAttributes() {
        return Collections.singletonList(KcKimAttributes.CLASS_NAME);
    }

    @Override
    protected boolean isCheckRequiredAttributes() {
        return true;
    }

    @Override
    protected boolean performMatch(Map<String, String> inputAttributes, Map<String, String> storedAttributes) {

        if (storedAttributes == null || inputAttributes == null) {
            return false;
        }

        return !storedAttributes.entrySet().stream()
                .anyMatch(entry -> inputAttributes.containsKey(entry.getKey()) && !matches(inputAttributes.get(entry.getKey()), entry.getValue().replaceAll("\\*", ".*")));
    }

    private boolean matches(String s, String regex) {
        return s != null && regex != null && s.matches(regex);
    }
}
