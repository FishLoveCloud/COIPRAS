package edu.seu.controller;

import edu.seu.async.EventConsumer;
import edu.seu.async.EventModel;
import edu.seu.async.EventType;
import edu.seu.base.CodeEnum;
import edu.seu.base.CommonResponse;
import edu.seu.base.Pagination;
import edu.seu.exceptions.COIPRASExceptions;
import edu.seu.model.User;
import edu.seu.service.CaptchaService;
import edu.seu.service.EmailService;
import edu.seu.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@RequestMapping("/reglogin")
@Controller
public class LoginController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    UserService userService;

    @Autowired
    EmailService emailService;

    @Autowired
    EventConsumer eventConsumer;

    @Autowired
    CaptchaService captchaService;

    @ResponseBody
    @RequestMapping("/register")
    public String register(User user, String codeCaptcha, String emailCaptcha, HttpServletRequest request, HttpServletResponse response) {
        try {
            user.setName(user.getName().trim());
            user.setEmail(user.getEmail().trim());
            String oldCodeCaptcha = (String)request.getSession().getAttribute("codeCaptcha");
            String oldEmailCaptcha = (String)request.getSession().getAttribute("emailCaptcha");
            String oldEmail = (String)request.getSession().getAttribute("email");
            String ticket = userService.register(user, oldEmail, codeCaptcha, emailCaptcha, oldCodeCaptcha, oldEmailCaptcha);
            addCookie(ticket, response);
            return new CommonResponse(CodeEnum.SUCCESS.getValue(), "注册成功").toJSONString();
        } catch (COIPRASExceptions e) {
            LOGGER.info(e.getMessage() + " parameter:user={}, oldEmail={}, codeCaptcha={}, emailCaptcha={}, oldCodeCaptcha={}, oldEmailCaptcha={}", user,
                    request.getSession().getAttribute("email"),
                    codeCaptcha, emailCaptcha, request.getSession().getAttribute("codeCaptcha"),
                    request.getSession().getAttribute("emailCaptcha"));
            return new CommonResponse(e.getCodeEnum().getValue(), e.getMessage()).toJSONString();
        } catch (Exception e) {
            LOGGER.error("/reglogin/register parameter:user={}, oldEmail={}, codeCaptcha={}, emailCaptcha={}, oldCodeCaptcha={}, oldEmailCaptcha={}", user,
                    request.getSession().getAttribute("email"),
                    codeCaptcha, emailCaptcha, request.getSession().getAttribute("codeCaptcha"),
                    request.getSession().getAttribute("emailCaptcha"), e);
            return new CommonResponse(CodeEnum.UNKNOWN_ERROR.getValue(), e.getMessage()).toJSONString();
        }
    }

    @ResponseBody
    @RequestMapping("/login")
    public String login(String nameEmail, String password, String codeCaptcha, HttpServletRequest request, HttpServletResponse response) {
        try {
            nameEmail = nameEmail.trim();
            String oldCodeCaptcha = (String) request.getSession().getAttribute("codeCaptcha");
            String ticket = userService.login(nameEmail, password, codeCaptcha, oldCodeCaptcha);
            addCookie(ticket, response);
            return new CommonResponse(CodeEnum.SUCCESS.getValue(), "登录成功").toJSONString();
        } catch (COIPRASExceptions e) {
            LOGGER.info(e.getMessage() + " parameter:nameEmail={}, password={}, codeCaptcha={}, oldCodeCaptcha={}",
                    nameEmail, password, codeCaptcha, request.getSession().getAttribute("codeCaptcha"));
            return new CommonResponse(e.getCodeEnum().getValue(), e.getMessage()).toJSONString();
        } catch (Exception e) {
            LOGGER.error("/reglogin/login parameter:nameEmail={}, password={}, codeCaptcha={}, oldCodeCaptcha={}",
                    nameEmail, password, codeCaptcha, request.getSession().getAttribute("codeCaptcha"), e);
            return new CommonResponse(CodeEnum.UNKNOWN_ERROR.getValue(), e.getMessage()).toJSONString();
        }

    }

    private void addCookie(String ticket, HttpServletResponse response) {
        Cookie cookie = new Cookie("ticket", ticket);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    @ResponseBody
    @RequestMapping("/logout")
    public String logout(@CookieValue("ticket") String ticket) {
        try {
            userService.logout(ticket);
            return new CommonResponse(CodeEnum.SUCCESS.getValue(), "退出成功").toJSONString();
        } catch (Exception e) {
            LOGGER.error("/reglogin/logout parameter:ticket={}", ticket, e);
            return new CommonResponse(CodeEnum.UNKNOWN_ERROR.getValue(), e.getMessage()).toJSONString();
        }
    }

    @RequestMapping("/codeCaptcha")
    @ResponseBody
    public String codeCaptcha(HttpServletRequest request) {
        try {
            request.getSession().removeAttribute("codeCaptcha");
            String codeCaptcha = captchaService.genCaptcha();
            request.getSession().setAttribute("codeCaptcha", codeCaptcha);
            String image = captchaService.getImageString(codeCaptcha);
            HashMap<String, Object> data = new HashMap<>();
            data.put("image", image);
            return new CommonResponse(CodeEnum.SUCCESS.getValue(), "验证码生成成功", data).toJSONString();
        } catch (Exception e) {
            LOGGER.error("/reglogin/codeCaptcha", e);
            return new CommonResponse(CodeEnum.USER_ERROR.getValue(), e.getMessage()).toJSONString();
        }
    }

    @RequestMapping("/modifyPassword")
    @ResponseBody
    public String modifyPassword(String newPassword, String confirmPassword, String codeCaptcha, HttpServletRequest request) {
        try {
            String email = (String)request.getSession().getAttribute("email");
            String oldCodeCaptcha = (String) request.getSession().getAttribute("codeCaptcha");
            userService.modifyPassword(email, newPassword, confirmPassword, codeCaptcha, oldCodeCaptcha);
            return new CommonResponse(CodeEnum.SUCCESS.getValue(), "修改密码成功").toJSONString();
        } catch (COIPRASExceptions e) {
            LOGGER.info(e.getMessage() + " parameter:newPassword={}, confirmPassword={}, codeCaptcha={}, oldCodeCaptcha={}",
                    newPassword, confirmPassword, codeCaptcha, request.getSession().getAttribute("codeCaptcha"));
            return new CommonResponse(e.getCodeEnum().getValue(), e.getMessage()).toJSONString();
        } catch (Exception e) {
            LOGGER.error("/reglogin/modifyPassword parameter:newPassword={}, confirmPassword={}, codeCaptcha={}, oldCodeCaptcha={}",
                    newPassword, confirmPassword, codeCaptcha, request.getSession().getAttribute("codeCaptcha"), e);
            return new CommonResponse(CodeEnum.UNKNOWN_ERROR.getValue(), e.getMessage()).toJSONString();
        }

    }

    @RequestMapping("/emailCaptcha")
    @ResponseBody
    public String emailCaptcha(String email, String codeCaptcha, HttpServletRequest request) {
        try {
            email = email.trim();
            String oldCodeCaptcha = (String)request.getSession().getAttribute("codeCaptcha");
            userService.checkBeforeEmailCaptcha(email, codeCaptcha, oldCodeCaptcha);
            request.getSession().removeAttribute("emailCaptcha");
            request.getSession().removeAttribute("email");
            String emailCaptcha = captchaService.genCaptcha();
            request.getSession().setAttribute("emailCaptcha", emailCaptcha);
            request.getSession().setAttribute("email", email);
            EventModel eventModel = new EventModel(EventType.EMAIL, userService.buildEmailCaptcha(email, emailCaptcha, request));
            eventConsumer.submit(eventModel);
            return new CommonResponse(CodeEnum.SUCCESS.getValue(), "邮箱验证码发送成功").toJSONString();
        } catch (COIPRASExceptions e) {
            LOGGER.info(e.getMessage() + " parameter:email={},codeCaptcha={}, oldCodeCaptcha={}", email, codeCaptcha,
                    request.getSession().getAttribute("codeCaptcha"));
            return new CommonResponse(e.getCodeEnum().getValue(), e.getMessage()).toJSONString();
        } catch (Exception e) {
            LOGGER.error("/reglogin/emailCaptcha parameter:email={},codeCaptcha={}, oldCodeCaptcha={}", email, codeCaptcha,
                    request.getSession().getAttribute("codeCaptcha"), e);
            return new CommonResponse(CodeEnum.UNKNOWN_ERROR.getValue(), e.getMessage()).toJSONString();
        }
    }

    @RequestMapping("/findPassword")
    @ResponseBody
    public String findPassword(String email, String codeCaptcha, String emailCaptcha, HttpServletRequest request){
        try{
            userService.checkEmail(email);
            String oldCodeCaptcha = (String) request.getSession().getAttribute("codeCaptcha");
            String oldEmailCaptcha = (String)request.getSession().getAttribute("emailCaptcha");
            userService.checkBeforeFindPassword(codeCaptcha, oldCodeCaptcha, emailCaptcha, oldEmailCaptcha);
            return new CommonResponse(CodeEnum.SUCCESS.getValue(), "找回密码成功").toJSONString();
        }catch (COIPRASExceptions e) {
            LOGGER.info(e.getMessage() + " parameter:email={}, codeCaptcha={}, emailCaptcha={}, oldCodeCaptcha={}, oldEmailCaptcha={}",
                    email, codeCaptcha, emailCaptcha, request.getSession().getAttribute("codeCaptcha"),
                    request.getSession().getAttribute("emailCaptcha"));
            return new CommonResponse(e.getCodeEnum().getValue(), e.getMessage()).toJSONString();
        } catch (Exception e) {
            LOGGER.error("/reglogin/findPassword parameter:email={}, codeCaptcha={}, emailCaptcha={}, oldCodeCaptcha={}, oldEmailCaptcha={}",
                    email, codeCaptcha, emailCaptcha, request.getSession().getAttribute("codeCaptcha"),
                    request.getSession().getAttribute("emailCaptcha"), e);
            return new CommonResponse(CodeEnum.UNKNOWN_ERROR.getValue(), e.getMessage()).toJSONString();
        }
    }

    @RequestMapping("/updatePassword")
    @ResponseBody
    public String updatePassword(String newPassword, String oldPassword, String codeCaptcha,
                                 @CookieValue("ticket") String ticket, HttpServletRequest request) {
        try {
            String oldCodeCaptcha = (String) request.getSession().getAttribute("codeCaptcha");
            userService.updatePassword(oldPassword, newPassword, codeCaptcha, oldCodeCaptcha, ticket);
            return new CommonResponse(CodeEnum.SUCCESS.getValue(), "修改密码成功").toJSONString();
        } catch (COIPRASExceptions e) {
            LOGGER.info(e.getMessage() + " parameter:oldPassword={}, newPassword={}, codeCaptcha={}, oldCodeCaptcha={}",
                    oldPassword, newPassword, codeCaptcha, request.getSession().getAttribute("codeCaptcha"));
            return new CommonResponse(e.getCodeEnum().getValue(), e.getMessage()).toJSONString();
        } catch (Exception e) {
            LOGGER.error("/reglogin/modifyPassword parameter:oldPassword={}, newPassword={}, codeCaptcha={}, oldCodeCaptcha={}",
                    oldPassword, newPassword, codeCaptcha, request.getSession().getAttribute("codeCaptcha"), e);
            return new CommonResponse(CodeEnum.UNKNOWN_ERROR.getValue(), e.getMessage()).toJSONString();
        }

    }

//    @RequestMapping("grantVIP")
//    @ResponseBody
//    public String grantVIP(Integer id) {
//        try {
//            userService.grantVIP(id);
//            return new CommonResponse(CodeEnum.SUCCESS.getValue(), "授予VIP成功").toJSONString();
//        } catch (COIPRASExceptions e) {
//            LOGGER.info(e.getMessage() + " parameter:id={}", id);
//            return new CommonResponse(e.getCodeEnum().getValue(), e.getMessage()).toJSONString();
//        } catch (Exception e) {
//            LOGGER.error("/reglogin/grantVIP parameter:id={}", id, e);
//            return new CommonResponse(CodeEnum.UNKNOWN_ERROR.getValue(), e.getMessage()).toJSONString();
//        }
//    }

    /**
     *
     * @param page 默认为1
     * @return
     */
    @RequestMapping("/showAllUser")
    @ResponseBody
    public String showAllUser(Integer page) {
        try {
            Pagination<User> pagination = userService.queryAllUser(page);
            HashMap<String, Object> data = new HashMap<>();
            data.put("pagination", pagination);
            return new CommonResponse(CodeEnum.SUCCESS.getValue(), "显示所有用户", data).toJSONString();
        } catch (COIPRASExceptions e) {
            LOGGER.info(e.getMessage() + " parameter:page={}", page);
            return new CommonResponse(e.getCodeEnum().getValue(), e.getMessage()).toJSONString();
        }  catch (Exception e) {
            LOGGER.error("/reglogin/showAllUser" + " parameter:page={}", page, e);
            return new CommonResponse(CodeEnum.USER_ERROR.getValue(), e.getMessage()).toJSONString();
        }
    }
}
