package com.example.comfortgrouptelegabot.onec.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Модель для представления номенклатуры из 1С через OData
 */
@Data
public class Nomenclature {

    @JsonProperty("odata.metadata")
    private String metadata;

    /**
     * Наименование номенклатуры
     */
    @JsonProperty("Description")
    private String description;

    /**
     * Код номенклатуры
     */
    @JsonProperty("Code")
    private String code;

}
