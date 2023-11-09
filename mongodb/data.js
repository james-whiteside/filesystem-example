db.users.insert([
    {
        "email": "cedric@vaticle.com",
        "user_type": "admin"
    },
    {
        "email": "jimmy@vaticle.com",
        "user_type": "user"
    }
])

db.user_groups.insert([
    {
        "name": "engineers",
        "owner": {
            "type": "admin",
            "id": "cedric@vaticle.com"
        }
    }
])

db.resources.insert([
    {
        "path": "/jimmy/benchmark-results.xlsx",
        "resource_type": "file",
        "owner": {
            "type": "user",
            "id": "jimmy@vaticle.com"
        }
    },
    {
        "path": "/vaticle/feature-roadmap.pdf",
        "resource_type": "file",
        "owner": {
            "type": "user_group",
            "id": "engineers"
        }
    }
])