
package com.mgeovany.pollsystem.controllers;

import com.mgeovany.pollsystem.dtos.PollCreationRequest;
import com.mgeovany.pollsystem.dtos.PollResponse;
import com.mgeovany.pollsystem.models.Option;
import com.mgeovany.pollsystem.models.Poll;
import com.mgeovany.pollsystem.repositories.OptionRepository;
import com.mgeovany.pollsystem.repositories.PollRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/polls")
public class PollController {

    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private OptionRepository optionRepository;

    @PostMapping
    public ResponseEntity<PollResponse> createPoll(@RequestBody PollCreationRequest pollRequest) {
        Poll poll = new Poll();
        poll.setTitle(pollRequest.getTitle());
        poll.setUniqueUrl(UUID.randomUUID().toString());
    
        List<Option> options = new ArrayList<>();
        for (String optionText : pollRequest.getOptions()) {
            Option option = new Option();
            option.setText(optionText);
            option.setPoll(poll);
            options.add(option);
        }
    
        poll.setOptions(options);
        pollRepository.save(poll);
        optionRepository.saveAll(options);
    
        
        List<String> optionTexts = new ArrayList<>();
        for (Option option : options) {
            optionTexts.add(option.getText());
        }
    
        PollResponse pollResponse = new PollResponse(
            poll.getId(),
            poll.getTitle(),
            poll.getUniqueUrl(),
            optionTexts
        );
    
        return ResponseEntity.status(HttpStatus.CREATED).body(pollResponse);
    }
    
    @GetMapping
    public ResponseEntity<List<PollResponse>> getAllPolls() {
        List<Poll> polls = pollRepository.findAll();

        List<PollResponse> pollResponses = polls.stream()
            .map(poll -> new PollResponse(
                poll.getId(),
                poll.getTitle(),
                poll.getUniqueUrl(),
                poll.getOptions().stream()
                    .map(Option::getText)
                    .collect(Collectors.toList())
            ))
            .collect(Collectors.toList());

        return ResponseEntity.ok(pollResponses);
    }

    @GetMapping("/{pollId}")
    public ResponseEntity<PollResponse> getPollById(@PathVariable UUID pollId) {   
        return pollRepository.findById(pollId)
                .map(poll -> {
                    List<String> optionTexts = poll.getOptions().stream()
                            .map(option -> option.getText())
                            .collect(Collectors.toList());
                    PollResponse pollResponse = new PollResponse(
                            poll.getId(),
                            poll.getTitle(),
                            poll.getUniqueUrl(),
                            optionTexts
                    );
                    return ResponseEntity.ok(pollResponse);
                })
                .orElse(ResponseEntity.notFound().build());
    }
 
}
