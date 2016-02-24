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
package org.kuali.kra.irb.protocol.participant;

import org.junit.Before;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.keyvalue.ValuesFinderTestBase;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Test the Participant Type Values Finder.
 *
 * @author Kuali Research Administration Team (kc.dev@kuali.org)
 */
public class ParticipantTypeValuesFinderTest extends ValuesFinderTestBase {
    @Before
    public void setUp() throws Exception {
        GlobalVariables.clear();
        GlobalVariables.clear();
    }
    
    @Override
    protected Class<ParticipantTypeValuesFinder> getTestClass() {
        return ParticipantTypeValuesFinder.class;
    }
    
    @Override
    protected ParticipantTypeValuesFinder getKeyValuesFinder() {
    	return new ParticipantTypeValuesFinder() {
    	    protected Document getDocument() {
    	    	ProtocolDocument doc = new ProtocolDocument();
    	    	doc.setProtocol(new Protocol());
    	    	return doc;
    	    }
    	};
    }

    @Override
    protected List<KeyValue> getKeyValues() {
        final List<KeyValue> keylabel = new ArrayList<KeyValue>();
        
        keylabel.add(createKeyValue("", "select"));
        keylabel.add(createKeyValue("1", "Children"));
        keylabel.add(createKeyValue("2", "Decisionally impaired"));
        keylabel.add(createKeyValue("3", "Employees"));
        keylabel.add(createKeyValue("4", "Prisoners"));
        keylabel.add(createKeyValue("5", "Pregnant women"));
        keylabel.add(createKeyValue("6", "Fetuses"));
        keylabel.add(createKeyValue("7", "Students"));
        keylabel.add(createKeyValue("8", "Students - minors"));
        keylabel.add(createKeyValue("9", "Wards of the state"));
        keylabel.add(createKeyValue("10", "Other"));
        
        return keylabel;
    }
}
