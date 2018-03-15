package prsen.library.database

import java.util.UUID

import prsen.library.model.reader.{ReaderMapper, ReaderView}

import scala.collection.mutable

class ReaderPersistanceService extends JdbcService {
    def getReaderWithLastName(lastName: String)(implicit credentials: Credentials): Option[ReaderView] = {
        doWithJdbcTemplate(() => {
            val query: String = s"select * from Reader where lastName = '$lastName'"
            val reader: ReaderView = jdbcTemplate.queryForObject(query, new ReaderMapper)
            Some(reader)
        })(credentials)
    }
    
    def mapSet(list : java.util.List[java.util.Map[String, Object]]) : Set[ReaderView] = {
        import scala.collection.JavaConverters._
        val buffer: mutable.MutableList[ReaderView] = mutable.MutableList.empty
        list.asScala.map(row => {
            val readerView = new ReaderView(
                row.get("firstName").toString,
                row.get("lastName").toString,
                row.get("rents").asInstanceOf[Int],
                UUID.fromString(row.get("guid").toString))
            buffer += readerView
        })
        buffer.toSet
    }
}
