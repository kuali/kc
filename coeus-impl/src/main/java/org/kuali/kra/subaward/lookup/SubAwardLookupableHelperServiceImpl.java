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
package org.kuali.kra.subaward.lookup;

import org.kuali.coeus.common.framework.version.VersionStatus;
import org.kuali.coeus.sys.framework.util.CollectionUtils;
import org.kuali.kra.lookup.KraLookupableHelperServiceImpl;
import org.kuali.kra.subaward.bo.SubAward;
import org.kuali.kra.subaward.document.SubAwardDocument;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.kns.web.ui.Field;
import org.kuali.rice.kns.web.ui.Row;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.UrlFactory;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This class is for SubAwardLookupableHelperServiceImpl
 * for lookup searches...
 */
public class SubAwardLookupableHelperServiceImpl extends KraLookupableHelperServiceImpl {

    private static final String REQUISITIONER_USER_NAME="requisitionerUserName";
    public static final String VERSION_HISTORY_STATUS_FOR_OJB = "versionHistory.statusForOjb";
    public static final String VIEW_DOCUMENT = "viewDocument";
    public static final String DOC_OPENED_FROM_AWARD_SEARCH = "docOpenedFromAwardSearch";
    public static final String DOC_ID = "docId";
    public static final String PLACE_HOLDER_AWARD_ID = "placeHolderAwardId";
    public static final String START_DATE = "startDate";
    public static final String END_DATE = "endDate";
    public static final String CLOSEOUT_DATE = "closeoutDate";
    public static final String SUB_AWARD_HOME_DO = "subAwardHome.do";
    public static final String SUB_AWARD_DOCUMENT = "SubAwardDocument";
    public static final String SUB_AWARD_ID = "SubAwardId";
    public static final String MEDUSA = "medusa";
    public static final String REGEX_PATTERN = "(?i)%s";

    @Override
    public List<? extends BusinessObject>
    getSearchResults(Map<String, String> fieldValues) {
        super.setBackLocationDocFormKey(fieldValues);
        String requisitionerUserName = fieldValues.get(REQUISITIONER_USER_NAME);
        fieldValues.remove(REQUISITIONER_USER_NAME);
        fieldValues.put(VERSION_HISTORY_STATUS_FOR_OJB, VersionStatus.ACTIVE.toString());
        return filterForRequisitionerUserName((List<SubAward>) super.getSearchResultsUnbounded(fieldValues),requisitionerUserName);
    }

    /**.
     * this method for getCustomActionUrls
     * @param businessObject
     * @param pkNames
     *
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<HtmlData> getCustomActionUrls(
    BusinessObject businessObject, List pkNames) {
        List<HtmlData> htmlDataList =
        super.getCustomActionUrls(businessObject, pkNames);
        htmlDataList.add(getOpenLink((SubAward) businessObject, false));
        htmlDataList.add(getMedusaLink((SubAward) businessObject, false));
        return htmlDataList;
    }
   /**.
    * This method is for getOpenLink
     * @param subAward
     * @param viewOnly
     * @return htmlData
     */
    protected AnchorHtmlData getOpenLink(SubAward subAward, Boolean viewOnly) {
        SubAwardDocument subAwardDocument = subAward.getSubAwardDocument();
        AnchorHtmlData htmlData = new AnchorHtmlData();
        htmlData.setDisplayText("open");
        Properties parameters = new Properties();
        parameters.put(KRADConstants.DISPATCH_REQUEST_PARAMETER,
        KRADConstants.DOC_HANDLER_METHOD);
        parameters.put(KRADConstants.PARAMETER_COMMAND,
        KewApiConstants.DOCSEARCH_COMMAND);
        parameters.put(KRADConstants.DOCUMENT_TYPE_NAME, getDocumentTypeName());
        parameters.put(VIEW_DOCUMENT, viewOnly.toString());
        parameters.put(DOC_OPENED_FROM_AWARD_SEARCH, "true");
        parameters.put(DOC_ID, subAwardDocument.getDocumentNumber());
        parameters.put(PLACE_HOLDER_AWARD_ID,
                subAward.getSubAwardId().toString());
        String href = UrlFactory.parameterizeUrl(
        "../" + getHtmlAction(), parameters);
        htmlData.setHref(href);
        return htmlData;
    }
    /**.
     * This Method is for getMedusaLink
     * @param subAward
     * @param readOnly
     * @return htmlData
     */
    protected AnchorHtmlData getMedusaLink(
    SubAward subAward, Boolean readOnly) {
        AnchorHtmlData htmlData = new AnchorHtmlData();
        htmlData.setDisplayText(MEDUSA);
        Properties parameters = new Properties();
        parameters.put(KRADConstants.DISPATCH_REQUEST_PARAMETER, MEDUSA);
        parameters.put(KRADConstants.PARAMETER_COMMAND,
        KewApiConstants.DOCSEARCH_COMMAND);
        parameters.put(KRADConstants.DOCUMENT_TYPE_NAME, getDocumentTypeName());
        parameters.put(VIEW_DOCUMENT, readOnly.toString());
        parameters.put(DOC_ID, subAward.
        getSubAwardDocument().getDocumentNumber());
        parameters.put(DOC_OPENED_FROM_AWARD_SEARCH, "true");
        parameters.put(PLACE_HOLDER_AWARD_ID,
        subAward.getSubAwardId().toString());
        String href  = UrlFactory.parameterizeUrl(
         "../" + getHtmlAction(), parameters);
        htmlData.setHref(href);
        return htmlData;
    }

      @Override
    public List<Row> getRows() {
        List<Row> rows = super.getRows();
        for (Row row : rows) {
            for (Field field : row.getFields()) {

                if (field.getPropertyName().equals(START_DATE) || field.getPropertyName().equals(END_DATE)
                        || field.getPropertyName().equals(CLOSEOUT_DATE)) {
                    field.setDatePicker(true);
                }
                if (field.getPropertyName().equals(REQUISITIONER_USER_NAME)) {
                    field.setFieldConversions("principalName:requisitionerUserName,principalId:requisitionerId");
                }
            }
        }
        return rows;
    }
      @Override
      protected void addEditHtmlData(List<HtmlData> htmlDataList, BusinessObject businessObject) {
          //no-op
      }

    protected List<SubAward> filterForRequisitionerUserName(List<SubAward> subAwards,
                                                            String requisitionerUserName) {
        List<SubAward> filteredSubAwards = CollectionUtils.createCorrectImplementationForCollection(subAwards);
        if (requisitionerUserName != null && !requisitionerUserName.equalsIgnoreCase("")) {
            filteredSubAwards.addAll(subAwards.stream().filter(subAward ->
                    subAward.getRequisitionerUserName().matches(createSearchRegexFromString(requisitionerUserName))).collect(Collectors.toList()));
        } else {
            filteredSubAwards.addAll(subAwards);
        }

        return filteredSubAwards;
    }

    protected String createSearchRegexFromString(String string) {
        return String.format(REGEX_PATTERN,string.replaceAll("\\*",".*")
                        .replaceAll("%", ".*")
                        .replaceAll("\\?", "."));
    }

      @Override
      protected String getHtmlAction() {
          return SUB_AWARD_HOME_DO;
      }

    @Override
    protected String getDocumentTypeName() {
        return SUB_AWARD_DOCUMENT;
    }

    @Override
    protected String getKeyFieldName() {
        return SUB_AWARD_ID;
    }

}
