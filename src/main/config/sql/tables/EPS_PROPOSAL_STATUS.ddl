create table EPS_PROPOSAL_STATUS
(
	proposal_number               varchar2 (12) constraint eps_proposal_status_n1 not null,
	budget_status_code                 char (1),
	update_timestamp              date          constraint eps_proposal_status_n2 not null,
  	update_user                   varchar2 (8)  constraint eps_proposal__status_n3 not null,
	VER_NBR NUMBER(8,0) DEFAULT 1 constraint eps_proposal_status_n4 NOT NULL,
  	OBJ_ID VARCHAR2(36) DEFAULT SYS_GUID() constraint eps_proposal_status_n5 NOT NULL,
  	constraint eps_proposal_status_p1 primary key (proposal_number),
  	constraint eps_proposal_status_c0 unique (obj_id)
)
/
