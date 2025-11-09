package com.example.comfortgrouptelegabot.onec.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * DTO для ответа от 1С OData содержащего список номенклатуры на складе Закрытые рулоны
 *
 * @author Adilhan
 * @version 1.0
 */
@Data
public class ClosedRoll {

    @JsonProperty("value")
    private List<ClosedRollValue> value;

    @JsonProperty("odata.metadata")
    private String metadata;
}
