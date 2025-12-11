package org.uv.SGLAC.services;

import org.uv.SGLAC.entities.Result;
import java.util.List;

public interface ResultService {
    Result createResult(Result result);
    Result getResultById(Long id);
    List<Result> getAllResults();
    Result updateResult(Result result);
    void deleteResult(Long id);
}
