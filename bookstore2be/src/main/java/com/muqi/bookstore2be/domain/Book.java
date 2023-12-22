package com.muqi.bookstore2be.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import lombok.Data;

/**
 * 
 * @TableName book
 */
@TableName(value ="book")
@Data
public class Book implements Serializable {
    /**
     * 
     */
    @TableId
    private String id;

    /**
     * 
     */
    private String title;

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
    private String originalTitle;

    /**
     * 
     */
    private String translator;

    /**
     * 
     */
    private String pubYear;

    /**
     * 
     */
    private Integer pages;

    /**
     * 
     */
    private Integer price;

    /**
     * 
     */
    private String currencyUnit;

    /**
     * 
     */
    private String binding;

    /**
     * 
     */
    private String isbn;

    /**
     * 
     */
    private String authorIntro;

    /**
     * 
     */
    private String bookIntro;

    /**
     * 
     */
    private String content;

    /**
     * 
     */
    private String tags;

    /**
     * 
     */
    private byte[] picture;

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
        Book other = (Book) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getAuthor() == null ? other.getAuthor() == null : this.getAuthor().equals(other.getAuthor()))
            && (this.getPublisher() == null ? other.getPublisher() == null : this.getPublisher().equals(other.getPublisher()))
            && (this.getOriginalTitle() == null ? other.getOriginalTitle() == null : this.getOriginalTitle().equals(other.getOriginalTitle()))
            && (this.getTranslator() == null ? other.getTranslator() == null : this.getTranslator().equals(other.getTranslator()))
            && (this.getPubYear() == null ? other.getPubYear() == null : this.getPubYear().equals(other.getPubYear()))
            && (this.getPages() == null ? other.getPages() == null : this.getPages().equals(other.getPages()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getCurrencyUnit() == null ? other.getCurrencyUnit() == null : this.getCurrencyUnit().equals(other.getCurrencyUnit()))
            && (this.getBinding() == null ? other.getBinding() == null : this.getBinding().equals(other.getBinding()))
            && (this.getIsbn() == null ? other.getIsbn() == null : this.getIsbn().equals(other.getIsbn()))
            && (this.getAuthorIntro() == null ? other.getAuthorIntro() == null : this.getAuthorIntro().equals(other.getAuthorIntro()))
            && (this.getBookIntro() == null ? other.getBookIntro() == null : this.getBookIntro().equals(other.getBookIntro()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getTags() == null ? other.getTags() == null : this.getTags().equals(other.getTags()))
            && (Arrays.equals(this.getPicture(), other.getPicture()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getAuthor() == null) ? 0 : getAuthor().hashCode());
        result = prime * result + ((getPublisher() == null) ? 0 : getPublisher().hashCode());
        result = prime * result + ((getOriginalTitle() == null) ? 0 : getOriginalTitle().hashCode());
        result = prime * result + ((getTranslator() == null) ? 0 : getTranslator().hashCode());
        result = prime * result + ((getPubYear() == null) ? 0 : getPubYear().hashCode());
        result = prime * result + ((getPages() == null) ? 0 : getPages().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getCurrencyUnit() == null) ? 0 : getCurrencyUnit().hashCode());
        result = prime * result + ((getBinding() == null) ? 0 : getBinding().hashCode());
        result = prime * result + ((getIsbn() == null) ? 0 : getIsbn().hashCode());
        result = prime * result + ((getAuthorIntro() == null) ? 0 : getAuthorIntro().hashCode());
        result = prime * result + ((getBookIntro() == null) ? 0 : getBookIntro().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getTags() == null) ? 0 : getTags().hashCode());
        result = prime * result + (Arrays.hashCode(getPicture()));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", title=").append(title);
        sb.append(", author=").append(author);
        sb.append(", publisher=").append(publisher);
        sb.append(", originalTitle=").append(originalTitle);
        sb.append(", translator=").append(translator);
        sb.append(", pubYear=").append(pubYear);
        sb.append(", pages=").append(pages);
        sb.append(", price=").append(price);
        sb.append(", currencyUnit=").append(currencyUnit);
        sb.append(", binding=").append(binding);
        sb.append(", isbn=").append(isbn);
        sb.append(", authorIntro=").append(authorIntro);
        sb.append(", bookIntro=").append(bookIntro);
        sb.append(", content=").append(content);
        sb.append(", tags=").append(tags);
        sb.append(", picture=").append(picture);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}