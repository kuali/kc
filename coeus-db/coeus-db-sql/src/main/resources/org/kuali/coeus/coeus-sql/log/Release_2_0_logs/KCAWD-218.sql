alter table AWARD drop column BASIS_OF_PAYMENT_CODE;
alter table AWARD add BASIS_OF_PAYMENT_CODE VARCHAR2(3);
update AWARD set BASIS_OF_PAYMENT_CODE='1';


alter table AWARD drop column COMPETING_RENEWAL_PRPSL_DUE;
alter table AWARD add COMPETING_RENEWAL_PRPSL_DUE VARCHAR2(3);
update AWARD set COMPETING_RENEWAL_PRPSL_DUE='1';

alter table AWARD drop column METHOD_OF_PAYMENT_CODE;
alter table AWARD add METHOD_OF_PAYMENT_CODE VARCHAR2(3);
update AWARD set METHOD_OF_PAYMENT_CODE='1';

alter table AWARD drop column PAYMENT_INVOICE_FREQ_CODE;
alter table AWARD add PAYMENT_INVOICE_FREQ_CODE VARCHAR2(3);
update AWARD set PAYMENT_INVOICE_FREQ_CODE='1';

commit;
