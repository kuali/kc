
DELIMITER /

create table AWARD_CGB (
AWARD_ID decimal(22,0) primary key,
AWARD_NUMBER varchar(12) not null,
SEQUENCE_NUMBER decimal(4,0) not null,
ADDITIONAL_FORMS_REQ char(1),
MIN_INVOICE_AMT decimal(19,2),
AUTO_APPROVE_INVOICE char(1),
INVOICING_OPTION varchar(120),
STOP_WORK char(1),
DUNNING_CAMPAIGN_ID varchar(4),
LAST_BILLED_DATE date,
PREV_LAST_BILLED_DATE date,
FINAL_BILL char(1) not null default 'N',
AMT_TO_DRAW decimal(19,2),
LETTER_OF_CREDIT_REVIEW char(1) default 'N',
INVOICE_DOCUMENT_STATUS varchar(45),
LOC_CREATION_TYPE varchar(45),
SUSPEND_INVOICING char(1) default 'N',
UPDATE_TIMESTAMP datetime not null,
UPDATE_USER varchar(60) not null,
VER_NBR decimal(8,0) default 1 not null,
OBJ_ID varchar(36) not null
)
/

DELIMITER ;
