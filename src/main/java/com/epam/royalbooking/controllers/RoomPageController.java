package com.epam.royalbooking.controllers;

import com.epam.royalbooking.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RoomPageController {
    RoomService roomService;

    @RequestMapping(value = "room")
    public String getRoomPage(Model model, @ModelAttribute("roomId") String roomId) {
        model.addAttribute("room", roomService.getById(Integer.valueOf(roomId)));
        return "/room";
    }

    @Autowired
    public void setRoomService(RoomService roomService) {
        this.roomService = roomService;
    }
}
