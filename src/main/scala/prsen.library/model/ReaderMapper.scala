package prsen.library.model

import java.sql.{ResultSet, SQLException}
import java.util.UUID

import org.springframework.jdbc.core.RowMapper

class ReaderMapper extends RowMapper[ReaderView] {
    @throws[SQLException]
    override def mapRow(rs: ResultSet, rowNum: Int): ReaderView = {
        val readerView = new ReaderView(
            rs.getString("firstName"),
            rs.getString("lastName"),
            rs.getInt("rents"),
            UUID.fromString(rs.getString("guid")))
        readerView
    }
}
