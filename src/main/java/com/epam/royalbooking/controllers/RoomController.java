package com.epam.royalbooking.controllers;

import com.epam.royalbooking.services.RoomService;
import com.epam.royalbooking.entities.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RoomController {
    private RoomService roomService;

    @RequestMapping(value = "view/rooms", method = RequestMethod.GET)
    public String getAll(Model model) {
        model.addAttribute("room", new Room());
        model.addAttribute("rooms", roomService.getAll());
        return "/rooms";
    }

    @RequestMapping("view/rooms/room/{id}")
    public String getById(@PathVariable("id") int id, Model model){
        model.addAttribute("room", roomService.getById(id));
        return "/room";
    }

    @RequestMapping(value = "view/rooms", method = RequestMethod.POST)
    public String save(@ModelAttribute("room") Room room) {
        if(room.getId() == 0) {
            roomService.save(room);
        } else {
            roomService.update(room);
        }
        return "redirect:/view/rooms";
    }

    @RequestMapping(value = "view/rooms/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        roomService.delete(id);
        return "redirect:/view/rooms";
    }

    @RequestMapping(value = "view/rooms/edit/{id}")
    public String update(@PathVariable("id") int id, Model model) {
        model.addAttribute("room", roomService.getById(id));
        model.addAttribute("rooms", roomService.getAll());
        return "/rooms";
    }

    @Autowired
    public void setRoomService(RoomService roomService) {
        this.roomService = roomService;
    }
}
