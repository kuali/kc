delimiter /

alter table DOCUMENT_NEXTVALUE add DOCUMENT_NEXT_VALUE_TYPE varchar(200) default 'DOC' not null
/

delimiter ;
