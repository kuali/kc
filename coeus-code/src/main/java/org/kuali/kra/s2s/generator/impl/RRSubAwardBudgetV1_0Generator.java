/*
 * Copyright 2005-2014 The Kuali Foundation.
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

import gov.grants.apply.forms.rrBudgetV10.RRBudgetDocument;
import gov.grants.apply.forms.rrBudgetV10.RRBudgetDocument.RRBudget;
import gov.grants.apply.forms.rrSubawardBudgetV10.RRSubawardBudgetDocument;
import gov.grants.apply.forms.rrSubawardBudgetV10.RRSubawardBudgetDocument.RRSubawardBudget;
import gov.grants.apply.forms.rrSubawardBudgetV10.RRSubawardBudgetDocument.RRSubawardBudget.BudgetAttachments;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.budget.bo.BudgetSubAwards;
import org.kuali.kra.s2s.S2SException;
import org.kuali.kra.s2s.util.S2SConstants;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Class for generating the XML object for grants.gov RRSubAwardBudgetV1.0. Form is generated using XMLBean classes and is based on
 * RRSubAwardBudget schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class RRSubAwardBudgetV1_0Generator extends S2SAdobeFormAttachmentBaseGenerator {


    /**
     * 
     * This method is to get SubAward Budget details
     * 
     * @return rrSubawardBudgetDocument {@link XmlObject} of type RRSubawardBudgetDocument.
     */
    private RRSubawardBudgetDocument getRRSubawardBudgetDocument() {
        RRSubawardBudgetDocument rrSubawardBudgetDocument = RRSubawardBudgetDocument.Factory.newInstance();

        RRSubawardBudget rrSubawardBudget = RRSubawardBudget.Factory.newInstance();
        BudgetAttachments budgetAttachments = BudgetAttachments.Factory.newInstance();
        List<BudgetSubAwards> budgetSubAwardsList = getBudgetSubAwards(pdDoc,RR_BUDGET_10_NAMESPACE_URI, true);
        RRBudget[] budgetList = new RRBudget[budgetSubAwardsList.size()];


        rrSubawardBudget.setFormVersion(S2SConstants.FORMVERSION_1_0);
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
            addSubAwdAttachments(budgetSubAwards);
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
     * @param budgetSubAwards (BudgetSubAwards) budget sub awards entry.
     * @return RRBudget corresponding to the BudgetSubAwards object.
     */
    private RRBudget getRRBudget(BudgetSubAwards budgetSubAwards) {
        RRBudgetDocument rrBudgetDocument = RRBudgetDocument.Factory.newInstance();
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
        NodeList subAwdNodeList = subAwdFormsElement.getElementsByTagNameNS(RR_BUDGET_10_NAMESPACE_URI, LOCAL_NAME);
        Node subAwdNode = null;
        if (subAwdNodeList != null){
            if(subAwdNodeList.getLength() == 0) {
                return null;
            }
            subAwdNode = subAwdNodeList.item(0);
        }
        byte[] subAwdNodeBytes = null;
        try {
            subAwdNodeBytes = docToBytes(nodeToDom(subAwdNode));
            InputStream bgtIS = new ByteArrayInputStream(subAwdNodeBytes);
            rrBudgetDocument = (RRBudgetDocument) XmlObject.Factory.parse(bgtIS);
            rrBudget = rrBudgetDocument.getRRBudget();
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

//    /**
//     * 
//     * This method is used to get BudgetSubAwrads from ProposalDevelopmentDocument
//     * 
//     * @param proposalDevelopmentDocument (ProposalDevelopmentDocument)
//     * @return List<BudgetSubAwards> list of budget sub awards.
//     */
//
//    private List<BudgetSubAwards> getBudgetSubAwards(ProposalDevelopmentDocument proposalDevelopmentDocument) {
//        List<BudgetSubAwards> budgetSubAwardsList = new ArrayList<BudgetSubAwards>();
//        // TODO need to set to BudgetSubAwrads from ProposalDevelopmentDocument
//        return budgetSubAwardsList;
//    }

    /**
     * This method creates {@link XmlObject} of type {@link RRSubawardBudgetDocument} by populating data from the given
     * {@link ProposalDevelopmentDocument}
     * 
     * @param proposalDevelopmentDocument for which the {@link XmlObject} needs to be created
     * @return {@link XmlObject} which is generated using the given {@link ProposalDevelopmentDocument}
     * @see org.kuali.kra.s2s.generator.S2SFormGenerator#getFormObject(ProposalDevelopmentDocument)
     */
    public XmlObject getFormObject(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        this.pdDoc = proposalDevelopmentDocument;
        return getRRSubawardBudgetDocument();
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
