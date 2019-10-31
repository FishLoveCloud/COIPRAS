package edu.seu.service;

import edu.seu.base.AuthorityEnum;
import edu.seu.base.CodeEnum;
import edu.seu.base.PageInfo;
import edu.seu.base.Pagination;
import edu.seu.dao.LoginTicketDAO;
import edu.seu.dao.UserDAO;
import edu.seu.exceptions.COIPRASExceptions;
import edu.seu.model.HostHolder;
import edu.seu.model.LoginTicket;
import edu.seu.model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private LoginTicketDAO loginTicketDAO;

    @Autowired
    private HostHolder hostHolder;

    public String register(User user, String oldEmail, String codeCaptcha, String emailCaptcha,
                           String oldCodeCaptcha,  String oldEmailCaptcha) throws COIPRASExceptions {
        checkBeforeRegister(user, oldEmail, codeCaptcha, emailCaptcha, oldCodeCaptcha, oldEmailCaptcha);

        userDAO.persist(user);

        return addLoginTicket(user.getId());

    }

    public String login(String nameEmail, String password, String codeCaptcha, String oldCodeCaptcha) throws COIPRASExceptions {
        checkBeforeLogin(nameEmail, password, codeCaptcha, oldCodeCaptcha);

        User old1 = userDAO.selectByName(nameEmail);
        User old2 = userDAO.selectByEmail(nameEmail);
        User old = old1 != null ? old1 : old2;
        if (old == null) {
            throw new COIPRASExceptions(CodeEnum.USER_ERROR, "该用户名/邮箱尚未注册！");
        }
        if (!StringUtils.equals(old.getPassword(), password)) {
            throw new COIPRASExceptions(CodeEnum.USER_ERROR, "密码错误！");
        }
        return addLoginTicket(old.getId());
    }

    private void checkBeforeRegister(User user, String oldEmail, String codeCaptcha, String emailCaptcha,
                                     String oldCodeCaptcha,  String oldEmailCaptcha) throws COIPRASExceptions{
        if (StringUtils.isBlank(user.getName())) {
            throw new COIPRASExceptions(CodeEnum.USER_ERROR, "用户名不能为空！");
        }
        if (StringUtils.isBlank(user.getPassword())) {
            throw new COIPRASExceptions(CodeEnum.USER_ERROR, "密码不能为空！");
        }
        if (StringUtils.isBlank(user.getEmail()) || !EmailService.isMail(user.getEmail())) {
            throw new COIPRASExceptions(CodeEnum.USER_ERROR, "邮箱格式错误！");
        }
        if (!StringUtils.equals(codeCaptcha, oldCodeCaptcha)) {
            throw new COIPRASExceptions(CodeEnum.USER_ERROR, "验证码错误！");
        }
        if (!StringUtils.equals(user.getEmail(), oldEmail)) {
            throw new COIPRASExceptions(CodeEnum.USER_ERROR, "邮箱已更换，需要重新发送验证码！");
        }
        if (!StringUtils.equals(emailCaptcha, oldEmailCaptcha)) {
            throw new COIPRASExceptions(CodeEnum.USER_ERROR, "邮箱验证码错误！");
        }
        User old = userDAO.selectByName(user.getName());
        if (old != null) {
            throw new COIPRASExceptions(CodeEnum.USER_ERROR, "该用户名已被注册！");
        }
        old = userDAO.selectByEmail(user.getEmail());
        if (old != null) {
            throw new COIPRASExceptions(CodeEnum.USER_ERROR, "该邮箱已被注册！");
        }

    }

    private void checkBeforeLogin(String nameEmail, String password, String codeCaptcha, String oldCodeCaptcha) throws COIPRASExceptions {

        if (StringUtils.isBlank(nameEmail)) {
            throw new COIPRASExceptions(CodeEnum.USER_ERROR, "用户名/邮箱不能为空！");
        }
        if (StringUtils.isBlank(password)) {
            throw new COIPRASExceptions(CodeEnum.USER_ERROR, "密码不能为空！");
        }
        if (!StringUtils.equals(codeCaptcha, oldCodeCaptcha)) {
            throw new COIPRASExceptions(CodeEnum.USER_ERROR, "验证码错误！");
        }
    }

    public void checkBeforeEmailCaptcha(String email, String codeCaptcha, String oldCodeCaptcha) throws COIPRASExceptions{
        if (StringUtils.isBlank(email) || !EmailService.isMail(email)) {
            throw new COIPRASExceptions(CodeEnum.USER_ERROR, "邮箱格式错误！");
        }
        if (!StringUtils.equals(codeCaptcha, oldCodeCaptcha)) {
            throw new COIPRASExceptions(CodeEnum.USER_ERROR, "验证码错误！");
        }
    }

    private String addLoginTicket(int userId) {
        LoginTicket ticket = new LoginTicket();
        ticket.setUserId(userId);
        Date date = new Date();
        // 一天过期
        date.setTime(date.getTime() + 3600*24*1000);
        ticket.setExpireTime(new Timestamp(date.getTime()));
        ticket.setStatus(0);
        ticket.setTicket(UUID.randomUUID().toString().replaceAll("-", ""));
        loginTicketDAO.persist(ticket);
        return ticket.getTicket();
    }

    public void logout(String ticket) {
        loginTicketDAO.updateStatus(ticket,1);
    }


    public Map<String, Object> buildEmailCaptcha(String email, String emailCaptcha, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("to", email);
        map.put("subject", "OICPRAS-验证码");
        String text = "<html><p><h3>验证码：" + emailCaptcha + "</h3></p></html>";
        map.put("text", text);
        return map;
    }

    public void modifyPassword(String email, String newPassword, String confirmPassword, String codeCaptcha, String oldCodeCaptcha) throws COIPRASExceptions{
        checkBeforeModifyPassword(email, newPassword, confirmPassword, codeCaptcha, oldCodeCaptcha);

        userDAO.updatePassword(email, newPassword);
    }

    private void checkBeforeModifyPassword(String email,String newPassword, String confirmPassword,
                                           String codeCaptcha, String oldCodeCaptcha) throws COIPRASExceptions{

        if(StringUtils.isEmpty(email)){
           throw new COIPRASExceptions(CodeEnum.USER_ERROR, "邮箱不能为空！");
        }
        if(!StringUtils.equals(newPassword, confirmPassword)){
            throw new COIPRASExceptions(CodeEnum.USER_ERROR, "输入密码不一致！");
        }
        if(!StringUtils.equals(codeCaptcha, oldCodeCaptcha)){
            throw new COIPRASExceptions(CodeEnum.USER_ERROR, "验证码错误");
        }
    }

    // 验证找回密码页
    public void checkBeforeFindPassword(String codeCaptcha, String oldCodeCaptcha, String emailCaptcha, String oldEmailCaptcha) throws COIPRASExceptions{
        if(!StringUtils.equals(codeCaptcha, oldCodeCaptcha)){
            throw new COIPRASExceptions(CodeEnum.USER_ERROR, "验证码错误!");
        }
        if(!StringUtils.equals(emailCaptcha, oldEmailCaptcha)){
            throw new COIPRASExceptions(CodeEnum.USER_ERROR, "邮箱验证码错误!");
        }
    }

    public void checkEmail(String email) throws COIPRASExceptions{
        User user = userDAO.selectByEmail(email);
        if(user == null){
            throw new COIPRASExceptions(CodeEnum.USER_ERROR, "该邮箱尚未注册！");
        }
    }

    /**
     * 是否管理员登录
     */
    public void adminAuth() throws COIPRASExceptions {
        User user = hostHolder.getUser();
        if (user == null || user.getLevel() != AuthorityEnum.ADMIN.getValue()) {
            throw new COIPRASExceptions(CodeEnum.USER_ERROR, "此操作需要管理员权限！");
        }
    }

    public void updatePassword(String oldPassword, String newPassword, String codeCaptcha,
                               String oldCodeCaptcha, String ticket) throws COIPRASExceptions{
        User user = hostHolder.getUser();
        if(!StringUtils.equals(user.getPassword(), oldPassword)){
            throw new COIPRASExceptions(CodeEnum.USER_ERROR, "旧密码输入有误");
        }
        if(!StringUtils.equals(codeCaptcha, oldCodeCaptcha)){
            throw new COIPRASExceptions(CodeEnum.USER_ERROR, "验证码错误!");
        }
        userDAO.updatePassword(user.getEmail(), newPassword);
        logout(ticket);

    }

//    public void grantVIP(Integer id) throws COIPRASExceptions {
//        adminAuth();
//
//        if (id == null) {
//            throw new COIPRASExceptions(CodeEnum.USER_ERROR, "参数不能为空！");
//        }
//        User user = userDAO.selectById(id);
//        if (user == null) {
//            throw new COIPRASExceptions(CodeEnum.USER_ERROR, "用户不存在！");
//        }
//        user.setLevel(AuthorityEnum.VIP.getValue());
//        userDAO.update(user);
//    }

    public Pagination<User> queryAllUser(Integer page) throws COIPRASExceptions{
        adminAuth();

        if (page == null) {
            page = 1;
        }
        Integer totalRow = userDAO.countAllUser();
        PageInfo pageInfo = new PageInfo(totalRow, page);
        List<User> userList = userDAO.selectAll(pageInfo.getBeginIndex(), pageInfo.getEndIndex());
        return new Pagination<User>(userList, pageInfo);


    }
}
