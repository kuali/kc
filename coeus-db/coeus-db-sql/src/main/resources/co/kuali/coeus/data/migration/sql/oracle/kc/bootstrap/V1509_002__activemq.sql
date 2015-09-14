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

CREATE TABLE KC_ACTIVEMQ_ACKS  (
  CONTAINER    	VARCHAR2(250) NOT NULL,
  SUB_DEST     	VARCHAR2(250) NULL,
  CLIENT_ID    	VARCHAR2(250) NOT NULL,
  SUB_NAME     	VARCHAR2(250) NOT NULL,
  SELECTOR     	VARCHAR2(250) NULL,
  LAST_ACKED_ID	NUMBER NULL,
  PRIORITY     	NUMBER DEFAULT 5 NOT NULL,
  XID          	VARCHAR2(250) NULL,
  PRIMARY KEY(CONTAINER,CLIENT_ID,SUB_NAME,PRIORITY)
);

CREATE INDEX KC_ACTIVEMQ_ACKS_XIDX
ON KC_ACTIVEMQ_ACKS(XID);

CREATE TABLE KC_ACTIVEMQ_LOCK  (
  ID         	NUMBER NOT NULL,
  TIME       	NUMBER NULL,
  BROKER_NAME	VARCHAR2(250) NULL,
  PRIMARY KEY(ID)
);

CREATE TABLE KC_ACTIVEMQ_MSGS  (
  ID        	NUMBER NOT NULL,
  CONTAINER 	VARCHAR2(250) NULL,
  MSGID_PROD	VARCHAR2(250) NULL,
  MSGID_SEQ 	NUMBER NULL,
  EXPIRATION	NUMBER NULL,
  MSG       	BLOB NULL,
  PRIORITY  	NUMBER NULL,
  XID       	VARCHAR2(250) NULL,
  PRIMARY KEY(ID)
);

CREATE INDEX KC_ACTIVEMQ_MSGS_CIDX
ON KC_ACTIVEMQ_MSGS(CONTAINER);

CREATE INDEX KC_ACTIVEMQ_MSGS_EIDX
ON KC_ACTIVEMQ_MSGS(EXPIRATION);

CREATE INDEX KC_ACTIVEMQ_MSGS_MIDX
ON KC_ACTIVEMQ_MSGS(MSGID_PROD, MSGID_SEQ);

CREATE INDEX KC_ACTIVEMQ_MSGS_PIDX
ON KC_ACTIVEMQ_MSGS(PRIORITY);

CREATE INDEX KC_ACTIVEMQ_MSGS_XIDX
ON KC_ACTIVEMQ_MSGS(XID);
