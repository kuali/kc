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
package org.kuali.kra.iacuc.noteattachment;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.keyvalue.ConditionValuesFinder;
import org.kuali.coeus.sys.framework.keyvalue.PrefixValuesFinder;
import org.kuali.coeus.sys.framework.keyvalue.SortedValuesFinder;
import org.kuali.kra.protocol.noteattachment.ProtocolAttachmentBase;
import org.kuali.kra.protocol.noteattachment.ProtocolAttachmentTypeGroupBase;
import org.kuali.kra.protocol.noteattachment.TypedAttachment;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.keyvalues.KeyValuesFinder;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class IacucProtocolAttachmentTypeByGroupValuesFinder <T extends ProtocolAttachmentBase & TypedAttachment> 
        extends UifKeyValuesFinderBase {
    private static final long serialVersionUID = -4314458177687918434L;

    private static final String GROUP_CODE_NAME = "groupCode";
    private static final String TYPE_DESCRIPTION_NAME = "type.description";
    private static final String TYPE_CODE_NAME = "type.code";
    
    private String groupCode;
    private Collection<T> filterTypes;

    /**
     * Gets the keyvalue pair for {@link ProtocolAttachmentTypeGroupBase ProtocolAttachmentTypeGroup}.
     * The key is the typeCode and the value is the type description.
     * <p>
     * {@link #setGroupCode(String) setGroupCode(String)}
     * must be called with valid values before calling this method.
     * </p>
     * @return a list of {@link KeyValue KeyValue}
     */
    @Override
    public List<KeyValue> getKeyValues() {
        this.validateRequiredProperties();
        
        @SuppressWarnings("unchecked")
        final List<KeyValue> exemptionTypes = this.createKeyValuesFinder().getKeyValues();
        return this.filterUsedTypes(exemptionTypes);
    }
    
    /**
     * Creates the {@link KeyValuesFinder KeyValuesFinder} that is used by this finder.
     * <p>
     * Default visibility for easier testing.
     * </p>
     * @return the {@link KeyValuesFinder KeyValuesFinder}
     */
    protected KeyValuesFinder createKeyValuesFinder() {
        ConditionValuesFinder<IacucProtocolAttachmentTypeGroup> condFinder
            = new ConditionValuesFinder<IacucProtocolAttachmentTypeGroup>();
        condFinder.setClazz(IacucProtocolAttachmentTypeGroup.class);
        condFinder.setKey(TYPE_CODE_NAME);
        condFinder.setValue(TYPE_DESCRIPTION_NAME);
        condFinder.setConditions(Collections.<String, Object>singletonMap(GROUP_CODE_NAME, this.getGroupCode()));
        return new PrefixValuesFinder(new SortedValuesFinder(condFinder));
    }
        
    /**
     * returns a KeyValue list removing all items with type codes matching the type codes contained in
     * {@link #filterTypes filterTypes}.
     * @param unfiltered the unfiltered list.
     * @return a filtered list.
     */
    protected List<KeyValue> filterUsedTypes(final List<KeyValue> unfiltered) {      
        assert unfiltered != null : "unfiltered is null";
        
        final List<KeyValue> filtered = new ArrayList<KeyValue>();
        for (KeyValue item : unfiltered) {
            if (!this.containsType((String) item.getKey())) {
                filtered.add(item);
            }
        }
        
        return filtered;
    }
    
    /**
     * Checks if a type code matches a type code contained in
     * {@link #filterTypes filterTypes}.
     * @param typeCode the typeCode
     * @return true if a match exists. false if not. 
     */
    protected boolean containsType(final String typeCode) {       
        
        if (this.filterTypes == null) {
            return false;
        }
        
        for (final TypedAttachment attachment : this.filterTypes) {
            if (attachment.getType().getCode().equals(typeCode)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Gets the group code.
     * @return the group code
     */
    public String getGroupCode() {
        return this.groupCode;
    }

    /**
     * Sets the group code.
     * @param groupCode the group code
     */
    public void setGroupCode(final String groupCode) {
        this.groupCode = groupCode;
    }

    /**
     * Gets the types to filter.
     * @return the types to filter
     */
    public Collection<T> getFilterTypes() {
        return (this.filterTypes != null) ? new ArrayList<T>(this.filterTypes) : null;
    }

    /**
     * Sets the types to filter.
     * @param filterTypes the types to filter
     */
    public void setFilterTypes(final Collection<T> filterTypes) {
        this.filterTypes = (filterTypes != null) ? new ArrayList<T>(filterTypes) : null;
    }
    
    /**
     * Validates the the proper fields have been set on this object.
     * @throws IllegalStateException if this properties are invalid
     */
    protected void validateRequiredProperties() {
        if (StringUtils.isBlank(this.groupCode)) {
            throw new IllegalStateException("the groupCode has not been set to a non-blank value");
        }
    }
}
