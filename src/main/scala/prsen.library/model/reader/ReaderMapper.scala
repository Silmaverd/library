package prsen.library.model.reader

import java.sql.{ResultSet, SQLException}
import java.util.UUID

import org.springframework.jdbc.core.RowMapper

import scala.collection.mutable

class ReaderMapper extends RowMapper[ReaderView] {
    @throws[SQLException]
    override def mapRow(rs: ResultSet, rowNum: Int): ReaderView = {
        val readerView = new ReaderView(
            rs.getString("readerName"),
            rs.getInt("rents"),
            rs.getInt("id"))
        readerView
    }
    
    def mapSet(list : java.util.List[java.util.Map[String, Object]]) : Set[ReaderView] = {
        import scala.collection.JavaConverters._
        val buffer: mutable.MutableList[ReaderView] = mutable.MutableList.empty
        list.asScala.map(row => {
            val readerView = new ReaderView(
                row.get("readerName").toString,
                row.get("rents").asInstanceOf[Int],
                row.get("id").asInstanceOf[Int])
            buffer += readerView
        })
        buffer.toSet
    }
}
