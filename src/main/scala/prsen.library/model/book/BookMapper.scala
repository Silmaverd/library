package prsen.library.model.book

import java.sql.{ResultSet, SQLException}

import org.springframework.jdbc.core.RowMapper
import prsen.library.model.rent.RentView

import scala.collection.mutable


class BookMapper extends RowMapper[BookView] {
    @throws[SQLException]
    override def mapRow(rs: ResultSet, rowNum: Int): BookView = {
        val bookView = new BookView(
            rs.getInt("id"),
            rs.getString("title"),
            rs.getString("author"),
            rs.getBoolean("rented"))
        bookView
    }
    
    def mapSet(list : java.util.List[java.util.Map[String, Object]]) : Set[BookView] = {
        import scala.collection.JavaConverters._
        val buffer: mutable.MutableList[BookView] = mutable.MutableList.empty
        list.asScala.map(row => {
            val bookView = new BookView(
                row.get("id").asInstanceOf[Int],
                row.get("title").toString,
                row.get("author").toString,
                row.get("rented").asInstanceOf[Boolean])
            buffer += bookView
        })
        buffer.toSet
    }
}