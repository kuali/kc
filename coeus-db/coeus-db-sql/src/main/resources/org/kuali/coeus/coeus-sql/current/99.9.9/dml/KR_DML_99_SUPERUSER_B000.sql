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

DECLARE roleCount NUMBER;
        rolePermCount NUMBER;
BEGIN
    SELECT COUNT(*) INTO roleCount FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-SYS' AND ROLE_NM = 'KC Superuser';
    IF roleCount = 0 THEN   
      INSERT INTO KRIM_ROLE_T (ROLE_ID,KIM_TYP_ID,NMSPC_CD,ROLE_NM,DESC_TXT,ACTV_IND,LAST_UPDT_DT,OBJ_ID,VER_NBR) 
          VALUES (CONCAT('KC',KRIM_ROLE_ID_S.NEXTVAL),(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'Default'),'KC-SYS','KC Superuser','KC Superuser role for administration access','Y',SYSDATE,SYS_GUID(),1);
      COMMIT;
    END IF;
END;
/

DECLARE roleMbrCount NUMBER;
BEGIN
    SELECT COUNT(*) INTO roleMbrCount FROM KRIM_ROLE_MBR_T WHERE ROLE_ID = (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-SYS' AND ROLE_NM = 'KC Superuser') AND MBR_ID = (SELECT PRNCPL_ID FROM KRIM_PRNCPL_T WHERE PRNCPL_NM = 'admin');

    IF roleMbrCount = 0 THEN
        INSERT INTO KRIM_ROLE_MBR_T (ROLE_MBR_ID,ROLE_ID,MBR_ID,MBR_TYP_CD,LAST_UPDT_DT,OBJ_ID,VER_NBR) 
            VALUES (CONCAT('KC',KRIM_ROLE_MBR_ID_S.NEXTVAL),(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-SYS' AND ROLE_NM = 'KC Superuser'),(SELECT PRNCPL_ID FROM KRIM_PRNCPL_T WHERE PRNCPL_NM = 'admin'),'P',SYSDATE,SYS_GUID(),1);
        INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID,ROLE_MBR_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,OBJ_ID,VER_NBR) 
            VALUES (CONCAT('KC',KRIM_ATTR_DATA_ID_S.NEXTVAL),CONCAT('KC',KRIM_ROLE_MBR_ID_S.CURRVAL),(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'UnitHierarchy'),(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = 'unitNumber'),'000001',SYS_GUID(),1);
        INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID,ROLE_MBR_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,OBJ_ID,VER_NBR) 
            VALUES (CONCAT('KC',KRIM_ATTR_DATA_ID_S.NEXTVAL),CONCAT('KC',KRIM_ROLE_MBR_ID_S.CURRVAL),(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'UnitHierarchy'),(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = 'subunits'),'Y',SYS_GUID(),1);
    END IF;
END;
/

DECLARE rolePermCount NUMBER;
        CURSOR cur IS SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD LIKE 'KC%';
BEGIN
    FOR rec IN cur 
    LOOP         
        EXECUTE IMMEDIATE 'SELECT COUNT(*) FROM KRIM_ROLE_PERM_T WHERE ROLE_ID = (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = ''KC-SYS'' AND ROLE_NM = ''KC Superuser'') AND PERM_ID = (:1)' INTO rolePermCount USING rec.PERM_ID;
        IF rolePermCount = 0 THEN           
            EXECUTE IMMEDIATE 'INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, ROLE_ID, PERM_ID, ACTV_IND, OBJ_ID, VER_NBR) VALUES (CONCAT(''KC'',KRIM_ROLE_PERM_ID_S.NEXTVAL),(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = ''KC-SYS'' AND ROLE_NM = ''KC Superuser''),(:1),''Y'',SYS_GUID(),1)' USING rec.PERM_ID;          
        	COMMIT;
        END IF;
    END LOOP;
END;
/
