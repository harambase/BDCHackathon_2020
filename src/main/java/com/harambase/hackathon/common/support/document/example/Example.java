package com.harambase.hackathon.common.support.document.example;

import com.harambase.hackathon.common.support.document.jlr.JLRConverter;
import com.harambase.hackathon.common.support.document.jlr.JLRGenerator;
import com.harambase.hackathon.common.support.document.jlr.JLROpener;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Example {

    public static void main(String[] args) {
        File projectDirectory = new File("");
        File workingDirectory = new File(projectDirectory.getAbsolutePath() + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "tex");

        File template = new File(workingDirectory.getAbsolutePath() + File.separator + "invoiceTemplate.tex");

        File tempDir = new File(File.separator + "temp");

        if (!tempDir.isDirectory()) {
            tempDir.mkdir();
        }

        File invoice1 = new File(tempDir.getAbsolutePath() + File.separator + "invoice1.tex");
        File invoice2 = new File(tempDir.getAbsolutePath() + File.separator + "invoice2.tex");


        try {
            JLRConverter converter = new JLRConverter(workingDirectory);

            converter.replace("Number", "1");
            converter.replace("CustomerName", "Ivan Pfeiffer");
            converter.replace("CustomerStreet", "Schwarzer Weg 4");
            converter.replace("CustomerZip", "13505 Berlin");

            ArrayList<ArrayList<String>> services = new ArrayList<>();

            ArrayList<String> subservice1 = new ArrayList<>();
            ArrayList<String> subservice2 = new ArrayList<>();
            ArrayList<String> subservice3 = new ArrayList<>();

            subservice1.add("Software");
            subservice1.add("50");
            subservice2.add("Hardware1");
            subservice2.add("500");
            subservice3.add("Hardware2");
            subservice3.add("850");

            services.add(subservice1);
            services.add(subservice2);
            services.add(subservice3);

            converter.replace("services", services);

            converter.parse(template, invoice1);

            converter.replace("Number", "2");
            converter.replace("CustomerName", "Mike Mueller");
            converter.replace("CustomerStreet", "Prenzlauer Berg 12");
            converter.replace("CustomerZip", "10405 Berlin");

            services = new ArrayList<>();

            subservice1 = new ArrayList<>();
            subservice2 = new ArrayList<>();
            subservice3 = new ArrayList<>();

            subservice1.add("Software");
            subservice1.add("150");
            subservice2.add("Hardware");
            subservice2.add("500");
            subservice3.add("Test");
            subservice3.add("350");

            services.add(subservice1);
            services.add(subservice2);
            services.add(subservice3);

            converter.replace("services", services);
            converter.parse(template, invoice2);

            File desktop = new File(System.getProperty("user.home") + File.separator + "Desktop");

            JLRGenerator pdfGen = new JLRGenerator();

            pdfGen.generate(invoice1, desktop, tempDir);
            File pdf1 = pdfGen.getPDF();

            pdfGen.generate(invoice2, desktop, tempDir);
            File pdf2 = pdfGen.getPDF();

            JLROpener.open(pdf1);
            JLROpener.open(pdf2);

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
