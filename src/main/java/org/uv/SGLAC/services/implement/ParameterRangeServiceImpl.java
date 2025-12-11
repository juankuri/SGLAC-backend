package org.uv.SGLAC.services.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uv.SGLAC.entities.ParameterRange;
import org.uv.SGLAC.repositories.ParameterRangeRepository;
import org.uv.SGLAC.services.ParameterRangeService;

import java.util.List;

@Service
public class ParameterRangeServiceImpl implements ParameterRangeService {

    @Autowired
    private ParameterRangeRepository parameterRangeRepository;

    @Override
    public ParameterRange createParameterRange(ParameterRange parameterRange) {
        if (parameterRange.getMinValue() > parameterRange.getMaxValue()) {
            throw new RuntimeException("El valor mínimo no puede ser mayor que el máximo");
        }
        if (parameterRange.getParameter() == null) {
            throw new RuntimeException("Debe asignarse un parámetro");
        }
        return parameterRangeRepository.save(parameterRange);
    }

    @Override
    public ParameterRange getParameterRangeById(Long id) {
        return parameterRangeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rango de parámetro no encontrado"));
    }

    @Override
    public List<ParameterRange> getAllParameterRanges() {
        return parameterRangeRepository.findAll();
    }

    @Override
    public ParameterRange updateParameterRange(ParameterRange parameterRange) {
        if (parameterRange.getId() == null) {
            throw new RuntimeException("Id requerido para actualizar");
        }
        return parameterRangeRepository.save(parameterRange);
    }

    @Override
    public void deleteParameterRange(Long id) {
        ParameterRange pr = getParameterRangeById(id);
        parameterRangeRepository.delete(pr);
    }
}
