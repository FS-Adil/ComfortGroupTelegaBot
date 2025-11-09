package com.example.comfortgrouptelegabot.onec.service;

import com.example.comfortgrouptelegabot.onec.dto.ClosedRollValue;

import java.util.List;

public interface ClosedRollService {
    List<ClosedRollValue> findAllClosedRoll();
}
