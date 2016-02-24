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
package org.kuali.coeus.common.framework.medusa;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.negotiations.bo.Negotiation;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.kra.subaward.bo.SubAward;

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
