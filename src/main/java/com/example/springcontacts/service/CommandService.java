package com.example.springcontacts.service;

import org.springframework.stereotype.Component;

@Component
public class CommandService {
    private final ContactService contactService;

    public CommandService(ContactService contactService) {
        this.contactService = contactService;
    }

    public void handleCommand(String command, String fileName) {
        String[] parts = command.split(" ");

        switch (parts[0]) {
            case "exit":
                    System.out.println("Завершение программы");
                break;
            case "--add-contact":
              contactService.handleUserInput();
              break;

            case "--get-all-contacts":
              contactService.printContactsFromFile(fileName);
              break;

            case "--get-contact":
              try {
                String email = parts[1];
                contactService.printContactByEmail(fileName, email);
              } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Для получения записи укажите e-mail ! ");
              }
              break;

            case "--del-contact":
              try {
                String email = parts[1];
                contactService.deleteContactByEmail(email, fileName);
              } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Для удаления записи укажите e-mail ! ");
              }
              break;

            default:
              System.out.println("Неверная команда!");
              break;
        }
    }
}
