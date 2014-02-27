DELIMITER /
--
-- Copyright 2005-2014 The Kuali Foundation
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
