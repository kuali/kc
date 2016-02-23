/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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

import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.negotiations.bo.Negotiation;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.subaward.bo.SubAward;
import org.kuali.rice.core.api.util.tree.Node;

import java.io.Serializable;
import java.util.List;


public class MedusaNode extends Node<Object, String> implements Serializable {
    private static final long serialVersionUID = 6899695450845010658L;

    private Object extraInfo;



    private String coeusModuleCode;

    public Object getBo() {
        return getData();
    }
    public void setBo(Object bo) {
    	setData(bo);
    }
    public String getType() {
        return getNodeType();
    }
    public void setType(String type) {
    	setNodeType(type);
    }
    public List<? super MedusaNode> getChildNodes() {
    	return getChildren();
    }
    public void setChildNodes(List<? extends Node<Object, String>> childNodes) {
    	setChildren((List<Node<Object, String>>) childNodes);
    }
    public Object getExtraInfo() {
        return extraInfo;
    }
    public void setExtraInfo(Object extraInfo) {
        this.extraInfo = extraInfo;
    }
    public String getCoeusModuleCode() {
        return coeusModuleCode;
    }

    public void setCoeusModuleCode(String coeusModuleCode) {
        this.coeusModuleCode = coeusModuleCode;
    }
    public void addChildNode(MedusaNode node) {
    	getChildren().add(node);
    }
    public String getNodeLabel() {
    	if (getData() instanceof DevelopmentProposal) {
    		return "Development Proposal " + ((DevelopmentProposal) getData()).getProposalNumber();
    	}
        else if (getData() instanceof InstitutionalProposal) {
    		return "Institutional Proposal " + ((InstitutionalProposal) getData()).getProposalNumber();
    	}
        else if (getData() instanceof ProtocolBase) {
            return "Protocol " + ((ProtocolBase) getData()).getProtocolNumber();
        }
        else if (getData() instanceof Award) {
            return "Award " + ((Award) getData()).getAwardNumber();
        }
        else if (getData() instanceof SubAward) {
            return "Subaward " + ((SubAward) getData()).getSubAwardId();
        }
        else if (getData() instanceof Negotiation) {
            return "Negotiation " + ((Negotiation) getData()).getNegotiationId();
        }
        else {
    		return "---";
    	}
    }


}     
