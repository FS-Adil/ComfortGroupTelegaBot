package com.example.comfortgrouptelegabot.onec.service;

import com.example.comfortgrouptelegabot.config.RestClientConfig;
import com.example.comfortgrouptelegabot.onec.dto.NomenclatureCharacteristic;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Сервис для взаимодействия с 1С через OData протокол
 * Предоставляет методы для получения характеристик номенклатуры
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class NomenclatureCharacteristicServiceImpl implements NomenclatureCharacteristicService {

    private final RestClientConfig restClientConfig;

    /**
     * Получает конкретную характеристику по её идентификатору
     *
     * @param characteristicId UUID характеристики
     * @return характеристика номенклатуры или null если не найдена
     */
    @Override
    public NomenclatureCharacteristic findCharacteristicById(String characteristicId) {
        String url = String.format("/Catalog_ХарактеристикиНоменклатуры(guid'%s')?" +
                "$select=Description,Code&$format=json", characteristicId);

        try {
            return restClientConfig.restClient().get()
                    .uri(url)
                    .retrieve()
                    .body(NomenclatureCharacteristic.class);

        } catch (Exception e) {
            // Логирование ошибки
            log.error(
                    String.format("Ошибка при получении характеристики с ID %s", characteristicId), String.valueOf(e)
            );
            throw new RuntimeException("Ошибка получения данных из 1С", e);
        }
    }
}
