package org.uv.SGLAC.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.uv.SGLAC.entities.OrderStudy;
import java.util.List;

public interface OrderStudyRepository extends JpaRepository<OrderStudy,Long>{
  void deleteByOrderIdAndStudyId(Long orderId, Long studyId);
  List<OrderStudy> findByOrderId(Long orderId);
}
