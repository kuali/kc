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

-- add all role memberships that are currently in KC-PROTOCOL:Protocol Aggregator namespace:role to the KC-PROTOCOL-DOC:Protocol Aggregator 
-- also need to limit this to truly unit qualified memberships and associate the RoleMembership to the unit in ROLE_MBR_ATTR_DATA_T
DECLARE

   CURSOR role_mbr_data IS SELECT RMT.MBR_ID, RMT.MBR_TYP_CD, RMAD.ATTR_VAL, RMAD.KIM_TYP_ID, RMAD.KIM_ATTR_DEFN_ID FROM KRIM_ROLE_MBR_T RMT 
     JOIN KRIM_ROLE_MBR_ATTR_DATA_T RMAD ON RMT.ROLE_MBR_ID = RMAD.ROLE_MBR_ID 
         WHERE RMT.ROLE_ID = (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND ROLE_NM = 'Protocol Aggregator') 
            AND RMAD.KIM_TYP_ID = (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Unit') 
            AND RMAD.KIM_ATTR_DEFN_ID = (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM='unitNumber')
    minus
    SELECT RMT.MBR_ID, RMT.MBR_TYP_CD, RMAD.ATTR_VAL, RMAD.KIM_TYP_ID, RMAD.KIM_ATTR_DEFN_ID FROM KRIM_ROLE_MBR_T RMT 
     JOIN KRIM_ROLE_MBR_ATTR_DATA_T RMAD ON RMT.ROLE_MBR_ID = RMAD.ROLE_MBR_ID 
         WHERE RMT.ROLE_ID = (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-PROTOCOL-DOC' AND ROLE_NM = 'Protocol Aggregator') 
            AND RMAD.KIM_TYP_ID = (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Unit') 
            AND RMAD.KIM_ATTR_DEFN_ID = (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM='unitNumber');

BEGIN
   FOR record IN role_mbr_data 
     LOOP
      INSERT INTO KRIM_ROLE_MBR_T (ROLE_MBR_ID,ROLE_ID,MBR_ID,MBR_TYP_CD,LAST_UPDT_DT,OBJ_ID,VER_NBR) 
        VALUES (CONCAT('KC',KRIM_ROLE_MBR_ID_S.NEXTVAL),(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-PROTOCOL-DOC' AND ROLE_NM = 'Protocol Aggregator'),record.MBR_ID, record.MBR_TYP_CD,SYSDATE,SYS_GUID(),1 );


      INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID,ROLE_MBR_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,OBJ_ID,VER_NBR) 
        VALUES (CONCAT('KC',KRIM_ATTR_DATA_ID_S.NEXTVAL),CONCAT('KC',KRIM_ROLE_MBR_ID_S.CURRVAL),record.KIM_TYP_ID, record.KIM_ATTR_DEFN_ID, record.ATTR_VAL,SYS_GUID(),1);
  
   COMMIT;
  END LOOP;
END;
/

-- add all role memberships that are currently in KC-PROTOCOL:Protocol Aggregator namespace:role to the KC-PROTOCOL-DOC:Protocol Viewer 
-- also need to limit this to truly unit qualified memberships and associate the RoleMembership to the unit in ROLE_MBR_ATTR_DATA_T
DECLARE

   CURSOR role_mbr_data IS SELECT RMT.MBR_ID, RMT.MBR_TYP_CD, RMAD.ATTR_VAL, RMAD.KIM_TYP_ID, RMAD.KIM_ATTR_DEFN_ID FROM KRIM_ROLE_MBR_T RMT 
     JOIN KRIM_ROLE_MBR_ATTR_DATA_T RMAD ON RMT.ROLE_MBR_ID = RMAD.ROLE_MBR_ID 
         WHERE RMT.ROLE_ID = (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND ROLE_NM = 'Protocol Viewer') 
            AND RMAD.KIM_TYP_ID = (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Unit') 
            AND RMAD.KIM_ATTR_DEFN_ID = (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM='unitNumber')
    minus
    SELECT RMT.MBR_ID, RMT.MBR_TYP_CD, RMAD.ATTR_VAL, RMAD.KIM_TYP_ID, RMAD.KIM_ATTR_DEFN_ID FROM KRIM_ROLE_MBR_T RMT 
     JOIN KRIM_ROLE_MBR_ATTR_DATA_T RMAD ON RMT.ROLE_MBR_ID = RMAD.ROLE_MBR_ID 
         WHERE RMT.ROLE_ID = (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-PROTOCOL-DOC' AND ROLE_NM = 'Protocol Viewer') 
            AND RMAD.KIM_TYP_ID = (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Unit') 
            AND RMAD.KIM_ATTR_DEFN_ID = (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM='unitNumber');

BEGIN
   FOR record IN role_mbr_data 
     LOOP
      INSERT INTO KRIM_ROLE_MBR_T (ROLE_MBR_ID,ROLE_ID,MBR_ID,MBR_TYP_CD,LAST_UPDT_DT,OBJ_ID,VER_NBR) 
        VALUES (CONCAT('KC',KRIM_ROLE_MBR_ID_S.NEXTVAL),(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-PROTOCOL-DOC' AND ROLE_NM = 'Protocol Viewer'),record.MBR_ID, record.MBR_TYP_CD,SYSDATE,SYS_GUID(),1 );


      INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID,ROLE_MBR_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,OBJ_ID,VER_NBR) 
        VALUES (CONCAT('KC',KRIM_ATTR_DATA_ID_S.NEXTVAL),CONCAT('KC',KRIM_ROLE_MBR_ID_S.CURRVAL),record.KIM_TYP_ID, record.KIM_ATTR_DEFN_ID, record.ATTR_VAL,SYS_GUID(),1);
  
   COMMIT;
  END LOOP;
END;
/

