package com.assess.modules.sys.controller;

import com.assess.common.utils.R;
import com.assess.modules.sys.entity.SysFile;
import com.assess.modules.sys.service.SysFileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件上传
 */
@Slf4j
@Controller
@RequestMapping("sys/file")
public class SysFileController {
    @Value("${file.upload}")
    String uploadPath;

    @Autowired
    SysFileService sysFileService;

    /**
     * 添加附件
     *
     * @param file
     * @return
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public R upload(@RequestParam(value = "file", required = false) MultipartFile file,
                    HttpServletRequest request) {
        String id = RandomStringUtils.randomNumeric(10);
        log.info("上传文件开始！");
        long startTime = System.currentTimeMillis();
        //判断文件夹是否存在
        File direct = new File(uploadPath);
        if (!direct.isDirectory()) {
            //创建文件夹
            direct.mkdirs();
            log.info("创建文件夹：{}", uploadPath);
        }
        if (!file.getOriginalFilename().isEmpty()) {
            String path = uploadPath + file.getOriginalFilename();
            log.info("fileName：{}", path);
            //保存文件记录
            File newFile = new File(path);
            SysFile sysFile = new SysFile();
            String filename = file.getOriginalFilename();
            sysFile.setName(filename);
            sysFile.setRelationId(id);
            sysFile.setPath(path);
            sysFile.setSuffix(filename.substring(filename.lastIndexOf(".") + 1));
            sysFileService.save(sysFile);
            try {
                //上传日志
                file.transferTo(newFile);
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }

        }
        long endTime = System.currentTimeMillis();
        log.info("运行时间：{} ms", String.valueOf(endTime - startTime));
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("relationId", id);
        return R.ok(data);

    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public ResponseEntity<byte[]> download(HttpServletResponse response, HttpServletRequest request, String relationId) {
        List<SysFile> sysFiles = sysFileService.queryByRelationId(relationId);
        HttpHeaders headers = new HttpHeaders();
        // 响应头设置ContentType
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        if (null != sysFiles) {
            try {
                SysFile sysFile = sysFiles.get(0);
                String abstFilePath = sysFile.getPath();
                // 附件形式
                headers.setContentDispositionFormData("attachment",
                        URLEncoder.encode(sysFile.getName(), "UTF-8"));
                return new ResponseEntity<byte[]>(Files.readAllBytes(Paths
                        .get(abstFilePath)), headers, HttpStatus.OK);
            } catch (IOException e) {
                return new ResponseEntity<byte[]>(null, headers,
                        HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<byte[]>(null, headers,
                    HttpStatus.NOT_FOUND);
        }
    }

    /**
     * 删除附件
     *
     * @param id
     * @return
     */
//	@RequestMapping(value = "/delete")
//	@ResponseBody
//	public Object delete(String id) {
//		fileService.delete(id);
//		return resultSuccess("删除成功！");
//	}

}
