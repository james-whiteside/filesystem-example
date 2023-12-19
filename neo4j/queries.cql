// Retrieve the ID of the feature roadmap's owner.
MATCH
    (roadmap:File {path: "/jimmy/benchmark-results.xlsx"}),
    (owner)-[:OWNS]->(roadmap)
UNWIND labels(owner) AS owner_type
WITH
    owner, owner_type,
    {
        User: "email",
        Admin: "email",
        UserGroup: "name",
        File: "path"
    } AS id_type_map
WHERE owner_type IN keys(id_type_map)
AND id_type_map[owner_type] IN keys(owner)
RETURN owner[id_type_map[owner_type]] AS owner_id;

// Retrieve the IDs of all resources owned by Jimmy.
MATCH
    (jimmy:User {email: "jimmy@vaticle.com"}),
    (jimmy)-[:OWNS]->(resource:Resource)
UNWIND labels(resource) AS resource_type
WITH
    resource, resource_type,
    {
        User: "email",
        Admin: "email",
        UserGroup: "name",
        File: "path"
    } AS id_type_map
WHERE resource_type IN keys(id_type_map)
AND id_type_map[resource_type] IN keys(resource)
RETURN resource[id_type_map[resource_type]] AS resource_id;

// Retrieve the type and ID of every object in the filesystem.
MATCH (object)
UNWIND labels(object) AS object_type
WITH object, object_type,
    {
        User: "email",
        Admin: "email",
        UserGroup: "name",
        File: "path"
    } AS id_type_map
WHERE object_type IN keys(id_type_map)
AND id_type_map[object_type] IN keys(object)
AND NOT (
     object_type = "User"
     AND "Admin" IN labels(object)
)
RETURN object_type, object[id_type_map[object_type]] AS object_id;

// Retrieve the details of every ownership in the filesystem.
MATCH (owner)-[ownership:OWNS]->(owned)
UNWIND labels(owned) AS owned_type
UNWIND labels(owner) AS owner_type
WITH ownership, owned, owned_type, owner, owner_type,
    {
        User: "email",
        Admin: "email",
        UserGroup: "name",
        File: "path"
    } AS id_type_map
WHERE owned_type IN keys(id_type_map)
AND id_type_map[owned_type] IN keys(owned)
AND owner_type IN keys(id_type_map)
AND id_type_map[owner_type] IN keys(owner)
AND NOT (
    owner_type = "User"
    AND "Admin" IN labels(owner)
)
RETURN
    ownership.ownership_type AS ownership_type,
    owned_type,
    owned[id_type_map[owned_type]] AS owned_id,
    owner_type,
    owner[id_type_map[owner_type]] AS owner_id;
