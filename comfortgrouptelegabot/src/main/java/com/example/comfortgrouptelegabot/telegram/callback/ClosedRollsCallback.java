package com.example.comfortgrouptelegabot.telegram.callback;

import com.example.comfortgrouptelegabot.onec.dto.ClosedRollValue;
import com.example.comfortgrouptelegabot.onec.service.ClosedRollService;
import com.example.comfortgrouptelegabot.onec.service.NomenclatureCharacteristicService;
import com.example.comfortgrouptelegabot.onec.service.NomenclatureService;
import com.example.comfortgrouptelegabot.util.Consts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ClosedRollsCallback implements CallbackHandler{
    private final ClosedRollService closedRollService;
    private final NomenclatureService nomenclatureService;
    private final NomenclatureCharacteristicService nomenclatureCharacteristicService;

    @Override
    public SendMessage apply(Update update) {
        String chatId = update.getCallbackQuery().getMessage().getChatId().toString();

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);

        List<ClosedRollValue> closedRollValueList = closedRollService.findAllClosedRoll();

        String text;
        int size = closedRollValueList.size();
        if (size == 0) {
            text = "На складе 'Закрытые рулоны' на данный момент нет рулонов.";
        } else if (size == 1) {
            text = "На складе 'Закрытые рулоны' на данный момент 1 рулон:";
        } else if (size <= 4) {
            text = String.format("На складе 'Закрытые рулоны' на данный момент %s рулона:", size);
        } else {
            text = String.format("На складе 'Закрытые рулоны' на данный момент %s рулонов:", size);
        }

        int num = 0;
        StringBuilder nomenclature = new StringBuilder();

        nomenclature.append("\n\n");

        for (ClosedRollValue closedRollValue: closedRollValueList) {
            nomenclature.append(++num);
            nomenclature.append(". ");
            nomenclature.append(nomenclatureService.findNomenclatureById(
                    closedRollValue.getNomenclatureKey().toString()
            ).getDescription());

            nomenclature.append(" | ");

            nomenclature.append(nomenclatureCharacteristicService.findCharacteristicById(
                    closedRollValue.getCharacteristicKey().toString()
            ).getDescription());

            nomenclature.append("\n");
        }

        sendMessage.setText(
                text + nomenclature
        );

        return sendMessage;
    }
}
