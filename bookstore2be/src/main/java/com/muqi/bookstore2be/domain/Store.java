package com.muqi.bookstore2be.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName store
 */
@TableName(value ="store")
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
     * 
     */
    private String bookId;

    /**
     * 
     */
    private String bookInfo;

    /**
     * 
     */
    private Integer stockLevel;

    /**
     * owner
     */
    private String userId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Store other = (Store) that;
        return (this.getStoreId() == null ? other.getStoreId() == null : this.getStoreId().equals(other.getStoreId()))
            && (this.getBookId() == null ? other.getBookId() == null : this.getBookId().equals(other.getBookId()))
            && (this.getBookInfo() == null ? other.getBookInfo() == null : this.getBookInfo().equals(other.getBookInfo()))
            && (this.getStockLevel() == null ? other.getStockLevel() == null : this.getStockLevel().equals(other.getStockLevel()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getStoreId() == null) ? 0 : getStoreId().hashCode());
        result = prime * result + ((getBookId() == null) ? 0 : getBookId().hashCode());
        result = prime * result + ((getBookInfo() == null) ? 0 : getBookInfo().hashCode());
        result = prime * result + ((getStockLevel() == null) ? 0 : getStockLevel().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", storeId=").append(storeId);
        sb.append(", bookId=").append(bookId);
        sb.append(", bookInfo=").append(bookInfo);
        sb.append(", stockLevel=").append(stockLevel);
        sb.append(", userId=").append(userId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}