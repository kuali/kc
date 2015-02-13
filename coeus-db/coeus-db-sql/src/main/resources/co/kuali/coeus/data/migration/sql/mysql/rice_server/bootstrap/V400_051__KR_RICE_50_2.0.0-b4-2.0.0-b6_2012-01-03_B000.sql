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

INSERT INTO KRCR_NMSPC_T VALUES ('KR-KRAD', uuid(), 1, 'Kuali Rapid Application Development', 'Y', 'RICE')
/

INSERT INTO KRIM_ATTR_DEFN_T VALUES ((select KIM_ATTR_DEFN_ID from (select (max(cast(KIM_ATTR_DEFN_ID as decimal)) + 1) as KIM_ATTR_DEFN_ID from KRIM_ATTR_DEFN_T where KIM_ATTR_DEFN_ID is not NULL and cast(KIM_ATTR_DEFN_ID as decimal) < 10000) as tmptable), uuid(), 1, 'viewId', null, 'Y', 'KR-KRAD', 'org.kuali.rice.kim.bo.impl.KimAttributes')
/

INSERT INTO KRIM_ATTR_DEFN_T VALUES ((select KIM_ATTR_DEFN_ID from (select (max(cast(KIM_ATTR_DEFN_ID as decimal)) + 1) as KIM_ATTR_DEFN_ID from KRIM_ATTR_DEFN_T where KIM_ATTR_DEFN_ID is not NULL and cast(KIM_ATTR_DEFN_ID as decimal) < 10000) as tmptable), uuid(), 1, 'actionEvent', null, 'Y', 'KR-KRAD', 'org.kuali.rice.kim.bo.impl.KimAttributes')
/

INSERT INTO KRIM_ATTR_DEFN_T VALUES ((select KIM_ATTR_DEFN_ID from (select (max(cast(KIM_ATTR_DEFN_ID as decimal)) + 1) as KIM_ATTR_DEFN_ID from KRIM_ATTR_DEFN_T where KIM_ATTR_DEFN_ID is not NULL and cast(KIM_ATTR_DEFN_ID as decimal) < 10000) as tmptable), uuid(), 1, 'collectionPropertyName', null, 'Y', 'KR-KRAD', 'org.kuali.rice.kim.bo.impl.KimAttributes')
/

INSERT INTO KRIM_ATTR_DEFN_T VALUES ((select KIM_ATTR_DEFN_ID from (select (max(cast(KIM_ATTR_DEFN_ID as decimal)) + 1) as KIM_ATTR_DEFN_ID from KRIM_ATTR_DEFN_T where KIM_ATTR_DEFN_ID is not NULL and cast(KIM_ATTR_DEFN_ID as decimal) < 10000) as tmptable), uuid(), 1, 'fieldId', null, 'Y', 'KR-KRAD', 'org.kuali.rice.kim.bo.impl.KimAttributes')
/

INSERT INTO KRIM_ATTR_DEFN_T VALUES ((select KIM_ATTR_DEFN_ID from (select (max(cast(KIM_ATTR_DEFN_ID as decimal)) + 1) as KIM_ATTR_DEFN_ID from KRIM_ATTR_DEFN_T where KIM_ATTR_DEFN_ID is not NULL and cast(KIM_ATTR_DEFN_ID as decimal) < 10000) as tmptable), uuid(), 1, 'groupId', null, 'Y', 'KR-KRAD', 'org.kuali.rice.kim.bo.impl.KimAttributes')
/

INSERT INTO KRIM_ATTR_DEFN_T VALUES ((select KIM_ATTR_DEFN_ID from (select (max(cast(KIM_ATTR_DEFN_ID as decimal)) + 1) as KIM_ATTR_DEFN_ID from KRIM_ATTR_DEFN_T where KIM_ATTR_DEFN_ID is not NULL and cast(KIM_ATTR_DEFN_ID as decimal) < 10000) as tmptable), uuid(), 1, 'widgetId', null, 'Y', 'KR-KRAD', 'org.kuali.rice.kim.bo.impl.KimAttributes')
/

INSERT INTO KRIM_ATTR_DEFN_T VALUES ((select KIM_ATTR_DEFN_ID from (select (max(cast(KIM_ATTR_DEFN_ID as decimal)) + 1) as KIM_ATTR_DEFN_ID from KRIM_ATTR_DEFN_T where KIM_ATTR_DEFN_ID is not NULL and cast(KIM_ATTR_DEFN_ID as decimal) < 10000) as tmptable), uuid(), 1, 'actionId', null, 'Y', 'KR-KRAD', 'org.kuali.rice.kim.bo.impl.KimAttributes')
/

INSERT INTO KRIM_TYP_T VALUES ((select KIM_TYP_ID from (select (max(cast(KIM_TYP_ID as decimal)) + 1) as KIM_TYP_ID from KRIM_TYP_T where cast(KIM_TYP_ID as decimal) < 10000) as tmptable), uuid(), 1, 'View', 'viewPermissionTypeService', 'Y', 'KR-KRAD')
/

INSERT INTO KRIM_TYP_T VALUES ((select KIM_TYP_ID from (select (max(cast(KIM_TYP_ID as decimal)) + 1) as KIM_TYP_ID from KRIM_TYP_T where cast(KIM_TYP_ID as decimal) < 10000) as tmptable), uuid(), 1, 'View Edit Mode', 'viewEditModePermissionTypeService', 'Y', 'KR-KRAD')
/

INSERT INTO KRIM_TYP_T VALUES ((select KIM_TYP_ID from (select (max(cast(KIM_TYP_ID as decimal)) + 1) as KIM_TYP_ID from KRIM_TYP_T where cast(KIM_TYP_ID as decimal) < 10000) as tmptable), uuid(), 1, 'View Field', 'viewFieldPermissionTypeService', 'Y', 'KR-KRAD')
/

INSERT INTO KRIM_TYP_T VALUES ((select KIM_TYP_ID from (select (max(cast(KIM_TYP_ID as decimal)) + 1) as KIM_TYP_ID from KRIM_TYP_T where cast(KIM_TYP_ID as decimal) < 10000) as tmptable), uuid(), 1, 'View Group', 'viewGroupPermissionTypeService', 'Y', 'KR-KRAD')
/

INSERT INTO KRIM_TYP_T VALUES ((select KIM_TYP_ID from (select (max(cast(KIM_TYP_ID as decimal)) + 1) as KIM_TYP_ID from KRIM_TYP_T where cast(KIM_TYP_ID as decimal) < 10000) as tmptable), uuid(), 1, 'View Widget', 'viewWidgetPermissionTypeService', 'Y', 'KR-KRAD')
/

INSERT INTO KRIM_TYP_T VALUES ((select KIM_TYP_ID from (select (max(cast(KIM_TYP_ID as decimal)) + 1) as KIM_TYP_ID from KRIM_TYP_T where cast(KIM_TYP_ID as decimal) < 10000) as tmptable), uuid(), 1, 'View Action', 'viewActionPermissionTypeService', 'Y', 'KR-KRAD')
/

INSERT INTO KRIM_TYP_T VALUES ((select KIM_TYP_ID from (select (max(cast(KIM_TYP_ID as decimal)) + 1) as KIM_TYP_ID from KRIM_TYP_T where cast(KIM_TYP_ID as decimal) < 10000) as tmptable), uuid(), 1, 'View Line Field', 'viewLineFieldPermissionTypeService', 'Y', 'KR-KRAD')
/

INSERT INTO KRIM_TYP_T VALUES ((select KIM_TYP_ID from (select (max(cast(KIM_TYP_ID as decimal)) + 1) as KIM_TYP_ID from KRIM_TYP_T where cast(KIM_TYP_ID as decimal) < 10000) as tmptable), uuid(), 1, 'View Line Action', 'viewLineActionPermissionTypeService', 'Y', 'KR-KRAD')
/

INSERT INTO KRIM_TYP_ATTR_T VALUES ((select KIM_TYP_ATTR_ID from (select (max(cast(KIM_TYP_ATTR_ID as decimal)) + 1) as KIM_TYP_ATTR_ID from KRIM_TYP_ATTR_T where KIM_TYP_ATTR_ID is not NULL and cast(KIM_TYP_ATTR_ID as decimal) < 10000) as tmptable), uuid(), 1, 'a', (select kim_typ_id from krim_typ_t where NMSPC_CD='KR-KRAD' and NM='View'), (select KIM_ATTR_DEFN_ID from KRIM_ATTR_DEFN_T where NMSPC_CD='KR-KRAD' and NM='viewId'), 'Y')
/

INSERT INTO KRIM_TYP_ATTR_T VALUES ((select KIM_TYP_ATTR_ID from (select (max(cast(KIM_TYP_ATTR_ID as decimal)) + 1) as KIM_TYP_ATTR_ID from KRIM_TYP_ATTR_T where KIM_TYP_ATTR_ID is not NULL and cast(KIM_TYP_ATTR_ID as decimal) < 10000) as tmptable), uuid(), 1, 'a', (select kim_typ_id from krim_typ_t where NMSPC_CD='KR-KRAD' and NM='View Edit Mode'), (select KIM_ATTR_DEFN_ID from KRIM_ATTR_DEFN_T where NMSPC_CD='KR-KRAD' and NM='viewId'), 'Y')
/

INSERT INTO KRIM_TYP_ATTR_T VALUES ((select KIM_TYP_ATTR_ID from (select (max(cast(KIM_TYP_ATTR_ID as decimal)) + 1) as KIM_TYP_ATTR_ID from KRIM_TYP_ATTR_T where KIM_TYP_ATTR_ID is not NULL and cast(KIM_TYP_ATTR_ID as decimal) < 10000) as tmptable), uuid(), 1, 'b', (select kim_typ_id from krim_typ_t where NMSPC_CD='KR-KRAD' and NM='View Edit Mode'), '10', 'Y')
/

INSERT INTO KRIM_TYP_ATTR_T VALUES ((select KIM_TYP_ATTR_ID from (select (max(cast(KIM_TYP_ATTR_ID as decimal)) + 1) as KIM_TYP_ATTR_ID from KRIM_TYP_ATTR_T where KIM_TYP_ATTR_ID is not NULL and cast(KIM_TYP_ATTR_ID as decimal) < 10000) as tmptable), uuid(), 1, 'a', (select kim_typ_id from krim_typ_t where NMSPC_CD='KR-KRAD' and NM='View Field'), (select KIM_ATTR_DEFN_ID from KRIM_ATTR_DEFN_T where NMSPC_CD='KR-KRAD' and NM='viewId'), 'Y')
/

INSERT INTO KRIM_TYP_ATTR_T VALUES ((select KIM_TYP_ATTR_ID from (select (max(cast(KIM_TYP_ATTR_ID as decimal)) + 1) as KIM_TYP_ATTR_ID from KRIM_TYP_ATTR_T where KIM_TYP_ATTR_ID is not NULL and cast(KIM_TYP_ATTR_ID as decimal) < 10000) as tmptable), uuid(), 1, 'b', (select kim_typ_id from krim_typ_t where NMSPC_CD='KR-KRAD' and NM='View Field'), (select KIM_ATTR_DEFN_ID from KRIM_ATTR_DEFN_T where NMSPC_CD='KR-KRAD' and NM='fieldId'), 'Y')
/

INSERT INTO KRIM_TYP_ATTR_T VALUES ((select KIM_TYP_ATTR_ID from (select (max(cast(KIM_TYP_ATTR_ID as decimal)) + 1) as KIM_TYP_ATTR_ID from KRIM_TYP_ATTR_T where KIM_TYP_ATTR_ID is not NULL and cast(KIM_TYP_ATTR_ID as decimal) < 10000) as tmptable), uuid(), 1, 'c', (select kim_typ_id from krim_typ_t where NMSPC_CD='KR-KRAD' and NM='View Field'), '6', 'Y')
/

INSERT INTO KRIM_TYP_ATTR_T VALUES ((select KIM_TYP_ATTR_ID from (select (max(cast(KIM_TYP_ATTR_ID as decimal)) + 1) as KIM_TYP_ATTR_ID from KRIM_TYP_ATTR_T where KIM_TYP_ATTR_ID is not NULL and cast(KIM_TYP_ATTR_ID as decimal) < 10000) as tmptable), uuid(), 1, 'a', (select kim_typ_id from krim_typ_t where NMSPC_CD='KR-KRAD' and NM='View Group'), (select KIM_ATTR_DEFN_ID from KRIM_ATTR_DEFN_T where NMSPC_CD='KR-KRAD' and NM='viewId'), 'Y')
/

INSERT INTO KRIM_TYP_ATTR_T VALUES ((select KIM_TYP_ATTR_ID from (select (max(cast(KIM_TYP_ATTR_ID as decimal)) + 1) as KIM_TYP_ATTR_ID from KRIM_TYP_ATTR_T where KIM_TYP_ATTR_ID is not NULL and cast(KIM_TYP_ATTR_ID as decimal) < 10000) as tmptable), uuid(), 1, 'b', (select kim_typ_id from krim_typ_t where NMSPC_CD='KR-KRAD' and NM='View Group'), (select KIM_ATTR_DEFN_ID from KRIM_ATTR_DEFN_T where NMSPC_CD='KR-KRAD' and NM='groupId'), 'Y')
/

INSERT INTO KRIM_TYP_ATTR_T VALUES ((select KIM_TYP_ATTR_ID from (select (max(cast(KIM_TYP_ATTR_ID as decimal)) + 1) as KIM_TYP_ATTR_ID from KRIM_TYP_ATTR_T where KIM_TYP_ATTR_ID is not NULL and cast(KIM_TYP_ATTR_ID as decimal) < 10000) as tmptable), uuid(), 1, 'c', (select kim_typ_id from krim_typ_t where NMSPC_CD='KR-KRAD' and NM='View Group'), (select KIM_ATTR_DEFN_ID from KRIM_ATTR_DEFN_T where NMSPC_CD='KR-KRAD' and NM='fieldId'), 'Y')
/

INSERT INTO KRIM_TYP_ATTR_T VALUES ((select KIM_TYP_ATTR_ID from (select (max(cast(KIM_TYP_ATTR_ID as decimal)) + 1) as KIM_TYP_ATTR_ID from KRIM_TYP_ATTR_T where KIM_TYP_ATTR_ID is not NULL and cast(KIM_TYP_ATTR_ID as decimal) < 10000) as tmptable), uuid(), 1, 'a', (select kim_typ_id from krim_typ_t where NMSPC_CD='KR-KRAD' and NM='View Widget'), (select KIM_ATTR_DEFN_ID from KRIM_ATTR_DEFN_T where NMSPC_CD='KR-KRAD' and NM='viewId'), 'Y')
/

INSERT INTO KRIM_TYP_ATTR_T VALUES ((select KIM_TYP_ATTR_ID from (select (max(cast(KIM_TYP_ATTR_ID as decimal)) + 1) as KIM_TYP_ATTR_ID from KRIM_TYP_ATTR_T where KIM_TYP_ATTR_ID is not NULL and cast(KIM_TYP_ATTR_ID as decimal) < 10000) as tmptable), uuid(), 1, 'b', (select kim_typ_id from krim_typ_t where NMSPC_CD='KR-KRAD' and NM='View Widget'), (select KIM_ATTR_DEFN_ID from KRIM_ATTR_DEFN_T where NMSPC_CD='KR-KRAD' and NM='widgetId'), 'Y')
/

INSERT INTO KRIM_TYP_ATTR_T VALUES ((select KIM_TYP_ATTR_ID from (select (max(cast(KIM_TYP_ATTR_ID as decimal)) + 1) as KIM_TYP_ATTR_ID from KRIM_TYP_ATTR_T where KIM_TYP_ATTR_ID is not NULL and cast(KIM_TYP_ATTR_ID as decimal) < 10000) as tmptable), uuid(), 1, 'a', (select kim_typ_id from krim_typ_t where NMSPC_CD='KR-KRAD' and NM='View Action'), (select KIM_ATTR_DEFN_ID from KRIM_ATTR_DEFN_T where NMSPC_CD='KR-KRAD' and NM='viewId'), 'Y')
/

INSERT INTO KRIM_TYP_ATTR_T VALUES ((select KIM_TYP_ATTR_ID from (select (max(cast(KIM_TYP_ATTR_ID as decimal)) + 1) as KIM_TYP_ATTR_ID from KRIM_TYP_ATTR_T where KIM_TYP_ATTR_ID is not NULL and cast(KIM_TYP_ATTR_ID as decimal) < 10000) as tmptable), uuid(), 1, 'b', (select kim_typ_id from krim_typ_t where NMSPC_CD='KR-KRAD' and NM='View Action'), (select KIM_ATTR_DEFN_ID from KRIM_ATTR_DEFN_T where NMSPC_CD='KR-KRAD' and NM='actionId'), 'Y')
/

INSERT INTO KRIM_TYP_ATTR_T VALUES ((select KIM_TYP_ATTR_ID from (select (max(cast(KIM_TYP_ATTR_ID as decimal)) + 1) as KIM_TYP_ATTR_ID from KRIM_TYP_ATTR_T where KIM_TYP_ATTR_ID is not NULL and cast(KIM_TYP_ATTR_ID as decimal) < 10000) as tmptable), uuid(), 1, 'c', (select kim_typ_id from krim_typ_t where NMSPC_CD='KR-KRAD' and NM='View Action'), (select KIM_ATTR_DEFN_ID from KRIM_ATTR_DEFN_T where NMSPC_CD='KR-KRAD' and NM='actionEvent'), 'Y')
/

INSERT INTO KRIM_TYP_ATTR_T VALUES ((select KIM_TYP_ATTR_ID from (select (max(cast(KIM_TYP_ATTR_ID as decimal)) + 1) as KIM_TYP_ATTR_ID from KRIM_TYP_ATTR_T where KIM_TYP_ATTR_ID is not NULL and cast(KIM_TYP_ATTR_ID as decimal) < 10000) as tmptable), uuid(), 1, 'a', (select kim_typ_id from krim_typ_t where NMSPC_CD='KR-KRAD' and NM='View Line Field'), (select KIM_ATTR_DEFN_ID from KRIM_ATTR_DEFN_T where NMSPC_CD='KR-KRAD' and NM='viewId'), 'Y')
/

INSERT INTO KRIM_TYP_ATTR_T VALUES ((select KIM_TYP_ATTR_ID from (select (max(cast(KIM_TYP_ATTR_ID as decimal)) + 1) as KIM_TYP_ATTR_ID from KRIM_TYP_ATTR_T where KIM_TYP_ATTR_ID is not NULL and cast(KIM_TYP_ATTR_ID as decimal) < 10000) as tmptable), uuid(), 1, 'b', (select kim_typ_id from krim_typ_t where NMSPC_CD='KR-KRAD' and NM='View Line Field'), (select KIM_ATTR_DEFN_ID from KRIM_ATTR_DEFN_T where NMSPC_CD='KR-KRAD' and NM='groupId'), 'Y')
/

INSERT INTO KRIM_TYP_ATTR_T VALUES ((select KIM_TYP_ATTR_ID from (select (max(cast(KIM_TYP_ATTR_ID as decimal)) + 1) as KIM_TYP_ATTR_ID from KRIM_TYP_ATTR_T where KIM_TYP_ATTR_ID is not NULL and cast(KIM_TYP_ATTR_ID as decimal) < 10000) as tmptable), uuid(), 1, 'c', (select kim_typ_id from krim_typ_t where NMSPC_CD='KR-KRAD' and NM='View Line Field'), (select KIM_ATTR_DEFN_ID from KRIM_ATTR_DEFN_T where NMSPC_CD='KR-KRAD' and NM='collectionPropertyName'), 'Y')
/

INSERT INTO KRIM_TYP_ATTR_T VALUES ((select KIM_TYP_ATTR_ID from (select (max(cast(KIM_TYP_ATTR_ID as decimal)) + 1) as KIM_TYP_ATTR_ID from KRIM_TYP_ATTR_T where KIM_TYP_ATTR_ID is not NULL and cast(KIM_TYP_ATTR_ID as decimal) < 10000) as tmptable), uuid(), 1, 'd', (select kim_typ_id from krim_typ_t where NMSPC_CD='KR-KRAD' and NM='View Line Field'), (select KIM_ATTR_DEFN_ID from KRIM_ATTR_DEFN_T where NMSPC_CD='KR-KRAD' and NM='fieldId'), 'Y')
/

INSERT INTO KRIM_TYP_ATTR_T VALUES ((select KIM_TYP_ATTR_ID from (select (max(cast(KIM_TYP_ATTR_ID as decimal)) + 1) as KIM_TYP_ATTR_ID from KRIM_TYP_ATTR_T where KIM_TYP_ATTR_ID is not NULL and cast(KIM_TYP_ATTR_ID as decimal) < 10000) as tmptable), uuid(), 1, 'e', (select kim_typ_id from krim_typ_t where NMSPC_CD='KR-KRAD' and NM='View Line Field'), '6', 'Y')
/

INSERT INTO KRIM_TYP_ATTR_T VALUES ((select KIM_TYP_ATTR_ID from (select (max(cast(KIM_TYP_ATTR_ID as decimal)) + 1) as KIM_TYP_ATTR_ID from KRIM_TYP_ATTR_T where KIM_TYP_ATTR_ID is not NULL and cast(KIM_TYP_ATTR_ID as decimal) < 10000) as tmptable), uuid(), 1, 'a', (select kim_typ_id from krim_typ_t where NMSPC_CD='KR-KRAD' and NM='View Line Action'), (select KIM_ATTR_DEFN_ID from KRIM_ATTR_DEFN_T where NMSPC_CD='KR-KRAD' and NM='viewId'), 'Y')
/

INSERT INTO KRIM_TYP_ATTR_T VALUES ((select KIM_TYP_ATTR_ID from (select (max(cast(KIM_TYP_ATTR_ID as decimal)) + 1) as KIM_TYP_ATTR_ID from KRIM_TYP_ATTR_T where KIM_TYP_ATTR_ID is not NULL and cast(KIM_TYP_ATTR_ID as decimal) < 10000) as tmptable), uuid(), 1, 'b', (select kim_typ_id from krim_typ_t where NMSPC_CD='KR-KRAD' and NM='View Line Action'), (select KIM_ATTR_DEFN_ID from KRIM_ATTR_DEFN_T where NMSPC_CD='KR-KRAD' and NM='groupId'), 'Y')
/

INSERT INTO KRIM_TYP_ATTR_T VALUES ((select KIM_TYP_ATTR_ID from (select (max(cast(KIM_TYP_ATTR_ID as decimal)) + 1) as KIM_TYP_ATTR_ID from KRIM_TYP_ATTR_T where KIM_TYP_ATTR_ID is not NULL and cast(KIM_TYP_ATTR_ID as decimal) < 10000) as tmptable), uuid(), 1, 'c', (select kim_typ_id from krim_typ_t where NMSPC_CD='KR-KRAD' and NM='View Line Action'), (select KIM_ATTR_DEFN_ID from KRIM_ATTR_DEFN_T where NMSPC_CD='KR-KRAD' and NM='collectionPropertyName'), 'Y')
/

INSERT INTO KRIM_TYP_ATTR_T VALUES ((select KIM_TYP_ATTR_ID from (select (max(cast(KIM_TYP_ATTR_ID as decimal)) + 1) as KIM_TYP_ATTR_ID from KRIM_TYP_ATTR_T where KIM_TYP_ATTR_ID is not NULL and cast(KIM_TYP_ATTR_ID as decimal) < 10000) as tmptable), uuid(), 1, 'd', (select kim_typ_id from krim_typ_t where NMSPC_CD='KR-KRAD' and NM='View Line Action'), (select KIM_ATTR_DEFN_ID from KRIM_ATTR_DEFN_T where NMSPC_CD='KR-KRAD' and NM='actionId'), 'Y')
/

INSERT INTO KRIM_TYP_ATTR_T VALUES ((select KIM_TYP_ATTR_ID from (select (max(cast(KIM_TYP_ATTR_ID as decimal)) + 1) as KIM_TYP_ATTR_ID from KRIM_TYP_ATTR_T where KIM_TYP_ATTR_ID is not NULL and cast(KIM_TYP_ATTR_ID as decimal) < 10000) as tmptable), uuid(), 1, 'e', (select kim_typ_id from krim_typ_t where NMSPC_CD='KR-KRAD' and NM='View Line Action'), (select KIM_ATTR_DEFN_ID from KRIM_ATTR_DEFN_T where NMSPC_CD='KR-KRAD' and NM='actionEvent'), 'Y')
/

INSERT INTO KRIM_PERM_TMPL_T VALUES ((select perm_tmpl_id from (select (max(cast(perm_tmpl_id as decimal)) + 1) as perm_tmpl_id from krim_perm_tmpl_t where perm_tmpl_id is not NULL and cast(perm_tmpl_id as decimal) < 10000) as tmptable), uuid(), 1, 'KR-KRAD', 'Open View', null, (select kim_typ_id from krim_typ_t where NMSPC_CD='KR-KRAD' and NM='View'), 'Y')
/

INSERT INTO KRIM_PERM_TMPL_T VALUES ((select perm_tmpl_id from (select (max(cast(perm_tmpl_id as decimal)) + 1) as perm_tmpl_id from krim_perm_tmpl_t where perm_tmpl_id is not NULL and cast(perm_tmpl_id as decimal) < 10000) as tmptable), uuid(), 1, 'KR-KRAD', 'Edit View', null, (select kim_typ_id from krim_typ_t where NMSPC_CD='KR-KRAD' and NM='View'), 'Y')
/

INSERT INTO KRIM_PERM_TMPL_T VALUES ((select perm_tmpl_id from (select (max(cast(perm_tmpl_id as decimal)) + 1) as perm_tmpl_id from krim_perm_tmpl_t where perm_tmpl_id is not NULL and cast(perm_tmpl_id as decimal) < 10000) as tmptable), uuid(), 1, 'KR-KRAD', 'Use View', null, (select kim_typ_id from krim_typ_t where NMSPC_CD='KR-KRAD' and NM='View Edit Mode'), 'Y')
/

INSERT INTO KRIM_PERM_TMPL_T VALUES ((select perm_tmpl_id from (select (max(cast(perm_tmpl_id as decimal)) + 1) as perm_tmpl_id from krim_perm_tmpl_t where perm_tmpl_id is not NULL and cast(perm_tmpl_id as decimal) < 10000) as tmptable), uuid(), 1, 'KR-KRAD', 'View Field', null, (select kim_typ_id from krim_typ_t where NMSPC_CD='KR-KRAD' and NM='View Field'), 'Y')
/

INSERT INTO KRIM_PERM_TMPL_T VALUES ((select perm_tmpl_id from (select (max(cast(perm_tmpl_id as decimal)) + 1) as perm_tmpl_id from krim_perm_tmpl_t where perm_tmpl_id is not NULL and cast(perm_tmpl_id as decimal) < 10000) as tmptable), uuid(), 1, 'KR-KRAD', 'Edit Field', null, (select kim_typ_id from krim_typ_t where NMSPC_CD='KR-KRAD' and NM='View Field'), 'Y')
/

INSERT INTO KRIM_PERM_TMPL_T VALUES ((select perm_tmpl_id from (select (max(cast(perm_tmpl_id as decimal)) + 1) as perm_tmpl_id from krim_perm_tmpl_t where perm_tmpl_id is not NULL and cast(perm_tmpl_id as decimal) < 10000) as tmptable), uuid(), 1, 'KR-KRAD', 'View Group', null, (select kim_typ_id from krim_typ_t where NMSPC_CD='KR-KRAD' and NM='View Group'), 'Y')
/

INSERT INTO KRIM_PERM_TMPL_T VALUES ((select perm_tmpl_id from (select (max(cast(perm_tmpl_id as decimal)) + 1) as perm_tmpl_id from krim_perm_tmpl_t where perm_tmpl_id is not NULL and cast(perm_tmpl_id as decimal) < 10000) as tmptable), uuid(), 1, 'KR-KRAD', 'Edit Group', null, (select kim_typ_id from krim_typ_t where NMSPC_CD='KR-KRAD' and NM='View Group'), 'Y')
/

INSERT INTO KRIM_PERM_TMPL_T VALUES ((select perm_tmpl_id from (select (max(cast(perm_tmpl_id as decimal)) + 1) as perm_tmpl_id from krim_perm_tmpl_t where perm_tmpl_id is not NULL and cast(perm_tmpl_id as decimal) < 10000) as tmptable), uuid(), 1, 'KR-KRAD', 'View Widget', null, (select kim_typ_id from krim_typ_t where NMSPC_CD='KR-KRAD' and NM='View Widget'), 'Y')
/

INSERT INTO KRIM_PERM_TMPL_T VALUES ((select perm_tmpl_id from (select (max(cast(perm_tmpl_id as decimal)) + 1) as perm_tmpl_id from krim_perm_tmpl_t where perm_tmpl_id is not NULL and cast(perm_tmpl_id as decimal) < 10000) as tmptable), uuid(), 1, 'KR-KRAD', 'Edit Widget', null, (select kim_typ_id from krim_typ_t where NMSPC_CD='KR-KRAD' and NM='View Widget'), 'Y')
/

INSERT INTO KRIM_PERM_TMPL_T VALUES ((select perm_tmpl_id from (select (max(cast(perm_tmpl_id as decimal)) + 1) as perm_tmpl_id from krim_perm_tmpl_t where perm_tmpl_id is not NULL and cast(perm_tmpl_id as decimal) < 10000) as tmptable), uuid(), 1, 'KR-KRAD', 'Perform Action', null, (select kim_typ_id from krim_typ_t where NMSPC_CD='KR-KRAD' and NM='View Action'), 'Y')
/

INSERT INTO KRIM_PERM_TMPL_T VALUES ((select perm_tmpl_id from (select (max(cast(perm_tmpl_id as decimal)) + 1) as perm_tmpl_id from krim_perm_tmpl_t where perm_tmpl_id is not NULL and cast(perm_tmpl_id as decimal) < 10000) as tmptable), uuid(), 1, 'KR-KRAD', 'View Line', null, (select kim_typ_id from krim_typ_t where NMSPC_CD='KR-KRAD' and NM='View Group'), 'Y')
/

INSERT INTO KRIM_PERM_TMPL_T VALUES ((select perm_tmpl_id from (select (max(cast(perm_tmpl_id as decimal)) + 1) as perm_tmpl_id from krim_perm_tmpl_t where perm_tmpl_id is not NULL and cast(perm_tmpl_id as decimal) < 10000) as tmptable), uuid(), 1, 'KR-KRAD', 'Edit Line', null, (select kim_typ_id from krim_typ_t where NMSPC_CD='KR-KRAD' and NM='View Group'), 'Y')
/

INSERT INTO KRIM_PERM_TMPL_T VALUES ((select perm_tmpl_id from (select (max(cast(perm_tmpl_id as decimal)) + 1) as perm_tmpl_id from krim_perm_tmpl_t where perm_tmpl_id is not NULL and cast(perm_tmpl_id as decimal) < 10000) as tmptable), uuid(), 1, 'KR-KRAD', 'View Line Field', null, (select kim_typ_id from krim_typ_t where NMSPC_CD='KR-KRAD' and NM='View Line Field'), 'Y')
/

INSERT INTO KRIM_PERM_TMPL_T VALUES ((select perm_tmpl_id from (select (max(cast(perm_tmpl_id as decimal)) + 1) as perm_tmpl_id from krim_perm_tmpl_t where perm_tmpl_id is not NULL and cast(perm_tmpl_id as decimal) < 10000) as tmptable), uuid(), 1, 'KR-KRAD', 'Edit Line Field', null, (select kim_typ_id from krim_typ_t where NMSPC_CD='KR-KRAD' and NM='View Line Field'), 'Y')
/

INSERT INTO KRIM_PERM_TMPL_T VALUES ((select perm_tmpl_id from (select (max(cast(perm_tmpl_id as decimal)) + 1) as perm_tmpl_id from krim_perm_tmpl_t where perm_tmpl_id is not NULL and cast(perm_tmpl_id as decimal) < 10000) as tmptable), uuid(), 1, 'KR-KRAD', 'Perform Line Action', null, (select kim_typ_id from krim_typ_t where NMSPC_CD='KR-KRAD' and NM='View Line Action'), 'Y')
/
DELIMITER ;
