package prsen.library.database.jdbc

import org.springframework.stereotype.Component

@Component
class H2DatabaseCredentials extends Credentials (
    url = "jdbc:h2:mem:testdb",
    user = "sa",
    password = "",
    driver = "org.h2.Driver"
)
