/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.document.search;


public class ProposalDocumentSearchResultProcessor {    
/*
    extends StandardDocumentSearchResultProcessor {

    private static final String PROPERTY_NAME_COPY_DOCUMENT = "copyDocument";
    private static final String DOC_TYPE_PROPOSAL_DEVELOPMENT = "ProposalDevelopmentDocument";
    private static final String DOC_COPY_HANDLER_ACTION = "DocCopyHandler.do";
    
    @Override
    public List<Column> constructColumnList(DocSearchCriteriaDTO criteria, List<DocSearchDTO> dtos) {
        List<Column> proposalSearchResultColumns = super.constructColumnList(criteria, dtos);
        this.addColumnUsingKey(proposalSearchResultColumns, PROPERTY_NAME_COPY_DOCUMENT, "Copy Document");
        
        return proposalSearchResultColumns;
    }  
    
    private String buildDocCopyHandlerUrl(String documentNumber) {
        String appContext = ConfigContext.getCurrentContextConfig().getProperty(Constants.APP_CONTEXT_KEY);
        StringBuffer urlBuffer = new StringBuffer();
        urlBuffer.append("/");
        urlBuffer.append(appContext);
        urlBuffer.append("/");
        urlBuffer.append(DOC_COPY_HANDLER_ACTION);
        urlBuffer.append("?");
        urlBuffer.append(KRADConstants.PARAMETER_DOC_ID);
        urlBuffer.append("=");
        urlBuffer.append(documentNumber);
        urlBuffer.append(KRADConstants.DOCHANDLER_URL_CHUNK);
        urlBuffer.append("&documentTypeName=" + DOC_TYPE_PROPOSAL_DEVELOPMENT);
        
        return urlBuffer.toString();
    }
    
    @Override
    public boolean isDocumentHandlerPopup() {
        String applicationConstant =  Utilities.getKNSParameterValue(
                KewApiConstants.KEW_NAMESPACE, "DocumentSearch", KewApiConstants.DOCUMENT_SEARCH_DOCUMENT_POPUP_IND);
        return (KEWConstants.DOCUMENT_SEARCH_DOCUMENT_POPUP_VALUE.equals(applicationConstant));
    }

    private DisplayValues getCustomFieldValue(DocSearchDTO docSearchDTO) {
        String copyDocumentUrl = buildDocCopyHandlerUrl(docSearchDTO.getDocumentId().toString());
        String linkPopup = "";
        
        
        DisplayValues dv = null;

        if(StringUtils.isNotEmpty(docSearchDTO.getDocTypeName()) && docSearchDTO.getDocTypeName().equalsIgnoreCase(DOC_TYPE_PROPOSAL_DEVELOPMENT)) {
            if (this.isDocumentHandlerPopup()) {
                linkPopup = " target=\"_blank\"";
            }
            dv = new DisplayValues();
            dv.htmlValue = "<a href=\"" + copyDocumentUrl
                    + "\"" + linkPopup + "> copy</a>";
            dv.userDisplayValue = "<a href=\"" + copyDocumentUrl
            + "\"" + linkPopup + "> copy</a>";
        } 
        return dv;
    }
    
    @Override
    public KeyValueSort generateSearchResult(DocSearchDTO docSearchDTO, Column column, Map<String,Object> sortValuesByColumnKey) {
        KeyValueSort returnValue = null;
        DisplayValues fieldValue = null;
        Object sortFieldValue = null;
        String columnKeyName = column.getPropertyName();
        SearchableAttributeValue attributeValue = null;
        
        if (PROPERTY_NAME_COPY_DOCUMENT.equals(columnKeyName)) {
            fieldValue = this.getCustomFieldValue(docSearchDTO);
            sortFieldValue = sortValuesByColumnKey.get(columnKeyName);
            column.setMaxLength(150); // for now force this.  100 is not enough in some cases
            column.setEscapeXMLValue(false);            
            
        } else { 
            returnValue = super.generateSearchResult(docSearchDTO, column, sortValuesByColumnKey);
        }
        if (fieldValue != null) {
            String userDisplaySortValue = fieldValue.userDisplayValue;
            if (StringUtils.isBlank(userDisplaySortValue)) {
                userDisplaySortValue = fieldValue.htmlValue;
            }
            returnValue = new KeyValueSort(columnKeyName, fieldValue.htmlValue,
                    fieldValue.userDisplayValue,
                    (sortFieldValue != null) ? sortFieldValue
                            : userDisplaySortValue, attributeValue);
        }
        
        return returnValue;
    }
    */
}
