/*
 * Copyright 2005-2013 The Kuali Foundation.
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
package org.kuali.kra.s2s.rrsubawardbudget1_3;

import gov.grants.apply.forms.rrBudget13V13.RRBudget13Document;
import gov.grants.apply.forms.rrBudget13V13.RRBudget13Document.RRBudget13;
import gov.grants.apply.forms.rrSubawardBudget13V13.RRSubawardBudget13Document;
import gov.grants.apply.forms.rrSubawardBudget13V13.RRSubawardBudget13Document.RRSubawardBudget13;
import gov.grants.apply.forms.rrSubawardBudget13V13.RRSubawardBudget13Document.RRSubawardBudget13.BudgetAttachments;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.budget.bo.BudgetSubAwards;
import org.kuali.kra.s2s.S2SException;
import org.kuali.kra.s2s.generator.impl.S2SAdobeFormAttachmentBaseGenerator;
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
 * Class for generating the XML object for grants.gov RRSubAwardBudgetV1.2. Form is generated using XMLBean classes and is based on
 * RRSubAwardBudget schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class RRSubAwardBudget1_3V1_3Generator extends S2SAdobeFormAttachmentBaseGenerator {

    private static final String RR_BUDGET1_3_NAMESPACE_URI = "http://apply.grants.gov/forms/RR_Budget_1_3-V1.3";
    private static final String RR_BUDGET1_3_LOCAL_NAME = "RR_Budget_1_3";

    /**
     * 
     * This method is to get SubAward Budget details
     * 
     * @return rrSubawardBudgetDocument {@link XmlObject} of type RRSubawardBudgetDocument.
     */
    private RRSubawardBudget13Document getRRSubawardBudgetDocument() {

        RRSubawardBudget13Document rrSubawardBudgetDocument = RRSubawardBudget13Document.Factory.newInstance();
        RRSubawardBudget13 rrSubawardBudget = RRSubawardBudget13.Factory.newInstance();
        BudgetAttachments budgetAttachments = BudgetAttachments.Factory.newInstance();
        List<BudgetSubAwards> budgetSubAwardsList = getBudgetSubAwards(pdDoc,RR_BUDGET1_3_NAMESPACE_URI,false);
        RRBudget13[] budgetList = new RRBudget13[budgetSubAwardsList.size()];
        rrSubawardBudget.setFormVersion(S2SConstants.FORMVERSION_1_3);

        int attCount = 1;
        for (BudgetSubAwards budgetSubAwards : budgetSubAwardsList) {
            RRBudget13 rrBudget = getRRBudget10(budgetSubAwards).getRRBudget13();
            switch (attCount) {
                case 1:
                    rrSubawardBudget.setATT1(prepareAttName(budgetSubAwards));
                    budgetList[0] = rrBudget;
                    break;
                case 2:
                    rrSubawardBudget.setATT2(prepareAttName(budgetSubAwards));
                    budgetList[1] = rrBudget;
                    break;
                case 3:
                    rrSubawardBudget.setATT3(prepareAttName(budgetSubAwards));
                    budgetList[2] = rrBudget;
                    break;
                case 4:
                    rrSubawardBudget.setATT4(prepareAttName(budgetSubAwards));
                    budgetList[3] = rrBudget;
                    break;
                case 5:
                    rrSubawardBudget.setATT5(prepareAttName(budgetSubAwards));
                    budgetList[4] = rrBudget;
                    break;
                case 6:
                    rrSubawardBudget.setATT6(prepareAttName(budgetSubAwards));
                    budgetList[5] = rrBudget;
                    break;
                case 7:
                    rrSubawardBudget.setATT7(prepareAttName(budgetSubAwards));
                    budgetList[6] = rrBudget;
                    break;
                case 8:
                    rrSubawardBudget.setATT8(prepareAttName(budgetSubAwards));
                    budgetList[7] = rrBudget;
                    break;
                case 9:
                    rrSubawardBudget.setATT9(prepareAttName(budgetSubAwards));
                    budgetList[8] = rrBudget;
                    break;
                case 10:
                    rrSubawardBudget.setATT10(prepareAttName(budgetSubAwards));
                    budgetList[9] = rrBudget;
                    break;
            }
            addSubAwdAttachments(budgetSubAwards);
            attCount++;
            
        }
        budgetAttachments.setRRBudget13Array(budgetList);
        rrSubawardBudget.setBudgetAttachments(budgetAttachments);
        rrSubawardBudgetDocument.setRRSubawardBudget13(rrSubawardBudget);
        return rrSubawardBudgetDocument;
    }

    /**
     * 
     * This method is used to get RRBudget from BudgetSubAwards
     * 
     * @param budgetSubAwards(BudgetSubAwards) budget sub awards entry.
     * @return RRBudget corresponding to the BudgetSubAwards object.
     */
    private RRBudget13Document getRRBudget10(BudgetSubAwards budgetSubAwards) {
        RRBudget13Document rrBudget = RRBudget13Document.Factory.newInstance();
        String subAwdXML = budgetSubAwards.getSubAwardXmlFileData();
        Document subAwdFormsDoc;
        try {
            subAwdFormsDoc = stringToDom(subAwdXML);
        }catch (S2SException e1) {
            return rrBudget;
        }
        Element subAwdFormsElement = subAwdFormsDoc.getDocumentElement();
        NodeList subAwdNodeList = subAwdFormsElement.getElementsByTagNameNS(RR_BUDGET1_3_NAMESPACE_URI, RR_BUDGET1_3_LOCAL_NAME);
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
            rrBudget = (RRBudget13Document) XmlObject.Factory.parse(bgtIS);
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
     * This method creates {@link XmlObject} of type {@link RRSubawardBudgetDocument} by populating data from the given
     * {@link ProposalDevelopmentDocument}
     * 
     * @param proposalDevelopmentDocument for which the {@link XmlObject} needs to be created
     * @return {@link XmlObject} which is generated using the given {@link ProposalDevelopmentDocument}
     * @see org.kuali.kra.s2s.generator.S2SFormGenerator#getFormObject(ProposalDevelopmentDocument)
     */
    public XmlObject getFormObject(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        pdDoc=proposalDevelopmentDocument;
        return getRRSubawardBudgetDocument();
    }

}
