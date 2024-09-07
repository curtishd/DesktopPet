package me.cdh.Draw;


/*
 * 1.加载图片同BufferedImage记录图片
 * 2.定义Rect类用来获取区域
 * 3.用Rect来裁剪图片+绘图
 * 4.Repaint方法来重绘
 * 5.线程实现Timer计时器
 * */

import me.cdh.DrawControl.MyFrame;
import me.cdh.Main.MainAWT;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Display {

    private Display() {
    }

    public static class Rect {
        //属性
        public int x, y, width, height;
        public float xf, yf, wf, hf;

        public Rect(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        public Rect(float x, float y, float w, float h) {
            this.xf = x;
            this.yf = y;
            this.wf = w;
            this.hf = h;
        }


        public boolean Judge_PointInRect(int x, int y) {
            return x >= this.x
                    && x <= this.x + this.width
                    && y >= this.y
                    && y <= this.y + this.height;
        }

    }
    //嵌套类：矩形区域Rect

    public static class Load {

        public static BufferedImage LoadImage(String RelativePath) {
            File ImageFile = new File("target/classes/me/cdh/" + RelativePath);
            //从路劲获取文件对象
            try {
                //加载图片
                return ImageIO.read(ImageFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
                //抛出异常
            }
        }

    }

    public static class Text {
        public Text(int Size, String Content) {
            this.Init(Size);
            this.Content = Content;
        }

        public String Content;
        public Point Location;
        public int Size;
        public Font font;
        public int State;

        private void Init(int Size) {
            this.Size = Size;
            this.font = new Font("楷体", Font.PLAIN, this.Size);
            this.Location = new Point(0, 0);
            this.State = State_NotDisplay;
        }

        public static final int State_NotDisplay = 0, State_Display = 1;

        public void Display(Graphics2D g) {
            g.setFont(font);
            g.drawString(Content, this.Location.x, this.Location.y);
        }
        //绘制文本：通用
    }
    //文本

    public static class Paint extends JPanel {
        public Paint() {
            setBackground(null);
            //设置背景为空：JPanel里面设置为空
            setOpaque(false);
            //设置背景颜色不透明，选择false
        }

        //--------------------------------私有构造器--------------------------------------

        //函数指针

        //--------------------------------函数指针--------------------------------------

        public static void Draw_ChooseSrc_DrawDst(BufferedImage image,
                                                  Rect Src, Rect Dst,
                                                  final int ScaleMethod, Graphics2D g) {
            BufferedImage Temp_image;//为了不改变原来Image的值
            Image Object_image;
            if (Src != null) {
                int Temp_x, Temp_y, Temp_width, Temp_height;

                if (Src.x <= image.getWidth() && Src.x >= 0) {
                    Temp_x = Src.x;
                } else if (Src.x < 0) {
                    Temp_x = 0;
                } else {
                    Temp_x = image.getWidth() - 1;
                }
                //x限界

                if (Src.y <= image.getHeight() && Src.y >= 0) {
                    Temp_y = Src.y;
                } else if (Src.y < 0) {
                    Temp_y = 0;
                } else {
                    Temp_y = image.getHeight() - 1;
                }
                //y限界

                if (Src.width <= 0) {
                    return;
                } else if (Src.x >= image.getWidth()) {
                    return;
                } else if (Src.width + Src.x <= 0) {
                    return;
                } else if (Src.x < 0 &&
                        Src.width + Src.x <= image.getWidth() &&
                        Src.width + Src.x >= 0) {
                    Temp_width = Src.width + Src.x;
                } else if (Src.x >= 0 &&
                        Src.width + Src.x <= image.getWidth() &&
                        Src.width + Src.x >= 0) {
                    Temp_width = Src.width;
                } else if (Src.x >= 0 &&
                        Src.width + Src.x >= image.getWidth() &&
                        Src.width + Src.x >= 0) {
                    Temp_width = image.getWidth() - Src.x;
                } else {
                    Temp_width = image.getWidth();
                }
                //width限界

                if (Src.height <= 0) {
                    return;
                } else if (Src.y >= image.getHeight()) {
                    return;
                } else if (Src.height + Src.y <= 0) {
                    return;
                } else if (Src.y < 0 &&
                        Src.height + Src.y <= image.getHeight() &&
                        Src.height + Src.y >= 0) {
                    Temp_height = Src.height + Src.y;
                } else if (Src.y >= 0 &&
                        Src.height + Src.y <= image.getHeight() &&
                        Src.height + Src.y >= 0) {
                    Temp_height = Src.height;
                } else if (Src.y >= 0 &&
                        Src.height + Src.y >= image.getHeight() &&
                        Src.height + Src.y >= 0) {
                    Temp_height = image.getHeight() - Src.y;
                } else {
                    Temp_height = image.getHeight();
                }
                //height限界

                Temp_image = image.getSubimage(Temp_x, Temp_y, Temp_width, Temp_height);
            } else {
                Temp_image = image.getSubimage(0, 0, image.getWidth(), image.getHeight());
            }
            if (Dst != null) {
                if (Dst.width <= 0 || Dst.height <= 0) {
                    return;
                }
                Object_image = Temp_image.getScaledInstance(Dst.width, Dst.height, ScaleMethod);
                //放缩一下
                g.drawImage(Object_image, Dst.x, Dst.y, Dst.width, Dst.height, null);
                //绘制Dst区域
            } else {
                g.drawImage(Temp_image, 0, 0, Temp_image.getWidth(), Temp_image.getHeight(), null);
                //从0，0点绘制本来大小
            }
        }

        //int
        public static void DrawF_ChooseSrc_DrawDst(BufferedImage image,
                                                   Rect Src, Rect Dst,
                                                   final int ScaleMethod, Graphics2D g) {
            BufferedImage Temp_image;//为了不改变原来Image的值
            Image Object_image;

            if (Src != null) {
                float Temp_x, Temp_y, Temp_width, Temp_height;

                if (Src.xf <= image.getWidth() && Src.xf >= 0) {
                    Temp_x = Src.xf;
                } else if (Src.xf < 0) {
                    Temp_x = 0;
                } else {
                    Temp_x = image.getWidth() - 1;
                }
                //x限界

                if (Src.yf <= image.getHeight() && Src.yf >= 0) {
                    Temp_y = Src.yf;
                } else if (Src.yf < 0) {
                    Temp_y = 0;
                } else {
                    Temp_y = image.getHeight() - 1;
                }
                //y限界

                if (Src.wf <= 0) {
                    return;
                } else if (Src.xf >= image.getWidth()) {
                    return;
                } else if (Src.wf + Src.xf <= 0) {
                    return;
                } else if (Src.xf < 0 &&
                        Src.wf + Src.xf <= image.getWidth() &&
                        Src.wf + Src.xf >= 0) {
                    Temp_width = Src.wf + Src.xf;
                } else if (Src.xf >= 0 &&
                        Src.wf + Src.xf <= image.getWidth() &&
                        Src.wf + Src.xf >= 0) {
                    Temp_width = Src.wf;
                } else if (Src.xf >= 0 &&
                        Src.wf + Src.xf >= image.getWidth() &&
                        Src.wf + Src.xf >= 0) {
                    Temp_width = image.getWidth() - Src.xf;
                } else {
                    Temp_width = image.getWidth();
                }
                //width限界

                if (Src.hf <= 0) {
                    return;
                } else if (Src.yf >= image.getWidth()) {
                    return;
                } else if (Src.hf + Src.yf <= 0) {
                    return;
                } else if (Src.yf < 0 &&
                        Src.hf + Src.yf <= image.getWidth() &&
                        Src.hf + Src.yf >= 0) {
                    Temp_height = Src.hf + Src.yf;
                } else if (Src.yf >= 0 &&
                        Src.hf + Src.yf <= image.getWidth() &&
                        Src.hf + Src.yf >= 0) {
                    Temp_height = Src.hf;
                } else if (Src.yf >= 0 &&
                        Src.hf + Src.yf >= image.getWidth() &&
                        Src.hf + Src.yf >= 0) {
                    Temp_height = image.getHeight() - Src.yf;
                } else {
                    Temp_height = image.getWidth();
                }
                //height限界

                Temp_image = image.getSubimage((int) Temp_x, (int) Temp_y, (int) Temp_width, (int) Temp_height);
            } else {
                Temp_image = image.getSubimage(0, 0, image.getWidth(), image.getHeight());
            }
            if (Dst != null) {
                if (Dst.wf <= 0 || Dst.hf <= 0) {
                    return;
                }
                int w, h;
                if ((int) Dst.wf > 0) {
                    w = (int) Dst.wf;
                } else {
                    w = 1;
                }
                if ((int) Dst.hf > 0) {
                    h = (int) Dst.hf;
                } else {
                    h = 1;
                }
                //非0处理
                Object_image = Temp_image.getScaledInstance(w, h, ScaleMethod);
                //放缩一下
                g.drawImage(Object_image, (int) Dst.xf, (int) Dst.yf, w, h, null);
                //绘制Dst区域
            } else {
                g.drawImage(Temp_image, 0, 0, Temp_image.getWidth(), Temp_image.getHeight(), null);
                //从0，0点绘制本来大小
            }
        }
        //float
        //绘制图像：此处绘制图片没有加事件

        public static void Draw_ChooseSrcDegree_DrawDst(Picture p, int Degree,
                                                        Rect Src, Rect Dst,
                                                        final int ScaleMethod, Graphics2D g, MyFrame frame) {
            p.DrawAddEvent(frame);
            //绘制添加事件
            int Centre_x = (int) (p.Dst.x + p.SrcCentre.x * p.Dst.width / (float) p.Src.width);
            int Centre_y = (int) (p.Dst.y + p.SrcCentre.y * p.Dst.height / (float) p.Src.height);
            g.rotate(Math.toRadians(Degree), Centre_x, Centre_y);
            DrawF_ChooseSrc_DrawDst(p.Image_B, Src, Dst, ScaleMethod, g);
            g.rotate(Math.toRadians(-Degree), Centre_x, Centre_y);
        }
        //绘制图像指定位置去窗口中的指定位置:先切割出Src再在Src中选择点

        public static Rect Get_PictureRelativeLocation_OfChoseDst(Rect Src, Point Src_Point, Point Dst_Point,
                                                                  int Dst_Width, int Dst_Height, Point ChoseDst_xy) {//其中Dst_Point是希望在主控件上的相对位置
            Rect Dst = new Rect(0, 0, 0, 0);
            if (Src_Point.x >= Src.x && Src_Point.x <= Src.x + Src.width &&
                    Src_Point.y >= Src.y && Src_Point.y <= Src.y + Src.height) {
                int Distance_x = Dst_Width * Src_Point.x / Src.width;
                int Distance_y = Dst_Height * Src_Point.y / Src.height;
                int Object_x = Dst_Point.x - Distance_x + ChoseDst_xy.x;
                int Object_y = Dst_Point.y - Distance_y + ChoseDst_xy.y;
                Dst = new Rect(Object_x, Object_y, Dst_Width, Dst_Height);
            } else {
                System.out.println("Draw_SrcPoint_To_DstPoint Src_Point Error");
            }
            return Dst;
        }
        //给后来的图片设置相对于MainBody的位置

        //--------------------------------功能绘制函数--------------------------------------

        @Override
        protected void paintComponent(Graphics g) {
            MainAWT.MainFrame.Refresh_PictureList();
            //刷新事件函数
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            MainAWT.robot.Display_Main(g2);
        }
        //总绘制函数

        @Override
        public void repaint() {
            super.repaint();
        }
        //重新绘图函数：重绘之后要刷新事件列表

    }
}
