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

    @RequestMapping(value = "/admin/rooms_list", method = RequestMethod.GET)
    public String getListForAdmin(Model model) {
        model.addAttribute("rooms", roomService.getAll());
        return "/rooms/rooms_list";
    }

    @RequestMapping(value = "/admin/room_add", method = RequestMethod.POST)
    public String save(@ModelAttribute("room") Room room) {
        roomService.save(room);
        return "redirect:/admin/rooms_list";
    }

    @RequestMapping(value = "/admin/rooms/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        roomService.delete(id);
        return "redirect:/admin/rooms_list";
    }

    @RequestMapping(value = "/admin/room_edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("room", roomService.getById(id));
        return "/rooms/room_edit";
    }

    @RequestMapping(value = "/admin/room_update", method = RequestMethod.POST)
    public String update(@ModelAttribute("room") Room room) {
        roomService.update(room);
        return "/rooms/room_edit";
    }

    @RequestMapping(value = "/admin/room_creation")
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
        imageSwitcher(roomType, model);
        return "rooms/room";
    }

    private void imageSwitcher(RoomType roomType, Model model){
        switch (roomType ){
            case BASIC:
                model.addAttribute("imageUrl_1", "/media/basic1.jpg");
                model.addAttribute("imageUrl_2", "/media/basic2.jpg");
                model.addAttribute("imageUrl_3", "/media/basic3.jpg");
                break;
            case FAMILY:
                model.addAttribute("imageUrl_1", "/media/family1.jpg");
                model.addAttribute("imageUrl_2", "/media/family2.jpg");
                model.addAttribute("imageUrl_3", "/media/family3.jpg");
                break;
            case LUXURY:
                model.addAttribute("imageUrl_1", "/media/luxury1.jpg");
                model.addAttribute("imageUrl_2", "/media/luxury2.jpg");
                model.addAttribute("imageUrl_3", "/media/luxury3.jpg");
                break;
            case PENTHOUSE:
                model.addAttribute("imageUrl_1", "/media/pent1.jpg");
                model.addAttribute("imageUrl_2", "/media/pent2.jpg");
                model.addAttribute("imageUrl_3", "/media/pent3.jpg");
                break;
        }
    }

    @Autowired
    public void setRoomService(RoomService roomService) {
        this.roomService = roomService;
    }
}
