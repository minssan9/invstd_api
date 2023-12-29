
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@OpenAPIDefinition(info = Info(title = "00 프로젝트 API 명세서", description = "00 프로젝트에 사용되는 API 명세서", version = "v1"))
@Configuration
class SwaggerConfig {
    @Bean // 운영 환경에는 Swagger를 비활성화하기 위해 추가했습니다.
    @Profile("!Prod")
    fun openAPI(): OpenAPI {
//        val jwtSchemeName: String = JwtTokenProvider.AUTHORIZATION_HEADER
//        val securityRequirement: SecurityRequirement = SecurityRequirement().addList(jwtSchemeName)
//        val components = Components()
//            .addSecuritySchemes(
//                jwtSchemeName, SecurityScheme()
//                    .name(jwtSchemeName)
//                    .type(SecurityScheme.Type.HTTP)
//                    .scheme(BEARER_TOKEN_PREFIX)
//                    .bearerFormat(JwtTokenProvider.TYPE)
//            )


        // Swagger UI 접속 후, 딱 한 번만 accessToken을 입력해주면 모든 API에 토큰 인증 작업이 적용됩니다.
        return OpenAPI()
//            .addSecurityItem(securityRequirement)
//            .components(components)
    }

    companion object {
        private const val BEARER_TOKEN_PREFIX = "Bearer"
    }
}