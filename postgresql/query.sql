SELECT
	'group_ownership' AS ownership_type,
	'user_group' AS owned_type,
	admin_of_group_ownerships.user_group_id AS owned_id,
	'admin' AS owner_type,
	admin_of_group_ownerships.admin_id AS owner_id
FROM ownerships
JOIN group_ownerships
ON group_ownerships.id = ownerships.id
JOIN admin_of_group_ownerships
ON admin_of_group_ownerships.id = group_ownerships.id
UNION ALL
SELECT
	'resource_ownership' AS ownership_type,
	'file' AS owned_type,
	files.path AS owned_id,
	'user' AS owner_type,
	user_of_resource_ownerships.user_id AS owner_id
FROM ownerships
JOIN resource_ownerships
ON resource_ownerships.id = ownerships.id
JOIN user_of_resource_ownerships
ON user_of_resource_ownerships.id = resource_ownerships.id
JOIN files
ON files.path = user_of_resource_ownerships.resource_id
WHERE user_of_resource_ownerships.user_id NOT IN (
	SELECT admins.email
	FROM admins
)
UNION ALL
SELECT
	'resource_ownership' AS ownership_type,
	'file' AS owned_type,
	files.path AS owned_id,
	'admin' AS owner_type,
	admins.email AS owner_id
FROM ownerships
JOIN resource_ownerships
ON resource_ownerships.id = ownerships.id
JOIN user_of_resource_ownerships
ON user_of_resource_ownerships.id = resource_ownerships.id
JOIN files
ON files.path = user_of_resource_ownerships.resource_id
JOIN admins
ON admins.email = user_of_resource_ownerships.user_id
UNION ALL
SELECT
	'resource_ownership' AS ownership_type,
	'file' AS owned_type,
	files.path AS owned_id,
	'user_group' AS owner_type,
	user_group_of_resource_ownerships.user_group_id AS owner_id
FROM ownerships
JOIN resource_ownerships
ON resource_ownerships.id = ownerships.id
JOIN user_group_of_resource_ownerships
ON user_group_of_resource_ownerships.id = resource_ownerships.id
JOIN files
ON files.path = user_group_of_resource_ownerships.resource_id;