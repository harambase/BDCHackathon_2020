//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
// de.nixosoft.jlr
//

package com.harambase.hackathon.common.support.document.jlr;

import javax.swing.*;
import java.awt.*;
import java.awt.Desktop.Action;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public final class JLROpener {
    private static String newline = System.getProperty("line.separator");

    private JLROpener() {
    }

    public static void open(File var0, File var1) throws IOException {
        if (!var0.isFile()) {
            throw new FileNotFoundException("The file " + var0.getAbsolutePath() + " does not exist or is not a file!");
        } else if (!var1.isFile()) {
            throw new FileNotFoundException("The file " + var1.getAbsolutePath() + " does not exist or is not a file!");
        } else {
            ProcessBuilder var2 = new ProcessBuilder(var0.getAbsolutePath(), var1.getAbsolutePath());
            var2.start();
        }
    }

    public static void open(File var0) throws IOException {
        if (!var0.isFile()) {
            throw new FileNotFoundException("The file " + var0.getAbsolutePath() + " does not exist or is not a file!");
        } else {
            Desktop var1 = null;
            if (Desktop.isDesktopSupported()) {
                var1 = Desktop.getDesktop();
                if (var1.isSupported(Action.OPEN)) {
                    try {
                        Desktop.getDesktop().open(var0);
                    } catch (IOException var3) {
                        JOptionPane.showMessageDialog(null, "Could not open " + var0.getAbsolutePath() + newline + "The specified file has no associated application or the associated application fails to be launched!", "FileOpenError", 0);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Could not open " + var0.getAbsolutePath() + newline + "Desktop.Action.OPEN is not supported!", "FileOpenError", 0);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Could not open " + var0.getAbsolutePath() + newline + "Desktop is not supported on the current platform!", "FileOpenError", 0);
            }

        }
    }

    public static void print(File var0) throws IOException {
        if (!var0.isFile()) {
            throw new FileNotFoundException("The file " + var0.getAbsolutePath() + " does not exist or is not a file!");
        } else {
            Desktop var1 = null;
            if (Desktop.isDesktopSupported()) {
                var1 = Desktop.getDesktop();
                if (var1.isSupported(Action.PRINT)) {
                    try {
                        Desktop.getDesktop().print(var0);
                    } catch (IOException var3) {
                        JOptionPane.showMessageDialog(null, "Could not print " + var0.getAbsolutePath() + newline + "The specified file has no associated application or the associated application fails to be launched!", "FileOpenError", 0);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Could not print " + var0.getAbsolutePath() + newline + "Desktop.Action.PRINT is not supported!", "FilePrintError", 0);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Could not print " + var0.getAbsolutePath() + newline + "Desktop is not supported on the current platform!", "FilePrintError", 0);
            }

        }
    }
}
