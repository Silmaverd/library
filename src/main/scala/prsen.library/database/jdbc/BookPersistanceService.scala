package prsen.library.database.jdbc

import org.springframework.stereotype.Component
import prsen.library.model.book.{BookMapper, BookView}

@Component
@Deprecated
class BookPersistanceService(credentials: Credentials) extends JdbcService {
    
    def getBookWithTitle(title: String): Option[BookView] = {
        try {
            doWithJdbcTemplate(() => {
                val query: String = s"select * from Book where title = '$title'"
                val book: BookView = jdbcTemplate.queryForObject(query, new BookMapper)
                Some(book)
            })(credentials)
        } catch{
            case t: Throwable => None
        }
    }
    
    def getAllBooksOfAuthor(author: String): Option[Set[BookView]] = {
         doWithJdbcTemplate(() => {
            val query: String = s"select * from Book where author = '$author'"
            val books = jdbcTemplate.queryForList(query)
            val bookMapper = new BookMapper
            Some(bookMapper.mapSet(books))
        })(credentials)
    }
}
