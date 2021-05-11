package com.assess.modules.assess.entity;

import com.assess.common.validator.group.AddGroup;
import com.assess.common.validator.group.UpdateGroup;
import com.assess.modules.sys.entity.SysFile;
import com.assess.modules.sys.entity.SysUser;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@TableName("a_assess_declare")
@Data
public class AssessDeclare implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 员工id
     */
    private Long userId;

    @TableField(exist = false)
    private SysUser user;
    /**
     * 考核id
     */
    private Long assessId;
    @TableField(exist = false)
    private AssessSet assessSet;
    @TableField(exist = false)
    private List<AssessItem> items;
    /**
     * 考核项id
     */
    private Long itemId;
    /**
     * 考核项名称
     */
    @TableField(exist = false)
    private String itemName;
    @TableField(exist = false)
    private List<AssessDeclare> declareList;

    @TableField(exist = false)
    private AssessApproval approval;
    /**
     * 类型:0-本次考核整体；1-单个考核项记录
     */
    private int type;
    /**
     * 考核项内容
     */
    @NotBlank(message="申报内容不能为空", groups = {AddGroup.class, UpdateGroup.class})

    private String content;

    /**
     * 自评分
     */
    @NotBlank(message="自评分不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Integer score;
    /**
     * 审批总分
     */
    private Integer approvalScore;

    /**
     * 附件
     */
    private String filePath;
    @TableField(exist = false)
    private SysFile sysFile;
    /**
     * 状态
     */
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