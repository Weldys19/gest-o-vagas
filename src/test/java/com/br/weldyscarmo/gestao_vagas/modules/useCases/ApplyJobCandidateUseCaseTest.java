package com.br.weldyscarmo.gestao_vagas.modules.useCases;

import com.br.weldyscarmo.gestao_vagas.exceptions.JobNotFoundException;
import com.br.weldyscarmo.gestao_vagas.exceptions.UserNotFoundException;
import com.br.weldyscarmo.gestao_vagas.modules.candidate.CandidateEntity;
import com.br.weldyscarmo.gestao_vagas.modules.candidate.CandidateRepository;
import com.br.weldyscarmo.gestao_vagas.modules.candidate.entity.ApplyJobEntity;
import com.br.weldyscarmo.gestao_vagas.modules.candidate.repository.ApplyJobRepository;
import com.br.weldyscarmo.gestao_vagas.modules.candidate.useCases.ApplyJobCandidateUseCase;
import com.br.weldyscarmo.gestao_vagas.modules.company.entities.JobEntity;
import com.br.weldyscarmo.gestao_vagas.modules.company.repositories.JobRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateUseCaseTest {

    @InjectMocks
    private ApplyJobCandidateUseCase applyJobCandidateUseCase;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private JobRepository jobRepository;

    @Mock
    private ApplyJobRepository applyJobRepository;

    @Test
    @DisplayName("Should not be able to apply job with candidate not found")
    public void shouldNotBeAbleToApplyJobWithCandidateNotFound(){
        try {
            this.applyJobCandidateUseCase.execute(null, null);
        }catch(Exception e){
            assertThat(e).isInstanceOf(UserNotFoundException.class);
        }
    }
    @Test
    @DisplayName("Should not be able to apply job with job not found")
    public void shouldNotBeAbleToApplyJobWithJobNotFound() {

        var idCandidate = UUID.randomUUID();
        var candidate = new CandidateEntity();
        candidate.setId(idCandidate);

        when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(candidate));

        try {
            this.applyJobCandidateUseCase.execute(idCandidate, null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(JobNotFoundException.class);
        }
    }

    @Test
    @DisplayName("Should be able to create a new apply job")
    public void shouldBeAbleToCreateANewApplyJob(){
        var candidateId = UUID.randomUUID();
        var jobId = UUID.randomUUID();

        var applyJob = ApplyJobEntity.builder()
                .jobId(jobId)
                .candidateId(candidateId)
                .build();

        var applyJobId = ApplyJobEntity.builder()
                        .id(UUID.randomUUID())
                        .build();

        when(candidateRepository.findById(candidateId)).thenReturn(Optional.of(new CandidateEntity()));
        when(jobRepository.findById(jobId)).thenReturn(Optional.of(new JobEntity()));

        when(applyJobRepository.save(applyJob)).thenReturn(applyJobId);

        var result = this.applyJobCandidateUseCase.execute(candidateId, jobId);

        assertThat(result).hasFieldOrProperty("id");
        assertNotNull(result.getId());
    }
}
