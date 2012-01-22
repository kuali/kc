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

-- KULRICE-5360 rename KIM entity fields
alter table KRIM_ENTITY_NM_T rename column TITLE_NM to PREFIX_NM
/

alter table KRIM_ENTITY_BIO_T rename column BIRTH_STATE_CD to BIRTH_STATE_PVC_CD
/

alter table KRIM_ENTITY_ADDR_T rename column POSTAL_STATE_CD to STATE_PVC_CD
/
alter table KRIM_ENTITY_ADDR_T rename column CITY_NM to CITY
/

alter table KRIM_PND_NM_MT rename column TITLE_NM to PREFIX_NM
/
alter table KRIM_PND_ADDR_MT rename column POSTAL_STATE_CD to STATE_PVC_CD
/
alter table KRIM_PND_ADDR_MT rename column CITY_NM to CITY
/
