package controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import entity.Administrator;
import entity.Customer;
import entity.Flower;
import service.CustomerService;
import service.FlowerService;

@Controller
@RequestMapping("/Flower")
public class FlowerController {
	@Autowired
	private FlowerService flower;
	
	@Autowired
	private CustomerService customerService;
	/*List<Customer> list=new ArrayList<Customer>();
	HttpSession session = null;
	int id=0;*/
	//����ֱ��������ʾҳ�棬���������������
	@RequestMapping("/")
	public String index() {
		return "flower/administrator";
	}
	
	@RequestMapping("/index")
	public String index1() {
		return "flower/index";
	}
	//��ַ�ض��壬���򵽿�����AccountController��·��
	@RequestMapping("/Login")
	public String login() {
		return "redirect:/Account/";
	}
	@RequestMapping("/Register")
	public String register() {
		return "redirect:/Account/register";
	}
	//Ϊ����ת����һ�����������˵�ҳ��
	@RequestMapping("/Bill")
	public String Bill() {
		return "redirect:/Customer/show";
	}
	//�鿴����Ա�ĺ�̨�˵���Ϣ
	@RequestMapping("/Boss")
	public String Boss() {
		return "redirect:/Customer/show1";
	}
	@RequestMapping("/AdministratorLogin")
	public String AdministratorLogin() {
		return "flower/administratorLogin";
	}
	@RequestMapping("/addFlower")
	public String AddFlower() {
		return "flower/addflower";
	}
	
	@RequestMapping("/FlowerInformation")
	public String FlowerInformation(Flower flower1,RedirectAttributes redirectAttributes) {
		boolean flag=flower.AddFlower(flower1);
		if(flag) {
			return "redirect:/Flower/FindAll";
		}
		else {
			return "redirect:/Flower/addFlower";
		}
	}
	//�޸Ļ�����Ϣ
	@RequestMapping("/Revise")
	public String revise() {
		//System.out.println("11111");
		return "flower/update";
	}
	@RequestMapping("/viewmore")
	public String viewmore() {
		return "flower/ViewMore";
	}
	
	@RequestMapping("/Logincheck")
	public String Logincheck(Administrator admini,RedirectAttributes redirectAttributes) {
		System.out.println(admini.getUsername());
		String username=admini.getUsername();
		String password=admini.getPassword();
		if(username.equals("123")&&password.equals("123"))
		{
			return "redirect:/Flower/FindAll";
		}
		else {
			redirectAttributes.addFlashAttribute("errorMsg","����������˺�����");
			System.out.println("Administrator��¼����");
			return "redirect:/Flower/AdministratorLogin";
		}
	}
	@RequestMapping("/ViewMore")
	public String ViewMore(Flower flower1,RedirectAttributes redirectAttributes) {	
		Flower newflower=flower.FindFlowerById(flower1.getId());
		System.out.println(newflower.getId());
		System.out.println(newflower.getName());
		redirectAttributes.addFlashAttribute("newflower", newflower);
		return "redirect:/Flower/viewmore";
	}
	//�������ֲ��һ�
	@RequestMapping("/Find")
	public String Find(Flower fl,RedirectAttributes redirectAttributes) {
		String name=fl.getName();
		Flower f=flower.FindFlowerByName(name);
		List<Flower> list=new ArrayList<Flower>();
		list.add(f);
		if(f!=null) {
			redirectAttributes.addFlashAttribute("list", list);
			return "redirect:/Flower/index";
		}
		else {
			return "redirect:/Flower/";
		}
	}
	//ǰ̨ҳ����ʾ���ݵĲ���
	@RequestMapping("/ListAllFlower")
	public String ListAllFlower(RedirectAttributes redirectAttributes) {
		
		List<Flower> list=new ArrayList<Flower>();
		list=flower.FindAllFlower();
		redirectAttributes.addFlashAttribute("list", list);
		return "redirect:/Flower/index";
	}
	//��ӵ����ﳵ
	@RequestMapping("/Customer")
	public String customer(Flower flower1) {
		int check=0;
		Flower newflower=flower.FindFlowerById(flower1.getId());
		Customer customer=new Customer();
		customer.setFlower_id(newflower.getId());
		customer.setFlower_name(newflower.getName());
		customer.setFlower_price(newflower.getPrice());
		customer.setUpdateStatus(check);
		customerService.add(customer);
		
		return "redirect:/Flower/ListAllFlower";
	}
	
	//�������еĻ�,��̨����ҳ��Ĳ�ѯ
	@RequestMapping("/FindAll")
	public String FindAll(RedirectAttributes redirectAttributes) {
		List<Flower> list=new ArrayList<Flower>();
		list=flower.FindAllFlower();
		redirectAttributes.addFlashAttribute("list", list);
		return "redirect:/Flower/";
	}
	//����idɾ������Ȼ����ת����ʾҳ��index
	@RequestMapping("/Delete")
	public String Delete(int id,RedirectAttributes redirectAttributes) {
		//int ID=Integer.parseInt(id);
		boolean flag=flower.DeleteFlower(id);
		if(flag) {
			return "redirect:/Flower/FindAll";
		}
		else {
			//û��д���������ʾҳ�棬���ڼ���
			System.out.println("ͨ��idɾ������");
			return "redirect:/Flower/FindAll";
		}
	}
	//����������Ҫ���»��������ͼ۸񣬿ͻ�����֮�����ٻ���������ֱ������Ϊ0��Ȼ��ͱ���ɾ����һ��Ļ�
	@RequestMapping("/Update")
	public String Update(Flower flower1,RedirectAttributes redirectAttributes)
	{
		//flower1ֻ��idһ�������ݣ��������ݽ�Ϊ�գ���ǰ̨������Ȼ���Զ���װ���������
		//mapper�ӿڲ��UpdateFlowerֻ�ܴ�һ��������ݣ����������Ҫ��װ�£�һ�����ݾͲ���Ҫ��
		Flower newflower=flower.FindFlowerById(flower1.getId());
		
		//System.out.println(flower1.getId());
		//System.out.println(newflower.getId()+";"+newflower.getPrice());
	
		redirectAttributes.addFlashAttribute("newflower",newflower);
		return "redirect:/Flower/Revise";
	
	}
	//����update���������ݣ����޸����ݿ�����
	@RequestMapping("/newUpdate")
	public String newUpdate(Flower flower1,RedirectAttributes redirectAttributes)
	{
		boolean flag=flower.UpdateFlower(flower1);
		System.out.println(flower1.getPrice()+","+flower1.getNumber());
		if(flag) {
			return "redirect:/Flower/FindAll";
		}
		else {
			System.out.println("newupdate���´���");
			return "redirect:/Flower/FindAll";
		}
	}
	
	@RequestMapping("/add")
	public String add() {
		return "flower/addflower";
	}

}
