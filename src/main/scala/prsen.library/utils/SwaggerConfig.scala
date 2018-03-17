package prsen.library.utils

import org.springframework.context.annotation.{ComponentScan, Configuration, PropertySource}
import prsen.library.api.BooksAPI
import springfox.documentation.builders.{ApiInfoBuilder, PathSelectors}
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
@PropertySource(Array("classpath:swagger.properties"))
@ComponentScan(basePackageClasses = Array(classOf[BooksAPI]))
class SwaggerConfig {
    private val swaggerApiVersion: String  = "1.0"
    private val licenseText: String = "License"
    private val title: String = "REST API"
    private val description: String = "API for Library Managment"
    
    private val apiInfo: ApiInfo =
        new ApiInfoBuilder()
          .title(title)
          .description(description)
          .version(swaggerApiVersion)
          .license(licenseText)
          .build()
    
    val docket: Docket = new Docket(DocumentationType.SWAGGER_2)
      .apiInfo(apiInfo)
      .pathMapping("/")
      .select()
      .paths(PathSelectors.regex("/api.*"))
      .build()
    
    
}
