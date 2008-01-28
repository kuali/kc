#
# $Id: load_inv_credit_type.sql,v 1.4 2008-01-28 20:00:33 lprzybyl Exp $
#
insert into INV_CREDIT_TYPE (INV_CREDIT_TYPE_CODE, DESCRIPTION, ADDS_TO_HUNDRED, UPDATE_TIMESTAMP, UPDATE_USER, ACTIVE_FLAG) values ('0', 'Recognition', 'Y', sysdate, 'kradev', 'Y');
insert into INV_CREDIT_TYPE (INV_CREDIT_TYPE_CODE, DESCRIPTION, ADDS_TO_HUNDRED, UPDATE_TIMESTAMP, UPDATE_USER, ACTIVE_FLAG) values ('1', 'Responsibility', 'Y', sysdate, 'kradev', 'Y');
insert into INV_CREDIT_TYPE (INV_CREDIT_TYPE_CODE, DESCRIPTION, ADDS_TO_HUNDRED, UPDATE_TIMESTAMP, UPDATE_USER, ACTIVE_FLAG) values ('2', 'Space', 'Y', sysdate, 'kradev', 'Y');
insert into INV_CREDIT_TYPE (INV_CREDIT_TYPE_CODE, DESCRIPTION, ADDS_TO_HUNDRED, UPDATE_TIMESTAMP, UPDATE_USER, ACTIVE_FLAG) values ('3', 'Financial', 'Y', sysdate, 'kradev', 'Y');
