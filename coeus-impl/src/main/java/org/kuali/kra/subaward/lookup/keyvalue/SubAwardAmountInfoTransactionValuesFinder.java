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
package org.kuali.kra.subaward.lookup.keyvalue;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.keyvalue.FormViewAwareUifKeyValuesFinderBase;
import org.kuali.kra.subaward.document.SubAwardDocument;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SubAwardAmountInfoTransactionValuesFinder extends FormViewAwareUifKeyValuesFinderBase {

    private static final ConcreteKeyValue SELECT = new ConcreteKeyValue("", "select");

    @Override
    public List<KeyValue> getKeyValues() {
        final SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        return Stream.concat(Stream.of(SELECT),
                ((SubAwardDocument) getDocument()).getSubAward().getAllSubAwardAmountInfos()
                .stream()
                .filter(info -> StringUtils.isNotEmpty(info.getModificationTypeCode()))
                .filter(info -> info.getModificationEffectiveDate() != null)
                .peek(info -> {
                    if (info.getModificationType() == null) {
                        info.refreshReferenceObject("modificationType");
                    }
                })
                .map(info -> new ConcreteKeyValue(info.getSubAwardAmountInfoId().toString(),
                        info.getModificationType().getDescription() + " - " + sdf.format(info.getEffectiveDate()))))
                .collect(Collectors.toList());
    }
}
