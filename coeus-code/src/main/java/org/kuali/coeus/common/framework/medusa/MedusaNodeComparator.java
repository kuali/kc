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
package org.kuali.coeus.common.framework.medusa;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.negotiations.bo.Negotiation;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.kra.subaward.bo.SubAward;
import org.kuali.rice.krad.bo.BusinessObject;

import java.util.Comparator;


public class MedusaNodeComparator implements Comparator<MedusaNode> {

    public int compare(MedusaNode m1, MedusaNode m2) {
        return getNodeValue(m1).compareTo(getNodeValue(m2));
    }   

    private String getNodeValue(MedusaNode m){             
        String nodeType = m.getType();  
        Object mBo = m.getBo();
        if(!StringUtils.isNotBlank(nodeType) || mBo == null){
            return "medusa: unknown medusa node type";
        } else {
            if(StringUtils.equals(nodeType, "award")){
                return nodeType + ((Award)mBo).getAwardNumber();
            }
            else if(StringUtils.equals(nodeType, "IP"))
                return nodeType + ((InstitutionalProposal)mBo).getProposalNumber();
            else if(StringUtils.equals(nodeType, "DP")) 
                return nodeType + ((DevelopmentProposal)mBo).getProposalNumber();    
            else if (StringUtils.equals(nodeType, "neg")) {
                return nodeType + ((Negotiation)mBo).getNegotiationId();
            } else if (StringUtils.equals(nodeType, "subaward")) {
                return nodeType + ((SubAward)mBo).getSubAwardId();
            } else
                return "medusa: unsupported medusa node type";
        }
    }
}
