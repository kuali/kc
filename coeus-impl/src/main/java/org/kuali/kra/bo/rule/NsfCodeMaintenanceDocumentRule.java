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
package org.kuali.kra.bo.rule;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.rule.KcMaintenanceDocumentRuleBase;
import org.kuali.kra.bo.NsfCode;
import org.kuali.rice.kns.document.MaintenanceDocument;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Stream;
import static org.kuali.coeus.sys.framework.util.CollectionUtils.entry;
import static org.kuali.coeus.sys.framework.util.CollectionUtils.entriesToMap;


public class NsfCodeMaintenanceDocumentRule extends KcMaintenanceDocumentRuleBase {

    @Override
    protected boolean processCustomSaveDocumentBusinessRules(MaintenanceDocument document) {
        boolean valid = super.processCustomSaveDocumentBusinessRules(document);

        final NsfCode newNsfCode = (NsfCode) document.getNewMaintainableObject().getBusinessObject();
        if (StringUtils.isNotBlank(newNsfCode.getNsfCode()) && newNsfCode.getYear() != null)  {
            final Collection<NsfCode> nsfCodes = getBoService().findMatching(NsfCode.class,
                    Stream.<Map.Entry<String, Object>>of(entry("nsfCode", newNsfCode.getNsfCode()), entry("year", newNsfCode.getYear()))
                            .collect(entriesToMap()));

            if (!nsfCodes.isEmpty()) {
                final NsfCode existing = nsfCodes.iterator().next();
                if (!existing.getNsfSequenceNumber().equals(newNsfCode.getNsfSequenceNumber())) {
                    putFieldError("nsfCode", "error.nsf.code.duplicate.code.year", new String[] {newNsfCode.getNsfCode(), newNsfCode.getYear().toString()});
                    valid = false;
                }
            }
        }

        return valid;
    }
}
