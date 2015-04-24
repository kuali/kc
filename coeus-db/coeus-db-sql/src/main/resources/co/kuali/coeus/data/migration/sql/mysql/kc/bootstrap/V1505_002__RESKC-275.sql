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

DELIMITER /

DROP PROCEDURE IF EXISTS p
/
CREATE PROCEDURE p()
BEGIN
  DECLARE DONE INT DEFAULT FALSE;
  DECLARE TRANS_DETAIL_ID decimal(10,0);
  DECLARE TNM_DOC_NUMBER varchar(40);
  DECLARE NEXT_TRANS_ID bigint(19);

  DECLARE TRANS_DETAIL_CUR CURSOR FOR SELECT TRANSACTION_DETAIL_ID, TNM_DOCUMENT_NUMBER FROM TRANSACTION_DETAILS WHERE TRANSACTION_ID = '-1';
  DECLARE CONTINUE HANDLER FOR NOT FOUND SET DONE = TRUE;

  OPEN TRANS_DETAIL_CUR;

  read_loop: LOOP
    FETCH TRANS_DETAIL_CUR INTO TRANS_DETAIL_ID, TNM_DOC_NUMBER;
    IF DONE THEN
      LEAVE read_loop;
    END IF;

    INSERT INTO SEQ_TRANSACTION_ID VALUES (null);
    SELECT MAX(ID) + 1 INTO NEXT_TRANS_ID FROM SEQ_TRANSACTION_ID;

    UPDATE TRANSACTION_DETAILS SET TRANSACTION_DETAIL_TYPE = 'DATE', TRANSACTION_ID = NEXT_TRANS_ID WHERE TRANSACTION_DETAIL_ID = TRANS_DETAIL_ID;
    UPDATE AWARD_AMOUNT_INFO SET TRANSACTION_ID = NEXT_TRANS_ID WHERE TNM_DOCUMENT_NUMBER = TNM_DOC_NUMBER AND TRANSACTION_ID IS NULL;
  END LOOP;

  CLOSE TRANS_DETAIL_CUR;
END
/
CALL p()
/
DROP PROCEDURE IF EXISTS p
/

DELIMITER ;