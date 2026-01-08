package com.br.weldyscarmo.gestao_vagas.modules.company.controllers;

import com.br.weldyscarmo.gestao_vagas.modules.candidate.dto.ProfileCandidateDTO;
import com.br.weldyscarmo.gestao_vagas.modules.company.entities.CompanyEntity;
import com.br.weldyscarmo.gestao_vagas.modules.company.useCases.CreateCompanyUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
@Tag(name = "Companhia", description = "Informações da companhia")
public class CompanyController {

    @Autowired
    CreateCompanyUseCase createCompanyUseCase;

    @PostMapping("/")
    @Operation(summary = "Cadastrar companhia",
            description = "Essa função é resposável por cadastrar uma companhia")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = CompanyEntity.class))
            }),
            @ApiResponse(responseCode = "400", description = "Usuário já existe")
    })
    public ResponseEntity<Object> create(@Valid @RequestBody CompanyEntity companyEntity){
        try {
            var result = this.createCompanyUseCase.execute(companyEntity);
            return ResponseEntity.ok().body(result);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
