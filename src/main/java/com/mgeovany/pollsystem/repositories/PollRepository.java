package com.mgeovany.pollsystem.repositories;

import com.mgeovany.pollsystem.models.Poll;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PollRepository extends JpaRepository<Poll, UUID> {
  Optional<Poll> findByUniqueUrl(String uniqueUrl);
}
