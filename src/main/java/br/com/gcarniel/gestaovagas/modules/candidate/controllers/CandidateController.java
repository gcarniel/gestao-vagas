package br.com.gcarniel.gestaovagas.modules.candidate.controllers;

import br.com.gcarniel.gestaovagas.modules.candidate.CandidateEntity;
import br.com.gcarniel.gestaovagas.modules.candidate.usecases.CreateCandidateUseCase;
import br.com.gcarniel.gestaovagas.modules.candidate.usecases.ProfileCandidateUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/candidate")
public class CandidateController {


    final
    CreateCandidateUseCase candidateUseCase;

    final ProfileCandidateUseCase profileCandidateUseCase;

    public CandidateController(CreateCandidateUseCase candidateUseCase, ProfileCandidateUseCase profileCandidateUseCase) {
        this.candidateUseCase = candidateUseCase;
        this.profileCandidateUseCase = profileCandidateUseCase;
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidate) {
        try {
            var result = this.candidateUseCase.execute(candidate);

            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("")
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<Object> profile(HttpServletRequest httpServletRequest) {

        var candidateId = httpServletRequest.getAttribute("candidate_id");
        try {
            var profile = this.profileCandidateUseCase.execute(UUID.fromString(candidateId.toString()));

            return ResponseEntity.status(HttpStatus.CREATED).body(profile);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
