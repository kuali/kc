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
package org.kuali.kra.iacuc.service.impl;

import org.kuali.coeus.common.committee.impl.bo.CommitteeBase;
import org.kuali.coeus.common.committee.impl.bo.CommitteeMembershipBase;
import org.kuali.coeus.common.committee.impl.bo.CommitteeMembershipExpertiseBase;
import org.kuali.coeus.common.committee.impl.bo.CommitteeResearchAreaBase;
import org.kuali.kra.bo.ResearchAreaBase;
import org.kuali.kra.iacuc.IacucResearchArea;
import org.kuali.kra.iacuc.committee.bo.IacucCommittee;
import org.kuali.kra.iacuc.committee.bo.IacucCommitteeMembership;
import org.kuali.kra.iacuc.committee.bo.IacucCommitteeMembershipExpertise;
import org.kuali.kra.iacuc.committee.bo.IacucCommitteeResearchArea;
import org.kuali.kra.iacuc.protocol.research.IacucProtocolResearchArea;
import org.kuali.kra.iacuc.service.IacucResearchAreasService;
import org.kuali.kra.protocol.protocol.research.ProtocolResearchAreaBase;
import org.kuali.kra.service.impl.ResearchAreasServiceBaseImpl;

public class IacucResearchAreasServiceImpl extends ResearchAreasServiceBaseImpl implements IacucResearchAreasService {

    @Override
    protected Class<? extends ProtocolResearchAreaBase> getProtocolResearchAreaBOClassHook() {
        return IacucProtocolResearchArea.class;
    }

    @Override
    protected Class<? extends ResearchAreaBase> getResearchAreaBOClassHook() {
        return IacucResearchArea.class;
    }

    @Override
    protected ResearchAreaBase getNewResearchAreaInstanceHook(String researchAreaCode, String parentResearchAreaCode,
            String description, boolean active) {
        return new IacucResearchArea(researchAreaCode, parentResearchAreaCode, 
                description, active);
    }

    @Override
    protected Class<? extends CommitteeMembershipExpertiseBase> getCommitteeMembershipExpertiseClassHook() {
        return IacucCommitteeMembershipExpertise.class;
    }

    @Override
    protected Class<? extends CommitteeMembershipBase> getCommitteeMembershipBOClassHook() {
        return IacucCommitteeMembership.class;
    }

    @Override
    protected Class<? extends CommitteeBase> getCommitteeBOClassHook() {
        return IacucCommittee.class;
    }

    @Override
    protected Class<? extends CommitteeResearchAreaBase> getCommitteeResearchAreaBOClassHook() {
        return IacucCommitteeResearchArea.class;
    }

}
