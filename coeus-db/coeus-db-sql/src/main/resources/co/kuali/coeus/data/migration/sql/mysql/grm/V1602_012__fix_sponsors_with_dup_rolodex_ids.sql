--
-- Kuali Coeus, a comprehensive research administration system for higher education.
--
-- Copyright 2005-2016 Kuali, Inc.
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

drop procedure if exists duplicate_sponsor_rolodex_id;

DELIMITER /
CREATE PROCEDURE duplicate_sponsor_rolodex_id()
  BEGIN
    DECLARE DONE INT DEFAULT FALSE;
    DECLARE rolodexId decimal(6,0);
    DECLARE sponsorCode char(6);

    DECLARE CUR CURSOR FOR select sponsor_code, sponsor.rolodex_id from sponsor INNER JOIN (select rolodex_id, count(rolodex_id) from sponsor group by rolodex_id having count(rolodex_id) > 1) dup ON sponsor.rolodex_id = dup.rolodex_id;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET DONE = TRUE;

    OPEN CUR;

    read_loop: LOOP
      FETCH CUR INTO sponsorCode, rolodexId;
      IF DONE THEN
        LEAVE read_loop;
      END IF;

    insert into seq_rolodex_id values (NULL);
    insert into rolodex (ROLODEX_ID, LAST_NAME, FIRST_NAME, MIDDLE_NAME, SUFFIX, PREFIX, TITLE, ORGANIZATION, ADDRESS_LINE_1, ADDRESS_LINE_2, ADDRESS_LINE_3, FAX_NUMBER, EMAIL_ADDRESS, CITY, COUNTY, STATE, POSTAL_CODE, COMMENTS, PHONE_NUMBER, COUNTRY_CODE, SPONSOR_CODE, OWNED_BY_UNIT, SPONSOR_ADDRESS_FLAG, DELETE_FLAG, CREATE_USER, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID, ACTV_IND ) SELECT (SELECT (MAX(ID)) FROM SEQ_ROLODEX_ID), LAST_NAME, FIRST_NAME, MIDDLE_NAME, SUFFIX, PREFIX, TITLE, ORGANIZATION, ADDRESS_LINE_1, ADDRESS_LINE_2, ADDRESS_LINE_3, FAX_NUMBER, EMAIL_ADDRESS, CITY, COUNTY, STATE, POSTAL_CODE, COMMENTS, PHONE_NUMBER, COUNTRY_CODE, SPONSOR_CODE, OWNED_BY_UNIT, SPONSOR_ADDRESS_FLAG, DELETE_FLAG, CREATE_USER, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, UUID() , ACTV_IND from rolodex where rolodex_id = rolodexId;

    update sponsor set rolodex_id = (SELECT (MAX(ID)) FROM SEQ_ROLODEX_ID) where sponsor_code = sponsorCode;
    END LOOP;

    CLOSE CUR;
  END
/
DELIMITER ;
CALL duplicate_sponsor_rolodex_id();

drop procedure if exists duplicate_sponsor_rolodex_id;