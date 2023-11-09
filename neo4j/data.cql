CREATE (cedric:User:Admin {email: "cedric@vaticle.com"});

CREATE (jimmy:User {email: "jimmy@vaticle.com"});

MATCH (cedric:User {email: "cedric@vaticle.com"})
CREATE
    (engineers:UserGroup {name: "engineers"}),
    (cedric)-[:OWNS {ownership_type: "GroupOwnership"}]->(engineers);

MATCH (jimmy:User {email: "jimmy@vaticle.com"})
CREATE
    (benchmark:Resource:File {path: "/jimmy/benchmark-results.xlsx"}),
    (jimmy)-[:OWNS {ownership_type: "ResourceOwnership"}]->(benchmark);

MATCH (engineers:UserGroup {name: "engineers"})
CREATE
    (roadmap:Resource:File {path: "/vaticle/feature-roadmap.pdf"}),
    (engineers)-[:OWNS {ownership_type: "ResourceOwnership"}]->(roadmap);
