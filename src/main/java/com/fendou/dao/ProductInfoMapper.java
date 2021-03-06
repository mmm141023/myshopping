package com.fendou.dao;

import com.fendou.pojo.ProductInfo;
import java.util.List;

public interface ProductInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_product
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_product
     *
     * @mbg.generated
     */
    int insert(ProductInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_product
     *
     * @mbg.generated
     */
    ProductInfo selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_product
     *
     * @mbg.generated
     */
    List<ProductInfo> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_product
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ProductInfo record);
}