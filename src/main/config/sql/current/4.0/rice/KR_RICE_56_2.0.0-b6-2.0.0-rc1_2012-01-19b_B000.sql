--
-- Copyright 2005-2012 The Kuali Foundation
--
-- Licensed under the Educational Community License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
--
-- http://www.opensource.org/licenses/ecl2.php
--
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
--


--
-- KULRICE-5856: KRNS_DOC_HDR_T.FDOC_DESC column is only 40 char
--

ALTER TABLE KRNS_DOC_HDR_T MODIFY (FDOC_DESC varchar2(255))
/

--
-- KULRICE-6463: New DB Indexes for KIM Permission checks
--

CREATE INDEX KRIM_ROLE_PERM_TI2 ON KRIM_ROLE_PERM_T (PERM_ID, ACTV_IND)
/
CREATE INDEX KRIM_PERM_TI1 ON KRIM_PERM_T (PERM_TMPL_ID)
/
CREATE INDEX KRIM_PERM_TI2 ON KRIM_PERM_T (PERM_TMPL_ID, ACTV_IND)
/
CREATE INDEX KRIM_PERM_TMPL_TI1 ON KRIM_PERM_TMPL_T (NMSPC_CD, NM)
/
CREATE INDEX KRIM_ROLE_MBR_TI2 ON KRIM_ROLE_MBR_T (role_id, mbr_id, mbr_typ_cd)
/
CREATE INDEX KRIM_ROLE_MBR_TI3 ON KRIM_ROLE_MBR_T (mbr_id, mbr_typ_cd)
/

--
-- KRMS Sample (and production) Data
--

---- If you should want to clean out your KRMS tables:
delete from  krms_cntxt_vld_rule_typ_t
/
delete from  krms_cntxt_vld_func_t
/
delete from  krms_term_spec_ctgry_t
/
delete from  krms_func_ctgry_t
/
delete from  krms_ctgry_t
/
delete from  krms_func_parm_t
/
delete from  krms_func_t
/
delete from  krms_term_parm_t
/
delete from  krms_term_rslvr_parm_spec_t
/
delete from  krms_term_t
/
delete from  krms_cntxt_vld_term_spec_t
/
delete from  krms_term_rslvr_input_spec_t
/
delete from  krms_term_rslvr_attr_t
/
delete from  krms_term_rslvr_t
/
delete from  krms_term_spec_t
/
delete from  krms_prop_parm_t
/
delete from  krms_cmpnd_prop_props_t
/
delete from  krms_agenda_attr_t
/
delete from  krms_cntxt_vld_actn_typ_t
/
delete from  krms_cntxt_vld_agenda_typ_t
/
delete from  krms_cntxt_attr_t
/
delete from  krms_rule_attr_t
/
update krms_agenda_itm_t set when_true=null
/
update krms_agenda_itm_t set when_false=null
/
update krms_agenda_itm_t set always=null
/
delete from  krms_agenda_itm_t
/
delete from  krms_actn_attr_t
/
delete from  krms_actn_t
/
delete from  krms_typ_attr_t
/
delete from  krms_attr_defn_t
/
delete from  krms_agenda_t
/
update krms_rule_t set prop_id=null
/
delete from  krms_prop_t
/
delete from  krms_rule_t
/
delete from  krms_typ_t
/
delete from  krms_cntxt_t
/
delete from krcr_nmspc_t where obj_id = '5a83c912-94b9-4b4d-ac3f-88c53380a4a3'
/
---- KRMS test namespace

insert into krcr_nmspc_t (nmspc_cd, obj_id, nm, appl_id) 
values ('KR-RULE-TEST', '5a83c912-94b9-4b4d-ac3f-88c53380a4a3', 'Kuali Rules Test', 'RICE')
/

-- misc category
insert into krms_ctgry_t (ctgry_id, nm, nmspc_cd) values ('T1000', 'misc', 'KR-RULE-TEST')
/

--
-- TermResolver taking 1 campus code parameter
--

insert into krms_term_spec_t
(term_spec_id, nmspc_cd, nm, typ, desc_txt, actv, ver_nbr)
values ('T1000', 'KR-RULE-TEST', 'campusSize', 'java.lang.Integer', 'Size in # of students of the campus', 'Y', 1)
/

insert into krms_typ_t
(typ_id, nm, nmspc_cd, srvc_nm, actv, ver_nbr)
values ('T1000', 'TermResolver', 'KR-RULE-TEST', null, 'Y', 1)
/

insert into krms_term_rslvr_t
(term_rslvr_id, nmspc_cd, nm, typ_id, output_term_spec_id, actv, ver_nbr)
values ('T1000', 'KR-RULE-TEST', 'campusSizeResolver', 'T1000','T1000', 'Y', 1)
/

insert into krms_term_rslvr_parm_spec_t
(term_rslvr_parm_spec_id, term_rslvr_id, nm, ver_nbr)
values ('T1000', 'T1000', 'Campus Code', 1)
/





insert into krms_term_spec_t
(term_spec_id, nmspc_cd, nm, typ, desc_txt, actv, ver_nbr)
values ('T1001', 'KR-RULE-TEST', 'orgMember', 'java.lang.Boolean', 'is the principal in the organization', 'Y', 1)
/

insert into krms_term_rslvr_t
(term_rslvr_id, nmspc_cd, nm, typ_id, output_term_spec_id, actv, ver_nbr)
values ('T1001', 'KR-RULE-TEST', 'orgMemberResolver', 'T1000','T1001', 'Y', 1)
/

insert into krms_term_rslvr_parm_spec_t
(term_rslvr_parm_spec_id, term_rslvr_id, nm, ver_nbr)
values ('T1001', 'T1001', 'Org Code', 1)
/

insert into krms_term_rslvr_parm_spec_t
(term_rslvr_parm_spec_id, term_rslvr_id, nm, ver_nbr)
values ('T1002', 'T1001', 'Principal ID', 1)
/




insert into krms_term_t
(term_id, term_spec_id, desc_txt, ver_nbr)
values ('T1000', 'T1000', 'Bloomington Campus Size', 1)
/
insert into krms_term_parm_t
(term_parm_id, term_id, nm, val, ver_nbr)
values ('T1000', 'T1000', 'Campus Code', 'BL', 1)
/







insert into krms_attr_defn_t
(attr_defn_id, nm, nmspc_cd, lbl, actv, ver_nbr)
values('T1000', 'Context1Qualifier', 'KR-RULE-TEST', 'Context 1 Qualifier', 'Y', 1)
/

insert into krms_attr_defn_t
(attr_defn_id, nm, nmspc_cd, lbl, actv, ver_nbr)
values('T1001', 'Event', 'KR-RULE-TEST', 'Event Name', 'Y', 1)
/

insert into krms_typ_t
(typ_id, nm, nmspc_cd, srvc_nm, actv, ver_nbr)
values ('T1001', 'CAMPUS', 'KR-RULE-TEST', 'myCampusService', 'Y', 1)
/

insert into krms_typ_t
(typ_id, nm, nmspc_cd, srvc_nm, actv, ver_nbr)
values ('T1002', 'KrmsActionResolverType', 'KR-RULE-TEST', 'testActionTypeService', 'Y', 1)
/

insert into krms_typ_t
(typ_id, nm, nmspc_cd, actv, ver_nbr)
values ('T1003', 'CONTEXT', 'KR-RULE-TEST',  'Y', 1)
/

insert into krms_typ_attr_t
(typ_attr_id, seq_no, typ_id, attr_defn_id, actv, ver_nbr)
values ('T1000', 1, 'T1003', 'T1000', 'Y', 1)
/

insert into krms_typ_t
(typ_id, nm, nmspc_cd, actv, ver_nbr)
values ('T1004', 'AGENDA', 'KR-RULE-TEST',  'Y', 1)
/


insert into krms_typ_t
(typ_id, nm, nmspc_cd, srvc_nm, actv, ver_nbr)
values ('T1005', 'Campus Agenda', 'KR-RULE-TEST', 'campusAgendaTypeService', 'Y', 1)
/


insert into krms_cntxt_t
(cntxt_id, nmspc_cd, nm, typ_id, actv, ver_nbr)
values ('CONTEXT1','KR-RULE-TEST', 'Context1', 'T1003', 'Y', 1)
/

insert into krms_cntxt_t
(cntxt_id, nmspc_cd, nm, typ_id, actv, ver_nbr)
values ('CONTEXT_NO_PERMISSION','KRMS_TEST_VOID', 'Context with no premissions', 'T1003', 'Y', 1)
/

insert into krms_cntxt_attr_t
(cntxt_attr_id, cntxt_id, attr_val, attr_defn_id, ver_nbr)
values('T1000', 'CONTEXT1', 'BLAH', 'T1000', 1)
/

insert into krms_cntxt_vld_actn_typ_t
(cntxt_vld_actn_id, cntxt_id, actn_typ_id, ver_nbr)
values ('T1000', 'CONTEXT1', 'T1002', 1)
/

insert into krms_cntxt_vld_actn_typ_t
(cntxt_vld_actn_id, cntxt_id, actn_typ_id, ver_nbr)
values ('T1001', 'CONTEXT1', '1000', 1)
/

insert into krms_cntxt_vld_actn_typ_t
(cntxt_vld_actn_id, cntxt_id, actn_typ_id, ver_nbr)
values ('T1002', 'CONTEXT1', '1001', 1)
/

insert into krms_rule_t
(rule_id, nmspc_cd, nm, typ_id, prop_id, actv, ver_nbr, desc_txt)
values ('T1000', 'KR-RULE-TEST', 'Rule1', null, null, 'Y', 1, 'stub rule lorem ipsum')
/

insert into krms_prop_t(prop_id, desc_txt, typ_id, dscrm_typ_cd, rule_id, ver_nbr)
values ('T1000', 'is campus bloomington', null, 'S','T1000',1)
/

update krms_rule_t
set prop_id = 'T1000' where rule_id = 'T1000'
/

insert into krms_term_spec_t
(term_spec_id, nm, nmspc_cd,  typ, actv, ver_nbr)
values ('T1002', 'Campus Code', 'KR-RULE-TEST', 'java.lang.String', 'Y', 1)
/

insert into krms_term_t
(term_id, term_spec_id, desc_txt, ver_nbr)
values ('T1002', 'T1002', null, 1)
/

insert into krms_prop_parm_t
(prop_parm_id, prop_id, parm_val, parm_typ_cd, seq_no, ver_nbr)
values ('T1000', 'T1000', 'T1002', 'T', 1, 1)
/

insert into krms_prop_parm_t
(prop_parm_id, prop_id, parm_val, parm_typ_cd, seq_no, ver_nbr)
values ('T1001', 'T1000', 'BL', 'C', 2, 1)
/

insert into krms_prop_parm_t
(prop_parm_id, prop_id, parm_val, parm_typ_cd, seq_no, ver_nbr)
values ('T1002', 'T1000', '=', 'O', 3, 1)
/

insert into krms_actn_t
(actn_id, nm, nmspc_cd, desc_txt, typ_id, rule_id, seq_no, ver_nbr)
values ( 'T1000', 'testAction', 'KR-RULE-TEST', 'Action Stub for Testing', 'T1002', 'T1000', 1, 1)
/

insert into krms_agenda_t
(agenda_id, nm, cntxt_id, init_agenda_itm_id, typ_id, actv, ver_nbr)
values ( 'T1000', 'My Fabulous Agenda', 'CONTEXT1', null, 'T1005', 'Y', 1)
/

insert into krms_agenda_itm_t
(agenda_itm_id, rule_id, agenda_id, ver_nbr)
VALUES('T1000', 'T1000', 'T1000', 1)
/

update krms_agenda_t set INIT_AGENDA_ITM_ID = 'T1000' where agenda_id = 'T1000'
/


insert into krms_term_spec_t
(term_spec_id, nmspc_cd, nm, typ, actv, ver_nbr)
values ('T1008', 'KR-RULE-TEST', 'campusCode', 'java.lang.String', 'Y', 1)
/

-- next item

insert into krms_rule_t
(rule_id, nmspc_cd, nm, typ_id, prop_id, actv, ver_nbr, desc_txt)
values ('T1001', 'KR-RULE-TEST', 'Rule2', null, null, 'Y', 1, 'Frog specimens bogus rule foo')
/

insert into krms_prop_t(prop_id, desc_txt, typ_id, dscrm_typ_cd, rule_id, ver_nbr)
values ('T1001', 'is campus bloomington', null, 'S','T1001',1)
/

update krms_rule_t
set prop_id = 'T1001' where rule_id = 'T1001'
/

insert into krms_prop_parm_t
(prop_parm_id, prop_id, parm_val, parm_typ_cd, seq_no, ver_nbr)
values ('T1003', 'T1001', 'T1002', 'T', 1, 1)
/

insert into krms_prop_parm_t
(prop_parm_id, prop_id, parm_val, parm_typ_cd, seq_no, ver_nbr)
values ('T1004', 'T1001', 'BL', 'C', 2, 1)
/

insert into krms_prop_parm_t
(prop_parm_id, prop_id, parm_val, parm_typ_cd, seq_no, ver_nbr)
values ('T1005', 'T1001', '=', 'O', 3, 1)
/

insert into krms_agenda_itm_t
(agenda_itm_id, rule_id, agenda_id, ver_nbr)
VALUES('T1001', 'T1001', 'T1000', 1)
/

update krms_agenda_itm_t
SET when_true = 'T1001' WHERE agenda_itm_id = 'T1000'
/

-- next item

insert into krms_rule_t
(rule_id, nmspc_cd, nm, typ_id, prop_id, actv, ver_nbr, desc_txt)
values ('T1002', 'KR-RULE-TEST', 'Rule3', null, null, 'Y', 1, 'Bloomington campus code rule')
/

insert into krms_prop_t(prop_id, desc_txt, typ_id, dscrm_typ_cd, rule_id, ver_nbr)
values ('T1002', 'is campus bloomington', null, 'S','T1002',1)
/

update krms_rule_t
set prop_id = 'T1002' where rule_id = 'T1002'
/

insert into krms_prop_parm_t
(prop_parm_id, prop_id, parm_val, parm_typ_cd, seq_no, ver_nbr)
values ('T1006', 'T1002', 'T1002', 'T', 1, 1)
/

insert into krms_prop_parm_t
(prop_parm_id, prop_id, parm_val, parm_typ_cd, seq_no, ver_nbr)
values ('T1007', 'T1002', 'BL', 'C', 2, 1)
/

insert into krms_prop_parm_t
(prop_parm_id, prop_id, parm_val, parm_typ_cd, seq_no, ver_nbr)
values ('T1008', 'T1002', '=', 'O', 3, 1)
/

insert into krms_agenda_itm_t
(agenda_itm_id, rule_id, agenda_id, ver_nbr)
VALUES('T1002', 'T1002', 'T1000', 1)
/
--
update krms_agenda_itm_t
SET always = 'T1002' WHERE agenda_itm_id = 'T1001'
/

-- next item

insert into krms_rule_t
(rule_id, nmspc_cd, nm, typ_id, prop_id, actv, ver_nbr, desc_txt)
values ('T1003', 'KR-RULE-TEST', 'Rule4', null, null, 'Y', 1, 'check for possible BBQ ingiter hazard')
/

insert into krms_prop_t(prop_id, desc_txt, typ_id, dscrm_typ_cd, rule_id, ver_nbr)
values ('T1003', 'is campus bloomington', null, 'S','T1003',1)
/

update krms_rule_t
set prop_id = 'T1003' where rule_id = 'T1003'
/

insert into krms_prop_parm_t
(prop_parm_id, prop_id, parm_val, parm_typ_cd, seq_no, ver_nbr)
values ('T1009', 'T1003', 'T1002', 'T', 1, 1)
/

insert into krms_prop_parm_t
(prop_parm_id, prop_id, parm_val, parm_typ_cd, seq_no, ver_nbr)
values ('T1010', 'T1003', 'BL', 'C', 2, 1)
/

insert into krms_prop_parm_t
(prop_parm_id, prop_id, parm_val, parm_typ_cd, seq_no, ver_nbr)
values ('T1011', 'T1003', '=', 'O', 3, 1)
/

insert into krms_agenda_itm_t
(agenda_itm_id, rule_id, agenda_id, ver_nbr)
VALUES('T1003', 'T1003', 'T1000', 1)
/
--
update krms_agenda_itm_t
SET always = 'T1003' WHERE agenda_itm_id = 'T1002'
/

-- next item

insert into krms_rule_t
(rule_id, nmspc_cd, nm, typ_id, prop_id, actv, ver_nbr, desc_txt)
values ('T1004', 'KR-RULE-TEST', 'Rule5', null, null, 'Y', 1, 'remembered to wear socks')
/

insert into krms_prop_t(prop_id, desc_txt, typ_id, dscrm_typ_cd, rule_id, ver_nbr)
values ('T1004', 'is campus bloomington', null, 'S','T1004',1)
/

update krms_rule_t
set prop_id = 'T1004' where rule_id = 'T1004'
/

insert into krms_prop_parm_t
(prop_parm_id, prop_id, parm_val, parm_typ_cd, seq_no, ver_nbr)
values ('T1012', 'T1004', 'T1002', 'T', 1, 1)
/

insert into krms_prop_parm_t
(prop_parm_id, prop_id, parm_val, parm_typ_cd, seq_no, ver_nbr)
values ('T1013', 'T1004', 'BL', 'C', 2, 1)
/

insert into krms_prop_parm_t
(prop_parm_id, prop_id, parm_val, parm_typ_cd, seq_no, ver_nbr)
values ('T1014', 'T1004', '=', 'O', 3, 1)
/

insert into krms_agenda_itm_t
(agenda_itm_id, rule_id, agenda_id, ver_nbr)
VALUES('T1004', 'T1004', 'T1000', 1)
/

update krms_agenda_itm_t
SET when_false = 'T1004' WHERE agenda_itm_id = 'T1000'
/

-- next item

insert into krms_rule_t
(rule_id, nmspc_cd, nm, typ_id, prop_id, actv, ver_nbr, desc_txt)
values ('T1005', 'KR-RULE-TEST', 'Rule6', null, null, 'Y', 1, 'good behavior at carnival')
/

insert into krms_prop_t(prop_id, desc_txt, typ_id, dscrm_typ_cd, rule_id, ver_nbr)
values ('T1005', 'is campus bloomington', null, 'S','T1005',1)
/

update krms_rule_t
set prop_id = 'T1005' where rule_id = 'T1005'
/

insert into krms_prop_parm_t
(prop_parm_id, prop_id, parm_val, parm_typ_cd, seq_no, ver_nbr)
values ('T1015', 'T1005', 'T1002', 'T', 1, 1)
/

insert into krms_prop_parm_t
(prop_parm_id, prop_id, parm_val, parm_typ_cd, seq_no, ver_nbr)
values ('T1016', 'T1005', 'BL', 'C', 2, 1)
/

insert into krms_prop_parm_t
(prop_parm_id, prop_id, parm_val, parm_typ_cd, seq_no, ver_nbr)
values ('T1017', 'T1005', '=', 'O', 3, 1)
/

insert into krms_agenda_itm_t
(agenda_itm_id, rule_id, agenda_id, ver_nbr)
VALUES('T1005', 'T1005', 'T1000', 1)
/
--
update krms_agenda_itm_t
SET always = 'T1005' WHERE agenda_itm_id = 'T1000'
/



--
-- next item
--

insert into krms_rule_t
(rule_id, nmspc_cd, nm, typ_id, prop_id, actv, ver_nbr, desc_txt)
values ('T1006', 'KR-RULE-TEST', 'Rule7', null, null, 'Y', 1, 'is KRMS in da haus')
/

insert into krms_prop_t(prop_id, desc_txt, typ_id, dscrm_typ_cd, rule_id, ver_nbr)
values ('T1006', 'is campus bloomington', null, 'S','T1006',1)
/

update krms_rule_t
set prop_id = 'T1006' where rule_id = 'T1006'
/

insert into krms_prop_parm_t
(prop_parm_id, prop_id, parm_val, parm_typ_cd, seq_no, ver_nbr)
values ('T1018', 'T1006', 'T1002', 'T', 1, 1)
/

insert into krms_prop_parm_t
(prop_parm_id, prop_id, parm_val, parm_typ_cd, seq_no, ver_nbr)
values ('T1019', 'T1006', 'BL', 'C', 2, 1)
/

insert into krms_prop_parm_t
(prop_parm_id, prop_id, parm_val, parm_typ_cd, seq_no, ver_nbr)
values ('T1020', 'T1006', '=', 'O', 3, 1)
/

insert into krms_agenda_itm_t
(agenda_itm_id, rule_id, agenda_id, ver_nbr)
VALUES('T1006', 'T1006', 'T1000', 1)
/
--
update krms_agenda_itm_t
SET when_false = 'T1006' WHERE agenda_itm_id = 'T1002'
/

--
-- rule with a compound proposition
--
insert into krms_rule_t
(rule_id, nmspc_cd, nm, typ_id, prop_id, actv, ver_nbr, desc_txt)
values ('T1007', 'KR-RULE-TEST', 'CmpdTestRule', null, null, 'Y', 1, 'For testing compound props')
/

insert into krms_prop_t
(prop_id, desc_txt, typ_id, dscrm_typ_cd, rule_id, cmpnd_op_cd, ver_nbr)
values ('T1007', 'a compound prop', null, 'C','T1007', '&', 1)
/

update krms_rule_t
set prop_id = 'T1007' where rule_id = 'T1007'
/

insert into krms_term_spec_t
(term_spec_id, nmspc_cd, nm, typ, actv, ver_nbr)
values ('T1003', 'KR-RULE-TEST', 'bogusFundTermSpec', 'java.lang.String', 'Y', 1)
/

insert into krms_term_t
(term_id, term_spec_id, desc_txt, ver_nbr)
values ('T1003', 'T1003', 'Fund Name', 1)
/

-- 2nd level prop
insert into krms_prop_t(prop_id, desc_txt, typ_id, dscrm_typ_cd, rule_id, ver_nbr)
values ('T1008', 'a simple child to a compound prop', null, 'S','T1007', 1)
/

insert into krms_prop_parm_t
(prop_parm_id, prop_id, parm_val, parm_typ_cd, seq_no, ver_nbr)
values ('T1021', 'T1008', 'T1002', 'T', 1, 1)
/

insert into krms_prop_parm_t
(prop_parm_id, prop_id, parm_val, parm_typ_cd, seq_no, ver_nbr)
values ('T1022', 'T1008', 'Muir', 'C', 2, 1)
/

insert into krms_prop_parm_t
(prop_parm_id, prop_id, parm_val, parm_typ_cd, seq_no, ver_nbr)
values ('T1023', 'T1008', '=', 'O', 3, 1)
/

insert into krms_cmpnd_prop_props_t
(cmpnd_prop_id, prop_id)
values ('T1007', 'T1008')
/

insert into krms_prop_t(prop_id, desc_txt, typ_id, dscrm_typ_cd, rule_id, cmpnd_seq_no, ver_nbr)
values ('T1009', '2nd simple child to a compound prop ', null, 'S','T1007', 2, 1)
/

insert into krms_prop_parm_t
(prop_parm_id, prop_id, parm_val, parm_typ_cd, seq_no, ver_nbr)
values ('T1024', 'T1009', 'T1002', 'T', 1, 1)
/

insert into krms_prop_parm_t
(prop_parm_id, prop_id, parm_val, parm_typ_cd, seq_no, ver_nbr)
values ('T1025', 'T1009', 'Revelle', 'C', 2, 1)
/

insert into krms_prop_parm_t
(prop_parm_id, prop_id, parm_val, parm_typ_cd, seq_no, ver_nbr)
values ('T1026', 'T1009', '=', 'O', 3, 1)
/

insert into krms_cmpnd_prop_props_t
(cmpnd_prop_id, prop_id)
values ('T1007', 'T1009')
/


insert into krms_prop_t(prop_id, desc_txt, typ_id, dscrm_typ_cd, rule_id, cmpnd_seq_no, ver_nbr)
values ('T1010', '3nd simple child to a compound prop ', null, 'S','T1007', 3, 1)
/

insert into krms_prop_parm_t
(prop_parm_id, prop_id, parm_val, parm_typ_cd, seq_no, ver_nbr)
values ('T1027', 'T1010', 'T1002', 'T', 1, 1)
/

insert into krms_prop_parm_t
(prop_parm_id, prop_id, parm_val, parm_typ_cd, seq_no, ver_nbr)
values ('T1028', 'T1010', 'Warren', 'C', 2, 1)
/

insert into krms_prop_parm_t
(prop_parm_id, prop_id, parm_val, parm_typ_cd, seq_no, ver_nbr)
values ('T1029', 'T1010', '=', 'O', 3, 1)
/

insert into krms_cmpnd_prop_props_t
(cmpnd_prop_id, prop_id)
values ('T1007', 'T1010')
/


--
-- start of new agendas (AGENDA002, AGENDA003) and their associated items
--



--  AGENDA 002
insert into krms_agenda_t (agenda_id, nm, cntxt_id, init_agenda_itm_id, typ_id, actv, ver_nbr)
values ('T1001', 'SimpleAgendaCompoundProp', 'CONTEXT1', null, 'T1004', 'Y', 1)
/

insert into krms_agenda_itm_t (AGENDA_ITM_ID, RULE_ID, AGENDA_ID, VER_NBR)
values ('T1007', 'T1007', 'T1001', 1)
/
update krms_agenda_t set INIT_AGENDA_ITM_ID = 'T1007' where AGENDA_ID = 'T1001'
/
--  AGENDA 003 stuff

insert into krms_term_spec_t (TERM_SPEC_ID, NM, TYP, ACTV, VER_NBR, DESC_TXT, nmspc_cd)
values ('T1004', 'PO Value', 'java.lang.Integer', 'Y', 1, 'Purchase Order Value', 'KR-RULE-TEST')
/
insert into krms_term_spec_t (TERM_SPEC_ID, NM, TYP, ACTV, VER_NBR, DESC_TXT, nmspc_cd)
values ('T1005', 'PO Item Type', 'java.lang.String', 'Y', 1, 'Purchased Item Type', 'KR-RULE-TEST')
/
insert into krms_term_spec_t (TERM_SPEC_ID, NM, TYP, ACTV, VER_NBR, DESC_TXT, nmspc_cd)
values ('T1006', 'Account', 'java.lang.String', 'Y', 1, 'Charged To Account', 'KR-RULE-TEST')
/
insert into krms_term_spec_t (TERM_SPEC_ID, NM, TYP, ACTV, VER_NBR, DESC_TXT, nmspc_cd)
values ('T1007', 'Occasion', 'java.lang.String', 'Y', 1, 'Special Event', 'KR-RULE-TEST')
/
insert into krms_cntxt_vld_term_spec_t(cntxt_term_spec_prereq_id, cntxt_id, term_spec_id, prereq)
values ('T1000', 'CONTEXT1', 'T1002', 'N')
/
insert into krms_cntxt_vld_term_spec_t(cntxt_term_spec_prereq_id, cntxt_id, term_spec_id, prereq)
values ('T1001', 'CONTEXT1', 'T1003', 'N')
/
insert into krms_cntxt_vld_term_spec_t(cntxt_term_spec_prereq_id, cntxt_id, term_spec_id, prereq)
values ('T1002', 'CONTEXT1', 'T1004', 'N')
/
insert into krms_cntxt_vld_term_spec_t(cntxt_term_spec_prereq_id, cntxt_id, term_spec_id, prereq)
values ('T1003', 'CONTEXT1', 'T1005', 'N')
/
insert into krms_cntxt_vld_term_spec_t(cntxt_term_spec_prereq_id, cntxt_id, term_spec_id, prereq)
values ('T1004', 'CONTEXT1', 'T1006', 'N')
/
insert into krms_cntxt_vld_term_spec_t(cntxt_term_spec_prereq_id, cntxt_id, term_spec_id, prereq)
values ('T1005', 'CONTEXT1', 'T1007', 'N')
/
insert into krms_cntxt_vld_term_spec_t(cntxt_term_spec_prereq_id, cntxt_id, term_spec_id, prereq)
values ('T1006', 'CONTEXT1', 'T1000', 'N')
/
insert into krms_cntxt_vld_term_spec_t(cntxt_term_spec_prereq_id, cntxt_id, term_spec_id, prereq)
values ('T1007', 'CONTEXT1', 'T1001', 'N')
/

insert into krms_term_t(term_id,TERM_SPEC_ID, DESC_TXT, VER_NBR)values ('T1004', 'T1004', 'PO Value', 1)
/
insert into krms_term_t(term_id,TERM_SPEC_ID, DESC_TXT, VER_NBR)values ('T1005', 'T1005', 'PO Item Type', 1)
/
insert into krms_term_t(term_id,TERM_SPEC_ID, DESC_TXT, VER_NBR)values ('T1006', 'T1006', 'Account', 1)
/
insert into krms_term_t(term_id,TERM_SPEC_ID, DESC_TXT, VER_NBR)values ('T1007', 'T1007', 'Occasion', 1)
/
--
-- big fin rule
--
insert into krms_rule_t(rule_id, nmspc_cd, nm, typ_id, prop_id, actv, ver_nbr, desc_txt)
values ('T1008', 'KR-RULE-TEST', 'Going Away Party for Travis', null, null, 'Y', 1, 'Does PO require my approval')
/
insert into krms_prop_t(prop_id, desc_txt, typ_id, dscrm_typ_cd, rule_id, cmpnd_op_cd, ver_nbr)
values ('T1011', 'is purchase special', null, 'C','T1008', '&', 1)
/
update krms_rule_t set prop_id = 'T1011' where rule_id = 'T1008'
/

-- 2nd level prop

-- is it expensive
insert into krms_prop_t(prop_id, desc_txt, typ_id, dscrm_typ_cd, rule_id, ver_nbr)
values ('T1012', 'is purchase order value large', null, 'S','T1008', 1)
/
insert into krms_prop_parm_t(prop_parm_id, prop_id, parm_val, parm_typ_cd, seq_no, ver_nbr)
values ('T1030', 'T1012', 'T1004', 'T', 1, 1)
/
insert into krms_prop_parm_t(prop_parm_id, prop_id, parm_val, parm_typ_cd, seq_no, ver_nbr)
values ('T1031', 'T1012', '5500.00', 'C', 2, 1)
/
insert into krms_prop_parm_t(prop_parm_id, prop_id, parm_val, parm_typ_cd, seq_no, ver_nbr)
values ('T1032', 'T1012', '>', 'O', 3, 1)
/
insert into krms_cmpnd_prop_props_t(cmpnd_prop_id, prop_id)
values ('T1011', 'T1012')
/
-- is it controlled
insert into krms_prop_t(prop_id, desc_txt, typ_id, dscrm_typ_cd, rule_id, cmpnd_op_cd, ver_nbr)
values ('T1013', 'is purchased item controlled', null, 'C','T1008', '|', 1)
/
insert into krms_cmpnd_prop_props_t(cmpnd_prop_id, prop_id)
values ('T1011', 'T1013')
/
-- is it special
insert into krms_prop_t(prop_id, desc_txt, typ_id, dscrm_typ_cd, rule_id, cmpnd_op_cd, ver_nbr)
values ('T1014', 'is it for a special event', null, 'C','T1008', '&', 1)
/
insert into krms_cmpnd_prop_props_t(cmpnd_prop_id, prop_id)
values ('T1011', 'T1014')
/
---- controlled 3rd level props -----

insert into krms_prop_t(prop_id, desc_txt, typ_id, dscrm_typ_cd, rule_id, ver_nbr)
values ('T1015', 'is item purchased animal', null, 'S','T1008', 1)
/
insert into krms_cmpnd_prop_props_t(cmpnd_prop_id, prop_id)
values ('T1013', 'T1015')
/
insert into krms_prop_parm_t(prop_parm_id, prop_id, parm_val, parm_typ_cd, seq_no, ver_nbr)
values ('T1033', 'T1015', 'T1005', 'T', 1, 1)
/
insert into krms_prop_parm_t(prop_parm_id, prop_id, parm_val, parm_typ_cd, seq_no, ver_nbr)
values ('T1034', 'T1015', 'ANIMAL', 'C', 2, 1)
/
insert into krms_prop_parm_t(prop_parm_id, prop_id, parm_val, parm_typ_cd, seq_no, ver_nbr)
values ('T1035', 'T1015', '=', 'O', 3, 1)
/


insert into krms_prop_t(prop_id, desc_txt, typ_id, dscrm_typ_cd, rule_id, ver_nbr)
values ('T1016', 'is purchased item radioactive', null, 'S','T1008', 1)
/
insert into krms_cmpnd_prop_props_t(cmpnd_prop_id, prop_id)
values ('T1013', 'T1016')
/
insert into krms_prop_parm_t(prop_parm_id, prop_id, parm_val, parm_typ_cd, seq_no, ver_nbr)
values ('T1036', 'T1016', 'T1005', 'T', 1, 1)
/
insert into krms_prop_parm_t(prop_parm_id, prop_id, parm_val, parm_typ_cd, seq_no, ver_nbr)
values ('T1037', 'T1016', 'RADIOACTIVE', 'C', 2, 1)
/
insert into krms_prop_parm_t(prop_parm_id, prop_id, parm_val, parm_typ_cd, seq_no, ver_nbr)
values ('T1038', 'T1016', '=', 'O', 3, 1)
/

insert into krms_prop_t(prop_id, desc_txt, typ_id, dscrm_typ_cd, rule_id, cmpnd_seq_no, ver_nbr)
values ('T1017', 'is it medicinal', null, 'S','T1008', 3, 1)
/
insert into krms_cmpnd_prop_props_t(cmpnd_prop_id, prop_id)
values ('T1013', 'T1017')
/
insert into krms_prop_parm_t(prop_parm_id, prop_id, parm_val, parm_typ_cd, seq_no, ver_nbr)
values ('T1039', 'T1017', 'T1005', 'T', 1, 1)
/
insert into krms_prop_parm_t(prop_parm_id, prop_id, parm_val, parm_typ_cd, seq_no, ver_nbr)
values ('T1040', 'T1017', 'ALCOHOL BEVERAGE', 'C', 2, 1)
/
insert into krms_prop_parm_t(prop_parm_id, prop_id, parm_val, parm_typ_cd, seq_no, ver_nbr)
values ('T1041', 'T1017', '=', 'O', 3, 1)
/

-- is it special 3rd level props

insert into krms_prop_t(prop_id, desc_txt, typ_id, dscrm_typ_cd, rule_id, ver_nbr)
values ('T1018', 'charged to Kuali', null, 'S','T1008', 1)
/
insert into krms_cmpnd_prop_props_t(cmpnd_prop_id, prop_id)
values ('T1014', 'T1018')
/
insert into krms_prop_parm_t(prop_parm_id, prop_id, parm_val, parm_typ_cd, seq_no, ver_nbr)
values ('T1042', 'T1018', 'T1006', 'T', 1, 1)
/
insert into krms_prop_parm_t(prop_parm_id, prop_id, parm_val, parm_typ_cd, seq_no, ver_nbr)
values ('T1043', 'T1018', 'KUALI SLUSH FUND', 'C', 2, 1)
/
insert into krms_prop_parm_t(prop_parm_id, prop_id, parm_val, parm_typ_cd, seq_no, ver_nbr)
values ('T1044', 'T1018', '=', 'O', 3, 1)
/


insert into krms_prop_t(prop_id, desc_txt, typ_id, dscrm_typ_cd, rule_id, ver_nbr)
values ('T1019', 'Party at Travis House', null, 'S','T1008', 1)
/
insert into krms_cmpnd_prop_props_t(cmpnd_prop_id, prop_id)
values ('T1014', 'T1019')
/
insert into krms_prop_parm_t(prop_parm_id, prop_id, parm_val, parm_typ_cd, seq_no, ver_nbr)
values ('T1045', 'T1019', 'T1007', 'T', 1, 1)
/
insert into krms_prop_parm_t(prop_parm_id, prop_id, parm_val, parm_typ_cd, seq_no, ver_nbr)
values ('T1046', 'T1019', 'Christmas Party', 'C', 2, 1)
/
insert into krms_prop_parm_t(prop_parm_id, prop_id, parm_val, parm_typ_cd, seq_no, ver_nbr)
values ('T1047', 'T1019', '=', 'O', 3, 1)
/

--  AGENDA 003
insert into krms_agenda_t (agenda_id, nm, cntxt_id, init_agenda_itm_id, typ_id, actv, ver_nbr)
values ('T1002', 'One Big Rule', 'CONTEXT1', null, 'T1004', 'Y', 1)
/
insert into krms_agenda_itm_t (AGENDA_ITM_ID, RULE_ID, AGENDA_ID, VER_NBR)
values ('T1008', 'T1008', 'T1002', 1)
/
update krms_agenda_t set INIT_AGENDA_ITM_ID = 'T1008' where AGENDA_ID = 'T1002'
/


-- SQL for test CampusAgendaType:

insert into krms_cntxt_vld_agenda_typ_t
(cntxt_vld_agenda_id, cntxt_id, agenda_typ_id, ver_nbr)
values ('T1000', 'CONTEXT1', 'T1005', 1)
/

-- add a db-only attribute to CampusAgendaType
insert into krms_attr_defn_t (ATTR_DEFN_ID, NM, NMSPC_CD, LBL, CMPNT_NM, DESC_TXT)
values ('T1002', 'Optional Test Attribute', 'KR-RULE-TEST', 'label', null,
'this is an optional attribute for testing')
/
insert into krms_typ_attr_t (TYP_ATTR_ID, SEQ_NO, TYP_ID, ATTR_DEFN_ID) values ('T1001', 2, 'T1005', 'T1002')
/
-- add our campus attribute to CampusAgendaType
insert into krms_attr_defn_t (ATTR_DEFN_ID, NM, NMSPC_CD, LBL, CMPNT_NM, DESC_TXT)
values ('T1003', 'Campus', 'KR-RULE-TEST', 'campus label', null, 'the campus which this agenda is valid for')
/
insert into krms_typ_attr_t (TYP_ATTR_ID, SEQ_NO, TYP_ID, ATTR_DEFN_ID) values ('T1002', 1, 'T1005', 'T1003')
/

--
-- Copyright 2005-2012 The Kuali Foundation
--
--

-- Notification PeopleFlowActionType

insert into krms_typ_t
(typ_id, nm, nmspc_cd, srvc_nm, actv, ver_nbr)
values ('1000', 'Notify PeopleFlow', 'KR-RULE', 'notificationPeopleFlowActionTypeService', 'Y', 1)
/

-- Approval PeopleFlowActionType

insert into krms_typ_t
(typ_id, nm, nmspc_cd, srvc_nm, actv, ver_nbr)
values ('1001', 'Route to PeopleFlow', 'KR-RULE', 'approvalPeopleFlowActionTypeService', 'Y', 1)
/


-- add a PeopleFlow attribute to the PeopleFlow types
insert into krms_attr_defn_t (ATTR_DEFN_ID, NM, NMSPC_CD, LBL, CMPNT_NM, DESC_TXT)
values ('1000', 'peopleFlowId', 'KR-RULE', 'PeopleFlow', null,
'An identifier for a PeopleFlow')
/
insert into krms_typ_attr_t (TYP_ATTR_ID, SEQ_NO, TYP_ID, ATTR_DEFN_ID) values ('1000', 1, '1000', '1000')
/
insert into krms_typ_attr_t (TYP_ATTR_ID, SEQ_NO, TYP_ID, ATTR_DEFN_ID) values ('1001', 1, '1001', '1000')
/


-- General validation rule type
insert into krms_typ_t (typ_id, nm, nmspc_cd, srvc_nm, actv, ver_nbr) values('1002', 'Validation Rule', 'KR-RULE', 'validationRuleTypeService', 'Y', 1)
/
-- General validation action type
insert into krms_typ_t (typ_id, nm, nmspc_cd, srvc_nm, actv, ver_nbr) values('1003', 'Validation Action', 'KR-RULE', 'validationActionTypeService', 'Y', 1)
/
-- Invalid rule
insert into krms_attr_defn_t (attr_defn_id, nm, nmspc_cd, lbl, actv, cmpnt_nm, ver_nbr, desc_txt) values ('1001', 'Invalid Rule', 'KR-RULE', 'Invalid Rule', 'Y', null, 1, 'If true, execute the action')
/
-- Valid rule
insert into krms_attr_defn_t (attr_defn_id, nm, nmspc_cd, lbl, actv, cmpnt_nm, ver_nbr, desc_txt) values ('1002', 'Valid Rule', 'KR-RULE', 'Valid Rule', 'Y', null, 1, 'If false, execute the action')
/
-- General context rule type mapping
insert into krms_cntxt_vld_rule_typ_t (cntxt_vld_rule_id, cntxt_id, rule_typ_id, ver_nbr) values ('T1000', 'CONTEXT1', '1002', 1)
/
-- The General rule subtypes attributes
-- use same sequence number to prove that falling back to natural order when sequences are the same works.
insert into krms_typ_attr_t (typ_attr_id, seq_no, typ_id, attr_defn_id, actv, ver_nbr) values ('1002', 1, '1002', '1001', 'Y', 1)
/
insert into krms_typ_attr_t (typ_attr_id, seq_no, typ_id, attr_defn_id, actv, ver_nbr) values ('1003', 1, '1002', '1002', 'Y', 1)
/
-- warning action
insert into krms_attr_defn_t (attr_defn_id, nm, nmspc_cd, lbl, actv, cmpnt_nm, ver_nbr, desc_txt) values ('1003', 'Warning Action', 'KR-RULE', 'Warning Action', 'Y', null, 1, 'Warning')
/
-- error action
insert into krms_attr_defn_t (attr_defn_id, nm, nmspc_cd, lbl, actv, cmpnt_nm, ver_nbr, desc_txt) values ('1004', 'Error Action', 'KR-RULE', 'Error Action', 'Y', null, 1, 'Error')
/
-- action message
insert into krms_attr_defn_t (attr_defn_id, nm, nmspc_cd, lbl, actv, cmpnt_nm, ver_nbr, desc_txt) values ('1005', 'Action Message', 'KR-RULE', 'Action Message', 'Y', null, 1, 'Message validation action returns')
/
-- Context general validation acton type mapping
insert into krms_cntxt_vld_actn_typ_t (cntxt_vld_actn_id, cntxt_id, actn_typ_id, ver_nbr) values ('T1003', 'CONTEXT1', '1003', 1)
/
-- The General action type attribute
insert into krms_typ_attr_t (typ_attr_id, seq_no, typ_id, attr_defn_id, actv, ver_nbr) values ('1004', 1, '1003', '1003', 'Y', 1)
/
insert into krms_typ_attr_t (typ_attr_id, seq_no, typ_id, attr_defn_id, actv, ver_nbr) values ('1005', 2, '1003', '1004', 'Y', 1)
/
insert into krms_typ_attr_t (typ_attr_id, seq_no, typ_id, attr_defn_id, actv, ver_nbr) values ('1006', 3, '1003', '1005', 'Y', 1)
/


-- change permisison to use new krms test namespace
update krim_perm_attr_data_t set attr_val = 'KR-RULE-TEST' where attr_val = 'KRMS_TEST'
/
update krim_perm_t
   set nmspc_cd = 'KR-RULE-TEST'
 where nm = 'Maintain KRMS Agenda'
   and nmspc_cd = 'KR-RULE'
/

update krim_perm_t
   set nmspc_cd = 'KR-RULE-TEST'
 where nm = 'Maintain KRMS Agenda'
   and nmspc_cd = 'KRMS_TEST'
/

delete from krim_perm_attr_data_t
 where perm_id = (select perm_id from krim_perm_t where nm = 'Maintain KRMS Agenda' and nmspc_cd = 'KRMS_TEST')
/

