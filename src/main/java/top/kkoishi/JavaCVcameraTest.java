package top.kkoishi;

import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameGrabber;

import javax.swing.*;

public class JavaCVcameraTest {
    public void camera () {
        OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
        try {
            grabber.start();
            CanvasFrame frame = new CanvasFrame("display");
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            while (frame.isDisplayable()) {
                frame.showImage(grabber.grab());
            }
            grabber.close();
        } catch (FrameGrabber.Exception e) {
            e.printStackTrace();
        }
    }
}

class Test {
    public static void main (String[] args) {
        new JavaCVcameraTest().camera();
    }
}