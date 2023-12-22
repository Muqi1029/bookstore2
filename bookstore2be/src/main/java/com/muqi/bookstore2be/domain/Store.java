package com.muqi.bookstore2be.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @TableName store
 */
@TableName(value = "store")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Store implements Serializable {
    /**
     *
     */
    @TableId
    private String storeId;

    /**
     * owner
     */
    private String userId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}