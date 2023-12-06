package dev.tiltrikt.todolist.service;

import dev.tiltrikt.todolist.service.console.ConsoleService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommandBootstrap implements CommandLineRunner {

    Scanner scanner = new Scanner(System.in);
    ConsoleService consoleService;

    @Autowired
    public CommandBootstrap(ConsoleService consoleService) {
        this.consoleService = consoleService;
    }

    @Override
    public void run(String... args) throws Exception {
        bootstrap();
    }

    public void bootstrap() {
        consoleService.printUsageInfo();
        while (true) {
            consoleService.printCommandLinePrompt();
            String line = scanner.nextLine();
            switch (line.toLowerCase()) {
                case "exit" -> System.exit(0);
                case "help" -> consoleService.printUsageInfo();
                case "add" -> consoleService.askToAddTask();
                case "active" -> consoleService.printActive();
                case "finish" -> consoleService.askToFinishTask();
                case "finished" -> consoleService.printFinished();
                default -> {
                    System.out.println("Command not found!");
                    consoleService.printUsageInfo();
                }
            }
        }
    }

}