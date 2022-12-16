package com.zhanyang.taobaohelper;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**

 * @author: zzy
 * @date: 2022-12-16 11:14
 * @description: controller层
 **/
@RestController
@RequestMapping("/api/taoBao")
public class TaoBaoController {
    @Resource
    private TaoBaoHelperService taoBaoHelperService;

    @GetMapping
    public ResponseEntity taoBaoHelper(@RequestParam String time) throws Exception {
        taoBaoHelperService.taoBao(time);
        return new ResponseEntity("恭喜你消费成功",HttpStatus.OK);
    }
}
