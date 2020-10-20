package com.java.yh.dao;

import com.java.yh.domain.Category;

import java.util.List;

public interface CategoryDAO {
    List<Category> queryAll();

    int insert(Category category);
}