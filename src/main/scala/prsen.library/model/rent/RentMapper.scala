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
            rs.getInt("readerId"),
            rs.getInt("bookId"),
            new java.sql.Date(format.parse(rs.getString("startingDate")).getTime),
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
                row.get("readerId").asInstanceOf[Int],
                row.get("bookId").asInstanceOf[Int],
                new java.sql.Date(format.parse(row.get("startingDate").toString).getTime),
                row.get("closed").asInstanceOf[Boolean])
            buffer += rentView
        })
        buffer.toSet
    }
}
