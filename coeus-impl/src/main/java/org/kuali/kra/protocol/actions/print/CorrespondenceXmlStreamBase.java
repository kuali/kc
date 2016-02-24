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
package org.kuali.kra.protocol.actions.print;

import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.framework.print.stream.xml.PrintBaseXmlStream;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

import java.util.Map;




/**
 * This class is used to populate the xml beans objects for Correspondence schema elements in irb.xsd.
 */
public abstract class CorrespondenceXmlStreamBase extends PrintBaseXmlStream {

    public abstract Map<String, XmlObject> generateXmlStream(KcPersistableBusinessObjectBase printableBusinessObject, Map<String, Object> reportParameters);
    
}
