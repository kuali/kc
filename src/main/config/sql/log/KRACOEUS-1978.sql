-- Add discriminator column for JPA upgrade

alter table NARRATIVE add (NARRATIVE_TYPE_GROUP varchar2(1) DEFAULT 'P' NOT NULL);

update NARRATIVE N set NARRATIVE_TYPE_GROUP=(select T.NARRATIVE_TYPE_GROUP from NARRATIVE_TYPE T 
                                             where N.NARRATIVE_TYPE_CODE = T.NARRATIVE_TYPE_CODE);
