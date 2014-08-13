/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.coeus.sys.framework.persistence;

import org.apache.ojb.broker.accesslayer.conversions.ConversionException;
import org.apache.ojb.broker.accesslayer.conversions.FieldConversion;

public class OjbBlobClobFieldConversion implements FieldConversion {

    @Override
    public Object javaToSql(Object source) throws ConversionException {
        byte[] sourceBytes = (byte[])source;
        return sourceBytes==null?null:new String(sourceBytes);
    }

    @Override
    public Object sqlToJava(Object source) throws ConversionException {
        if(source==null) return null;
        byte[] sourceBytes = null;
        if(source instanceof String){
            sourceBytes = source.toString().getBytes();
        }else if(source instanceof char[]){
            sourceBytes = new String((char[])source).getBytes();
        }
        return sourceBytes;
    }
}
