/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.hierarchyrouting;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

import edu.iu.uis.eden.engine.node.hierarchyrouting.HierarchyProvider.Stop;

public class UnitStop implements Stop {
    // maybe, rice should implement this as a base type, so the other app can extend it ?
    public UnitStop parent;
    public List<UnitStop> children = new ArrayList<UnitStop>();
    public String id;

    public String toString() {
        return new ToStringBuilder(this).append("id", id)
                                        .append("parent", parent == null ? null : parent.id)
                                        .append("children", StringUtils.join(CollectionUtils.collect(children, new Transformer() {
                                                    public Object transform(Object o) { return ((UnitStop) o).id; }
                                                })
                                                , ','))
                                        .toString();
                                                
    }
    
    public boolean equals(Object o) {
        if (!(o instanceof UnitStop)) return false;
        return id.equals(((UnitStop) o).id);
    }
    
    public int hashCode() {
        return ObjectUtils.hashCode(id);
    }
}



