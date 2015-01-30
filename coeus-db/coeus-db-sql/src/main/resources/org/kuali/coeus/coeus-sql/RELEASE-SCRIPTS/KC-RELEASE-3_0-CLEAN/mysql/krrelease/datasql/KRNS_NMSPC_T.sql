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

delimiter /
TRUNCATE TABLE KRNS_NMSPC_T
/
INSERT INTO KRNS_NMSPC_T (ACTV_IND,APPL_NMSPC_CD,NM,NMSPC_CD,OBJ_ID,VER_NBR)
  VALUES ('Y','RICE','Service Bus','KR-BUS','5B960CFDBB370FDFE0404F8189D83CBD',1)
/
INSERT INTO KRNS_NMSPC_T (ACTV_IND,APPL_NMSPC_CD,NM,NMSPC_CD,OBJ_ID,VER_NBR)
  VALUES ('Y','RICE','Identity Management','KR-IDM','61645D045B0005D7E0404F8189D849B1',1)
/
INSERT INTO KRNS_NMSPC_T (ACTV_IND,APPL_NMSPC_CD,NM,NMSPC_CD,OBJ_ID,VER_NBR)
  VALUES ('Y','RICE','Kuali Nervous System','KR-NS','53680C68F595AD9BE0404F8189D80A6C',1)
/
INSERT INTO KRNS_NMSPC_T (ACTV_IND,APPL_NMSPC_CD,NM,NMSPC_CD,OBJ_ID,VER_NBR)
  VALUES ('Y','RICE','Notification','KR-NTFCN','5B960CFDBB360FDFE0404F8189D83CBD',1)
/
INSERT INTO KRNS_NMSPC_T (ACTV_IND,APPL_NMSPC_CD,NM,NMSPC_CD,OBJ_ID,VER_NBR)
  VALUES ('Y','RICE','Enterprise Infrastructure','KR-SYS','5B960CFDBB390FDFE0404F8189D83CBD',1)
/
INSERT INTO KRNS_NMSPC_T (ACTV_IND,APPL_NMSPC_CD,NM,NMSPC_CD,OBJ_ID,VER_NBR)
  VALUES ('Y','RICE','Workflow','KR-WKFLW','5E1D690C419B3E2EE0404F8189D82677',0)
/
INSERT INTO KRNS_NMSPC_T (ACTV_IND,NM,NMSPC_CD,OBJ_ID,VER_NBR)
  VALUES ('Y','Kuali Systems','KUALI','5ADF18B6D4817954E0404F8189D85002',1)
/
delimiter ;
