package ru.vhsroni.service;

import ru.vhsroni.model.PenaltyEntity;

import java.util.Date;
import java.util.List;

public interface PenaltyService {
    List<PenaltyEntity> findAllByCarId(Long id);

    List<PenaltyEntity> findAllWhereAmountLargerThan(int minValue);

    List<PenaltyEntity> findAllOlderThanDate(Date minData);

    PenaltyEntity addNewPenalty(PenaltyEntity penalty);
}
