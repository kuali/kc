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

delete from krcr_parm_t
where nmspc_cd = 'KR-NS'
and cmpnt_cd = 'All'
and parm_nm in ('STRING_TO_DATE_FORMATS', 'STRING_TO_TIMESTAMP_FORMATS', 'TIMESTAMP_TO_STRING_FORMAT_FOR_USER_INTERFACE', 'DATE_TO_STRING_FORMAT_FOR_FILE_NAME', 'TIMESTAMP_TO_STRING_FORMAT_FOR_FILE_NAME')
/
