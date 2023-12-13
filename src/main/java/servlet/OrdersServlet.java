package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.OrderService;
import utils.JspHelper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/orders")
public class OrdersServlet extends HttpServlet {
    private final OrderService orderService = OrderService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        req.setAttribute("orders", orderService.findAll());
        req.getRequestDispatcher(JspHelper.getPath("orders")).forward(req, resp);

//        try (var writer = resp.getWriter()) {
//            writer.write("<h1>Список заказов</h1>");
//            writer.write("<u>");
//            orderService.findAll().stream().forEach(orderDto -> writer.write("""
//                    <li>
//                    %s %s %s - %s - %d
//                    </li>
//                    """.formatted(orderDto.getCreated(), orderDto.getType(), orderDto.getModel(),
//                    orderDto.getProblem(), orderDto.getSum())));
//            writer.write("</u>");
//        }

    }
}
