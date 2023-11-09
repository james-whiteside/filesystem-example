// Instantiate an admin with the specified email.
db.users.insert(
    {
        "email": "cedric@vaticle.com",
        "user_type": "admin"
    }
)

// Instantiate a user with the specified email.
db.users.insert(
    {
        "email": "jimmy@vaticle.com",
        "user_type": "user"
    }
)

// Instantiate a user group with the specified name, and assign the admin Cedric as its owner.
db.user_groups.insert(
    {
        "name": "engineers",
        "owner": {
            "type": "admin",
            "id": "cedric@vaticle.com"
        }
    }
)

// Instantiate a file with the specified path, and assign the user Jimmy as its owner.
db.resources.insert(
    {
        "path": "/jimmy/benchmark-results.xlsx",
        "resource_type": "file",
        "owner": {
            "type": "user",
            "id": "jimmy@vaticle.com"
        }
    }
)

// Instantiate a file with the specified path, and assign the Engineers group as its owner.
db.resources.insert(
    {
        "path": "/vaticle/feature-roadmap.pdf",
        "resource_type": "file",
        "owner": {
            "type": "user_group",
            "id": "engineers"
        }
    }
)
