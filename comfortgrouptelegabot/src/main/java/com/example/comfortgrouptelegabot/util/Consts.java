package com.example.comfortgrouptelegabot.util;

public class Consts {
    public static final String START_MESSAGE = """
                %s добро пожаловать в бот компании "Группа Комфорт"!
                
                Для удобства работы с данным ботом используйте клавиатуру под окном ввода сообщения,
                
                так же можете использовать следующие команды:
                /start - запуск программы
                /help - получение справки

                В разделе 📋 Меню, Вы сможете узнать следующую информацию о производственных предприятиях:
                 - Остатки закрытых рулонов
                 - Остальные разделы в стадии разработки ....
                """;
//            " Приветствую! Это ComfortGroupTelegaBot - бот, который интегрирован с 1С.\n";

    public static final String CANT_UNDERSTAND = "Извините, я не понял, что вы имеете ввиду";

    public static final String UNKNOWN_COMMAND = "Извините, я не знаю такой команды";

    public static final String RESTART = "Можете написать /start, чтобы начать заново. Спасибо за использование бота.";

    public static final String MENU_MESSAGE = """
            Из списка задач ниже, выберете интересующую:
            """;
}
