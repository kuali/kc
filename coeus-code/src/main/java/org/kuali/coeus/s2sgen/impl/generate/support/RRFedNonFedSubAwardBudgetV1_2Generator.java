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
package org.kuali.coeus.s2sgen.impl.generate.support;

import gov.grants.apply.forms.rrFedNonFedBudgetV11.RRFedNonFedBudgetDocument;
import gov.grants.apply.forms.rrFedNonFedBudgetV11.RRFedNonFedBudgetDocument.RRFedNonFedBudget;
import gov.grants.apply.forms.rrFedNonFedSubawardBudgetV12.RRFedNonFedSubawardBudgetDocument;
import gov.grants.apply.forms.rrFedNonFedSubawardBudgetV12.RRFedNonFedSubawardBudgetDocument.RRFedNonFedSubawardBudget;
import gov.grants.apply.forms.rrFedNonFedSubawardBudgetV12.RRFedNonFedSubawardBudgetDocument.RRFedNonFedSubawardBudget.BudgetAttachments;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.propdev.api.budget.subaward.BudgetSubAwardsContract;
import org.kuali.coeus.s2sgen.api.core.S2SException;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.s2sgen.impl.generate.FormGenerator;
import org.kuali.coeus.s2sgen.impl.generate.FormVersion;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
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
@FormGenerator("RRFedNonFedSubAwardBudgetV1_2Generator")
public class RRFedNonFedSubAwardBudgetV1_2Generator extends S2SAdobeFormAttachmentBaseGenerator {

    @Value("http://apply.grants.gov/forms/RR_FedNonFed_SubawardBudget-V1.2")
    private String namespace;

    @Value("RR_FedNonFed_SubawardBudget-V1.2")
    private String formName;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/RR_FedNonFedSubawardBudget-V1.2.fo.xsl")
    private Resource stylesheet;

    @Value("gov.grants.apply.forms.rrFedNonFedSubawardBudgetV12")
    private String packageName;

    @Value("172")
    private int sortIndex;

    private static final String RR_FED_NON_FED_BUDGET_11_NAMESPACE_URI = "http://apply.grants.gov/forms/RR_FedNonFedBudget-V1.1";
    private static final String LOCAL_FED_NON_FED_NAME = "RR_FedNonFedBudget";

    /**
     * 
     * This method is to get SubAward Budget details
     * 
     * @return rrSubawardBudgetDocument {@link XmlObject} of type RRFedNonFedSubawardBudgetDocument.
     */
    private RRFedNonFedSubawardBudgetDocument getRRFedNonFedSubawardBudgetDocument() {

        RRFedNonFedSubawardBudgetDocument rrSubawardBudgetDocument = RRFedNonFedSubawardBudgetDocument.Factory.newInstance();
        RRFedNonFedSubawardBudget rrSubawardBudget = RRFedNonFedSubawardBudget.Factory.newInstance();
        BudgetAttachments budgetAttachments = BudgetAttachments.Factory.newInstance();
        List<BudgetSubAwardsContract> budgetSubAwardsList = getBudgetSubAwards(pdDoc,RR_FED_NON_FED_BUDGET_11_NAMESPACE_URI,false);
        RRFedNonFedBudget[] budgetList = new RRFedNonFedBudget[budgetSubAwardsList.size()];
        rrSubawardBudget.setFormVersion(FormVersion.v1_2.getVersion());

        int attCount = 1;
        for (BudgetSubAwardsContract budgetSubAwards : budgetSubAwardsList) {
            RRFedNonFedBudget rrBudget = getRRFedNonFedBudget(budgetSubAwards).getRRFedNonFedBudget();
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
        budgetAttachments.setRRFedNonFedBudgetArray(budgetList);
        rrSubawardBudget.setBudgetAttachments(budgetAttachments);
        rrSubawardBudgetDocument.setRRFedNonFedSubawardBudget(rrSubawardBudget);
        return rrSubawardBudgetDocument;
    }

    /**
     * 
     * This method is used to get RRFedNonFedBudget from BudgetSubAwards
     * 
     * @param budgetSubAwards(BudgetSubAwards) budget sub awards entry.
     * @return RRFedNonFedBudget corresponding to the BudgetSubAwards object.
     */
    private RRFedNonFedBudgetDocument getRRFedNonFedBudget(BudgetSubAwardsContract budgetSubAwards) {
        RRFedNonFedBudgetDocument rrBudget = RRFedNonFedBudgetDocument.Factory.newInstance();
        String subAwdXML = budgetSubAwards.getSubAwardXmlFileData();
        Document subAwdFormsDoc;
        try {
            subAwdFormsDoc = stringToDom(subAwdXML);
        }
        catch (S2SException e1) {
            return rrBudget;
        }
        Element subAwdFormsElement = subAwdFormsDoc.getDocumentElement();
        NodeList subAwdNodeList = subAwdFormsElement.getElementsByTagNameNS(RR_FED_NON_FED_BUDGET_11_NAMESPACE_URI, LOCAL_FED_NON_FED_NAME);
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
            rrBudget = (RRFedNonFedBudgetDocument) XmlObject.Factory.parse(bgtIS);
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
     * This method creates {@link XmlObject} of type {@link RRFedNonFedSubawardBudgetDocument} by populating data from the given
     * {@link ProposalDevelopmentDocumentContract}
     * 
     * @param proposalDevelopmentDocument for which the {@link XmlObject} needs to be created
     * @return {@link XmlObject} which is generated using the given {@link ProposalDevelopmentDocumentContract}
     */
    public XmlObject getFormObject(ProposalDevelopmentDocumentContract proposalDevelopmentDocument) {
        pdDoc=proposalDevelopmentDocument;
        return getRRFedNonFedSubawardBudgetDocument();
    }

    @Override
    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    @Override
    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    @Override
    public Resource getStylesheet() {
        return stylesheet;
    }

    public void setStylesheet(Resource stylesheet) {
        this.stylesheet = stylesheet;
    }

    @Override
    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    @Override
    public int getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(int sortIndex) {
        this.sortIndex = sortIndex;
    }
}
