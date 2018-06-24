package controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import entity.User;
import service.UserService;

@Controller
@RequestMapping("/Account")
public class AccountController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/")
	public String index() {
		//System.out.println("Account");
		return "account/login";
	}
	
	@RequestMapping("/register")
	public String register() {
		return "account/register";
	}
	
	@RequestMapping("/update")
	public String update() {
		return "account/update";
	}
	
	@RequestMapping("/Login")
	public String login(User user,RedirectAttributes redirectAttributes) {
		//�����ַ�����user���г������⣿���������������������������������
		//System.out.println(user.getUsername()+":"+user.getPassword());
		boolean flag=userService.login(user);
		if(flag) {
			return "redirect:/Flower/ListAllFlower";
		}
		else {
			redirectAttributes.addFlashAttribute("errorMsg", "�û������������");
			return "redirect:/Account/";
		}
	}
	
	@RequestMapping("/Register")
	public String register(User user,RedirectAttributes redirectAttributes) {
		boolean flag=userService.findUser(user.getUsername());
		if(flag) {
			redirectAttributes.addFlashAttribute("errorMsg", "�û��Ѵ���");
			return "redirect:/Account/";
		}
		else {
			boolean add_flag=userService.register(user);
			//�����ַ�����user���г������⣿���������������������������������
			//System.out.println(user.getAdress());
			if(add_flag) {
				//redirectAttributes.addFlashAttribute("user",user);
				return "redirect:/Flower/ListAllFlower";
			}
			redirectAttributes.addFlashAttribute("errorMsg", "ע��ʧ��");
			return "redirect:/Account/register";
		}
	}
	
	@RequestMapping("/Update")
	public String Update(User user,RedirectAttributes redirectAttributes) {
		boolean flag=userService.findUser(user.getUsername());
		if(flag) {
			redirectAttributes.addFlashAttribute("errorMsg", "�û��Ѵ���");
			return "redirect:/Account/update";
		}
		else {
			boolean update_flag=userService.Update(user);
			if(update_flag) {
				return "redirect:/Account/login";
			}
			else {
				redirectAttributes.addFlashAttribute("errorMsg", "ע��ʧ��");
				return "redirect:/Account/login";
			}
		}
	}
	
	@RequestMapping("/FindAllUser")
	public String FindAllUser(RedirectAttributes redirectAttributes) {
		List<User> list=new ArrayList<User>();
		list=userService.getAllUser();
		System.out.println((list!=null));
		redirectAttributes.addFlashAttribute("list",list);
		return "redirect:/Account/Welcome";
	}
}
