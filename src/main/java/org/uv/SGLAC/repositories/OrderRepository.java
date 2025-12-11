package org.uv.SGLAC.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.uv.SGLAC.entities.Order;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long>{
  List<Order> findByPatientId(Long patientId);
}