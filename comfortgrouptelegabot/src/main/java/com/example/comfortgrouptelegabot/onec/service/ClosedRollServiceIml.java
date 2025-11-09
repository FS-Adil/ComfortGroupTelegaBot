package com.example.comfortgrouptelegabot.onec.service;

import com.example.comfortgrouptelegabot.config.RestClientConfig;
import com.example.comfortgrouptelegabot.onec.dto.ClosedRoll;
import com.example.comfortgrouptelegabot.onec.dto.ClosedRollValue;
import com.example.comfortgrouptelegabot.util.WarehouseGuid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Сервис для взаимодействия с 1С через OData протокол
 * для получения остатков рулонов на складе Закрытые рулоны
 *
 * @author Adilhan
 * @version 1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ClosedRollServiceIml implements ClosedRollService {
    private final RestClientConfig restClientConfig;

    /**
     * Получает все рулоны доступные на складе Закрытые рулоны из 1С
     *
     * @return список рулонов
     * @throws RuntimeException если произошла ошибка при запросе к 1С
     */
    @Override
    public List<ClosedRollValue> findAllClosedRoll() {
        String uri = String.format("/AccumulationRegister_Запасы/Balance(Period=datetime'2025-12-01T00:00:00', " +
                "Condition='cast(СтруктурнаяЕдиница, 'Catalog_СтруктурныеЕдиницы') eq guid'%s'')" +
                "?&$format=json", WarehouseGuid.CLOSED_ROLL_GUID);
        try {
            log.info("Запрос всех рулонов на складе ЗР из 1С");

            ClosedRoll closedRoll = restClientConfig.restClient().get()
                    .uri(uri)
                    .retrieve()
                    .body(ClosedRoll.class);

            List<ClosedRollValue> characteristics = Objects.requireNonNull(closedRoll).getValue();
            log.info("Успешно получено {} рулонов", characteristics.size());

            return characteristics;
        } catch (Exception e) {
            log.error("Ошибка при получении рулонов из 1С", e);
            throw new RuntimeException("Ошибка получения данных из 1С", e);
        }
    }
}
