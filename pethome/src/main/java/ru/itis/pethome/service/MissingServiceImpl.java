package ru.itis.pethome.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.pethome.dao.AccountDao;
import ru.itis.pethome.dao.DistrictDao;
import ru.itis.pethome.dao.MissingDao;
import ru.itis.pethome.dto.request.ImageRequest;
import ru.itis.pethome.dto.request.MissingRequest;
import ru.itis.pethome.dto.request.VolunteerRequest;
import ru.itis.pethome.dto.response.MissingResponse;
import ru.itis.pethome.exception.DistrictNotFoundException;
import ru.itis.pethome.exception.MissingNotFoundException;
import ru.itis.pethome.mappers.MissingMapper;
import ru.itis.pethome.model.Account;
import ru.itis.pethome.model.District;
import ru.itis.pethome.model.Missing;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MissingServiceImpl implements MissingService {

    private final MissingDao missingDao;
    private final MissingMapper missingMapper;

    private final NotificationService notificationService;

    private final AccountDao accountDao;
    private final DistrictDao districtDao;

    @Override
    public MissingResponse createMissing(MissingRequest missingRequest) {
        if (missingRequest.getImage() == null || missingRequest.getImage().equals("")){missingRequest.setImage(ImageRequest.builder()
                .path("search.png")
                .build());}

        Missing missing = missingDao.save(missingMapper.toEntity(missingRequest));

        if (missing.getType().name().equals("LOST")) {

            List<Account> volunteers = missing.getDistrict().getVolunteer();
            notificationService.sendMessageAll((String[]) volunteers
                    .stream()
                    .map((Account::getEmail))
                    .toArray(), "В районе "
                    + missing.getDistrict().getName()
                    + " пропала " + missing.getKind()
                    + ". Почта для связи с хозяином " + missing.getOwner().getEmail());
        }

        return missingMapper.toResponse(missing);
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
    public List<MissingResponse> getMissing(Map<String, String> param) {
        if (param.isEmpty()){
            return missingMapper.toListResponse(missingDao.findAll());
        } else {
            return missingMapper.toListResponse(missingDao.getMissingByParameters(param));
        }
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

    @Override
    public void responseByMissing(UUID missingId, UUID accountId) {
        Account account = accountDao.findById(accountId).orElseThrow(() -> new UsernameNotFoundException(accountId.toString()));
        Missing missing = missingDao.findById(missingId).orElseThrow(() -> new MissingNotFoundException(missingId));

        notificationService.sendMessage(missing.getOwner().getEmail(),
                "Пользователь " + account.getUsername() + " откликнулся на ваше оъявление " + missing.getName() + ". Почта " + account.getEmail());
    }


}
