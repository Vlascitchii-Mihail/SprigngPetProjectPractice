package com.pet.learn_spring.spring_shell;

import com.pet.learn_spring.core.photo.PhotoService;
import com.pet.learn_spring.spring_shell.factory_method.CryptographicalAnnotation;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

@ShellComponent
public class PhotoCommands {

    private final PhotoService photoService;
    private Random randomizer;

    public PhotoCommands(
            PhotoService photoService,
            @CryptographicalAnnotation Random random
    ) {
        this.photoService = photoService;
        this.randomizer = random;
    }

    @ShellMethod("Show photo")
    String showPhoto(String name) {
        return photoService.download(name).map(bytes -> {
            try {
                var image = ImageIO.read(new ByteArrayInputStream(bytes));
                return "Width: " + image.getWidth() +
                        ", Height: " + image.getHeight();
            } catch(IOException ex) {
                return "Unable to read the image dimensions";
            }
        }).orElse("Image not found");
    }

    @ShellMethod("Upload photo")
    String uploadPhoto(String fileName) {
        String loadMsg = "Empty";
        try {
        byte[] bytes = Files.readAllBytes(Paths.get(fileName));

        loadMsg = "Uploaded " + photoService.upload(bytes);

        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return loadMsg;
    }
}
