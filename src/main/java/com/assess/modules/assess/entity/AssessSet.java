package com.assess.modules.assess.entity;

import com.assess.common.validator.group.AddGroup;
import com.assess.common.validator.group.UpdateGroup;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@TableName("a_assess_set")
@Data
public class AssessSet implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.NONE)
    private Long id;

    /**
     * 标题
     */
    @NotBlank(message = "标题不能为空", groups = {AddGroup.class, UpdateGroup.class})

    private String title;

    /**
     * 考核开始日期
     */
    @NotBlank(message = "考核开始日期不能为空", groups = {AddGroup.class, UpdateGroup.class})

    private String startDate;

    /**
     * 考核结束日期
     */
    @NotBlank(message = "考核结束日期不能为空", groups = {AddGroup.class, UpdateGroup.class})

    private String endDate;

    /**
     * 考核说明
     */
    @NotBlank(message = "考核说明不能为空", groups = {AddGroup.class, UpdateGroup.class})

    private String remark;

    /**
     * 考核总分
     */
    @NotBlank(message = "考核总分不能为空", groups = {AddGroup.class, UpdateGroup.class})

    private Integer score;

    /**
     * 评分标准
     */
    @NotBlank(message = "评分标准不能为空", groups = {AddGroup.class, UpdateGroup.class})

    private String standard;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;
    /**
     * 考核项id集合
     */
    @TableField(exist = false)
    private List<Long> itemIdList;
    /**
     * 考核项集合
     */
    @TableField(exist = false)
    private List<AssessItem> items;

}