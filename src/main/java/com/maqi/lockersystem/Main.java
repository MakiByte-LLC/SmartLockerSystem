package com.maqi.lockersystem;

import com.maqi.lockersystem.models.LockerSystem;
import com.maqi.lockersystem.models.Package;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 初始化 LockerSystem（柜子大小：1, 2, 3）
        LockerSystem system = new LockerSystem(new int[]{1, 2, 3});
        Scanner scanner = new Scanner(System.in);

        System.out.println("欢迎使用储物柜系统！可用操作：store, retrieve, exit");

        while (true) {
            System.out.print("\n请输入操作 (store/retrieve/exit): ");
            String command = scanner.nextLine().trim().toLowerCase();

            if (command.equals("store")) {
                System.out.print("输入包裹 ID: ");
                int packageId = scanner.nextInt();
                System.out.print("输入包裹大小: ");
                int packageSize = scanner.nextInt();
                scanner.nextLine(); // 处理换行符

                Package p = new Package(packageId, packageSize);
                if (system.storePackage(p)) {
                    System.out.println("✅ 包裹 " + packageId + " 存放成功！");
                } else {
                    System.out.println("❌ 存放失败，没有合适的柜子！");
                }

            } else if (command.equals("retrieve")) {
                System.out.print("输入要取出的包裹 ID: ");
                int packageId = scanner.nextInt();
                scanner.nextLine(); // 处理换行符

                Package retrieved = system.retrievePackage(packageId);
                if (retrieved != null) {
                    System.out.println("✅ 取出包裹：" + retrieved.getId());
                } else {
                    System.out.println("❌ 取出失败，包裹不存在！");
                }

            } else if (command.equals("exit")) {
                System.out.println("🚪 退出储物柜系统，感谢使用！");
                break;
            } else {
                System.out.println("⚠️ 无效操作，请输入 store/retrieve/exit！");
            }
        }

        scanner.close();
    }
}
