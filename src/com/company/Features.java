package com.company;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Features {
    private int frequency = 0; // 每个方法调用的次数，每调用一次需要加一
    private boolean status = false; // 登录状态
    private String username; // 用户名
    private String password; // 密码
    private int userNumber = 0; // 添加的人员数量
    private String[][][] list = new String[100][5][2]; // 全局创建数组对象
    private Scanner input = new Scanner(System.in); // 获取用户输入的对象

    public Features() { }

    public Features(int frequency, boolean status, String username, String password) {
        this.frequency = frequency;
        this.status = status;
        this.username = username;
        this.password = password;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void user(){
        while (true) {
            if (status) {
                System.out.print("学生管理系统\n" +
                        "-----------------------------------\n" +
                        " \t1) 显示所有学生信息\n" +
                        " \t2) 查询单个学员信息\n" +
                        " \t3) 添加学员信息\n" +
                        " \t4) 修改学员信息（修改年龄和住址）\n" +
                        " \t5) 删除学员信息\n" +
                        " \t6) 返回\n" +
                        " \t7) 结束\n" +
                        "\n");
                System.out.print("请选择你要执行的操作：");
                int operating = input.nextInt();

                // 判断用户选择的操作
                switch (operating) {
                    case 1 -> {
                        // 打印所有学员信息
                        one();
                        frequency++;
                    }
                    case 2 -> {
                        //查询单个学员信息
                        inquire();
                        frequency++;
                    }
                    case 3 -> {
                        // 添加学员信息
                        information();
                        frequency++;
                    }
                    case 4 -> {
                        // 修改学员信息
                        int id = inquire();
                        fixIt(id);
                        frequency++;
                    }
                    case 5 -> {
                        //删除学员信息
                        int id2 = inquire();
                        remove(id2);
                        frequency++;
                    }
                    case 6 -> {
                        // 返回上一层
                        this.status = false;
                        frequency++;
                    }
                    case 7 -> {
                        // 结束程序
                        System.out.println("您使用学生管理系统" + frequency + "次。");
                        System.out.println("谢谢使用，学生管理系统，再见！");
                        return;
                    }
                    default -> System.out.println("你的输入有误！");
                }
            } else {
                System.out.print("------------学生管理系统-------------\n登录(1)/注册(2)/退出(0)：");
                int logIn = input.nextInt();
                if (logIn == 1) {
                    for (int i = 3; i > 0; i--) {
                        System.out.print("请输入用户名：");
                        String userName = input.next();
                        System.out.print("请输入密码：");
                        String userPassword = input.next();
                        if (Objects.equals(userName, username) && Objects.equals(userPassword, password)) {
                            status = true;
                            System.out.println("登录成功！");
                            break;
                        } else {
                            System.out.println("用户名或密码错误！");
                            System.out.println("还剩" + (i - 1) + "次登录机会");
                            if (i == 1){
                                return;
                            }
                        }
                    }
                } else if (logIn == 2) {
                    while (true) {
                        System.out.println("-----------------------------------");
                        System.out.print("请输入用户名：");
                        String username = input.next();
                        System.out.print("请输入秘密：");
                        String userPassword = input.next();
                        System.out.print("请确认密码：");
                        String userPassword1 = input.next();
                        if (Objects.equals(userPassword, userPassword1)) {
                            System.out.println("注册成功！");
                            this.username = username;
                            this.password = userPassword1;
                            this.status = true;
                            break;
                        } else {
                            System.out.println("两次密码不一致");
                        }
                    }
                }else {
                    return;
                }
            }
        }
    }

    public void one(){
        if (Objects.equals(list[0][0][0], null)){
            System.out.println("请先添加数据!");
            information();
        }else{
            for (int i = 0; i < list.length; i++) {
                if (list[i][0][0] != null){
                    System.out.println("----------------------------");
                    for (int j = 0; j < 5; j++) {
                        System.out.println(list[i][j][0] + " : " + list[i][j][1]);
                    }
                    System.out.println("----------------------------");
                }
            }
        }
    }

    public void information(){
        // [[["name","li"], ["age", "18"]], [["name","li"], ["age", "18"]]]
//        int userNumber = 0;
        list[userNumber][0][0] = "name";
        list[userNumber][1][0] = "age";
        list[userNumber][2][0] = "sex";
        list[userNumber][3][0] = "home";
        list[userNumber][4][0] = "studentId";

        for (int i = 0; i < list[userNumber].length; i++) {
            String[] user = list[userNumber][i];
            String userName = user[0];
            System.out.print("please enter " + userName + ":");
            user[1] = input.next();
        }
        userNumber++;
    }

    public int inquire(){
        System.out.print("请输入学生studentId:");
        String id = input.next();
        for (int i = 0; i < list.length; i++) {
            if (Objects.equals(id, list[i][4][1])){
                System.out.println("----------------------------");
                for (int j = 0; j < 5; j++) {
                    System.out.println(list[i][j][0] + " : " + list[i][j][1]);
                }
                System.out.println("----------------------------");
                return i;
            }
        }
        System.out.println("查无此人！");
        return -1;
    }

    public void fixIt(int i){
        System.out.print("请输年龄：");
        list[i][1][1] = input.next();
        System.out.println("请输入住址：");
        list[i][3][1] = input.next();
    }

    public void remove(int i){
        System.out.println("确认删除(y/n):");
        String yes = input.next();
        if (yes.equals("y")){
            for (int j = 0; j < 5; j++) {
                list[i][j][0] = null;
                list[i][j][1] = null;
            }
            System.out.println("删除成功");
        }else {
            System.out.println("取消");
        }

    }
}
