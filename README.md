# fiona tenant

A simple multi-tenant application using spring boot with **schema per tenant** approach:
* Each user of the application has a tenant associated with him.
* Each tenant connection properties is stored in the database along with the users.

The tenant selection is not made by taking by HTTP header (_"X-Tenant-ID"_ for example) but instead uses the current logged user to do this:
1. At login the application connects to default schema (see below), retrieve the user from database (and the associed tenant), generate the JWT token with tenant information into token claims and returns this token.
2. The next calls to API then pass the JWT token (_Authorization Bearer ..._), the application validate this token, extract the tenant information from claims and sets the current tenant.
3. The operation (_selects/inserts/updates_) is then done in the correct tenant for that user.

This application has 3 schemas:
* **core**, default schema used by application to store the _users_, _roles_ and _tenant_ properties.
* **user1** used by _user1_.
* **user2**, used by _user2_.

The migration is done by Liquibase, running in a multi tenant mode (_MultiTenantSpringLiquibase_).

# swagger
[swagger api](http://localhost:8090/swagger-ui.html)
