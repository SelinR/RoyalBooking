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

    @RequestMapping(value = "/rooms", method = RequestMethod.GET)
    public String getAll(Model model) {
        model.addAttribute("rooms", roomService.getAll());
        return "/rooms";
    }

    @RequestMapping(value = "/rooms_admin", method = RequestMethod.GET)
    public String getListForAdmin(Model model) {
        model.addAttribute("rooms", roomService.getAll());
        return "/rooms/rooms_admin";
    }

    @RequestMapping(value = "/room/{roomId}")
    public String getRoomPage(Model model, @PathVariable("roomId") String roomId) {
        model.addAttribute("room", roomService.getById(Integer.valueOf(roomId)));
        return "/room";
    }

    @RequestMapping(value = "/room_add", method = RequestMethod.POST)
    public String save(@ModelAttribute("room") Room room) {
        roomService.save(room);
        /*roomService.update(room);*/
        return "redirect:/rooms_admin";
    }


    @RequestMapping(value = "/rooms/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        roomService.delete(id);
        return "redirect:/rooms_admin";
    }

    @RequestMapping(value = "/room_edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("room", roomService.getById(id));
        return "/rooms/room_edit";
    }

    @RequestMapping(value = "/room_update", method = RequestMethod.POST)
    public String update(@ModelAttribute("room") Room room, Model model) {
        roomService.update(room);
        return "/rooms/room_edit";
    }

    @RequestMapping(value = "/room_creation")
    public String getCreationPage() {
        return "/rooms/room_creation";
    }

    @Autowired
    public void setRoomService(RoomService roomService) {
        this.roomService = roomService;
    }
}
