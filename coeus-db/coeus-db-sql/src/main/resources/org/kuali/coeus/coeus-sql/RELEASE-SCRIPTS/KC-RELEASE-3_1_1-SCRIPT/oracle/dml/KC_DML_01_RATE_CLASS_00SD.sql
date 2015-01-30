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

INSERT INTO RATE_CLASS (RATE_CLASS_CODE,DESCRIPTION,RATE_CLASS_TYPE,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) 
	VALUES('13','MTDC - AWARD','O',sysdate,'admin',1,sys_guid())
/
insert into RATE_TYPE (RATE_CLASS_CODE,RATE_TYPE_CODE,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) values (13,1,'MTDC',sysdate,'admin',1,sys_guid())
/
insert into RATE_TYPE (RATE_CLASS_CODE,RATE_TYPE_CODE,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) values (13,2,'TDC- DO NOT USE',sysdate,'admin',1,sys_guid())
/
insert into RATE_TYPE (RATE_CLASS_CODE,RATE_TYPE_CODE,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) values (13,3,'Other',sysdate,'admin',1,sys_guid())
/
insert into RATE_TYPE (RATE_CLASS_CODE,RATE_TYPE_CODE,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) values (13,4,'None',sysdate,'admin',1,sys_guid())
/
insert into RATE_TYPE (RATE_CLASS_CODE,RATE_TYPE_CODE,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) values (13,5,'RESMN',sysdate,'admin',1,sys_guid())
/
insert into RATE_TYPE (RATE_CLASS_CODE,RATE_TYPE_CODE,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) values (13,6,'RESMF',sysdate,'admin',1,sys_guid())
/
insert into RATE_TYPE (RATE_CLASS_CODE,RATE_TYPE_CODE,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) values (13,7,'EXCLU',sysdate,'admin',1,sys_guid())
/
insert into RATE_TYPE (RATE_CLASS_CODE,RATE_TYPE_CODE,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) values (13,8,'RESEB',sysdate,'admin',1,sys_guid())
/
insert into RATE_TYPE (RATE_CLASS_CODE,RATE_TYPE_CODE,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) values (13,9,'RESMFF',sysdate,'admin',1,sys_guid())
/
insert into RATE_TYPE (RATE_CLASS_CODE,RATE_TYPE_CODE,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) values (13,10,'FUNSN',sysdate,'admin',1,sys_guid())
/
insert into RATE_TYPE (RATE_CLASS_CODE,RATE_TYPE_CODE,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) values (13,11,'FUNNX',sysdate,'admin',1,sys_guid())
/
insert into RATE_TYPE (RATE_CLASS_CODE,RATE_TYPE_CODE,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) values (13,12,'FUNSNX',sysdate,'admin',1,sys_guid())
/
insert into RATE_TYPE (RATE_CLASS_CODE,RATE_TYPE_CODE,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) values (13,13,'FUNSAX',sysdate,'admin',1,sys_guid())
/
insert into RATE_TYPE (RATE_CLASS_CODE,RATE_TYPE_CODE,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) values (13,14,'FUNNXF',sysdate,'admin',1,sys_guid())
/
insert into RATE_TYPE (RATE_CLASS_CODE,RATE_TYPE_CODE,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) values (13,15,'FUNMNX',sysdate,'admin',1,sys_guid())
/
insert into RATE_TYPE (RATE_CLASS_CODE,RATE_TYPE_CODE,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) values (13,16,'FUNMFX',sysdate,'admin',1,sys_guid())
/
insert into RATE_TYPE (RATE_CLASS_CODE,RATE_TYPE_CODE,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) values (13,17,'SLNSN',sysdate,'admin',1,sys_guid())
/
insert into RATE_TYPE (RATE_CLASS_CODE,RATE_TYPE_CODE,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) values (13,18,'SLNMN',sysdate,'admin',1,sys_guid())
/
insert into RATE_TYPE (RATE_CLASS_CODE,RATE_TYPE_CODE,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) values (13,19,'SLNMF',sysdate,'admin',1,sys_guid())
/
insert into RATE_TYPE (RATE_CLASS_CODE,RATE_TYPE_CODE,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) values (13,20,'GENSN',sysdate,'admin',1,sys_guid())
/
insert into RATE_TYPE (RATE_CLASS_CODE,RATE_TYPE_CODE,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) values (13,21,'GENSNF',sysdate,'admin',1,sys_guid())
/
insert into RATE_TYPE (RATE_CLASS_CODE,RATE_TYPE_CODE,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) values (13,22,'RESSN',sysdate,'admin',1,sys_guid())
/
insert into RATE_TYPE (RATE_CLASS_CODE,RATE_TYPE_CODE,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) values (13,23,'FUNSAN',sysdate,'admin',1,sys_guid())
/
insert into RATE_TYPE (RATE_CLASS_CODE,RATE_TYPE_CODE,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) values (13,24,'FUNFN',sysdate,'admin',1,sys_guid())
/
insert into RATE_TYPE (RATE_CLASS_CODE,RATE_TYPE_CODE,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) values (13,25,'RESTDC',sysdate,'admin',1,sys_guid())
/
insert into RATE_TYPE (RATE_CLASS_CODE,RATE_TYPE_CODE,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) values (13,26,'FUNTDC',sysdate,'admin',1,sys_guid())
/
insert into RATE_TYPE (RATE_CLASS_CODE,RATE_TYPE_CODE,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) values (13,27,'RESTG',sysdate,'admin',1,sys_guid())
/
insert into RATE_TYPE (RATE_CLASS_CODE,RATE_TYPE_CODE,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) values (13,28,'RESTDE',sysdate,'admin',1,sys_guid())
/
insert into RATE_TYPE (RATE_CLASS_CODE,RATE_TYPE_CODE,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) values (13,29,'BIMN',sysdate,'admin',1,sys_guid())
/
