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

DECLARE   max_id NUMBER; max_sq NUMBER;
BEGIN
  SELECT max(PROPOSAL_PERSON_ID) INTO max_id FROM PROPOSAL_PERSONS;
  SELECT max(PROPOSAL_PERSON_UNIT_ID) INTO max_sq FROM PROPOSAL_PERSON_UNITS;
  IF max_id is null THEN max_id:=0; END IF;
  IF max_sq is null THEN max_sq:=0; END IF;
  SELECT GREATEST (max_id, max_sq) INTO max_id FROM dual;
  SELECT SEQ_PROPOSAL_PROPOSAL_ID.NEXTVAL INTO max_sq FROM dual;
  IF max_sq < max_id THEN 
   max_sq := max_id - max_sq;
   EXECUTE IMMEDIATE 'ALTER SEQUENCE SEQ_PROPOSAL_PROPOSAL_ID INCREMENT BY ' || max_sq; 
   EXECUTE IMMEDIATE 'SELECT SEQ_PROPOSAL_PROPOSAL_ID.NEXTVAL FROM DUAL' INTO max_id;
   EXECUTE IMMEDIATE 'ALTER SEQUENCE SEQ_PROPOSAL_PROPOSAL_ID INCREMENT BY 1';
  END IF;
END;
/
