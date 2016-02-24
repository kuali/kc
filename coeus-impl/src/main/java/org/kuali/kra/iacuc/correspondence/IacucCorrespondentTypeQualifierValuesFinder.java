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
package org.kuali.kra.iacuc.correspondence;

import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * This class returns the qualifiers that CorrespondentType can have.
 */
public class IacucCorrespondentTypeQualifierValuesFinder extends UifKeyValuesFinderBase {


    private static final long serialVersionUID = -3106476961230607417L;


    @Override
    public List<KeyValue> getKeyValues() {
        List<KeyValue> KeyValues = new ArrayList<KeyValue>();

        KeyValues.add(new ConcreteKeyValue("", "select"));
        for (IacucCorrespondentTypeQualifierConstants correspondentTypeQualifierConstants : IacucCorrespondentTypeQualifierConstants.values()) {
            KeyValues.add(new ConcreteKeyValue(correspondentTypeQualifierConstants.code(), correspondentTypeQualifierConstants.description()));
        }
        
        return KeyValues; 
    }

}
