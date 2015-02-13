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

drop table KRSB_SVC_DEF_T
/
drop table KRSB_FLT_SVC_DEF_T
/
drop table KRSB_SVC_DEF_S
/
drop table KRSB_FLT_SVC_DEF_S
/
CREATE TABLE KRSB_SVC_DSCRPTR_T (
  SVC_DSCRPTR_ID varchar(40) NOT NULL,
  DSCRPTR longtext NOT NULL,
  PRIMARY KEY (SVC_DSCRPTR_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin
/
CREATE TABLE KRSB_SVC_DSCRPTR_S (
  ID bigint(19) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (ID)
) ENGINE=MyISAM AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COLLATE=utf8_bin
/
CREATE TABLE KRSB_SVC_DEF_T (
  SVC_DEF_ID varchar(40) NOT NULL,
  SVC_NM varchar(255) NOT NULL,
  SVC_URL varchar(500) NOT NULL,
  INSTN_ID varchar(255) NOT NULL,
  APPL_NMSPC varchar(255) NOT NULL,
  SRVR_IP varchar(40) NOT NULL,
  TYP_CD varchar(40) NOT NULL,
  SVC_VER varchar(40) NOT NULL,
  STAT_CD varchar(1) NOT NULL,
  SVC_DSCRPTR_ID varchar(40) NOT NULL,
  CHKSM varchar(30) NOT NULL,
  VER_NBR decimal(8,0) DEFAULT '0',
  PRIMARY KEY (SVC_DEF_ID),
  index krsb_svc_def_ti1 (instn_id),
  index krsb_svc_def_ti2 (svc_nm, stat_cd),
  index krsb_svc_def_ti3 (stat_cd),
  foreign key krsb_svc_def_fk1 (svc_dscrptr_id) references krsb_svc_dscrptr_t(svc_dscrptr_id) on delete cascade
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin
/
CREATE TABLE KRSB_SVC_DEF_S (
  ID bigint(19) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (ID)
) ENGINE=MyISAM AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COLLATE=utf8_bin
/
DELIMITER ;
