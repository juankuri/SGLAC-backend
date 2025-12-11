package org.uv.SGLAC.services;

import org.uv.SGLAC.entities.OrderStudy;
import java.util.List;

public interface OrderStudyService {

    OrderStudy saveOrderStudy(OrderStudy orderStudy);

    List<OrderStudy> getAllOrderStudies();

    OrderStudy getOrderStudyById(Long id);

    OrderStudy updateOrderStudy(Long id, OrderStudy orderStudy);

    void deleteOrderStudy(Long id);
}
