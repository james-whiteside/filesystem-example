DO $$
BEGIN
	INSERT INTO users (email)
	VALUES ('naomi@vaticle.com');

	INSERT INTO admins (email)
	VALUES ('naomi@vaticle.com');
END $$;

DO $$
BEGIN
	INSERT INTO users (email)
	VALUES ('amos@vaticle.com');
END $$;

DO $$
DECLARE
	ownership_id INT;
BEGIN
	INSERT INTO user_groups (name)
	VALUES ('engineers');

	INSERT INTO ownerships (id)
	VALUES (DEFAULT)
	RETURNING id INTO ownership_id;
	
	INSERT INTO group_ownerships (id)
	VALUES (ownership_id);
	
	INSERT INTO admin_of_group_ownerships (id, admin_id, user_group_id)
	VALUES (ownership_id, 'naomi@vaticle.com', 'engineers');
END $$;

DO $$
DECLARE
	ownership_id INT;
BEGIN
	INSERT INTO resources (id)
	VALUES ('/amos/benchmark-results.xlsx');
	
	INSERT INTO files (path)
	VALUES ('/amos/benchmark-results.xlsx');
	
	INSERT INTO ownerships (id)
	VALUES (DEFAULT)
	RETURNING id INTO ownership_id;
	
	INSERT INTO resource_ownerships (id)
	VALUES (ownership_id);
	
	INSERT INTO user_of_resource_ownerships (id, user_id, resource_id)
	VALUES (ownership_id, 'amos@vaticle.com', '/amos/benchmark-results.xlsx');
END $$;

DO $$
DECLARE
	ownership_id INT;
BEGIN
	INSERT INTO resources (id)
	VALUES ('/vaticle/feature-roadmap.pdf');
	
	INSERT INTO files (path)
	VALUES ('/vaticle/feature-roadmap.pdf');
	
	INSERT INTO ownerships (id)
	VALUES (DEFAULT)
	RETURNING id INTO ownership_id;
	
	INSERT INTO resource_ownerships (id)
	VALUES (ownership_id);
	
	INSERT INTO user_group_of_resource_ownerships (id, user_group_id, resource_id)
	VALUES (ownership_id, 'engineers', '/vaticle/feature-roadmap.pdf');
END $$;