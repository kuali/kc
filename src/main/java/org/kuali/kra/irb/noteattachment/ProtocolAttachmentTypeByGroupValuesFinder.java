/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.irb.noteattachment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.lookup.keyvalues.KeyValuesBase;
import org.kuali.core.lookup.keyvalues.KeyValuesFinder;
import org.kuali.core.web.ui.KeyLabelPair;
import org.kuali.kra.lookup.keyvalue.ConditionValuesFinder;
import org.kuali.kra.lookup.keyvalue.PrefixValuesFinder;
import org.kuali.kra.lookup.keyvalue.SortedValuesFinder;


/**
 * This class can be used in a tag file like the following
 * <pre>
    <c:set var="property" value="notesAndAttachmentsHelper.newAttachmentProtocol.typeCode" />

    <%-- attachment type finder logic start--%>
        <jsp:useBean id="typeParamsType" class="java.util.HashMap"/>
        <c:set target="${typeParamsType}" property="groupCode" value="${notesAndAttachmentsHelper.newAttachmentProtocol.groupCode}" />
        <c:set target="${typeParamsType}" property="filterTypes" value="${KualiForm.document.protocol.attachmentProtocols}" />
        <c:set var="options" value="${krafn:getOptionList('org.kuali.kra.irb.noteattachment.ProtocolAttachmentTypeByGroupValuesFinder', typeParamsType)}" />
    <%-- attachment type finder logic end --%>
    
    <%-- attachment type error handling logic start--%>
        <kul:checkErrors keyMatch="${property}" auditMatch="${property}"/>
        <c:set var="textStyle" value="${hasErrors == true ? 'background-color:#FFD5D5' : ''}"/>
    <%-- attachment type error handling logic start--%>
    
    <html:select property="${property}" style="${textStyle}">
        <html:options collection="options" labelProperty="label" property="key" />
    </html:select>
 * </pre>
 * 
 * See {@link #getKeyValues()}.
 */
public class ProtocolAttachmentTypeByGroupValuesFinder extends KeyValuesBase {
    
    private static final String GROUP_CODE_NAME = "groupCode";
    private static final String TYPE_DESCRIPTION_NAME = "type.description";
    private static final String TYPE_CODE_NAME = "type.code";
    
    private String groupCode;
    private Collection<ProtocolAttachmentBase> filterTypes;

    /**
     * Gets the keyvalue pair for {@link ProtocolAttachmentTypeGroup ProtocolAttachmentTypeGroup}.
     * The key is the typeCode and the value is the type description.
     * <p>
     * {@link #setGroupCode(String) setGroupCode(String)}
     * must be called with valid values before calling this method.
     * </p>
     * @return a list of {@link KeyLabelPair KeyLabelPair}
     */
    public List<KeyLabelPair> getKeyValues() {   
        this.validateRequiredProperties();
        
        @SuppressWarnings("unchecked")
        final List<KeyLabelPair> exemptionTypes = this.createKeyValuesFinder().getKeyValues();
        return this.filterUsedTypes(exemptionTypes);
    }
    
    /**
     * Creates the {@link KeyValuesFinder KeyValuesFinder} that is used by this finder.
     * <p>
     * Default visibility for easier testing.
     * </p>
     * @return the {@link KeyValuesFinder KeyValuesFinder}
     */
    KeyValuesFinder createKeyValuesFinder() {
        ConditionValuesFinder<ProtocolAttachmentTypeGroup> condFinder
            = new ConditionValuesFinder<ProtocolAttachmentTypeGroup>();
        condFinder.setClazz(ProtocolAttachmentTypeGroup.class);
        condFinder.setKey(TYPE_CODE_NAME);
        condFinder.setValue(TYPE_DESCRIPTION_NAME);
        condFinder.setConditions(Collections.<String, Object>singletonMap(GROUP_CODE_NAME, this.getGroupCode()));
        return new PrefixValuesFinder(new SortedValuesFinder(condFinder));
    }
    
    /**
     * returns a KeyLabelPair list removing all items with type codes matching the type codes contained in
     * {@link #filterTypes filterTypes}.
     * @param unfiltered the unfiltered list.
     * @return a filtered list.
     */
    private List<KeyLabelPair> filterUsedTypes(final List<KeyLabelPair> unfiltered) {      
        assert unfiltered != null : "unfiltered is null";
        
        final List<KeyLabelPair> filtered = new ArrayList<KeyLabelPair>();
        for (KeyLabelPair item : unfiltered) {
            if (!containsType((String) item.getKey())) {
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
    private boolean containsType(final String typeCode) {       
        
        if (this.filterTypes == null) {
            return false;
        }
        
        for (final ProtocolAttachmentBase attachment : this.filterTypes) {
            if (attachment.getTypeCode().equals(typeCode)) {
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
    public Collection<ProtocolAttachmentBase> getFilterTypes() {
        return new ArrayList<ProtocolAttachmentBase>(this.filterTypes);
    }

    /**
     * Sets the types to filter.
     * @param filterTypes the types to filter
     */
    public void setFilterTypes(final Collection<ProtocolAttachmentBase> filterTypes) {
        this.filterTypes = new ArrayList<ProtocolAttachmentBase>(filterTypes);
    }
    
    /**
     * Validates the the proper fields have been set on this object.
     * @throws IllegalStateException if this properties are invalid
     */
    private void validateRequiredProperties() {
        if (StringUtils.isBlank(this.groupCode)) {
            throw new IllegalStateException("the groupCode has not been set to a non-blank value");
        }
    }
}

