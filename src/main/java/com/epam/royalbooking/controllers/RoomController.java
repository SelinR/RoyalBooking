package com.epam.royalbooking.controllers;

import com.epam.royalbooking.enums.RoomType;
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

    @RequestMapping(value = "/admin/rooms", method = RequestMethod.GET)
    public String getListForAdmin(Model model) {
        model.addAttribute("rooms", roomService.getAll());
        return "/rooms/rooms_list";
    }

    @RequestMapping(value = "/admin/room/add", method = RequestMethod.POST)
    public String save(@ModelAttribute("room") Room room) {
        roomService.save(room);
        return "redirect:/admin/rooms";
    }

    @RequestMapping(value = "/admin/rooms/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        roomService.delete(id);
        return "redirect:/admin/rooms";
    }

    @RequestMapping(value = "/admin/room/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("room", roomService.getById(id));
        return "/rooms/room_edit";
    }

    @RequestMapping(value = "/admin/room/update", method = RequestMethod.POST)
    public String update(@ModelAttribute("room") Room room) {
        roomService.update(room);
        return "/rooms/room_edit";
    }

    @RequestMapping(value = "/admin/room/creation")
    public String getCreationPage() {
        return "/rooms/room_creation";
    }

    /**
     * Mapping for user room's single page
     */
    @RequestMapping(value = "/room/{roomId}")
    public String getRoomPage(Model model, @PathVariable("roomId") int roomId) {
        model.addAttribute("room", roomService.getById(roomId));
        RoomType roomType = roomService.getById(roomId).getRoomType();
        roomService.imageSwitcher(roomType, model);
        return "rooms/room";
    }

    @Autowired
    public void setRoomService(RoomService roomService) {
        this.roomService = roomService;
    }
}
