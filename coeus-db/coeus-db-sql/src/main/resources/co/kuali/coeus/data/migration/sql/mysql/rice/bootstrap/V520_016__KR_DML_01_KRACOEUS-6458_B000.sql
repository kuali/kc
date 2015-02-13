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


INSERT INTO KRCR_NMSPC_T (NMSPC_CD,OBJ_ID,VER_NBR, NM, ACTV_IND, APPL_ID )
VALUES ('KC-PROTOCOL-DOC',UUID(),1, 'IRB Protocol Doc Level Nmspc' , 'Y', 'KC')
/
	

INSERT INTO KRIM_ROLE_ID_S VALUES(NULL)
/

INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
  VALUES((SELECT (MAX(ID)) FROM KRIM_ROLE_ID_S), UUID(), 1, 'Protocol Aggregator', 'KC-PROTOCOL-DOC', 'Added to Document Qualified Role memberships for corresponding Role in KC-PROTOCOL namespace', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Unit'), 'Y', NOW());
/

INSERT INTO KRIM_ROLE_ID_S VALUES(NULL)
/

INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
  VALUES((SELECT (MAX(ID)) FROM KRIM_ROLE_ID_S), UUID(), 1, 'Protocol Viewer', 'KC-PROTOCOL-DOC', 'Added to Document Qualified Role memberships for corresponding Role in KC-PROTOCOL namespace', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Unit'), 'Y', NOW());
/

        

DROP PROCEDURE IF EXISTS update_irb_protocol_document_derived_pi_role
/
CREATE PROCEDURE update_irb_protocol_document_derived_pi_role ()
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE permId VARCHAR(40);
    DECLARE newId VARCHAR(40);
    DECLARE rolePermCount INT DEFAULT 0;    
    DECLARE cur_irb_aggregator_perms CURSOR FOR  SELECT  RP.PERM_ID
  FROM { OJ KRIM_ROLE_T RO LEFT OUTER JOIN KRIM_ROLE_PERM_T RP ON RP.ROLE_ID=RO.ROLE_ID}
  WHERE RO.NMSPC_CD = 'KC-PROTOCOL' AND RO.ROLE_NM = 'Protocol Aggregator';
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
  
    OPEN cur_irb_aggregator_perms;
    
        insert_irb_protocol_aggr_perm_loop: LOOP
        FETCH cur_irb_aggregator_perms INTO permId;
        IF done THEN
            LEAVE insert_irb_protocol_aggr_perm_loop;
        END IF;
        SELECT COUNT(*) INTO rolePermCount FROM KRIM_ROLE_PERM_T WHERE ROLE_ID = (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND ROLE_NM = 'PI') AND PERM_ID = permId;
        IF rolePermCount = 0 THEN
            INSERT INTO KRIM_ROLE_PERM_ID_S VALUES (null);
            SELECT CONCAT('KC', MAX(ID)) INTO newId FROM KRIM_ROLE_PERM_ID_S;
            INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) VALUES (newId,(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND ROLE_NM = 'PI'),permId,'Y',UUID(),1);        
        END IF;
    END LOOP;
    
    CLOSE cur_irb_aggregator_perms;
END;
/

CALL update_irb_protocol_document_derived_pi_role ()
/

DELIMITER ;

