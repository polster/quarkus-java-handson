db.createUser(
    {
        user: "ecomm123",
        pwd: "34adg234f",
        roles: [
            { role: "readWrite", db: "ecommerce-demo" }
        ]
    }
)

db.createUser(
    {
        user: "ecomm897",
        pwd: "drg3c4gd",
        roles: [
            { role: "readWrite", db: "ecommerce-demo" }
        ]
    }
)