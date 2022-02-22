package br.com.opussoftware.plead.config;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.opussoftware.plead"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "PLeaD",
                "Ambiente de consulta jurídica para validação de novos Leads (PF e PJ), visando agilizar processos " +
                        "para equipes de Prevenção à Lavagem de Dinheiro",
                "1.0.0",
                "",
                new Contact("Renê Cardozo", "https://github.com/reneepc", "rene.cardozo@opus-software.com.br"),
                "GPLv3", "https://www.gnu.org/licenses/gpl-3.0.en.html", Collections.emptyList());
    }
}
