package cn.badguy.dream.testing;

import java.util.Scanner;

public class login {


    //insert
    public int[] insertData() {

        int[] arr = new int[10];
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < arr.length - 1; i++) {
            System.out.println("请输入第" + (i + 1) + "个数据：");
            int n = sc.nextByte();
            if (n == 0) {
                i--;
                System.out.println("输入数据不能为0，请重新插入！");
                continue;
            } else {
                arr[i] = n;
            }
        }
        return arr;
    }

    //show
    public void showData(int[] a, int length) {
        for (int i = 0; i < length; i++) {
            if (a[i] != 0) {
                System.out.print(a[i] + " ");
            }
        }
        System.out.println();
    }

    //insertAt
    public void insertAtArray(int[] a, int n, int k) {
        for (int i = a.length - 1; i >= k; i--) {
            a[i] = a[i - 1];
        }
        a[k] = n;
    }

    //divThree
    public void divThree(int[] a) {
        int count = 0;
        String result = "";
        for (int x : a) {
            if ((x != 0) && (x % 3 == 0)) {
                result += " " + x;
                count++;
            }
        }
        if (count != 0) {
            System.out.println(result);
        } else {
            System.out.println("没有数据可以被三整除");
        }
    }

    //notice
    public void notice() {
        System.out.println("**********************************");
        System.out.println("      1-插入数据");
        System.out.println("      2-显示所有数据");
        System.out.println("      3-在指定位置插入数据");
        System.out.println("      4-查询能被3整除的数据");
        System.out.println("      0-退出");
        System.out.println("**********************************");
        System.out.println("请输入对应的数字进行操作:");
    }


    public static void main(String[] args) {
        //项目准备
        login hw = new login();
        Scanner sc = new Scanner(System.in);

        //需要操作的数组
        int[] arr = new int[10];
        //结束标志
        boolean flag = true;

        //用户具体操作
        while (flag) {
            hw.notice();
            switch (sc.nextInt()) {
                case 1:
                    arr = hw.insertData();
                    break;
                case 2:
                    hw.showData(arr, 10);
                    break;
                case 3:
                    System.out.println("请输入要插入的数据：");
                    int n = sc.nextByte();
                    System.out.println("请输入要插入数据的位置：");
                    int k = sc.nextByte();
                    //如果需要防止数组越界，请打开注释
//                    if (k > 10) {
//                        System.out.println("不合法,超出数组范围");
//                        break;
//                    }
                    hw.insertAtArray(arr, n, k - 1);
                    break;
                case 4:
                    hw.divThree(arr);
                    break;
                case 0:
                    flag = false;
                    System.out.println("退出程序！");
                    break;
            }
        }


    }


}


