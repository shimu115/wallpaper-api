package com.shimu.wallpaper.api.controlloer;

import com.shimu.wallpaper.api.services.AcgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/acg/wallpaper")
public class AcgController {

    @Autowired
    private AcgService acgService;

    @GetMapping("/random")
    public void random(HttpServletResponse response, HttpServletRequest request) {
        acgService.random(response, request);
    }
}
