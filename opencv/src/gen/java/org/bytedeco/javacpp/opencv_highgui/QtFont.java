// Targeted by JavaCPP version 1.5-SNAPSHOT: DO NOT EDIT THIS FILE

package org.bytedeco.javacpp.opencv_highgui;

import java.nio.*;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

import org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_core.opencv_core.*;
import org.bytedeco.javacpp.opencv_imgproc.*;
import static org.bytedeco.javacpp.opencv_imgproc.opencv_imgproc.*;
import org.bytedeco.javacpp.opencv_imgcodecs.*;
import static org.bytedeco.javacpp.opencv_imgcodecs.opencv_imgcodecs.*;
import org.bytedeco.javacpp.opencv_videoio.*;
import static org.bytedeco.javacpp.opencv_videoio.opencv_videoio.*;

import static org.bytedeco.javacpp.opencv_highgui.opencv_highgui.*;


/** \} highgui_opengl
 <p>
 *  \addtogroup highgui_qt
 *  \{
<p>
/** \brief QtFont available only for Qt. See cv::fontQt
 */
@Namespace("cv") @Properties(inherit = org.bytedeco.javacpp.opencv_highgui.opencv_highgui_presets.class)
public class QtFont extends Pointer {
    static { Loader.load(); }
    /** Default native constructor. */
    public QtFont() { super((Pointer)null); allocate(); }
    /** Native array allocator. Access with {@link Pointer#position(long)}. */
    public QtFont(long size) { super((Pointer)null); allocateArray(size); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public QtFont(Pointer p) { super(p); }
    private native void allocate();
    private native void allocateArray(long size);
    @Override public QtFont position(long position) {
        return (QtFont)super.position(position);
    }

    /** Name of the font */
    @MemberGetter public native @Cast("const char*") BytePointer nameFont();
    /** Color of the font. Scalar(blue_component, green_component, red_component[, alpha_component]) */
    public native @ByRef Scalar color(); public native QtFont color(Scalar color);
    /** See cv::QtFontStyles */
    public native int font_face(); public native QtFont font_face(int font_face);
    /** font data and metrics */
    @MemberGetter public native @Const IntPointer ascii();
    @MemberGetter public native @Const IntPointer greek();
    @MemberGetter public native @Const IntPointer cyrillic();
    public native float hscale(); public native QtFont hscale(float hscale);
    public native float vscale(); public native QtFont vscale(float vscale);
    /** slope coefficient: 0 - normal, >0 - italic */
    public native float shear(); public native QtFont shear(float shear);
    /** See cv::QtFontWeights */
    public native int thickness(); public native QtFont thickness(int thickness);
    /** horizontal interval between letters */
    public native float dx(); public native QtFont dx(float dx);
    /** PointSize */
    public native int line_type(); public native QtFont line_type(int line_type);
}