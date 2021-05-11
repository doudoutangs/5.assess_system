package com.assess.modules.assess.entity;

import com.assess.common.validator.group.AddGroup;
import com.assess.common.validator.group.UpdateGroup;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@TableName("a_assess_staff")
@Data
public class AssessStaff  implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 人员类型：1-受考核人员；2-有审批权限人员
     */
    private String type;

    /**
     * 员工id
     */
    @NotBlank(message="员工id不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Long userId;

    @TableField(exist = false)
    private List<Long> userIdList;
    /**
     * 考核id
     */
    @NotBlank(message="考核id不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Long assessId;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;
    @TableField(exist=false)
    private String typeName;
    @TableField(exist=false)
    private String userName;
    /**
     * 部门id
     */
    @NotBlank(message="部门id不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @TableField(exist = false)
    private Long deptId;
    @TableField(exist=false)
    private String deptName;
    @TableField(exist=false)
    private String assessTitle;

}