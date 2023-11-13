package com.example.springcontacts.service;

import com.example.springcontacts.contact.Contact;
import com.example.springcontacts.contact.ContactStatus;
import com.example.springcontacts.runner.InitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Scanner;

@Service
public class ContactService {
    private static final Logger LOG = LoggerFactory.getLogger(ContactService.class);
    private final FileService fileService;
    private final ValidatorService validatorService;

    public ContactService(FileService fileService, ValidatorService validatorService) {
        this.fileService = fileService;
        this.validatorService = validatorService;
    }

    public void handleUserInput() {
        Scanner scanner = new Scanner(System.in);

        final Contact contact = new Contact();

        System.out.println("Добавление нового контакта");
        System.out.println("Введите ФИО полностью: ");
        String fullName = scanner.nextLine();
        if (validatorService.validateFullName(fullName)) {
            contact.setFullName(fullName);
        } else {
            System.out.println("В стороке 'ФИО' должны содержаться только буквы русского и английского алфавитов");
            return;
        }

        System.out.println("Введите номер телефона в формате '+''код страны''основной номер': ");
        String phoneNumber = scanner.nextLine();
        if (validatorService.validatePhoneNumber(phoneNumber) ) {
            contact.setPhoneNumber(phoneNumber);
        } else {
            System.out.println("Номер телефона не соответствует нужному формату");
            return;
        }

        System.out.println("Введите адрес электронной почты: ");
        String email = scanner.nextLine();
        if (validatorService.validateEmail(email) ) {
            if (isEmailUnique(email)) {
                contact.setEmail(email);
            } else {
                System.out.println("Email: " + email + " уже существует, нужен другой email для добавления контакта");
                return;
            }
        } else {
            System.out.println("Введён некорректный e-mail");
            return;
        }

        String messageFormat = MessageFormat.format(
                "\nДобавлен новый контакт:\nФИО: {0};\nНомер телефона: {1};\nАдрес электронной почты: {2}\n",
                contact.getFullName(), contact.getPhoneNumber(), contact.getEmail()
        );
        LOG.info(messageFormat);
        fileService.writeContactsToFile(List.of(contact.toString()), InitRunner.FILE_NAME);
    }

    private boolean isEmailUnique(String email) {
        List<String> contacts = fileService.readContactsFromFile(InitRunner.FILE_NAME);
        for (String contact : contacts) {
            String[] parts = contact.split(";");
            if (parts.length >= 3 && parts[2].equals(email)) {
                return false;
            }
        }
        return true;
    }

    public void printContactsFromFile(String filename) {
        List<String> lines = fileService.readContactsFromFile(filename);
        for (String line : lines) {
            String[] parts = line.split(";");
            if (parts.length >= 3) {
                System.out.println(parts[0] + " | " + parts[1] + " | " + parts[2]);
            }
        }
    }

    public void printContactByEmail(String filename, String email) {
        List<String> lines = fileService.readContactsFromFile(filename);
        boolean found = false;
        for (String line : lines) {
            String[] parts = line.split(";");
            if (parts.length >= 3 && parts[2].equalsIgnoreCase(email)) {
                System.out.println(parts[0] + " | " + parts[1] + " | " + parts[2]);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Контакт с адресом электронной почты: " + email + " не найден !");
        }
    }

    public void deleteContactByEmail(String email, String filename) {
        ContactStatus contactStatus = fileService.deleteContactByEmail(email, filename);
        if (contactStatus.equals(ContactStatus.NOT_FOUND)) {
            System.out.println("Контакт с адресом электронной почты: " + email + " не найден !");
        }
    }
}

