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
package org.kuali.kra.irb.noteattachment;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.keyvalue.ConditionValuesFinder;
import org.kuali.coeus.sys.framework.keyvalue.PrefixValuesFinder;
import org.kuali.coeus.sys.framework.keyvalue.SortedValuesFinder;
import org.kuali.kra.protocol.noteattachment.ProtocolAttachmentBase;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.keyvalues.KeyValuesFinder;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


/**
 * This class can be used in a tag file like the following
 * <pre>
    <c:set var="property" value="notesAttachmentsHelper.newAttachmentProtocol.typeCode" />

    <%-- attachment type finder logic start--%>
        <jsp:useBean id="typeParamsType" class="java.util.HashMap"/>
        <c:set target="${typeParamsType}" property="groupCode" value="${notesAttachmentsHelper.newAttachmentProtocol.groupCode}" />
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
public class ProtocolAttachmentTypeByGroupValuesFinder <T extends ProtocolAttachmentBase & org.kuali.kra.protocol.noteattachment.TypedAttachment> extends UifKeyValuesFinderBase {
    

    private static final long serialVersionUID = 1806457024473671666L;
    private static final String GROUP_CODE_NAME = "groupCode";
    private static final String TYPE_DESCRIPTION_NAME = "type.description";
    private static final String TYPE_CODE_NAME = "type.code";
    
    private String groupCode;
    private Collection<T> filterTypes;

    /**
     * Gets the keyvalue pair for {@link ProtocolAttachmentTypeGroup ProtocolAttachmentTypeGroup}.
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
     * returns a KeyValue list removing all items with type codes matching the type codes contained in
     * {@link #filterTypes filterTypes}.
     * @param unfiltered the unfiltered list.
     * @return a filtered list.
     */
    private List<KeyValue> filterUsedTypes(final List<KeyValue> unfiltered) {      
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
    private boolean containsType(final String typeCode) {       
        
        if (this.filterTypes == null) {
            return false;
        }
        
        for (final org.kuali.kra.protocol.noteattachment.TypedAttachment attachment : this.filterTypes) {
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
    private void validateRequiredProperties() {
        if (StringUtils.isBlank(this.groupCode)) {
            throw new IllegalStateException("the groupCode has not been set to a non-blank value");
        }
    }
}

