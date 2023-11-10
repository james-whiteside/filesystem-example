// Instantiate an admin with the specified email.
db.users.insert( {
    "email": "cedric@vaticle.com",
    "user_type": "admin"
} )

// Instantiate a user with the specified email.
db.users.insert( {
    "email": "jimmy@vaticle.com",
    "user_type": "user"
} )

// Instantiate a user group with the specified name, and assign the admin Cedric as its owner.
owner_id = db.users.find({ "email": "cedric@vaticle.com" }).next()["_id"]
db.groups.insert( {
    "name": "engineers",
    "group_type": "user_group",
    "owner": owner_id
} )

// Instantiate a file with the specified path, and assign the user Jimmy as its owner.
owner_id = db.users.find({ "email": "jimmy@vaticle.com" }).next()["_id"]
db.resources.insert( {
    "path": "/jimmy/benchmark-results.xlsx",
    "resource_type": "file",
    "owner": owner_id
} )

// Instantiate a file with the specified path, and assign the Engineers group as its owner.
owner_id = db.groups.find({ "name": "engineers" }).next()["_id"]
db.resources.insert( {
    "path": "/vaticle/feature-roadmap.pdf",
    "resource_type": "file",
    "owner": owner_id
} )
