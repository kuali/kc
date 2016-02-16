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

-- award person total effort
insert into krms_func_t (func_id, nmspc_cd, nm, desc_txt, rtrn_typ, typ_id, actv, ver_nbr)
 values ('KC10013', 'KC-AWARD', 'awardPersonnelTotalEffort','Award total effort rule for award persons.', 'java.lang.Boolean', 'KC10001', 'Y', 1);

insert into krms_func_parm_t (func_parm_id, nm, desc_txt, typ, func_id, seq_no)
values ('KC10020', 'award', 'Award BO', 'org.kuali.kra.award.home.Award', 'KC10013', 1);

insert into krms_func_parm_t (func_parm_id, nm, desc_txt, typ, func_id, seq_no)
values ('KC10021', 'awardPersonnelTotalEffort', 'Award Total Effort', 'java.lang.String', 'KC10013', 2);

insert into krms_term_spec_t (term_spec_id, nm, typ, actv, ver_nbr, desc_txt, nmspc_cd)
values ('KC2090', 'KC10013', 'java.lang.Boolean', 'Y', 1, 'Award effort rule for award persons.', 'KC-AWARD');

insert into krms_term_rslvr_t (term_rslvr_id, nmspc_cd, nm, typ_id, output_term_spec_id, actv, ver_nbr)
values ('KC2086', 'KC-AWARD', 'Award Effort', 'KC1001', 'KC2090', 'Y', 1);

insert into krms_term_rslvr_parm_spec_t (term_rslvr_parm_spec_id, term_rslvr_id, nm, ver_nbr)
values ('KC2070', 'KC2086', 'awardPersonnelTotalEffort', 1);

insert into krms_cntxt_vld_term_spec_t (cntxt_term_spec_prereq_id, cntxt_id, term_spec_id, prereq)
values ('KC1030','KC-AWARD-CONTEXT', 'KC2090','Y');

insert into krms_term_spec_ctgry_t (term_spec_id, ctgry_id)
values ('KC2090', 'KC1010');

-- award person calendar effort
insert into krms_func_t (func_id, nmspc_cd, nm, desc_txt, rtrn_typ, typ_id, actv, ver_nbr)
 values ('KC10014', 'KC-AWARD', 'awardPersonnelCalendarEffort','Award calendar effort rule for award persons.', 'java.lang.Boolean', 'KC10001', 'Y', 1);

insert into krms_func_parm_t (func_parm_id, nm, desc_txt, typ, func_id, seq_no)
values ('KC10022', 'award', 'Award BO', 'org.kuali.kra.award.home.Award', 'KC10014', 1);

insert into krms_func_parm_t (func_parm_id, nm, desc_txt, typ, func_id, seq_no)
values ('KC10023', 'awardPersonnelCalendarEffort', 'Award Total Effort', 'java.lang.String', 'KC10014', 2);

insert into krms_term_spec_t (term_spec_id, nm, typ, actv, ver_nbr, desc_txt, nmspc_cd)
values ('KC2091', 'KC10014', 'java.lang.Boolean', 'Y', 1, 'Award calendar effort rule for award persons.', 'KC-AWARD');

insert into krms_term_rslvr_t (term_rslvr_id, nmspc_cd, nm, typ_id, output_term_spec_id, actv, ver_nbr)
values ('KC2087', 'KC-AWARD', 'Award Calendar Effort', 'KC1001', 'KC2091', 'Y', 1);

insert into krms_term_rslvr_parm_spec_t (term_rslvr_parm_spec_id, term_rslvr_id, nm, ver_nbr)
values ('KC2071', 'KC2087', 'awardPersonnelCalendarEffort', 1);

insert into krms_cntxt_vld_term_spec_t (cntxt_term_spec_prereq_id, cntxt_id, term_spec_id, prereq)
values ('KC1031','KC-AWARD-CONTEXT', 'KC2091','Y');

insert into krms_term_spec_ctgry_t (term_spec_id, ctgry_id)
values ('KC2091', 'KC1010');

-- award comments rule

insert into krms_func_t (func_id, nmspc_cd, nm, desc_txt, rtrn_typ, typ_id, actv, ver_nbr)
 values ('KC10015', 'KC-AWARD', 'awardCommentsRule','Rule to check award comments.', 'java.lang.Boolean', 'KC10001', 'Y', 1);

insert into krms_func_parm_t (func_parm_id, nm, desc_txt, typ, func_id, seq_no)
values ('KC10024', 'award', 'Award BO', 'org.kuali.kra.award.home.Award', 'KC10015', 1);

insert into krms_func_parm_t (func_parm_id, nm, desc_txt, typ, func_id, seq_no)
values ('KC10025', 'awardCommentsRule', 'Award comments rule', 'java.lang.String', 'KC10015', 2);

insert into krms_term_spec_t (term_spec_id, nm, typ, actv, ver_nbr, desc_txt, nmspc_cd)
values ('KC2092', 'KC10015', 'java.lang.Boolean', 'Y', 1, 'Award comments rule.', 'KC-AWARD');

insert into krms_term_rslvr_t (term_rslvr_id, nmspc_cd, nm, typ_id, output_term_spec_id, actv, ver_nbr)
values ('KC2088', 'KC-AWARD', 'Award comments rule', 'KC1001', 'KC2092', 'Y', 1);

insert into krms_term_rslvr_parm_spec_t (term_rslvr_parm_spec_id, term_rslvr_id, nm, ver_nbr)
values ('KC2072', 'KC2088', 'awardCommentsRule', 1);

insert into krms_cntxt_vld_term_spec_t (cntxt_term_spec_prereq_id, cntxt_id, term_spec_id, prereq)
values ('KC1032','KC-AWARD-CONTEXT', 'KC2092','Y');

insert into krms_term_spec_ctgry_t (term_spec_id, ctgry_id)
values ('KC2092', 'KC1010');