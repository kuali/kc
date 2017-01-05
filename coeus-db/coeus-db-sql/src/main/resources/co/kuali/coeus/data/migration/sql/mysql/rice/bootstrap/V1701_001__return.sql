--
-- Kuali Coeus, a comprehensive research administration system for higher education.
--
-- Copyright 2005-2016 Kuali, Inc.
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

update KRCR_PARM_T set parm_nm = 'awardBudgetStatusReturned' where appl_id = 'KC' and nmspc_cd = 'KC-AB' and cmpnt_cd = 'Document' and parm_nm = 'awardBudgetStatusRejected';
update KRCR_PARM_T set parm_nm = 'returnNarrativeTypeCode' where appl_id = 'KC' and nmspc_cd = 'KC-PD' and cmpnt_cd = 'Document' and parm_nm = 'rejectNarrativeTypeCode';

update KRIM_PERM_T set nm = 'Return ProposalDevelopmentDocument', desc_txt = 'Return Proposal Development Document' where nmspc_cd = 'KC-PD' and nm = 'Reject ProposalDevelopmentDocument';

update KRMS_FUNC_T set desc_txt = 'Check if the proposal has been approved or returned by OSP' where nmspc_cd = 'KC-PD' and nm = 'routedToOSPRule';

update KRMS_TERM_SPEC_T set desc_txt = 'Check if the proposal has been approved or returned by OSP' where nmspc_cd = 'KC-PD' and nm =
  (select FUNC_ID from KRMS_FUNC_T where NM = 'routedToOSPRule' and NMSPC_CD = 'KC-PD');

update KRMS_TERM_T set desc_txt = 'Check if the proposal has been approved or returned by OSP' where term_spec_id =
  (select term_spec_id from KRMS_TERM_SPEC_T where nmspc_cd = 'KC-PD' and nm =
    (select FUNC_ID from KRMS_FUNC_T where NM = 'routedToOSPRule' and NMSPC_CD = 'KC-PD'));

update KRMS_TERM_RSLVR_T set nm = 'Proposal approved or returned Resolver' where nmspc_cd = 'KC-PD' and nm = 'Proposal approved or rejected Resolver';

