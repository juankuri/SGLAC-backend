package org.uv.SGLAC.services.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uv.SGLAC.entities.Result;
import org.uv.SGLAC.repositories.ResultRepository;
import org.uv.SGLAC.services.ResultService;

import java.util.List;

@Service
public class ResultServiceImpl implements ResultService {

    @Autowired
    private ResultRepository resultRepository;

    @Override
    public Result createResult(Result result) {
        if (result.getOrderStudy() == null || result.getParameter() == null) {
            throw new RuntimeException("Debe asignarse un estudio de orden y un parámetro");
        }
        return resultRepository.save(result);
    }

    @Override
    public Result getResultById(Long id) {
        return resultRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resultado no encontrado"));
    }

    @Override
    public List<Result> getAllResults() {
        return resultRepository.findAll();
    }

    @Override
    public Result updateResult(Result result) {
        if (result.getId() == null) {
            throw new RuntimeException("ID requerido para actualizar");
        }
        return resultRepository.save(result);
    }

    @Override
    public void deleteResult(Long id) {
        Result r = getResultById(id);
        resultRepository.delete(r);
    }
}
