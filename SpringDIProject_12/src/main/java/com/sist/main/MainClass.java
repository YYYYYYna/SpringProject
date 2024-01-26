package com.sist.main;
/* 
 *   ���ǻ��� : ������̼��� �ö󰣴��ؼ� �� �޸��Ҵ��ϴ°� �ƴ�!!
 *   
 *   [������̼�]
 *   1. �޸� �Ҵ� (������)
 *      => ���������� ��ɺ��� �����ؼ� ���
 *      @Component : �Ϲ�Ŭ���� => ~Manager, MainClass
 *      @Repository : ����� => ~DAO
 *      @Service : DAO �������� �����ؼ� ��� , BI
 *                 => ����� �����ؼ� ���
 *                 => �ǹ������� ���� ���� ���Ǵ� ������̼�
 *                 => ~Service
 *      @Controller : Model (��Ʈ��ġ : ~Action)
 *                 => BoardController
 *      @RestController : Model => �ڹٽ�ũ��Ʈ�� ����
 *       => VueJS
 *      @ControllerAdvice : ��� ModelŬ������ ����ó��
 *      @Configuration : application.xml �ڹٷ� ����
 *    
 *   2. DI
 *      @Autowired => �ڵ����� ��ü �ּҸ� ã�Ƽ� ����
 *                 => �ݵ�� ���������� �޸� �Ҵ��� �ؾ� �ڵ����� ����
 *      @
 *      @
 */

//�Ʒ��� ������ ���� annotation�� �ö󰣰͸� �޸��Ҵ��� �����ϵ��� ����� ����
@Component
class A
{
	public void display()
	{
		System.out.println("A:display call...");
	}
}
class B
{
	public void display()
	{
		System.out.println("B:display call...");
	}
}
@Component
class C
{
	public void display()
	{
		System.out.println("C:display call...");
	}
}
public class MainClass {
	public static void main(String[] args) {
		String[] cls= {"com.sist.main.A",
				"com.sist.main.B",
				"com.sist.main.C"};
		try {
			for(String s:cls)
			{
				Class clsName=Class.forName(s);
				if(clsName.isAnnotationPresent(Component.class)==false)
				{
					continue;
				}
				Object obj=clsName.getDeclaredConstructor().newInstance();
				System.out.println(obj);
			}
		}catch(Exception ex) {}
	}
}
