//package com.licenta.backendlicenta.utils;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.util.UUID;
//
//public class FileUtils {
//    private static final String PROFILE_PICTURE_DIR = "com/licenta/backendlicenta/utils/defaultPictures/defaultProfilePicture.png";
//    public static byte[] saveProfilePicture(byte[] profilePictureBytes, String originalFilename) throws IOException {
//        // Ensure the directory exists
//        File directory = new File(PROFILE_PICTURE_DIR);
//        if (!directory.exists()) {
//            directory.mkdirs();
//        }
//
//        // Generate a unique file name
//        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("/"));
//        String uniqueFilename = UUID.randomUUID().toString() + fileExtension;
//
//        // Create the file
//        File file = new File(PROFILE_PICTURE_DIR + uniqueFilename);
//        try (FileOutputStream fos = new FileOutputStream(file)) {
//            fos.write(profilePictureBytes);
//        }
//
//        return Files.readAllBytes(file.toPath());
//    }
//}
