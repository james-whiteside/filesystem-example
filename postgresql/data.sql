-- Instantiate an admin with the specified email.
DO $$
BEGIN
	INSERT INTO users (email)
	VALUES ('cedric@vaticle.com');

	INSERT INTO admins (email)
	VALUES ('cedric@vaticle.com');
END $$;

-- Instantiate a user with the specified email.
DO $$
BEGIN
	INSERT INTO users (email)
	VALUES ('jimmy@vaticle.com');
END $$;

-- Instantiate a user group with the specified name, and assign the user Cedric as its owner.
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
	VALUES (ownership_id, 'cedric@vaticle.com', 'engineers');
END $$;

-- Instantiate a file with the specified path, and assign the user Jimmy as its owner.
DO $$
DECLARE
	ownership_id INT;
BEGIN
	INSERT INTO resources (id)
	VALUES ('/jimmy/benchmark-results.xlsx');
	
	INSERT INTO files (path)
	VALUES ('/jimmy/benchmark-results.xlsx');
	
	INSERT INTO ownerships (id)
	VALUES (DEFAULT)
	RETURNING id INTO ownership_id;
	
	INSERT INTO resource_ownerships (id)
	VALUES (ownership_id);
	
	INSERT INTO user_of_resource_ownerships (id, user_id, resource_id)
	VALUES (ownership_id, 'jimmy@vaticle.com', '/jimmy/benchmark-results.xlsx');
END $$;

-- Instantiate a file with the specified path, and assign the Engineers group as its owner.
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
