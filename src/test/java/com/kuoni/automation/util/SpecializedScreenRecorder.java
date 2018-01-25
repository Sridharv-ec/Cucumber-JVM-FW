package com.kuoni.automation.util;

import org.monte.media.Format;
import org.monte.screenrecorder.ScreenRecorder;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class SpecializedScreenRecorder extends ScreenRecorder {
    private String name;

    public SpecializedScreenRecorder(GraphicsConfiguration cfg,
                                     Rectangle captureArea, Format fileFormat, Format screenFormat,
                                     Format mouseFormat, Format audioFormat, File movieFolder,
                                     String name) throws IOException, AWTException {
        super(cfg, captureArea, fileFormat, screenFormat, mouseFormat,
                audioFormat, movieFolder);
        this.name = name;
    }
}
