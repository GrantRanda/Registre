package gr.registre.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    Logger logger = Logger.getLogger(getClass().getName());

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = Integer.valueOf(request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE).toString());
        model.addAttribute("status", status);

        logger.severe("Error: " + status);

        return "error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
