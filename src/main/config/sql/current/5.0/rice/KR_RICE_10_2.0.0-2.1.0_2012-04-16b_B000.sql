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


-- KULRICE-6964: Update Rice default From email address

update KRCR_PARM_T set val='rice.test@kuali.org'
where nmspc_cd='KR-WKFLW' and cmpnt_cd='Mailer' and parm_nm='FROM_ADDRESS' and appl_id='KUALI'
/