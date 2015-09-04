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

CREATE TABLE kc_activemq_acks  (
  CONTAINER    	varchar(250) NOT NULL,
  SUB_DEST     	varchar(250) NULL,
  CLIENT_ID    	varchar(250) NOT NULL,
  SUB_NAME     	varchar(250) NOT NULL,
  SELECTOR     	varchar(250) NULL,
  LAST_ACKED_ID	bigint(20) NULL,
  PRIORITY     	bigint(20) NOT NULL DEFAULT '5',
  XID          	varchar(250) NULL,
  PRIMARY KEY(CONTAINER,CLIENT_ID,SUB_NAME,PRIORITY)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 0;

CREATE INDEX KC_ACTIVEMQ_ACKS_XIDX USING BTREE
ON kc_activemq_acks(XID);

CREATE TABLE kc_activemq_lock  (
  ID         	bigint(20) NOT NULL,
  TIME       	bigint(20) NULL,
  BROKER_NAME	varchar(250) NULL,
  PRIMARY KEY(ID)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 0;

CREATE TABLE kc_activemq_msgs  (
  ID        	bigint(20) NOT NULL,
  CONTAINER 	varchar(250) NULL,
  MSGID_PROD	varchar(250) NULL,
  MSGID_SEQ 	bigint(20) NULL,
  EXPIRATION	bigint(20) NULL,
  MSG       	longblob NULL,
  PRIORITY  	bigint(20) NULL,
  XID       	varchar(250) NULL,
  PRIMARY KEY(ID)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 0;

CREATE INDEX KC_ACTIVEMQ_MSGS_CIDX USING BTREE
ON kc_activemq_msgs(CONTAINER);

CREATE INDEX KC_ACTIVEMQ_MSGS_EIDX USING BTREE
ON kc_activemq_msgs(EXPIRATION);

CREATE INDEX KC_ACTIVEMQ_MSGS_MIDX USING BTREE
ON kc_activemq_msgs(MSGID_PROD, MSGID_SEQ);

CREATE INDEX KC_ACTIVEMQ_MSGS_PIDX USING BTREE
ON kc_activemq_msgs(PRIORITY);

CREATE INDEX KC_ACTIVEMQ_MSGS_XIDX USING BTREE
ON kc_activemq_msgs(XID);
