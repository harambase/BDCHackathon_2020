//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
// de.nixosoft.jlr
//

package com.harambase.hackathon.common.support.document.jlr;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;

public class JLRConverter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private String errorMessage;
    private String defaultCharsetName;
    private VelocityContext contextData;
    private VelocityEngine velocityEngine;

    public JLRConverter(File file) {
        this(file, "org.apache.velocity.runtime.log.Log4JLogChute");
    }

    public JLRConverter(File file, String var2) {
        this.errorMessage = "No errors occurred!";
        this.defaultCharsetName = "UTF-8"; //Charset.defaultCharset().name();

        Properties p = new Properties();
        p.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, file.getAbsolutePath());
        p.setProperty(Velocity.RUNTIME_LOG_LOGSYSTEM_CLASS, var2);
        p.setProperty(Velocity.ENCODING_DEFAULT, "UTF-8");
        p.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
        p.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");

        this.velocityEngine = new VelocityEngine();
        this.velocityEngine.init(p);

        this.contextData = new VelocityContext();
    }

    public void replace(String key, Object var2) {
        logger.info("Replace: " + key + " to "+ var2);
        this.contextData.put(key, var2);
    }

    public void clear() {
        this.contextData = new VelocityContext();
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public boolean parse(String var1, File var2) throws IOException {
        try {
            return this.parse(var1, var2, this.defaultCharsetName);
        } catch (UnsupportedEncodingException var4) {
            this.errorMessage = var4.toString();
            return false;
        }
    }

    public boolean parse(String var1, File var2, String var3) throws IOException {
        if (var2.getParentFile() != null && !var2.getParentFile().isDirectory() && !var2.getParentFile().mkdirs()) {
            this.errorMessage = "Could not createPerson directory: " + var2.getParentFile().getAbsolutePath();
            return false;
        } else {
            BufferedReader var4 = new BufferedReader(new StringReader(var1));
            BufferedWriter var5 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(var2), var3));
            if (!this.velocityEngine.evaluate(this.contextData, var5, "myTemplate", var4)) {
                this.errorMessage = "Errors occurred and logged to velocity log";
                var5.flush();
                var5.close();
                return false;
            } else {
                var5.flush();
                var5.close();
                return true;
            }
        }
    }

    public boolean parse(File template, File tex) throws IOException {
        try {
            return this.parse(template, tex, this.defaultCharsetName);
        } catch (UnsupportedEncodingException var4) {
            this.errorMessage = var4.toString();
            return false;
        }
    }

    public boolean parse(File template, File tex, String charsetName) throws IOException {
        if (template.getParentFile() != null && !tex.getParentFile().isDirectory() && !tex.getParentFile().mkdirs()) {
            this.errorMessage = "Could not createPerson directory: " + tex.getParentFile().getAbsolutePath();
            return false;
        } else {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tex), charsetName));
            if (!this.velocityEngine.mergeTemplate(template.getName(), charsetName, this.contextData, bufferedWriter)) {
                this.errorMessage = "Errors occurred and logged to velocity log";
                bufferedWriter.flush();
                bufferedWriter.close();
                return false;
            } else {
                bufferedWriter.flush();
                bufferedWriter.close();
                return true;
            }
        }
    }
}
