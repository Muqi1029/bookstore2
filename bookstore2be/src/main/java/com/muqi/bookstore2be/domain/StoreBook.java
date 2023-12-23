package com.muqi.bookstore2be.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName store_book
 */
@TableName(value ="store_book")
@Data
public class StoreBook implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private String storeId;

    /**
     * 
     */
    private String bookId;

    /**
     * 
     */
    private Integer stockLevel;

    /**
     * 
     */
    private Integer price;

    /**
     * 
     */
    private String title;

    /**
     * 
     */
    private String tags;

    /**
     * 
     */
    private String author;

    /**
     * 
     */
    private String publisher;

    /**
     * 
     */
    private String content;

    /**
     * 
     */
    private String bookIntro;

    /**
     * 
     */
    private String authorIntro;

    /**
     * 
     */
    private Integer pages;

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
        StoreBook other = (StoreBook) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getStoreId() == null ? other.getStoreId() == null : this.getStoreId().equals(other.getStoreId()))
            && (this.getBookId() == null ? other.getBookId() == null : this.getBookId().equals(other.getBookId()))
            && (this.getStockLevel() == null ? other.getStockLevel() == null : this.getStockLevel().equals(other.getStockLevel()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getTags() == null ? other.getTags() == null : this.getTags().equals(other.getTags()))
            && (this.getAuthor() == null ? other.getAuthor() == null : this.getAuthor().equals(other.getAuthor()))
            && (this.getPublisher() == null ? other.getPublisher() == null : this.getPublisher().equals(other.getPublisher()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getBookIntro() == null ? other.getBookIntro() == null : this.getBookIntro().equals(other.getBookIntro()))
            && (this.getAuthorIntro() == null ? other.getAuthorIntro() == null : this.getAuthorIntro().equals(other.getAuthorIntro()))
            && (this.getPages() == null ? other.getPages() == null : this.getPages().equals(other.getPages()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getStoreId() == null) ? 0 : getStoreId().hashCode());
        result = prime * result + ((getBookId() == null) ? 0 : getBookId().hashCode());
        result = prime * result + ((getStockLevel() == null) ? 0 : getStockLevel().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getTags() == null) ? 0 : getTags().hashCode());
        result = prime * result + ((getAuthor() == null) ? 0 : getAuthor().hashCode());
        result = prime * result + ((getPublisher() == null) ? 0 : getPublisher().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getBookIntro() == null) ? 0 : getBookIntro().hashCode());
        result = prime * result + ((getAuthorIntro() == null) ? 0 : getAuthorIntro().hashCode());
        result = prime * result + ((getPages() == null) ? 0 : getPages().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", storeId=").append(storeId);
        sb.append(", bookId=").append(bookId);
        sb.append(", stockLevel=").append(stockLevel);
        sb.append(", price=").append(price);
        sb.append(", title=").append(title);
        sb.append(", tags=").append(tags);
        sb.append(", author=").append(author);
        sb.append(", publisher=").append(publisher);
        sb.append(", content=").append(content);
        sb.append(", bookIntro=").append(bookIntro);
        sb.append(", authorIntro=").append(authorIntro);
        sb.append(", pages=").append(pages);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}