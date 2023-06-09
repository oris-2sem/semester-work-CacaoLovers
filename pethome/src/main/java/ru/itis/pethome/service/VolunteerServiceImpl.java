package ru.itis.pethome.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.pethome.dao.AccountDao;
import ru.itis.pethome.dao.CityDao;
import ru.itis.pethome.dao.DistrictDao;
import ru.itis.pethome.dao.VolunteerDao;
import ru.itis.pethome.dto.response.DistrictInfoResponse;
import ru.itis.pethome.dto.response.DistrictResponse;
import ru.itis.pethome.dto.response.VolunteerResponse;
import ru.itis.pethome.exception.DistrictNotFoundException;
import ru.itis.pethome.exception.VolunteerNotFoundException;
import ru.itis.pethome.mappers.AccountMapper;
import ru.itis.pethome.mappers.DistrictMapper;
import ru.itis.pethome.mappers.MissingMapper;
import ru.itis.pethome.mappers.VolunteerMapper;
import ru.itis.pethome.model.Account;
import ru.itis.pethome.model.City;
import ru.itis.pethome.model.District;
import ru.itis.pethome.model.Volunteer;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class VolunteerServiceImpl implements VolunteerService {

    private final DistrictDao districtDao;
    private final AccountDao accountDao;
    private final AccountMapper accountMapper;
    private final VolunteerDao volunteerDao;

    private final VolunteerMapper volunteerMapper;
    private final DistrictMapper districtMapper;
    private final MissingMapper missingMapper;


    @Override
    @Transactional
    public VolunteerResponse addDistrict(UUID accountId, UUID districtId) {
        Account account = accountDao.findById(accountId).orElseThrow(() -> new UsernameNotFoundException(accountId.toString()));
        District district = districtDao.findById(districtId).orElseThrow(() -> new DistrictNotFoundException(districtId.toString()));

        if (volunteerDao.findVolunteerByAccount(account).isEmpty()){
            volunteerDao.save(Volunteer.builder()
                    .account(account)
                    .rating(0)
                    .summaryFound(0)
                    .build());

        }

        district.getVolunteer().add(account);

        districtDao.save(district);

        return VolunteerResponse.builder()
                .account(accountMapper.toResponse(account))
                .build();
    }

    @Override
    @Transactional
    public VolunteerResponse deleteDistrict(UUID accountId, UUID districtId) {
        Account account = accountDao.findById(accountId).orElseThrow(() -> new UsernameNotFoundException(accountId.toString()));
        District district = districtDao.findById(districtId).orElseThrow(() -> new DistrictNotFoundException(districtId.toString()));

        account.getDistricts().remove(district);
        district.getVolunteer().remove(account);

        accountDao.save(account);
        districtDao.save(district);

        return VolunteerResponse.builder().account(accountMapper.toResponse(account))
                .build();
    }

    @Override
    @Transactional
    public VolunteerResponse getVolunteer(UUID accountId) {
        Account account = accountDao.findById(accountId).orElseThrow(() -> new UsernameNotFoundException(accountId.toString()));


        return volunteerMapper.toResponse(volunteerDao.findVolunteerByAccount(account).orElseThrow(() -> new VolunteerNotFoundException(accountId.toString())));
    }

    @Override
    public List<DistrictResponse> getDistrictByAccountWithoutSelectDistrict(UUID accountId) {
        Account account = accountDao.findById(accountId).orElseThrow(() -> new UsernameNotFoundException(accountId.toString()));

        City city = account.getCity();

        List<District> districtList = districtDao.getDistrictByCity(city);

        if (city != null) districtList = districtDao.getDistrictByCity(city).stream().filter((district -> !account.getDistricts().contains(district))).toList();

        return districtMapper.toListResponse(districtList);
    }

    @Override
    public List<DistrictResponse> getDistrictByAccountWithSelectDistrict(UUID accountId) {
        Account account = accountDao.findById(accountId).orElseThrow(() -> new UsernameNotFoundException(accountId.toString()));
        return districtMapper.toListResponse(account.getDistricts());
    }

    @Override
    @Transactional
    public List<DistrictInfoResponse> getDistrictInfoByAccount(UUID accountId) {
        Account account = accountDao.findById(accountId).orElseThrow(() -> new UsernameNotFoundException(accountId.toString()));
        List<District> districtList = account.getDistricts();


        return districtList.stream()
                .map((district -> DistrictInfoResponse.builder()
                        .district(districtMapper.toResponse(district))
                        .missingAll(district.getMissings().size())
                        .missingToday((int) district.getMissings()
                                .stream()
                                .filter(missing -> {
                                    LocalDateTime localDateTime = LocalDateTime.ofInstant(missing.getCreatedTime(), ZoneId.of("Europe/Moscow"));
                                    return LocalDateTime.now().getDayOfMonth() == localDateTime.getDayOfMonth();
                                }).count())
                        .activeVolunteer(district.getVolunteer().size())
                        .missingList(missingMapper.toListResponse(district.getMissings()))
                        .build()))
                .toList();
    }


    @Override
    @Transactional
    public List<VolunteerResponse> getVolunteerByDistrict(UUID districtId) {
        District district = districtDao.findById(districtId).orElseThrow(() -> new DistrictNotFoundException(districtId.toString()));
        List<Account> accounts = district.getVolunteer();

        return accounts.stream().map(account ->  VolunteerResponse.builder().account(accountMapper.toResponse(account)).build()).toList();
    }
}
