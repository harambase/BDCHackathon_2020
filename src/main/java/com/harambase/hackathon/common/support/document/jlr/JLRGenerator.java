//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
// de.nixosoft.jlr
//

package com.harambase.hackathon.common.support.document.jlr;

import java.io.*;

public class JLRGenerator {
    private static String newline = System.getProperty("line.separator");
    private String errorMessage = "No errors occurred!";
    private String outputStream = "";
    private File inputFile = null;
    private File pdfFile = null;
    private File auxFile = null;
    private File logFile = null;
    private boolean deleteTempTex = false;
    private boolean deleteAux = false;
    private boolean deleteLog = false;

    public JLRGenerator() {
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public String getOutputStream() {
        return this.outputStream;
    }

    public File getPDF() {
        return this.pdfFile;
    }

    public void deleteTempFiles(boolean var1, boolean var2, boolean var3) {
        this.deleteTempTex = var1;
        this.deleteAux = var2;
        this.deleteLog = var3;
    }

    public boolean generate(File var1, File var2, File var3) throws IOException {
        return this.generate(new File("pdflatex"), 3, var1, var2, var3);
    }

    public boolean generate(int var1, File var2, File var3, File var4) throws IOException {
        return this.generate(new File("pdflatex"), var1, var2, var3, var4);
    }

    public boolean generate(File var1, File var2, File var3, File var4) throws IOException {
        return this.generate(var1, 3, var2, var3, var4);
    }

    public boolean generate(File var1, int var2, File var3, File var4, File var5) throws IOException {
        this.inputFile = var3;
        if (!this.checkingInputs(var2, var3, var4, var5)) {
            return false;
        } else {
            String var6 = var1.getPath();
            String var7 = "--interaction=nonstopmode";
            String var8 = "--output-directory=" + var4.getAbsolutePath();
            String var9 = "--aux-directory=" + var3.getParent();
            String var10 = var3.getAbsolutePath();
            ProcessBuilder var11 = new ProcessBuilder(var6, var7, var8, var9, var10);
            var11.redirectErrorStream(true);
            var11.directory(var5);

            for (int var12 = 1; var12 <= var2; ++var12) {
                Process var13 = var11.start();
                InputStreamReader var14 = new InputStreamReader(var13.getInputStream());
                BufferedReader var15 = new BufferedReader(var14);
                StringBuilder var16 = new StringBuilder();
                String var17 = null;

                try {
                    while ((var17 = var15.readLine()) != null) {
                        var16.append(var17 + newline);
                    }
                } finally {
                    var15.close();
                }

                this.outputStream = var16.toString();

                try {
                    int var18 = var13.waitFor();
                    if (!this.determiningFiles(var3, var4)) {
                        return false;
                    }

                    if (var18 != 0) {
                        this.errorMessage = "Errors occurred while executing pdfLaTeX! Exit value of the process: " + var18;
                        return false;
                    }

                    this.errorMessage = "No errors occurred!";
                } catch (InterruptedException var22) {
                    this.errorMessage = "The process pdfLaTeX was interrupted and an exception occurred!" + newline + var22.toString();
                    return false;
                }
            }

            this.cleanUp();
            return true;
        }
    }

    private boolean checkingInputs(int var1, File var2, File var3, File var4) throws IOException {
        if (var1 <= 0) {
            this.errorMessage = "The value for \"cycles\" has to be greater than 0!";
            return false;
        } else if (!var2.isFile()) {
            throw new FileNotFoundException("The file " + var2.getAbsolutePath() + " does not exist or is not a file!");
        } else if (var3 != null && !var3.isDirectory() && !var3.mkdirs()) {
            this.errorMessage = "Could not createPerson directory: " + var3.getAbsolutePath();
            return false;
        } else if (!var4.isDirectory()) {
            throw new IOException("The directory " + var4.getAbsolutePath() + " does not exist or is not a directory!");
        } else {
            return true;
        }
    }

    private boolean determiningFiles(File var1, File var2) {
        File var3 = null;
        int var4 = var1.getName().lastIndexOf(".");
        if (var4 != -1) {
            this.auxFile = new File(var1.getParent() + File.separator + var1.getName().substring(0, var4) + ".aux");
            this.logFile = new File(var1.getParent() + File.separator + var1.getName().substring(0, var4) + ".log");
            var3 = new File(var2.getAbsolutePath() + File.separator + var1.getName().substring(0, var4) + ".pdf");
        } else {
            this.auxFile = new File(var1.getParent() + File.separator + var1.getName() + ".aux");
            this.logFile = new File(var1.getParent() + File.separator + var1.getName() + ".log");
            var3 = new File(var2.getAbsolutePath() + File.separator + var1.getName() + ".pdf");
        }

        if (var3.isFile()) {
            this.pdfFile = var3;
            return true;
        } else {
            this.errorMessage = "The pdf file could not be created or does not exist!";
            return false;
        }
    }

    private void cleanUp() {
        if (this.deleteAux) {
            this.deleteFile(this.auxFile);
        }

        if (this.deleteLog) {
            this.deleteFile(this.logFile);
        }

        if (this.deleteTempTex) {
            this.deleteFile(this.inputFile);
        }

    }

    private void deleteFile(File var1) {
        if (var1.isFile() && !var1.delete()) {
            this.errorMessage = "Warning: Could not delete " + var1.getAbsolutePath() + " !";
        }

    }
}
