package org.uv.SGLAC.services;

import org.uv.SGLAC.entities.Parameter;
import java.util.List;

public interface ParameterService {
    Parameter createParameter(Parameter parameter);
    Parameter getParameterById(Long id);
    List<Parameter> getAllParameters();
    Parameter updateParameter(Parameter parameter);
    void deleteParameter(Long id);
}
