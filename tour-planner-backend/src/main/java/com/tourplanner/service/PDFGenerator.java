package com.tourplanner.service;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.FileOutputStream;
import java.io.OutputStream;

@Service
public class PDFGenerator {

    private String parseThymeleafTemplateHelloWorld() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("thymeleaf/"); // Adjusted to match your directory structure
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        Context context = new Context();
        context.setVariable("to", "ace");

        return templateEngine.process("hello_world", context); // No need for the directory prefix here
    }

    private void generatePdfFromHtml(String html) throws Exception {
        try (OutputStream outputStream = new FileOutputStream("report.pdf")) {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(html);
            renderer.layout();
            renderer.createPDF(outputStream);
        }
    }

    public void startDemo() throws Exception {
        String html = parseThymeleafTemplateHelloWorld();
        generatePdfFromHtml(html);
    }

    public static void main(String[] args) throws Exception {
        new PDFGenerator().startDemo();
    }
}
