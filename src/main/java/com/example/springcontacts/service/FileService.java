package com.example.springcontacts.service;

import com.example.springcontacts.contact.ContactStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;

@Service
public class FileService {

    public List<String> readContactsFromFile(String filename) {
        try {
            return Files.readAllLines(Paths.get(filename));
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public void writeContactsToFile(List<String> contacts, String filename) {
        try {
            Files.write(
                    Paths.get(filename),
                    contacts,
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }

    public ContactStatus deleteContactByEmail(String email, String filename) {
        ContactStatus status = ContactStatus.NOT_FOUND;
        try {
            // Создаем временный файл
            Path tempFile = Files.createTempFile("contacts", ".tmp");

            // Читаем все контакты из файла
            List<String> contacts = readContactsFromFile(filename);

            // Проходим по всем контактам
            for (String contact : contacts) {
                // Если контакт не соответствует электронной почте, добавляем его в новый список
                if (!contact.endsWith(email)) {
                    Files.write(
                            tempFile, Collections.singletonList(contact),
                            StandardCharsets.UTF_8,
                            StandardOpenOption.APPEND
                    );
                } else {
                    String messageFormat = MessageFormat.format(
                            "Контакт: {0} с email: {1} успешно удалён!", contact, email
                    );
                    System.out.println(messageFormat);
                    status = ContactStatus.DELETED;
                }
            }
            // Удаляем исходный файл
            Files.delete(Paths.get(filename));

            // Переименовываем временный файл в исходный
            Files.move(tempFile, Paths.get(filename));

        } catch (IOException e) {
            System.out.println("Ошибка при удалении записи из файла: " + e.getMessage());
        }
        return status;
    }
}
