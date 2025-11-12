package com.example.comfortgrouptelegabot.onec.service;

import com.example.comfortgrouptelegabot.config.RestClientConfig;
import com.example.comfortgrouptelegabot.onec.dto.Nomenclature;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Сервис для взаимодействия с 1С через OData протокол
 * для получения данных о номенклатуре
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class NomenclatureServiceImpl implements NomenclatureService{

    private final RestClientConfig restClientConfig;

    /**
     * Получение номенклатуры по идентификатору
     *
     * @param refKey уникальный идентификатор номенклатуры
     * @return объект номенклатуры или null если не найден
     */
    @Override
    public Nomenclature findNomenclatureById(String refKey) {
        String url = String.format("/Catalog_Номенклатура(guid'%s')?" +
                "$select=Description,Code&$format=json", refKey);

        try {
            return restClientConfig.restClient().get()
                    .uri(url)
                    .retrieve()
                    .body(Nomenclature.class);

        } catch (Exception e) {
            // Логирование ошибки
            log.error(
                    String.format("Ошибка при получении номенклатуры с ID %s", refKey), String.valueOf(e)
            );
            throw new RuntimeException("Ошибка получения данных из 1С", e);
        }
    }
}
