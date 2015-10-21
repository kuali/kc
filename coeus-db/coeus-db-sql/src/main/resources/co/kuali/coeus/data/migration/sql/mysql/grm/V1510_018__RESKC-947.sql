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

update PROPOSAL_TYPE set PROPOSAL_TYPE_CODE = '500' where PROPOSAL_TYPE_CODE = '10' and DESCRIPTION = 'New - Change/Corrected' and UPDATE_USER = 'admin';
update PROPOSAL_TYPE set PROPOSAL_TYPE_CODE = '501' where PROPOSAL_TYPE_CODE = '14' and DESCRIPTION = 'Pre Proposal' and UPDATE_USER = 'admin';
update PROPOSAL_TYPE set PROPOSAL_TYPE_CODE = '502' where PROPOSAL_TYPE_CODE = '15' and DESCRIPTION = 'Supplement-Changed/Corrected' and UPDATE_USER = 'admin';
update PROPOSAL_TYPE set PROPOSAL_TYPE_CODE = '503' where PROPOSAL_TYPE_CODE = '16' and DESCRIPTION = 'Resubmission-Changed/Corrected' and UPDATE_USER = 'admin';
update PROPOSAL_TYPE set PROPOSAL_TYPE_CODE = '504' where PROPOSAL_TYPE_CODE = '17' and DESCRIPTION = 'Budget-SOW Update' and UPDATE_USER = 'admin';
update PROPOSAL_TYPE set PROPOSAL_TYPE_CODE = '505' where PROPOSAL_TYPE_CODE = '18' and DESCRIPTION = 'Renewal-Changed/Corrected' and UPDATE_USER = 'admin';

update KRCR_PARM_T set VAL='500' where NMSPC_CD = 'KC-PD' and PARM_NM = 'PROPOSAL_TYPE_CODE_NEW_CHANGE_CORRECTED' and VAL = '10';
update KRCR_PARM_T set VAL='501' where NMSPC_CD = 'KC-PD' and PARM_NM = 'PROPOSAL_TYPE_CODE_PRE_PROPOSAL' and VAL = '14';
update KRCR_PARM_T set VAL='502' where NMSPC_CD = 'KC-PD' and PARM_NM = 'PROPOSAL_TYPE_CODE_SUPPLEMENT_CHANGE_CORRECTED' and VAL = '15';
update KRCR_PARM_T set VAL='503' where NMSPC_CD = 'KC-PD' and PARM_NM = 'PROPOSAL_TYPE_CODE_RESUBMISSION_CHANGE_CORRECTED' and VAL = '16';
update KRCR_PARM_T set VAL='504' where NMSPC_CD = 'KC-PD' and PARM_NM = 'PROPOSAL_TYPE_CODE_BUDGET_SOW_UPDATE' and VAL = '17';
update KRCR_PARM_T set VAL='505' where NMSPC_CD = 'KC-PD' and PARM_NM = 'PROPOSAL_TYPE_CODE_RENEWAL_CHANGE_CORRECTED' and VAL = '18';
update KRCR_PARM_T set VAL='1,500,501' where NMSPC_CD = 'KC-S2S' and PARM_NM = 'PROPOSAL_TYPE_CODE_NEW' and VAL = '1,10,14';
update KRCR_PARM_T set VAL='4,502' where NMSPC_CD = 'KC-S2S' and PARM_NM = 'PROPOSAL_TYPE_CODE_REVISION' and VAL = '4,15';
update KRCR_PARM_T set VAL='5,505' where NMSPC_CD = 'KC-S2S' and PARM_NM = 'PROPOSAL_TYPE_CODE_RENEWAL' and VAL = '5,18';
update KRCR_PARM_T set VAL='6,503' where NMSPC_CD = 'KC-S2S' and PARM_NM = 'PROPOSAL_TYPE_CODE_RESUBMISSION' and VAL = '6,16';
