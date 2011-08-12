/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.irb.web;

import org.junit.Ignore;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class ProtocolFundingSourceWebTest extends ProtocolWebTestBase {
    
    private static final String PROTOCOL_FUNDINGSOURCE_TYPE_FIELD = "protocolHelper.newFundingSource.fundingSourceTypeCode";
    private static final String PROTOCOL_FUNDINGSOURCE_NUMBER_FIELD = "protocolHelper.newFundingSource.fundingSourceNumber";
    private static final String PROTOCOL_FUNDINGSOURCE_NAME_FIELD = "protocolHelper.newFundingSource.fundingSourceName";
    
    private static final String ADD_LOCATION = "methodToCall.addProtocolFundingSource.anchorFundingSources";
    private static final String VIEW_LOCATION = "methodToCall.viewProtocolFundingSource.line0.anchor27";
    private static final String DELETELINE_1_LOCATION = "methodToCall.deleteProtocolFundingSource.line1.anchor27";
    
    private static final String SPONSOR_FUNDINGSOURCE_VAL = "1";
    private static final String SPONSOR_FUNDINGSOURCE_ID = "005174";
    private static final String SPONSOR_FUNDINGSOURCE_NAME = "Arkansas Enterprises for the Blind"; 
    
    private static final String UNIT_FUNDINGSOURCE_VAL = "2";
    private static final String UNIT_FUNDINGSOURCE_ID = "IU-UNIV";
    private static final String UNIT_FUNDINGSOURCE_NAME = "UNIVERSITY LEVEL";
    
    private static final String OTHER_FUNDINGSOURCE_VAL = "3";
    private static final String OTHER_FUNDINGSOURCE_ID = "123";
    private static final String OTHER_FUNDINGSOURCE_NAME = "Other Name";
    
    private static final String DEVPROP_FUNDINGSOURCE_VAL = "4";
    private static final String DEVPROP_FUNDINGSOURCE_ID = "10000";
    private static final String DEVPROP_FUNDINGSOURCE_NAME = "Novartis Institutes for BioMedical Research Incorporated";
    
    private static final String INSTPROP_FUNDINGSOURCE_VAL = "5";
    private static final String INSTPROP_FUNDINGSOURCE_ID = "Other ID";
    private static final String INSTPROP_FUNDINGSOURCE_NUMBER = "Other Number";
    private static final String INSTPROP_FUNDINGSOURCE_NAME = "Other Name";
    
    private static final String AWARD_FUNDINGSOURCE_VAL = "6";
    private static final String AWARD_FUNDINGSOURCE_ID = "10004";
    private static final String AWARD_FUNDINGSOURCE_NUMBER = "010001-00001";
    private static final String AWARD_FUNDINGSOURCE_NAME = "Award Title";
    
    private static final String CONFIRM_DELETE_YES_BUTTON = "methodToCall.processAnswer.button0";

    @Test
    public void testAddViewSponsorFundingSourcePage() throws Exception {
        HtmlPage protocolPage = getProtocolSavedRequiredFieldsPage();
        
        setFieldValue(protocolPage, PROTOCOL_FUNDINGSOURCE_TYPE_FIELD, SPONSOR_FUNDINGSOURCE_VAL);
        setFieldValue(protocolPage, PROTOCOL_FUNDINGSOURCE_NUMBER_FIELD, SPONSOR_FUNDINGSOURCE_ID);
        setFieldValue(protocolPage, PROTOCOL_FUNDINGSOURCE_NAME_FIELD, SPONSOR_FUNDINGSOURCE_NAME);
        protocolPage = clickOn(protocolPage, ADD_LOCATION);
        
        protocolPage = saveDoc(protocolPage);
        
        assertContains(protocolPage, "Document was successfully saved.");
        assertContains(protocolPage, SPONSOR_FUNDINGSOURCE_ID);
        assertContains(protocolPage, SPONSOR_FUNDINGSOURCE_NAME);
        
        HtmlPage viewSponsorPage = clickOn(protocolPage, VIEW_LOCATION);
        assertContains(viewSponsorPage, "Sponsor Code: " + SPONSOR_FUNDINGSOURCE_ID);
    }
    
    @Test
    public void testAddViewUnitFundingSourcePage() throws Exception {
        HtmlPage protocolPage = getProtocolSavedRequiredFieldsPage();

        setFieldValue(protocolPage, PROTOCOL_FUNDINGSOURCE_TYPE_FIELD, UNIT_FUNDINGSOURCE_VAL);
        setFieldValue(protocolPage, PROTOCOL_FUNDINGSOURCE_NUMBER_FIELD, UNIT_FUNDINGSOURCE_ID);
        setFieldValue(protocolPage, PROTOCOL_FUNDINGSOURCE_NAME_FIELD, UNIT_FUNDINGSOURCE_NAME);
        protocolPage = clickOn(protocolPage, ADD_LOCATION);

        protocolPage = saveDoc(protocolPage);
        
        assertContains(protocolPage, "Document was successfully saved.");
        assertContains(protocolPage, UNIT_FUNDINGSOURCE_ID);
        assertContains(protocolPage, UNIT_FUNDINGSOURCE_NAME);
        
        HtmlPage viewUnitPage = clickOn(protocolPage, VIEW_LOCATION);
        assertContains(viewUnitPage, "Unit Number: " + UNIT_FUNDINGSOURCE_ID);
    }
    
    @Test
    public void testAddOtherFundingSourcePage() throws Exception {
        HtmlPage protocolPage = getProtocolSavedRequiredFieldsPage();

        setFieldValue(protocolPage, PROTOCOL_FUNDINGSOURCE_TYPE_FIELD, OTHER_FUNDINGSOURCE_VAL);
        setFieldValue(protocolPage, PROTOCOL_FUNDINGSOURCE_NUMBER_FIELD, OTHER_FUNDINGSOURCE_ID);
        setFieldValue(protocolPage, PROTOCOL_FUNDINGSOURCE_NAME_FIELD, OTHER_FUNDINGSOURCE_NAME);
        protocolPage = clickOn(protocolPage, ADD_LOCATION);

        protocolPage = saveDoc(protocolPage);
        
        assertContains(protocolPage, "Document was successfully saved.");
        assertContains(protocolPage, OTHER_FUNDINGSOURCE_ID);
        assertContains(protocolPage, OTHER_FUNDINGSOURCE_NAME);
    }
    
    /*
     * This is ignored since there is no available DevProp test data yet...
     */
    @Ignore
    @Test
    public void testAddViewDevelopmentProposalFundingSourcePage() throws Exception {
        HtmlPage protocolPage = getProtocolSavedRequiredFieldsPage();

        setFieldValue(protocolPage, PROTOCOL_FUNDINGSOURCE_TYPE_FIELD, DEVPROP_FUNDINGSOURCE_VAL);
        setFieldValue(protocolPage, PROTOCOL_FUNDINGSOURCE_NUMBER_FIELD, DEVPROP_FUNDINGSOURCE_ID);
        setFieldValue(protocolPage, PROTOCOL_FUNDINGSOURCE_NAME_FIELD, DEVPROP_FUNDINGSOURCE_NAME);
        protocolPage = clickOn(protocolPage, ADD_LOCATION);

        protocolPage = saveDoc(protocolPage);
        
        assertContains(protocolPage, "Document was successfully saved.");
        assertContains(protocolPage, DEVPROP_FUNDINGSOURCE_ID);
        assertContains(protocolPage, DEVPROP_FUNDINGSOURCE_NAME);
        
        HtmlPage viewDevPropPage = clickOn(protocolPage, VIEW_LOCATION);
        assertContains(viewDevPropPage, "Proposal Number: " + DEVPROP_FUNDINGSOURCE_ID);
    }
    
    /*
     * This is ignored since there is no available InstProp test data yet...
     */
    @Ignore
    @Test
    public void testAddViewInstitutionalProposalFundingSourcePage() throws Exception {
        HtmlPage protocolPage = getProtocolSavedRequiredFieldsPage();

        setFieldValue(protocolPage, PROTOCOL_FUNDINGSOURCE_TYPE_FIELD, INSTPROP_FUNDINGSOURCE_VAL);
        setFieldValue(protocolPage, PROTOCOL_FUNDINGSOURCE_NUMBER_FIELD, INSTPROP_FUNDINGSOURCE_ID);
        setFieldValue(protocolPage, PROTOCOL_FUNDINGSOURCE_NAME_FIELD, INSTPROP_FUNDINGSOURCE_NAME);
        protocolPage = clickOn(protocolPage, ADD_LOCATION);

        protocolPage = saveDoc(protocolPage);
        
        assertContains(protocolPage, "Document was successfully saved.");
        assertContains(protocolPage, INSTPROP_FUNDINGSOURCE_NUMBER);
        assertContains(protocolPage, INSTPROP_FUNDINGSOURCE_NAME);
        
        HtmlPage viewDevPropPage = clickOn(protocolPage, VIEW_LOCATION);
        assertContains(viewDevPropPage, "Institutional Proposal ID: " + INSTPROP_FUNDINGSOURCE_NUMBER);
    }
    
    /*
     * This is ignored since there is no available Award test data yet...
     */
    @Ignore
    @Test
    public void testAddViewAwardFundingSourcePage() throws Exception {
        HtmlPage protocolPage = getProtocolSavedRequiredFieldsPage();

        setFieldValue(protocolPage, PROTOCOL_FUNDINGSOURCE_TYPE_FIELD, AWARD_FUNDINGSOURCE_VAL);
        setFieldValue(protocolPage, PROTOCOL_FUNDINGSOURCE_NUMBER_FIELD, AWARD_FUNDINGSOURCE_NUMBER);
        setFieldValue(protocolPage, PROTOCOL_FUNDINGSOURCE_NAME_FIELD, AWARD_FUNDINGSOURCE_NAME);
        protocolPage = clickOn(protocolPage, ADD_LOCATION);

        protocolPage = saveDoc(protocolPage);
        
        assertContains(protocolPage, "Document was successfully saved.");
        assertContains(protocolPage, AWARD_FUNDINGSOURCE_NUMBER);
        assertContains(protocolPage, AWARD_FUNDINGSOURCE_NAME);
        
        HtmlPage viewDevPropPage = clickOn(protocolPage, VIEW_LOCATION);
        assertContains(viewDevPropPage, "Award ID: " + AWARD_FUNDINGSOURCE_NUMBER);
    }
    
    @Test
    public void testAddDeleteFundingSourcePage() throws Exception {
        HtmlPage protocolPage = getProtocolSavedRequiredFieldsPage();
        
        setFieldValue(protocolPage, PROTOCOL_FUNDINGSOURCE_NUMBER_FIELD, SPONSOR_FUNDINGSOURCE_ID);
        setFieldValue(protocolPage, PROTOCOL_FUNDINGSOURCE_NAME_FIELD, SPONSOR_FUNDINGSOURCE_NAME);
        setFieldValue(protocolPage, PROTOCOL_FUNDINGSOURCE_TYPE_FIELD, SPONSOR_FUNDINGSOURCE_VAL);
        protocolPage = clickOn(protocolPage, ADD_LOCATION);
        
        setFieldValue(protocolPage, PROTOCOL_FUNDINGSOURCE_NUMBER_FIELD, UNIT_FUNDINGSOURCE_ID);
        setFieldValue(protocolPage, PROTOCOL_FUNDINGSOURCE_NAME_FIELD, UNIT_FUNDINGSOURCE_NAME);
        setFieldValue(protocolPage, PROTOCOL_FUNDINGSOURCE_TYPE_FIELD, UNIT_FUNDINGSOURCE_VAL);
        protocolPage = clickOn(protocolPage, ADD_LOCATION);

        protocolPage = saveDoc(protocolPage);

        protocolPage = clickOn(protocolPage, DELETELINE_1_LOCATION);
        protocolPage = clickOn(protocolPage, CONFIRM_DELETE_YES_BUTTON);
        protocolPage = saveDoc(protocolPage);
        assertContains(protocolPage, "Document was successfully saved.");
        assertContains(protocolPage, SPONSOR_FUNDINGSOURCE_ID);
        assertContains(protocolPage, SPONSOR_FUNDINGSOURCE_NAME);
        assertDoesNotContain(protocolPage, UNIT_FUNDINGSOURCE_ID);
        assertDoesNotContain(protocolPage, UNIT_FUNDINGSOURCE_NAME);
     }

}