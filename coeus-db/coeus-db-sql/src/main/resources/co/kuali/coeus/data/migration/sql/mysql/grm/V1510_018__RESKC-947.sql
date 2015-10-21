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

create procedure updateProposalType(in curProposalType varchar(3), in newProposalType varchar(3), in curDesc varchar(200))
	begin
		declare dbProposalType varchar(3) default null;

		select proposal_type_code into dbProposalType from proposal_type where proposal_type_code = curProposalType and description = curDesc and update_user = 'admin';
		if dbProposalType is not null then
			insert into proposal_type values ('999', 'TEMP', NOW(), 'admin', 1, UUID());
			update proposal_log set proposal_type_code = '999' where proposal_type_code = dbProposalType;
			update proposal set proposal_type_code = '999' where proposal_type_code = dbProposalType;
			update eps_proposal set proposal_type_code = '999' where proposal_type_code = dbProposalType;
			update proposal_type set proposal_type_code = newProposalType where proposal_type_code = dbProposalType;
			update proposal_log set proposal_type_code = newProposalType where proposal_type_code = '999';
			update proposal set proposal_type_code = newProposalType where proposal_type_code = '999';
			update eps_proposal set proposal_type_code = newProposalType where proposal_type_code = '999';
			delete from proposal_type where proposal_type_code = '999';
		end if;
	end /
	
DELIMITER ;
			
call updateProposalType('10', '500', 'New - Change/Corrected');
call updateProposalType('14', '501', 'Pre Proposal');
call updateProposalType('15', '502', 'Supplement-Changed/Corrected');
call updateProposalType('16', '503', 'Resubmission-Changed/Corrected');
call updateProposalType('17', '504', 'Budget-SOW Update');
call updateProposalType('18', '505', 'Renewal-Changed/Corrected');

drop procedure updateProposalType;

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
