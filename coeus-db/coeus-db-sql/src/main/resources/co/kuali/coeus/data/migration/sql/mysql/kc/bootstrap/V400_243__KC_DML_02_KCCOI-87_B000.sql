--
-- Kuali Coeus, a comprehensive research administration system for higher education.
-- 
-- Copyright 2005-2015 Kuali, Inc.
-- 
-- This program is free software: you can redistribute it and/or modify
-- it under the terms of the GNU Affero General Public License as
-- published by the Free Software Foundation, either version 3 of the
-- License, or (at your option) any later version.
-- 
-- This program is distributed in the hope that it will be useful,
-- but WITHOUT ANY WARRANTY; without even the implied warranty of
-- MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
-- GNU Affero General Public License for more details.
-- 
-- You should have received a copy of the GNU Affero General Public License
-- along with this program.  If not, see <http://www.gnu.org/licenses/>.
--

DELIMITER /
UPDATE COI_DISCLOSURE_EVENT_TYPE SET ACTIVE_FLAG = 'Y'
/
UPDATE COI_DISCLOSURE_EVENT_TYPE 
    SET USE_LNG_TXT_FLD_1 = 'Y', 
        REQ_LNG_TXT_FLD_1 = 'Y', 
        LNG_TXT_FLD_1_LABEL = 'Sponsor',
        USE_SHRT_TXT_FLD_1 = 'Y',
        REQ_SHRT_TXT_FLD_1 = 'Y',
        SHRT_TXT_FLD_1_LABEL = 'Project Role',
        USE_NMBR_FLD_1 = 'Y',
        REQ_NMBR_FLD_1 = 'Y',
        NMBR_FLD_1_LABEL = 'Project Funding Amount',
        USE_DATE_FLD_1 = 'Y',
        REQ_DATE_FLD_1 = 'Y',
        DATE_FLD_1_LABEL = 'Project Start Date',
        USE_DATE_FLD_2 = 'Y',
        REQ_DATE_FLD_2 = 'Y',
        DATE_FLD_2_LABEL = 'Project End Date',
        USE_SLCT_BOX_1 = 'Y',
        REQ_SLCT_BOX_1 = 'Y',
        SLCT_BOX_1_LABEL = 'Project Type',
        SLCT_BOX_1_VAL_FNDR = 'org.kuali.kra.coi.lookup.keyvalue.CoiDisclosureProjectsProjectTypeValuesFinder',
        PROJECT_ID_LABEL = 'Project Id',
        PROJECT_TITLE_LABEL = 'Project Title'
    WHERE DESCRIPTION = 'Manual Proposal'
/
UPDATE COI_DISCLOSURE_EVENT_TYPE 
    SET PROJECT_ID_LABEL = 'Award Number',
        PROJECT_TITLE_LABEL = 'Award Title',
        USE_DATE_FLD_1 = 'Y',
        REQ_DATE_FLD_1 = 'Y',
        DATE_FLD_1_LABEL = 'Award Date'
    WHERE DESCRIPTION = 'Manual Award'
/
UPDATE COI_DISCLOSURE_EVENT_TYPE 
    SET PROJECT_ID_LABEL = 'Protocol Number',
        PROJECT_TITLE_LABEL = 'Protocol Name',
        USE_SLCT_BOX_1 = 'Y',
        REQ_SLCT_BOX_1 = 'Y',
        SLCT_BOX_1_LABEL = 'Protocol Type',
        SLCT_BOX_1_VAL_FNDR = 'org.kuali.kra.irb.protocol.ProtocolTypeValuesFinder'            
    WHERE DESCRIPTION = 'Manual IRB Protocol'
/
DELIMITER ;
