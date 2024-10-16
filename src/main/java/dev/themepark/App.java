package dev.themepark;

import dev.themepark.service.EnterService;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) throws InterruptedException {
        EnterService enterService = new EnterService();
        enterService.run();
    }
}
