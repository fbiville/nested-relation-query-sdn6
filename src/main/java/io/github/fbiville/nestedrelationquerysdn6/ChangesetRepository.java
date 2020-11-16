package io.github.fbiville.nestedrelationquerysdn6;


import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface ChangesetRepository extends Repository<Changeset, Long> {

    Optional<Changeset> findByIdAndCanceledFalse(Long id);
}
