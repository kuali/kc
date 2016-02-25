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

-- Institutional Proposal custom data
insert into krms_typ_t (TYP_ID, NM, NMSPC_CD, SRVC_NM, ACTV, VER_NBR)
values ('RES-BOOT10003', 'IP Custom Data Term Resolver Type Service', 'KC-IP', '{http://kc.kuali.org/core/v5_0}institutionalProposalCustomDataResolverTypeService', 'Y', 1);
insert into KRMS_CTGRY_T (CTGRY_ID, NM, NMSPC_CD, VER_NBR) values ('RES-BOOT1027', 'Custom Data Attribute', 'KC-IP', 1);

insert into krms_term_spec_t (term_spec_id, nm, typ, actv, ver_nbr, desc_txt, nmspc_cd)
values ('RES-BOOT2105', 'Custom Attribute Id', 'java.lang.Boolean', 'Y', 1, 'Check if custom data matches the given value', 'KC-IP');

insert into krms_term_rslvr_t (term_rslvr_id, nmspc_cd, nm, typ_id, output_term_spec_id, actv, ver_nbr)
values ('RES-BOOT10003', 'KC-IP', 'IP Custom Data Value Resolver', 'RES-BOOT10003', 'RES-BOOT2105', 'Y', 1);
insert into krms_term_rslvr_parm_spec_t (term_rslvr_parm_spec_id, term_rslvr_id, nm, ver_nbr)
values ('RES-BOOT2082', 'RES-BOOT10003', 'Custom Attribute Id', 1);
insert into krms_cntxt_vld_term_spec_t (cntxt_term_spec_prereq_id, cntxt_id, term_spec_id, prereq)
values ('RES-BOOT1045', 'KC-IP-CONTEXT', 'RES-BOOT2105', 'Y');
insert into krms_term_spec_ctgry_t (term_spec_id, ctgry_id)
values ('RES-BOOT2105', 'RES-BOOT1027');

-- award
insert into krms_typ_t (TYP_ID, NM, NMSPC_CD, SRVC_NM, ACTV, VER_NBR)
values ('RES-BOOT10004', 'Award Custom Data Term Resolver Type Service', 'KC-AWARD', '{http://kc.kuali.org/core/v5_0}awardCustomDataResolverTypeService', 'Y', 1);
insert into KRMS_CTGRY_T (CTGRY_ID, NM, NMSPC_CD, VER_NBR) values ('RES-BOOT1028', 'Custom Data Attribute', 'KC-AWARD', 1);
insert into krms_term_spec_t (term_spec_id, nm, typ, actv, ver_nbr, desc_txt, nmspc_cd)
values ('RES-BOOT2106', 'Custom Attribute Id', 'java.lang.Boolean', 'Y', 1, 'Check if custom data matches the given value', 'KC-AWARD');
insert into krms_term_rslvr_t (term_rslvr_id, nmspc_cd, nm, typ_id, output_term_spec_id, actv, ver_nbr)
values ('RES-BOOT10020', 'KC-AWARD', 'Award Custom Data Value Resolver', 'RES-BOOT10004', 'RES-BOOT2106', 'Y', 1);
insert into krms_term_rslvr_parm_spec_t (term_rslvr_parm_spec_id, term_rslvr_id, nm, ver_nbr)
values ('RES-BOOT2083', 'RES-BOOT10020', 'Custom Attribute Id', 1);
insert into krms_cntxt_vld_term_spec_t (cntxt_term_spec_prereq_id, cntxt_id, term_spec_id, prereq)
values ('RES-BOOT1046', 'KC-AWARD-CONTEXT', 'RES-BOOT2106', 'Y');
insert into krms_term_spec_ctgry_t (term_spec_id, ctgry_id)
values ('RES-BOOT2106', 'RES-BOOT1028');

-- subaward
insert into krms_typ_t (TYP_ID, NM, NMSPC_CD, SRVC_NM, ACTV, VER_NBR)
values ('RES-BOOT10005', 'Subaward Custom Data Term Resolver Type Service', 'KC-SUBAWARD', '{http://kc.kuali.org/core/v5_0}subawardCustomDataResolverTypeService', 'Y', 1);
insert into KRMS_CTGRY_T (CTGRY_ID, NM, NMSPC_CD, VER_NBR) values
('RES-BOOT1029', 'Custom Data Attribute', 'KC-SUBAWARD', 1);
insert into krms_term_spec_t (term_spec_id, nm, typ, actv, ver_nbr, desc_txt, nmspc_cd)
values ('RES-BOOT2107', 'Custom Attribute Id', 'java.lang.Boolean', 'Y', 1, 'Check if custom data matches the given value', 'KC-SUBAWARD');
insert into krms_term_rslvr_t (term_rslvr_id, nmspc_cd, nm, typ_id, output_term_spec_id, actv, ver_nbr)
values ('RES-BOOT10021', 'KC-SUBAWARD', 'Subaward Custom Data Value Resolver', 'RES-BOOT10005', 'RES-BOOT2107', 'Y', 1);
insert into krms_term_rslvr_parm_spec_t (term_rslvr_parm_spec_id, term_rslvr_id, nm, ver_nbr)
values ('RES-BOOT2084', 'RES-BOOT10021', 'Custom Attribute Id', 1);
insert into krms_cntxt_vld_term_spec_t (cntxt_term_spec_prereq_id, cntxt_id, term_spec_id, prereq)
values ('RES-BOOT1047', 'KC-SUBAWARD-CONTEXT', 'RES-BOOT2107', 'Y');
insert into krms_term_spec_ctgry_t (term_spec_id, ctgry_id)
values ('RES-BOOT2107', 'RES-BOOT1029');

-- irb protocol
insert into krms_typ_t (TYP_ID, NM, NMSPC_CD, SRVC_NM, ACTV, VER_NBR)
values ('RES-BOOT10006', 'Irb Protocol Custom Data Term Resolver Type Service', 'KC-PROTOCOL', '{http://kc.kuali.org/core/v5_0}irbProtocolCustomDataResolverTypeService', 'Y', 1);
insert into KRMS_CTGRY_T (CTGRY_ID, NM, NMSPC_CD, VER_NBR) values
('RES-BOOT1030', 'Custom Data Attribute', 'KC-PROTOCOL', 1);
insert into krms_term_spec_t (term_spec_id, nm, typ, actv, ver_nbr, desc_txt, nmspc_cd)
values ('RES-BOOT2108', 'Custom Attribute Id', 'java.lang.Boolean', 'Y', 1, 'Check if custom data matches the given value', 'KC-PROTOCOL');
insert into krms_term_rslvr_t (term_rslvr_id, nmspc_cd, nm, typ_id, output_term_spec_id, actv, ver_nbr)
values ('RES-BOOT10022', 'KC-PROTOCOL', 'IRB Protocol Custom Data Value Resolver', 'RES-BOOT10006', 'RES-BOOT2108', 'Y', 1);
insert into krms_term_rslvr_parm_spec_t (term_rslvr_parm_spec_id, term_rslvr_id, nm, ver_nbr)
values ('RES-BOOT2085', 'RES-BOOT10022', 'Custom Attribute Id', 1);
insert into krms_cntxt_vld_term_spec_t (cntxt_term_spec_prereq_id, cntxt_id, term_spec_id, prereq)
values ('RES-BOOT1048', 'KC-PROTOCOL-CONTEXT', 'RES-BOOT2108', 'Y');
insert into krms_term_spec_ctgry_t (term_spec_id, ctgry_id)
values ('RES-BOOT2108', 'RES-BOOT1030');

-- iacuc protocol
insert into krms_typ_t (TYP_ID, NM, NMSPC_CD, SRVC_NM, ACTV, VER_NBR)
values ('RES-BOOT10007', 'Iacuc Protocol Custom Data Term Resolver Type Service', 'KC-IACUC', '{http://kc.kuali.org/core/v5_0}iacucProtocolCustomDataResolverTypeService', 'Y', 1);
insert into KRMS_CTGRY_T (CTGRY_ID, NM, NMSPC_CD, VER_NBR) values
('RES-BOOT1031', 'Custom Data Attribute', 'KC-IACUC', 1);
insert into krms_term_spec_t (term_spec_id, nm, typ, actv, ver_nbr, desc_txt, nmspc_cd)
values ('RES-BOOT2109', 'Custom Attribute Id', 'java.lang.Boolean', 'Y', 1, 'Check if custom data matches the given value', 'KC-IACUC');
insert into krms_term_rslvr_t (term_rslvr_id, nmspc_cd, nm, typ_id, output_term_spec_id, actv, ver_nbr)
values ('RES-BOOT10023', 'KC-IACUC', 'IACUC Protocol Custom Data Value Resolver', 'RES-BOOT10007', 'RES-BOOT2109', 'Y', 1);
insert into krms_term_rslvr_parm_spec_t (term_rslvr_parm_spec_id, term_rslvr_id, nm, ver_nbr)
values ('RES-BOOT2086', 'RES-BOOT10023', 'Custom Attribute Id', 1);
insert into krms_cntxt_vld_term_spec_t (cntxt_term_spec_prereq_id, cntxt_id, term_spec_id, prereq)
values ('RES-BOOT1049', 'KC-IACUC-CONTEXT', 'RES-BOOT2109', 'Y');
insert into krms_term_spec_ctgry_t (term_spec_id, ctgry_id)
values ('RES-BOOT2109', 'RES-BOOT1031');

-- Proposal Development
insert into krms_typ_t (TYP_ID, NM, NMSPC_CD, SRVC_NM, ACTV, VER_NBR)
values ('RES-BOOT10008', 'Proposal Development Custom Data Term Resolver Type Service', 'KC-PD', '{http://kc.kuali.org/core/v5_0}proposalDevelopmentCustomDataResolverTypeService', 'Y', 1);
insert into KRMS_CTGRY_T (CTGRY_ID, NM, NMSPC_CD, VER_NBR) values
('RES-BOOT1032', 'Custom Data Attribute', 'KC-PD', 1);
insert into krms_term_spec_t (term_spec_id, nm, typ, actv, ver_nbr, desc_txt, nmspc_cd)
values ('RES-BOOT2110', 'Custom Attribute Id', 'java.lang.Boolean', 'Y', 1, 'Check if custom data matches the given value', 'KC-PD');
insert into krms_term_rslvr_t (term_rslvr_id, nmspc_cd, nm, typ_id, output_term_spec_id, actv, ver_nbr)
values ('RES-BOOT10024', 'KC-PD', 'Proposal Development Custom Data Value Resolver', 'RES-BOOT10008', 'RES-BOOT2110', 'Y', 1);
insert into krms_term_rslvr_parm_spec_t (term_rslvr_parm_spec_id, term_rslvr_id, nm, ver_nbr)
values ('RES-BOOT2087', 'RES-BOOT10024', 'Custom Attribute Id', 1);
insert into krms_cntxt_vld_term_spec_t (cntxt_term_spec_prereq_id, cntxt_id, term_spec_id, prereq)
values ('RES-BOOT1050', 'KC-PD-CONTEXT', 'RES-BOOT2110', 'Y');
insert into krms_term_spec_ctgry_t (term_spec_id, ctgry_id)
values ('RES-BOOT2110', 'RES-BOOT1032');
