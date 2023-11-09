CREATE TABLE users (
    email TEXT PRIMARY KEY
);

CREATE TABLE admins (
    email TEXT PRIMARY KEY REFERENCES users(email)
);

CREATE TABLE user_groups (
    name TEXT PRIMARY KEY
);

CREATE TABLE resources (
    id TEXT PRIMARY KEY
);

CREATE TABLE files (
    path TEXT PRIMARY KEY REFERENCES resources(id)
);

CREATE TABLE ownerships (
    id SERIAL PRIMARY KEY
);

CREATE TABLE resource_ownerships (
    id INT PRIMARY KEY REFERENCES ownerships(id)
);

CREATE TABLE user_of_resource_ownerships (
    id INT PRIMARY KEY REFERENCES resource_ownerships(id),
    user_id TEXT REFERENCES users(email),
    resource_id TEXT REFERENCES resources(id)
);

CREATE TABLE user_group_of_resource_ownerships (
    id INT PRIMARY KEY REFERENCES resource_ownerships(id),
    user_group_id TEXT REFERENCES user_groups(name),
    resource_id TEXT REFERENCES resources (id)
);

CREATE TABLE group_ownerships (
    id INT PRIMARY KEY REFERENCES ownerships(id)
);

CREATE TABLE admin_of_group_ownerships (
    id INT PRIMARY KEY REFERENCES group_ownerships(id),
    admin_id TEXT REFERENCES admins(email),
    user_group_id TEXT REFERENCES user_groups(name)
);
