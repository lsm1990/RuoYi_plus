package com.ruoyi.app.controller.tool;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ruoyi.app.common.base.BaseAppController;

/**
 * swagger 接口
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/tool/swagger")
public class SwaggerController extends BaseAppController
{
    @GetMapping()
    public String index()
    {
        return redirect("/swagger-ui.html");
    }
}
