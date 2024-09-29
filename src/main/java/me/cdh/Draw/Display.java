package me.cdh.Draw;


import me.cdh.Main;

/*
 * 1.加载图片同BufferedImage记录图片
 * 2.定义Rect类用来获取区域
 * 3.用Rect来裁剪图片+绘图
 * 4.Repaint方法来重绘
 * 5.线程实现Timer计时器
 * */

import me.cdh.DrawControl.MyFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

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


        public boolean judgePointInRect(int x, int y) {
            return x >= this.x
                    && x <= this.x + this.width
                    && y >= this.y
                    && y <= this.y + this.height;
        }

    }
    //嵌套类：矩形区域Rect

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

        public static void drawChooseSrcDrawDst(BufferedImage image,
                                                Rect src, Rect dst,
                                                final int scaleMethod, Graphics2D g) {
            BufferedImage Temp_image;//为了不改变原来Image的值
            Image Object_image;
            if (src != null) {
                int Temp_x, Temp_y, Temp_width, Temp_height;

                if (src.x <= image.getWidth() && src.x >= 0) {
                    Temp_x = src.x;
                } else if (src.x < 0) {
                    Temp_x = 0;
                } else {
                    Temp_x = image.getWidth() - 1;
                }
                //x限界

                if (src.y <= image.getHeight() && src.y >= 0) {
                    Temp_y = src.y;
                } else if (src.y < 0) {
                    Temp_y = 0;
                } else {
                    Temp_y = image.getHeight() - 1;
                }
                //y限界

                if (src.width <= 0) {
                    return;
                } else if (src.x >= image.getWidth()) {
                    return;
                } else if (src.width + src.x <= 0) {
                    return;
                } else if (src.x < 0 &&
                        src.width + src.x <= image.getWidth() &&
                        src.width + src.x >= 0) {
                    Temp_width = src.width + src.x;
                } else if (src.x >= 0 &&
                        src.width + src.x <= image.getWidth() &&
                        src.width + src.x >= 0) {
                    Temp_width = src.width;
                } else if (src.x >= 0 &&
                        src.width + src.x >= image.getWidth() &&
                        src.width + src.x >= 0) {
                    Temp_width = image.getWidth() - src.x;
                } else {
                    Temp_width = image.getWidth();
                }
                //width限界

                if (src.height <= 0) {
                    return;
                } else if (src.y >= image.getHeight()) {
                    return;
                } else if (src.height + src.y <= 0) {
                    return;
                } else if (src.y < 0 &&
                        src.height + src.y <= image.getHeight() &&
                        src.height + src.y >= 0) {
                    Temp_height = src.height + src.y;
                } else if (src.y >= 0 &&
                        src.height + src.y <= image.getHeight() &&
                        src.height + src.y >= 0) {
                    Temp_height = src.height;
                } else if (src.y >= 0 &&
                        src.height + src.y >= image.getHeight() &&
                        src.height + src.y >= 0) {
                    Temp_height = image.getHeight() - src.y;
                } else {
                    Temp_height = image.getHeight();
                }
                //height限界

                Temp_image = image.getSubimage(Temp_x, Temp_y, Temp_width, Temp_height);
            } else {
                Temp_image = image.getSubimage(0, 0, image.getWidth(), image.getHeight());
            }
            if (dst != null) {
                if (dst.width <= 0 || dst.height <= 0) {
                    return;
                }
                Object_image = Temp_image.getScaledInstance(dst.width, dst.height, scaleMethod);
                //放缩一下
                g.drawImage(Object_image, dst.x, dst.y, dst.width, dst.height, null);
                //绘制Dst区域
            } else {
                g.drawImage(Temp_image, 0, 0, Temp_image.getWidth(), Temp_image.getHeight(), null);
                //从0，0点绘制本来大小
            }
        }

        //int
        public static void drawFChooseSrcDrawDst(BufferedImage image,
                                                 Rect src, Rect dst,
                                                 final int scaleMethod, Graphics2D g) {
            BufferedImage tmpImg;//为了不改变原来Image的值
            Image objImg;

            if (src != null) {
                float Temp_x, Temp_y, Temp_width, Temp_height;

                if (src.xf <= image.getWidth() && src.xf >= 0) {
                    Temp_x = src.xf;
                } else if (src.xf < 0) {
                    Temp_x = 0;
                } else {
                    Temp_x = image.getWidth() - 1;
                }
                //x限界

                if (src.yf <= image.getHeight() && src.yf >= 0) {
                    Temp_y = src.yf;
                } else if (src.yf < 0) {
                    Temp_y = 0;
                } else {
                    Temp_y = image.getHeight() - 1;
                }
                //y限界

                if (src.wf <= 0) {
                    return;
                } else if (src.xf >= image.getWidth()) {
                    return;
                } else if (src.wf + src.xf <= 0) {
                    return;
                } else if (src.xf < 0 &&
                        src.wf + src.xf <= image.getWidth() &&
                        src.wf + src.xf >= 0) {
                    Temp_width = src.wf + src.xf;
                } else if (src.xf >= 0 &&
                        src.wf + src.xf <= image.getWidth() &&
                        src.wf + src.xf >= 0) {
                    Temp_width = src.wf;
                } else if (src.xf >= 0 &&
                        src.wf + src.xf >= image.getWidth() &&
                        src.wf + src.xf >= 0) {
                    Temp_width = image.getWidth() - src.xf;
                } else {
                    Temp_width = image.getWidth();
                }
                //width限界

                if (src.hf <= 0) {
                    return;
                } else if (src.yf >= image.getWidth()) {
                    return;
                } else if (src.hf + src.yf <= 0) {
                    return;
                } else if (src.yf < 0 &&
                        src.hf + src.yf <= image.getWidth() &&
                        src.hf + src.yf >= 0) {
                    Temp_height = src.hf + src.yf;
                } else if (src.yf >= 0 &&
                        src.hf + src.yf <= image.getWidth() &&
                        src.hf + src.yf >= 0) {
                    Temp_height = src.hf;
                } else if (src.yf >= 0 &&
                        src.hf + src.yf >= image.getWidth() &&
                        src.hf + src.yf >= 0) {
                    Temp_height = image.getHeight() - src.yf;
                } else {
                    Temp_height = image.getWidth();
                }
                //height限界

                tmpImg = image.getSubimage((int) Temp_x, (int) Temp_y, (int) Temp_width, (int) Temp_height);
            } else {
                tmpImg = image.getSubimage(0, 0, image.getWidth(), image.getHeight());
            }
            if (dst != null) {
                if (dst.wf <= 0 || dst.hf <= 0) {
                    return;
                }
                int w, h;
                if ((int) dst.wf > 0) {
                    w = (int) dst.wf;
                } else {
                    w = 1;
                }
                if ((int) dst.hf > 0) {
                    h = (int) dst.hf;
                } else {
                    h = 1;
                }
                //非0处理
                objImg = tmpImg.getScaledInstance(w, h, scaleMethod);
                //放缩一下
                g.drawImage(objImg, (int) dst.xf, (int) dst.yf, w, h, null);
                //绘制Dst区域
            } else {
                g.drawImage(tmpImg, 0, 0, tmpImg.getWidth(), tmpImg.getHeight(), null);
                //从0，0点绘制本来大小
            }
        }
        //float
        //绘制图像：此处绘制图片没有加事件

        public static void Draw_ChooseSrcDegree_DrawDst(Picture p, int Degree,
                                                        Rect src, Rect dst,
                                                        final int scaleMethod, Graphics2D g, MyFrame frame) {
            p.DrawAddEvent(frame);
            //绘制添加事件
            int Centre_x = (int) (p.Dst.x + p.SrcCentre.x * p.Dst.width / (float) p.Src.width);
            int Centre_y = (int) (p.Dst.y + p.SrcCentre.y * p.Dst.height / (float) p.Src.height);
            g.rotate(Math.toRadians(Degree), Centre_x, Centre_y);
            drawFChooseSrcDrawDst(p.Image_B, src, dst, scaleMethod, g);
            g.rotate(Math.toRadians(-Degree), Centre_x, Centre_y);
        }
        //绘制图像指定位置去窗口中的指定位置:先切割出Src再在Src中选择点

        public static Rect getPictureRelativeLocationOfChoseDst(Rect src, Point srcPoint, Point Dst_Point,
                                                                int Dst_Width, int Dst_Height, Point ChoseDst_xy) {//其中Dst_Point是希望在主控件上的相对位置
            Rect Dst = new Rect(0, 0, 0, 0);
            if (srcPoint.x >= src.x && srcPoint.x <= src.x + src.width &&
                    srcPoint.y >= src.y && srcPoint.y <= src.y + src.height) {
                int Distance_x = Dst_Width * srcPoint.x / src.width;
                int Distance_y = Dst_Height * srcPoint.y / src.height;
                int Object_x = Dst_Point.x - Distance_x + ChoseDst_xy.x;
                int Object_y = Dst_Point.y - Distance_y + ChoseDst_xy.y;
                Dst = new Rect(Object_x, Object_y, Dst_Width, Dst_Height);
            } else {
                System.out.println("Draw_SrcPoint_To_DstPoint srcPoint Error");
            }
            return Dst;
        }
        //给后来的图片设置相对于MainBody的位置

        //--------------------------------功能绘制函数--------------------------------------

        @Override
        protected void paintComponent(Graphics g) {
            Main.MainFrame.Refresh_PictureList();
            //刷新事件函数
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            Main.robot.Display_Main(g2);
        }
        //总绘制函数

        @Override
        public void repaint() {
            super.repaint();
        }
        //重新绘图函数：重绘之后要刷新事件列表

    }
}
