package prsen.library.database

import org.springframework.stereotype.Component
import prsen.library.model.book.{BookMapper, BookView}

@Component
class BookPersistanceService(credentials: Credentials) extends JdbcService {
    
    def getBookWithTitle(title: String): Option[BookView] = {
        doWithJdbcTemplate(() => {
            val query: String = s"select * from Book where title = '$title'"
            val book: BookView = jdbcTemplate.queryForObject(query, new BookMapper)
            Some(book)
        })(credentials)
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
