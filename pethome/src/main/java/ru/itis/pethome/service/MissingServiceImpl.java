package ru.itis.pethome.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.pethome.dao.AccountDao;
import ru.itis.pethome.dao.MissingDao;
import ru.itis.pethome.dto.request.MissingRequest;
import ru.itis.pethome.dto.response.MissingResponse;
import ru.itis.pethome.exception.MissingNotFoundException;
import ru.itis.pethome.mappers.MissingMapper;
import ru.itis.pethome.model.Account;
import ru.itis.pethome.model.Missing;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MissingServiceImpl implements MissingService {

    private final MissingDao missingDao;
    private final MissingMapper missingMapper;

    private final AccountDao accountDao;

    @Override
    public MissingResponse createMissing(MissingRequest missingRequest) {
        return missingMapper.toResponse(missingDao.save(missingMapper.toEntity(missingRequest)));
    }

    @Override
    public MissingResponse updateMissing(MissingRequest missingRequest) {
        Missing missing = missingDao.findById(missingRequest.getId()).orElseThrow(() -> new MissingNotFoundException(missingRequest.getId()));
        Missing missingUpdate = missingMapper.toEntity(missingRequest);
        missingUpdate.setId(missing.getId());
        return missingMapper.toResponse(missingDao.save(missingUpdate));
    }

    @Override
    public void deleteMissing(UUID uuid) {
        Missing missing = missingDao.findById(uuid).orElseThrow(() -> new MissingNotFoundException(uuid));
        missingDao.delete(missing);
    }

    @Override
    public MissingResponse getMissingById(UUID uuid) {
        return missingMapper.toResponse(missingDao.findById(uuid).orElseThrow(() -> new MissingNotFoundException(uuid)));
    }

    @Override
    public List<MissingResponse> getMissingList() {
        return missingMapper.toListResponse(missingDao.findAll());
    }

    @Override
    public List<MissingResponse> getMissingPageableList(int page) {
        return null;
    }

    @Override
    public List<MissingResponse> getMissingListByOwner(UUID id) {
        Account account = accountDao.findById(id).orElseThrow();
        return missingMapper.toListResponse(missingDao.findMissingByOwner(account));
    }
}
