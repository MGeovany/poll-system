package com.mgeovany.pollsystem.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mgeovany.pollsystem.models.Option;

public interface OptionRepository  extends JpaRepository<Option, UUID> {  

  List<Option> findByPollId(UUID pollId);
}
