package prsen.library.model.reader

import java.util.UUID

import scala.beans.BeanProperty

class ReaderViews(
                   @BeanProperty firstName: String,
                   @BeanProperty lastName: String,
                   @BeanProperty rentsNumber: Int = 0,
                   @BeanProperty readerUUID: UUID = UUID.randomUUID()
                 )
