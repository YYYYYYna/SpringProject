package com.sist.spring;
/*
 * �������� ���¼ҽ� ���̺귯�� => Ȯ�强 (�����ؼ� ����� ����)
 *              ===============================
 *               �����ӿ�ũ(������ = �������� �����ӿ�ũ
 *                              ANY �����ӿ�ũ)
 * [�����ϴ� ���̺귯��]
 * ===============
 *  Core : 1. bean : ��ü���� => ��ü�Ҹ�
 *         <bean> : Ŭ���� �Ѱ�
 *         <context:component-scan> : ��Ű�� ������ (*)
 *          => �������� ������ ���� => ������̼��� �̿���
 *         2. xml���� �����ϰ� �ڹٸ� �̿� => @Bean (������̼�)
 *         ==========================================
 *  Data Access : JDBC / ORM(MyBatis,JPA) / JMS / OXM
 *                ======================== Transaction
 *  Web : Web / MVC
 *  AOP : ���� ��� => Commons~
 *        Transaction / Security
 *  ������ => Ŭ���� ������ (���α׷��� �°� ���� Ŭ������ ����)
 *          ����� ���� Ŭ���� / ���̺귯�� Ŭ����
 *          ===========�����̳�===========
 *  �����̳� => ��ü�� �����ϴ� ����
 *        => ��ü�� �����ֱ⸦ ��� (����/�Ҹ�)
 *  
 *  [���������� �����ϴ� �����̳�]
 *  ----------------------
 *            |
 *       BeanFactory
 *            |
 *     ApplicationContext
 *            |
 *     --------------------------------------------------------------------
 *     |                                  |                               |
 *  GenericXmlApplicationContext        AnntationConfigApplication      WebApplicationContext
 *   => close() : Application            => XML���� ���
 *   Ŭ������ ���� ��� / ������谡 ���� ���
 *   => �ڹ� ������ �ƴ�
 *       
 *     
 *  [�ǹ����� ����Ҷ�]
 *   ���������Ŭ���� => ������̼�
 *   ���̺귯��Ŭ���� => ����(XML)
 *   
 *  
 *  [������ ������ ��ũ�� Ư¡]
 *   1. �淮 �����̳�
 *      : Spring���� ��ü ���� => �Ҹ��� ���
 *        = DL => Ŭ������ ã�� => getBean()
 *        = DI => ��ü �����ÿ� ��������� �ʱⰪ�� �ʿ��� ��� => setterDI / ������ DI
 *                ��) �ڵ��α���, ������ ���̽� ó��
 *   2. POJO(Plain Old Java Object) ����� ����Ѵ� ==> 2.5����
 *      Ư�� �����ӿ�ũ ����� �������� �ʴ´�
 *      Plain : ��ӵ��� �ʴ� Ŭ���� (�׳� �⺻ �����Ǵ� Ŭ����)
 *      => OLD => �Ϲ��ڹ�
 *   3. �������� : Ŭ������ ���������� �����
 *   4. Ȯ�强�� �پ��
 *   5. ��� ���̺귯���� �����Ѵ�
 *   
 *   
 *   [�������� �ٽ�]
 *   1) DI ****
 *   2) DL 
 *   3) AOP ****
 *   4) DataBase (ORM)
 *   5) Transaction ****
 *   6) Web (MVC) ****
 *   7) Security
 *   ------------------
 *   DI => ������� �ʱ�ȭ
 *      
 *   ApplicationContext app=new ClassPa...("~.xml")
 *   ==> �̰� �� ������ ������ִ� �����̶� ��
 *   
 *   => ���̺귯���� �ڹټҽ��� �߰��� �� ����
 *      => ���̺귯������ �о �� �ִ� ������ �����ؼ� �Ѱ������
 *                   ==============
 *                    1. xml ����
 *                       => �±׸� / �Ӽ����� �ٸ��� �ν��� ���Ѵ�.
 *                    2. Annotation�� �ִ� ����
 *                       => ���������� �����ϴ� ������̼Ǹ� �̿��Ѵ�.
 */
//���������� �����ϴ� Ŭ����
public class Sawon {
	//1. setter DI
	private int sabun;
	private String name;
	private String sex;
	
	public int getSabun() {
		return sabun;
	}
	public void setSabun(int sabun) {
		this.sabun = sabun;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}	
}
