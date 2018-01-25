package com.kuoni.automation.util;

import org.monte.media.Format;
import org.monte.media.FormatKeys;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import static org.monte.media.FormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;

public class GeneralUtil {

    public static int month;
    public static int day;
    public static int year;
    public static final String DATE_FORMAT_NOW = "yy-MM-dd hh.mm.ss";
    private String strAbsolutePath = new File("").getAbsolutePath();
    private String strScreenshot;
    private ScreenRecorder screenRecorder;

    public static String generateEmailId() {
        String timeStamp = new SimpleDateFormat("HHmmss").format(new Date());
        String emailId = "sap" + timeStamp + "@gmail.com";
        System.out.println("Email id generated is " + emailId);
        return emailId;
    }

    public String targetScreenShotPath() {
        Calendar cal = Calendar.getInstance();
        month = cal.get(Calendar.MONTH) + 1;
        day = cal.get(Calendar.DAY_OF_MONTH);
        year = cal.get(Calendar.YEAR);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_NOW);
        String sDate = simpleDateFormat.format(cal.getTime());
        Random random = new Random();
        int num = random.nextInt(1000);
        String strScreenShotPath = strAbsolutePath + "/target/reports/";
        System.out.println("The screen shot path is " + strScreenShotPath);
        strScreenshot = (String) (strScreenShotPath + sDate + num + ".png");
        System.out.println("Screen shot png " + strScreenshot);
        return strScreenshot;
    }

    public void startRecordingVideo() throws IOException, AWTException {
        File file = new File(System.getProperty("user.dir")+"/target/videos");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        Rectangle captureSize = new Rectangle(0,0, width, height);

        GraphicsConfiguration gc = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration();

        this.screenRecorder = new SpecializedScreenRecorder(gc, captureSize,
                new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                        CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                        DepthKey, 24, FrameRateKey, Rational.valueOf(15),
                        QualityKey, 1.0f,
                        KeyFrameIntervalKey, 15 * 60),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black",
                        FrameRateKey, Rational.valueOf(30)),
                null, file, "MyVideo");
        this.screenRecorder.start();
    }

    public void stopVedioRecording() throws IOException {
        this.screenRecorder.stop();
    }
}
