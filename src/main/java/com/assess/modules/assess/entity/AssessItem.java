package com.assess.modules.assess.entity;


import com.assess.common.validator.group.AddGroup;
import com.assess.common.validator.group.UpdateGroup;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@TableName("a_assess_item")
@Data
public class AssessItem implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 考核id
     */
    private Long assessId;

    /**
     * 名称
     */
    @NotBlank(message = "名称不能为空", groups = {AddGroup.class, UpdateGroup.class})

    private String name;

    /**
     * 评分标准
     */
    @NotBlank(message = "评分标准不能为空", groups = {AddGroup.class, UpdateGroup.class})

    private String standard;

    /**
     * 考核项说明
     */
    @NotBlank(message = "考核项说明不能为空", groups = {AddGroup.class, UpdateGroup.class})

    private String remark;

    /**
     * 总分值
     */
    @NotBlank(message = "总分值不能为空", groups = {AddGroup.class, UpdateGroup.class})

    private Integer totalScore;

    /**
     * 评分占比
     */
    private Integer ratio;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;
}