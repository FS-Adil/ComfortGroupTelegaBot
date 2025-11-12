package com.example.comfortgrouptelegabot.onec.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.UUID;

/**
 * DTO для представления характеристик рулона, хранящийся на складе Закрытые рулоны
 *
 * @author Adilhan
 * @version 1.0
 */
@Data
public class ClosedRollValue {
    @JsonProperty("Организация_Key")
    private UUID organizationKey;

    @JsonProperty("СтруктурнаяЕдиница")
    private UUID structuralUnit;

    @JsonProperty("СтруктурнаяЕдиница_Type")
    private String structuralUnitType;

    @JsonProperty("Номенклатура_Key")
    private UUID nomenclatureKey;

    @JsonProperty("Характеристика_Key")
    private UUID characteristicKey;

    @JsonProperty("Партия_Key")
    private UUID batchKey;

    @JsonProperty("ЗаказПокупателя_Key")
    private UUID customerOrderKey;

    @JsonProperty("СчетУчета_Key")
    private UUID accountKey;

    @JsonProperty("ЗаказНаПроизводство_Key")
    private UUID productionOrderKey;

    @JsonProperty("ДвижениеЧекаВОткрытойСмене")
    private Boolean receiptMovementInOpenShift;

    @JsonProperty("КоличествоBalance")
    private Double quantityBalance;

    @JsonProperty("СуммаBalance")
    private Double amountBalance;

    @JsonProperty("КоличествоИнтBalance")
    private Integer quantityIntBalance;

    @JsonProperty("СуммаИнтBalance")
    private Integer amountIntBalance;

    @JsonProperty("СуммаБезНДСBalance")
    private Double amountWithoutVatBalance;

    @JsonProperty("СуммаБезНДСИнтBalance")
    private Integer amountWithoutVatIntBalance;
}
