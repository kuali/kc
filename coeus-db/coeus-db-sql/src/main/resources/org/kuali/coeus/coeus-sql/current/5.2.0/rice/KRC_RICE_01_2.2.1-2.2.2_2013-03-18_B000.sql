--
-- Kuali Coeus, a comprehensive research administration system for higher education.
-- 
-- Copyright 2005-2015 Kuali, Inc.
-- 
-- This program is free software: you can redistribute it and/or modify
-- it under the terms of the GNU Affero General Public License as
-- published by the Free Software Foundation, either version 3 of the
-- License, or (at your option) any later version.
-- 
-- This program is distributed in the hope that it will be useful,
-- but WITHOUT ANY WARRANTY; without even the implied warranty of
-- MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
-- GNU Affero General Public License for more details.
-- 
-- You should have received a copy of the GNU Affero General Public License
-- along with this program.  If not, see <http://www.gnu.org/licenses/>.
--


--
-- KULRICE-9179: KRAD_MSG_T not in client dataset upgrade scripts
--

DECLARE temp NUMBER;
BEGIN
  select count(*) into temp from user_tables where table_name = upper('krad_msg_t');
    IF temp = 0 THEN EXECUTE IMMEDIATE
        'CREATE TABLE krad_msg_t ( nmspc_cd VARCHAR2(20) NOT NULL,  cmpnt_cd VARCHAR2(100) NOT NULL, msg_key VARCHAR2(100) NOT NULL, loc VARCHAR2(255) NOT NULL, obj_id VARCHAR2(36) NOT NULL, ver_nbr DECIMAL(8) DEFAULT 1 NOT NULL, msg_desc VARCHAR2(255), txt VARCHAR2(4000) )';
    END IF;
END;
/


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

