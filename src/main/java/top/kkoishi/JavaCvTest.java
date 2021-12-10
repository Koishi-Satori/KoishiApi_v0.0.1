package top.kkoishi;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;

import java.io.File;
import java.util.Scanner;

public class JavaCvTest {
    public void get (String srcPath) {
        File file = new File(srcPath);
        FFmpegFrameGrabber grabber1 = new FFmpegFrameGrabber(srcPath);
        Frame frame;
        FFmpegFrameRecorder recorder;
        try {
            grabber1.start();
            String toPath = file.getParentFile().getAbsolutePath()
                    + File.separatorChar
                    + "[audio]"
                    + file.getName()
                    + ".mp3";
            System.out.println(toPath);
            recorder = new FFmpegFrameRecorder(toPath, grabber1.getAudioChannels());
            recorder.setFormat("mp3");
            recorder.setSampleRate(grabber1.getSampleRate());
            recorder.setTimestamp(grabber1.getTimestamp());
            recorder.setAudioQuality(0);
            recorder.start();
            int index = 0;
            while (true) {
                frame = grabber1.grab();
                if (frame == null) {
                    System.out.println("Finished!");
                    break;
                }
                if (frame.samples != null) {
                    recorder.recordSamples(frame.sampleRate, frame.audioChannels, frame.samples);
                }
                System.out.println("当前帧值:" + index++);
            }
            recorder.stop();
            recorder.release();
            grabber1.stop();
        } catch (FFmpegFrameGrabber.Exception | FFmpegFrameRecorder.Exception e) {
            e.printStackTrace();
        }
    }
}

class Test1 {
    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        String srcPath = scanner.nextLine();
        new JavaCvTest().get(srcPath);
        scanner.close();
    }
}
