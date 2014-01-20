alter table KRMS_AGENDA_ATTR_T disable constraint KRMS_AGENDA_ATTR_FK1
/

alter table KRMS_AGENDA_ITM_T disable constraint KRMS_AGENDA_ITM_FK2
/

update KRMS_AGENDA_T set AGENDA_ID = 'KC1000' where AGENDA_ID = 'Q1000'
/

update KRMS_AGENDA_ITM_T set AGENDA_ID = 'KC1000' where AGENDA_ID = 'Q1000'
/

update KRMS_AGENDA_ATTR_T set AGENDA_ID = 'KC1000' where AGENDA_ID = 'Q1000'
/

alter table KRMS_AGENDA_ATTR_T enable constraint KRMS_AGENDA_ATTR_FK1
/

alter table KRMS_AGENDA_ITM_T enable constraint KRMS_AGENDA_ITM_FK2
/