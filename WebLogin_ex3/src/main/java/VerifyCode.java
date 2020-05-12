import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/get_verifycode")
public class VerifyCode extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int width = 80, height = 40;
        //创建图片
        BufferedImage img= new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //获取画布
        Graphics2D gp = (Graphics2D) img.getGraphics();
          //设置画布的背景色和边框
        gp.setBackground(Color.LIGHT_GRAY);
        gp.fillRect(0, 0, width, height);
        gp.setColor(Color.black);
        gp.drawRect(0, 0, width - 1, height - 1);

        //生成验证码图片
          //生成四位随机数
        StringBuilder vcode = new StringBuilder();
        Random random = new Random();
        int style = random.nextInt(4);//生成随机的样式, 0(无样式), 1(粗体), 2(斜体), 3(粗体+斜体)
        int size = random.nextInt(5) + 24; //生成随机字号, 24 ~ 28
        Font f = new Font("宋体", style, size);
        gp.setFont(f);
        for(int i = 0; i < 4; i++){
            String temp = String.valueOf(random.nextInt(10));
            vcode.append(temp);
            gp.setColor(getRandColor(100,150));
            gp.drawString(temp, 6 + 18 * i, 30);
        }
            //生成干扰线
        gp.setColor(getRandColor(100,150));
        for (int i = 0; i < 5; i++) {
            int x1 = new Random().nextInt(width);
            int y1 = new Random().nextInt(height);
            int x2 = new Random().nextInt(width);
            int y2 = new Random().nextInt(height);
            gp.drawLine(x1, y1, x2, y2);
        }
        gp.dispose();
          //设置响应头，告诉浏览器不缓存该图片，便于刷新时更新验证码
        response.setHeader("Content-Type", "image/jpeg");
        response.setDateHeader("expries", -1);
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
          //将图片输出至网页上
        ImageIO.write(img,"jpg",response.getOutputStream());

        //将验证码String添加至session中
        request.getSession().setAttribute("session_vcode", vcode.toString());
    }

    //生成随机颜色
    public Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}
