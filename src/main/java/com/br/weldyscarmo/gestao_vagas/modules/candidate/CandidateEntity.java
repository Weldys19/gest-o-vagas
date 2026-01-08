package com.br.weldyscarmo.gestao_vagas.modules.candidate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "candidate")
public class CandidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Schema(example = "Maria do Santos")
    private String name;

    @NotBlank
    @Pattern(regexp = "\\S+", message = "O campo [username] não deve conter espaços")
    @Schema(example = "maria")
    private String username;

    @Email(message = "O campo [email] deve conter um email válido")
    @Schema(example = "maria@gmail.com")
    private String email;

    @Length(min = 10, max = 100, message = "A senha deve conter entre 10 e 100 caracters")
    @Schema(example = "maria12345")
    private String password;

    @Schema(example = "Desenvolvedora Java em busca da primeira oportunidade")
    private String description;
    private String curriculum;

    @CreationTimestamp
    LocalDateTime createdAt;
}
