package prsen.library

import io.swagger.annotations.{Api, ApiOperation}
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, RequestParam, RestController}
import prsen.library.database.ReaderPersistanceService
import prsen.library.model.reader.ReaderView

@RestController
@Api(value = "Library Management API")
@RequestMapping(path = Array("/api/library"))
class ReadersAPI(readerPersistanceService: ReaderPersistanceService) {

    @RequestMapping(path = Array("readers"), method = Array(RequestMethod.GET))
    @ApiOperation(value = "Get reader with surname")
    def getReaderInfoForSurname(@RequestParam(name = "reader name") lastName: String): ReaderView = readerPersistanceService.getReaderWithName(lastName).orNull
}
