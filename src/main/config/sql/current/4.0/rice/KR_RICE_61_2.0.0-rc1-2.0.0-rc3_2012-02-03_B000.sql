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

-- KULRICE-6630: Add SQL scripts for missing data in master database

insert into krew_typ_t values ('1', 'Sample Type', 'KR-SAP', 'sampleAppPeopleFlowTypeService', 'Y', 1)
/
insert into krew_attr_defn_t values ('1', 'number', 'KR-SAP', 'Travel Number', 'Y', 'edu.sampleu.travel.bo.TravelAccount', 1, null)
/
insert into krew_attr_defn_t values ('2', 'id', 'KR-SAP', null, 'Y', 'edu.sampleu.travel.bo.FiscalOfficer', 1, null)
/
insert into krew_typ_attr_t values ('1', 1, '1', '1', 'Y', 1)
/
insert into krew_typ_attr_t values ('2', 2, '1', '2', 'Y', 1)
/

