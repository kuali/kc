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

UPDATE KRMS_FUNC_T set DESC_TXT = 'Check if the submit (logged in) user is the PI of the proposal'
WHERE NM = 'isUserProposalPI'
/

UPDATE KRMS_TERM_SPEC_T set DESC_TXT = 'Check if the submit (logged in) user is the PI of the proposal'
WHERE NM = (select FUNC_ID from KRMS_FUNC_T where  NM='isUserProposalPI' and NMSPC_CD='KC-PD')
/

UPDATE KRMS_TERM_T set DESC_TXT = 'Check if the submit (logged in) user is the PI of the proposal'
WHERE TERM_SPEC_ID = (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and
NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isUserProposalPI' and NMSPC_CD='KC-PD'))
/

DELETE FROM KRMS_FUNC_PARM_T
WHERE NM = 'PrincipalId' AND FUNC_ID = (select FUNC_ID from KRMS_FUNC_T where  NM='isUserProposalPI' and NMSPC_CD='KC-PD')
AND SEQ_NO = 2
/

DELETE FROM KRMS_TERM_RSLVR_PARM_SPEC_T
WHERE TERM_RSLVR_ID = (select TERM_RSLVR_ID from KRMS_TERM_RSLVR_T where NM='Proposal PI Resolver' and NMSPC_CD='KC-PD') AND
NM = 'PrincipalId'
/

DELIMITER ;
