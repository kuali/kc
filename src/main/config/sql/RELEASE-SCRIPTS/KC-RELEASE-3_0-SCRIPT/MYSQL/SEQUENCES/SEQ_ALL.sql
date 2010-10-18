CREATE TABLE SEQUENCE_IPPUCS_ID
(
	id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM
/

CREATE TABLE SEQ_PROPOSAL_NUMBER
(
	id bigint(19) not null auto_increment, primary key (id)
) ENGINE MyISAM
/

CREATE TABLE SEQ_PROTO_NOTIFICATION_TEMPL
(
	id bigint(19) not null auto_increment, primary key (id)
) ENGINE MyISAM
/

CREATE TABLE SEQ_VALID_SUBM_REVW_TYPE_QUAL
(
	id bigint(19) not null auto_increment, primary key (id)
) ENGINE MyISAM
/

drop table sequence_submission_history
/

drop table KC_COUNTRY_CODES_SEQ
/

CREATE TABLE SEQ_VALID_NARR_FORMS_ID
(
	id bigint(19) not null auto_increment, primary key (id)
) ENGINE MyISAM
/


