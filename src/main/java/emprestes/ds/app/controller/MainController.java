package emprestes.ds.app.controller;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class MainController {

    @ShellMethod("Welcome command!")
    CharSequence welcome() {
        return "Welcome!";
    }

    @ShellMethod("Run a data structure")
    CharSequence run() {
        return "Running...";
    }
}
