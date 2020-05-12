import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginProcess extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //获取用户输入的用户名、密码、验证码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String vcode_input = request.getParameter("verifycode");
        //从session中获取正确的验证码
        String vcode_check = (String) request.getSession().getAttribute("session_vcode");
        //检查
        if(vcode_check.equals(vcode_input)){
            if(username.equals("admin") && password.equals("root")){
                response.sendRedirect(request.getContextPath() + "/home.jsp");
            }
            else{
                request.setAttribute("error","用户名或密码错误");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        }
        else{
            request.setAttribute("error","验证码输入错误");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
}
