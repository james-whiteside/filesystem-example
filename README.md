# Filesystem example code and queries

This repository contains the code and queries featured in the following articles and lectures in the TypeDB Fundamentals
series:

- **Why We Need a Polymorphic Database**
[[article](https://typedb.com/fundamentals/why-polymorphic-database)]
[[lecture](https://typedb.com/lectures/why-polymorphic-database)]
- **TypeDB: the Polymorphic Database**
[[article](https://typedb.com/fundamentals/typedb-polymorphic-database)]
[[lecture](https://typedb.com/lectures/typedb-polymorphic-database)]

The material is illustrative only and not intended to be run as a complete application. It contains the following
sections:

- [Java object model](https://github.com/james-whiteside/filesystem-example/tree/master/filesystem/src):
  Lightweight application code for a simple DAC filesystem. It includes a number of class and interface definitions, and
  features some [query-like operations](https://github.com/james-whiteside/filesystem-example/blob/master/filesystem/src/Main.java)
  that can be performed on the model.
- [PostgreSQL implementation](https://github.com/james-whiteside/filesystem-example/tree/master/postgresql):
  A relational implementation of the Java object model, comprising the schema, data, and queries necessary to directly
  replicate the functionalities of the object model.
- [MongoDB implementation](https://github.com/james-whiteside/filesystem-example/tree/master/mongodb):
  A document implementation of the Java object model, comprising the data and queries necessary to directly replicate
  the functionalities of the object model.
- [Neo4j implementation](https://github.com/james-whiteside/filesystem-example/tree/master/neo4j):
  A graph implementation of the Java object model, comprising the data and queries necessary to directly replicate the
  functionalities of the object model.
- [TypeDB implementation](https://github.com/james-whiteside/filesystem-example/tree/master/typedb):
  A polymorphic implementation of an *extended version* of the Java object model, comprising a
  [schema](https://github.com/james-whiteside/filesystem-example/blob/master/typedb/schema-complete.tql),
  [data](https://github.com/james-whiteside/filesystem-example/blob/master/typedb/data-complete.tql), 
  and queries. The schema includes the necessary types required to run [the same queries](https://github.com/james-whiteside/filesystem-example/blob/master/typedb/comparative-queries.tql)
  as the other database implementations, though the actual data and query results differ. For those following along with
  the examples in **TypeDB: the Polymorphic Database**, incremental versions of the [schema](https://github.com/james-whiteside/filesystem-example/tree/master/typedb/schema-incremental)
  and [data](https://github.com/james-whiteside/filesystem-example/tree/master/typedb/data-incremental)
  are included, alongside the [individual queries](https://github.com/james-whiteside/filesystem-example/tree/master/typedb/queries)
  featured.