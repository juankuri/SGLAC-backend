package org.uv.SGLAC.services;

import org.uv.SGLAC.entities.ParameterRange;
import java.util.List;

public interface ParameterRangeService {
    ParameterRange createParameterRange(ParameterRange parameterRange);
    ParameterRange getParameterRangeById(Long id);
    List<ParameterRange> getAllParameterRanges();
    ParameterRange updateParameterRange(ParameterRange parameterRange);
    void deleteParameterRange(Long id);
}
