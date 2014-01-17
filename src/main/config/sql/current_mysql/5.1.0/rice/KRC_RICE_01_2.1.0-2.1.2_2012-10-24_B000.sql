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

--
-- KULRICE-7378 - MySQL Upgrade script for Rice 2.0 is dropping not null constraints
--

--
-- NOTE - This is only an issue for the MySQL scripts, so that is why there is no corresponding
--        2012-010-24.sql script for Oracle.
--

ALTER TABLE KRSB_MSG_QUE_T MODIFY APPL_ID VARCHAR(255) NOT NULL
/
DELIMITER ;

