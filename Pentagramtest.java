package school;
import javax.swing.*;
import java.awt.*;

public class Pentagramtest {
    public static void main(String[] args) {
        Pentagram pentagram = new Pentagram(5);
        JFrame frame = new JFrame();
        frame.setSize(400, 400);//设置对象大小
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//如何关闭
        frame.add(pentagram);//输出到内容面板
        frame.setVisible(true);//是将 frame 对象设置为可见。
        pentagram.setLocation(50,50);//更改位置
        System.out.println("五角星的周长：" + pentagram.perimeter());
        System.out.println("五角星的面积：" + pentagram.area());

    }
}

class Pentagram extends JPanel {
    private static final long serialVersionUID = 1L;
    private int n; // 五角星边数，5为标准
    private int r = 100; // 半径，初始值为100
    private int[] x; // 多边形各个顶点的x坐标
    private int[] y; // 多边形各个顶点的y坐标

    /* 构造函数
     * 传入五角星边数n，计算各个顶点的坐标并初始化
     */
    public Pentagram(int n) {
        this.n = n;
        x = new int[2 * n];
        y = new int[2 * n];
        calculateVertices();
    }

    /* paintComponent方法
     * 重写JPanel的绘制方法，画出五角星
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.RED);//这里可以更改颜色，要大写的
        g.fillPolygon(x, y, 2 * n);
    }

    /* 计算顶点坐标
     * 根据五角星的边数和初始半径，计算出各个顶点在JPanel中的坐标
     */
    private void calculateVertices() {
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        for (int i = 0; i < 2 * n; i++) {
            double angle = Math.PI / n + i * Math.PI / n;
            double length = i % 2 == 0 ? r : r / 2;
            x[i] = (int) (centerX + length * Math.cos(angle));
            y[i] = (int) (centerY + length * Math.sin(angle));
        }
    }

    /* 计算五角星周长
     * 根据首尾两个顶点计算五角星的周长
     */
    public double perimeter() {
        double length = Math.sqrt(Math.pow(x[0]-x[1],2)+Math.pow(y[0]-y[1],2));
        return 5 * length;
    }

    /* 计算五角星面积
     * 根据首尾两个顶点计算边长length，再根据length计算五角星的内心距apothem，
     * 最终计算出五角星的面积
     */
    public double area() {
        double length = Math.sqrt(Math.pow(x[0]-x[1],2)+Math.pow(y[0]-y[1],2));
        double apothem = length / (2 * Math.tan(Math.PI / 5));
        return (5 * length * apothem) / 2;
    }

    /* 更新五角星位置
     * 根据传入的x、y坐标，更新当前五角星在JPanel中的位置
     */
    public void setLocation(int x, int y) {
        this.setBounds(x, y, this.getWidth(), this.getHeight());
        calculateVertices();
    }
}

