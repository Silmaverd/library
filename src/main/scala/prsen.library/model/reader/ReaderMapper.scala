package prsen.library.model.reader

import java.sql.{ResultSet, SQLException}
import java.util.UUID

import org.springframework.jdbc.core.RowMapper

import scala.collection.mutable

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
    
    def mapSet(list : java.util.List[java.util.Map[String, Object]]) : Set[ReaderView] = {
        import scala.collection.JavaConverters._
        val buffer: mutable.MutableList[ReaderView] = mutable.MutableList.empty
        list.asScala.map(row => {
            val readerView = new ReaderView(
                row.get("firstName").toString,
                row.get("lastName").toString,
                row.get("rents").asInstanceOf[Int],
                UUID.fromString(row.get("rented").toString))
            buffer += readerView
        })
        buffer.toSet
    }
}
