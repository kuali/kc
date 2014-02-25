CREATE TABLE SEQ_NOTIFICATION_ID (
  id bigint(19) not null auto_increment, primary key (id)
) ENGINE MyISAM;
ALTER TABLE SEQ_NOTIFICATION_ID auto_increment = 1;

CREATE TABLE SEQ_SPONSOR_FORMS (
  id bigint(19) not null auto_increment, primary key (id)
) ENGINE MyISAM;
ALTER TABLE SEQ_SPONSOR_FORMS auto_increment = 1;

CREATE TABLE SEQ_SPONSOR_FORM_TEMPLATES (
  id bigint(19) not null auto_increment, primary key (id)
) ENGINE MyISAM;
ALTER TABLE SEQ_SPONSOR_FORM_TEMPLATES auto_increment = 1;