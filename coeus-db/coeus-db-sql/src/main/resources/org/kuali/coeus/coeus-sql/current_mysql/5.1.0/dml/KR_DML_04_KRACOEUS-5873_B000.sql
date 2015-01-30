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
delete from krms_agenda_itm_t where agenda_itm_id like 'T%'
/
delete from krms_agenda_t where agenda_id like 'T%'
/
delete from krms_cntxt_vld_actn_typ_t where cntxt_vld_actn_id like 'T%'
/
delete from krms_actn_t where actn_id like 'T%'
/
delete from krms_cntxt_vld_agenda_typ_t where cntxt_vld_agenda_id like 'T%'
/
delete from krms_cntxt_vld_rule_typ_t where cntxt_vld_rule_id like 'T%'
/
delete from krms_cntxt_vld_term_spec_t where cntxt_term_spec_prereq_id like 'T%'
/
delete from krms_cntxt_attr_t where cntxt_attr_id like 'T%'
/
delete from krms_cntxt_t where typ_id like 'T%'
/
delete from krms_term_rslvr_parm_spec_t where term_rslvr_parm_spec_id like 'T%'
/
delete from krms_term_rslvr_t where term_rslvr_id like 'T%'
/
delete from krms_term_parm_t where term_parm_id like 'T%'
/
delete from krms_term_t where term_id like 'T%'
/
delete from krms_term_spec_t where term_spec_id like 'T%'
/
delete from krms_rule_t where rule_id like 'T%'
/
delete from krms_prop_parm_t where prop_parm_id like 'T%'
/
delete from krms_cmpnd_prop_props_t where cmpnd_prop_id like 'T%'
/
delete from krms_prop_t where prop_id like 'T%'
/
delete from krms_typ_attr_t where typ_attr_id like 'T%'
/
delete from krms_attr_defn_t where attr_defn_id like 'T%'
/
delete from krms_typ_t where typ_id like 'T%'
/
delete from krms_ctgry_t where ctgry_id like 'T%'
/

DELIMITER ;
