package ru.vhsroni.service;

import ru.vhsroni.model.PenaltyEntity;

import java.sql.Date;
import java.util.List;

public interface PenaltyService {
    List<PenaltyEntity> findAllPenaltiesByCarId(Long id);

    List<PenaltyEntity> findAllPenaltiesWhereAmountLargerThan(Integer minValue);

    List<PenaltyEntity> findAllPenaltiesOlderThanDate(Date minData);

    PenaltyEntity addNewPenalty(PenaltyEntity penalty);
}
