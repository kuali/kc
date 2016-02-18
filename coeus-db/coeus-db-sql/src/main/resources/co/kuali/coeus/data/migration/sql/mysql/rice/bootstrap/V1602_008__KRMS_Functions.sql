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

-- Institutional Proposal
insert into krms_typ_t (typ_id, nm, nmspc_cd, srvc_nm, actv, ver_nbr)
values ('KC10002', 'Institutional Proposal Java Function Term Service', 'KC-IP', 'institutionalProposalJavaFunctionKrmsTermService', 'Y', 1);
insert into krms_func_t (func_id, nmspc_cd, nm, desc_txt, rtrn_typ, typ_id, actv, ver_nbr)
values ('KC10016', 'KC-IP', 'doSponsorAndPrimeSponsorMatch', 'Check if Sponsor and Prime Sponsor are the same', 'java.lang.Boolean', 'KC10002', 'Y', 1);
insert into krms_func_parm_t (func_parm_id, nm, desc_txt, typ, func_id, seq_no)
values ('KC10026', 'InstitutionalProposal', 'Institutional Proposal BO', 'org.kuali.coeus.common.framework.sponsor.Sponsorable', 'KC10016', 1);
insert into krms_term_spec_t (term_spec_id, nm, typ, actv, ver_nbr, desc_txt, nmspc_cd)
values ('KC2093', 'KC10016', 'java.lang.Boolean', 'Y', 1, 'Check if Sponsor and Prime Sponsor are the same', 'KC-IP');
insert into krms_term_t (term_id, term_spec_id, ver_nbr, desc_txt)
values ('KC10014', 'KC2093', '1', 'Check if Sponsor and Prime Sponsor are the same');
insert into krms_term_rslvr_t (term_rslvr_id, nmspc_cd, nm, typ_id, output_term_spec_id, actv, ver_nbr)
values ('KC2089', 'KC-IP', 'Sponsor and Prime Sponsor Match Resolver', 'KC1001', 'KC2093', 'Y', 1);
insert into krms_cntxt_vld_term_spec_t (cntxt_term_spec_prereq_id, cntxt_id, term_spec_id, prereq)
values ('KC1033', 'KC-IP-CONTEXT', 'KC2093', 'Y');
insert into krms_term_spec_ctgry_t (term_spec_id, ctgry_id)
values ('KC2093', 'KC1016');

-- hasSpecialReviewOfType
insert into krms_func_t (func_id, nmspc_cd, nm, desc_txt, rtrn_typ, typ_id, actv, ver_nbr)
values ('KC10017', 'KC-IP', 'hasSpecialReviewOfType', 'Check if IP has a special review with the specified type', 'java.lang.Boolean', 'KC10002', 'Y', 1);
insert into krms_func_parm_t (func_parm_id, nm, desc_txt, typ, func_id, seq_no)
values ('KC10027', 'InstitutionalProposal', 'Institutional Proposal BO', 'org.kuali.kra.institutionalproposal.home.InstitutionalProposal',  'KC10017', 1);
insert into krms_func_parm_t (func_parm_id, nm, desc_txt, typ, func_id, seq_no)
values ('KC10028', 'Special Review Type', 'Special Review Type Code or Description', 'java.lang.String', 'KC10017', 2);
insert into krms_term_spec_t (term_spec_id, nm, typ, actv, ver_nbr, desc_txt, nmspc_cd)
values ('KC2094', 'KC10017', 'java.lang.Boolean', 'Y', 1, 'Check if IP has a special review with the specified type', 'KC-IP');
insert into krms_term_rslvr_t (term_rslvr_id, nmspc_cd, nm, typ_id, output_term_spec_id, actv, ver_nbr)
values ('KC2090', 'KC-IP', 'IP Special Review Type Resolver', 'KC1001', 'KC2094', 'Y', 1);
insert into krms_term_rslvr_parm_spec_t (term_rslvr_parm_spec_id, term_rslvr_id, nm, ver_nbr)
values ('KC2073', 'KC2090', 'Special Review Type', 1);
insert into krms_cntxt_vld_term_spec_t (cntxt_term_spec_prereq_id, cntxt_id, term_spec_id, prereq)
values ('KC1034', 'KC-IP-CONTEXT', 'KC2094', 'Y');
insert into krms_term_spec_ctgry_t (term_spec_id, ctgry_id)
values ('KC2094', 'KC1016');

-- isCurrentFiscalMonth
insert into krms_func_t (func_id, nmspc_cd, nm, desc_txt, rtrn_typ, typ_id, actv, ver_nbr)
values ('KC10018', 'KC-IP', 'isCurrentFiscalMonth', 'Check if IP has a fiscal month the same as the current fiscal month', 'java.lang.Boolean', 'KC10002', 'Y', 1);
insert into krms_func_parm_t (func_parm_id, nm, desc_txt, typ, func_id, seq_no)
values ('KC10029', 'InstitutionalProposal', 'Institutional Proposal BO', 'org.kuali.kra.institutionalproposal.home.InstitutionalProposal', 'KC10018', 1);
insert into krms_term_spec_t (term_spec_id, nm, typ, actv, ver_nbr, desc_txt, nmspc_cd)
values ('KC2095', 'KC10018', 'java.lang.Boolean', 'Y', 1, 'Check if IP has a fiscal month the same as the current fiscal month', 'KC-IP');
insert into krms_term_t (term_id, term_spec_id, ver_nbr, desc_txt)
values ('KC10015', 'KC2095', '1', 'Check if IP has a fiscal month the same as the current fiscal month');
insert into krms_term_rslvr_t (term_rslvr_id, nmspc_cd, nm, typ_id, output_term_spec_id, actv, ver_nbr)
values ('KC2091', 'KC-IP', 'IP Current Fiscal Month Resolver', 'KC1001', 'KC2095', 'Y', 1);
insert into krms_cntxt_vld_term_spec_t (cntxt_term_spec_prereq_id, cntxt_id, term_spec_id, prereq)
values ('KC1035', 'KC-IP-CONTEXT', 'KC2095', 'Y');
insert into krms_term_spec_ctgry_t (term_spec_id, ctgry_id)
values ('KC2095', 'KC1016');

-- Award
insert into krms_func_t (func_id, nmspc_cd, nm, desc_txt, rtrn_typ, typ_id, actv, ver_nbr)
values ('KC10019', 'KC-AWARD', 'doSponsorAndPrimeSponsorMatch', 'Check if Sponsor and Prime Sponsor are the same', 'java.lang.Boolean', 'KC10001', 'Y', 1);
insert into krms_func_parm_t (func_parm_id, nm, desc_txt, typ, func_id, seq_no)
values ('KC10030', 'award', 'Award BO', 'org.kuali.coeus.common.framework.sponsor.Sponsorable', 'KC10019', 1);

insert into krms_term_spec_t (term_spec_id, nm, typ, actv, ver_nbr, desc_txt, nmspc_cd)
values ('KC2096', 'KC10019', 'java.lang.Boolean', 'Y', 1, 'Check if Sponsor and Prime Sponsor are the same', 'KC-AWARD');
insert into krms_term_t (term_id, term_spec_id, ver_nbr, desc_txt)
values ('KC10016', 'KC2096', '1', 'Check if Sponsor and Prime Sponsor are the same');

insert into krms_term_rslvr_t (term_rslvr_id, nmspc_cd, nm, typ_id, output_term_spec_id, actv, ver_nbr)
values ('KC2092', 'KC-AWARD', 'Sponsor and Prime Sponsor Match Resolver', 'KC1001', 'KC2096', 'Y', 1);

insert into krms_cntxt_vld_term_spec_t (cntxt_term_spec_prereq_id, cntxt_id, term_spec_id, prereq)
values ('KC1036', 'KC-AWARD-CONTEXT', 'KC2096', 'Y');
insert into krms_term_spec_ctgry_t (term_spec_id, ctgry_id)
values ('KC2096', 'KC1010');

-- check prop value for previous version
insert into krms_func_t (func_id, nmspc_cd, nm, desc_txt, rtrn_typ, typ_id, actv, ver_nbr)
values ('KC10020', 'KC-AWARD', 'checkPropertyValueForAnyPreviousVersion', 'Check if Property Value matches for any previous Award version', 'java.lang.Boolean', 'KC10001', 'Y', 1);
insert into krms_func_parm_t (func_parm_id, nm, desc_txt, typ, func_id, seq_no)
values ('KC10031', 'award', 'Award BO', 'org.kuali.coeus.common.framework.version.sequence.owner.SequenceOwner', 'KC10020', 1);
insert into krms_func_parm_t (func_parm_id, nm, desc_txt, typ, func_id, seq_no)
values ('KC10032', 'Property Name', 'Property to compare', 'java.lang.String', 'KC10020', 2);
insert into krms_func_parm_t (func_parm_id, nm, desc_txt, typ, func_id, seq_no)
values ('KC10033', 'Comparison Value', 'Value to compare', 'java.lang.String', 'KC10020', 3);
insert into krms_term_spec_t (term_spec_id, nm, typ, actv, ver_nbr, desc_txt, nmspc_cd)
values ('KC2097', 'KC10020', 'java.lang.Boolean', 'Y', 1, 'Check Previous Version has Property matching Value', 'KC-AWARD');
insert into krms_term_rslvr_t (term_rslvr_id, nmspc_cd, nm, typ_id, output_term_spec_id, actv, ver_nbr)
values ('KC2093', 'KC-AWARD', 'Previous Version Property Value Comparison Resolver', 'KC1001', 'KC2097', 'Y', 1);
insert into krms_term_rslvr_parm_spec_t (term_rslvr_parm_spec_id, term_rslvr_id, nm, ver_nbr)
values ('KC2074', 'KC2093', 'Property Name', 1);
insert into krms_term_rslvr_parm_spec_t (term_rslvr_parm_spec_id, term_rslvr_id, nm, ver_nbr)
values ('KC2075', 'KC2093', 'Comparison Value', 1);
insert into krms_cntxt_vld_term_spec_t (cntxt_term_spec_prereq_id, cntxt_id, term_spec_id, prereq)
values ('KC1037', 'KC-AWARD-CONTEXT', 'KC2097', 'Y');
insert into krms_term_spec_ctgry_t (term_spec_id, ctgry_id)
values ('KC2097', 'KC1010');

-- hasPropertyChangedThisVersion
insert into krms_func_t (func_id, nmspc_cd, nm, desc_txt, rtrn_typ, typ_id, actv, ver_nbr)
values ('KC10021', 'KC-AWARD', 'hasPropertyChangedThisVersion', 'Check if Property Value has changed since the previous Award version', 'java.lang.Boolean', 'KC10001', 'Y', 1);
insert into krms_func_parm_t (func_parm_id, nm, desc_txt, typ, func_id, seq_no)
values ('KC10034', 'award', 'Award BO', 'org.kuali.coeus.common.framework.version.sequence.owner.SequenceOwner', 'KC10021', 1);
insert into krms_func_parm_t (func_parm_id, nm, desc_txt, typ, func_id, seq_no)
values ('KC10035', 'Property Name', 'Property to compare', 'java.lang.String', 'KC10021', 2);
insert into krms_term_spec_t (term_spec_id, nm, typ, actv, ver_nbr, desc_txt, nmspc_cd)
values ('KC2098', 'KC10021', 'java.lang.Boolean', 'Y', 1, 'Check if Property Value has changed since the previous Award version', 'KC-AWARD');

insert into krms_term_rslvr_t (term_rslvr_id, nmspc_cd, nm, typ_id, output_term_spec_id, actv, ver_nbr)
values ('KC2094', 'KC-AWARD', 'Has Property Changed Comparison Resolver', 'KC1001', 'KC2098', 'Y', 1);
insert into krms_term_rslvr_parm_spec_t (term_rslvr_parm_spec_id, term_rslvr_id, nm, ver_nbr)
values ('KC2076', 'KC2094', 'Property Name', 1);

insert into krms_cntxt_vld_term_spec_t (cntxt_term_spec_prereq_id, cntxt_id, term_spec_id, prereq)
values ('KC1038', 'KC-AWARD-CONTEXT', 'KC2098', 'Y');
insert into krms_term_spec_ctgry_t (term_spec_id, ctgry_id)
values ('KC2098', 'KC1010');

-- hasSpecialReviewOfType here
insert into krms_func_t (func_id, nmspc_cd, nm, desc_txt, rtrn_typ, typ_id, actv, ver_nbr)
values ('KC10022', 'KC-AWARD', 'hasSpecialReviewOfType', 'Check if Award has a special review with the specified type', 'java.lang.Boolean', 'KC10001', 'Y', 1);
insert into krms_func_parm_t (func_parm_id, nm, desc_txt, typ, func_id, seq_no)
values ('KC10036', 'award', 'Award BO', 'org.kuali.kra.award.home.Award', 'KC10022', 1);
insert into krms_func_parm_t (func_parm_id, nm, desc_txt, typ, func_id, seq_no)
values ('KC10037', 'Special Review Type', 'Special Review Type Code or Description', 'java.lang.String', 'KC10022', 2);

insert into krms_term_spec_t (term_spec_id, nm, typ, actv, ver_nbr, desc_txt, nmspc_cd)
values ('KC2099', 'KC10022', 'java.lang.Boolean', 'Y', 1, 'Check if Award has a special review with the specified type', 'KC-AWARD');

insert into krms_term_rslvr_t (term_rslvr_id, nmspc_cd, nm, typ_id, output_term_spec_id, actv, ver_nbr)
values ('KC2095', 'KC-AWARD', 'Award Historical Special Review Resolver', 'KC1001', 'KC2099', 'Y', 1);
insert into krms_term_rslvr_parm_spec_t (term_rslvr_parm_spec_id, term_rslvr_id, nm, ver_nbr)
values ('KC2077', 'KC2095', 'Special Review Type', 1);

insert into krms_cntxt_vld_term_spec_t (cntxt_term_spec_prereq_id, cntxt_id, term_spec_id, prereq)
values ('KC1039', 'KC-AWARD-CONTEXT', 'KC2099', 'Y');
insert into krms_term_spec_ctgry_t (term_spec_id, ctgry_id)
values ('KC2099', 'KC1010');

-- Proposal Development
insert into krms_func_t (func_id, nmspc_cd, nm, desc_txt, rtrn_typ, typ_id, actv, ver_nbr)
values ('KC10023', 'KC-PD', 'doSponsorAndPrimeSponsorMatch', 'Check if Sponsor and Prime Sponsor are the same', 'java.lang.Boolean', 'KC1006', 'Y', 1);
insert into krms_func_parm_t (func_parm_id, nm, desc_txt, typ, func_id, seq_no)
values ('KC10038', 'DevelopmentProposal', 'DevelopmentProposal', 'org.kuali.coeus.common.framework.sponsor.Sponsorable', 'KC10023', 1);

insert into krms_term_spec_t (term_spec_id, nm, typ, actv, ver_nbr, desc_txt, nmspc_cd)
values ('KC2100', 'KC10023', 'java.lang.Boolean', 'Y', 1, 'Check if Sponsor and Prime Sponsor are the same', 'KC-PD');
insert into krms_term_t (term_id, term_spec_id, ver_nbr, desc_txt)
values ('KC10017', 'KC2100', '1', 'Check if Sponsor and Prime Sponsor are the same');

insert into krms_term_rslvr_t (term_rslvr_id, nmspc_cd, nm, typ_id, output_term_spec_id, actv, ver_nbr)
values ('KC2096', 'KC-PD', 'Sponsor and Prime Sponsor Match Resolver', 'KC1001', 'KC2100', 'Y', 1);

insert into krms_cntxt_vld_term_spec_t (cntxt_term_spec_prereq_id, cntxt_id, term_spec_id, prereq)
values ('KC1040', 'KC-PD-CONTEXT', 'KC2100', 'Y');
insert into krms_term_spec_ctgry_t (term_spec_id, ctgry_id)
values ('KC2100', 'KC1001');

-- IRB hasProtocolContainsAmendRenewModule
insert into krms_func_t (func_id, nmspc_cd, nm, desc_txt, rtrn_typ, typ_id, actv, ver_nbr)
values ('KC10024', 'KC-PROTOCOL', 'hasProtocolContainsAmendRenewModule', 'Check if Protocol has passed-in Amendment Section', 'java.lang.Boolean', 'KC1007', 'Y', 1);
insert into krms_func_parm_t (func_parm_id, nm, desc_txt, typ, func_id, seq_no)
values ('KC10039', 'IrbProtocol', 'Irb Protocol BO', 'org.kuali.kra.protocol.ProtocolBase', 'KC10024', 1);
insert into krms_func_parm_t (func_parm_id, nm, desc_txt, typ, func_id, seq_no)
values ('KC10040', 'Amendment Section', 'Amendment Section Code or Description', 'java.lang.String', 'KC10024', 2);

insert into krms_term_spec_t (term_spec_id, nm, typ, actv, ver_nbr, desc_txt, nmspc_cd)
values ('KC2101', 'KC10024', 'java.lang.Boolean', 'Y', 1, 'Check Protocol has Amendment Section', 'KC-PROTOCOL');

insert into krms_term_rslvr_t (term_rslvr_id, nmspc_cd, nm, typ_id, output_term_spec_id, actv, ver_nbr)
values ('KC2097', 'KC-PROTOCOL', 'Protocol has Amendment Section Resolver', 'KC1001', 'KC2101', 'Y', 1);
insert into krms_term_rslvr_parm_spec_t (term_rslvr_parm_spec_id, term_rslvr_id, nm, ver_nbr)
values ('KC2078', 'KC2097', 'Amendment Section', 1);

insert into krms_cntxt_vld_term_spec_t (cntxt_term_spec_prereq_id, cntxt_id, term_spec_id, prereq)
values ('KC1041', 'KC-PROTOCOL-CONTEXT', 'KC2101', 'Y');
insert into krms_term_spec_ctgry_t (term_spec_id, ctgry_id)
values ('KC2101', 'KC1004');

-- getProtocolParticipantTypeCount
insert into krms_func_t (func_id, nmspc_cd, nm, desc_txt, rtrn_typ, typ_id, actv, ver_nbr)
values ('KC10025', 'KC-PROTOCOL', 'getProtocolParticipantTypeCount', 'How many Participants does a Protocol have of a passed-in Type', 'java.lang.Integer', 'KC1007', 'Y', 1);
insert into krms_func_parm_t (func_parm_id, nm, desc_txt, typ, func_id, seq_no)
values ('KC10041', 'IrbProtocol', 'Irb Protocol BO', 'org.kuali.kra.irb.Protocol', 'KC10025', 1);
insert into krms_func_parm_t (func_parm_id, nm, desc_txt, typ, func_id, seq_no)
values ('KC10042', 'Participant Type Code or Description', 'Participant Type Code or Description', 'java.lang.String', 'KC10025', 2);

insert into krms_term_spec_t (term_spec_id, nm, typ, actv, ver_nbr, desc_txt, nmspc_cd)
values ('KC2102', 'KC10025', 'java.lang.Integer', 'Y', 1, 'How many participants does Protocol have of type', 'KC-PROTOCOL');

insert into krms_term_rslvr_t (term_rslvr_id, nmspc_cd, nm, typ_id, output_term_spec_id, actv, ver_nbr)
values ('KC2098', 'KC-PROTOCOL', 'Protocol participant type count Resolver', 'KC1001', 'KC2102', 'Y', 1);
insert into krms_term_rslvr_parm_spec_t (term_rslvr_parm_spec_id, term_rslvr_id, nm, ver_nbr)
values ('KC2079', 'KC2098', 'Participant Type Code or Description', 1);

insert into krms_cntxt_vld_term_spec_t (cntxt_term_spec_prereq_id, cntxt_id, term_spec_id, prereq)
values ('KC1042', 'KC-PROTOCOL-CONTEXT', 'KC2102', 'Y');
insert into krms_term_spec_ctgry_t (term_spec_id, ctgry_id)
values ('KC2102', 'KC1004');

-- hasProtocolContainsSponsorType
insert into krms_func_t (func_id, nmspc_cd, nm, desc_txt, rtrn_typ, typ_id, actv, ver_nbr)
values ('KC10026', 'KC-PROTOCOL', 'hasProtocolContainsSponsorType', 'Check if Protocol has Funding Source with passed-in Sponsor Type', 'java.lang.Boolean', 'KC1007', 'Y', 1);
insert into krms_func_parm_t (func_parm_id, nm, desc_txt, typ, func_id, seq_no)
values ('KC10043', 'IrbProtocol', 'Irb Protocol BO', 'org.kuali.kra.protocol.ProtocolBase', 'KC10026', 1);
insert into krms_func_parm_t (func_parm_id, nm, desc_txt, typ, func_id, seq_no)
values ('KC10044', 'Sponsor Type Code or Description', 'Sponsor Type Code or Description', 'java.lang.String', 'KC10026', 2);

insert into krms_term_spec_t (term_spec_id, nm, typ, actv, ver_nbr, desc_txt, nmspc_cd)
values ('KC2103', 'KC10026', 'java.lang.Boolean', 'Y', 1, 'Check Protocol has Funding Source with Sponsor Type', 'KC-PROTOCOL');

insert into krms_term_rslvr_t (term_rslvr_id, nmspc_cd, nm, typ_id, output_term_spec_id, actv, ver_nbr)
values ('KC2099', 'KC-PROTOCOL', 'Protocol funding source sponsor type Resolver', 'KC1001', 'KC2103', 'Y', 1);
insert into krms_term_rslvr_parm_spec_t (term_rslvr_parm_spec_id, term_rslvr_id, nm, ver_nbr)
values ('KC2080', 'KC2099', 'Sponsor Type Code or Description', 1);

insert into krms_cntxt_vld_term_spec_t (cntxt_term_spec_prereq_id, cntxt_id, term_spec_id, prereq)
values ('KC1043', 'KC-PROTOCOL-CONTEXT', 'KC2103', 'Y');
insert into krms_term_spec_ctgry_t (term_spec_id, ctgry_id)
values ('KC2103', 'KC1004');

-- hasBaseProtocolHasLastApprovalDate
insert into krms_func_t (func_id, nmspc_cd, nm, desc_txt, rtrn_typ, typ_id, actv, ver_nbr)
values ('KC10027', 'KC-PROTOCOL', 'hasBaseProtocolHasLastApprovalDate', 'Check if Base Protocol has a Last Approval Date', 'java.lang.Boolean', 'KC1007', 'Y', 1);
insert into krms_func_parm_t (func_parm_id, nm, desc_txt, typ, func_id, seq_no)
values ('KC10045', 'IrbProtocol', 'Irb Protocol BO', 'org.kuali.kra.protocol.ProtocolBase', 'KC10027', 1);

insert into krms_term_spec_t (term_spec_id, nm, typ, actv, ver_nbr, desc_txt, nmspc_cd)
values ('KC2104', 'KC10027', 'java.lang.Boolean', 'Y', 1, 'Check if Base Protocol has Last Approval Date', 'KC-PROTOCOL');
insert into krms_term_t (term_id, term_spec_id, ver_nbr, desc_txt)
values ('KC10018', 'KC2104', 1, 'Check if Base Protocol has Last Approval Date');

insert into krms_term_rslvr_t (term_rslvr_id, nmspc_cd, nm, typ_id, output_term_spec_id, actv, ver_nbr)
values ('KC2100', 'KC-PROTOCOL', 'Base Protocol has Last Approval Date Resolver', 'KC1001', 'KC2104', 'Y', 1);

insert into krms_cntxt_vld_term_spec_t (cntxt_term_spec_prereq_id, cntxt_id, term_spec_id, prereq)
values ('KC1044', 'KC-PROTOCOL-CONTEXT', 'KC2104', 'Y');
insert into krms_term_spec_ctgry_t (term_spec_id, ctgry_id)
values ('KC2104', 'KC1004');

-- adding comment type code as param
update krms_term_rslvr_parm_spec_t set nm = 'awardComments' where term_rslvr_parm_spec_id = 'KC2072';
update krms_func_parm_t set nm='awardComments' where func_parm_id = 'KC10025';
update krms_term_spec_t set desc_txt = 'Check content of comments of type same as specified type' where term_spec_id = 'KC2092';

insert into krms_func_parm_t (func_parm_id, nm, desc_txt, typ, func_id, seq_no)
values ('KC10046', 'commentTypeCode', 'Comment Type Code', 'java.lang.String', 'KC10015', 3);
insert into krms_term_rslvr_parm_spec_t (term_rslvr_parm_spec_id, term_rslvr_id, nm, ver_nbr)
values ('KC2081', 'KC2088', 'commentTypeCode', 1);

