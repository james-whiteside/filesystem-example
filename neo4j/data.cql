// Instantiate an admin with the specified email.
CREATE (cedric:User:Admin {email: "cedric@vaticle.com"});

// Instantiate a user with the specified email.
CREATE (jimmy:User {email: "jimmy@vaticle.com"});

// Instantiate a user group with the specified name, and assign the admin Cedric as its owner.
MATCH (cedric:Admin {email: "cedric@vaticle.com"})
CREATE
    (engineers:UserGroup {name: "engineers"}),
    (cedric)-[:OWNS {ownership_type: "GroupOwnership"}]->(engineers);

// Instantiate a file with the specified path, and assign the user Jimmy as its owner.
MATCH (jimmy:User {email: "jimmy@vaticle.com"})
CREATE
    (benchmark:Resource:File {path: "/jimmy/benchmark-results.xlsx"}),
    (jimmy)-[:OWNS {ownership_type: "ResourceOwnership"}]->(benchmark);

// Instantiate a file with the specified path, and assign the Engineers group as its owner.
MATCH (engineers:UserGroup {name: "engineers"})
CREATE
    (roadmap:Resource:File {path: "/vaticle/feature-roadmap.pdf"}),
    (engineers)-[:OWNS {ownership_type: "ResourceOwnership"}]->(roadmap);
