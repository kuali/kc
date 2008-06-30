CREATE OR REPLACE PROCEDURE          GET_ORGA_NAME_ADDRESSID (
	as_organization_id in osp$organization.organization_id%type,
	as_organization_name out osp$organization.organization_name%type,
	as_contact_address_id out osp$organization.contact_address_id%type,
	as_congressional_district out osp$organization.congressional_district%type) is
begin
	select organization_name,contact_address_id, congressional_district
	into as_organization_name, as_contact_address_id, as_congressional_district
	from osp$organization
	where ltrim(rtrim(organization_id)) = ltrim(rtrim(as_organization_id));

	EXCEPTION

			 WHEN NO_DATA_FOUND THEN

			 as_organization_name := '';
			 as_contact_address_id := 0;
			 as_congressional_district := '';


end;
/

