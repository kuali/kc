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
CREATE PROCEDURE p ()
BEGIN
    DECLARE brch_1_id INT;
    DECLARE node_1_id INT;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO brch_1_id FROM KREW_RTE_NODE_S;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_1_id FROM KREW_RTE_NODE_S;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_T (RTE_BRCH_ID,NM,PARNT_ID,INIT_RTE_NODE_INSTN_ID,SPLT_RTE_NODE_INSTN_ID,JOIN_RTE_NODE_INSTN_ID,VER_NBR) VALUES (',brch_1_id,',"PRIMARY",null,',node_1_id,',null,null,6)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_1_id,',4091,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "Initiated"),',brch_1_id,',1,0,0,3)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_INIT_RTE_NODE_INSTN_T (DOC_HDR_ID,RTE_NODE_INSTN_ID) VALUES (4091,',node_1_id,')');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
END;
/
CALL p ()
/
DROP PROCEDURE IF EXISTS p
/
CREATE PROCEDURE p ()
BEGIN
    DECLARE brch_1_id INT;
    DECLARE node_1_id INT;
    DECLARE node_2_id INT;
    DECLARE brch_2_id INT;
    DECLARE node_3_id INT;
    DECLARE brch_3_id INT;
    DECLARE node_4_id INT;
    DECLARE node_5_id INT;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO brch_1_id FROM KREW_RTE_NODE_S;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_1_id FROM KREW_RTE_NODE_S;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_T (RTE_BRCH_ID,NM,PARNT_ID,INIT_RTE_NODE_INSTN_ID,SPLT_RTE_NODE_INSTN_ID,JOIN_RTE_NODE_INSTN_ID,VER_NBR) VALUES (',brch_1_id,',"PRIMARY",null,null,null,null,68)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_1_id,',4092,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "Initiated"),',brch_1_id,',0,1,0,30)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_INIT_RTE_NODE_INSTN_T (DOC_HDR_ID,RTE_NODE_INSTN_ID) VALUES (4092,',node_1_id,')');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_2_id FROM KREW_RTE_NODE_S;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_2_id,',4092,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "hasSync"),',brch_1_id,',0,1,0,5)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO brch_2_id FROM KREW_RTE_NODE_S;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_3_id FROM KREW_RTE_NODE_S;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_T (RTE_BRCH_ID,NM,PARNT_ID,INIT_RTE_NODE_INSTN_ID,SPLT_RTE_NODE_INSTN_ID,JOIN_RTE_NODE_INSTN_ID,VER_NBR) VALUES (',brch_2_id,',"False",',brch_1_id,',',node_3_id,',',node_2_id,',null,11)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_3_id,',4092,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "isSyncChild"),',brch_2_id,',0,1,0,4)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO brch_3_id FROM KREW_RTE_NODE_S;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_4_id FROM KREW_RTE_NODE_S;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_5_id FROM KREW_RTE_NODE_S;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_T (RTE_BRCH_ID,NM,PARNT_ID,INIT_RTE_NODE_INSTN_ID,SPLT_RTE_NODE_INSTN_ID,JOIN_RTE_NODE_INSTN_ID,VER_NBR) VALUES (',brch_3_id,',"False",',brch_2_id,',',node_4_id,',',node_3_id,',',node_5_id,',5)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_4_id,',4092,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "AwardStandardApproval"),',brch_3_id,',0,1,0,3)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_5_id,',4092,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "JoinSyncChild"),',brch_2_id,',0,1,0,6)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_ST_T (RTE_NODE_INSTN_ST_ID,RTE_NODE_INSTN_ID,KEY_CD,VAL,VER_NBR) VALUES ((SELECT MAX(ID) FROM KREW_RTE_NODE_S),',node_5_id,',"ExpectedJoiners","',brch_3_id,',",5)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_ST_T (RTE_NODE_INSTN_ST_ID,RTE_NODE_INSTN_ID,KEY_CD,VAL,VER_NBR) VALUES ((SELECT MAX(ID) FROM KREW_RTE_NODE_S),',node_5_id,',"ActualJoiners","',brch_3_id,',",5)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_ST_T (RTE_BRCH_ST_ID,RTE_BRCH_ID,KEY_CD,VAL,VER_NBR) VALUES ((SELECT MAX(ID) FROM KREW_RTE_NODE_S),',brch_1_id,',"System.PostProcessorProcessed","true",2)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_ST_T (RTE_BRCH_ST_ID,RTE_BRCH_ID,KEY_CD,VAL,VER_NBR) VALUES ((SELECT MAX(ID) FROM KREW_RTE_NODE_S),',brch_1_id,',"System.PostProcessorFinal","true",1)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
END;
/
CALL p ()
/
DROP PROCEDURE IF EXISTS p
/
CREATE PROCEDURE p ()
BEGIN
    DECLARE brch_1_id INT;
    DECLARE node_1_id INT;
    DECLARE node_2_id INT;
    DECLARE brch_2_id INT;
    DECLARE node_3_id INT;
    DECLARE brch_3_id INT;
    DECLARE node_4_id INT;
    DECLARE node_5_id INT;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO brch_1_id FROM KREW_RTE_NODE_S;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_1_id FROM KREW_RTE_NODE_S;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_T (RTE_BRCH_ID,NM,PARNT_ID,INIT_RTE_NODE_INSTN_ID,SPLT_RTE_NODE_INSTN_ID,JOIN_RTE_NODE_INSTN_ID,VER_NBR) VALUES (',brch_1_id,',"PRIMARY",null,null,null,null,58)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_1_id,',4093,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "Initiated"),',brch_1_id,',0,1,0,25)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_INIT_RTE_NODE_INSTN_T (DOC_HDR_ID,RTE_NODE_INSTN_ID) VALUES (4093,',node_1_id,')');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_2_id FROM KREW_RTE_NODE_S;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_2_id,',4093,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "hasSync"),',brch_1_id,',0,1,0,5)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO brch_2_id FROM KREW_RTE_NODE_S;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_3_id FROM KREW_RTE_NODE_S;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_T (RTE_BRCH_ID,NM,PARNT_ID,INIT_RTE_NODE_INSTN_ID,SPLT_RTE_NODE_INSTN_ID,JOIN_RTE_NODE_INSTN_ID,VER_NBR) VALUES (',brch_2_id,',"False",',brch_1_id,',',node_3_id,',',node_2_id,',null,11)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_3_id,',4093,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "isSyncChild"),',brch_2_id,',0,1,0,4)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO brch_3_id FROM KREW_RTE_NODE_S;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_4_id FROM KREW_RTE_NODE_S;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_5_id FROM KREW_RTE_NODE_S;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_T (RTE_BRCH_ID,NM,PARNT_ID,INIT_RTE_NODE_INSTN_ID,SPLT_RTE_NODE_INSTN_ID,JOIN_RTE_NODE_INSTN_ID,VER_NBR) VALUES (',brch_3_id,',"False",',brch_2_id,',',node_4_id,',',node_3_id,',',node_5_id,',5)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_4_id,',4093,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "AwardStandardApproval"),',brch_3_id,',0,1,0,3)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_5_id,',4093,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "JoinSyncChild"),',brch_2_id,',0,1,0,6)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_ST_T (RTE_NODE_INSTN_ST_ID,RTE_NODE_INSTN_ID,KEY_CD,VAL,VER_NBR) VALUES ((SELECT MAX(ID) FROM KREW_RTE_NODE_S),',node_5_id,',"ExpectedJoiners","',brch_3_id,',",5)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_ST_T (RTE_NODE_INSTN_ST_ID,RTE_NODE_INSTN_ID,KEY_CD,VAL,VER_NBR) VALUES ((SELECT MAX(ID) FROM KREW_RTE_NODE_S),',node_5_id,',"ActualJoiners","',brch_3_id,',",5)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_ST_T (RTE_BRCH_ST_ID,RTE_BRCH_ID,KEY_CD,VAL,VER_NBR) VALUES ((SELECT MAX(ID) FROM KREW_RTE_NODE_S),',brch_1_id,',"System.PostProcessorProcessed","true",2)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_ST_T (RTE_BRCH_ST_ID,RTE_BRCH_ID,KEY_CD,VAL,VER_NBR) VALUES ((SELECT MAX(ID) FROM KREW_RTE_NODE_S),',brch_1_id,',"System.PostProcessorFinal","true",1)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
END;
/
CALL p ()
/
DROP PROCEDURE IF EXISTS p
/
CREATE PROCEDURE p ()
BEGIN
    DECLARE brch_1_id INT;
    DECLARE node_1_id INT;
    DECLARE node_2_id INT;
    DECLARE brch_2_id INT;
    DECLARE node_3_id INT;
    DECLARE brch_3_id INT;
    DECLARE node_4_id INT;
    DECLARE node_5_id INT;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO brch_1_id FROM KREW_RTE_NODE_S;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_1_id FROM KREW_RTE_NODE_S;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_T (RTE_BRCH_ID,NM,PARNT_ID,INIT_RTE_NODE_INSTN_ID,SPLT_RTE_NODE_INSTN_ID,JOIN_RTE_NODE_INSTN_ID,VER_NBR) VALUES (',brch_1_id,',"PRIMARY",null,null,null,null,66)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_1_id,',4094,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "Initiated"),',brch_1_id,',0,1,0,29)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_INIT_RTE_NODE_INSTN_T (DOC_HDR_ID,RTE_NODE_INSTN_ID) VALUES (4094,',node_1_id,')');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_2_id FROM KREW_RTE_NODE_S;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_2_id,',4094,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "hasSync"),',brch_1_id,',0,1,0,5)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO brch_2_id FROM KREW_RTE_NODE_S;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_3_id FROM KREW_RTE_NODE_S;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_T (RTE_BRCH_ID,NM,PARNT_ID,INIT_RTE_NODE_INSTN_ID,SPLT_RTE_NODE_INSTN_ID,JOIN_RTE_NODE_INSTN_ID,VER_NBR) VALUES (',brch_2_id,',"False",',brch_1_id,',',node_3_id,',',node_2_id,',null,11)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_3_id,',4094,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "isSyncChild"),',brch_2_id,',0,1,0,4)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO brch_3_id FROM KREW_RTE_NODE_S;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_4_id FROM KREW_RTE_NODE_S;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_5_id FROM KREW_RTE_NODE_S;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_T (RTE_BRCH_ID,NM,PARNT_ID,INIT_RTE_NODE_INSTN_ID,SPLT_RTE_NODE_INSTN_ID,JOIN_RTE_NODE_INSTN_ID,VER_NBR) VALUES (',brch_3_id,',"False",',brch_2_id,',',node_4_id,',',node_3_id,',',node_5_id,',5)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_4_id,',4094,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "AwardStandardApproval"),',brch_3_id,',0,1,0,3)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_5_id,',4094,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "JoinSyncChild"),',brch_2_id,',0,1,0,6)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_ST_T (RTE_NODE_INSTN_ST_ID,RTE_NODE_INSTN_ID,KEY_CD,VAL,VER_NBR) VALUES ((SELECT MAX(ID) FROM KREW_RTE_NODE_S),',node_5_id,',"ExpectedJoiners","',brch_3_id,',",5)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_ST_T (RTE_NODE_INSTN_ST_ID,RTE_NODE_INSTN_ID,KEY_CD,VAL,VER_NBR) VALUES ((SELECT MAX(ID) FROM KREW_RTE_NODE_S),',node_5_id,',"ActualJoiners","',brch_3_id,',",5)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_ST_T (RTE_BRCH_ST_ID,RTE_BRCH_ID,KEY_CD,VAL,VER_NBR) VALUES ((SELECT MAX(ID) FROM KREW_RTE_NODE_S),',brch_1_id,',"System.PostProcessorProcessed","true",2)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_ST_T (RTE_BRCH_ST_ID,RTE_BRCH_ID,KEY_CD,VAL,VER_NBR) VALUES ((SELECT MAX(ID) FROM KREW_RTE_NODE_S),',brch_1_id,',"System.PostProcessorFinal","true",1)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
END;
/
CALL p ()
/
DROP PROCEDURE IF EXISTS p
/
CREATE PROCEDURE p ()
BEGIN
    DECLARE brch_1_id INT;
    DECLARE node_1_id INT;
    DECLARE node_2_id INT;
    DECLARE brch_2_id INT;
    DECLARE node_3_id INT;
    DECLARE brch_3_id INT;
    DECLARE node_4_id INT;
    DECLARE node_5_id INT;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO brch_1_id FROM KREW_RTE_NODE_S;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_1_id FROM KREW_RTE_NODE_S;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_T (RTE_BRCH_ID,NM,PARNT_ID,INIT_RTE_NODE_INSTN_ID,SPLT_RTE_NODE_INSTN_ID,JOIN_RTE_NODE_INSTN_ID,VER_NBR) VALUES (',brch_1_id,',"PRIMARY",null,',node_1_id,',null,null,62)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_1_id,',4095,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "Initiated"),',brch_1_id,',0,1,0,27)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_INIT_RTE_NODE_INSTN_T (DOC_HDR_ID,RTE_NODE_INSTN_ID) VALUES (4095,',node_1_id,')');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_2_id FROM KREW_RTE_NODE_S;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_2_id,',4095,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "hasSync"),',brch_1_id,',0,1,0,5)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO brch_2_id FROM KREW_RTE_NODE_S;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_3_id FROM KREW_RTE_NODE_S;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_T (RTE_BRCH_ID,NM,PARNT_ID,INIT_RTE_NODE_INSTN_ID,SPLT_RTE_NODE_INSTN_ID,JOIN_RTE_NODE_INSTN_ID,VER_NBR) VALUES (',brch_2_id,',"False",',brch_1_id,',',node_3_id,',',node_2_id,',null,11)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_3_id,',4095,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "isSyncChild"),',brch_2_id,',0,1,0,4)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO brch_3_id FROM KREW_RTE_NODE_S;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_4_id FROM KREW_RTE_NODE_S;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_5_id FROM KREW_RTE_NODE_S;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_T (RTE_BRCH_ID,NM,PARNT_ID,INIT_RTE_NODE_INSTN_ID,SPLT_RTE_NODE_INSTN_ID,JOIN_RTE_NODE_INSTN_ID,VER_NBR) VALUES (',brch_3_id,',"False",',brch_2_id,',',node_4_id,',',node_3_id,',',node_5_id,',5)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_4_id,',4095,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "AwardStandardApproval"),',brch_3_id,',0,1,0,3)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_5_id,',4095,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "JoinSyncChild"),',brch_2_id,',0,1,0,6)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_ST_T (RTE_NODE_INSTN_ST_ID,RTE_NODE_INSTN_ID,KEY_CD,VAL,VER_NBR) VALUES ((SELECT MAX(ID) FROM KREW_RTE_NODE_S),',node_5_id,',"ExpectedJoiners","',brch_3_id,',",5)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_ST_T (RTE_NODE_INSTN_ST_ID,RTE_NODE_INSTN_ID,KEY_CD,VAL,VER_NBR) VALUES ((SELECT MAX(ID) FROM KREW_RTE_NODE_S),',node_5_id,',"ActualJoiners","',brch_3_id,',",5)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_ST_T (RTE_BRCH_ST_ID,RTE_BRCH_ID,KEY_CD,VAL,VER_NBR) VALUES ((SELECT MAX(ID) FROM KREW_RTE_NODE_S),',brch_1_id,',"System.PostProcessorProcessed","true",2)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_ST_T (RTE_BRCH_ST_ID,RTE_BRCH_ID,KEY_CD,VAL,VER_NBR) VALUES ((SELECT MAX(ID) FROM KREW_RTE_NODE_S),',brch_1_id,',"System.PostProcessorFinal","true",1)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
END;
/
CALL p ()
/
DROP PROCEDURE IF EXISTS p
/
CREATE PROCEDURE p ()
BEGIN
    DECLARE brch_1_id INT;
    DECLARE node_1_id INT;
    DECLARE node_2_id INT;
    DECLARE brch_2_id INT;
    DECLARE node_3_id INT;
    DECLARE brch_3_id INT;
    DECLARE node_4_id INT;
    DECLARE node_5_id INT;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO brch_1_id FROM KREW_RTE_NODE_S;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_1_id FROM KREW_RTE_NODE_S;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_T (RTE_BRCH_ID,NM,PARNT_ID,INIT_RTE_NODE_INSTN_ID,SPLT_RTE_NODE_INSTN_ID,JOIN_RTE_NODE_INSTN_ID,VER_NBR) VALUES (',brch_1_id,',"PRIMARY",null,null,null,null,58)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_1_id,',4096,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "Initiated"),',brch_1_id,',0,1,0,25)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_INIT_RTE_NODE_INSTN_T (DOC_HDR_ID,RTE_NODE_INSTN_ID) VALUES (4096,',node_1_id,')');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_2_id FROM KREW_RTE_NODE_S;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_2_id,',4096,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "hasSync"),',brch_1_id,',0,1,0,5)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO brch_2_id FROM KREW_RTE_NODE_S;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_3_id FROM KREW_RTE_NODE_S;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_T (RTE_BRCH_ID,NM,PARNT_ID,INIT_RTE_NODE_INSTN_ID,SPLT_RTE_NODE_INSTN_ID,JOIN_RTE_NODE_INSTN_ID,VER_NBR) VALUES (',brch_2_id,',"False",',brch_1_id,',',node_3_id,',',node_2_id,',null,11)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_3_id,',4096,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "isSyncChild"),',brch_2_id,',0,1,0,4)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO brch_3_id FROM KREW_RTE_NODE_S;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_4_id FROM KREW_RTE_NODE_S;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_5_id FROM KREW_RTE_NODE_S;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_T (RTE_BRCH_ID,NM,PARNT_ID,INIT_RTE_NODE_INSTN_ID,SPLT_RTE_NODE_INSTN_ID,JOIN_RTE_NODE_INSTN_ID,VER_NBR) VALUES (',brch_3_id,',"False",',brch_2_id,',',node_4_id,',',node_3_id,',',node_5_id,',5)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_4_id,',4096,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "AwardStandardApproval"),',brch_3_id,',0,1,0,3)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_5_id,',4096,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "JoinSyncChild"),',brch_2_id,',0,1,0,6)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_ST_T (RTE_NODE_INSTN_ST_ID,RTE_NODE_INSTN_ID,KEY_CD,VAL,VER_NBR) VALUES ((SELECT MAX(ID) FROM KREW_RTE_NODE_S),',node_5_id,',"ExpectedJoiners","',brch_3_id,',",5)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_ST_T (RTE_NODE_INSTN_ST_ID,RTE_NODE_INSTN_ID,KEY_CD,VAL,VER_NBR) VALUES ((SELECT MAX(ID) FROM KREW_RTE_NODE_S),',node_5_id,',"ActualJoiners","',brch_3_id,',",5)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_ST_T (RTE_BRCH_ST_ID,RTE_BRCH_ID,KEY_CD,VAL,VER_NBR) VALUES ((SELECT MAX(ID) FROM KREW_RTE_NODE_S),',brch_1_id,',"System.PostProcessorProcessed","true",2)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_ST_T (RTE_BRCH_ST_ID,RTE_BRCH_ID,KEY_CD,VAL,VER_NBR) VALUES ((SELECT MAX(ID) FROM KREW_RTE_NODE_S),',brch_1_id,',"System.PostProcessorFinal","true",1)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
END;
/
CALL p ()
/
DROP PROCEDURE IF EXISTS p
/
CREATE PROCEDURE p ()
BEGIN
    DECLARE brch_1_id INT;
    DECLARE node_1_id INT;
    DECLARE node_2_id INT;
    DECLARE brch_2_id INT;
    DECLARE node_3_id INT;
    DECLARE brch_3_id INT;
    DECLARE node_4_id INT;
    DECLARE node_5_id INT;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO brch_1_id FROM KREW_RTE_NODE_S;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_1_id FROM KREW_RTE_NODE_S;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_T (RTE_BRCH_ID,NM,PARNT_ID,INIT_RTE_NODE_INSTN_ID,SPLT_RTE_NODE_INSTN_ID,JOIN_RTE_NODE_INSTN_ID,VER_NBR) VALUES (',brch_1_id,',"PRIMARY",null,',node_1_id,',null,null,58)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_1_id,',4097,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "Initiated"),',brch_1_id,',0,1,0,25)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_INIT_RTE_NODE_INSTN_T (DOC_HDR_ID,RTE_NODE_INSTN_ID) VALUES (4097,',node_1_id,')');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_2_id FROM KREW_RTE_NODE_S;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_2_id,',4097,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "hasSync"),',brch_1_id,',0,1,0,5)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO brch_2_id FROM KREW_RTE_NODE_S;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_3_id FROM KREW_RTE_NODE_S;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_T (RTE_BRCH_ID,NM,PARNT_ID,INIT_RTE_NODE_INSTN_ID,SPLT_RTE_NODE_INSTN_ID,JOIN_RTE_NODE_INSTN_ID,VER_NBR) VALUES (',brch_2_id,',"False",',brch_1_id,',',node_3_id,',',node_2_id,',null,11)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_3_id,',4097,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "isSyncChild"),',brch_2_id,',0,1,0,4)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO brch_3_id FROM KREW_RTE_NODE_S;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_4_id FROM KREW_RTE_NODE_S;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_5_id FROM KREW_RTE_NODE_S;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_T (RTE_BRCH_ID,NM,PARNT_ID,INIT_RTE_NODE_INSTN_ID,SPLT_RTE_NODE_INSTN_ID,JOIN_RTE_NODE_INSTN_ID,VER_NBR) VALUES (',brch_3_id,',"False",',brch_2_id,',',node_4_id,',',node_3_id,',',node_5_id,',5)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_4_id,',4097,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "AwardStandardApproval"),',brch_3_id,',0,1,0,3)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_5_id,',4097,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "JoinSyncChild"),',brch_2_id,',0,1,0,6)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_ST_T (RTE_NODE_INSTN_ST_ID,RTE_NODE_INSTN_ID,KEY_CD,VAL,VER_NBR) VALUES ((SELECT MAX(ID) FROM KREW_RTE_NODE_S),',node_5_id,',"ExpectedJoiners","',brch_3_id,',",5)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_ST_T (RTE_NODE_INSTN_ST_ID,RTE_NODE_INSTN_ID,KEY_CD,VAL,VER_NBR) VALUES ((SELECT MAX(ID) FROM KREW_RTE_NODE_S),',node_5_id,',"ActualJoiners","',brch_3_id,',",5)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_ST_T (RTE_BRCH_ST_ID,RTE_BRCH_ID,KEY_CD,VAL,VER_NBR) VALUES ((SELECT MAX(ID) FROM KREW_RTE_NODE_S),',brch_1_id,',"System.PostProcessorProcessed","true",2)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_ST_T (RTE_BRCH_ST_ID,RTE_BRCH_ID,KEY_CD,VAL,VER_NBR) VALUES ((SELECT MAX(ID) FROM KREW_RTE_NODE_S),',brch_1_id,',"System.PostProcessorFinal","true",1)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
END;
/
CALL p ()
/
DROP PROCEDURE IF EXISTS p
/
CREATE PROCEDURE p ()
BEGIN
    DECLARE brch_1_id INT;
    DECLARE node_1_id INT;
    DECLARE node_2_id INT;
    DECLARE brch_2_id INT;
    DECLARE node_3_id INT;
    DECLARE brch_3_id INT;
    DECLARE node_4_id INT;
    DECLARE node_5_id INT;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO brch_1_id FROM KREW_RTE_NODE_S;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_1_id FROM KREW_RTE_NODE_S;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_T (RTE_BRCH_ID,NM,PARNT_ID,INIT_RTE_NODE_INSTN_ID,SPLT_RTE_NODE_INSTN_ID,JOIN_RTE_NODE_INSTN_ID,VER_NBR) VALUES (',brch_1_id,',"PRIMARY",null,',node_1_id,',null,null,70)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_1_id,',4098,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "Initiated"),',brch_1_id,',0,1,0,31)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_INIT_RTE_NODE_INSTN_T (DOC_HDR_ID,RTE_NODE_INSTN_ID) VALUES (4098,',node_1_id,')');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_2_id FROM KREW_RTE_NODE_S;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_2_id,',4098,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "hasSync"),',brch_1_id,',0,1,0,5)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO brch_2_id FROM KREW_RTE_NODE_S;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_3_id FROM KREW_RTE_NODE_S;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_T (RTE_BRCH_ID,NM,PARNT_ID,INIT_RTE_NODE_INSTN_ID,SPLT_RTE_NODE_INSTN_ID,JOIN_RTE_NODE_INSTN_ID,VER_NBR) VALUES (',brch_2_id,',"False",',brch_1_id,',',node_3_id,',',node_2_id,',null,11)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_3_id,',4098,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "isSyncChild"),',brch_2_id,',0,1,0,4)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO brch_3_id FROM KREW_RTE_NODE_S;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_4_id FROM KREW_RTE_NODE_S;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_5_id FROM KREW_RTE_NODE_S;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_T (RTE_BRCH_ID,NM,PARNT_ID,INIT_RTE_NODE_INSTN_ID,SPLT_RTE_NODE_INSTN_ID,JOIN_RTE_NODE_INSTN_ID,VER_NBR) VALUES (',brch_3_id,',"False",',brch_2_id,',',node_4_id,',',node_3_id,',',node_5_id,',5)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_4_id,',4098,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "AwardStandardApproval"),',brch_3_id,',0,1,0,3)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_5_id,',4098,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "JoinSyncChild"),',brch_2_id,',0,1,0,6)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_ST_T (RTE_NODE_INSTN_ST_ID,RTE_NODE_INSTN_ID,KEY_CD,VAL,VER_NBR) VALUES ((SELECT MAX(ID) FROM KREW_RTE_NODE_S),',node_5_id,',"ExpectedJoiners","',brch_3_id,',",5)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_ST_T (RTE_NODE_INSTN_ST_ID,RTE_NODE_INSTN_ID,KEY_CD,VAL,VER_NBR) VALUES ((SELECT MAX(ID) FROM KREW_RTE_NODE_S),',node_5_id,',"ActualJoiners","',brch_3_id,',",5)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_ST_T (RTE_BRCH_ST_ID,RTE_BRCH_ID,KEY_CD,VAL,VER_NBR) VALUES ((SELECT MAX(ID) FROM KREW_RTE_NODE_S),',brch_1_id,',"System.PostProcessorProcessed","true",2)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_ST_T (RTE_BRCH_ST_ID,RTE_BRCH_ID,KEY_CD,VAL,VER_NBR) VALUES ((SELECT MAX(ID) FROM KREW_RTE_NODE_S),',brch_1_id,',"System.PostProcessorFinal","true",1)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
END;
/
CALL p ()
/
DROP PROCEDURE IF EXISTS p
/
CREATE PROCEDURE p ()
BEGIN
    DECLARE brch_1_id INT;
    DECLARE node_1_id INT;
    DECLARE node_2_id INT;
    DECLARE brch_2_id INT;
    DECLARE node_3_id INT;
    DECLARE brch_3_id INT;
    DECLARE node_4_id INT;
    DECLARE node_5_id INT;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO brch_1_id FROM KREW_RTE_NODE_S;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_1_id FROM KREW_RTE_NODE_S;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_T (RTE_BRCH_ID,NM,PARNT_ID,INIT_RTE_NODE_INSTN_ID,SPLT_RTE_NODE_INSTN_ID,JOIN_RTE_NODE_INSTN_ID,VER_NBR) VALUES (',brch_1_id,',"PRIMARY",null,',node_1_id,',null,null,58)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_1_id,',4099,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "Initiated"),',brch_1_id,',0,1,0,25)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_INIT_RTE_NODE_INSTN_T (DOC_HDR_ID,RTE_NODE_INSTN_ID) VALUES (4099,',node_1_id,')');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_2_id FROM KREW_RTE_NODE_S;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_2_id,',4099,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "hasSync"),',brch_1_id,',0,1,0,5)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO brch_2_id FROM KREW_RTE_NODE_S;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_3_id FROM KREW_RTE_NODE_S;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_T (RTE_BRCH_ID,NM,PARNT_ID,INIT_RTE_NODE_INSTN_ID,SPLT_RTE_NODE_INSTN_ID,JOIN_RTE_NODE_INSTN_ID,VER_NBR) VALUES (',brch_2_id,',"False",',brch_1_id,',',node_3_id,',',node_2_id,',null,11)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_3_id,',4099,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "isSyncChild"),',brch_2_id,',0,1,0,4)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO brch_3_id FROM KREW_RTE_NODE_S;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_4_id FROM KREW_RTE_NODE_S;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_5_id FROM KREW_RTE_NODE_S;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_T (RTE_BRCH_ID,NM,PARNT_ID,INIT_RTE_NODE_INSTN_ID,SPLT_RTE_NODE_INSTN_ID,JOIN_RTE_NODE_INSTN_ID,VER_NBR) VALUES (',brch_3_id,',"False",',brch_2_id,',',node_4_id,',',node_3_id,',',node_5_id,',5)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_4_id,',4099,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "AwardStandardApproval"),',brch_3_id,',0,1,0,3)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_5_id,',4099,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "JoinSyncChild"),',brch_2_id,',0,1,0,6)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_ST_T (RTE_NODE_INSTN_ST_ID,RTE_NODE_INSTN_ID,KEY_CD,VAL,VER_NBR) VALUES ((SELECT MAX(ID) FROM KREW_RTE_NODE_S),',node_5_id,',"ExpectedJoiners","',brch_3_id,',",5)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_ST_T (RTE_NODE_INSTN_ST_ID,RTE_NODE_INSTN_ID,KEY_CD,VAL,VER_NBR) VALUES ((SELECT MAX(ID) FROM KREW_RTE_NODE_S),',node_5_id,',"ActualJoiners","',brch_3_id,',",5)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_ST_T (RTE_BRCH_ST_ID,RTE_BRCH_ID,KEY_CD,VAL,VER_NBR) VALUES ((SELECT MAX(ID) FROM KREW_RTE_NODE_S),',brch_1_id,',"System.PostProcessorProcessed","true",2)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_ST_T (RTE_BRCH_ST_ID,RTE_BRCH_ID,KEY_CD,VAL,VER_NBR) VALUES ((SELECT MAX(ID) FROM KREW_RTE_NODE_S),',brch_1_id,',"System.PostProcessorFinal","true",1)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
END;
/
CALL p ()
/
DROP PROCEDURE IF EXISTS p
/
CREATE PROCEDURE p ()
BEGIN
    DECLARE brch_1_id INT;
    DECLARE node_1_id INT;
    DECLARE node_2_id INT;
    DECLARE brch_2_id INT;
    DECLARE node_3_id INT;
    DECLARE brch_3_id INT;
    DECLARE node_4_id INT;
    DECLARE node_5_id INT;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO brch_1_id FROM KREW_RTE_NODE_S;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_1_id FROM KREW_RTE_NODE_S;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_T (RTE_BRCH_ID,NM,PARNT_ID,INIT_RTE_NODE_INSTN_ID,SPLT_RTE_NODE_INSTN_ID,JOIN_RTE_NODE_INSTN_ID,VER_NBR) VALUES (',brch_1_id,',"PRIMARY",null,null,null,null,54)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_1_id,',4100,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "Initiated"),',brch_1_id,',0,1,0,23)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_INIT_RTE_NODE_INSTN_T (DOC_HDR_ID,RTE_NODE_INSTN_ID) VALUES (4100,',node_1_id,')');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_2_id FROM KREW_RTE_NODE_S;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_2_id,',4100,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "hasSync"),',brch_1_id,',0,1,0,5)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO brch_2_id FROM KREW_RTE_NODE_S;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_3_id FROM KREW_RTE_NODE_S;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_T (RTE_BRCH_ID,NM,PARNT_ID,INIT_RTE_NODE_INSTN_ID,SPLT_RTE_NODE_INSTN_ID,JOIN_RTE_NODE_INSTN_ID,VER_NBR) VALUES (',brch_2_id,',"False",',brch_1_id,',',node_3_id,',',node_2_id,',null,11)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_3_id,',4100,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "isSyncChild"),',brch_2_id,',0,1,0,4)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO brch_3_id FROM KREW_RTE_NODE_S;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_4_id FROM KREW_RTE_NODE_S;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_5_id FROM KREW_RTE_NODE_S;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_T (RTE_BRCH_ID,NM,PARNT_ID,INIT_RTE_NODE_INSTN_ID,SPLT_RTE_NODE_INSTN_ID,JOIN_RTE_NODE_INSTN_ID,VER_NBR) VALUES (',brch_3_id,',"False",',brch_2_id,',',node_4_id,',',node_3_id,',',node_5_id,',5)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_4_id,',4100,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "AwardStandardApproval"),',brch_3_id,',0,1,0,3)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_5_id,',4100,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "JoinSyncChild"),',brch_2_id,',0,1,0,6)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_ST_T (RTE_NODE_INSTN_ST_ID,RTE_NODE_INSTN_ID,KEY_CD,VAL,VER_NBR) VALUES ((SELECT MAX(ID) FROM KREW_RTE_NODE_S),',node_5_id,',"ExpectedJoiners","',brch_3_id,',",5)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_ST_T (RTE_NODE_INSTN_ST_ID,RTE_NODE_INSTN_ID,KEY_CD,VAL,VER_NBR) VALUES ((SELECT MAX(ID) FROM KREW_RTE_NODE_S),',node_5_id,',"ActualJoiners","',brch_3_id,',",5)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_ST_T (RTE_BRCH_ST_ID,RTE_BRCH_ID,KEY_CD,VAL,VER_NBR) VALUES ((SELECT MAX(ID) FROM KREW_RTE_NODE_S),',brch_1_id,',"System.PostProcessorProcessed","true",2)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_ST_T (RTE_BRCH_ST_ID,RTE_BRCH_ID,KEY_CD,VAL,VER_NBR) VALUES ((SELECT MAX(ID) FROM KREW_RTE_NODE_S),',brch_1_id,',"System.PostProcessorFinal","true",1)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
END;
/
CALL p ()
/
DROP PROCEDURE IF EXISTS p
/
CREATE PROCEDURE p ()
BEGIN
DECLARE brch_1_id INT;
DECLARE node_1_id INT;
DECLARE node_2_id INT;
DECLARE brch_2_id INT;
DECLARE node_3_id INT;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO brch_1_id FROM KREW_RTE_NODE_S;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_1_id FROM KREW_RTE_NODE_S;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_T (RTE_BRCH_ID,NM,PARNT_ID,INIT_RTE_NODE_INSTN_ID,SPLT_RTE_NODE_INSTN_ID,JOIN_RTE_NODE_INSTN_ID,VER_NBR) VALUES (',brch_1_id,',"PRIMARY",null,',node_1_id,',null,null,22)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_1_id,',4102,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "Initiated"),',brch_1_id,',0,1,0,8)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_INIT_RTE_NODE_INSTN_T (DOC_HDR_ID,RTE_NODE_INSTN_ID) VALUES (4102,',node_1_id,')');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_2_id FROM KREW_RTE_NODE_S;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_2_id,',4102,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "hasSync"),',brch_1_id,',0,1,0,3)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO brch_2_id FROM KREW_RTE_NODE_S;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_3_id FROM KREW_RTE_NODE_S;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_T (RTE_BRCH_ID,NM,PARNT_ID,INIT_RTE_NODE_INSTN_ID,SPLT_RTE_NODE_INSTN_ID,JOIN_RTE_NODE_INSTN_ID,VER_NBR) VALUES (',brch_2_id,',"False",',brch_1_id,',',node_3_id,',',node_2_id,',null,3)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_3_id,',4102,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "isSyncChild"),',brch_2_id,',0,1,0,2)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_ST_T (RTE_BRCH_ST_ID,RTE_BRCH_ID,KEY_CD,VAL,VER_NBR) VALUES ((SELECT MAX(ID) FROM KREW_RTE_NODE_S),',brch_1_id,',"System.PostProcessorProcessed","true",2)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_ST_T (RTE_BRCH_ST_ID,RTE_BRCH_ID,KEY_CD,VAL,VER_NBR) VALUES ((SELECT MAX(ID) FROM KREW_RTE_NODE_S),',brch_1_id,',"System.PostProcessorFinal","true",1)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
END;
/
CALL p ()
/
DROP PROCEDURE IF EXISTS p
/
CREATE PROCEDURE p ()
BEGIN
    DECLARE brch_1_id INT;
    DECLARE node_1_id INT;
    DECLARE node_2_id INT;
    DECLARE brch_2_id INT;
    DECLARE node_3_id INT;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO brch_1_id FROM KREW_RTE_NODE_S;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_1_id FROM KREW_RTE_NODE_S;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_T (RTE_BRCH_ID,NM,PARNT_ID,INIT_RTE_NODE_INSTN_ID,SPLT_RTE_NODE_INSTN_ID,JOIN_RTE_NODE_INSTN_ID,VER_NBR) VALUES (',brch_1_id,',"PRIMARY",null,',node_1_id,',null,null,22)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_1_id,',4103,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "Initiated"),',brch_1_id,',0,1,0,8)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_INIT_RTE_NODE_INSTN_T (DOC_HDR_ID,RTE_NODE_INSTN_ID) VALUES (4103,',node_1_id,')');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_2_id FROM KREW_RTE_NODE_S;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_2_id,',4103,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "hasSync"),',brch_1_id,',0,1,0,3)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO brch_2_id FROM KREW_RTE_NODE_S;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_3_id FROM KREW_RTE_NODE_S;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_T (RTE_BRCH_ID,NM,PARNT_ID,INIT_RTE_NODE_INSTN_ID,SPLT_RTE_NODE_INSTN_ID,JOIN_RTE_NODE_INSTN_ID,VER_NBR) VALUES (',brch_2_id,',"False",',brch_1_id,',',node_3_id,',',node_2_id,',null,3)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_3_id,',4103,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "isSyncChild"),',brch_2_id,',0,1,0,2)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_ST_T (RTE_BRCH_ST_ID,RTE_BRCH_ID,KEY_CD,VAL,VER_NBR) VALUES ((SELECT MAX(ID) FROM KREW_RTE_NODE_S),',brch_1_id,',"System.PostProcessorProcessed","true",2)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_ST_T (RTE_BRCH_ST_ID,RTE_BRCH_ID,KEY_CD,VAL,VER_NBR) VALUES ((SELECT MAX(ID) FROM KREW_RTE_NODE_S),',brch_1_id,',"System.PostProcessorFinal","true",1)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
END;
/
CALL p ()
/
DROP PROCEDURE IF EXISTS p
/
CREATE PROCEDURE p ()
BEGIN
    DECLARE brch_1_id INT;
    DECLARE node_1_id INT;
    DECLARE node_2_id INT;
    DECLARE brch_2_id INT;
    DECLARE node_3_id INT;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO brch_1_id FROM KREW_RTE_NODE_S;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_1_id FROM KREW_RTE_NODE_S;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_T (RTE_BRCH_ID,NM,PARNT_ID,INIT_RTE_NODE_INSTN_ID,SPLT_RTE_NODE_INSTN_ID,JOIN_RTE_NODE_INSTN_ID,VER_NBR) VALUES (',brch_1_id,',"PRIMARY",null,',node_1_id,',null,null,22)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_1_id,',4104,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "Initiated"),',brch_1_id,',0,1,0,8)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_INIT_RTE_NODE_INSTN_T (DOC_HDR_ID,RTE_NODE_INSTN_ID) VALUES (4104,',node_1_id,')');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_2_id FROM KREW_RTE_NODE_S;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_2_id,',4104,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "hasSync"),',brch_1_id,',0,1,0,3)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO brch_2_id FROM KREW_RTE_NODE_S;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_3_id FROM KREW_RTE_NODE_S;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_T (RTE_BRCH_ID,NM,PARNT_ID,INIT_RTE_NODE_INSTN_ID,SPLT_RTE_NODE_INSTN_ID,JOIN_RTE_NODE_INSTN_ID,VER_NBR) VALUES (',brch_2_id,',"False",',brch_1_id,',',node_3_id,',',node_2_id,',null,3)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_3_id,',4104,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "isSyncChild"),',brch_2_id,',0,1,0,2)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_ST_T (RTE_BRCH_ST_ID,RTE_BRCH_ID,KEY_CD,VAL,VER_NBR) VALUES ((SELECT MAX(ID) FROM KREW_RTE_NODE_S),',brch_1_id,',"System.PostProcessorProcessed","true",2)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_ST_T (RTE_BRCH_ST_ID,RTE_BRCH_ID,KEY_CD,VAL,VER_NBR) VALUES ((SELECT MAX(ID) FROM KREW_RTE_NODE_S),',brch_1_id,',"System.PostProcessorFinal","true",1)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
END;
/
CALL p ()
/
DROP PROCEDURE IF EXISTS p
/
CREATE PROCEDURE p ()
BEGIN
    DECLARE brch_1_id INT;
    DECLARE node_1_id INT;
    DECLARE node_2_id INT;
    DECLARE brch_2_id INT;
    DECLARE node_3_id INT;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO brch_1_id FROM KREW_RTE_NODE_S;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_1_id FROM KREW_RTE_NODE_S;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_T (RTE_BRCH_ID,NM,PARNT_ID,INIT_RTE_NODE_INSTN_ID,SPLT_RTE_NODE_INSTN_ID,JOIN_RTE_NODE_INSTN_ID,VER_NBR) VALUES (',brch_1_id,',"PRIMARY",null,',node_1_id,',null,null,22)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_1_id,',4105,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "Initiated"),',brch_1_id,',0,1,0,8)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_INIT_RTE_NODE_INSTN_T (DOC_HDR_ID,RTE_NODE_INSTN_ID) VALUES (4105,',node_1_id,')');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_2_id FROM KREW_RTE_NODE_S;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_2_id,',4105,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "hasSync"),',brch_1_id,',0,1,0,3)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO brch_2_id FROM KREW_RTE_NODE_S;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_3_id FROM KREW_RTE_NODE_S;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_T (RTE_BRCH_ID,NM,PARNT_ID,INIT_RTE_NODE_INSTN_ID,SPLT_RTE_NODE_INSTN_ID,JOIN_RTE_NODE_INSTN_ID,VER_NBR) VALUES (',brch_2_id,',"False",',brch_1_id,',',node_3_id,',',node_2_id,',null,3)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_3_id,',4105,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "isSyncChild"),',brch_2_id,',0,1,0,2)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_ST_T (RTE_BRCH_ST_ID,RTE_BRCH_ID,KEY_CD,VAL,VER_NBR) VALUES ((SELECT MAX(ID) FROM KREW_RTE_NODE_S),',brch_1_id,',"System.PostProcessorProcessed","true",2)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_ST_T (RTE_BRCH_ST_ID,RTE_BRCH_ID,KEY_CD,VAL,VER_NBR) VALUES ((SELECT MAX(ID) FROM KREW_RTE_NODE_S),',brch_1_id,',"System.PostProcessorFinal","true",1)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
END;
/
CALL p ()
/
DROP PROCEDURE IF EXISTS p
/
CREATE PROCEDURE p ()
BEGIN
    DECLARE brch_1_id INT;
    DECLARE node_1_id INT;
    DECLARE node_2_id INT;
    DECLARE brch_2_id INT;
    DECLARE node_3_id INT;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO brch_1_id FROM KREW_RTE_NODE_S;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_1_id FROM KREW_RTE_NODE_S;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_T (RTE_BRCH_ID,NM,PARNT_ID,INIT_RTE_NODE_INSTN_ID,SPLT_RTE_NODE_INSTN_ID,JOIN_RTE_NODE_INSTN_ID,VER_NBR) VALUES (',brch_1_id,',"PRIMARY",null,',node_1_id,',null,null,22)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_1_id,',4106,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "Initiated"),',brch_1_id,',0,1,0,8)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_INIT_RTE_NODE_INSTN_T (DOC_HDR_ID,RTE_NODE_INSTN_ID) VALUES (4106,',node_1_id,')');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_2_id FROM KREW_RTE_NODE_S;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_2_id,',4106,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "hasSync"),',brch_1_id,',0,1,0,3)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO brch_2_id FROM KREW_RTE_NODE_S;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_3_id FROM KREW_RTE_NODE_S;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_T (RTE_BRCH_ID,NM,PARNT_ID,INIT_RTE_NODE_INSTN_ID,SPLT_RTE_NODE_INSTN_ID,JOIN_RTE_NODE_INSTN_ID,VER_NBR) VALUES (',brch_2_id,',"False",',brch_1_id,',',node_3_id,',',node_2_id,',null,3)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_3_id,',4106,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "isSyncChild"),',brch_2_id,',0,1,0,2)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_ST_T (RTE_BRCH_ST_ID,RTE_BRCH_ID,KEY_CD,VAL,VER_NBR) VALUES ((SELECT MAX(ID) FROM KREW_RTE_NODE_S),',brch_1_id,',"System.PostProcessorProcessed","true",2)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_ST_T (RTE_BRCH_ST_ID,RTE_BRCH_ID,KEY_CD,VAL,VER_NBR) VALUES ((SELECT MAX(ID) FROM KREW_RTE_NODE_S),',brch_1_id,',"System.PostProcessorFinal","true",1)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
END;
/
CALL p ()
/
DROP PROCEDURE IF EXISTS p
/
CREATE PROCEDURE p ()
BEGIN
    DECLARE brch_1_id INT;
    DECLARE node_1_id INT;
    DECLARE node_2_id INT;
    DECLARE brch_2_id INT;
    DECLARE node_3_id INT;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO brch_1_id FROM KREW_RTE_NODE_S;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_1_id FROM KREW_RTE_NODE_S;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_T (RTE_BRCH_ID,NM,PARNT_ID,INIT_RTE_NODE_INSTN_ID,SPLT_RTE_NODE_INSTN_ID,JOIN_RTE_NODE_INSTN_ID,VER_NBR) VALUES (',brch_1_id,',"PRIMARY",null,',node_1_id,',null,null,22)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_1_id,',4107,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "Initiated"),',brch_1_id,',0,1,0,8)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_INIT_RTE_NODE_INSTN_T (DOC_HDR_ID,RTE_NODE_INSTN_ID) VALUES (4107,',node_1_id,')');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_2_id FROM KREW_RTE_NODE_S;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_2_id,',4107,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "hasSync"),',brch_1_id,',0,1,0,3)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO brch_2_id FROM KREW_RTE_NODE_S;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_3_id FROM KREW_RTE_NODE_S;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_T (RTE_BRCH_ID,NM,PARNT_ID,INIT_RTE_NODE_INSTN_ID,SPLT_RTE_NODE_INSTN_ID,JOIN_RTE_NODE_INSTN_ID,VER_NBR) VALUES (',brch_2_id,',"False",',brch_1_id,',',node_3_id,',',node_2_id,',null,3)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_3_id,',4107,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "isSyncChild"),',brch_2_id,',0,1,0,2)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_ST_T (RTE_BRCH_ST_ID,RTE_BRCH_ID,KEY_CD,VAL,VER_NBR) VALUES ((SELECT MAX(ID) FROM KREW_RTE_NODE_S),',brch_1_id,',"System.PostProcessorProcessed","true",2)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_ST_T (RTE_BRCH_ST_ID,RTE_BRCH_ID,KEY_CD,VAL,VER_NBR) VALUES ((SELECT MAX(ID) FROM KREW_RTE_NODE_S),',brch_1_id,',"System.PostProcessorFinal","true",1)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
END;
/
CALL p ()
/
DROP PROCEDURE IF EXISTS p
/
CREATE PROCEDURE p ()
BEGIN
    DECLARE brch_1_id INT;
    DECLARE node_1_id INT;
    DECLARE node_2_id INT;
    DECLARE brch_2_id INT;
    DECLARE node_3_id INT;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO brch_1_id FROM KREW_RTE_NODE_S;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_1_id FROM KREW_RTE_NODE_S;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_T (RTE_BRCH_ID,NM,PARNT_ID,INIT_RTE_NODE_INSTN_ID,SPLT_RTE_NODE_INSTN_ID,JOIN_RTE_NODE_INSTN_ID,VER_NBR) VALUES (',brch_1_id,',"PRIMARY",null,',node_1_id,',null,null,22)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_1_id,',4108,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "Initiated"),',brch_1_id,',0,1,0,8)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_INIT_RTE_NODE_INSTN_T (DOC_HDR_ID,RTE_NODE_INSTN_ID) VALUES (4108,',node_1_id,')');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_2_id FROM KREW_RTE_NODE_S;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_2_id,',4108,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "hasSync"),',brch_1_id,',0,1,0,3)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO brch_2_id FROM KREW_RTE_NODE_S;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_3_id FROM KREW_RTE_NODE_S;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_T (RTE_BRCH_ID,NM,PARNT_ID,INIT_RTE_NODE_INSTN_ID,SPLT_RTE_NODE_INSTN_ID,JOIN_RTE_NODE_INSTN_ID,VER_NBR) VALUES (',brch_2_id,',"False",',brch_1_id,',',node_3_id,',',node_2_id,',null,3)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_3_id,',4108,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "isSyncChild"),',brch_2_id,',0,1,0,2)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_ST_T (RTE_BRCH_ST_ID,RTE_BRCH_ID,KEY_CD,VAL,VER_NBR) VALUES ((SELECT MAX(ID) FROM KREW_RTE_NODE_S),',brch_1_id,',"System.PostProcessorProcessed","true",2)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_ST_T (RTE_BRCH_ST_ID,RTE_BRCH_ID,KEY_CD,VAL,VER_NBR) VALUES ((SELECT MAX(ID) FROM KREW_RTE_NODE_S),',brch_1_id,',"System.PostProcessorFinal","true",1)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
END;
/
CALL p ()
/
DROP PROCEDURE IF EXISTS p
/
CREATE PROCEDURE p ()
BEGIN
    DECLARE brch_1_id_4101 INT;
    DECLARE node_1_id_4101 INT;
    DECLARE node_2_id_4101 INT;
    DECLARE brch_2_id_4101 INT;
    DECLARE node_3_id_4101 INT;
    DECLARE node_4_id_4101 INT;
    DECLARE node_5_id_4101 INT;
    DECLARE node_6_id_4101 INT;
    DECLARE node_7_id_4101 INT;
    DECLARE brch_1_id_4108 INT;
    DECLARE node_1_id_4108 INT;
    DECLARE node_2_id_4108 INT;
    DECLARE brch_2_id_4108 INT;
    DECLARE node_3_id_4108 INT;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO brch_1_id_4101 FROM KREW_RTE_NODE_S;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_1_id_4101 FROM KREW_RTE_NODE_S;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_T (RTE_BRCH_ID,NM,PARNT_ID,INIT_RTE_NODE_INSTN_ID,SPLT_RTE_NODE_INSTN_ID,JOIN_RTE_NODE_INSTN_ID,VER_NBR) VALUES (',brch_1_id_4101,',"PRIMARY",null,',node_1_id_4101,',null,null,59)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_1_id_4101,',4101,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "Initiated"),',brch_1_id_4101,',0,1,0,19)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_INIT_RTE_NODE_INSTN_T (DOC_HDR_ID,RTE_NODE_INSTN_ID) VALUES (4101,',node_1_id_4101,')');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_2_id_4101 FROM KREW_RTE_NODE_S;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_2_id_4101,',4101,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "hasSync"),',brch_1_id_4101,',0,1,0,9)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO brch_2_id_4101 FROM KREW_RTE_NODE_S;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_3_id_4101 FROM KREW_RTE_NODE_S;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_4_id_4101 FROM KREW_RTE_NODE_S;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_5_id_4101 FROM KREW_RTE_NODE_S;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_6_id_4101 FROM KREW_RTE_NODE_S;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_7_id_4101 FROM KREW_RTE_NODE_S;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_T (RTE_BRCH_ID,NM,PARNT_ID,INIT_RTE_NODE_INSTN_ID,SPLT_RTE_NODE_INSTN_ID,JOIN_RTE_NODE_INSTN_ID,VER_NBR) VALUES (',brch_2_id_4101,',"true",',brch_1_id_4101,',',node_3_id_4101,',',node_2_id_4101,',',node_7_id_4101,',28)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_3_id_4101,',4101,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "SyncValidationApproval"),',brch_2_id_4101,',0,1,0,9)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_4_id_4101,',4101,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "AwardStandardApprovalInSync"),',brch_2_id_4101,',0,1,0,6)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_5_id_4101,',4101,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "ApplySync"),',brch_2_id_4101,',0,1,0,4)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_6_id_4101,',4101,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "SyncFYIs"),',brch_2_id_4101,',0,1,0,3)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO brch_1_id_4108 FROM KREW_RTE_NODE_S;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_1_id_4108 FROM KREW_RTE_NODE_S;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_T (RTE_BRCH_ID,NM,PARNT_ID,INIT_RTE_NODE_INSTN_ID,SPLT_RTE_NODE_INSTN_ID,JOIN_RTE_NODE_INSTN_ID,VER_NBR) VALUES (',brch_1_id_4108,',"PRIMARY",null,',node_1_id_4108,',null,null,30)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_1_id_4108,',4109,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "Initiated"),',node_1_id_4108,',0,1,0,13)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_INIT_RTE_NODE_INSTN_T (DOC_HDR_ID,RTE_NODE_INSTN_ID) VALUES (4109,',node_1_id_4108,')');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_2_id_4108 FROM KREW_RTE_NODE_S;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_2_id_4108,',4109,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "hasSync"),',node_1_id_4108,',0,1,0,3)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO brch_2_id_4108 FROM KREW_RTE_NODE_S;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SELECT MAX(ID) INTO node_3_id_4108 FROM KREW_RTE_NODE_S;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_T (RTE_BRCH_ID,NM,PARNT_ID,INIT_RTE_NODE_INSTN_ID,SPLT_RTE_NODE_INSTN_ID,JOIN_RTE_NODE_INSTN_ID,VER_NBR) VALUES (',brch_2_id_4108,',"true",',brch_1_id_4108,',',node_3_id_4108,',',node_2_id_4108,',null,3)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_3_id_4108,',4109,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "SyncValidationApproval"),',brch_2_id_4108,',1,0,0,2)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES (',node_7_id_4101,',4101,(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "AwardDocument") AND NM = "JoinSync"),',brch_1_id_4101,',0,1,0,9)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_ST_T (RTE_NODE_INSTN_ST_ID,RTE_NODE_INSTN_ID,KEY_CD,VAL,VER_NBR) VALUES ((SELECT MAX(ID) FROM KREW_RTE_NODE_S),',node_7_id_4101,',"ExpectedJoiners","',node_3_id_4108,',",8)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_NODE_INSTN_ST_T (RTE_NODE_INSTN_ST_ID,RTE_NODE_INSTN_ID,KEY_CD,VAL,VER_NBR) VALUES ((SELECT MAX(ID) FROM KREW_RTE_NODE_S),',node_7_id_4101,',"ActualJoiners","',node_3_id_4108,',",8)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_ST_T (RTE_BRCH_ST_ID,RTE_BRCH_ID,KEY_CD,VAL,VER_NBR) VALUES ((SELECT MAX(ID) FROM KREW_RTE_NODE_S),',brch_1_id_4108,',"System.PostProcessorProcessed","true",2)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
    INSERT INTO KREW_RTE_NODE_S VALUES(NULL);
    SET @insert_brch := CONCAT('INSERT INTO KREW_RTE_BRCH_ST_T (RTE_BRCH_ST_ID,RTE_BRCH_ID,KEY_CD,VAL,VER_NBR) VALUES ((SELECT MAX(ID) FROM KREW_RTE_NODE_S),',brch_1_id_4108,',"System.PostProcessorFinal","true",1)');
    PREPARE insert_brch_stmt FROM @insert_brch;
    EXECUTE insert_brch_stmt;
    DEALLOCATE PREPARE insert_brch_stmt;
END;
/
CALL p ()
/
DELIMITER ;
