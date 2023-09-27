package com.example.testcachetransaction.domain;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 
 * @TableName t_user
 */
@Data
public class TUser implements Serializable {

    private Integer age;

    private String name;

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
        TUser other = (TUser) that;
        return (this.getAge() == null ? other.getAge() == null : this.getAge().equals(other.getAge()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAge() == null) ? 0 : getAge().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", age=").append(age);
        sb.append(", name=").append(name);
        sb.append("]");
        return sb.toString();
    }
}