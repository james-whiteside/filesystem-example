-- Retrieve the ID of the feature roadmap's owner.
SELECT owner_id
FROM (
    SELECT
        resource_ownerships.id AS ownership_id,
        user_of_resource_ownerships.user_id AS owner_id,
        user_of_resource_ownerships.resource_id AS resource_id
    FROM resource_ownerships
    JOIN user_of_resource_ownerships USING (id)
    UNION
    SELECT
        resource_ownerships.id AS ownership_id,
        user_group_of_resource_ownerships.user_group_id AS owner_id,
        user_group_of_resource_ownerships.resource_id AS resource_id
    FROM resource_ownerships
    JOIN user_group_of_resource_ownerships USING (id)
) AS resource_ownerships
WHERE resource_id = '/vaticle/feature-roadmap.pdf';

-- Retrieve the IDs of all resources owned by Jimmy.
SELECT user_of_resource_ownerships.resource_id AS resource_id
FROM resource_ownerships
JOIN user_of_resource_ownerships USING (id)
WHERE user_of_resource_ownerships.user_id = 'jimmy@vaticle.com';

-- Retrieve the type and ID of all objects in the filesystem.
SELECT object_type, object_id
FROM (
    SELECT
        'user' AS object_type,
        email AS object_id
    FROM users
    WHERE email NOT IN (
        SELECT email
        FROM admins
    )
    UNION
    SELECT
        'admin' AS object_type,
        email AS object_id
    FROM admins
    UNION
    SELECT
        'user_group' AS object_type,
        name AS object_id
    FROM user_groups
    UNION
    SELECT
        'file' AS object_type,
        path AS object_id
    FROM files
) AS objects;

-- Retrieve the details of every ownership in the filesystem.
SELECT
    ownership_type,
    owned_type,
    owned_id,
    owner_type,
    owner_id
FROM (
    SELECT
        'group_ownership' AS ownership_type,
        'user_group' AS owned_type,
        admin_of_group_ownerships.user_group_id AS owned_id,
        'admin' AS owner_type,
        admin_of_group_ownerships.admin_id AS owner_id
    FROM ownerships
    JOIN admin_of_group_ownerships USING (id)
    UNION ALL
    SELECT
        'resource_ownership' AS ownership_type,
        'file' AS owned_type,
        files.path AS owned_id,
        'user' AS owner_type,
        user_of_resource_ownerships.user_id AS owner_id
    FROM ownerships
    JOIN user_of_resource_ownerships USING (id)
    JOIN files ON files.path = user_of_resource_ownerships.resource_id
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
    JOIN user_of_resource_ownerships USING (id)
    JOIN files ON files.path = user_of_resource_ownerships.resource_id
    JOIN admins ON admins.email = user_of_resource_ownerships.user_id
    UNION ALL
    SELECT
        'resource_ownership' AS ownership_type,
        'file' AS owned_type,
        files.path AS owned_id,
        'user_group' AS owner_type,
        user_group_of_resource_ownerships.user_group_id AS owner_id
    FROM ownerships
    JOIN user_group_of_resource_ownerships USING (id)
    JOIN files ON files.path = user_group_of_resource_ownerships.resource_id
) AS ownerships;
