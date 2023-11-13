package com.example.springcontacts.service;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ValidatorService {

    public boolean validateFullName(String fullName) {
        // Разрешаем тире в фамилии
        String lastNamePattern = "[а-яА-Я]+(-[а-яА-Я]+)?";

        // Имя и отчество разрешаем через пробел
        String firstNamePattern = "[а-яА-Я]+([а-яА-Я]+[\\s-][а-яА-Я]+)?";

        // Склеиваем в общее выражение
        String patternString = lastNamePattern + "\\s" + firstNamePattern + "\\s" + firstNamePattern;

        // Создаем регулярное выражение, которое соответствует всем допустимым символам в имени
        Pattern pattern = Pattern.compile(patternString);

        // Проверяем, содержит ли имя недопустимые символы
        Matcher matcher = pattern.matcher(fullName);
        return matcher.matches();
    }

    public boolean validatePhoneNumber(String phoneNumber) {
        if (phoneNumber.length() < 12) {
            return false;
        }
        // Создаем регулярное выражение, которое соответствует формату номера телефона
        Pattern pattern = Pattern.compile("^\\+[0-9]{1,3}[0-9]{1,15}$");

        // Проверяем, соответствует ли номер телефона формату
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    public boolean validateEmail(String email) {
        // Создаем регулярное выражение, которое соответствует формату адреса электронной почты
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");

        // Проверяем, соответствует ли адрес электронной почты регулярному выражению
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
