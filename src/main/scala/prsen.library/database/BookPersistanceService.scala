package prsen.library.database

import prsen.library.model.book.{BookMapper, BookView}

class BookPersistanceService extends JdbcService {
    
    def getBookWithTitle(title: String)(implicit credentials: Credentials): Option[BookView] = {
        doWithJdbcTemplate(() => {
            val query: String = s"select * from Book where title = '$title'"
            val book: BookView = jdbcTemplate.queryForObject(query, new BookMapper)
            Some(book)
        })(credentials)
    }
    
    def getAllBooksOfAuthor(author: String)(implicit credentials: Credentials): Option[Set[BookView]] = {
        doWithJdbcTemplate(() => {
            val query: String = s"select * from Book where author = '$author'"
            val books = jdbcTemplate.queryForList(query)
            val bookMapper = new BookMapper
            Some(bookMapper.mapSet(books))
        })(credentials)
    }
}
