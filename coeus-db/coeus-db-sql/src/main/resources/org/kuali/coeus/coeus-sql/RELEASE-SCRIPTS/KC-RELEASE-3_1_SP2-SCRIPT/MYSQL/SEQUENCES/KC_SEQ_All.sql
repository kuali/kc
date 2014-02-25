CREATE TABLE SEQ_PROP_SCIENCE_KEYWORD_ID
(
    id bigint(19) not null auto_increment, primary key (id) 
) ENGINE MyISAM;

create table SEQ_SPONSOR_CODE (
  id bigint(19) not null auto_increment, primary key (id)
) ENGINE MyISAM;
ALTER TABLE SEQ_SPONSOR_CODE auto_increment = 100000;

ALTER TABLE SEQ_QUESTIONNAIRE_ID auto_increment = 1000;