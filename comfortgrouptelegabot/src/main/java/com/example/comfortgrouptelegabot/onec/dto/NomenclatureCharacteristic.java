package com.example.comfortgrouptelegabot.onec.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Модель для представления характеристики номенклатуры
 */
@Data
public class NomenclatureCharacteristic {

    @JsonProperty("odata.metadata")
    private String metadata;

    /**
     * Наименование характеристики
     */
    @JsonProperty("Description")
    private String description;

    /**
     * Код характеристики
     */
    @JsonProperty("Code")
    private String code;
}
