package prsen.library.model

import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet
import java.sql.SQLException


class BookMapper extends RowMapper[BookView] {
    @throws[SQLException]
    override def mapRow(rs: ResultSet, rowNum: Int): BookView = {
        val bookView = new BookView(
            rs.getString("ISBN"),
            rs.getString("title"),
            rs.getString("author"),
            rs.getBoolean("rented"))
        bookView
    }
}