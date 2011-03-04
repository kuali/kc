sqlplus <username>/<password>@<service> @KRA-Release1_0

sqlldr <username>/<password>@<service> control=DML/LOB-CTL/EXEMPTION_TYPE.ctl
sqlldr <username>/<password>@<service> control=DML/LOB-CTL/EN_DOC_HDR_CNTNT_T.ctl
sqlldr <username>/<password>@<service> control=DML/LOB-CTL/EN_RULE_ATTRIB_T.ctl
sqlldr <username>/<password>@<service> control=DML/LOB-CTL/FP_MAINTENANCE_DOCUMENT_T.ctl
sqlldr <username>/<password>@<service> control=DML/LOB-CTL/SPONSOR_FORM_TEMPLATES.ctl
sqlldr <username>/<password>@<service> control=DML/LOB-CTL/YNQ_EXPLANATION.ctl

