// Retrieve the ID of the feature roadmap's owner.
db.resources.find(
    { "path": "/vaticle/feature-roadmap.pdf" },
    { "_id": false, "owner_id": "$owner.id" }
)


// Retrieve the IDs of all resources owned by Jimmy.
db.resources.aggregate([
    { "$match": { "owner.id": "jimmy@vaticle.com" } },
    { "$project": { "_id": false, "resource_id": {
        "$switch": { "branches": [
                { "case": { "$eq": ["$resource_type", "file"] }, "then": "$path" }
            ] }
    } } }
])

// Retrieve the type and ID of all objects in the filesystem.
db.users.aggregate([
    { "$project": { "_id": false, "object_type": "$user_type", "object_id": "$email" } },
    { "$unionWith": { "coll": "user_groups", "pipeline": [
        { "$project": { "_id": false, "object_type": "user_group", "object_id": "$name" } }
    ] } },
    { "$unionWith": { "coll": "resources", "pipeline": [
        { "$project": { "_id": false, "object_type": "$resource_type", "object_id": "$path" } }
    ] } }
])

// Retrieve the details of every ownership in the filesystem.
db.user_groups.aggregate([
    { "$project": {
            "_id": false,
            "ownership_type": "group_ownership",
            "owned_type": "user_group",
            "owned_id": "$name",
            "owner_type": "$owner.type",
            "owner_id": "$owner.id"
    } },
    { "$unionWith": { "coll": "resources", "pipeline": [
        { "$project": {
            "_id": false,
            "ownership_type": "resource_ownership",
            "owned_type": "$resource_type",
            "owned_id": "$path",
            "owner_type": "$owner.type",
            "owner_id": "$owner.id"
        } }
    ] } }
])
