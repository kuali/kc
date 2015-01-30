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


-- add all role memberships that are currently in KC-PROTOCOL:Protocol Viewer namespace:role to the KC-PROTOCOL-DOC:Protocol Aggregator 
-- also need to limit this to truly unit qualified memberships and associate the RoleMembership to the unit in ROLE_MBR_ATTR_DATA_T
DROP PROCEDURE IF EXISTS copy_protocol_doc_role_aggregators
/
CREATE PROCEDURE copy_protocol_doc_role_aggregators ()
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE newRmId VARCHAR(40);
    DECLARE newRmaId VARCHAR(40);
    DECLARE roleId VARCHAR(40);
    DECLARE destRoleId VARCHAR(40);
    DECLARE mbrId VARCHAR(40);
    DECLARE attrVal VARCHAR(40);
    DECLARE mbrTypCd CHAR(1);    
    DECLARE kimTypId INT DEFAULT 0;    
    DECLARE kimAttrDefnId INT DEFAULT 0;    
    DECLARE cur_irb_doc_role CURSOR FOR SELECT RMT.MBR_ID, RMT.ROLE_ID, RMT.MBR_TYP_CD, RMAD.ATTR_VAL, RMAD.KIM_TYP_ID, RMAD.KIM_ATTR_DEFN_ID  
      FROM { OJ KRIM_ROLE_MBR_T RMT LEFT OUTER JOIN KRIM_ROLE_MBR_ATTR_DATA_T RMAD ON RMT.ROLE_MBR_ID = RMAD.ROLE_MBR_ID}
        WHERE RMT.ROLE_ID = (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND ROLE_NM = 'Protocol Aggregator') 
          AND RMAD.KIM_TYP_ID = (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Unit') 
          AND RMAD.KIM_ATTR_DEFN_ID = (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM='unitNumber')
          AND RMT.MBR_ID NOT IN (SELECT RMT2.MBR_ID  
               FROM { OJ KRIM_ROLE_MBR_T RMT2 LEFT OUTER JOIN KRIM_ROLE_MBR_ATTR_DATA_T RMAD2 ON RMT2.ROLE_MBR_ID = RMAD2.ROLE_MBR_ID}
                 WHERE RMT2.ROLE_ID = (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-PROTOCOL-DOC' AND ROLE_NM = 'Protocol Aggregator') 
                   AND RMAD2.KIM_TYP_ID = (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Unit') 
                   AND RMAD2.KIM_ATTR_DEFN_ID = (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM='unitNumber'));
          
            
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    SELECT ROLE_ID INTO destRoleId FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-PROTOCOL-DOC' AND ROLE_NM = 'Protocol Aggregator';
    OPEN cur_irb_doc_role;

    insert_protocol_doc_role_members: LOOP
        FETCH cur_irb_doc_role INTO mbrId, roleId, mbrTypCd, attrVal, kimTypId, kimAttrDefnId;
        IF done THEN
            LEAVE insert_protocol_doc_role_members;
        END IF;

        INSERT INTO KRIM_ROLE_MBR_ID_S VALUES (null);
        SELECT CONCAT('KC', MAX(ID)) INTO newRmId FROM KRIM_ROLE_MBR_ID_S;
        INSERT INTO KRIM_ROLE_MBR_T (ROLE_MBR_ID,ROLE_ID,MBR_ID,MBR_TYP_CD,LAST_UPDT_DT,OBJ_ID,VER_NBR) 
          VALUES (newRmId,destRoleId,mbrId,mbrTypCd,NOW(),UUID(),1);
      
        INSERT INTO KRIM_ATTR_DATA_ID_S VALUES (null);
        SELECT CONCAT('KC', MAX(ID)) INTO newRmaId FROM KRIM_ATTR_DATA_ID_S;
        INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID,ROLE_MBR_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,OBJ_ID,VER_NBR) 
          VALUES (newRmaId,newRmId,kimTypId,kimAttrDefnId,attrVal,UUID(),1);

    END LOOP;
    
    CLOSE cur_irb_doc_role;
END;
/


CALL copy_protocol_doc_role_aggregators ()
/



-- add all role memberships that are currently in KC-PROTOCOL:Protocol Viewer namespace:role to the KC-PROTOCOL-DOC:Protocol Viewer 
-- also need to limit this to truly unit qualified memberships and associate the RoleMembership to the unit in ROLE_MBR_ATTR_DATA_T
DROP PROCEDURE IF EXISTS copy_protocol_doc_role_viewers
/
CREATE PROCEDURE copy_protocol_doc_role_viewers ()
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE newRmId VARCHAR(40);
    DECLARE newRmaId VARCHAR(40);
    DECLARE roleId VARCHAR(40);
    DECLARE destRoleId VARCHAR(40);
    DECLARE mbrId VARCHAR(40);
    DECLARE attrVal VARCHAR(40);
    DECLARE mbrTypCd CHAR(1);    
    DECLARE kimTypId INT DEFAULT 0;    
    DECLARE kimAttrDefnId INT DEFAULT 0;    
    DECLARE cur_irb_doc_role CURSOR FOR SELECT RMT.MBR_ID, RMT.ROLE_ID, RMT.MBR_TYP_CD, RMAD.ATTR_VAL, RMAD.KIM_TYP_ID, RMAD.KIM_ATTR_DEFN_ID  
      FROM { OJ KRIM_ROLE_MBR_T RMT LEFT OUTER JOIN KRIM_ROLE_MBR_ATTR_DATA_T RMAD ON RMT.ROLE_MBR_ID = RMAD.ROLE_MBR_ID}
        WHERE RMT.ROLE_ID = (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND ROLE_NM = 'Protocol Viewer') 
          AND RMAD.KIM_TYP_ID = (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Unit') 
          AND RMAD.KIM_ATTR_DEFN_ID = (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM='unitNumber')
          AND RMT.MBR_ID NOT IN (SELECT RMT2.MBR_ID  
               FROM { OJ KRIM_ROLE_MBR_T RMT2 LEFT OUTER JOIN KRIM_ROLE_MBR_ATTR_DATA_T RMAD2 ON RMT2.ROLE_MBR_ID = RMAD2.ROLE_MBR_ID}
                 WHERE RMT2.ROLE_ID = (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-PROTOCOL-DOC' AND ROLE_NM = 'Protocol Viewer') 
                   AND RMAD2.KIM_TYP_ID = (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Unit') 
                   AND RMAD2.KIM_ATTR_DEFN_ID = (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM='unitNumber'));  
                   
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    SELECT ROLE_ID INTO destRoleId FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-PROTOCOL-DOC' AND ROLE_NM = 'Protocol Viewer';
    OPEN cur_irb_doc_role;

    insert_protocol_doc_role_members: LOOP
        FETCH cur_irb_doc_role INTO mbrId, roleId, mbrTypCd, attrVal, kimTypId, kimAttrDefnId;
        IF done THEN
            LEAVE insert_protocol_doc_role_members;
        END IF;

   
        INSERT INTO KRIM_ROLE_MBR_ID_S VALUES (null);
        SELECT CONCAT('KC', MAX(ID)) INTO newRmId FROM KRIM_ROLE_MBR_ID_S;
        INSERT INTO KRIM_ROLE_MBR_T (ROLE_MBR_ID,ROLE_ID,MBR_ID,MBR_TYP_CD,LAST_UPDT_DT,OBJ_ID,VER_NBR) 
          VALUES (newRmId,destRoleId,mbrId,mbrTypCd,NOW(),UUID(),1);
      
        INSERT INTO KRIM_ATTR_DATA_ID_S VALUES (null);
        SELECT CONCAT('KC', MAX(ID)) INTO newRmaId FROM KRIM_ATTR_DATA_ID_S;
        INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID,ROLE_MBR_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,OBJ_ID,VER_NBR) 
          VALUES (newRmaId,newRmId,kimTypId,kimAttrDefnId,attrVal,UUID(),1);

    END LOOP;
    
    CLOSE cur_irb_doc_role;
END;
/


CALL copy_protocol_doc_role_viewers ()
/

DELIMITER ;

