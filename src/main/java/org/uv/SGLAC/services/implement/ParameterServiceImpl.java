package org.uv.SGLAC.services.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uv.SGLAC.entities.Parameter;
import org.uv.SGLAC.repositories.ParameterRepository;
import org.uv.SGLAC.services.ParameterService;

import java.util.List;

@Service
public class ParameterServiceImpl implements ParameterService {

    @Autowired
    private ParameterRepository parameterRepository;

    @Override
    public Parameter createParameter(Parameter parameter) {
        if (parameter.getName() == null || parameter.getName().isEmpty()) {
            throw new RuntimeException("El nombre del parámetro no puede estar vacío");
        }
        return parameterRepository.save(parameter);
    }

    @Override
    public Parameter getParameterById(Long id) {
        return parameterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parámetro no encontrado"));
    }

    @Override
    public List<Parameter> getAllParameters() {
        return parameterRepository.findAll();
    }

    @Override
    public Parameter updateParameter(Parameter parameter) {
        if (parameter.getId() == null) {
            throw new RuntimeException("El Id del parámetro es requerido para actualizar");
        }
        return parameterRepository.save(parameter);
    }

    @Override
    public void deleteParameter(Long id) {
        Parameter parameter = getParameterById(id);
        parameterRepository.delete(parameter);
    }
}
