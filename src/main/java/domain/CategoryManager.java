package com.java.yh.domain;

import com.java.yh.dao.CategoryDAO;
import com.java.yh.dao.daoImpl.CategoryDAOImpl;


import java.util.ArrayList;
import java.util.List;

// 充当Service的角色
public class CategoryManager {
    private static List<Category> categories;

    // Manager内部自动地获取数据库当中的内容，并填充到categories的静态成员内部
    static {
        CategoryDAO dao = new CategoryDAOImpl();
        categories = dao.queryAll();
    }

    // 根据等级查询
    // 场景: 列举所有的1级类别
    public static List<Category> queryByLevel(int level) {
        // 返回值空数组
        List<Category> result = new ArrayList<>();
        System.out.println("我进来了Level");
        // 遍历所有的categories，将满足条件的结果，添加到result内部
        for (Category category : categories) {
            if (category.getLevel() == level) {
                result.add(category);
            }
        }

        return result;
    }

    // 根据父类别查询
    // 场景: 1级类别选中了家用电器，第2个select应该列出所有家用电器的子类别
    public static List<Category> queryByParent(int parentId) {
        // 返回值空数组
        List<Category> result = new ArrayList<>();

        System.out.println("我进来了Parent");

        // 遍历所有的categories，将满足条件的结果，添加到result内部
        if (parentId !=0) {
            for (Category category : categories) {
                // 如果传进来的parentId为null
                if (parentId==category.getParent()) {
                    result.add(category);
                }
            }
        }


        return result;
    }

    // 数据库的内容和Manager内部的内容，要一致

    // 1数据库，2Manager，要保证两个地方的数据是一致的
    public static void add(Category category) {
        // 1. 插入到数据库当中
        CategoryDAO dao = new CategoryDAOImpl();
        int count = dao.insert(category);

        // 2. 添加到categories静态成员当中
        if (count == 1) {
            // 插入成功
            categories.add(category);
        }
    }

}
