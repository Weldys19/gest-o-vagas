package com.br.weldyscarmo.gestao_vagas.modules.candidate.entity;

import com.br.weldyscarmo.gestao_vagas.modules.candidate.CandidateEntity;
import com.br.weldyscarmo.gestao_vagas.modules.company.entities.JobEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ApplyJobEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "candidate_id", updatable = false, insertable = false)
    private CandidateEntity candidateEntity;

    @ManyToOne
    @JoinColumn(name = "job_id", updatable = false, insertable = false)
    private JobEntity jobEntity;

    @Column(name = "candidate_id")
    private UUID candidateId;

    @Column(name = "job_id")
    private UUID jobId;

    @CreationTimestamp
    LocalDateTime createdAt;
}
