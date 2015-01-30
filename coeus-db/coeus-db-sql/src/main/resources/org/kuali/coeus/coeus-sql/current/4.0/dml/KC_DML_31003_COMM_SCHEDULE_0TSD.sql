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

INSERT INTO COMM_SCHEDULE (ID,SCHEDULE_ID,COMMITTEE_ID_FK,SCHEDULED_DATE,PLACE,TIME,PROTOCOL_SUB_DEADLINE,SCHEDULE_STATUS_CODE,START_TIME,END_TIME,MAX_PROTOCOLS,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_COMMITTEE_ID.NEXTVAL,SEQ_COMMITTEE_ID.NEXTVAL,(SELECT ID FROM COMMITTEE WHERE COMMITTEE_NAME = 'KC IRB 1'),TO_DATE('20100719','YYYYMMDD'),'EHOB 341',TO_DATE('197001011300','YYYYMMDDHH24MI'),TO_DATE('20100717','YYYYMMDD'),(SELECT SCHEDULE_STATUS_CODE FROM SCHEDULE_STATUS WHERE DESCRIPTION = 'Scheduled'),TO_DATE('19700101','YYYYMMDD'),TO_DATE('19700101','YYYYMMDD'),22,'quickstart',SYSDATE,SYS_GUID(),0)
/
INSERT INTO COMM_SCHEDULE (ID,SCHEDULE_ID,COMMITTEE_ID_FK,SCHEDULED_DATE,PLACE,TIME,PROTOCOL_SUB_DEADLINE,SCHEDULE_STATUS_CODE,START_TIME,END_TIME,MAX_PROTOCOLS,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_COMMITTEE_ID.NEXTVAL,SEQ_COMMITTEE_ID.NEXTVAL,(SELECT ID FROM COMMITTEE WHERE COMMITTEE_NAME = 'KC IRB 1'),TO_DATE('20100816','YYYYMMDD'),'EHOB 341',TO_DATE('197001011300','YYYYMMDDHH24MI'),TO_DATE('20100814','YYYYMMDD'),(SELECT SCHEDULE_STATUS_CODE FROM SCHEDULE_STATUS WHERE DESCRIPTION = 'Scheduled'),TO_DATE('19700101','YYYYMMDD'),TO_DATE('19700101','YYYYMMDD'),22,'quickstart',SYSDATE,SYS_GUID(),0)
/
INSERT INTO COMM_SCHEDULE (ID,SCHEDULE_ID,COMMITTEE_ID_FK,SCHEDULED_DATE,PLACE,TIME,PROTOCOL_SUB_DEADLINE,SCHEDULE_STATUS_CODE,START_TIME,END_TIME,MAX_PROTOCOLS,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_COMMITTEE_ID.NEXTVAL,SEQ_COMMITTEE_ID.NEXTVAL,(SELECT ID FROM COMMITTEE WHERE COMMITTEE_NAME = 'KC IRB 1'),TO_DATE('20100920','YYYYMMDD'),'EHOB 341',TO_DATE('197001011300','YYYYMMDDHH24MI'),TO_DATE('20100918','YYYYMMDD'),(SELECT SCHEDULE_STATUS_CODE FROM SCHEDULE_STATUS WHERE DESCRIPTION = 'Scheduled'),TO_DATE('19700101','YYYYMMDD'),TO_DATE('19700101','YYYYMMDD'),22,'quickstart',SYSDATE,SYS_GUID(),0)
/
INSERT INTO COMM_SCHEDULE (ID,SCHEDULE_ID,COMMITTEE_ID_FK,SCHEDULED_DATE,PLACE,TIME,PROTOCOL_SUB_DEADLINE,SCHEDULE_STATUS_CODE,START_TIME,END_TIME,MAX_PROTOCOLS,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_COMMITTEE_ID.NEXTVAL,SEQ_COMMITTEE_ID.NEXTVAL,(SELECT ID FROM COMMITTEE WHERE COMMITTEE_NAME = 'KC IRB 1'),TO_DATE('20101018','YYYYMMDD'),'Day 120',TO_DATE('197001011400','YYYYMMDDHH24MI'),TO_DATE('20101016','YYYYMMDD'),(SELECT SCHEDULE_STATUS_CODE FROM SCHEDULE_STATUS WHERE DESCRIPTION = 'Scheduled'),TO_DATE('19700101','YYYYMMDD'),TO_DATE('19700101','YYYYMMDD'),22,'quickstart',SYSDATE,SYS_GUID(),0)
/
INSERT INTO COMM_SCHEDULE (ID,SCHEDULE_ID,COMMITTEE_ID_FK,SCHEDULED_DATE,PLACE,TIME,PROTOCOL_SUB_DEADLINE,SCHEDULE_STATUS_CODE,START_TIME,END_TIME,MAX_PROTOCOLS,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_COMMITTEE_ID.NEXTVAL,SEQ_COMMITTEE_ID.NEXTVAL,(SELECT ID FROM COMMITTEE WHERE COMMITTEE_NAME = 'KC IRB 1'),TO_DATE('20101115','YYYYMMDD'),'EHOB 341',TO_DATE('197001011300','YYYYMMDDHH24MI'),TO_DATE('20101113','YYYYMMDD'),(SELECT SCHEDULE_STATUS_CODE FROM SCHEDULE_STATUS WHERE DESCRIPTION = 'Scheduled'),TO_DATE('19700101','YYYYMMDD'),TO_DATE('19700101','YYYYMMDD'),22,'quickstart',SYSDATE,SYS_GUID(),0)
/
INSERT INTO COMM_SCHEDULE (ID,SCHEDULE_ID,COMMITTEE_ID_FK,SCHEDULED_DATE,PLACE,TIME,PROTOCOL_SUB_DEADLINE,SCHEDULE_STATUS_CODE,START_TIME,END_TIME,MAX_PROTOCOLS,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_COMMITTEE_ID.NEXTVAL,SEQ_COMMITTEE_ID.NEXTVAL,(SELECT ID FROM COMMITTEE WHERE COMMITTEE_NAME = 'KC IRB 1'),TO_DATE('20101220','YYYYMMDD'),'EHOB 341',TO_DATE('197001011300','YYYYMMDDHH24MI'),TO_DATE('20101218','YYYYMMDD'),(SELECT SCHEDULE_STATUS_CODE FROM SCHEDULE_STATUS WHERE DESCRIPTION = 'Scheduled'),TO_DATE('19700101','YYYYMMDD'),TO_DATE('19700101','YYYYMMDD'),300,'quickstart',SYSDATE,SYS_GUID(),0)
/
INSERT INTO COMM_SCHEDULE (ID,SCHEDULE_ID,COMMITTEE_ID_FK,SCHEDULED_DATE,PLACE,TIME,PROTOCOL_SUB_DEADLINE,SCHEDULE_STATUS_CODE,START_TIME,END_TIME,MAX_PROTOCOLS,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_COMMITTEE_ID.NEXTVAL,SEQ_COMMITTEE_ID.NEXTVAL,(SELECT ID FROM COMMITTEE WHERE COMMITTEE_NAME = 'KC IRB 1'),TO_DATE('20110117','YYYYMMDD'),'EHOB 341',TO_DATE('197001011300','YYYYMMDDHH24MI'),TO_DATE('20110115','YYYYMMDD'),(SELECT SCHEDULE_STATUS_CODE FROM SCHEDULE_STATUS WHERE DESCRIPTION = 'Scheduled'),TO_DATE('19700101','YYYYMMDD'),TO_DATE('19700101','YYYYMMDD'),22,'quickstart',SYSDATE,SYS_GUID(),0)
/
INSERT INTO COMM_SCHEDULE (ID,SCHEDULE_ID,COMMITTEE_ID_FK,SCHEDULED_DATE,PLACE,TIME,PROTOCOL_SUB_DEADLINE,SCHEDULE_STATUS_CODE,START_TIME,END_TIME,MAX_PROTOCOLS,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_COMMITTEE_ID.NEXTVAL,SEQ_COMMITTEE_ID.NEXTVAL,(SELECT ID FROM COMMITTEE WHERE COMMITTEE_NAME = 'KC IRB 1'),TO_DATE('20110221','YYYYMMDD'),'EHOB 341',TO_DATE('197001011300','YYYYMMDDHH24MI'),TO_DATE('20110219','YYYYMMDD'),(SELECT SCHEDULE_STATUS_CODE FROM SCHEDULE_STATUS WHERE DESCRIPTION = 'Scheduled'),TO_DATE('19700101','YYYYMMDD'),TO_DATE('19700101','YYYYMMDD'),22,'quickstart',SYSDATE,SYS_GUID(),0)
/
INSERT INTO COMM_SCHEDULE (ID,SCHEDULE_ID,COMMITTEE_ID_FK,SCHEDULED_DATE,PLACE,TIME,PROTOCOL_SUB_DEADLINE,SCHEDULE_STATUS_CODE,START_TIME,END_TIME,MAX_PROTOCOLS,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_COMMITTEE_ID.NEXTVAL,SEQ_COMMITTEE_ID.NEXTVAL,(SELECT ID FROM COMMITTEE WHERE COMMITTEE_NAME = 'KC IRB 1'),TO_DATE('20110321','YYYYMMDD'),'EHOB 341',TO_DATE('197001011300','YYYYMMDDHH24MI'),TO_DATE('20110319','YYYYMMDD'),(SELECT SCHEDULE_STATUS_CODE FROM SCHEDULE_STATUS WHERE DESCRIPTION = 'Scheduled'),TO_DATE('19700101','YYYYMMDD'),TO_DATE('19700101','YYYYMMDD'),22,'quickstart',SYSDATE,SYS_GUID(),0)
/
INSERT INTO COMM_SCHEDULE (ID,SCHEDULE_ID,COMMITTEE_ID_FK,SCHEDULED_DATE,PLACE,TIME,PROTOCOL_SUB_DEADLINE,SCHEDULE_STATUS_CODE,START_TIME,END_TIME,MAX_PROTOCOLS,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_COMMITTEE_ID.NEXTVAL,SEQ_COMMITTEE_ID.NEXTVAL,(SELECT ID FROM COMMITTEE WHERE COMMITTEE_NAME = 'KC IRB 1'),TO_DATE('20110421','YYYYMMDD'),'Day 120',TO_DATE('197001011400','YYYYMMDDHH24MI'),TO_DATE('20110415','YYYYMMDD'),(SELECT SCHEDULE_STATUS_CODE FROM SCHEDULE_STATUS WHERE DESCRIPTION = 'Scheduled'),TO_DATE('19700101','YYYYMMDD'),TO_DATE('19700101','YYYYMMDD'),22,'quickstart',SYSDATE,SYS_GUID(),0)
/
INSERT INTO COMM_SCHEDULE (ID,SCHEDULE_ID,COMMITTEE_ID_FK,SCHEDULED_DATE,PLACE,TIME,PROTOCOL_SUB_DEADLINE,SCHEDULE_STATUS_CODE,START_TIME,END_TIME,MAX_PROTOCOLS,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_COMMITTEE_ID.NEXTVAL,SEQ_COMMITTEE_ID.NEXTVAL,(SELECT ID FROM COMMITTEE WHERE COMMITTEE_NAME = 'KC IRB 1'),TO_DATE('20110516','YYYYMMDD'),'EHOB 341',TO_DATE('197001011300','YYYYMMDDHH24MI'),TO_DATE('20110514','YYYYMMDD'),(SELECT SCHEDULE_STATUS_CODE FROM SCHEDULE_STATUS WHERE DESCRIPTION = 'Scheduled'),TO_DATE('19700101','YYYYMMDD'),TO_DATE('19700101','YYYYMMDD'),22,'quickstart',SYSDATE,SYS_GUID(),0)
/
INSERT INTO COMM_SCHEDULE (ID,SCHEDULE_ID,COMMITTEE_ID_FK,SCHEDULED_DATE,PLACE,TIME,PROTOCOL_SUB_DEADLINE,SCHEDULE_STATUS_CODE,START_TIME,END_TIME,MAX_PROTOCOLS,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_COMMITTEE_ID.NEXTVAL,SEQ_COMMITTEE_ID.NEXTVAL,(SELECT ID FROM COMMITTEE WHERE COMMITTEE_NAME = 'KC IRB 1'),TO_DATE('20110620','YYYYMMDD'),'EHOB 341',TO_DATE('197001011300','YYYYMMDDHH24MI'),TO_DATE('20110618','YYYYMMDD'),(SELECT SCHEDULE_STATUS_CODE FROM SCHEDULE_STATUS WHERE DESCRIPTION = 'Scheduled'),TO_DATE('19700101','YYYYMMDD'),TO_DATE('19700101','YYYYMMDD'),22,'quickstart',SYSDATE,SYS_GUID(),0)
/
INSERT INTO COMM_SCHEDULE (ID,SCHEDULE_ID,COMMITTEE_ID_FK,SCHEDULED_DATE,PLACE,TIME,PROTOCOL_SUB_DEADLINE,SCHEDULE_STATUS_CODE,START_TIME,END_TIME,MAX_PROTOCOLS,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_COMMITTEE_ID.NEXTVAL,SEQ_COMMITTEE_ID.NEXTVAL,(SELECT ID FROM COMMITTEE WHERE COMMITTEE_NAME = 'KC IRB 1'),TO_DATE('20110718','YYYYMMDD'),'EHOB 341',TO_DATE('197001011300','YYYYMMDDHH24MI'),TO_DATE('20110716','YYYYMMDD'),(SELECT SCHEDULE_STATUS_CODE FROM SCHEDULE_STATUS WHERE DESCRIPTION = 'Cancelled'),TO_DATE('19700101','YYYYMMDD'),TO_DATE('19700101','YYYYMMDD'),22,'quickstart',SYSDATE,SYS_GUID(),0)
/
INSERT INTO COMM_SCHEDULE (ID,SCHEDULE_ID,COMMITTEE_ID_FK,SCHEDULED_DATE,PLACE,TIME,PROTOCOL_SUB_DEADLINE,SCHEDULE_STATUS_CODE,START_TIME,END_TIME,MAX_PROTOCOLS,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_COMMITTEE_ID.NEXTVAL,SEQ_COMMITTEE_ID.NEXTVAL,(SELECT ID FROM COMMITTEE WHERE COMMITTEE_NAME = 'KC IRB 1'),TO_DATE('20110815','YYYYMMDD'),'EHOB 341',TO_DATE('197001011300','YYYYMMDDHH24MI'),TO_DATE('20110813','YYYYMMDD'),(SELECT SCHEDULE_STATUS_CODE FROM SCHEDULE_STATUS WHERE DESCRIPTION = 'Scheduled'),TO_DATE('19700101','YYYYMMDD'),TO_DATE('19700101','YYYYMMDD'),22,'quickstart',SYSDATE,SYS_GUID(),0)
/
INSERT INTO COMM_SCHEDULE (ID,SCHEDULE_ID,COMMITTEE_ID_FK,SCHEDULED_DATE,PLACE,TIME,PROTOCOL_SUB_DEADLINE,SCHEDULE_STATUS_CODE,START_TIME,END_TIME,MAX_PROTOCOLS,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_COMMITTEE_ID.NEXTVAL,SEQ_COMMITTEE_ID.NEXTVAL,(SELECT ID FROM COMMITTEE WHERE COMMITTEE_NAME = 'KC IRB 1'),TO_DATE('20110919','YYYYMMDD'),'EHOB 341',TO_DATE('197001011300','YYYYMMDDHH24MI'),TO_DATE('20110917','YYYYMMDD'),(SELECT SCHEDULE_STATUS_CODE FROM SCHEDULE_STATUS WHERE DESCRIPTION = 'Scheduled'),TO_DATE('19700101','YYYYMMDD'),TO_DATE('19700101','YYYYMMDD'),22,'quickstart',SYSDATE,SYS_GUID(),0)
/
INSERT INTO COMM_SCHEDULE (ID,SCHEDULE_ID,COMMITTEE_ID_FK,SCHEDULED_DATE,PLACE,TIME,PROTOCOL_SUB_DEADLINE,SCHEDULE_STATUS_CODE,START_TIME,END_TIME,MAX_PROTOCOLS,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_COMMITTEE_ID.NEXTVAL,SEQ_COMMITTEE_ID.NEXTVAL,(SELECT ID FROM COMMITTEE WHERE COMMITTEE_NAME = 'KC IRB 1'),TO_DATE('20111017','YYYYMMDD'),'EHOB 341',TO_DATE('197001011300','YYYYMMDDHH24MI'),TO_DATE('20111015','YYYYMMDD'),(SELECT SCHEDULE_STATUS_CODE FROM SCHEDULE_STATUS WHERE DESCRIPTION = 'Cancelled'),TO_DATE('19700101','YYYYMMDD'),TO_DATE('19700101','YYYYMMDD'),22,'quickstart',SYSDATE,SYS_GUID(),0)
/
INSERT INTO COMM_SCHEDULE (ID,SCHEDULE_ID,COMMITTEE_ID_FK,SCHEDULED_DATE,PLACE,TIME,PROTOCOL_SUB_DEADLINE,SCHEDULE_STATUS_CODE,START_TIME,END_TIME,MAX_PROTOCOLS,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_COMMITTEE_ID.NEXTVAL,SEQ_COMMITTEE_ID.NEXTVAL,(SELECT ID FROM COMMITTEE WHERE COMMITTEE_NAME = 'KC IRB 1'),TO_DATE('20111121','YYYYMMDD'),'EHOB 341',TO_DATE('197001011300','YYYYMMDDHH24MI'),TO_DATE('20111119','YYYYMMDD'),(SELECT SCHEDULE_STATUS_CODE FROM SCHEDULE_STATUS WHERE DESCRIPTION = 'Scheduled'),TO_DATE('19700101','YYYYMMDD'),TO_DATE('19700101','YYYYMMDD'),22,'quickstart',SYSDATE,SYS_GUID(),0)
/
INSERT INTO COMM_SCHEDULE (ID,SCHEDULE_ID,COMMITTEE_ID_FK,SCHEDULED_DATE,PLACE,TIME,PROTOCOL_SUB_DEADLINE,SCHEDULE_STATUS_CODE,START_TIME,END_TIME,MAX_PROTOCOLS,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_COMMITTEE_ID.NEXTVAL,SEQ_COMMITTEE_ID.NEXTVAL,(SELECT ID FROM COMMITTEE WHERE COMMITTEE_NAME = 'KC IRB 1'),TO_DATE('20111219','YYYYMMDD'),'EHOB 341',TO_DATE('197001011300','YYYYMMDDHH24MI'),TO_DATE('20111217','YYYYMMDD'),(SELECT SCHEDULE_STATUS_CODE FROM SCHEDULE_STATUS WHERE DESCRIPTION = 'Scheduled'),TO_DATE('19700101','YYYYMMDD'),TO_DATE('19700101','YYYYMMDD'),22,'quickstart',SYSDATE,SYS_GUID(),0)
/
INSERT INTO COMM_SCHEDULE (ID,SCHEDULE_ID,COMMITTEE_ID_FK,SCHEDULED_DATE,PLACE,TIME,PROTOCOL_SUB_DEADLINE,SCHEDULE_STATUS_CODE,START_TIME,END_TIME,MAX_PROTOCOLS,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_COMMITTEE_ID.NEXTVAL,SEQ_COMMITTEE_ID.NEXTVAL,(SELECT ID FROM COMMITTEE WHERE COMMITTEE_NAME = 'KC IRB 1'),TO_DATE('20120116','YYYYMMDD'),'EHOB 341',TO_DATE('197001011300','YYYYMMDDHH24MI'),TO_DATE('20120114','YYYYMMDD'),(SELECT SCHEDULE_STATUS_CODE FROM SCHEDULE_STATUS WHERE DESCRIPTION = 'Scheduled'),TO_DATE('19700101','YYYYMMDD'),TO_DATE('19700101','YYYYMMDD'),22,'quickstart',SYSDATE,SYS_GUID(),0)
/
INSERT INTO COMM_SCHEDULE (ID,SCHEDULE_ID,COMMITTEE_ID_FK,SCHEDULED_DATE,PLACE,TIME,PROTOCOL_SUB_DEADLINE,SCHEDULE_STATUS_CODE,START_TIME,END_TIME,MAX_PROTOCOLS,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_COMMITTEE_ID.NEXTVAL,SEQ_COMMITTEE_ID.NEXTVAL,(SELECT ID FROM COMMITTEE WHERE COMMITTEE_NAME = 'KC IRB 1'),TO_DATE('20120220','YYYYMMDD'),'EHOB 341',TO_DATE('197001011300','YYYYMMDDHH24MI'),TO_DATE('20120218','YYYYMMDD'),(SELECT SCHEDULE_STATUS_CODE FROM SCHEDULE_STATUS WHERE DESCRIPTION = 'Scheduled'),TO_DATE('19700101','YYYYMMDD'),TO_DATE('19700101','YYYYMMDD'),22,'quickstart',SYSDATE,SYS_GUID(),0)
/
INSERT INTO COMM_SCHEDULE (ID,SCHEDULE_ID,COMMITTEE_ID_FK,SCHEDULED_DATE,PLACE,TIME,PROTOCOL_SUB_DEADLINE,SCHEDULE_STATUS_CODE,START_TIME,END_TIME,MAX_PROTOCOLS,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_COMMITTEE_ID.NEXTVAL,SEQ_COMMITTEE_ID.NEXTVAL,(SELECT ID FROM COMMITTEE WHERE COMMITTEE_NAME = 'KC IRB 1'),TO_DATE('20120319','YYYYMMDD'),'EHOB 341',TO_DATE('197001011300','YYYYMMDDHH24MI'),TO_DATE('20120317','YYYYMMDD'),(SELECT SCHEDULE_STATUS_CODE FROM SCHEDULE_STATUS WHERE DESCRIPTION = 'Scheduled'),TO_DATE('19700101','YYYYMMDD'),TO_DATE('19700101','YYYYMMDD'),22,'quickstart',SYSDATE,SYS_GUID(),0)
/
INSERT INTO COMM_SCHEDULE (ID,SCHEDULE_ID,COMMITTEE_ID_FK,SCHEDULED_DATE,PLACE,TIME,PROTOCOL_SUB_DEADLINE,SCHEDULE_STATUS_CODE,START_TIME,END_TIME,MAX_PROTOCOLS,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_COMMITTEE_ID.NEXTVAL,SEQ_COMMITTEE_ID.NEXTVAL,(SELECT ID FROM COMMITTEE WHERE COMMITTEE_NAME = 'KC IRB 1'),TO_DATE('20120416','YYYYMMDD'),'EHOB 341',TO_DATE('197001011300','YYYYMMDDHH24MI'),TO_DATE('20120414','YYYYMMDD'),(SELECT SCHEDULE_STATUS_CODE FROM SCHEDULE_STATUS WHERE DESCRIPTION = 'Scheduled'),TO_DATE('19700101','YYYYMMDD'),TO_DATE('19700101','YYYYMMDD'),22,'quickstart',SYSDATE,SYS_GUID(),0)
/
INSERT INTO COMM_SCHEDULE (ID,SCHEDULE_ID,COMMITTEE_ID_FK,SCHEDULED_DATE,PLACE,TIME,PROTOCOL_SUB_DEADLINE,SCHEDULE_STATUS_CODE,START_TIME,END_TIME,MAX_PROTOCOLS,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_COMMITTEE_ID.NEXTVAL,SEQ_COMMITTEE_ID.NEXTVAL,(SELECT ID FROM COMMITTEE WHERE COMMITTEE_NAME = 'KC IRB 1'),TO_DATE('20120521','YYYYMMDD'),'EHOB 341',TO_DATE('197001011300','YYYYMMDDHH24MI'),TO_DATE('20120519','YYYYMMDD'),(SELECT SCHEDULE_STATUS_CODE FROM SCHEDULE_STATUS WHERE DESCRIPTION = 'Scheduled'),TO_DATE('19700101','YYYYMMDD'),TO_DATE('19700101','YYYYMMDD'),22,'quickstart',SYSDATE,SYS_GUID(),0)
/
INSERT INTO COMM_SCHEDULE (ID,SCHEDULE_ID,COMMITTEE_ID_FK,SCHEDULED_DATE,PLACE,TIME,PROTOCOL_SUB_DEADLINE,SCHEDULE_STATUS_CODE,START_TIME,END_TIME,MAX_PROTOCOLS,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_COMMITTEE_ID.NEXTVAL,SEQ_COMMITTEE_ID.NEXTVAL,(SELECT ID FROM COMMITTEE WHERE COMMITTEE_NAME = 'KC IRB 1'),TO_DATE('20120618','YYYYMMDD'),'EHOB 341',TO_DATE('197001011300','YYYYMMDDHH24MI'),TO_DATE('20120616','YYYYMMDD'),(SELECT SCHEDULE_STATUS_CODE FROM SCHEDULE_STATUS WHERE DESCRIPTION = 'Scheduled'),TO_DATE('19700101','YYYYMMDD'),TO_DATE('19700101','YYYYMMDD'),22,'quickstart',SYSDATE,SYS_GUID(),0)
/
INSERT INTO COMM_SCHEDULE (ID,SCHEDULE_ID,COMMITTEE_ID_FK,SCHEDULED_DATE,PLACE,TIME,PROTOCOL_SUB_DEADLINE,SCHEDULE_STATUS_CODE,START_TIME,END_TIME,MAX_PROTOCOLS,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_COMMITTEE_ID.NEXTVAL,SEQ_COMMITTEE_ID.NEXTVAL,(SELECT ID FROM COMMITTEE WHERE COMMITTEE_NAME = 'KC IRB 1'),TO_DATE('20120716','YYYYMMDD'),'EHOB 341',TO_DATE('197001011300','YYYYMMDDHH24MI'),TO_DATE('20120714','YYYYMMDD'),(SELECT SCHEDULE_STATUS_CODE FROM SCHEDULE_STATUS WHERE DESCRIPTION = 'Scheduled'),TO_DATE('19700101','YYYYMMDD'),TO_DATE('19700101','YYYYMMDD'),22,'quickstart',SYSDATE,SYS_GUID(),0)
/
INSERT INTO COMM_SCHEDULE (ID,SCHEDULE_ID,COMMITTEE_ID_FK,SCHEDULED_DATE,PLACE,TIME,PROTOCOL_SUB_DEADLINE,SCHEDULE_STATUS_CODE,START_TIME,END_TIME,MAX_PROTOCOLS,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_COMMITTEE_ID.NEXTVAL,SEQ_COMMITTEE_ID.NEXTVAL,(SELECT ID FROM COMMITTEE WHERE COMMITTEE_NAME = 'KC IRB 1'),TO_DATE('20120820','YYYYMMDD'),'EHOB 341',TO_DATE('197001011300','YYYYMMDDHH24MI'),TO_DATE('20120818','YYYYMMDD'),(SELECT SCHEDULE_STATUS_CODE FROM SCHEDULE_STATUS WHERE DESCRIPTION = 'Scheduled'),TO_DATE('19700101','YYYYMMDD'),TO_DATE('19700101','YYYYMMDD'),22,'quickstart',SYSDATE,SYS_GUID(),0)
/
INSERT INTO COMM_SCHEDULE (ID,SCHEDULE_ID,COMMITTEE_ID_FK,SCHEDULED_DATE,PLACE,TIME,PROTOCOL_SUB_DEADLINE,SCHEDULE_STATUS_CODE,START_TIME,END_TIME,MAX_PROTOCOLS,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_COMMITTEE_ID.NEXTVAL,SEQ_COMMITTEE_ID.NEXTVAL,(SELECT ID FROM COMMITTEE WHERE COMMITTEE_NAME = 'KC IRB 1'),TO_DATE('20120917','YYYYMMDD'),'EHOB 341',TO_DATE('197001011300','YYYYMMDDHH24MI'),TO_DATE('20120915','YYYYMMDD'),(SELECT SCHEDULE_STATUS_CODE FROM SCHEDULE_STATUS WHERE DESCRIPTION = 'Scheduled'),TO_DATE('19700101','YYYYMMDD'),TO_DATE('19700101','YYYYMMDD'),22,'quickstart',SYSDATE,SYS_GUID(),0)
/
INSERT INTO COMM_SCHEDULE (ID,SCHEDULE_ID,COMMITTEE_ID_FK,SCHEDULED_DATE,PLACE,TIME,PROTOCOL_SUB_DEADLINE,SCHEDULE_STATUS_CODE,START_TIME,END_TIME,MAX_PROTOCOLS,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_COMMITTEE_ID.NEXTVAL,SEQ_COMMITTEE_ID.NEXTVAL,(SELECT ID FROM COMMITTEE WHERE COMMITTEE_NAME = 'KC IRB 1'),TO_DATE('20121015','YYYYMMDD'),'EHOB 341',TO_DATE('197001011300','YYYYMMDDHH24MI'),TO_DATE('20121013','YYYYMMDD'),(SELECT SCHEDULE_STATUS_CODE FROM SCHEDULE_STATUS WHERE DESCRIPTION = 'Scheduled'),TO_DATE('19700101','YYYYMMDD'),TO_DATE('19700101','YYYYMMDD'),22,'quickstart',SYSDATE,SYS_GUID(),0)
/
INSERT INTO COMM_SCHEDULE (ID,SCHEDULE_ID,COMMITTEE_ID_FK,SCHEDULED_DATE,PLACE,TIME,PROTOCOL_SUB_DEADLINE,SCHEDULE_STATUS_CODE,START_TIME,END_TIME,MAX_PROTOCOLS,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_COMMITTEE_ID.NEXTVAL,SEQ_COMMITTEE_ID.NEXTVAL,(SELECT ID FROM COMMITTEE WHERE COMMITTEE_NAME = 'KC IRB 1'),TO_DATE('20121119','YYYYMMDD'),'EHOB 341',TO_DATE('197001011300','YYYYMMDDHH24MI'),TO_DATE('20121117','YYYYMMDD'),(SELECT SCHEDULE_STATUS_CODE FROM SCHEDULE_STATUS WHERE DESCRIPTION = 'Scheduled'),TO_DATE('19700101','YYYYMMDD'),TO_DATE('19700101','YYYYMMDD'),22,'quickstart',SYSDATE,SYS_GUID(),0)
/
INSERT INTO COMM_SCHEDULE (ID,SCHEDULE_ID,COMMITTEE_ID_FK,SCHEDULED_DATE,PLACE,TIME,PROTOCOL_SUB_DEADLINE,SCHEDULE_STATUS_CODE,START_TIME,END_TIME,MAX_PROTOCOLS,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_COMMITTEE_ID.NEXTVAL,SEQ_COMMITTEE_ID.NEXTVAL,(SELECT ID FROM COMMITTEE WHERE COMMITTEE_NAME = 'KC IRB 1'),TO_DATE('20121217','YYYYMMDD'),'EHOB 341',TO_DATE('197001011300','YYYYMMDDHH24MI'),TO_DATE('20121215','YYYYMMDD'),(SELECT SCHEDULE_STATUS_CODE FROM SCHEDULE_STATUS WHERE DESCRIPTION = 'Scheduled'),TO_DATE('19700101','YYYYMMDD'),TO_DATE('19700101','YYYYMMDD'),22,'quickstart',SYSDATE,SYS_GUID(),0)
/
INSERT INTO COMM_SCHEDULE (ID,SCHEDULE_ID,COMMITTEE_ID_FK,SCHEDULED_DATE,PLACE,TIME,PROTOCOL_SUB_DEADLINE,SCHEDULE_STATUS_CODE,START_TIME,END_TIME,MAX_PROTOCOLS,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_COMMITTEE_ID.NEXTVAL,SEQ_COMMITTEE_ID.NEXTVAL,(SELECT ID FROM COMMITTEE WHERE COMMITTEE_NAME = 'KC IRB 1'),TO_DATE('20130121','YYYYMMDD'),'EHOB 341',TO_DATE('197001011300','YYYYMMDDHH24MI'),TO_DATE('20130119','YYYYMMDD'),(SELECT SCHEDULE_STATUS_CODE FROM SCHEDULE_STATUS WHERE DESCRIPTION = 'Scheduled'),TO_DATE('19700101','YYYYMMDD'),TO_DATE('19700101','YYYYMMDD'),22,'quickstart',SYSDATE,SYS_GUID(),0)
/
INSERT INTO COMM_SCHEDULE (ID,SCHEDULE_ID,COMMITTEE_ID_FK,SCHEDULED_DATE,PLACE,TIME,PROTOCOL_SUB_DEADLINE,SCHEDULE_STATUS_CODE,START_TIME,END_TIME,MAX_PROTOCOLS,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_COMMITTEE_ID.NEXTVAL,SEQ_COMMITTEE_ID.NEXTVAL,(SELECT ID FROM COMMITTEE WHERE COMMITTEE_NAME = 'KC IRB 1'),TO_DATE('20130218','YYYYMMDD'),'EHOB 341',TO_DATE('197001011300','YYYYMMDDHH24MI'),TO_DATE('20130216','YYYYMMDD'),(SELECT SCHEDULE_STATUS_CODE FROM SCHEDULE_STATUS WHERE DESCRIPTION = 'Scheduled'),TO_DATE('19700101','YYYYMMDD'),TO_DATE('19700101','YYYYMMDD'),22,'quickstart',SYSDATE,SYS_GUID(),0)
/
INSERT INTO COMM_SCHEDULE (ID,SCHEDULE_ID,COMMITTEE_ID_FK,SCHEDULED_DATE,PLACE,TIME,PROTOCOL_SUB_DEADLINE,SCHEDULE_STATUS_CODE,START_TIME,END_TIME,MAX_PROTOCOLS,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_COMMITTEE_ID.NEXTVAL,SEQ_COMMITTEE_ID.NEXTVAL,(SELECT ID FROM COMMITTEE WHERE COMMITTEE_NAME = 'KC IRB 1'),TO_DATE('20130318','YYYYMMDD'),'EHOB 341',TO_DATE('197001011300','YYYYMMDDHH24MI'),TO_DATE('20130316','YYYYMMDD'),(SELECT SCHEDULE_STATUS_CODE FROM SCHEDULE_STATUS WHERE DESCRIPTION = 'Scheduled'),TO_DATE('19700101','YYYYMMDD'),TO_DATE('19700101','YYYYMMDD'),22,'quickstart',SYSDATE,SYS_GUID(),0)
/
INSERT INTO COMM_SCHEDULE (ID,SCHEDULE_ID,COMMITTEE_ID_FK,SCHEDULED_DATE,PLACE,TIME,PROTOCOL_SUB_DEADLINE,SCHEDULE_STATUS_CODE,START_TIME,END_TIME,MAX_PROTOCOLS,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_COMMITTEE_ID.NEXTVAL,SEQ_COMMITTEE_ID.NEXTVAL,(SELECT ID FROM COMMITTEE WHERE COMMITTEE_NAME = 'KC IRB 1'),TO_DATE('20130415','YYYYMMDD'),'EHOB 341',TO_DATE('197001011300','YYYYMMDDHH24MI'),TO_DATE('20130413','YYYYMMDD'),(SELECT SCHEDULE_STATUS_CODE FROM SCHEDULE_STATUS WHERE DESCRIPTION = 'Scheduled'),TO_DATE('19700101','YYYYMMDD'),TO_DATE('19700101','YYYYMMDD'),22,'quickstart',SYSDATE,SYS_GUID(),0)
/
INSERT INTO COMM_SCHEDULE (ID,SCHEDULE_ID,COMMITTEE_ID_FK,SCHEDULED_DATE,PLACE,TIME,PROTOCOL_SUB_DEADLINE,SCHEDULE_STATUS_CODE,START_TIME,END_TIME,MAX_PROTOCOLS,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_COMMITTEE_ID.NEXTVAL,SEQ_COMMITTEE_ID.NEXTVAL,(SELECT ID FROM COMMITTEE WHERE COMMITTEE_NAME = 'KC IRB 1'),TO_DATE('20130520','YYYYMMDD'),'EHOB 341',TO_DATE('197001011300','YYYYMMDDHH24MI'),TO_DATE('20130518','YYYYMMDD'),(SELECT SCHEDULE_STATUS_CODE FROM SCHEDULE_STATUS WHERE DESCRIPTION = 'Scheduled'),TO_DATE('19700101','YYYYMMDD'),TO_DATE('19700101','YYYYMMDD'),22,'quickstart',SYSDATE,SYS_GUID(),0)
/
INSERT INTO COMM_SCHEDULE (ID,SCHEDULE_ID,COMMITTEE_ID_FK,SCHEDULED_DATE,PLACE,TIME,PROTOCOL_SUB_DEADLINE,SCHEDULE_STATUS_CODE,START_TIME,END_TIME,MAX_PROTOCOLS,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_COMMITTEE_ID.NEXTVAL,SEQ_COMMITTEE_ID.NEXTVAL,(SELECT ID FROM COMMITTEE WHERE COMMITTEE_NAME = 'KC IRB 1'),TO_DATE('20130617','YYYYMMDD'),'EHOB 341',TO_DATE('197001011300','YYYYMMDDHH24MI'),TO_DATE('20130615','YYYYMMDD'),(SELECT SCHEDULE_STATUS_CODE FROM SCHEDULE_STATUS WHERE DESCRIPTION = 'Scheduled'),TO_DATE('19700101','YYYYMMDD'),TO_DATE('19700101','YYYYMMDD'),22,'quickstart',SYSDATE,SYS_GUID(),0)
/
INSERT INTO COMM_SCHEDULE (ID,SCHEDULE_ID,COMMITTEE_ID_FK,SCHEDULED_DATE,PLACE,TIME,PROTOCOL_SUB_DEADLINE,SCHEDULE_STATUS_CODE,START_TIME,END_TIME,MAX_PROTOCOLS,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_COMMITTEE_ID.NEXTVAL,SEQ_COMMITTEE_ID.NEXTVAL,(SELECT ID FROM COMMITTEE WHERE COMMITTEE_NAME = 'KC IRB 1'),TO_DATE('20130715','YYYYMMDD'),'EHOB 341',TO_DATE('197001011300','YYYYMMDDHH24MI'),TO_DATE('20130713','YYYYMMDD'),(SELECT SCHEDULE_STATUS_CODE FROM SCHEDULE_STATUS WHERE DESCRIPTION = 'Scheduled'),TO_DATE('19700101','YYYYMMDD'),TO_DATE('19700101','YYYYMMDD'),22,'quickstart',SYSDATE,SYS_GUID(),0)
/
INSERT INTO COMM_SCHEDULE (ID,SCHEDULE_ID,COMMITTEE_ID_FK,SCHEDULED_DATE,PLACE,TIME,PROTOCOL_SUB_DEADLINE,SCHEDULE_STATUS_CODE,START_TIME,END_TIME,MAX_PROTOCOLS,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_COMMITTEE_ID.NEXTVAL,SEQ_COMMITTEE_ID.NEXTVAL,(SELECT ID FROM COMMITTEE WHERE COMMITTEE_NAME = 'KC IRB 1'),TO_DATE('20130819','YYYYMMDD'),'EHOB 341',TO_DATE('197001011300','YYYYMMDDHH24MI'),TO_DATE('20130817','YYYYMMDD'),(SELECT SCHEDULE_STATUS_CODE FROM SCHEDULE_STATUS WHERE DESCRIPTION = 'Scheduled'),TO_DATE('19700101','YYYYMMDD'),TO_DATE('19700101','YYYYMMDD'),22,'quickstart',SYSDATE,SYS_GUID(),0)
/
INSERT INTO COMM_SCHEDULE (ID,SCHEDULE_ID,COMMITTEE_ID_FK,SCHEDULED_DATE,PLACE,TIME,PROTOCOL_SUB_DEADLINE,SCHEDULE_STATUS_CODE,START_TIME,END_TIME,MAX_PROTOCOLS,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_COMMITTEE_ID.NEXTVAL,SEQ_COMMITTEE_ID.NEXTVAL,(SELECT ID FROM COMMITTEE WHERE COMMITTEE_NAME = 'KC IRB 1'),TO_DATE('20130916','YYYYMMDD'),'EHOB 341',TO_DATE('197001011300','YYYYMMDDHH24MI'),TO_DATE('20130914','YYYYMMDD'),(SELECT SCHEDULE_STATUS_CODE FROM SCHEDULE_STATUS WHERE DESCRIPTION = 'Scheduled'),TO_DATE('19700101','YYYYMMDD'),TO_DATE('19700101','YYYYMMDD'),22,'quickstart',SYSDATE,SYS_GUID(),0)
/
INSERT INTO COMM_SCHEDULE (ID,SCHEDULE_ID,COMMITTEE_ID_FK,SCHEDULED_DATE,PLACE,TIME,PROTOCOL_SUB_DEADLINE,SCHEDULE_STATUS_CODE,START_TIME,END_TIME,MAX_PROTOCOLS,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_COMMITTEE_ID.NEXTVAL,SEQ_COMMITTEE_ID.NEXTVAL,(SELECT ID FROM COMMITTEE WHERE COMMITTEE_NAME = 'KC IRB 1'),TO_DATE('20131021','YYYYMMDD'),'EHOB 341',TO_DATE('197001011300','YYYYMMDDHH24MI'),TO_DATE('20131019','YYYYMMDD'),(SELECT SCHEDULE_STATUS_CODE FROM SCHEDULE_STATUS WHERE DESCRIPTION = 'Scheduled'),TO_DATE('19700101','YYYYMMDD'),TO_DATE('19700101','YYYYMMDD'),22,'quickstart',SYSDATE,SYS_GUID(),0)
/
INSERT INTO COMM_SCHEDULE (ID,SCHEDULE_ID,COMMITTEE_ID_FK,SCHEDULED_DATE,PLACE,TIME,PROTOCOL_SUB_DEADLINE,SCHEDULE_STATUS_CODE,START_TIME,END_TIME,MAX_PROTOCOLS,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_COMMITTEE_ID.NEXTVAL,SEQ_COMMITTEE_ID.NEXTVAL,(SELECT ID FROM COMMITTEE WHERE COMMITTEE_NAME = 'KC IRB 1'),TO_DATE('20131118','YYYYMMDD'),'EHOB 341',TO_DATE('197001011300','YYYYMMDDHH24MI'),TO_DATE('20131116','YYYYMMDD'),(SELECT SCHEDULE_STATUS_CODE FROM SCHEDULE_STATUS WHERE DESCRIPTION = 'Scheduled'),TO_DATE('19700101','YYYYMMDD'),TO_DATE('19700101','YYYYMMDD'),22,'quickstart',SYSDATE,SYS_GUID(),0)
/
INSERT INTO COMM_SCHEDULE (ID,SCHEDULE_ID,COMMITTEE_ID_FK,SCHEDULED_DATE,PLACE,TIME,PROTOCOL_SUB_DEADLINE,SCHEDULE_STATUS_CODE,START_TIME,END_TIME,MAX_PROTOCOLS,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_COMMITTEE_ID.NEXTVAL,SEQ_COMMITTEE_ID.NEXTVAL,(SELECT ID FROM COMMITTEE WHERE COMMITTEE_NAME = 'KC IRB 1'),TO_DATE('20131216','YYYYMMDD'),'EHOB 341',TO_DATE('197001011300','YYYYMMDDHH24MI'),TO_DATE('20131214','YYYYMMDD'),(SELECT SCHEDULE_STATUS_CODE FROM SCHEDULE_STATUS WHERE DESCRIPTION = 'Scheduled'),TO_DATE('19700101','YYYYMMDD'),TO_DATE('19700101','YYYYMMDD'),22,'quickstart',SYSDATE,SYS_GUID(),0)
/
INSERT INTO COMM_SCHEDULE (ID,SCHEDULE_ID,COMMITTEE_ID_FK,SCHEDULED_DATE,PLACE,TIME,PROTOCOL_SUB_DEADLINE,SCHEDULE_STATUS_CODE,START_TIME,END_TIME,MAX_PROTOCOLS,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_COMMITTEE_ID.NEXTVAL,SEQ_COMMITTEE_ID.NEXTVAL,(SELECT ID FROM COMMITTEE WHERE COMMITTEE_NAME = 'KC IRB 1'),TO_DATE('20140120','YYYYMMDD'),'EHOB 341',TO_DATE('197001011300','YYYYMMDDHH24MI'),TO_DATE('20140118','YYYYMMDD'),(SELECT SCHEDULE_STATUS_CODE FROM SCHEDULE_STATUS WHERE DESCRIPTION = 'Scheduled'),TO_DATE('19700101','YYYYMMDD'),TO_DATE('19700101','YYYYMMDD'),22,'quickstart',SYSDATE,SYS_GUID(),0)
/
INSERT INTO COMM_SCHEDULE (ID,SCHEDULE_ID,COMMITTEE_ID_FK,SCHEDULED_DATE,PLACE,TIME,PROTOCOL_SUB_DEADLINE,SCHEDULE_STATUS_CODE,START_TIME,END_TIME,MAX_PROTOCOLS,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_COMMITTEE_ID.NEXTVAL,SEQ_COMMITTEE_ID.NEXTVAL,(SELECT ID FROM COMMITTEE WHERE COMMITTEE_NAME = 'KC IRB 1'),TO_DATE('20140217','YYYYMMDD'),'EHOB 341',TO_DATE('197001011300','YYYYMMDDHH24MI'),TO_DATE('20140215','YYYYMMDD'),(SELECT SCHEDULE_STATUS_CODE FROM SCHEDULE_STATUS WHERE DESCRIPTION = 'Scheduled'),TO_DATE('19700101','YYYYMMDD'),TO_DATE('19700101','YYYYMMDD'),22,'quickstart',SYSDATE,SYS_GUID(),0)
/
INSERT INTO COMM_SCHEDULE (ID,SCHEDULE_ID,COMMITTEE_ID_FK,SCHEDULED_DATE,PLACE,TIME,PROTOCOL_SUB_DEADLINE,SCHEDULE_STATUS_CODE,START_TIME,END_TIME,MAX_PROTOCOLS,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_COMMITTEE_ID.NEXTVAL,SEQ_COMMITTEE_ID.NEXTVAL,(SELECT ID FROM COMMITTEE WHERE COMMITTEE_NAME = 'KC IRB 1'),TO_DATE('20140317','YYYYMMDD'),'EHOB 341',TO_DATE('197001011300','YYYYMMDDHH24MI'),TO_DATE('20140315','YYYYMMDD'),(SELECT SCHEDULE_STATUS_CODE FROM SCHEDULE_STATUS WHERE DESCRIPTION = 'Scheduled'),TO_DATE('19700101','YYYYMMDD'),TO_DATE('19700101','YYYYMMDD'),22,'quickstart',SYSDATE,SYS_GUID(),0)
/
INSERT INTO COMM_SCHEDULE (ID,SCHEDULE_ID,COMMITTEE_ID_FK,SCHEDULED_DATE,PLACE,TIME,PROTOCOL_SUB_DEADLINE,SCHEDULE_STATUS_CODE,START_TIME,END_TIME,MAX_PROTOCOLS,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_COMMITTEE_ID.NEXTVAL,SEQ_COMMITTEE_ID.NEXTVAL,(SELECT ID FROM COMMITTEE WHERE COMMITTEE_NAME = 'KC IRB 1'),TO_DATE('20140421','YYYYMMDD'),'EHOB 341',TO_DATE('197001011300','YYYYMMDDHH24MI'),TO_DATE('20140419','YYYYMMDD'),(SELECT SCHEDULE_STATUS_CODE FROM SCHEDULE_STATUS WHERE DESCRIPTION = 'Scheduled'),TO_DATE('19700101','YYYYMMDD'),TO_DATE('19700101','YYYYMMDD'),22,'quickstart',SYSDATE,SYS_GUID(),0)
/
INSERT INTO COMM_SCHEDULE (ID,SCHEDULE_ID,COMMITTEE_ID_FK,SCHEDULED_DATE,PLACE,TIME,PROTOCOL_SUB_DEADLINE,SCHEDULE_STATUS_CODE,START_TIME,END_TIME,MAX_PROTOCOLS,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_COMMITTEE_ID.NEXTVAL,SEQ_COMMITTEE_ID.NEXTVAL,(SELECT ID FROM COMMITTEE WHERE COMMITTEE_NAME = 'KC IRB 1'),TO_DATE('20140519','YYYYMMDD'),'EHOB 341',TO_DATE('197001011300','YYYYMMDDHH24MI'),TO_DATE('20140517','YYYYMMDD'),(SELECT SCHEDULE_STATUS_CODE FROM SCHEDULE_STATUS WHERE DESCRIPTION = 'Scheduled'),TO_DATE('19700101','YYYYMMDD'),TO_DATE('19700101','YYYYMMDD'),22,'quickstart',SYSDATE,SYS_GUID(),0)
/
INSERT INTO COMM_SCHEDULE (ID,SCHEDULE_ID,COMMITTEE_ID_FK,SCHEDULED_DATE,PLACE,TIME,PROTOCOL_SUB_DEADLINE,SCHEDULE_STATUS_CODE,START_TIME,END_TIME,MAX_PROTOCOLS,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_COMMITTEE_ID.NEXTVAL,SEQ_COMMITTEE_ID.NEXTVAL,(SELECT ID FROM COMMITTEE WHERE COMMITTEE_NAME = 'KC IRB 1'),TO_DATE('20140616','YYYYMMDD'),'EHOB 341',TO_DATE('197001011300','YYYYMMDDHH24MI'),TO_DATE('20140614','YYYYMMDD'),(SELECT SCHEDULE_STATUS_CODE FROM SCHEDULE_STATUS WHERE DESCRIPTION = 'Scheduled'),TO_DATE('19700101','YYYYMMDD'),TO_DATE('19700101','YYYYMMDD'),22,'quickstart',SYSDATE,SYS_GUID(),0)
/
INSERT INTO COMM_SCHEDULE (ID,SCHEDULE_ID,COMMITTEE_ID_FK,SCHEDULED_DATE,PLACE,TIME,PROTOCOL_SUB_DEADLINE,SCHEDULE_STATUS_CODE,START_TIME,END_TIME,MAX_PROTOCOLS,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_COMMITTEE_ID.NEXTVAL,SEQ_COMMITTEE_ID.NEXTVAL,(SELECT ID FROM COMMITTEE WHERE COMMITTEE_NAME = 'KC IRB 1'),TO_DATE('20140721','YYYYMMDD'),'EHOB 341',TO_DATE('197001011300','YYYYMMDDHH24MI'),TO_DATE('20140719','YYYYMMDD'),(SELECT SCHEDULE_STATUS_CODE FROM SCHEDULE_STATUS WHERE DESCRIPTION = 'Scheduled'),TO_DATE('19700101','YYYYMMDD'),TO_DATE('19700101','YYYYMMDD'),22,'quickstart',SYSDATE,SYS_GUID(),0)
/
INSERT INTO COMM_SCHEDULE (ID,SCHEDULE_ID,COMMITTEE_ID_FK,SCHEDULED_DATE,PLACE,TIME,PROTOCOL_SUB_DEADLINE,SCHEDULE_STATUS_CODE,START_TIME,END_TIME,MAX_PROTOCOLS,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_COMMITTEE_ID.NEXTVAL,SEQ_COMMITTEE_ID.NEXTVAL,(SELECT ID FROM COMMITTEE WHERE COMMITTEE_NAME = 'KC IRB 1'),TO_DATE('20140818','YYYYMMDD'),'EHOB 341',TO_DATE('197001011300','YYYYMMDDHH24MI'),TO_DATE('20140816','YYYYMMDD'),(SELECT SCHEDULE_STATUS_CODE FROM SCHEDULE_STATUS WHERE DESCRIPTION = 'Scheduled'),TO_DATE('19700101','YYYYMMDD'),TO_DATE('19700101','YYYYMMDD'),22,'quickstart',SYSDATE,SYS_GUID(),0)
/
INSERT INTO COMM_SCHEDULE (ID,SCHEDULE_ID,COMMITTEE_ID_FK,SCHEDULED_DATE,PLACE,TIME,PROTOCOL_SUB_DEADLINE,SCHEDULE_STATUS_CODE,START_TIME,END_TIME,MAX_PROTOCOLS,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_COMMITTEE_ID.NEXTVAL,SEQ_COMMITTEE_ID.NEXTVAL,(SELECT ID FROM COMMITTEE WHERE COMMITTEE_NAME = 'KC IRB 1'),TO_DATE('20140915','YYYYMMDD'),'EHOB 341',TO_DATE('197001011300','YYYYMMDDHH24MI'),TO_DATE('20140913','YYYYMMDD'),(SELECT SCHEDULE_STATUS_CODE FROM SCHEDULE_STATUS WHERE DESCRIPTION = 'Scheduled'),TO_DATE('19700101','YYYYMMDD'),TO_DATE('19700101','YYYYMMDD'),22,'quickstart',SYSDATE,SYS_GUID(),0)
/
INSERT INTO COMM_SCHEDULE (ID,SCHEDULE_ID,COMMITTEE_ID_FK,SCHEDULED_DATE,PLACE,TIME,PROTOCOL_SUB_DEADLINE,SCHEDULE_STATUS_CODE,START_TIME,END_TIME,MAX_PROTOCOLS,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_COMMITTEE_ID.NEXTVAL,SEQ_COMMITTEE_ID.NEXTVAL,(SELECT ID FROM COMMITTEE WHERE COMMITTEE_NAME = 'KC IRB 1'),TO_DATE('20141020','YYYYMMDD'),'EHOB 341',TO_DATE('197001011300','YYYYMMDDHH24MI'),TO_DATE('20141018','YYYYMMDD'),(SELECT SCHEDULE_STATUS_CODE FROM SCHEDULE_STATUS WHERE DESCRIPTION = 'Scheduled'),TO_DATE('19700101','YYYYMMDD'),TO_DATE('19700101','YYYYMMDD'),22,'quickstart',SYSDATE,SYS_GUID(),0)
/
INSERT INTO COMM_SCHEDULE (ID,SCHEDULE_ID,COMMITTEE_ID_FK,SCHEDULED_DATE,PLACE,TIME,PROTOCOL_SUB_DEADLINE,SCHEDULE_STATUS_CODE,START_TIME,END_TIME,MAX_PROTOCOLS,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_COMMITTEE_ID.NEXTVAL,SEQ_COMMITTEE_ID.NEXTVAL,(SELECT ID FROM COMMITTEE WHERE COMMITTEE_NAME = 'KC IRB 1'),TO_DATE('20141117','YYYYMMDD'),'EHOB 341',TO_DATE('197001011300','YYYYMMDDHH24MI'),TO_DATE('20141115','YYYYMMDD'),(SELECT SCHEDULE_STATUS_CODE FROM SCHEDULE_STATUS WHERE DESCRIPTION = 'Scheduled'),TO_DATE('19700101','YYYYMMDD'),TO_DATE('19700101','YYYYMMDD'),22,'quickstart',SYSDATE,SYS_GUID(),0)
/
INSERT INTO COMM_SCHEDULE (ID,SCHEDULE_ID,COMMITTEE_ID_FK,SCHEDULED_DATE,PLACE,TIME,PROTOCOL_SUB_DEADLINE,SCHEDULE_STATUS_CODE,START_TIME,END_TIME,MAX_PROTOCOLS,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_COMMITTEE_ID.NEXTVAL,SEQ_COMMITTEE_ID.NEXTVAL,(SELECT ID FROM COMMITTEE WHERE COMMITTEE_NAME = 'KC IRB 1'),TO_DATE('20141215','YYYYMMDD'),'EHOB 341',TO_DATE('197001011300','YYYYMMDDHH24MI'),TO_DATE('20141213','YYYYMMDD'),(SELECT SCHEDULE_STATUS_CODE FROM SCHEDULE_STATUS WHERE DESCRIPTION = 'Scheduled'),TO_DATE('19700101','YYYYMMDD'),TO_DATE('19700101','YYYYMMDD'),22,'quickstart',SYSDATE,SYS_GUID(),0)
/
INSERT INTO COMM_SCHEDULE (ID,SCHEDULE_ID,COMMITTEE_ID_FK,SCHEDULED_DATE,PLACE,TIME,PROTOCOL_SUB_DEADLINE,SCHEDULE_STATUS_CODE,START_TIME,END_TIME,MAX_PROTOCOLS,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_COMMITTEE_ID.NEXTVAL,SEQ_COMMITTEE_ID.NEXTVAL,(SELECT ID FROM COMMITTEE WHERE COMMITTEE_NAME = 'KC IRB 1'),TO_DATE('20150119','YYYYMMDD'),'EHOB 341',TO_DATE('197001011300','YYYYMMDDHH24MI'),TO_DATE('20150117','YYYYMMDD'),(SELECT SCHEDULE_STATUS_CODE FROM SCHEDULE_STATUS WHERE DESCRIPTION = 'Scheduled'),TO_DATE('19700101','YYYYMMDD'),TO_DATE('19700101','YYYYMMDD'),22,'quickstart',SYSDATE,SYS_GUID(),0)
/
INSERT INTO COMM_SCHEDULE (ID,SCHEDULE_ID,COMMITTEE_ID_FK,SCHEDULED_DATE,PLACE,TIME,PROTOCOL_SUB_DEADLINE,SCHEDULE_STATUS_CODE,START_TIME,END_TIME,MAX_PROTOCOLS,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_COMMITTEE_ID.NEXTVAL,SEQ_COMMITTEE_ID.NEXTVAL,(SELECT ID FROM COMMITTEE WHERE COMMITTEE_NAME = 'KC IRB 1'),TO_DATE('20150216','YYYYMMDD'),'EHOB 341',TO_DATE('197001011300','YYYYMMDDHH24MI'),TO_DATE('20150214','YYYYMMDD'),(SELECT SCHEDULE_STATUS_CODE FROM SCHEDULE_STATUS WHERE DESCRIPTION = 'Scheduled'),TO_DATE('19700101','YYYYMMDD'),TO_DATE('19700101','YYYYMMDD'),22,'quickstart',SYSDATE,SYS_GUID(),0)
/
INSERT INTO COMM_SCHEDULE (ID,SCHEDULE_ID,COMMITTEE_ID_FK,SCHEDULED_DATE,PLACE,TIME,PROTOCOL_SUB_DEADLINE,SCHEDULE_STATUS_CODE,START_TIME,END_TIME,MAX_PROTOCOLS,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_COMMITTEE_ID.NEXTVAL,SEQ_COMMITTEE_ID.NEXTVAL,(SELECT ID FROM COMMITTEE WHERE COMMITTEE_NAME = 'KC IRB 1'),TO_DATE('20150316','YYYYMMDD'),'EHOB 341',TO_DATE('197001011300','YYYYMMDDHH24MI'),TO_DATE('20150314','YYYYMMDD'),(SELECT SCHEDULE_STATUS_CODE FROM SCHEDULE_STATUS WHERE DESCRIPTION = 'Scheduled'),TO_DATE('19700101','YYYYMMDD'),TO_DATE('19700101','YYYYMMDD'),22,'quickstart',SYSDATE,SYS_GUID(),0)
/
INSERT INTO COMM_SCHEDULE (ID,SCHEDULE_ID,COMMITTEE_ID_FK,SCHEDULED_DATE,PLACE,TIME,PROTOCOL_SUB_DEADLINE,SCHEDULE_STATUS_CODE,START_TIME,END_TIME,MAX_PROTOCOLS,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_COMMITTEE_ID.NEXTVAL,SEQ_COMMITTEE_ID.NEXTVAL,(SELECT ID FROM COMMITTEE WHERE COMMITTEE_NAME = 'KC IRB 1'),TO_DATE('20150420','YYYYMMDD'),'EHOB 341',TO_DATE('197001011300','YYYYMMDDHH24MI'),TO_DATE('20150418','YYYYMMDD'),(SELECT SCHEDULE_STATUS_CODE FROM SCHEDULE_STATUS WHERE DESCRIPTION = 'Scheduled'),TO_DATE('19700101','YYYYMMDD'),TO_DATE('19700101','YYYYMMDD'),22,'quickstart',SYSDATE,SYS_GUID(),0)
/
INSERT INTO COMM_SCHEDULE (ID,SCHEDULE_ID,COMMITTEE_ID_FK,SCHEDULED_DATE,PLACE,TIME,PROTOCOL_SUB_DEADLINE,SCHEDULE_STATUS_CODE,START_TIME,END_TIME,MAX_PROTOCOLS,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_COMMITTEE_ID.NEXTVAL,SEQ_COMMITTEE_ID.NEXTVAL,(SELECT ID FROM COMMITTEE WHERE COMMITTEE_NAME = 'KC IRB 1'),TO_DATE('20150518','YYYYMMDD'),'EHOB 341',TO_DATE('197001011300','YYYYMMDDHH24MI'),TO_DATE('20150516','YYYYMMDD'),(SELECT SCHEDULE_STATUS_CODE FROM SCHEDULE_STATUS WHERE DESCRIPTION = 'Scheduled'),TO_DATE('19700101','YYYYMMDD'),TO_DATE('19700101','YYYYMMDD'),22,'quickstart',SYSDATE,SYS_GUID(),0)
/
INSERT INTO COMM_SCHEDULE (ID,SCHEDULE_ID,COMMITTEE_ID_FK,SCHEDULED_DATE,PLACE,TIME,PROTOCOL_SUB_DEADLINE,SCHEDULE_STATUS_CODE,START_TIME,END_TIME,MAX_PROTOCOLS,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (SEQ_COMMITTEE_ID.NEXTVAL,SEQ_COMMITTEE_ID.NEXTVAL,(SELECT ID FROM COMMITTEE WHERE COMMITTEE_NAME = 'KC IRB 1'),TO_DATE('20150615','YYYYMMDD'),'EHOB 341',TO_DATE('197001011300','YYYYMMDDHH24MI'),TO_DATE('20150613','YYYYMMDD'),(SELECT SCHEDULE_STATUS_CODE FROM SCHEDULE_STATUS WHERE DESCRIPTION = 'Scheduled'),TO_DATE('19700101','YYYYMMDD'),TO_DATE('19700101','YYYYMMDD'),22,'quickstart',SYSDATE,SYS_GUID(),0)
/
