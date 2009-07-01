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
package org.kuali.kra.s2s.generator.impl;

import gov.grants.apply.forms.rrBudgetV10.RRBudgetDocument.RRBudget;
import gov.grants.apply.forms.rrSubawardBudgetV11.RRSubawardBudgetDocument;
import gov.grants.apply.forms.rrSubawardBudgetV11.RRSubawardBudgetDocument.RRSubawardBudget;
import gov.grants.apply.forms.rrSubawardBudgetV11.RRSubawardBudgetDocument.RRSubawardBudget.BudgetAttachments;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.budget.bo.BudgetSubAwards;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.S2SException;
import org.kuali.kra.s2s.util.S2SConstants;
import org.kuali.kra.s2s.validator.S2SErrorMessages;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Class for generating the XML object for grants.gov RRSubAwardBudgetV1.1. Form is generated using XMLBean classes and is based on
 * RRSubAwardBudget schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class RRSubAwardBudgetV1_1Generator extends RRSubAwardBudgetBaseGenerator {

    private static final Logger LOG = Logger.getLogger(RRSubAwardBudgetV1_1Generator.class);
    private ProposalDevelopmentDocument proposalDevelopmentDocument;

    /**
     * 
     * This method is to get SubAward Budget details
     * 
     * @return rrSubawardBudgetDocument {@link XmlObject} of type RRSubawardBudgetDocument.
     */
    private RRSubawardBudgetDocument getRRSubAwardBudget() {

        RRSubawardBudgetDocument rrSubawardBudgetDocument = RRSubawardBudgetDocument.Factory.newInstance();
        RRSubawardBudget rrSubawardBudget = RRSubawardBudget.Factory.newInstance();

        BudgetAttachments budgetAttachments = BudgetAttachments.Factory.newInstance();
        List<BudgetSubAwards> budgetSubAwardsList = getBudgetSubAwards(proposalDevelopmentDocument);
        RRBudget[] budgetList = new RRBudget[budgetSubAwardsList.size()];
        rrSubawardBudget.setFormVersion(S2SConstants.FORMVERSION_1_1);
        int attCount = 1;
        for (BudgetSubAwards budgetSubAwards : budgetSubAwardsList) {
            switch (attCount) {
                case 1:
                    rrSubawardBudget.setATT1(prepareAttName(budgetSubAwards));
                    budgetList[0] = getRRBudget(budgetSubAwards);
                    break;
                case 2:
                    rrSubawardBudget.setATT2(prepareAttName(budgetSubAwards));
                    budgetList[1] = getRRBudget(budgetSubAwards);
                    break;
                case 3:
                    rrSubawardBudget.setATT3(prepareAttName(budgetSubAwards));
                    budgetList[2] = getRRBudget(budgetSubAwards);
                    break;
                case 4:
                    rrSubawardBudget.setATT4(prepareAttName(budgetSubAwards));
                    budgetList[3] = getRRBudget(budgetSubAwards);
                    break;
                case 5:
                    rrSubawardBudget.setATT5(prepareAttName(budgetSubAwards));
                    budgetList[4] = getRRBudget(budgetSubAwards);
                    break;
                case 6:
                    rrSubawardBudget.setATT6(prepareAttName(budgetSubAwards));
                    budgetList[5] = getRRBudget(budgetSubAwards);
                    break;
                case 7:
                    rrSubawardBudget.setATT7(prepareAttName(budgetSubAwards));
                    budgetList[6] = getRRBudget(budgetSubAwards);
                    break;
                case 8:
                    rrSubawardBudget.setATT8(prepareAttName(budgetSubAwards));
                    budgetList[7] = getRRBudget(budgetSubAwards);
                    break;
                case 9:
                    rrSubawardBudget.setATT9(prepareAttName(budgetSubAwards));
                    budgetList[8] = getRRBudget(budgetSubAwards);
                    break;
                case 10:
                    rrSubawardBudget.setATT10(prepareAttName(budgetSubAwards));
                    budgetList[9] = getRRBudget(budgetSubAwards);
                    break;
            }
            attCount++;
        }
        budgetAttachments.setRRBudgetArray(budgetList);
        rrSubawardBudget.setBudgetAttachments(budgetAttachments);
        rrSubawardBudgetDocument.setRRSubawardBudget(rrSubawardBudget);
        return rrSubawardBudgetDocument;
    }

    /**
     * 
     * This method is used to get RRBudget from BudgetSubAwards
     * 
     * @param budgetSubAwards(BudgetSubAwards) budget sub awards entry.
     * @return RRBudget corresponding to the BudgetSubAwards object.
     */
    private RRBudget getRRBudget(BudgetSubAwards budgetSubAwards) {
        RRBudget rrBudget = RRBudget.Factory.newInstance();
        String subAwdXML = budgetSubAwards.getSubAwardXmlFileData();
        Document subAwdFormsDoc;
        try {
            subAwdFormsDoc = stringToDom(subAwdXML);
        }
        catch (S2SException e1) {
            return rrBudget;
        }
        Element subAwdFormsElement = subAwdFormsDoc.getDocumentElement();
        NodeList subAwdNodeList = subAwdFormsElement.getElementsByTagNameNS(NAMESPACE_URI, LOCAL_NAME);
        if (subAwdNodeList != null && subAwdNodeList.getLength() == 0) {
            String err1 = S2SErrorMessages.getProperty(ERROR1_PROPERTY_KEY);
            String err2 = S2SErrorMessages.getProperty(ERROR2_PROPERTY_KEY);
            StringBuilder err = new StringBuilder();
            err.append(err1);
            err.append(" for organization ");
            err.append(budgetSubAwards.getOrganizationName());
            err.append(" ");
            err.append(err2);
            LOG.error("Not able to extract xml" + err.toString());
            return null;
        }
        Node subAwdNode = null;
        if (subAwdNodeList != null) {
            subAwdNode = subAwdNodeList.item(0);
        }
        byte[] subAwdNodeBytes = null;
        try {
            subAwdNodeBytes = docToBytes(nodeToDom(subAwdNode));
            InputStream bgtIS = new ByteArrayInputStream(subAwdNodeBytes);
            rrBudget = (RRBudget) XmlObject.Factory.parse(bgtIS);
        }
        catch (S2SException e) {
            return rrBudget;
        }
        catch (XmlException e) {
            return rrBudget;
        }
        catch (IOException e) {
            return rrBudget;
        }
        return rrBudget;
    }

    /**
     * 
     * This method is used to get BudgetSubAwrads from ProposalDevelopmentDocument
     * 
     * @param proposalDevelopmentDocument (ProposalDevelopmentDocument)
     * @return List<BudgetSubAwards> list of budget sub awards.
     */

    private List<BudgetSubAwards> getBudgetSubAwards(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        List<BudgetSubAwards> budgetSubAwardsList = new ArrayList<BudgetSubAwards>();
        // TODO need to set to BudgetSubAwrads from ProposalDevelopmentDocument
        return budgetSubAwardsList;
    }


    /**
     * This method creates {@link XmlObject} of type {@link RRSubawardBudgetDocument} by populating data from the given
     * {@link ProposalDevelopmentDocument}
     * 
     * @param proposalDevelopmentDocument for which the {@link XmlObject} needs to be created
     * @return {@link XmlObject} which is generated using the given {@link ProposalDevelopmentDocument}
     * @see org.kuali.kra.s2s.generator.S2SFormGenerator#getFormObject(ProposalDevelopmentDocument)
     */
    public XmlObject getFormObject(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        this.proposalDevelopmentDocument=proposalDevelopmentDocument;
        return getRRSubAwardBudget();
    }

    /**
     * This method typecasts the given {@link XmlObject} to the required generator type and returns back the document of that
     * generator type.
     * 
     * @param xmlObject which needs to be converted to the document type of the required generator
     * @return {@link XmlObject} document of the required generator type
     * @see org.kuali.kra.s2s.generator.S2SFormGenerator#getFormObject(XmlObject)
     */
    public XmlObject getFormObject(XmlObject xmlObject) {
        RRSubawardBudget rrSubawardBudget = (RRSubawardBudget) xmlObject;
        RRSubawardBudgetDocument rrSubawardBudgetDocument = RRSubawardBudgetDocument.Factory.newInstance();
        rrSubawardBudgetDocument.setRRSubawardBudget(rrSubawardBudget);
        return rrSubawardBudgetDocument;
    }

}
