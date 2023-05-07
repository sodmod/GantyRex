package com.Ganty.GantyRex.guarantors.repository;

import com.Ganty.GantyRex.guarantors.Guarantors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuarantorsRepository extends JpaRepository<Guarantors, Long> {
}
