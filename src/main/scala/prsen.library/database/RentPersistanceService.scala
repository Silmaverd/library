package prsen.library.database

import prsen.library.model.rent.{RentMapper, RentView}

class RentPersistanceService extends JdbcService {
    def getRentWithId(id: Int)(implicit credentials: Credentials): Option[RentView] = {
        doWithJdbcTemplate(() => {
            val query: String = s"select * from Rent where id = '$id'"
            val rent: RentView = jdbcTemplate.queryForObject(query, new RentMapper)
            Some(rent)
        })(credentials)
    }
}
