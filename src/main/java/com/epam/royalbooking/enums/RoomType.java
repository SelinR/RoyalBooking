package com.epam.royalbooking.enums;

import java.util.Locale;

public enum RoomType {
    BASIC, FAMILY, LUXURY, PENTHOUSE;

    public String getLocalizedType(Locale locale) {
        switch (locale.toString()) {
            case "ru":
                switch (this) {
                    case BASIC:
                        return "Обычная";
                    case FAMILY:
                        return "Семейная";
                    case LUXURY:
                        return "Роскошная";
                    case PENTHOUSE:
                        return "Пентхаус";
                    default:
                        return "Тип комнаты не найден";
                }
            case "en":
                return this.toString();
            default: return "Only RU and EN locales are supported";
        }
    }
}
