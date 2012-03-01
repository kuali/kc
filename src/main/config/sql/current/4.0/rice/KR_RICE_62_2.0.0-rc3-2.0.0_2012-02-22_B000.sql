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
-- KULRICE-6799: At one point (2.0-RC4 and before), the DOC_STAT_NM column was incorrectly set to 20 characters
--               the original script (2011-06-17.sql, and the "final" script) has been corrected, but this script has
--               been created in case that script had already been run.  Mysql scripts are unaffected by this, so there is no
--               equivalent mysql script.
--

alter table KREW_DOC_TYP_APP_DOC_STAT_T modify (DOC_STAT_NM varchar2(64))
/