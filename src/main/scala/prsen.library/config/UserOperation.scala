package prsen.library.config

import io.swagger.annotations.{ApiModel, ApiModelProperty}
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

/*trait UserOperationFormat extends DefaultJsonProtocol{
    implicit protected val userOperationFormat: RootJsonFormat[UserOperation] = jsonFormat2(UserOperation)
}*/

@ApiModel
class UserOperation(@ApiModelProperty(required = true) username: String,
                    @ApiModelProperty(required = true) processId: String) {
}
