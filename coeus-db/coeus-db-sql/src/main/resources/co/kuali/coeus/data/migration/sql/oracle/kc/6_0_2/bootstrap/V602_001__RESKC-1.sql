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

CREATE OR REPLACE FUNCTION clob_to_blob(p_clob IN CLOB) RETURN BLOB IS
  v_blob BLOB;
  v_offset NUMBER DEFAULT 1;
  v_amount NUMBER DEFAULT 4096;
  v_offsetwrite NUMBER DEFAULT 1;
  v_amountwrite NUMBER;
  v_buffer VARCHAR2(4096 CHAR);
BEGIN
  dbms_lob.createtemporary(v_blob, TRUE);

  Begin
    LOOP
      dbms_lob.READ (lob_loc => p_clob,
                     amount  => v_amount,
                     offset  => v_offset,
                     buffer  => v_buffer);

      v_amountwrite := utl_raw.length (r => utl_raw.cast_to_raw(c => v_buffer));

      dbms_lob.WRITE (lob_loc => v_blob,
                      amount  => v_amountwrite,
                      offset  => v_offsetwrite,
                      buffer  => utl_raw.cast_to_raw(v_buffer));

      v_offsetwrite := v_offsetwrite + v_amountwrite;

      v_offset := v_offset + v_amount;
      v_amount := 4096;

    END LOOP;
  EXCEPTION
    WHEN no_data_found THEN
      NULL;
  End;
  RETURN v_blob;
END clob_to_blob;
/

ALTER TABLE BUDGET_SUB_AWARDS ADD FILE_DATA_ID VARCHAR2(36)
/
ALTER TABLE BUDGET_SUB_AWARDS ADD XML_DATA_ID VARCHAR2(36)
/
UPDATE BUDGET_SUB_AWARDS SET FILE_DATA_ID = SYS_GUID() WHERE SUB_AWARD_XFD_FILE IS NOT NULL
/
UPDATE BUDGET_SUB_AWARDS SET XML_DATA_ID = SYS_GUID() WHERE SUB_AWARD_XML_FILE IS NOT NULL
/
INSERT INTO FILE_DATA (SELECT FILE_DATA_ID, SUB_AWARD_XFD_FILE FROM BUDGET_SUB_AWARDS WHERE FILE_DATA_ID IS NOT NULL)
/

ALTER TABLE BUDGET_SUB_AWARDS
    ADD CONSTRAINT FK2_BUDGET_SUB_AWARDS
    FOREIGN KEY (FILE_DATA_ID)
    REFERENCES FILE_DATA (ID)
/

ALTER TABLE BUDGET_SUB_AWARDS
    ADD CONSTRAINT FK3_BUDGET_SUB_AWARDS
    FOREIGN KEY (XML_DATA_ID)
    REFERENCES FILE_DATA (ID)
/


DECLARE
         CURSOR cur IS SELECT SUB_AWARD_NUMBER FROM BUDGET_SUB_AWARDS;
    content CLOB;
BEGIN
    FOR rec IN cur
    LOOP
        EXECUTE IMMEDIATE 'SELECT SUB_AWARD_XML_FILE FROM BUDGET_SUB_AWARDS where sub_award_number = ' || rec.sub_award_number INTO content;

    IF content IS NOT NULL THEN
            EXECUTE IMMEDIATE 'INSERT INTO FILE_DATA (SELECT XML_DATA_ID, clob_to_blob2(SUB_AWARD_XML_FILE) FROM BUDGET_SUB_AWARDS where sub_award_number = ' || rec.sub_award_number || ')';
    END IF;
    END LOOP;
END;
/

ALTER TABLE BUDGET_SUB_AWARDS DROP COLUMN SUB_AWARD_XFD_FILE
/

ALTER TABLE BUDGET_SUB_AWARDS DROP COLUMN SUB_AWARD_XML_FILE
/