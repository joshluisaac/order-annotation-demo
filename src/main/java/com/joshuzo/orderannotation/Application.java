package com.joshuzo.orderannotation;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

interface FileFormatHandler {
  void execute();
}

@Service
@Order(10)
class ASCIIFileService implements FileFormatHandler {

  @Override
  public void execute() {
    System.out.println("Handled ASCII File");
  }
}

@Service
@Order(1)
class BinaryFileService implements FileFormatHandler {

  @Override
  public void execute() {
    System.out.println("Handled Binary File");
  }
}

@Service
@RequiredArgsConstructor
class FileManager {

  private final List<FileFormatHandler> handlers;

  void handle() {
    handlers.forEach(
        handler -> {
          System.out.println(handler.getClass());
          handler.execute();
        });
  }
}

public class Application {

  public static final String BASE_PACKAGE = "com.joshuzo.orderannotation";

  public static void main(String[] args) {
    ApplicationContext context = new AnnotationConfigApplicationContext(BASE_PACKAGE);
    FileManager fileManager = context.getBean(FileManager.class);
    fileManager.handle();
  }
}

// OUTPUT
// class com.joshuzo.orderannotation.BinaryFileService
// Handled Binary File
// class com.joshuzo.orderannotation.ASCIIFileService
// Handled ASCII File
