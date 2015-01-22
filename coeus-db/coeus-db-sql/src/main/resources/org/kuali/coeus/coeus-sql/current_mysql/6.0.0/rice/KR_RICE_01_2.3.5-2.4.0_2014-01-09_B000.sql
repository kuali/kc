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

-- KULRICE-9198 - krms_attr_defn_t.attr_defn_id is a varchar(255),
--                but referencing columns are a mixture of varchar(40) and varchar(255)
DELIMITER /

ALTER TABLE KRMS_TYP_ATTR_T MODIFY ATTR_DEFN_ID VARCHAR(40)
/
ALTER TABLE KRMS_ATTR_DEFN_T MODIFY ATTR_DEFN_ID VARCHAR(40)
/

DELIMITER ;
