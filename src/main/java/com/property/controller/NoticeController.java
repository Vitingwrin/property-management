package com.property.controller;

import com.property.controller.result.Result;
import com.property.pojo.Notice;
import com.property.service.MessageService;
import com.property.service.NoticeService;
import org.springframework.web.bind.annotation.*;

/**
 * @author Chichiu Yeung
 * Created in 2019/4/11 9:59
 */
@RestController
@RequestMapping("/notice")
public class NoticeController {

    private final NoticeService noticeService;
    private final MessageService messageService;

    public NoticeController(NoticeService noticeService, MessageService messageService) {
        this.noticeService = noticeService;
        this.messageService = messageService;
    }

    @PostMapping("/saveNotice")
    public Result saveNotice(@RequestBody Notice notice) {
        if (notice.getId() == null) {
            noticeService.insertNotice(notice);
        } else {
            noticeService.updateNotice(notice);
        }
        return Result.success("发布成功");
    }

    @GetMapping("/getAllNotices")
    public Result getAllNotices() {
        return Result.success().add("notices", noticeService.getAllNotices());
    }

    @DeleteMapping("/deleteNotice")
    public Result deleteNotice(Integer id) {
        messageService.deleteMessageByNotice(id);
        noticeService.deleteNotice(id);
        return Result.success("删除成功");
    }

    @GetMapping("/checkTitleUnique")
    public Result checkTitleUnique(String title) {
        return noticeService.isTitleUnique(title) ? Result.success() : Result.error("标题已被占用");
    }

    @GetMapping("/getNoticesWithMsg")
    public Result getNoticeWithMsg() {
        return Result.success().add("notices", noticeService.getNoticesWithMsg());
    }
}
