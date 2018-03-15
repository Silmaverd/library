package prsen.library.model.rent

import java.sql.{ResultSet, SQLException}
import java.text.SimpleDateFormat
import java.util.{Locale, UUID}

import org.springframework.jdbc.core.RowMapper

import scala.collection.mutable

class RentMapper extends RowMapper[RentView] {
    
    @throws[SQLException]
    override def mapRow(rs: ResultSet, rowNum: Int): RentView = {
        val format = new SimpleDateFormat("YYYY-MM-DD", Locale.ENGLISH)
        val rentView = new RentView(
            rs.getInt("id"),
            UUID.fromString(rs.getString("readerGUID")),
            rs.getString("bookISBN"),
            new java.sql.Date(format.parse(rs.getString("rentalDate")).getTime),
            rs.getBoolean("closed"))
        rentView
    }
    
    def mapSet(list: java.util.List[java.util.Map[String, Object]]): Set[RentView] = {
        import scala.collection.JavaConverters._
        val format = new SimpleDateFormat("YYYY-MM-DD", Locale.ENGLISH)
        val buffer: mutable.MutableList[RentView] = mutable.MutableList.empty
        list.asScala.map(row => {
            val rentView = new RentView(
                row.get("id").asInstanceOf[Int],
                UUID.fromString(row.get("readerGUID").toString),
                row.get("bookISBN").toString,
                new java.sql.Date(format.parse(row.get("rentalDate").toString).getTime),
                row.get("closed").asInstanceOf[Boolean])
            buffer += rentView
        })
        buffer.toSet
    }
}
