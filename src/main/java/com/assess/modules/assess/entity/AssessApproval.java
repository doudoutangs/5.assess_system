package com.assess.modules.assess.entity;

import com.assess.common.validator.group.AddGroup;
import com.assess.common.validator.group.UpdateGroup;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@TableName("a_assess_approval")
@Data
public class AssessApproval  implements Serializable {
        /**
     * 主键
     */
    @TableId
    private Long id;

    /**
     * 审批人id
     */
    private Long userId;

    /**
     * 绩效申报id,关联个人绩效申报表
     */
    @NotBlank(message="绩效申报id不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Long declareId;

    /**
     * 打分
     */
    @NotBlank(message="审批评分不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Integer score;

    /**
     * 审批意见
     */
    @NotBlank(message="审批意见不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String comments;

    /**
     * 状态
     */
    @NotBlank(message="审批状态不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Integer status;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;


}