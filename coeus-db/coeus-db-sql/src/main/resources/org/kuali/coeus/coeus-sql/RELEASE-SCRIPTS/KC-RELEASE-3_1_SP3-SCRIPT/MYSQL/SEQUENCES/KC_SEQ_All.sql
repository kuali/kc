CREATE TABLE SEQ_PERSON_EDITABLE_FIELD (
  id bigint(19) not null auto_increment, primary key (id)
) ENGINE MyISAM;
ALTER TABLE SEQ_PERSON_EDITABLE_FIELD auto_increment = 1001;

CREATE TABLE SEQ_BGT_SUM_PER_CALC_AMT_ID (
  id bigint(19) not null auto_increment, primary key (id)
) ENGINE MyISAM;
ALTER TABLE SEQ_BGT_SUM_PER_CALC_AMT_ID auto_increment = 1;

CREATE TABLE SEQ_AWRD_BDGT_LMT_ID (
  id bigint(19) not null auto_increment, primary key (id)
) ENGINE MyISAM;
ALTER TABLE SEQ_AWRD_BDGT_LMT_ID auto_increment = 1;

CREATE TABLE SEQ_NOTIFICATION_TYPE_ID (
  id bigint(19) not null auto_increment, primary key (id)
) ENGINE MyISAM;
ALTER TABLE SEQ_NOTIFICATION_TYPE_ID auto_increment = 10000;