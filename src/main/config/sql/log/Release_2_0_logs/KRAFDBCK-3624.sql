update award_template set description = regexp_replace(description, '[0-9]+[:](.+$)','\1');
commit;