package com.epam.royalbooking.controllers;

import com.epam.royalbooking.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomePageController {
    private RoomService roomService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getAll(Model model) {
        model.addAttribute("rooms", roomService.getAll());
        return "home_page";
    }

    @Autowired
    public void setRoomService(RoomService roomService) {
        this.roomService = roomService;
    }
}
