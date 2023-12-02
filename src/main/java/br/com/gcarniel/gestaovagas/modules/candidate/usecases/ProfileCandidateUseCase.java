package br.com.gcarniel.gestaovagas.modules.candidate.usecases;

import br.com.gcarniel.gestaovagas.modules.candidate.CandidateRepository;
import br.com.gcarniel.gestaovagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public ProfileCandidateResponseDTO execute(UUID candidateId) {
        var candidate = candidateRepository.findById(candidateId).orElseThrow(() -> {
            throw new UsernameNotFoundException("Candidato não encontrado");
        });


        return ProfileCandidateResponseDTO.builder()
                .username(candidate.getUsername())
                .name(candidate.getName())
                .description(candidate.getDescription())
                .email(candidate.getEmail())
                .id(candidate.getId())
                .build();
    }
}
