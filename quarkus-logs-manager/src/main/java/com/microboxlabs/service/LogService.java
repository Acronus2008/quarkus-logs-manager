package com.microboxlabs.service;

import java.io.InputStream;


public interface LogService {
    /**
     * Parse the uploaded file and save logs to the database.
     *
     * @param file {@link InputStream} The uploaded log file.
     */
    void parseAndSaveLogs(InputStream file);
}
