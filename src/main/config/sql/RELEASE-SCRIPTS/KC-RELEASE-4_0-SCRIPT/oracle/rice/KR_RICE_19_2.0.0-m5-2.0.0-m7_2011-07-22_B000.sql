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

-- Oracle sql for KULRICE-5419:
alter table krms_cntxt_t add desc_txt varchar2(255) default null
/
alter table krms_term_spec_t add desc_txt varchar2(255) default null
/
alter table krms_term_t add desc_txt varchar2(255) default null
/
alter table krms_attr_defn_t add desc_txt varchar2(255) default null
/
