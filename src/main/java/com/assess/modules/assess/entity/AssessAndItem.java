package com.assess.modules.assess.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@TableName("a_assess_and_item")
@Data
public class AssessAndItem  implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 考核id
     */
    private Long assessId;

    /**
     * 考核项id
     */
    private Long itemId;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;
}