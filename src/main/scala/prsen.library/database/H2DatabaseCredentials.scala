package prsen.library.database

object H2DatabaseCredentials {
    def get(): Credentials = Credentials(
        url = "jdbc:h2:mem:testdb",
        user = "sa",
        password = "",
        driver = "org.h2.Driver"
    )
}

case class Credentials(
                        url: String,
                        user: String,
                        password: String,
                        driver: String
                      )
