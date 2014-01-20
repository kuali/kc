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
-- KULRICE-9152: PK for krad_msg_t is too long for MySQL 5.1
--

ALTER TABLE KRAD_MSG_T RENAME TO OLD_KRAD_MSG_T
/

CREATE TABLE krad_msg_t
(
    nmspc_cd VARCHAR2(20) NOT NULL,
    cmpnt_cd VARCHAR2(100) NOT NULL,
    msg_key VARCHAR2(100) NOT NULL,
    loc VARCHAR2(80) NOT NULL,
    obj_id VARCHAR2(36) NOT NULL,
    ver_nbr DECIMAL(8) DEFAULT 1 NOT NULL,
    msg_desc VARCHAR2(255),
    txt VARCHAR2(4000)
)
/

INSERT INTO KRAD_MSG_T SELECT * FROM OLD_KRAD_MSG_T
/

-- drop constraints from old table so that they can be created for the new one

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_constraints WHERE constraint_name = 'KRAD_MSG_TP1';
    IF temp > 0 THEN EXECUTE IMMEDIATE
        'ALTER TABLE OLD_KRAD_MSG_T DROP CONSTRAINT KRAD_MSG_TP1';
    END IF;
END;
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_constraints WHERE constraint_name = 'KRAD_MSG_TC1';
    IF temp > 0 THEN EXECUTE IMMEDIATE
        'ALTER TABLE OLD_KRAD_MSG_T DROP CONSTRAINT KRAD_MSG_TC1';
    END IF;
END;
/

DECLARE temp NUMBER;
BEGIN
    SELECT COUNT(*) INTO temp FROM user_constraints WHERE constraint_name = 'KRAD_MSG_TC2';
    IF temp > 0 THEN EXECUTE IMMEDIATE
        'ALTER TABLE OLD_KRAD_MSG_T DROP CONSTRAINT KRAD_MSG_TC2';
    END IF;
END;
/

-- add constraints to new table

ALTER TABLE krad_msg_t ADD CONSTRAINT krad_msg_tp1 PRIMARY KEY (nmspc_cd,cmpnt_cd,msg_key,loc)
/

-- this seems inconsistent, but it aligns this constraint name with our MySQL version
ALTER TABLE krad_msg_t ADD CONSTRAINT krad_msg_tc0 UNIQUE (obj_id)
/

