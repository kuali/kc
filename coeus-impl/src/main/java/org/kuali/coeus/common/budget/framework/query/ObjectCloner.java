/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.coeus.common.budget.framework.query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;

/**
 * The class is used to Deep Clone an Object
 * 
 * @version 1.0
 */

public class ObjectCloner {
    private static final Log LOG = LogFactory.getLog(ObjectCloner.class);

    // so that nobody can accidentally create an ObjectCloner object
    private ObjectCloner() {
    }

    // returns a deep copy of an object
    static public Object deepCopy(Object oldObj) {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream(); // A
            oos = new ObjectOutputStream(bos); // B
            // serialize and pass the object
            oos.writeObject(oldObj); // C
            oos.flush(); // D
            ByteArrayInputStream bin = new ByteArrayInputStream(bos.toByteArray()); // E
            ois = new ObjectInputStream(bin); // F
            // return the new object
            Object object = ois.readObject();
            oos.close();
            ois.close();
            return object; // G
        }
        catch (Exception e) {
            return null;
        }
        finally {
            try {
                if (oos != null)
                    oos.close();
                if (ois != null)
                    ois.close();
            }catch (IOException e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }
}
