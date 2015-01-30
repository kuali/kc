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

-- KULRICE-5931

-- add 'appDocStatus' attr definition
INSERT INTO KRIM_ATTR_DEFN_T VALUES ((select KIM_ATTR_DEFN_ID from (select (max(cast(KIM_ATTR_DEFN_ID as decimal)) + 1) as KIM_ATTR_DEFN_ID from KRIM_ATTR_DEFN_T where KIM_ATTR_DEFN_ID is not NULL and KIM_ATTR_DEFN_ID REGEXP '^[1-9][0-9]*$' and cast(KIM_ATTR_DEFN_ID as decimal) < 10000) as tmptable), uuid(), 1, 'appDocStatus', null, 'Y', 'KR-WKFLW', 'org.kuali.rice.kim.bo.impl.KimAttributes')
/
-- assign it to 'Document Type & Routing Node or State' type
INSERT INTO KRIM_TYP_ATTR_T VALUES ((select KIM_TYP_ATTR_ID from (select (max(cast(KIM_TYP_ATTR_ID as decimal)) + 1) as KIM_TYP_ATTR_ID from KRIM_TYP_ATTR_T where KIM_TYP_ATTR_ID is not NULL and KIM_TYP_ATTR_ID REGEXP '^[1-9][0-9]*$' and cast(KIM_TYP_ATTR_ID as decimal) < 10000) as tmptable), uuid(), 1, 'a', (select kim_typ_id from krim_typ_t where NMSPC_CD='KR-SYS' and NM='Document Type & Routing Node or State'), (select KIM_ATTR_DEFN_ID from KRIM_ATTR_DEFN_T where NMSPC_CD='KR-WKFLW' and NM='appDocStatus'), 'Y')
/

-- create Recall permission template
INSERT INTO KRIM_PERM_TMPL_T VALUES ((select perm_tmpl_id from (select (max(cast(perm_tmpl_id as decimal)) + 1) as perm_tmpl_id from krim_perm_tmpl_t where perm_tmpl_id is not NULL and perm_tmpl_id REGEXP '^[1-9][0-9]*$' and cast(perm_tmpl_id as decimal) < 10000) as tmptable), uuid(), 1, 'KR-WKFLW', 'Recall Document', null, (select kim_typ_id from krim_typ_t where NMSPC_CD='KR-SYS' and NM='Document Type & Routing Node or State'), 'Y')
/
DELIMITER ;
