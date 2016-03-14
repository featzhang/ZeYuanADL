package laboratory;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.awt.font.*;
import javax.swing.*;
import java.util.Vector;
import java.util.List;
import java.util.Arrays;
import util.AResource;
import static java.awt.Color.*;

public class Surface extends JPanel implements Runnable {

    private static Surface currentSurface;
    private static Image cupanim, java_logo;
    private static BufferedImage bimg;
    public Director director;
    public int index = 0;
    public long sleepAmt = 30;
    private Thread currentThread;
    private static Color myBlack = new Color(20, 20, 20);
    private static Color myWhite = new Color(240, 240, 255);
    private static Color myRed = new Color(149, 43, 42);
    private static Color myBlue = new Color(94, 105, 176);
    private static Color myYellow = new Color(255, 255, 140);

    public Surface() {
        currentSurface = this;
        setBackground(myBlack);
        setLayout(new BorderLayout());
        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (currentThread == null) {
                    start();
                } else {
                    stop();
                }
            }
        });
        cupanim = AResource.getImage("cupanim");
        java_logo = AResource.getImage("java_logo");
        director = new Director();
    }

    static FontMetrics getMetrics(Font font) {
        return currentSurface.getFontMetrics(font);
    }

    @Override
    public void paint(Graphics g) {
        Dimension d = getSize();
        if (d.width <= 0 || d.height <= 0) {
            return;
        }
        if (bimg == null
                || bimg.getWidth() != d.width
                || bimg.getHeight() != d.height) {
            bimg = getGraphicsConfiguration().createCompatibleImage(d.width, d.height);
            // reset future scenes
            for (int i = index + 1; i < director.size(); i++) {
                ((Scene) director.get(i)).reset(d.width, d.height);
            }
        }

        Scene scene = (Scene) director.get(index);
        if (scene.index <= scene.length) {
            if (currentThread != null) {
                scene.step(d.width, d.height);
            }

            Graphics2D g2 = bimg.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setBackground(getBackground());
            g2.clearRect(0, 0, d.width, d.height);

            scene.render(d.width, d.height, g2);

            if (currentThread != null) {
                // increment scene.index after scene.render
                scene.index++;
            }
            g2.dispose();
        }
        g.drawImage(bimg, 0, 0, this);
    }

    public void start() {
        if (currentThread == null) {
            currentThread = new Thread(this);
            currentThread.setPriority(Thread.MIN_PRIORITY);
            currentThread.setName("简介");
            currentThread.start();
        }
    }

    public synchronized void stop() {
        if (currentThread != null) {
            currentThread.interrupt();
        }
        currentThread = null;
        notifyAll();
    }

    public void reset() {
        index = 0;
        Dimension d = getSize();
        for (Scene scene : director) {
            scene.reset(d.width, d.height);
        }
    }

    @Override
    public void run() {
        Thread me = Thread.currentThread();
        while (currentThread == me && !isShowing() || getSize().width <= 0) {
            //currentThread是当前线程并且正在显示或者宽度小于等于0
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                return;
            }
        }

        if (index == 0) {
            reset();
        }

        while (currentThread == me) {
            Scene scene = (Scene) director.get(index);
            if (((Boolean) scene.participate).booleanValue()) {
                repaint();
                try {
                    Thread.sleep(sleepAmt);
                } catch (InterruptedException e) {
                    break;
                }
                if (scene.index > scene.length) {
                    scene.pause(currentThread);
                    if (++index >= director.size()) {
                        reset();
                    }
                }
            } else {
                if (++index >= director.size()) {
                    reset();
                }
            }
        }
        currentThread = null;
    }

    interface Part {

        public void reset(int newwidth, int newheight);

        public void step(int w, int h);

        public void render(int w, int h, Graphics2D g2);

        public int getBegin();

        public int getEnd();
    }

    static class Director extends Vector<Scene> {

        GradientPaint gp = new GradientPaint(0, 40, myBlue, 38, 2, myBlack);
        Font f1 = new Font("serif", Font.PLAIN, 200);
        Font f2 = new Font("serif", Font.PLAIN, 120);
        Font f3 = new Font("serif", Font.PLAIN, 72);
        Object partsInfo[][][] = {
            {{"J  -  scale text on gradient", "0"},
                {new GpE(GpE.BURI, myBlack, myBlue, 0, 20),
                    new TxE("J", f1, TxE.SCI, myYellow, 2, 20)}},
            {{"2  -  scale & rotate text on gradient", "0"},
                {new GpE(GpE.BURI, myBlue, myBlack, 0, 22),
                    new TxE("2", f1, TxE.RI | TxE.SCI, myYellow, 2, 22)}},
            {{"D  -  scale text on gradient", "0"},
                {new GpE(GpE.BURI, myBlack, myBlue, 0, 20),
                    new TxE("D", f1, TxE.SCI, myYellow, 2, 20)}},
            {{"Java2D  -  scale & rotate text on gradient", "1000"},
                {new GpE(GpE.SIH, myBlue, myBlack, 0, 40),
                    new TxE("Java2D", f2, TxE.RI | TxE.SCI, myYellow, 0, 40)}},
            {{"Previous scene dither dissolve out", "0"},
                {new DdE(0, 20, 1)}},
            {{"图形 特性", "999"},
                {new Temp(Temp.RECT, null, 0, 15),
                    new Temp(Temp.IMG, java_logo, 2, 15),
                    new Temp(Temp.RNA | Temp.INA, java_logo, 16, 130),
                    new Features(Features.GRAPHICS, 16, 130)}},
            {{"Java2D  -  texture text on gradient", "1000"},
                {new GpE(GpE.WI, myBlue, myBlack, 0, 20),
                    new GpE(GpE.WD, myBlue, myBlack, 21, 40),
                    new TpE(TpE.OI | TpE.NF, myBlack, myYellow, 4, 0, 10),
                    new TpE(TpE.OD | TpE.NF, myBlack, myYellow, 4, 11, 20),
                    new TpE(TpE.OI | TpE.NF | TpE.HAF, myBlack, myYellow, 5, 21, 40),
                    new TxE("Java2D", f2, 0, null, 0, 40)}},
            {{"Previous scene random close out", "0"},
                {new CoE(CoE.RAND, 0, 20)}},
            {{"文本 特性", "999"},
                {new Temp(Temp.RECT, null, 0, 15),
                    new Temp(Temp.IMG, java_logo, 2, 15),
                    new Temp(Temp.RNA | Temp.INA, java_logo, 16, 130),
                    new Features(Features.TEXT, 16, 130)}},
            {{"Java2D  -  composite text on texture", "1000"},
                {new TpE(TpE.RI, myBlack, gp, 40, 0, 20),
                    new TpE(TpE.RD, myBlack, gp, 40, 21, 40),
                    new TpE(TpE.RI, myBlack, gp, 40, 41, 60),
                    new TxE("Java2D", f2, TxE.AC, myYellow, 0, 60)}},
            {{"Previous scene dither dissolve out", "0"},
                {new DdE(0, 20, 4)}},
            {{"图片 特性", "999"},
                {new Temp(Temp.RECT, null, 0, 15),
                    new Temp(Temp.IMG, java_logo, 2, 15),
                    new Temp(Temp.RNA | Temp.INA, java_logo, 16, 130),
                    new Features(Features.IMAGES, 16, 130)}},
            {{"Java2D  -  text on gradient", "1000"},
                {new GpE(GpE.SDH, myBlue, myBlack, 0, 20),
                    new GpE(GpE.SIH, myBlue, myBlack, 21, 40),
                    new GpE(GpE.SDH, myBlue, myBlack, 41, 50),
                    new GpE(GpE.INC | GpE.NF, myRed, myYellow, 0, 50),
                    new TxE("Java2D", f2, TxE.NOP, null, 0, 50)}},
            {{"Previous scene ellipse close out", "0"},
                {new CoE(CoE.OVAL, 0, 20)}},
            {{"颜色 特性", "999"},
                {new Temp(Temp.RECT, null, 0, 15),
                    new Temp(Temp.IMG, java_logo, 2, 15),
                    new Temp(Temp.RNA | Temp.INA, java_logo, 16, 99),
                    new Features(Features.COLOR, 16, 99)}},
            {{"Java2D  -  composite and rotate text on paints", "2000"},
                {new GpE(GpE.BURI, myBlack, myBlue, 0, 20),
                    new GpE(GpE.BURD, myBlack, myBlue, 21, 30),
                    new TpE(TpE.OI | TpE.HAF, myBlack, myBlue, 10, 31, 40),
                    new TxE("Java2D", f2, TxE.AC | TxE.RI, myYellow, 0, 40)}},
            {{"Previous scene subimage transform out", "0"},
                {new SiE(60, 60, 0, 40)}},
            {{"信任  -  transform in", "1000"},
                {new LnE(LnE.ACI | LnE.ZOOMI | LnE.RI, 0, 60),
                    new TxE("信任", f3, TxE.AC | TxE.SCI, RED, 20, 30),
                    new TxE("信任", f3, TxE.SCXD, RED, 31, 38),
                    new TxE("信任", f3, TxE.SCXI, RED, 39, 48),
                    new TxE("信任", f3, TxE.SCXD, RED, 49, 54),
                    new TxE("信任", f3, TxE.SCXI, RED, 55, 60)}},
            {{"信任  -  transform out", "0"},
                {new LnE(LnE.ACD | LnE.ZOOMD | LnE.RD, 0, 45),
                    new TxE("信任", f3, 0, RED, 0, 9),
                    new TxE("信任", f3, TxE.SCD | TxE.RD, RED, 10, 30)}},
            {{"贡献者", "1000"},
                {new Temp(Temp.RECT, null, 0, 30),
                    new Temp(Temp.IMG, cupanim, 4, 30),
                    new Temp(Temp.RNA | Temp.INA, cupanim, 31, 200),
                    new Contributors(34, 200)}},};

        public Director() {
            for (Object[][] partInfo : partsInfo) {
                List<Part> parts = new Vector<Part>();
                for (Object part : partInfo[1]) {
                    parts.add((Part) part);
                }
                addElement(new Scene(parts, partInfo[0][0], partInfo[0][1]));
            }
        }
    }

    static class Scene extends Object {

        public Object name;
        public Object participate = Boolean.TRUE;
        public Object pauseAmt;
        public List<Part> parts;
        public int index;
        public int length;

        public Scene(List<Part> parts, Object name, Object pauseAmt) {
            this.name = name;
            this.parts = parts;
            this.pauseAmt = pauseAmt;
            for (Part part : parts) {
                int partLength = part.getEnd();
                if (partLength > length) {
                    length = partLength;
                }
            }
        }

        public void reset(int w, int h) {
            index = 0;
            for (int i = 0; i < parts.size(); i++) {
                ((Part) parts.get(i)).reset(w, h);
            }
        }

        public void step(int w, int h) {
            for (int i = 0; i < parts.size(); i++) {
                Part part = (Part) parts.get(i);
                if (index >= part.getBegin() && index <= part.getEnd()) {
                    part.step(w, h);
                }
            }
        }

        public void render(int w, int h, Graphics2D g2) {
            for (int i = 0; i < parts.size(); i++) {
                Part part = (Part) parts.get(i);
                if (index >= part.getBegin() && index <= part.getEnd()) {
                    part.render(w, h, g2);
                }
            }
        }

        public void pause(Thread thread) {
            try {
                Thread.sleep(Long.parseLong((String) pauseAmt));
            } catch (Exception e) {
            }
            System.gc();
        }
    }

    static class TxE implements Part {

        static final int INC = 1;
        static final int DEC = 2;
        static final int R = 4;            // rotate
        static final int RI = R | INC;
        static final int RD = R | DEC;
        static final int SC = 8;            // scale
        static final int SCI = SC | INC;
        static final int SCD = SC | DEC;
        static final int SCX = 16;           // scale invert x
        static final int SCXI = SCX | SC | INC;
        static final int SCXD = SCX | SC | DEC;
        static final int SCY = 32;           // scale invert y
        static final int SCYI = SCY | SC | INC;
        static final int SCYD = SCY | SC | DEC;
        static final int AC = 64;           // AlphaComposite
        static final int CLIP = 128;          // Clipping
        static final int NOP = 512;          // No Paint
        private int beginning, ending;
        private int type;
        private double rIncr, sIncr;
        private double sx, sy, rotate;
        private Shape shapes[], txShapes[];
        private int sw;
        private int numRev;
        private Paint paint;

        public TxE(String text,
                Font font,
                int type,
                Paint paint,
                int beg,
                int end) {
            this.type = type;
            this.paint = paint;
            this.beginning = beg;
            this.ending = end;

            setIncrements(2);

            char[] chars = text.toCharArray();
            shapes = new Shape[chars.length];
            txShapes = new Shape[chars.length];
            FontRenderContext frc = new FontRenderContext(null, true, true);
            TextLayout tl = new TextLayout(text, font, frc);
            sw = (int) tl.getOutline(null).getBounds().getWidth();
            for (int j = 0; j < chars.length; j++) {
                String s = String.valueOf(chars[j]);
                shapes[j] = new TextLayout(s, font, frc).getOutline(null);
            }
        }

        public void setIncrements(double numRevolutions) {
            this.numRev = (int) numRevolutions;
            rIncr = 360.0 / ((ending - beginning) / numRevolutions);
            sIncr = 1.0 / (ending - beginning);
            if ((type & SCX) != 0 || (type & SCY) != 0) {
                sIncr *= 2;
            }
            if ((type & DEC) != 0) {
                rIncr = -rIncr;
                sIncr = -sIncr;
            }
        }

        @Override
        public void reset(int w, int h) {
            if (type == SCXI) {
                sx = -1.0;
                sy = 1.0;
            } else if (type == SCYI) {
                sx = 1.0;
                sy = -1.0;
            } else {
                sx = sy = (type & DEC) != 0 ? 1.0 : 0.0;
            }
            rotate = 0;
        }

        @Override
        public void step(int w, int h) {

            float charWidth = w / 2 - sw / 2;

            for (int i = 0; i < shapes.length; i++) {
                AffineTransform at = new AffineTransform();
                Rectangle2D maxBounds = shapes[i].getBounds();
                at.translate(charWidth, h / 2 + maxBounds.getHeight() / 2);
                charWidth += (float) maxBounds.getWidth() + 1;
                Shape shape = at.createTransformedShape(shapes[i]);
                Rectangle2D b1 = shape.getBounds2D();

                if ((type & R) != 0) {
                    at.rotate(Math.toRadians(rotate));
                }
                if ((type & SC) != 0) {
                    at.scale(sx, sy);
                }
                shape = at.createTransformedShape(shapes[i]);
                Rectangle2D b2 = shape.getBounds2D();

                double xx = (b1.getX() + b1.getWidth() / 2)
                        - (b2.getX() + b2.getWidth() / 2);
                double yy = (b1.getY() + b1.getHeight() / 2)
                        - (b2.getY() + b2.getHeight() / 2);
                AffineTransform toCenterAT = new AffineTransform();
                toCenterAT.translate(xx, yy);
                toCenterAT.concatenate(at);
                txShapes[i] = toCenterAT.createTransformedShape(shapes[i]);
            }
            // avoid over rotation
            if (Math.abs(rotate) <= numRev * 360) {
                rotate += rIncr;
                if ((type & SCX) != 0) {
                    sx += sIncr;
                } else if ((type & SCY) != 0) {
                    sy += sIncr;
                } else {
                    sx += sIncr;
                    sy += sIncr;
                }
            }
        }

        @Override
        public void render(int w, int h, Graphics2D g2) {
            Composite saveAC = null;
            if ((type & AC) != 0 && sx > 0 && sx < 1) {
                saveAC = g2.getComposite();
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) sx));
            }
            GeneralPath path = null;
            if ((type & CLIP) != 0) {
                path = new GeneralPath();
            }
            if (paint != null) {
                g2.setPaint(paint);
            }
            for (int i = 0; i < txShapes.length; i++) {
                if ((type & CLIP) != 0) {
                    path.append(txShapes[i], false);
                } else {
                    g2.fill(txShapes[i]);
                }
            }
            if ((type & CLIP) != 0) {
                g2.clip(path);
            }
            if (saveAC != null) {
                g2.setComposite(saveAC);
            }
        }

        @Override
        public int getBegin() {
            return beginning;
        }

        @Override
        public int getEnd() {
            return ending;
        }
    }

    static class GpE implements Part {

        static final int INC = 1;               // increasing
        static final int DEC = 2;               // decreasing
        static final int CNT = 4;               // center
        static final int WID = 8;               // width
        static final int WI = WID | INC;
        static final int WD = WID | DEC;
        static final int HEI = 16;              // height
        static final int HI = HEI | INC;
        static final int HD = HEI | DEC;
        static final int SPL = 32 | CNT;        // split
        static final int SIW = SPL | INC | WID;
        static final int SDW = SPL | DEC | WID;
        static final int SIH = SPL | INC | HEI;
        static final int SDH = SPL | DEC | HEI;
        static final int BUR = 64 | CNT;        // burst
        static final int BURI = BUR | INC;
        static final int BURD = BUR | DEC;
        static final int NF = 128;             // no fill
        private Color c1, c2;
        private int beginning, ending;
        private float incr, index;
        private List<Rectangle2D> rect = new Vector<Rectangle2D>();
        private List<GradientPaint> grad = new Vector<GradientPaint>();
        private int type;

        public GpE(int type, Color c1, Color c2, int beg, int end) {
            this.type = type;
            this.c1 = c1;
            this.c2 = c2;
            this.beginning = beg;
            this.ending = end;
        }

        @Override
        public void reset(int w, int h) {
            incr = 1.0f / (ending - beginning);
            if ((type & CNT) != 0) {
                incr /= 2.3f;
            }
            if ((type & CNT) != 0 && (type & INC) != 0) {
                index = 0.5f;
            } else if ((type & DEC) != 0) {
                index = 1.0f;
                incr = -incr;
            } else {
                index = 0.0f;
            }
            index += incr;
        }

        @Override
        public void step(int w, int h) {
            rect.clear();
            grad.clear();

            if ((type & WID) != 0) {
                float w2 = 0, x1 = 0, x2 = 0;
                if ((type & SPL) != 0) {
                    w2 = w * 0.5f;
                    x1 = w * (1.0f - index);
                    x2 = w * index;
                } else {
                    w2 = w * index;
                    x1 = x2 = w2;
                }
                rect.add(new Rectangle2D.Float(0, 0, w2, h));
                rect.add(new Rectangle2D.Float(w2, 0, w - w2, h));
                grad.add(new GradientPaint(0, 0, c1, x1, 0, c2));
                grad.add(new GradientPaint(x2, 0, c2, w, 0, c1));
            } else if ((type & HEI) != 0) {
                float h2 = 0, y1 = 0, y2 = 0;
                if ((type & SPL) != 0) {
                    h2 = h * 0.5f;
                    y1 = h * (1.0f - index);
                    y2 = h * index;
                } else {
                    h2 = h * index;
                    y1 = y2 = h2;
                }
                rect.add(new Rectangle2D.Float(0, 0, w, h2));
                rect.add(new Rectangle2D.Float(0, h2, w, h - h2));
                grad.add(new GradientPaint(0, 0, c1, 0, y1, c2));
                grad.add(new GradientPaint(0, y2, c2, 0, h, c1));
            } else if ((type & BUR) != 0) {

                float w2 = w / 2;
                float h2 = h / 2;

                rect.add(new Rectangle2D.Float(0, 0, w2, h2));
                rect.add(new Rectangle2D.Float(w2, 0, w2, h2));
                rect.add(new Rectangle2D.Float(0, h2, w2, h2));
                rect.add(new Rectangle2D.Float(w2, h2, w2, h2));

                float x1 = w * (1.0f - index);
                float x2 = w * index;
                float y1 = h * (1.0f - index);
                float y2 = h * index;

                grad.add(new GradientPaint(0, 0, c1, x1, y1, c2));
                grad.add(new GradientPaint(w, 0, c1, x2, y1, c2));
                grad.add(new GradientPaint(0, h, c1, x1, y2, c2));
                grad.add(new GradientPaint(w, h, c1, x2, y2, c2));
            } else if ((type & NF) != 0) {
                float x = w * index;
                float y = h * index;
                grad.add(new GradientPaint(0, 0, c1, 0, y, c2));
            }

            if ((type & INC) != 0 || (type & DEC) != 0) {
                index += incr;
            }
        }

        @Override
        public void render(int w, int h, Graphics2D g2) {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_OFF);
            for (int i = 0; i < grad.size(); i++) {
                g2.setPaint((GradientPaint) grad.get(i));
                if ((type & NF) == 0) {
                    g2.fill((Rectangle2D) rect.get(i));
                }
            }
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
        }

        @Override
        public int getBegin() {
            return beginning;
        }

        @Override
        public int getEnd() {
            return ending;
        }
    }

    static class TpE implements Part {

        static final int INC = 1;             // increasing
        static final int DEC = 2;             // decreasing
        static final int OVAL = 4;             // oval
        static final int RECT = 8;             // rectangle
        static final int HAF = 16;             // half oval or rect size
        static final int NF = 32;             // no fill
        static final int OI = OVAL | INC;
        static final int OD = OVAL | DEC;
        static final int RI = RECT | INC;
        static final int RD = RECT | DEC;
        private Paint p1, p2;
        private int beginning, ending;
        private float incr, index;
        private TexturePaint texture;
        private int type;
        private int size;
        private BufferedImage bimg;
        private Rectangle rect;

        public TpE(int type, Paint p1, Paint p2, int size,
                int beg, int end) {
            this.type = type;
            this.p1 = p1;
            this.p2 = p2;
            this.beginning = beg;
            this.ending = end;
            setTextureSize(size);
        }

        public void setTextureSize(int size) {
            this.size = size;
            bimg = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
            rect = new Rectangle(0, 0, size, size);
        }

        @Override
        public void reset(int w, int h) {
            incr = (float) (size) / (float) (ending - beginning);
            if ((type & HAF) != 0) {
                incr /= 2;
            }
            if ((type & DEC) != 0) {
                index = size;
                if ((type & HAF) != 0) {
                    index /= 2;
                }
                incr = -incr;
            } else {
                index = 0.0f;
            }
            index += incr;
        }

        @Override
        public void step(int w, int h) {
            Graphics2D g2 = bimg.createGraphics();
            g2.setPaint(p1);
            g2.fillRect(0, 0, size, size);
            g2.setPaint(p2);
            if ((type & OVAL) != 0) {
                g2.fill(new Ellipse2D.Float(0, 0, index, index));
            } else if ((type & RECT) != 0) {
                g2.fill(new Rectangle2D.Float(0, 0, index, index));
            }
            texture = new TexturePaint(bimg, rect);
            g2.dispose();
            index += incr;
        }

        @Override
        public void render(int w, int h, Graphics2D g2) {
            g2.setPaint(texture);
            if ((type & NF) == 0) {
                g2.fillRect(0, 0, w, h);
            }
        }

        @Override
        public int getBegin() {
            return beginning;
        }

        @Override
        public int getEnd() {
            return ending;
        }
    }

    static class CoE implements Part {

        static final int WID = 1;
        static final int HEI = 2;
        static final int OVAL = 4;
        static final int RECT = 8;
        static final int RAND = 16;
        static final int ARC = 32;
        private int type;
        private int beginning, ending;
        private BufferedImage bimg;
        private Shape shape;
        private double zoom, extent;
        private double zIncr, eIncr;
        private boolean doRandom;

        public CoE(int type, int beg, int end) {
            this.type = type;
            this.beginning = beg;
            this.ending = end;
            zIncr = -(2.0 / (ending - beginning));
            eIncr = 360.0 / (ending - beginning);
            doRandom = (type & RAND) != 0;
        }

        @Override
        public void reset(int w, int h) {
            if (doRandom) {
                int num = (int) (Math.random() * 5.0);
                switch (num) {
                    case 0:
                        type = OVAL;
                        break;
                    case 1:
                        type = RECT;
                        break;
                    case 2:
                        type = RECT | WID;
                        break;
                    case 3:
                        type = RECT | HEI;
                        break;
                    case 4:
                        type = ARC;
                        break;
                    default:
                        type = OVAL;
                }
            }
            shape = null;
            bimg = null;
            extent = 360.0;
            zoom = 2.0;
        }

        @Override
        public void step(int w, int h) {
            if (bimg == null) {
                int biw = Surface.bimg.getWidth();
                int bih = Surface.bimg.getHeight();
                bimg = new BufferedImage(biw, bih, BufferedImage.TYPE_INT_RGB);
                Graphics2D big = bimg.createGraphics();
                big.drawImage(Surface.bimg, 0, 0, null);
            }
            double z = Math.min(w, h) * zoom;
            if ((type & OVAL) != 0) {
                shape = new Ellipse2D.Double(w / 2 - z / 2, h / 2 - z / 2, z, z);
            } else if ((type & ARC) != 0) {
                shape = new Arc2D.Double(-100, -100, w + 200, h + 200, 90, extent, Arc2D.PIE);
                extent -= eIncr;
            } else if ((type & RECT) != 0) {
                if ((type & WID) != 0) {
                    shape = new Rectangle2D.Double(w / 2 - z / 2, 0, z, h);
                } else if ((type & HEI) != 0) {
                    shape = new Rectangle2D.Double(0, h / 2 - z / 2, w, z);
                } else {
                    shape = new Rectangle2D.Double(w / 2 - z / 2, h / 2 - z / 2, z, z);
                }
            }
            zoom += zIncr;
        }

        @Override
        public void render(int w, int h, Graphics2D g2) {
            g2.clip(shape);
            g2.drawImage(bimg, 0, 0, null);
        }

        @Override
        public int getBegin() {
            return beginning;
        }

        @Override
        public int getEnd() {
            return ending;
        }
    }

    static class DdE implements Part {

        private int beginning, ending;
        private BufferedImage bimg;
        private Graphics2D big;
        private List list, xlist, ylist;
        private int xeNum, yeNum;    // element number
        private int xcSize, ycSize;  // chunk size
        private int inc;
        private int blocksize;

        public DdE(int beg, int end, int blocksize) {
            this.beginning = beg;
            this.ending = end;
            this.blocksize = blocksize;
        }

        private void createShuffledLists() {
            int width = bimg.getWidth();
            int height = bimg.getHeight();
            Integer xarray[] = new Integer[width];
            Integer yarray[] = new Integer[height];
            Integer array[] = new Integer[ending - beginning + 1];
            for (int i = 0; i < xarray.length; i++) {
                xarray[i] = new Integer(i);
            }
            for (int j = 0; j < yarray.length; j++) {
                yarray[j] = new Integer(j);
            }
            for (int k = 0; k < array.length; k++) {
                array[k] = new Integer(k);
            }
            java.util.Collections.shuffle(xlist = Arrays.asList(xarray));
            java.util.Collections.shuffle(ylist = Arrays.asList(yarray));
            java.util.Collections.shuffle(list = Arrays.asList(array));
        }

        @Override
        public void reset(int w, int h) {
            bimg = null;
        }

        @Override
        public void step(int w, int h) {
            if (inc > ending) {
                bimg = null;
            }
            if (bimg == null) {
                int biw = Surface.bimg.getWidth();
                int bih = Surface.bimg.getHeight();
                bimg = new BufferedImage(biw, bih, BufferedImage.TYPE_INT_RGB);
                createShuffledLists();
                big = bimg.createGraphics();
                big.drawImage(Surface.bimg, 0, 0, null);
                xcSize = (xlist.size() / (ending - beginning)) + 1;
                ycSize = (ylist.size() / (ending - beginning)) + 1;
                xeNum = 0;
                inc = 0;
            }
            xeNum = xcSize * ((Integer) list.get(inc)).intValue();
            yeNum = -ycSize;
            inc++;
        }

        @Override
        public void render(int w, int h, Graphics2D g2) {
            big.setColor(myBlack);

            for (int k = 0; k <= (ending - beginning); k++) {
                if ((xeNum + xcSize) > xlist.size()) {
                    xeNum = 0;
                } else {
                    xeNum += xcSize;
                }
                yeNum += ycSize;

                for (int i = xeNum; i < xeNum + xcSize && i < xlist.size(); i++) {
                    for (int j = yeNum; j < yeNum + ycSize && j < ylist.size(); j++) {
                        int xval = ((Integer) xlist.get(i)).intValue();
                        int yval = ((Integer) ylist.get(j)).intValue();
                        if (((xval % blocksize) == 0)
                                && ((yval % blocksize) == 0)) {
                            big.fillRect(xval, yval, blocksize, blocksize);
                        }
                    }
                }
            }

            g2.drawImage(bimg, 0, 0, null);
        }

        @Override
        public int getBegin() {
            return beginning;
        }

        @Override
        public int getEnd() {
            return ending;
        }
    }

    static class SiE implements Part {

        private int beginning, ending;
        private BufferedImage bimg;
        private double rIncr, sIncr;
        private double scale, rotate;
        private int siw, sih;
        private List<BufferedImage> subs = new Vector<BufferedImage>(20);
        private List<Point> pts = new Vector<Point>(20);

        public SiE(int siw, int sih, int beg, int end) {
            this.siw = siw;
            this.sih = sih;
            this.beginning = beg;
            this.ending = end;
            rIncr = 360.0 / (ending - beginning);
            sIncr = 1.0 / (ending - beginning);
        }

        @Override
        public void reset(int w, int h) {
            scale = 1.0;
            rotate = 0.0;
            bimg = null;
            subs.clear();
            pts.clear();
        }

        @Override
        public void step(int w, int h) {
            if (bimg == null) {
                int biw = Surface.bimg.getWidth();
                int bih = Surface.bimg.getHeight();
                bimg = new BufferedImage(biw, bih, BufferedImage.TYPE_INT_RGB);
                Graphics2D big = bimg.createGraphics();
                big.drawImage(Surface.bimg, 0, 0, null);
                for (int x = 0; x < w && scale > 0.0; x += siw) {
                    int ww = x + siw < w ? siw : w - x;
                    for (int y = 0; y < h; y += sih) {
                        int hh = y + sih < h ? sih : h - y;
                        subs.add(bimg.getSubimage(x, y, ww, hh));
                        pts.add(new Point(x, y));
                    }
                }
            }

            rotate += rIncr;
            scale -= sIncr;
        }

        @Override
        public void render(int w, int h, Graphics2D g2) {
            AffineTransform saveTx = g2.getTransform();
            g2.setColor(myBlue);
            for (int i = 0; i < subs.size() && scale > 0.0; i++) {
                BufferedImage bi = (BufferedImage) subs.get(i);
                Point p = (Point) pts.get(i);
                int ww = bi.getWidth();
                int hh = bi.getHeight();
                AffineTransform at = new AffineTransform();
                at.rotate(Math.toRadians(rotate), p.x + ww / 2, p.y + hh / 2);
                at.translate(p.x, p.y);
                at.scale(scale, scale);

                Rectangle b1 = new Rectangle(0, 0, ww, hh);
                Shape shape = at.createTransformedShape(b1);
                Rectangle2D b2 = shape.getBounds2D();
                double xx = (p.x + ww / 2) - (b2.getX() + b2.getWidth() / 2);
                double yy = (p.y + hh / 2) - (b2.getY() + b2.getHeight() / 2);
                AffineTransform toCenterAT = new AffineTransform();
                toCenterAT.translate(xx, yy);
                toCenterAT.concatenate(at);

                g2.setTransform(toCenterAT);
                g2.drawImage(bi, 0, 0, null);
                g2.draw(b1);
            }
            g2.setTransform(saveTx);
        }

        @Override
        public int getBegin() {
            return beginning;
        }

        @Override
        public int getEnd() {
            return ending;
        }
    }

    static class LnE implements Part {

        static final int INC = 1;
        static final int DEC = 2;
        static final int R = 4;             // rotate
        static final int ZOOM = 8;             // zoom
        static final int AC = 32;             // AlphaComposite
        static final int RI = R | INC;
        static final int RD = R | DEC;
        static final int ZOOMI = ZOOM | INC;
        static final int ZOOMD = ZOOM | DEC;
        static final int ACI = AC | INC;
        static final int ACD = AC | DEC;
        private int beginning, ending;
        private double rIncr, rotate;
        private double zIncr, zoom;
        private List<Point2D.Double> pts = new Vector<Point2D.Double>();
        private float alpha, aIncr;
        private int type;

        public LnE(int type, int beg, int end) {
            this.type = type;
            this.beginning = beg;
            this.ending = end;
            float range = ending - beginning;
            rIncr = 360.0f / range;
            aIncr = 0.9f / range;
            zIncr = 2.0f / range;
            if ((type & DEC) != 0) {
                rIncr = -rIncr;
                aIncr = -aIncr;
                zIncr = -zIncr;
            }
        }

        public void generatePts(int w, int h, double sizeF) {
            pts.clear();
            double size = Math.min(w, h) * sizeF;
            Ellipse2D ellipse = new Ellipse2D.Double(w / 2 - size / 2, h / 2 - size / 2, size, size);
            PathIterator pi = ellipse.getPathIterator(null, 0.8);
            while (!pi.isDone()) {
                double[] pt = new double[6];
                switch (pi.currentSegment(pt)) {
                    case FlatteningPathIterator.SEG_MOVETO:
                    case FlatteningPathIterator.SEG_LINETO:
                        pts.add(new Point2D.Double(pt[0], pt[1]));
                }
                pi.next();
            }
        }

        @Override
        public void reset(int w, int h) {
            if ((type & DEC) != 0) {
                rotate = 360;
                alpha = 1.0f;
                zoom = 2.0;
            } else {
                rotate = alpha = 0;
                zoom = 0;
            }
            if ((type & ZOOM) == 0) {
                generatePts(w, h, 0.5);
            }
        }

        @Override
        public void step(int w, int h) {
            if ((type & ZOOM) != 0) {
                generatePts(w, h, zoom += zIncr);
            }
            if ((type & RI) != 0 || (type & RI) != 0) {
                rotate += rIncr;
            }
            if ((type & ACI) != 0 || (type & ACD) != 0) {
                alpha += aIncr;
            }
        }

        @Override
        public void render(int w, int h, Graphics2D g2) {
            Composite saveAC = null;
            if ((type & AC) != 0 && alpha >= 0 && alpha <= 1) {
                saveAC = g2.getComposite();
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            }
            AffineTransform saveTx = null;
            if ((type & R) != 0) {
                saveTx = g2.getTransform();
                AffineTransform at = new AffineTransform();
                at.rotate(Math.toRadians(rotate), w / 2, h / 2);
                g2.setTransform(at);
            }
            Point2D p1 = new Point2D.Double(w / 2, h / 2);
            g2.setColor(YELLOW);
            for (Point2D pt : pts) {
                g2.draw(new Line2D.Float(p1, (Point2D) pt));
            }
            if (saveTx != null) {
                g2.setTransform(saveTx);
            }
            if (saveAC != null) {
                g2.setComposite(saveAC);
            }
        }

        @Override
        public int getBegin() {
            return beginning;
        }

        @Override
        public int getEnd() {
            return ending;
        }
    }

    static class Temp implements Part {

        static final int NOANIM = 1;
        static final int RECT = 2;
        static final int IMG = 4;
        static final int RNA = RECT | NOANIM;
        static final int INA = IMG | NOANIM;
        private int beginning, ending;
        private float alpha, aIncr;
        private int type;
        private Rectangle rect1, rect2;
        private int x, y, xIncr, yIncr;
        private Image img;

        public Temp(int type, Image img, int beg, int end) {
            this.type = type;
            this.img = img;
            this.beginning = beg;
            this.ending = end;
            aIncr = 0.9f / (ending - beginning);
            if ((type & NOANIM) != 0) {
                alpha = 1.0f;
            }
        }

        @Override
        public void reset(int w, int h) {
            rect1 = new Rectangle(8, 20, w - 20, 30);
            rect2 = new Rectangle(20, 8, 30, h - 20);
            if ((type & NOANIM) == 0) {
                alpha = 0.0f;
                xIncr = w / (ending - beginning);
                yIncr = h / (ending - beginning);
                x = w + (int) (xIncr * 1.4);
                y = h + (int) (yIncr * 1.4);
            }
        }

        @Override
        public void step(int w, int h) {
            if ((type & NOANIM) != 0) {
                return;
            }
            if ((type & RECT) != 0) {
                rect1.setLocation(x -= xIncr, 20);
                rect2.setLocation(20, y -= yIncr);
            }
            if ((type & IMG) != 0) {
                alpha += aIncr;
            }
        }

        @Override
        public void render(int w, int h, Graphics2D g2) {
            if ((type & RECT) != 0) {
                g2.setColor(myBlue);
                g2.fill(rect1);
                g2.setColor(myRed);
                g2.fill(rect2);
            }
            if ((type & IMG) != 0) {
                Composite saveAC = g2.getComposite();
                if (alpha >= 0 && alpha <= 1) {
                    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
                }
                g2.drawImage(img, 30, 30, null);
                g2.setComposite(saveAC);
            }
        }

        @Override
        public int getBegin() {
            return beginning;
        }

        @Override
        public int getEnd() {
            return ending;
        }
    }

    static class Features implements Part {

        static final int GRAPHICS = 0;
        static final int TEXT = 1;
        static final int IMAGES = 2;
        static final int COLOR = 3;
        static Font font1 = new Font("serif", Font.BOLD, 38);
        static Font font2 = new Font("serif", Font.PLAIN, 24);
        static FontMetrics fm1 = Surface.getMetrics(font1);
        static FontMetrics fm2 = Surface.getMetrics(font2);
        static String table[][] = {{"图形", "平滑渲染", "贝兹曲线路径",
                "变换", "影像合成", "拍打参数"},
            {"文本", "扩展字体支持",
                "高级字体布局", "动态字体载入",
                "个性字体属性设置"},
            {"图片", "灵活图片布局",
                "扩展图片操作",
                "卷曲, 下拉列表",
                "渲染图片接口"},
            {"颜色", "ICC profile support", "Color conversion",
                "Arbitrary color spaces"}};
        private String list[];
        private int beginning, ending;
        private int strH;
        private int endIndex, listIndex;
        private List<String> v = new Vector<String>();

        public Features(int type, int beg, int end) {
            list = table[type];
            this.beginning = beg;
            this.ending = end;
        }

        @Override
        public void reset(int w, int h) {
            strH = (int) (fm2.getAscent() + fm2.getDescent());
            endIndex = 1;
            listIndex = 0;
            v.clear();
            v.add(list[listIndex].substring(0, endIndex));
        }

        @Override
        public void step(int w, int h) {
            if (listIndex < list.length) {
                if (++endIndex > list[listIndex].length()) {
                    if (++listIndex < list.length) {
                        endIndex = 1;
                        v.add(list[listIndex].substring(0, endIndex));
                    }
                } else {
                    v.set(listIndex, list[listIndex].substring(0, endIndex));
                }
            }
        }

        @Override
        public void render(int w, int h, Graphics2D g2) {
            g2.setColor(myWhite);
            g2.setFont(font1);
            g2.drawString((String) v.get(0), 90, 85);
            g2.setFont(font2);
            for (int i = 1, y = 90; i < v.size(); i++) {
                g2.drawString((String) v.get(i), 120, y += strH);
            }
        }

        @Override
        public int getBegin() {
            return beginning;
        }

        @Override
        public int getEnd() {
            return ending;
        }
    }

    static class Contributors implements Part {

        static String members[] = {
            "Brian Lichtenwalter", "Jeannette Hung",
            "Thanh Nguyen", "Jim Graham", "Jerry Evans",
            "John Raley", "Michael Peirce", "Robert Kim",
            "Jennifer Ball", "Deborah Adair", "Paul Charlton",
            "Dmitry Feld", "Gregory Stone", "Richard Blanchard",
            "Link Perry", "Phil Race", "Vincent Hardy",
            "Parry Kejriwal", "Doug Felt", "Rekha Rangarajan",
            "Paula Patel", "Michael Bundschuh", "Joe Warzecha",
            "Joey Beheler", "Aastha Bhardwaj", "Daniel Rice",
            "Chris Campbell", "Shinsuke Fukuda", "Dmitri Trembovetski",
            "Chet Haase", "Jennifer Godinez", "Nicholas Talian",
            "Raul Vera", "Ankit Patel", "Ilya Bagrak",
            "Praveen Mohan", "Rakesh Menon"
        };
        static Font font = new Font("serif", Font.PLAIN, 26);
        static FontMetrics fm = Surface.getMetrics(font);
        private int beginning, ending;
        private int nStrs, strH, index, yh, height;
        private List<String> v = new Vector<String>();
        private List<String> cast = new Vector<String>(members.length + 3);
        private int counter, cntMod;
        private GradientPaint gp;

        public Contributors(int beg, int end) {
            this.beginning = beg;
            this.ending = end;
            java.util.Arrays.sort(members);
            cast.add("CONTRIBUTORS");
            cast.add(" ");
            cast.addAll(Arrays.asList(members));
            cast.add(" ");
            cast.add(" ");
            cntMod = (ending - beginning) / cast.size() - 1;
        }

        @Override
        public void reset(int w, int h) {
            v.clear();
            strH = (int) (fm.getAscent() + fm.getDescent());
            nStrs = (h - 40) / strH + 1;
            height = strH * (nStrs - 1) + 48;
            index = 0;
            gp = new GradientPaint(0, h / 2, WHITE, 0, h + 20, BLACK);
            counter = 0;
        }

        @Override
        public void step(int w, int h) {
            if (counter++ % cntMod == 0) {
                if (index < cast.size()) {
                    v.add(cast.get(index));
                }
                if ((v.size() == nStrs || index >= cast.size()) && !v.isEmpty()) {
                    v.remove(0);
                }
                ++index;
            }
        }

        @Override
        public void render(int w, int h, Graphics2D g2) {
            g2.setPaint(gp);
            g2.setFont(font);
            double remainder = counter % cntMod;
            double incr = 1.0 - remainder / cntMod;
            incr = incr == 1.0 ? 0 : incr;
            int y = (int) (incr * strH);

            if (index >= cast.size()) {
                y = yh + y;
            } else {
                y = yh = height - v.size() * strH + y;
            }
            for (String s : v) {
                g2.drawString(s, w / 2 - fm.stringWidth(s) / 2, y += strH);
            }
        }

        @Override
        public int getBegin() {
            return beginning;
        }

        @Override
        public int getEnd() {
            return ending;
        }
    }

    public static void main(String argv[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }
        JFrame f = new JFrame("二维绘图演示 - 简介");
        Surface surface;
        f.getContentPane().add("Center", surface = new Surface());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        int w = 720;
        int h = 510;
        f.setSize(w, h);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        surface.start();
    }
}
