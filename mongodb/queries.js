// Retrieve the ID of the feature roadmap's owner.
db.resources.aggregate( [
    { "$match": { "path": "/vaticle/feature-roadmap.pdf" } },
    { "$lookup": {
        "from": "users",
        "localField": "owner",
        "foreignField": "_id",
        "as": "user_owners"
    } },
    { "$lookup": {
        "from": "groups",
        "localField": "owner",
        "foreignField": "_id",
        "as": "user_group_owners"
    } },
    { "$addFields": { "owners": { "$concatArrays": [ "$user_owners", "$user_group_owners" ] } } },
    { "$unwind": "$owners" },
    { "$project": {
        "_id": false,
        "owner_id": { "$switch": { "branches": [
            { "case": { "$eq": [ "$owners.user_type", "user" ] }, "then": "$owners.email" },
            { "case": { "$eq": [ "$owners.user_type", "admin" ] }, "then": "$owners.email" },
            { "case": { "$eq": [ "$owners.group_type", "user_group" ] }, "then": "$owners.name" }
        ] } }
    } }
] )

// Retrieve the IDs of all resources owned by Jimmy.
db.users.aggregate( [
    { "$match": { "email": "jimmy@vaticle.com" } },
    { "$lookup": {
        "from": "resources",
        "localField": "_id",
        "foreignField": "owner",
        "as": "owned_resources"
    } },
    { "$unwind": "$owned_resources" },
    { "$project": {
        "_id": false,
        "owner_id": { "$switch": { "branches": [
            { "case": { "$eq": [ "$owned_resources.resource_type", "file" ] }, "then": "$owned_resources.path" }
        ] } }
    } }
] )

// Retrieve the type and ID of every object in the filesystem.
db.users.aggregate( [
    { "$unionWith": { "coll": "groups" } },
    { "$unionWith": { "coll": "resources" } },
    { "$addFields": {
        "object_type": { "$switch": { "branches": [
            { "case": { "$eq": [ "$user_type", "user" ] }, "then": "user" },
            { "case": { "$eq": [ "$user_type", "admin" ] }, "then": "admin" },
            { "case": { "$eq": [ "$group_type", "user_group" ] }, "then": "user_group" },
            { "case": { "$eq": [ "$resource_type", "file" ] }, "then": "file" }
        ] } }
    } },
    { "$project": {
        "_id": false,
        "object_type": true,
        "object_id": { "$switch": { "branches": [
            { "case": { "$eq": [ "$user_type", "user" ] }, "then": "$email" },
            { "case": { "$eq": [ "$user_type", "admin" ] }, "then": "$email" },
            { "case": { "$eq": [ "$group_type", "user_group" ] }, "then": "$name" },
            { "case": { "$eq": [ "$resource_type", "file" ] }, "then": "$path" }
        ] } }
    } }
] )

// Retrieve the details of every ownership in the filesystem.
db.groups.aggregate( [
    { "$addFields": { "ownership_type": "group_ownership" } },
    { "$unionWith": {
        "coll": "resources",
        "pipeline": [ { "$addFields": { "ownership_type": "resource_ownership" } } ]
    } },
    { "$lookup": {
        "from": "users",
        "localField": "owner",
        "foreignField": "_id",
        "as": "user_owners"
    } },
    { "$lookup": {
        "from": "groups",
        "localField": "owner",
        "foreignField": "_id",
        "as": "user_group_owners"
    } },
    { "$addFields": { "owners": { "$concatArrays": [ "$user_owners", "$user_group_owners" ] } } },
    { "$unwind": "$owners" },
    { "$addFields": {
        "owned_type": { "$switch": { "branches": [
            { "case": { "$eq": [ "$group_type", "user_group" ] }, "then": "user_group" },
            { "case": { "$eq": [ "$resource_type", "file" ] }, "then": "file" }
        ] } }
    } },
    { "$addFields": {
        "owned_id": { "$switch": { "branches": [
            { "case": { "$eq": [ "$group_type", "user_group" ] }, "then": "$name" },
            { "case": { "$eq": [ "$resource_type", "file" ] }, "then": "$path" }
        ] } }
    } },
    { "$addFields": {
        "owner_type": { "$switch": { "branches": [
            { "case": { "$eq": [ "$owners.user_type", "user" ] }, "then": "user" },
            { "case": { "$eq": [ "$owners.user_type", "admin" ] }, "then": "admin" },
            { "case": { "$eq": [ "$owners.group_type", "user_group" ] }, "then": "user_group" }
        ] } }
    } },
    { "$project": {
        "_id": false,
        "ownership_type": true,
        "owned_type": true,
        "owned_id": true,
        "owner_type": true,
        "owner_id": { "$switch": { "branches": [
            { "case": { "$eq": [ "$owners.user_type", "user" ] }, "then": "$owners.email" },
            { "case": { "$eq": [ "$owners.user_type", "admin" ] }, "then": "$owners.email" },
            { "case": { "$eq": [ "$owners.group_type", "user_group" ] }, "then": "$owners.name" }
        ] } }
    } }
] )
