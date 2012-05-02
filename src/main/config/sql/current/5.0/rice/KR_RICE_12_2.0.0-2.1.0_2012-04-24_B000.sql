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

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'KRIM_ROLE_PERM_ID_S';
    IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE KRIM_ROLE_PERM_ID_S'; END IF;
END;
/
CREATE SEQUENCE KRIM_ROLE_PERM_ID_S INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/